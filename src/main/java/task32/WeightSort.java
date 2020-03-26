package task32;

/**
 * Created by konstantin on 26.03.2020.
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * When two numbers have the same "weight", let us class them as
 * if they were strings (alphabetical ordering)
 * and not numbers: 100 is before 180 because its "weight" (1) is less than the one of 180 (9)
 * and 180 is before 90 since, having the same "weight" (9), it comes before as a string. *
 * All numbers in the list are positive numbers and the list can be empty.
 * <p>
 * Notes
 * it may happen that the input string have leading, trailing whitespaces
 * and more than a unique whitespace between two consecutive numbers
 * Don't modify the input
 * For C: The result is freed.
 */
public class WeightSort {
    private static final Comparator<String> WEIGHT_COMPARATOR = (s1, s2) ->
    {
        int w1 = s1.chars().boxed().mapToInt(Character::getNumericValue).sum();
        int w2 = s2.chars().boxed().mapToInt(Character::getNumericValue).sum();
        int weightComparison = Integer.compare(w1, w2);
        return weightComparison == 0 ? s1.compareTo(s2) : weightComparison;
    };

    public static String orderWeight(String input) {
        return Arrays.stream(input.trim().split("\\s+"))
                .sorted(WEIGHT_COMPARATOR)
                .collect(Collectors.joining(" "));
    }

    public static String orderWeightBubbleSort(String input) {
        String[] weights = input.trim().split("\\s+");
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < weights.length - 1; i++) {
                int w1 = weights[i].chars().boxed().mapToInt(Character::getNumericValue).sum();
                int w2 = weights[i + 1].chars().boxed().mapToInt(Character::getNumericValue).sum();
                int weightComparison = Integer.compare(w1, w2);
                int res = weightComparison == 0 ? weights[i].compareTo(weights[i + 1]) : weightComparison;
                if (res > 0) {
                    String temp = weights[i];
                    weights[i] = weights[i + 1];
                    weights[i + 1] = temp;
                    sorted = false;
                }
            }
        }
        return String.join(" ", weights);
    }

    public static String orderWeight2(String input) {
        return Arrays.stream(input.trim().split("\\s+"))
                .sorted(Comparator
                        .comparing(WeightSort::sumDigits)
                        .thenComparing(String::compareTo))
                .collect(Collectors.joining(" "));

    }

    private static Integer sumDigits(String s) {
        return s.chars().boxed().mapToInt(Character::getNumericValue).sum();
    }


}
