package task71;

import java.util.stream.Collectors;

/**
 * Created by konstantin on 10.10.2021.
 */
public class SquareDigit {
    private static final int RADIX = 10;

    public static int squareDigits(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n < RADIX) {
            return n * n;
        }
        String result = String.valueOf(n).chars().boxed()
                .map(Character::getNumericValue)
                .map(i -> i * i)
                .map(Object::toString)
                .collect(Collectors.joining(""));
        return Integer.parseInt(result);
    }

    public static int squareDigits2(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n < RADIX) {
            return n * n;
        }

        int result = 0;
        char[] chars = String.valueOf(n).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int digit = Character.getNumericValue(chars[i]);
            int square = digit * digit;
            int power = square > RADIX ? 2 : 1;
            if (i==0) {
                result = square;
            } else {
                result = result * (int) Math.pow(RADIX, power) + square;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // 811181
        int n = 9119;
        int i1 = 9 * 9 << 9;
        System.out.println(i1);
    }

}
