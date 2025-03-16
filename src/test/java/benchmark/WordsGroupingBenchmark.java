package benchmark;

import org.openjdk.jmh.annotations.*;
import task_2025_03_14.WordsGrouping;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/*
Benchmark                                                (inputString)  Mode  Cnt    Score    Error  Units
WordsGroupingBenchmark.linkedSetSolution       eat,tea,tan,ate,nat,bat  avgt    5  272.151 ± 16.631  ns/op
WordsGroupingBenchmark.linkedSetSolution  eat,spoon,milk,tea,breakfast  avgt    5  229.686 ± 12.294  ns/op
WordsGroupingBenchmark.linkedSetSolution   eat,tea,tan,ate,nat,eat,nat  avgt    5  294.616 ± 15.660  ns/op
WordsGroupingBenchmark.mapSolution             eat,tea,tan,ate,nat,bat  avgt    5  204.346 ±  4.967  ns/op
WordsGroupingBenchmark.mapSolution        eat,spoon,milk,tea,breakfast  avgt    5  205.519 ±  5.997  ns/op
WordsGroupingBenchmark.mapSolution         eat,tea,tan,ate,nat,eat,nat  avgt    5  224.740 ±  3.448  ns/op
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class WordsGroupingBenchmark {
    @Param({"eat,tea,tan,ate,nat,bat", "eat,spoon,milk,tea,breakfast", "eat,tea,tan,ate,nat,eat,nat"})
    private String inputString;
    private String[] words;

    @Setup(Level.Trial)
    public void setup() {
        words = inputString.split(",");
    }

    @Benchmark
    public Collection<? extends Collection<String>> linkedSetSolution() {
        return WordsGrouping.linkedSetSolution(words);
    }

    @Benchmark
    public Collection<? extends Collection<String>> mapSolution() {
        return WordsGrouping.mapSolution(words);
    }
}
