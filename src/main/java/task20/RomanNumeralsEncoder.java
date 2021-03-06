package task20;

/**
 * Created by konstantin on 23.02.2020.
 */

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Create a function taking a positive integer as its parameter and returning a string containing the Roman Numeral representation of that integer.
 * <p>
 * Modern Roman numerals are written by expressing each digit separately starting with the left most digit and skipping any digit with a value of zero.
 * In Roman numerals 1990 is rendered: 1000=M, 900=CM, 90=XC; resulting in MCMXC.
 * 2008 is written as 2000=MM, 8=VIII; or MMVIII.
 * 1666 uses each Roman symbol in descending order: MDCLXVI.
 * <p>
 * Example:conversion.solution(1000); //should return "M"
 * <p>
 * Symbol    Value
 * I          1
 * V          5
 * X          10
 * L          50
 * C          100
 * D          500
 * M          1,000
 * Note:
 * 4 = IV,    9 = IX
 * 40 = XL,   90 = XC
 * 400 = CD,  900 = CM
 */
public class RomanNumeralsEncoder {

    private static final NavigableMap<Integer, Character> DECIMAL_TO_ROMAN = new TreeMap<>();

    static {
        DECIMAL_TO_ROMAN.put(1, 'I');
        DECIMAL_TO_ROMAN.put(5, 'V');
        DECIMAL_TO_ROMAN.put(10, 'X');
        DECIMAL_TO_ROMAN.put(50, 'L');
        DECIMAL_TO_ROMAN.put(100, 'C');
        DECIMAL_TO_ROMAN.put(500, 'D');
        DECIMAL_TO_ROMAN.put(1000, 'M');
    }

    public static String convertRecursively(int arabicDigit) {
        return arabicDigit <= 0 || arabicDigit >= 4_000 ? "" : append(arabicDigit, new StringBuilder());
    }

    private static String append(int arabicDigit, StringBuilder romanDigit) {
        Map.Entry<Integer, Character> factorEntry = DECIMAL_TO_ROMAN.floorEntry(arabicDigit);
        // case for 1
        if (factorEntry == null) {
            romanDigit.append(DECIMAL_TO_ROMAN.get(1));
            return romanDigit.toString();
        }
        // common cases
        int factor = factorEntry.getKey();
        char digit = factorEntry.getValue();
        int times = arabicDigit / factor;
        int remaining = arabicDigit % factor;
        int firstDigit = arabicDigit < 10 ? arabicDigit : Character.getNumericValue(String.valueOf(arabicDigit).charAt(0));
        if (!(times == 4 || times == 9 || firstDigit == 4 || firstDigit == 9)) {
            for (int i = 0; i < times; i++) {
                romanDigit.append(digit);
            }
        } else {
            boolean isPowerTen = factor == 1 || Character.getNumericValue(String.valueOf(factor).charAt(0)) == 1;
            Map.Entry<Integer, Character> lowerEntry = isPowerTen ? factorEntry : DECIMAL_TO_ROMAN.lowerEntry(factor);
            Map.Entry<Integer, Character> higherEntry = DECIMAL_TO_ROMAN.higherEntry(factor);
            // corner cases
            char left = lowerEntry == null ? DECIMAL_TO_ROMAN.get(1) : lowerEntry.getValue();
            romanDigit.append(left).append(higherEntry.getValue());
            remaining = arabicDigit - higherEntry.getKey() + (lowerEntry == null ? 1 : lowerEntry.getKey());
        }
        return remaining == 0 ? romanDigit.toString() : append(remaining, romanDigit);
    }
}
