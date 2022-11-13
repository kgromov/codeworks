package task90;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Created by konstantin on 13.11.2022.
 */
public class Actions {
    static List<Function<Integer, Integer>> FUNCTIONS = Arrays.asList(
            x -> x + 1,
            x -> 0,
            x -> x % 2,
            x -> x / 2,
            x -> x * 100
    );

}
