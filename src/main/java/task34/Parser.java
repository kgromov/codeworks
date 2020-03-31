package task34;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by konstantin on 28.03.2020.
 */

/**
 * In this kata we want to convert a string into an integer.
 * The strings simply represent the numbers in words.
 * <p>
 * Examples:
 * "one" => 1
 * "twenty" => 20
 * "two hundred forty-six" => 246
 * "seven hundred eighty-three thousand nine hundred and nineteen" => 783919
 * <p>
 * Additional Notes:
 * The minimum number is "zero" (inclusively)
 * The maximum number, which must be supported is 1 million (inclusively)
 * The "and" in e.g. "one hundred and twenty-four" is optional, in some cases it's present
 * and in others it's not
 * All tested numbers are valid, you don't need to validate them
 */
public class Parser {
    private static final Map<String, Integer> NAMED_DIGITS = new HashMap<>();

    static {
        NAMED_DIGITS.put("zero", 0);
        NAMED_DIGITS.put("one", 1);
        NAMED_DIGITS.put("two", 2);
        NAMED_DIGITS.put("three", 3);
        NAMED_DIGITS.put("four", 4);
        NAMED_DIGITS.put("five", 5);
        NAMED_DIGITS.put("six", 6);
        NAMED_DIGITS.put("seven", 7);
        NAMED_DIGITS.put("eight", 8);
        NAMED_DIGITS.put("nine", 9);

        NAMED_DIGITS.put("ten", 10);
        NAMED_DIGITS.put("eleven", 11);
        NAMED_DIGITS.put("twelve", 12);
        NAMED_DIGITS.put("thirteen", 13);
        NAMED_DIGITS.put("fourteen", 14);
        NAMED_DIGITS.put("fifteen", 15);
        NAMED_DIGITS.put("sixteen", 16);
        NAMED_DIGITS.put("seventeen", 17);
        NAMED_DIGITS.put("eighteen", 18);
        NAMED_DIGITS.put("nineteen", 19);

        NAMED_DIGITS.put("twenty", 20);
        NAMED_DIGITS.put("thirty", 30);
        NAMED_DIGITS.put("forty", 40);
        NAMED_DIGITS.put("fifty", 50);
        NAMED_DIGITS.put("sixty", 60);
        NAMED_DIGITS.put("seventy", 70);
        NAMED_DIGITS.put("eighty", 80);
        NAMED_DIGITS.put("ninety", 90);

        NAMED_DIGITS.put("hundred", 100);
        NAMED_DIGITS.put("thousand", 1_000);
        NAMED_DIGITS.put("million", 1_000_000);

        NAMED_DIGITS.put("and", 0);
    }

    public static int parseInt1(String numStr) {
        String[] data = numStr.trim().split("(\\s+and?\\s+)|(\\s?-\\s?)|(\\s+)");
        if (data.length == 1) {
            return NAMED_DIGITS.get(data[0]);
        }

        int result = 0;
//        for (int i = 0; i <= data.length / 2; i += 2) {
        for (int i = 0; i < data.length - 1; i += 2) {
            int prev = NAMED_DIGITS.get(data[i]);
            int next = NAMED_DIGITS.get(data[i + 1]);
            int temp = prev < next && !data[i].endsWith("y") ? prev * next : prev + next;
            result += temp;
        }
        if (data.length % 2 == 1) {
            result += NAMED_DIGITS.get(data[data.length - 1]);
        }
        return result;
    }

    public static int parseInt2(String numStr) {
        Pattern multiplyDataRegExp = Pattern.compile("(million)|(thousand)|(hundred)");
        Matcher matcher = multiplyDataRegExp.matcher(numStr);
        if (matcher.find()) {
            String factor = matcher.group();
            int startIndex = matcher.start();
            int endIndex = matcher.end();
            int multiplier = NAMED_DIGITS.get(factor);
            int leftPart = startIndex > 0 ? parseInt(numStr.substring(0, startIndex).trim()) : 1;
            int rightPart = endIndex < numStr.length() ? parseInt(numStr.substring(endIndex).trim()) : 0;
            return leftPart * multiplier + rightPart;
        }
        String[] data = numStr.trim().split("(\\s+and?\\s+)|(\\s?-\\s?)|(\\s+)");
        return Arrays.stream(data).mapToInt(NAMED_DIGITS::get).sum();
    }

    public static int parseInt(String numStr) {
        String factor = "million";
        int indexOfFactor = numStr.indexOf(factor);
        if (indexOfFactor != -1)
        {
            int endIndex = indexOfFactor + factor.length() + 1;
            int leftPart = indexOfFactor > 0 ? parseInt(numStr.substring(0, indexOfFactor)) : 1;
            int rightPart = endIndex < numStr.length() ? parseInt(numStr.substring(endIndex)) : 0;
            return leftPart * NAMED_DIGITS.get(factor) + rightPart;
        }
        factor = "thousand";
        indexOfFactor = numStr.indexOf(factor);
        if (indexOfFactor != -1)
        {
            int endIndex = indexOfFactor + factor.length() + 1;
            int leftPart = indexOfFactor > 0 ? parseInt(numStr.substring(0, indexOfFactor)) : 1;
            int rightPart = endIndex < numStr.length() ? parseInt(numStr.substring(endIndex)) : 0;
            return leftPart * NAMED_DIGITS.get(factor) + rightPart;
        }
        factor = "hundred";
        indexOfFactor = numStr.indexOf(factor);
        if (indexOfFactor != -1)
        {
            int endIndex = indexOfFactor + factor.length() + 1;
            int leftPart = indexOfFactor > 0 ? parseInt(numStr.substring(0, indexOfFactor)) : 1;
            int rightPart = endIndex < numStr.length() ? parseInt(numStr.substring(endIndex)) : 0;
            return leftPart * NAMED_DIGITS.get(factor) + rightPart;
        }
        String[] data = numStr.trim().split("(\\s+and?\\s+)|(\\s?-\\s?)|(\\s+)");
        return Arrays.stream(data).mapToInt(NAMED_DIGITS::get).sum();
    }

    public static void main(String[] args) {
//        parseInt(" seven hundred eighty-eight");
//        System.out.println(parseInt("hundred"));
//        System.out.println(parseInt(" and twenty-four"));
//        System.out.println(parseInt("two hundred"));
//        System.out.println(parseInt("two hundred forty-six"));
//        System.out.println(parseInt("two thousand seven hundred eighty-eight"));
        System.out.println(parseInt("seven hundred thousand"));
    }
}
