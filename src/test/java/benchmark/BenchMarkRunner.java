package benchmark;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

// TODO: run via cmd?
/*
`1) @Param({"C:\\Projects\\nds_here\\nds-qa", "C:\\Projects\\nds_here\\"}) under files
 2) @Setup method
 3) Inside class to benchmark:
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @State(Scope.Benchmark)
    @Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
    @Warmup(iterations = 3)
    @Measurement(iterations = 5)
 */
public class BenchMarkRunner {
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
//                .include(ConvertStringCamelCase.class.getSimpleName())
//                .include(CalculateDigitsSum.class.getSimpleName())
//                .include(BinaryArrayConverter.class.getSimpleName())
//                .include(WeightSortBenchMark.class.getSimpleName())
//                .include(LongestConsecBenchMark.class.getSimpleName())
//                .include(ParserBenchMark.class.getSimpleName())
//                .include(SudokuValidatorBenchMark.class.getSimpleName())
//                .include(RomanNumeralsEncoderBM.class.getSimpleName())
//                .include(BattleFieldValidatorBenchMark.class.getSimpleName())
//                .include(DaysInYearBenchmark.class.getSimpleName())
//                .include(SquareDigitBenchmark.class.getSimpleName())
                .include(BallotCounterBenchMark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
