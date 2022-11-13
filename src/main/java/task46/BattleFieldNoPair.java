package task46;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by konstantin on 26.04.2020.
 */
public class BattleFieldNoPair {
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
//        Set<String> cellsToSkip = new HashSet<>();
        Set<Integer> cellsToSkip = new HashSet<>();
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
//                    if (!cellsToSkip.contains(String.format("%d;%d", i, j))) {
                    if (!cellsToSkip.contains(31 * i + j)) {
                        // horizontally
                        int hSize = getSizeHorizontally(field, i, j);
                        // vertically
                        int vSize = getSizeVertically(field, i, j);
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
                            for (int k = 0; k <= hSize; k++) {
//                                cellsToSkip.add(String.format("%d;%d", i, j + k));
                                cellsToSkip.add(31 * i + j + k);
                            }
                        } else {
                            for (int k = 0; k <= vSize; k++) {
//                                cellsToSkip.add(String.format("%d;%d", i + k, j));
                                cellsToSkip.add(31 * (i + k) + j);
                            }
                        }
                    }
                }
            }
        }
        return totalCells == TOTAL_CELLS;
    }

    private static int getSizeHorizontally(int field[][], int rowIndex, int columnIndex) {
        int size = 1;
        for (int i = columnIndex + 1; i < SIZE && field[rowIndex][i] == 1; i++) {
            ++size;
        }
        return size;
    }

    private static int getSizeVertically(int field[][], int rowIndex, int columnIndex) {
        int size = 1;
        for (int i = rowIndex + 1; i < SIZE && field[i][columnIndex] == 1; i++) {
            ++size;
        }
        return size;
    }
}
