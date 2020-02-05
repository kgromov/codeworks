package benchmark;

import org.openjdk.jmh.annotations.*;
import task06.BinaryArrayToNumber;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by konstantin on 02.02.2020.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class BinaryArrayConverter {
    @Param({"0001", "1111", "0110", "1001"})
    private String intBinary;

    private List<Integer> binary;

    @Setup
    public void init()
    {
        binary = Arrays.stream( intBinary.split("")).map(Integer::parseInt).collect(Collectors.toList());
    }

    @Benchmark
    public int convertWithString()
    {
        return BinaryArrayToNumber.convertBinaryArrayToInt_(binary);
    }

    @Benchmark
    public int convertWithIntStream()
    {
        return BinaryArrayToNumber.convertBinaryArrayToInt(binary);
    }

    @Benchmark
    public int convertBinaryArrayToInt1_7()
    {
        return BinaryArrayToNumber.convertBinaryArrayToInt1_7(binary);
    }

}
