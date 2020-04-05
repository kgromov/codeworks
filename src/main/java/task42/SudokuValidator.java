package task42;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by konstantin on 01.04.2020.
 */
public class SudokuValidator {
    private static final int SIZE = 9;
    private static final int BLOCK_SIZE = 3;

    public static boolean check(int[][] sudoku) {
        if (sudoku.length != 9 || sudoku[0].length != 9) {
            return false;
        }
        Map<Integer, Set<Integer>> blocks = new HashMap<>(3);
        for (int i = 0; i < SIZE; i++) {
            // init rows
            Set<Integer> rows = new HashSet<>();
            Set<Integer> columns = new HashSet<>();
            for (int j = 0; j < SIZE; j++) {
                int cellRowValue = sudoku[i][j];
                int cellColumnValue = sudoku[j][i];
                if (cellRowValue < 1 || cellRowValue > SIZE
                        || cellColumnValue < 1 || cellColumnValue > SIZE) {
                    return false;
                }
                if (!rows.add(cellRowValue) || !columns.add(cellColumnValue)
                        || !blocks.computeIfAbsent(j / BLOCK_SIZE, v -> new HashSet<>()).add(cellRowValue)) {
                    return false;
                }
            }
            // new row of blocks 3x3
            if ((i + 1) % BLOCK_SIZE == 0) {
                blocks = new HashMap<>(3);
            }
        }
        return true;
    }
}
