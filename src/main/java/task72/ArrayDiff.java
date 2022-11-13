package task72;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by konstantin on 10.10.2021.
 */
public class ArrayDiff {
    // first and the most common solution - apache CollectionUtils#difference
    // convert to List and then use removeAll

    public static int[] arrayDiff(int[] a, int[] b) {
        if (a.length == 0 || b.length == 0) {
            return a;
        }

        return Arrays.stream(a)
                .filter(v -> Arrays.stream(b).noneMatch(v2 -> v2 == v))
                .toArray();
    }

}
