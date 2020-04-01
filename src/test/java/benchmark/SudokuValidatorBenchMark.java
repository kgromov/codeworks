package benchmark;

import org.openjdk.jmh.annotations.*;
import task42.SudokuValidator;
import task42.SudokuValidatorBySum;
import task42.SudokuValidatorBySumAndChars;
import task42.SudokuValidatorNoCollections;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by konstantin on 01.04.2020.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class SudokuValidatorBenchMark {
    private static final int [][] SUDOKU = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
    };

    public enum Condition
    {
        FIRST("valid"),
        SECOND("invalid at the start"),
        THIRD("invalid in the middle"),
        FOURTH("invalid at the end");

        private String description;

        Condition(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    @Param({"FIRST", "SECOND", "THIRD", "FOURTH"})
    private Condition condition;

    private int [][] input;

    @Setup
    public void setup()
    {
        System.out.println("Sudoku condition = " + condition.getDescription());
        input = new int[9][9];
        int i = 0;
        for (int [] row : SUDOKU)
        {
            input [i++] = row;
        }
        switch (condition)
        {
            case FIRST:
                break;
            case SECOND:
                input[0][0]++;
                input[1][1]++;
                input[0][1]--;
                input[1][0]--;
                break;
            case THIRD:
                input[4][4] = 0;
                break;
            case FOURTH:
                input[7][6]++;
                input[8][5]--;
                break;
        }
    }

    @Benchmark
    public boolean sudokuValidator()
    {
        return SudokuValidator.check(input);
    }

    @Benchmark
    public boolean sudokuValidatorBySum()
    {
        return SudokuValidatorBySum.check(input);
    }

    @Benchmark
    public boolean sudokuValidatorBySumAndChars()
    {
        return SudokuValidatorBySumAndChars.check(input);
    }

    @Benchmark
    public boolean sudokuValidatorNoCollections()
    {
        return SudokuValidatorNoCollections.check(input);
    }
}
