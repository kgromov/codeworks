package tech_tasks.task_2025_04_25;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class DigestSolution1 {
    private static final int INITIAL_CAPACITY = 1024;
    private final Map<CacheKey, byte[]> cache = new HashMap<>(INITIAL_CAPACITY);
//    private final Map<CacheKey, byte[]> cache = new ConcurrentHashMap<>(INITIAL_CAPACITY);

    public byte[] digest(byte[] input) {
        var key = new CacheKey(new String(input, StandardCharsets.UTF_8).hashCode());
        synchronized (this) {
            byte[] result = cache.get(key);
            if (result == null) {
                result = doDigest(input);
                cache.put(key, result);
            }
            return Arrays.copyOf(result, result.length);
        }
    }

    protected abstract byte[] doDigest(byte[] input);

    private record CacheKey(int code) {
    }
}