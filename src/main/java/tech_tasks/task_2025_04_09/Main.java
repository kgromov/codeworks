package tech_tasks.task_2025_04_09;

import java.util.Collection;
import java.util.List;

/**
 * Implement batch iterator over list with configurable batch size.
 * 1. BatchIterator should implement Iterator<T> interface
 * 2. Generic type should be restricted by any number type (Integer, Long, etc.)
 * 3. The class should throw exception in case invalid input arguments
 * 4. The BatchIterator should be an effectively immutable class
 */
public class Main {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8);

        BatchIterator<Integer> it = new BatchIterator<>(list, 3);

        boolean hasNext = it.hasNext();
        System.out.println("[TRACE] hasNext: true = " + hasNext);

        Collection<Integer> r = it.next();
        System.out.println("[TRACE] next: [1, 2, 3] = " + r);


        hasNext = it.hasNext();
        System.out.println("[TRACE] hasNext: true = " + hasNext);

        r = it.next();
        System.out.println("[TRACE] next: [4, 5, 6] = " + r);


        hasNext = it.hasNext();
        System.out.println("[TRACE] hasNext: true = " + hasNext);

        r = it.next();
        System.out.println("[TRACE] next: [7, 8] = " + r);


        hasNext = it.hasNext();
        System.out.println("[TRACE] hasNext: false = " + hasNext);
    }
}