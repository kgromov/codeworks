package benchmark;

import org.openjdk.jmh.annotations.*;
import task61.DaysInYear;
import task71.SquareDigit;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class SquareDigitBenchmark {
    @Param({"-1", "1778", "0", "2000", "9119", "305002"})
    private int number;

    @Benchmark
    public int squareDigits()
    {
        return SquareDigit.squareDigits(number);
    }

    @Benchmark
    public int squareDigits2()
    {
        return SquareDigit.squareDigits2(number);
    }

}
