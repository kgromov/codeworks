package benchmark;

import org.openjdk.jmh.annotations.*;
import task01.Solution;

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
public class ConvertStringCamelCase {
  /*  toCamelCase("the-stealth-warrior"); // returns "theStealthWarrior"

    toCamelCase("The_Stealth_Warrior"); // returns "TheStealthWarrior"*/
    @Param({"the-stealth-warrior", "theStealthWarrior", "The_Stealth_Warrior", "TheStealthWarrior"})
    private String inputString;

    @Benchmark
    public String toCamelCaseMySolution()
    {
        return Solution.toCamelCaseMySolution(inputString);
    }

    @Benchmark
    public String toCamelCaseAppendReplacement()
    {
        return Solution.toCamelCaseAppendReplacement(inputString);
    }


    @Benchmark
    public String toCamelCaseStream()
    {
        return Solution.toCamelCaseStream(inputString);
    }

    @Benchmark
    public String toCamelCaseCharArray()
    {
        return Solution.toCamelCaseCharArray(inputString);
    }
}
