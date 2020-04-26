package task42;

/**
 * Created by konstantin on 01.04.2020.
 */
public class SudokuValidatorBySum {
    private static final int SIZE = 9;
    private static final int BLOCK_SIZE = 3;

    public static boolean check(int[][] sudoku) {
        if (sudoku.length != SIZE || sudoku[0].length != SIZE) {
            return false;
        }
        int correctSum = (SIZE + 1) * SIZE / 2;
        int duplicatedValue = correctSum / SIZE;
        int sumSquare1 = 0;
        int sumSquare2 = 0;
        int sumSquare3 = 0;

        for (int i = 0; i < SIZE; i++) {
            int sumPerRows = 0;
            int sumPerColumns = 0;
            int duplicatedRowValues = 0;
            int duplicatedColumnValues = 0;

            for (int j = 0; j < SIZE; j++) {
                int cellRowValue = sudoku[i][j];
                int cellColumnValue = sudoku[i][j];
                if (cellRowValue < 1 || cellRowValue > SIZE
                        || cellColumnValue < 1 || cellColumnValue > SIZE) {
                    return false;
                }
                // validations for all 5 as 45 / 9 = 5
                if (cellRowValue == duplicatedValue) {
                    ++duplicatedRowValues;
                }
                if (cellColumnValue == duplicatedValue) {
                    ++duplicatedColumnValues;
                }
                if (duplicatedRowValues > 1 || duplicatedColumnValues > 1) {
                    return false;
                }
                sumPerRows += cellRowValue;
                sumPerColumns += cellColumnValue;

                if (j < BLOCK_SIZE) {
                    sumSquare1 += cellRowValue;
                } else if (j > 5) {
                    sumSquare3 += cellRowValue;
                } else {
                    sumSquare2 += cellRowValue;
                }
            }
            // row and column validation
            if (sumPerRows != correctSum || sumPerColumns != correctSum) {
                return false;
            }
            // new row of blocks 3x3
            if ((i + 1) % BLOCK_SIZE == 0) {
                if (sumSquare1 != correctSum || sumSquare2 != correctSum || sumSquare3 != correctSum) {
                    return false;
                }
                // reset
                sumSquare1 = 0;
                sumSquare2 = 0;
                sumSquare3 = 0;
            }
        }
        return true;
    }
}
