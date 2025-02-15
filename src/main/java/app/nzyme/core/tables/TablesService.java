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

package app.nzyme.core.tables;

import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import app.nzyme.core.NzymeNode;
import app.nzyme.core.tables.dns.DNSTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TablesService {

    private static final Logger LOG = LogManager.getLogger(TablesService.class);

    private final NzymeNode nzyme;

    private final Map<String, DataTable> tables;

    public TablesService(NzymeNode nzyme) {
        this.nzyme = nzyme;

        this.tables = new ImmutableMap.Builder<String, DataTable>()
                .put("dns", new DNSTable(this))
                .build();

        Executors.newSingleThreadScheduledExecutor(
                new ThreadFactoryBuilder()
                        .setNameFormat("tables-cleaner-%d")
                        .setDaemon(true)
                        .build()
        ).scheduleAtFixedRate(this::retentionClean, 0, 1, TimeUnit.HOURS);
    }

    private void retentionClean() {
        for (Map.Entry<String, DataTable> table : tables.entrySet()) {
            LOG.debug("Retention cleaning data table [{}].", table.getKey());
            table.getValue().retentionClean();
        }

    }

    public DNSTable dns() {
        return (DNSTable) tables.get("dns");
    }

    public NzymeNode getNzyme() {
        return nzyme;
    }

}
