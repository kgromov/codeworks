package benchmark;

import javafx.util.Pair;
import org.openjdk.jmh.annotations.*;
import task33.LongestConsec;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by konstantin on 28.03.2020.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class LongestConsecBenchMark {
    private static final Map<Integer, Pair<String[], Integer>> DATA = new HashMap<>();
    static
    {
        DATA.put(0, new Pair<>(new String[] {"zone", "abigail", "theta", "form", "libe", "zas", "theta", "abigail"}, 2));
        DATA.put(1, new Pair<>(new String[] {"ejjjjmmtthh", "zxxuueeg", "aanlljrrrxx", "dqqqaaabbb", "oocccffuucccjjjkkkjyyyeehh"}, 1));
        DATA.put(2, new Pair<>(new String[] {}, 3));
        DATA.put(3, new Pair<>(new String[] {"itvayloxrp","wkppqsztdkmvcuwvereiupccauycnjutlv","vweqilsfytihvrzlaodfixoyxvyuyvgpck"}, 2));
        DATA.put(4, new Pair<>(new String[] {"wlwsasphmxx","owiaxujylentrklctozmymu","wpgozvxxiu"}, 2));
        DATA.put(5, new Pair<>(new String[] {"zone", "abigail", "theta", "form", "libe", "zas"}, -2));
        DATA.put(6, new Pair<>(new String[] {"it","wkppv","ixoyx", "3452", "zzzzzzzzzzzz"}, 3));
        DATA.put(7, new Pair<>(new String[] {"it","wkppv","ixoyx", "3452", "zzzzzzzzzzzz"}, 15));
        DATA.put(8, new Pair<>(new String[] {"it","wkppv","ixoyx", "3452", "zzzzzzzzzzzz"}, 0));
    }

    @Param({"0", "1", "2", "3", "4", "5", "6", "7", "8"})
    private int key;
    private Pair<String[], Integer> inputData;

    @Setup
    public void setup()
    {
        inputData = DATA.get(key);
    }

    @Benchmark
    public String longestConsec()
    {
        return LongestConsec.longestConsec(inputData.getKey(), inputData.getValue());
    }

    @Benchmark
    public String longestConsecStringBuilder()
    {
        return LongestConsec.longestConsecStringBuilder(inputData.getKey(), inputData.getValue());
    }

    @Benchmark
    public String longestConsecMaxComparator()
    {
        return LongestConsec.longestConsecMaxComparator(inputData.getKey(), inputData.getValue());
    }
}
