package task02;

import java.util.Arrays;

/**
 * Created by konstantin on 02.02.2020.
 */
/*
A digital root is the recursive sum of all the digits in a number. Given n, take the sum of the digits of n. If that value has more than one digit, continue reducing in this way until a single-digit number is produced. This is only applicable to the natural numbers.

Here's how it works:
digital_root(16)
=> 1 + 6
=> 7

digital_root(942)
=> 9 + 4 + 2
=> 15 ...
=> 1 + 5
=> 6

digital_root(132189)
=> 1 + 3 + 2 + 1 + 8 + 9
=> 24 ...
=> 2 + 4
=> 6

digital_root(493193)
=> 4 + 9 + 3 + 1 + 9 + 3
=> 29 ...
=> 2 + 9
=> 11 ...
=> 1 + 1
=> 2
 */
public class DRoot {
    private static final int RADIX = 10;

    public static int digital_root_chars(int n) {
        char [] digits = String.valueOf(n).toCharArray();
        int sum = 0;
        for (char c : digits)
        {
            sum += Character.digit(c, RADIX);
        }
       /* System.out.println("=> " + Arrays.toString(digits).replaceAll(",", " +"));
        System.out.println("=> " + sum);*/
        return sum < RADIX ? sum : digital_root_chars(sum);
    }

//
    public static int digital_root_stream(int n) {
//        String.valueOf(n).chars().boxed().map(c -> Character.digit(c, RADIX)).reduce(0 , (a, b) -> a+b);
        // Character::getNumericValue -> Character.digit(c, radix);
        int sum = String.valueOf(n).chars().boxed().mapToInt(Character::getNumericValue).sum();
        return sum < RADIX ? sum : digital_root_stream(sum);
    }

    public static int digital_root(int n) {
        return (n != 0 && n % 9 == 0) ? 9 : n % 9;
    }

    public static void main(String[] args) {
        digital_root_stream(16);
        digital_root_stream(456);
    }
}
