package task08;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by konstantin on 08.02.2020.
 */
public class FindOutlier {
    static int find(int[] integers) {
        int odd = 0;
        int even = 0;
        int oddNumbers = 0;
        int evenNumbers = 0;
        boolean isEven = false;
        for (int i : integers) {
            isEven = i % 2 == 0;
            if (isEven) {
                ++evenNumbers;
                even = i;
            } else {
                ++oddNumbers;
                odd = i;
            }
            if (oddNumbers > 1 && isEven) {
                return even;
            }
            if (evenNumbers > 1 && !isEven) {
                return odd;
            }

        }
        return isEven ? odd : even;
    }

    static int find2(int[] integers) {
        int[] array = Arrays.stream(integers).filter(i -> i % 2 == 0).toArray();
        return array.length == 1 ? array[0] : Arrays.stream(integers).filter(i -> i % 2 != 0).findAny().getAsInt();
    }

    // should be rewritten a bit
    public static int findBest(int[] integers) {
        int sum = Arrays.stream(integers).limit(3).map(i -> Math.abs(i) % 2).sum();
        int mod = (sum == 0 || sum == 1) ? 1 : 0;
        return Arrays.stream(integers)
                .filter(n -> Math.abs(n) % 2 == mod).findFirst().getAsInt();
    }

    static int findWithSet(int[] integers) {
        Set<Integer> oddNumbers = new HashSet<>();
        Set<Integer> evenNumbers = new HashSet<>();
        for (int i : integers) {
            if (i % 2 == 0) {
                evenNumbers.add(i);
            } else {
                oddNumbers.add(i);
            }
            if (oddNumbers.size() > 1 && !evenNumbers.isEmpty()) {
                return evenNumbers.iterator().next();
            } else if (evenNumbers.size() > 1 && !oddNumbers.isEmpty()) {
                return oddNumbers.iterator().next();
            }
        }
        return 0;
    }
}
