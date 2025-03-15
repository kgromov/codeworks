package task_2025_03_14;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    Input: ["eat", "tea", "tan", "ate", "nat", "bat"]
    Output: [["eat", "tea", "ate"], ["tan", "nat"], ["bat"]]
 */
public class WordsGrouping {
    public static Collection<? extends Collection<String>> listSolution(String[] words) {
        long startTime = System.nanoTime();
        List<List<String>> groups = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (groups.stream().anyMatch(g -> g.contains(word))) {
                continue;
            }
            var chars = toSortedCharsInString(word);
            var wordsInGroup = new ArrayList<String>();
            wordsInGroup.add(word);
            groups.add(wordsInGroup);
            for (int j = i + 1; j < words.length; j++) {
                String compareWith = words[j];
                if (compareWith.length() == word.length()) {
                    var charsTpCompare = toSortedCharsInString(compareWith);
                    if (charsTpCompare.equals(chars)) {
                        groups.get(groups.size() - 1).add(compareWith);
                    }
                }
            }
        }
        System.out.println(TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime));
        return groups;
    }

    public static Collection<? extends Collection<String>> linkedSetSolution(String[] words) {
        long startTime = System.nanoTime();
        LinkedList<LinkedHashSet<String>> groups = new LinkedList<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (groups.stream().anyMatch(g -> g.contains(word))) {
                continue;
            }
            var chars = toSortedCharsInString(word);
            var wordsInGroup = new LinkedHashSet<String>();
            wordsInGroup.add(word);
            groups.add(wordsInGroup);
            for (int j = i + 1; j < words.length; j++) {
                String compareWith = words[j];
                if (compareWith.length() == word.length()) {
                    var charsTpCompare = toSortedCharsInString(compareWith);
                    if (charsTpCompare.equals(chars)) {
                        groups.getLast().add(compareWith);
                    }
                }
            }
        }
        System.out.println(TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime));
        return groups;
    }

    public static Collection<? extends Collection<String>> mapSolution(String[] words) {
        long startTime = System.nanoTime();
        var wordGroups = Stream.of(words)
                .collect(Collectors.groupingBy(
                        WordsGrouping::toSortedCharsInString,
                        LinkedHashMap::new,
                        Collectors.toList()
                ));
        System.out.println(TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime));
        return wordGroups.values();
    }

    private static List<Character> toSortedChartList(String word) {
        return word.chars().mapToObj(c -> (char) c).sorted(Character::compareTo).collect(Collectors.toList());
    }

    private static String toSortedCharsInString(String word) {
        char[] charArray = word.toCharArray();
        Arrays.sort(charArray);
        return String.valueOf(charArray);
    }
}
