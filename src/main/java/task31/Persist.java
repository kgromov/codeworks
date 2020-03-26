package task31;

/**
 * Created by konstantin on 24.03.2020.
 */

/**
 * Write a function, persistence, that takes in a positive parameter num and returns its multiplicative persistence,
 * which is the number of times you must multiply the digits in num until you reach a single digit.
 *
 *  persistence(39) == 3 // because 3*9 = 27, 2*7 = 14, 1*4=4
 // and 4 has only one digit

 persistence(999) == 4 // because 9*9*9 = 729, 7*2*9 = 126,
 // 1*2*6 = 12, and finally 1*2 = 2

 persistence(4) == 0 // because 4 is already a one-digit number
 */
public class Persist {
    private static final int RADIX = 10;

    public static int persistence(long n) {
        long res = n;
        int count = 0;
        while(res >= RADIX)
        {
            res = String.valueOf(res).chars().boxed()
                    .mapToInt(Character::getNumericValue).reduce(1, (a, b) -> a * b);
            ++count;
        }
        return count;
    }


}
