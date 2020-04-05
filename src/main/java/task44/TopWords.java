package task44;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by konstantin on 05.04.2020.
 */
public class TopWords {
    private static final Pattern WORD = Pattern.compile("\\b[A-Za-z']+\\b");
    private static final Comparator<Map.Entry<String, AtomicInteger>> ENTRY_COMPARATOR = (o1, o2) ->
            -Integer.compare(o1.getValue().get(), o2.getValue().get());

    public static List<String> top3(String s) {
        if (s == null || s.isEmpty())
        {
            return Collections.emptyList();
        }
        Map<String, AtomicInteger> topWords = new HashMap<>();
        Matcher matcher = WORD.matcher(s);
        while (matcher.find())
        {
            topWords.computeIfAbsent(matcher.group().toLowerCase(), v -> new AtomicInteger()).incrementAndGet();
        }
        return topWords.entrySet().stream()
                .sorted(ENTRY_COMPARATOR)
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        /*return Arrays.stream(s.split("\\s+"))
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue((o1, o2) -> -Long.compare(o1, o2)))
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());*/
    }
}
