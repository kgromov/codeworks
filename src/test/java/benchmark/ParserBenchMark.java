package benchmark;

import org.openjdk.jmh.annotations.*;
import task34.AnotherParser;
import task34.BufferParser;
import task34.Parser;
import task34.StackParser;

import java.util.concurrent.TimeUnit;

/**
 * Created by konstantin on 30.03.2020.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class ParserBenchMark {

    @Param({"one", "two hundred", "forty-six",
            "one hundred and twenty-four",
            "two hundred forty-six",
            "two thousand seven hundred eighty-eight",
            "thirty-one thousand three hundred forty-eight",
            "seven hundred eighty-three thousand nine hundred and nineteen"
    })
    private String inputData;

    @Benchmark
    public int myParser()
    {
        return Parser.parseInt(inputData);
    }

    @Benchmark
    public int anotherParser()
    {
        return AnotherParser.parseInt(inputData);
    }

    @Benchmark
    public int bufferParser()
    {
        return BufferParser.parseInt(inputData);
    }

    @Benchmark
    public int stackParser()
    {
        return StackParser.parseInt(inputData);
    }
}
