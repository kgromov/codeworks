package task05;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class EnoughIsEnough {
    public static int[] deleteNth(int[] elements, int maxOccurrences) {
        Map<Integer, AtomicInteger> occurrences = new HashMap<>();
        return Arrays.stream(elements)
                .filter(i -> occurrences.computeIfAbsent(i, v -> new AtomicInteger()).incrementAndGet() <= maxOccurrences)
                .toArray();
    }
}
