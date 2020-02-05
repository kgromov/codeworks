package task06;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
Testing: [0, 0, 0, 1] ==> 1
Testing: [0, 0, 1, 0] ==> 2
Testing: [0, 1, 0, 1] ==> 5
Testing: [1, 0, 0, 1] ==> 9
Testing: [0, 0, 1, 0] ==> 2
Testing: [0, 1, 1, 0] ==> 6
Testing: [1, 1, 1, 1] ==> 15
Testing: [1, 0, 1, 1] ==> 11
 */
public class BinaryArrayToNumber {
    public static int convertBinaryArrayToInt_(List<Integer> binary) {
        return Integer.parseInt(binary.stream().map(Objects::toString).collect(Collectors.joining()), 2);
    }

    public static int convertBinaryArrayToInt(List<Integer> binary) {
        return binary.stream().reduce(0, (x, y) -> x * 2 + y);
    }

    public static int convertBinaryArrayToInt1_7(List<Integer> binary) {
        int number = 0;
        for (int bit : binary)
            number = number << 1 | bit;
        return number;
    }

}
