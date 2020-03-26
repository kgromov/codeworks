package task20;

/**
 * Created by konstantin on 23.02.2020.
 */

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Create a function taking a positive integer as its parameter and returning a string containing the Roman Numeral representation of that integer.

 Modern Roman numerals are written by expressing each digit separately starting with the left most digit and skipping any digit with a value of zero.
 In Roman numerals 1990 is rendered: 1000=M, 900=CM, 90=XC; resulting in MCMXC.
 2008 is written as 2000=MM, 8=VIII; or MMVIII.
 1666 uses each Roman symbol in descending order: MDCLXVI.

 Example:conversion.solution(1000); //should return "M"

 Symbol    Value
 I          1
 V          5
 X          10
 L          50
 C          100
 D          500
 M          1,000
 */
public class RomanNumeralsEncoder {

    private static final NavigableMap<Integer, Character> DECIMAL_TO_ROMAN = new TreeMap<>();

    static
    {
        DECIMAL_TO_ROMAN.put(1, 'I');
        DECIMAL_TO_ROMAN.put(5, 'V');
        DECIMAL_TO_ROMAN.put(10, 'X');
        DECIMAL_TO_ROMAN.put(50, 'L');
        DECIMAL_TO_ROMAN.put(100, 'C');
        DECIMAL_TO_ROMAN.put(500, 'D');
        DECIMAL_TO_ROMAN.put(1000, 'M');
    }

    public static String convert(int i)
    {
        return "";
    }
}
