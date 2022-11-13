package benchmark;

import org.openjdk.jmh.annotations.*;
import task_2022_11_12.BallotsCounter;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by konstantin on 12.11.2022.
 *
 * Benchmark                            (inputString)  Mode  Cnt  Score   Error  Units
 BallotCounterBenchMark.getWinnerMap              A  avgt    5  0,101 ± 0,009  us/op
 BallotCounterBenchMark.getWinnerMap        AAABBBA  avgt    5  0,096 ± 0,007  us/op
 BallotCounterBenchMark.getWinnerMap          AABBC  avgt    5  0,096 ± 0,002  us/op
 BallotCounterBenchMark.getWinnerSet              A  avgt    5  0,180 ± 0,048  us/op
 BallotCounterBenchMark.getWinnerSet        AAABBBA  avgt    5  0,184 ± 0,044  us/op
 BallotCounterBenchMark.getWinnerSet          AABBC  avgt    5  0,181 ± 0,046  us/op
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class BallotCounterBenchMark {
    @Param({"A", "AAABBBA", "AABBC", "BBAABAA"})
    private String inputString;
    private List<String> ballots;

    @Setup
    public void setup() {
        ballots = Stream.of(inputString.toCharArray()).map(Object::toString).collect(Collectors.toList());
    }

    @Benchmark
    public String getWinnerMap() {
        return BallotsCounter.getWinner(ballots);
    }

    @Benchmark
    public String getWinnerSet() {
        return BallotsCounter.getWinnerBySet(ballots);
    }

    @Benchmark
    public String getWinnerMaps() {
        return BallotsCounter.getWinnerMap2(ballots);
    }


}
