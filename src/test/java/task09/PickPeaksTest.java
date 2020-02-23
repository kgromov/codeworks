package task09;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * Created by konstantin on 09.02.2020.
 */
public class PickPeaksTest {
    private static String[] msg = {"should support finding peaks",
            "should support finding peaks, but should ignore peaks on the edge of the array",
            "should support finding peaks; if the peak is a plateau, it should only return the position of the first element of the plateau",
            "should support finding peaks; if the peak is a plateau, it should only return the position of the first element of the plateau",
            "should support finding peaks, but should ignore peaks on the edge of the array"};

    private static int[][] array = {
            {-2, 19, 4, 5, 13, -2, 4, 7, 18, -1, 0, 13, 11, 12, 1, -3, -1, 10, -4},
            {1, 2, 3, 6, 4, 1, 2, 3, 2, 1},
            {3, 2, 3, 6, 4, 1, 2, 3, 2, 1, 2, 3},
            {3, 2, 3, 6, 4, 1, 2, 3, 2, 1, 2, 2, 2, 1},
            {2, 1, 3, 1, 2, 2, 2, 2, 1},
            {15, 1, 0, 3, 3, 11, 17, 18, -4, -2, 6},
            {2, 1, 3, 1, 2, 2, 2, 2}
    };

    private static int[][] posS = {
            {1, 4, 8, 11, 13, 17},
            {3, 7},
            {3, 7},
            {3, 7, 10},
            {2, 4},
            {7},
            {2}
};

    private static int[][] peaksS = {
            {19, 13, 18, 13, 12, 10},
            {6, 3},
            {6, 3},
            {6, 3, 2},
            {3, 2},
            {18},
            {3}
    };

    @Test
    public void sampleTests() {
        for (int n = 0; n < array.length; n++) {
            System.out.println(n);
            final int[] p1 = posS[n], p2 = peaksS[n];
            Map<String, List<Integer>> expected = new HashMap<String, List<Integer>>() {{
                put("pos", Arrays.stream(p1).boxed().collect(Collectors.toList()));
                put("peaks", Arrays.stream(p2).boxed().collect(Collectors.toList()));
            }},
            actual = PickPeaks.getPeaks(array[n]);
            assertEquals(msg[0], expected, actual);
        }
    }
}