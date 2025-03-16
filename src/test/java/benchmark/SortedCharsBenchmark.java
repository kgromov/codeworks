package benchmark;

import org.openjdk.jmh.annotations.*;
import task01.Solution;

import java.util.concurrent.TimeUnit;

/*
Benchmark                                                                            (inputString)  Mode  Cnt    Score   Error  Units
SortedCharsBenchmark.toSortedCharsInString                                                       a  avgt    5  102.567 ± 2.927  ns/op
SortedCharsBenchmark.toSortedCharsInString                                                     eat  avgt    5  106.452 ± 2.562  ns/op
SortedCharsBenchmark.toSortedCharsInString                                     The_Stealth_Warrior  avgt    5  219.831 ± 4.445  ns/op
SortedCharsBenchmark.toSortedCharsInString  lorem ipsum dolor sit amet consectetur adipiscing elit  avgt    5  138.844 ± 3.079  ns/op
SortedCharsBenchmark.toSortedCharsInString                                       TheStealthWarrior  avgt    5  129.604 ± 2.606  ns/op
SortedCharsBenchmark.toSortedChartList                                                           a  avgt    5   69.704 ± 4.879  ns/op
SortedCharsBenchmark.toSortedChartList                                                         eat  avgt    5   69.342 ± 3.765  ns/op
SortedCharsBenchmark.toSortedChartList                                         The_Stealth_Warrior  avgt    5  221.546 ± 4.414  ns/op
SortedCharsBenchmark.toSortedChartList      lorem ipsum dolor sit amet consectetur adipiscing elit  avgt    5   81.424 ± 2.740  ns/op
SortedCharsBenchmark.toSortedChartList                                           TheStealthWarrior  avgt    5   73.234 ± 1.874  ns/op
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class SortedCharsBenchmark {
    @Param({"a", "eat", "The_Stealth_Warrior", "lorem ipsum dolor sit amet consectetur adipiscing elit", "TheStealthWarrior"})
    private String inputString;

    @Benchmark
    public String toSortedChartList() {
        return Solution.toCamelCaseMySolution(inputString);
    }

    @Benchmark
    public String toSortedCharsInString() {
        return Solution.toCamelCaseAppendReplacement(inputString);
    }

}
