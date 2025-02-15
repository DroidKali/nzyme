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

package app.nzyme.core.configuration;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class DeauthenticationMonitorConfiguration {

    public abstract int globalThreshold();

    public static DeauthenticationMonitorConfiguration create(int globalThreshold) {
        return builder()
                .globalThreshold(globalThreshold)
                .build();
    }

    public static Builder builder() {
        return new AutoValue_DeauthenticationMonitorConfiguration.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder globalThreshold(int globalThreshold);

        public abstract DeauthenticationMonitorConfiguration build();
    }

}
