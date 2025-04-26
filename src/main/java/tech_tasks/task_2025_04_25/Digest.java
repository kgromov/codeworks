package tech_tasks.task_2025_04_25;

import java.util.HashMap;
import java.util.Map;

public abstract class Digest {

    private Map<byte[], byte[]> cache = new HashMap<>();

    public byte[] digest(byte[] input) {
        byte[] result = cache.get(input);
        if (result == null) {
            synchronized (cache) {
                result = cache.get(input);
                if (result == null) {
                    result = doDigest(input);
                    cache.put(input, result);
                }
            }
        }
        return result;
    }

    protected abstract byte[] doDigest(byte[] input);
}