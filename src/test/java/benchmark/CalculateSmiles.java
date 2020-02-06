package benchmark;

import org.openjdk.jmh.annotations.*;
import task02.DRoot;
import task07.SmileFaces;

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
public class CalculateSmiles {
    @Param({"':)', ';(', ';}', ':-D'", "';D', ':-(', ':-)', ';~)'", "';]', ':[', ';*', ':$', ';-D'"})
    private String input;

    private List<String> faces;

    @Setup
    public void init()
    {
        faces = Arrays.asList(input.replaceAll("'", "").split(","));
        System.out.println("faces: = " + faces);
    }

    @Benchmark
    public int countSmileys()
    {
        return SmileFaces.countSmileys(faces);
    }

    @Benchmark
    public int countSmileysRegexp()
    {
        return SmileFaces.countSmileysRegexp(faces);
    }

}
