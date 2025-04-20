package tech_tasks.task_2025_03_14;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SortedChars {
    private static List<Character> toSortedChartList(String word) {
        if (word == null || word.isEmpty()) {
            return Collections.emptyList();
        }
        return word.chars().mapToObj(c -> (char) c).sorted(Character::compareTo).collect(Collectors.toList());
    }

    private static String toSortedCharsInString(String word) {
        if (word == null || word.length() <= 1) {
            return word;
        }
        char[] charArray = word.toCharArray();
        Arrays.sort(charArray);
        return String.valueOf(charArray);
    }
}
