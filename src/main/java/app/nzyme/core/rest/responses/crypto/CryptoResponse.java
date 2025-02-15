package app.nzyme.core.rest.responses.crypto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

import java.util.Map;


@AutoValue
public abstract class CryptoResponse {

    @JsonProperty("metrics")
    public abstract CryptoNodeMetricsResponse metrics();

    @JsonProperty("pgp_keys")
    public abstract Map<String, PGPKeyResponse> pgpKeys();

    @JsonProperty("pgp_keys_in_sync")
    public abstract boolean pgpKeysInSync();

    public static CryptoResponse create(CryptoNodeMetricsResponse metrics, Map<String, PGPKeyResponse> pgpKeys, boolean pgpKeysInSync) {
        return builder()
                .metrics(metrics)
                .pgpKeys(pgpKeys)
                .pgpKeysInSync(pgpKeysInSync)
                .build();
    }

    public static Builder builder() {
        return new AutoValue_CryptoResponse.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder metrics(CryptoNodeMetricsResponse metrics);

        public abstract Builder pgpKeys(Map<String, PGPKeyResponse> pgpKeys);

        public abstract Builder pgpKeysInSync(boolean pgpKeysInSync);

        public abstract CryptoResponse build();
    }

}
