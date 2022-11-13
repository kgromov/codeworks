package task46;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by konstantin on 26.04.2020.
 */
public class BattleField {
    private static final int SIZE = 10;
    private static final Map<Integer, Integer> SHIPS_AMOUNT = new HashMap<>(4);

    static {
        SHIPS_AMOUNT.put(1, 4);
        SHIPS_AMOUNT.put(2, 3);
        SHIPS_AMOUNT.put(3, 2);
        SHIPS_AMOUNT.put(4, 1);
    }

    private static final int TOTAL_CELLS = SHIPS_AMOUNT.entrySet().stream().mapToInt(e -> e.getKey() * e.getValue()).sum();

    /*
     * Verification (return false if):
     * 0) size is not 10x10;
     * 1) not a binary value;
     * 2) there are more 1 than TOTAL_CELLS;
     * 3) touch by diagonal;
     * 4) there are more ships of certain type - check with SHIPS_AMOUNT
     */
    public static boolean fieldValidator(int[][] field) {
        // size is not 10x10
        if (field.length != SIZE || field[0].length != SIZE) {
            return false;
        }
        int totalCells = 0;
        Map<Integer, AtomicInteger> shipsAmount = new HashMap<>(4);
        Set<Pair<Integer, Integer>> cellsToSkip = new HashSet<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int cellValue = field[i][j];
                // not a binary value
                if (cellValue != 0 && cellValue != 1) {
                    return false;
                }
                if (cellValue == 1) {
                    boolean hasLeft = i - 1 > 0;
                    boolean hasRight = i + 1 < SIZE;
                    boolean hasDown = j - 1 > 0;
                    boolean hasUp = j + 1 < SIZE;
                    // touch by diagonal
                    if ((hasLeft && hasDown && field[i - 1][j - 1] == 1)
                            || (hasRight && hasDown && field[i + 1][j - 1] == 1)
                            || (hasLeft && hasUp && field[i - 1][j + 1] == 1)
                            || (hasRight && hasUp && field[i + 1][j + 1] == 1)) {
                        return false;
                    }
                    ++totalCells;
                    if (!cellsToSkip.contains(new Pair<>(i, j))) {
                        // horizontally
                        Pair<Integer, Integer> hRange = getSizeHorizontally(field, i, j);
                        // vertically
                        Pair<Integer, Integer> vRange = getSizeVertically(field, i, j);

                        int hSize = hRange.getValue() - hRange.getKey() + 1;
                        int vSize = vRange.getValue() - vRange.getKey() + 1;
                        if (hSize > 1 && vSize > 1) {
                            return false;
                        }
                        int shipSize = hSize > 1 ? hSize : vSize;
                        Integer expectedAmountBySize = SHIPS_AMOUNT.get(shipSize);
                        if (expectedAmountBySize == null
                                || shipsAmount.computeIfAbsent(shipSize, v -> new AtomicInteger()).incrementAndGet() > expectedAmountBySize) {
                            return false;
                        }
                        // add cells to skip not to find the same ship
                        if (hSize > 1) {
                            for (int k = hRange.getKey(); k <= hRange.getValue(); k++) {
                                cellsToSkip.add(new Pair<>(i, k));
                            }
                        } else {
                            for (int k = vRange.getKey(); k <= vRange.getValue(); k++) {
                                cellsToSkip.add(new Pair<>(k, j));
                            }
                        }
                    }
                }
            }
        }
        return totalCells == TOTAL_CELLS;
    }

    private static Pair<Integer, Integer> getSizeHorizontally(int field[][], int rowIndex, int columnIndex) {
        int right = columnIndex + 1;
        while (right < SIZE && field[rowIndex][right] == 1) {
            ++right;
        }
        return new Pair<>(columnIndex, right - 1);
    }

    private static Pair<Integer, Integer> getSizeVertically(int field[][], int rowIndex, int columnIndex) {
        int up = rowIndex + 1;
        while (up < SIZE && field[up][columnIndex] == 1) {
            ++up;
        }
        return new Pair<>(rowIndex, up - 1);
    }
}
