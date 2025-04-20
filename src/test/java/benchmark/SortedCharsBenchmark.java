package benchmark;

import org.openjdk.jmh.annotations.*;
import tech_tasks.task_2025_03_14.SortedChars;

import java.util.List;
import java.util.concurrent.TimeUnit;

/*
Benchmark                                                                            (inputString)  Mode  Cnt     Score     Error  Units
SortedCharsBenchmark.toSortedCharsAsString                                                       a  avgt    5    78.085 ±   9.483  ns/op
SortedCharsBenchmark.toSortedCharsAsString                                                     eat  avgt    5    98.331 ±   5.966  ns/op
SortedCharsBenchmark.toSortedCharsAsString                                     The_Stealth_Warrior  avgt    5   665.499 ±  33.306  ns/op
SortedCharsBenchmark.toSortedCharsAsString  lorem ipsum dolor sit amet consectetur adipiscing elit  avgt    5  2137.920 ± 200.295  ns/op
SortedCharsBenchmark.toSortedCharsAsString                                       TheStealthWarrior  avgt    5   582.197 ±  89.279  ns/op
SortedCharsBenchmark.toSortedCharsInString                                                       a  avgt    5     1.981 ±   0.038  ns/op
SortedCharsBenchmark.toSortedCharsInString                                                     eat  avgt    5    14.882 ±   0.615  ns/op
SortedCharsBenchmark.toSortedCharsInString                                     The_Stealth_Warrior  avgt    5    77.388 ±   2.679  ns/op
SortedCharsBenchmark.toSortedCharsInString  lorem ipsum dolor sit amet consectetur adipiscing elit  avgt    5   288.052 ±   8.086  ns/op
SortedCharsBenchmark.toSortedCharsInString                                       TheStealthWarrior  avgt    5    62.914 ±   5.024  ns/op
SortedCharsBenchmark.toSortedChartList                                                           a  avgt    5    54.958 ±   1.959  ns/op
SortedCharsBenchmark.toSortedChartList                                                         eat  avgt    5    78.732 ±   3.115  ns/op
SortedCharsBenchmark.toSortedChartList                                         The_Stealth_Warrior  avgt    5   439.195 ±  32.248  ns/op
SortedCharsBenchmark.toSortedChartList      lorem ipsum dolor sit amet consectetur adipiscing elit  avgt    5  1538.585 ±  45.761  ns/op
SortedCharsBenchmark.toSortedChartList                                           TheStealthWarrior  avgt    5   397.245 ±  42.841  ns/op
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
    public List<Character> toSortedChartList() {
        return SortedChars.toSortedChartList(inputString);
    }

    @Benchmark
    public String toSortedCharsAsString() {
        return SortedChars.toSortedCharsAsString(inputString);
    }

    @Benchmark
    public String toSortedCharsInString() {
        return SortedChars.toSortedCharsInString(inputString);
    }

}
