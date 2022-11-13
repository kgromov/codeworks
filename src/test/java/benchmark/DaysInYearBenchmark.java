package benchmark;

import org.openjdk.jmh.annotations.*;
import task07.SmileFaces;
import task61.DaysInYear;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
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
public class DaysInYearBenchmark {
    @Param({"1583", "1778", "1971", "2000", "2018", "3002"})
    private int year;

    @Benchmark
    public Collection<String> getMostFrequentDaysOfTheWeekCustom()
    {
        return DaysInYear.getMostFrequentDaysOfTheWeekCustom(year);
    }

    @Benchmark
    public Collection<String> getMostFrequentDaysOfTheWeekLocalDate()
    {
        return DaysInYear.getMostFrequentDaysOfTheWeekLocalDate(year);
    }

}
