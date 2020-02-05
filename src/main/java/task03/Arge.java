package task03;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

/*
At the end of the first year there will be:
1000 + 1000 * 0.02 + 50 => 1070 inhabitants

At the end of the 2nd year there will be:
1070 + 1070 * 0.02 + 50 => 1141 inhabitants (number of inhabitants is an integer)

At the end of the 3rd year there will be:
1141 + 1141 * 0.02 + 50 => 1213

It will need 3 entire years.
 */
public class Arge {
    public static int nbYear(int p0, double percent, int aug, int p) {
        if (p0 > p)
        {
            return 0;
        }
        double intermediatePopulation = p0;
//        BigDecimal toPercent = BigDecimal.valueOf(100);
        BigDecimal toPercent = new BigDecimal(100, MathContext.DECIMAL64);
        double percentage = BigDecimal.valueOf(percent).divide(toPercent, 100, RoundingMode.HALF_EVEN).doubleValue();
        int years;
        for (years = 0; Double.compare(intermediatePopulation, p) <= 0; years++)
        {
            intermediatePopulation = intermediatePopulation + intermediatePopulation * percentage + aug;
        }
        return years;
    }

    public static int nbYearStream(int p0, double percent, int aug, int p) {
        AtomicInteger intermediatePopulation = new AtomicInteger(p0);
        double percentage = (percent * 100) / 10000.0;
        IntUnaryOperator ibo = x -> BigDecimal.valueOf(x + x * percentage + aug).intValue();
        return (int) IntStream.iterate(0, c -> intermediatePopulation.getAndUpdate(ibo))
                .filter(i -> intermediatePopulation.get() <= p).count();
    }
}
