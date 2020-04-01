package task42;

/**
 * Created by konstantin on 01.04.2020.
 */
public class SudokuValidatorNoCollections {
    public static boolean check(int[][] sudoku) {
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku.length; j++) {
                for (int i2 = 0; i2 < sudoku.length; i2++) {
                    for (int j2 = 0; j2 < sudoku.length; j2++) {
                        if (sudoku[i][j] == 0)
                            return false;
                        if (i == i2 && j == j2)
                            continue;
                        if (sudoku[i][j] == sudoku[i2][j2])
                            if (i / 3 == i2 / 3 && j / 3 == j2 / 3 || i == i2 || j == j2)
                                return false;
                    }
                }
            }
        }
        return true;
    }
}
