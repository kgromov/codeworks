package task33;

/**
 * Created by konstantin on 27.03.2020.
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * You are given an array strarr of strings and an integer k.
 * Your task is to return the first longest string consisting of k consecutive strings taken in the array.
 * <p>
 * Example:
 * longest_consec(["zone", "abigail", "theta", "form", "libe", "zas", "theta", "abigail"], 2) --> "abigailtheta"
 * n being the length of the string array, if n = 0 or k > n or k <= 0 return "".
 * <p>
 * Note:
 * consecutive strings : follow one after another without an interruption
 */
public class LongestConsec {

    public static String longestConsec(String[] strarr, int k) {
        if (k <= 0 || strarr.length - k < 0) {
            return "";
        }
        int startGroupIndex = 0;
        int maxLength = 0;
        for (int i = 0; i <= strarr.length - k; i++) {
            int length = Arrays.stream(strarr, i, i + k).mapToInt(String::length).sum();
            if (length > maxLength) {
                maxLength = length;
                startGroupIndex = i;
            }
        }
        return IntStream.range(startGroupIndex, startGroupIndex + k).boxed()
                .map(i -> strarr[i])
                .collect(Collectors.joining());
    }

    public static String longestConsecMaxComparator(String[] strarr, int k) {
        if (k <= 0 || strarr.length - k < 0) {
            return "";
        }
        return IntStream.rangeClosed(0, strarr.length - k)
                .mapToObj(i -> Arrays.stream(strarr, i, i + k).collect(Collectors.joining()))
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }

    public static String longestConsecStringBuilder(String[] strarr, int k) {
        if (k <= 0 || strarr.length - k < 0) {
            return "";
        }
        String longestStr = "";
        for (int index = 0; index < strarr.length - k + 1; index++) {
            StringBuilder sb = new StringBuilder();
            for (int i = index; i < index + k; i++) {
                sb.append(strarr[i]);
            }
            if (sb.toString().length() > longestStr.length()) {
                longestStr = sb.toString();
            }
        }
        return longestStr;
    }
}
