package app.nzyme.core.rest.responses.nodes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import org.joda.time.DateTime;

@AutoValue
public abstract class NodeResponse {

    @JsonProperty("uuid")
    public abstract String uuid();

    @JsonProperty("name")
    public abstract String name();

    @JsonProperty("active")
    public abstract Boolean active();

    @JsonProperty("http_external_uri")
    public abstract String httpExternalUri();

    @JsonProperty("memory_bytes_total")
    public abstract long memoryBytesTotal();

    @JsonProperty("memory_bytes_available")
    public abstract long memoryBytesAvailable();

    @JsonProperty("memory_bytes_used")
    public abstract long memoryBytesUsed();

    @JsonProperty("heap_bytes_total")
    public abstract long heapBytesTotal();

    @JsonProperty("heap_bytes_available")
    public abstract long heapBytesAvailable();

    @JsonProperty("heap_bytes_used")
    public abstract long heapBytesUsed();

    @JsonProperty("cpu_system_load")
    public abstract double cpuSystemLoad();

    @JsonProperty("cpu_thread_count")
    public abstract int cpuThreadCount();

    @JsonProperty("process_start_time")
    public abstract DateTime processStartTime();

    @JsonProperty("process_virtual_size")
    public abstract long processVirtualSize();

    @JsonProperty("process_arguments")
    public abstract String processArguments();

    @JsonProperty("os_information")
    public abstract String osInformation();

    @JsonProperty("version")
    public abstract String version();

    @JsonProperty("last_seen")
    public abstract DateTime lastSeen();

    @JsonProperty("deleted")
    public abstract boolean deleted();

    @JsonProperty("clock")
    public abstract DateTime clock();

    @JsonProperty("clock_drift_ms")
    public abstract long clockDriftMs();

    @JsonProperty("is_ephemeral")
    public abstract boolean isEphemeral();

    public static NodeResponse create(String uuid, String name, Boolean active, String httpExternalUri, long memoryBytesTotal, long memoryBytesAvailable, long memoryBytesUsed, long heapBytesTotal, long heapBytesAvailable, long heapBytesUsed, double cpuSystemLoad, int cpuThreadCount, DateTime processStartTime, long processVirtualSize, String processArguments, String osInformation, String version, DateTime lastSeen, boolean deleted, DateTime clock, long clockDriftMs, boolean isEphemeral) {
        return builder()
                .uuid(uuid)
                .name(name)
                .active(active)
                .httpExternalUri(httpExternalUri)
                .memoryBytesTotal(memoryBytesTotal)
                .memoryBytesAvailable(memoryBytesAvailable)
                .memoryBytesUsed(memoryBytesUsed)
                .heapBytesTotal(heapBytesTotal)
                .heapBytesAvailable(heapBytesAvailable)
                .heapBytesUsed(heapBytesUsed)
                .cpuSystemLoad(cpuSystemLoad)
                .cpuThreadCount(cpuThreadCount)
                .processStartTime(processStartTime)
                .processVirtualSize(processVirtualSize)
                .processArguments(processArguments)
                .osInformation(osInformation)
                .version(version)
                .lastSeen(lastSeen)
                .deleted(deleted)
                .clock(clock)
                .clockDriftMs(clockDriftMs)
                .isEphemeral(isEphemeral)
                .build();
    }

    public static Builder builder() {
        return new AutoValue_NodeResponse.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder uuid(String uuid);

        public abstract Builder name(String name);

        public abstract Builder active(Boolean active);

        public abstract Builder httpExternalUri(String httpExternalUri);

        public abstract Builder memoryBytesTotal(long memoryBytesTotal);

        public abstract Builder memoryBytesAvailable(long memoryBytesAvailable);

        public abstract Builder memoryBytesUsed(long memoryBytesUsed);

        public abstract Builder heapBytesTotal(long heapBytesTotal);

        public abstract Builder heapBytesAvailable(long heapBytesAvailable);

        public abstract Builder heapBytesUsed(long heapBytesUsed);

        public abstract Builder cpuSystemLoad(double cpuSystemLoad);

        public abstract Builder cpuThreadCount(int cpuThreadCount);

        public abstract Builder processStartTime(DateTime processStartTime);

        public abstract Builder processVirtualSize(long processVirtualSize);

        public abstract Builder processArguments(String processArguments);

        public abstract Builder osInformation(String osInformation);

        public abstract Builder version(String version);

        public abstract Builder lastSeen(DateTime lastSeen);

        public abstract Builder deleted(boolean deleted);

        public abstract Builder clock(DateTime clock);

        public abstract Builder clockDriftMs(long clockDriftMs);

        public abstract Builder isEphemeral(boolean isEphemeral);

        public abstract NodeResponse build();
    }
}
