package benchmark;

import org.openjdk.jmh.annotations.*;
import task32.WeightSort;

import java.util.concurrent.TimeUnit;

/**
 * Created by konstantin on 02.02.2020.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class WeightSortBenchMark {
    @Param({"2000 103 123 4444 99","11 11 2000 10003 22 123 1234000 44444444 9999"})
    private String inputString;

    @Benchmark
    public String orderWeight()
    {
        return WeightSort.orderWeight(inputString);
    }

    @Benchmark
    public String orderWeight2()
    {
        return WeightSort.orderWeight2(inputString);
    }

    @Benchmark
    public String orderWeightBubbleSort()
    {
        return WeightSort.orderWeightBubbleSort(inputString);
    }

}
