package benchmark;

import org.openjdk.jmh.annotations.*;
import task20.RomanNumeralsEasyEncoder;
import task20.RomanNumeralsEncoder;

import java.util.concurrent.TimeUnit;

/**
 * Created by konstantin on 20.04.2020.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class RomanNumeralsEncoderBM {

    @Param({"4", "9", "16", "42", "68", "458", "901", "1666", "3999"})
    private int arabicNumeral;

    @Benchmark
    public String convertRecursevly()
    {
        return RomanNumeralsEncoder.convertRecursively(arabicNumeral);
    }

    @Benchmark
    public String convertRecursevlyEasy()
    {
        return RomanNumeralsEasyEncoder.convert(arabicNumeral);
    }

    /*@Benchmark
    public String convertLoopy()
    {
        return RomanNumeralsEncoder.convertLoopy(arabicNumeral);
    }*/
}
