/*
 * This file is part of nzyme.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the Server Side Public License, version 1,
 * as published by MongoDB, Inc.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * Server Side Public License for more details.
 *
 * You should have received a copy of the Server Side Public License
 * along with this program. If not, see
 * <http://www.mongodb.com/licensing/server-side-public-license>.
 */

package app.nzyme.core.bandits.trackers.trackerlogic;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import app.nzyme.core.NzymeTracker;
import app.nzyme.core.bandits.trackers.TrackerState;
import app.nzyme.core.bandits.trackers.protobuf.TrackerMessage;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class TrackerStateWatchdog {

    public static final int WEAK_RSSI_LIMIT = 165;

    private final NzymeTracker nzyme;

    private final AtomicReference<List<TrackerState>> states;

    private final AtomicReference<Optional<DateTime>> lastPingReceived;
    private final AtomicReference<Optional<Integer>> lastRSSIReceived;

    public TrackerStateWatchdog(NzymeTracker nzyme) {
        this.nzyme = nzyme;

        this.states = new AtomicReference<>(new ArrayList<>(){{
            add(TrackerState.DARK);
        }});

        this.lastPingReceived = new AtomicReference<>(Optional.empty());
        this.lastRSSIReceived = new AtomicReference<>(Optional.empty());
    }

    public void initialize() {
        Executors.newSingleThreadScheduledExecutor(new ThreadFactoryBuilder()
                .setDaemon(true)
                .setNameFormat("trackerstate-watchdog-%d")
                .build())
                .scheduleAtFixedRate(() -> {
                    List<TrackerState> previousState = Lists.newArrayList(states.get());
                    List<TrackerState> result = Lists.newArrayList();

                    if (lastPingReceived.get().isEmpty()) {
                        // Never received a ping at all.
                        result.add(TrackerState.DARK);
                    } else {
                        // Are we still connected?
                        lastPingReceived.get().ifPresent(time -> {
                            if (time.isAfter(DateTime.now().minusSeconds(20))) {
                                result.add(TrackerState.ONLINE);

                                // Is the signal weak?
                                lastRSSIReceived.get().ifPresent(rssi -> {
                                    if (rssi < WEAK_RSSI_LIMIT) {
                                        result.add(TrackerState.WEAK);
                                    }
                                });
                            } else {
                                // Last ping was too long ago.
                                result.add(TrackerState.DARK);
                            }
                        });
                    }

                    states.set(result);

                    Collections.sort(result);
                    Collections.sort(previousState);
                    if (!result.equals(previousState)) {
                        stateChanged();
                    }
                }, 5, 10, TimeUnit.SECONDS);
    }

    public void registerPing(TrackerMessage.Ping ping, int rssi) {
        if (ping.getNodeType() != TrackerMessage.Ping.NodeType.LEADER) {
            return;
        }

        this.lastPingReceived.set(Optional.of(DateTime.now()));
        this.lastRSSIReceived.set(Optional.of(rssi));
    }

    public List<TrackerState> getStates() {
        return new ArrayList<>(states.get());
    }

    private void stateChanged() {
        nzyme.getGroundStation().handleTrackerConnectionStateChange(states.get());
    }

}
