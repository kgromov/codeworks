package benchmark;

import org.openjdk.jmh.annotations.*;
import task01.Solution;
import task02.DRoot;

import java.util.concurrent.TimeUnit;

/**
 * Created by konstantin on 02.02.2020.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class CalculateDigitsSum {
    @Param({"7", "16", "456", "999"})
    private int input;

    @Benchmark
    public int digitalRootChars()
    {
        return DRoot.digital_root_chars(input);
    }

    @Benchmark
    public int digitalRootStream()
    {
        return DRoot.digital_root_stream(input);
    }

    @Benchmark
    public int digitalRootArithmetic()
    {
        return DRoot.digital_root(input);
    }

}
