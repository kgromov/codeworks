package task41;

import task41.Pair;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Created by konstantin on 01.04.2020.
 */
public class Power {

    public static Pair<Integer, Integer> compute(int value) {
        if (value <= 0) {
            return null;
        }
        boolean isEven = value % 2 == 0;
        int startX = isEven ? 2 : 3;
        for (int x = startX; x * x <= value; x += 2) {
            int res = x;
            int y = 2;
            while (res < value)
            {
                res*=x;
                if (res == value)
                {
                    System.out.println(String.format("%d = %d ^ %d", value, x, y));
                    return new Pair<>(x, y);
                }
                ++y;
            }
        }
        System.out.println(String.format("%d = %d ^ %d", value, value, 1));
        return new Pair<>(value, 1);
    }

    public static Pair<Integer, Integer> compute2(int value) {
        if (value <= 0) {
            return null;
        }
        int startX = value % 2 == 0 ? 2 : 3;
        for (int x = startX; x * x <= value; x += 2) {
            AtomicInteger res = new AtomicInteger(x);
            final int b = x;
            Optional<Integer> y = IntStream.range(2, 9).boxed()
                    .filter(i -> res.get() < value)
                    .peek(i -> res.updateAndGet(a -> res.get() * b))
                    .filter(i -> res.get() == value)
                    .findFirst();
            if (y.isPresent())
            {
                System.out.println(String.format("%d = %d ^ %d", value, x, y.get()));
                return new Pair<>(x, y.get());
            }
        }
        System.out.println(String.format("%d = %d ^ %d", value, value, 1));
        return new Pair<>(value, 1);
    }
}
