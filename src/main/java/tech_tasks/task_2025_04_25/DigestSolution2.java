package tech_tasks.task_2025_04_25;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class DigestSolution2 {

    private final Map<ByteArrayKey, byte[]> cache = new ConcurrentHashMap<>(1000);

    private static final int MAX_CACHE_SIZE = 10000;

    public byte[] digest(byte[] input) {
        byte[] inputCopy = Arrays.copyOf(input, input.length);
        var key = new ByteArrayKey(inputCopy);
        byte[] result = cache.get(key);
        if (result == null) {
            result = doDigest(inputCopy);
            if (cache.size() < MAX_CACHE_SIZE) {
                cache.put(key, result);
            }
        }
        return Arrays.copyOf(result, result.length);
    }

    protected abstract byte[] doDigest(byte[] input);

    private record ByteArrayKey(byte[] bytes, int hashcode) {
        private ByteArrayKey(byte[] bytes) {
            this(bytes, Arrays.hashCode(bytes));
        }
    }

}