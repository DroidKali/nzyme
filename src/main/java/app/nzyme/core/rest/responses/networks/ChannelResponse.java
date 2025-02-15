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

package app.nzyme.core.rest.responses.networks;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import app.nzyme.core.dot11.networks.signalstrength.tracks.SignalWaterfallHistogram;
import app.nzyme.core.dot11.networks.signalstrength.tracks.Track;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@AutoValue
public abstract class ChannelResponse {

    @JsonProperty("channel_number")
    public abstract int channelNumber();

    @JsonProperty("bssid")
    public abstract String bssid();

    @JsonProperty("ssid")
    public abstract String ssid();

    @JsonProperty("total_frames")
    public abstract long totalFrames();

    @JsonProperty("total_frames_recent")
    public abstract long totalFramesRecent();

    @JsonProperty("fingerprints")
    public abstract List<String> fingerprints();

    @JsonProperty("signal_index_distribution")
    public abstract Map<Integer, AtomicLong> signalIndexDistribution();

    @JsonProperty("signal_index_distribution_minutes")
    public abstract int signalIndexDistributionMinutes();

    @JsonProperty("signal_index_history")
    @Nullable
    public abstract SignalWaterfallHistogram signalIndexHistory();

    @JsonProperty("signal_index_tracks")
    @Nullable
    public abstract List<Track> signalIndexTracks();

    public static ChannelResponse create(int channelNumber, String bssid, String ssid, long totalFrames, long totalFramesRecent, List<String> fingerprints, Map<Integer, AtomicLong> signalIndexDistribution, int signalIndexDistributionMinutes, SignalWaterfallHistogram signalIndexHistory, List<Track> signalIndexTracks) {
        return builder()
                .channelNumber(channelNumber)
                .bssid(bssid)
                .ssid(ssid)
                .totalFrames(totalFrames)
                .totalFramesRecent(totalFramesRecent)
                .fingerprints(fingerprints)
                .signalIndexDistribution(signalIndexDistribution)
                .signalIndexDistributionMinutes(signalIndexDistributionMinutes)
                .signalIndexHistory(signalIndexHistory)
                .signalIndexTracks(signalIndexTracks)
                .build();
    }

    public static Builder builder() {
        return new AutoValue_ChannelResponse.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder channelNumber(int channelNumber);

        public abstract Builder bssid(String bssid);

        public abstract Builder ssid(String ssid);

        public abstract Builder totalFrames(long totalFrames);

        public abstract Builder totalFramesRecent(long totalFramesRecent);

        public abstract Builder fingerprints(List<String> fingerprints);

        public abstract Builder signalIndexDistribution(Map<Integer, AtomicLong> signalIndexDistribution);

        public abstract Builder signalIndexDistributionMinutes(int signalIndexDistributionMinutes);

        public abstract Builder signalIndexHistory(SignalWaterfallHistogram signalIndexHistory);

        public abstract Builder signalIndexTracks(List<Track> signalIndexTracks);

        public abstract ChannelResponse build();
    }

}
