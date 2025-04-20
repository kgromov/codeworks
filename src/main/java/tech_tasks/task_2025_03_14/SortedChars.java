package tech_tasks.task_2025_03_14;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SortedChars {
    public static List<Character> toSortedChartList(String word) {
        if (word == null || word.isEmpty()) {
            return Collections.emptyList();
        }
        return word.chars().mapToObj(c -> (char) c).sorted(Character::compareTo).collect(Collectors.toList());
    }

    public static String toSortedCharsAsString(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }
        return word.chars().mapToObj(c -> (char) c).sorted(Character::compareTo).map(Objects::toString).collect(Collectors.joining());
    }

    public static String toSortedCharsInString(String word) {
        if (word == null || word.length() <= 1) {
            return word;
        }
        char[] charArray = word.toCharArray();
        Arrays.sort(charArray);
        return String.valueOf(charArray);
    }
}
