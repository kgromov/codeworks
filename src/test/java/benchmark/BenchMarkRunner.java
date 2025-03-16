package benchmark;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;


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
//                .include(BallotCounterBenchMark.class.getSimpleName())
//                .include(SortedCharsBenchmark.class.getSimpleName())
                .include(WordsGroupingBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
