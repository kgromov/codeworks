package task42;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by konstantin on 01.04.2020.
 */
public class SudokuValidatorStream {
    static final private Set<Integer> REF = getSetOf(IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9));

    public static boolean check(int[][] grid) {
        return grid.length == 9
                && Stream.of(grid).allMatch(a -> a.length == 9 && check(IntStream.of(a)))
                && IntStream.range(0, 9).allMatch(y -> check(IntStream.range(0, 9).map(x -> grid[x][y]))
                && check(extractSquare(y, grid)));
    }

    private static Set<Integer> getSetOf(IntStream st) {
        return st.boxed().collect(Collectors.toSet());
    }

    private static boolean check(IntStream st) {
        return REF.equals(getSetOf(st));
    }

    private static IntStream extractSquare(int z, int[][] grid) {
        int a = z / 3 * 3, b = z % 3 * 3;
        return IntStream.range(0, 9).map(t -> grid[a + t / 3][b + t % 3]);
    }
}
