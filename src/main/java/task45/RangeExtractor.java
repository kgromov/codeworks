package task45;

/**
 * Created by konstantin on 08.04.2020.
 */

/**
 * A format for expressing an ordered list of integers is to use a comma separated list of either
 * or a range of integers denoted by the starting integer separated from the end integer in the range by a dash, '-'.
 * The range includes all integers in the interval including both endpoints.
 * It is not considered a range unless it spans at least 3 numbers.
 * For example ("12, 13, 15-17")
 * <p>
 * Complete the solution so that it takes a list of integers in increasing order
 * and returns a correctly formatted string in the range format.
 * <p>
 * E.g.
 * input = new int[] {-6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20}
 * output = "-6,-3-1,3-5,7-11,14,15,17-20"
 */
public class RangeExtractor {

    public static String toRange(int[] arr) {
        if (arr.length == 0) {
            return "";
        }
        int rangeStart = arr[0];
        int rangeLength = 0;
        StringBuilder rangeBuilder = new StringBuilder().append(rangeStart);
        for (int i = 1; i < arr.length; i++) {
            int diff = Math.abs(arr[i] - rangeStart) - rangeLength;
            if (diff == 1) {
                ++rangeLength;
            } else {
                if (rangeLength > 0) {
                    rangeBuilder.append(rangeLength > 1 ? '-' : ',').append(rangeStart + rangeLength);
                }
                rangeBuilder.append(',').append(arr[i]);
                rangeStart = arr[i];
                rangeLength = 0;
            }
        }
        if (rangeLength > 0) {
            rangeBuilder.append(rangeLength > 1 ? '-' : ',').append(rangeStart + rangeLength);
        }
        return rangeBuilder.toString();
    }
}
