package benchmark;

import org.openjdk.jmh.annotations.*;
import task46.BattleField;
import task46.BattleFieldNoPair;

import java.util.concurrent.TimeUnit;

/**
 * Created by konstantin on 01.04.2020.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class BattleFieldValidatorBenchMark {
    private static final int [][] FIELD =
            {{1, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 0},
            {1, 0, 1, 0, 1, 1, 1, 0, 1, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

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
        System.out.println("BattleField condition = " + condition.getDescription());
        input = new int[10][10];
        int i = 0;
        for (int [] row : FIELD)
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
    public boolean fieldValidator()
    {
        return BattleField.fieldValidator(input);
    }

    @Benchmark
    public boolean fieldValidatorNoPair()
    {
        return BattleFieldNoPair.fieldValidator(input);
    }
}
