package task82;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// sucked to solve
public class SkyScrapers {

    private final static Integer SIZE = 4;

    static int[][] solvePuzzle(int[] clues) {
        return new SkyScrapers().solve(clues);
    }

    private static class AbortException extends Exception {
    }

    private Table table;

    private int[][] solve(int[] clues) {
        table = new Table(clues);
        try {
            Position initPosition = table.getInitPosition();
            if (iter(initPosition)) {
                return table.table;
            } else {
                throw new RuntimeException("no solution");
            }
        } catch (AbortException e) {
            return table.table;
        }
    }

    private boolean iter(Position position) {
        Set<Integer> possibleValues = table.getPossibleValuesForPosition(position);
        for (Integer possibleValue : possibleValues) {
            table.setValueForPosition(position, possibleValue);
            try {
                Position nextPosition = table.getNextPosition(position);
                if (iter(nextPosition))
                    return true;
                else
                    table.setValueForPosition(position, 0);
            } catch (AbortException e) {
                return true;
            }
        }
        return false;
    }

    static class Position {
        Integer x;
        Integer y;

        Position(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }

        Integer getX() {
            return x;
        }

        Integer getY() {
            return y;
        }
    }

    static class Table {
        int[][] table;
        Line[] horizontalLines = new Line[SIZE];
        Line[] verticalLines = new Line[SIZE];

        Table(int[] clues) {
            initTable();
            initLinesWithClues(clues);
        }

        private Integer getValueForPosition(int x, int y) {
            return table[y][x]; // inverted!
        }

        void setValueForPosition(Position position, int value) {
            int x = position.getX();
            int y = position.getY();
            setValueForPosition(x, y, value);
            verticalLines[x].line[y] = value;
            horizontalLines[y].line[x] = value;
        }

        private void setValueForPosition(int x, int y, int value) {
            table[y][x] = value; // inverted!
        }

        Position getInitPosition() throws AbortException {
            Position position = new Position(0, 0);
            if (getValueForPosition(0, 0) != 0) position = getNextPosition(position);
            return position;
        }

        Position getNextPosition(Position position) throws AbortException {
            int x = position.getX();
            int y = position.getY();
            do {
                if (x == SIZE - 1)
                    if (y == SIZE - 1)
                        throw new AbortException();
                    else {
                        x = 0;
                        y++;
                    }
                else
                    x++;
            } while (getValueForPosition(x, y) != 0);
            return new Position(x, y);
        }

        Set<Integer> getPossibleValuesForPosition(Position position) {
            int x = position.getX();
            int y = position.getY();
            Set<Integer> possibleValues = new HashSet<>();
            Set<Integer> possibleValuesForHorizontalLine = horizontalLines[y].getPossibleValuesForPosition(x);
            Set<Integer> possibleValuesForVerticalLine = verticalLines[x].getPossibleValuesForPosition(y);
            possibleValues.addAll(possibleValuesForHorizontalLine);
            possibleValues.retainAll(possibleValuesForVerticalLine);
            return possibleValues;
        }

        private void initLinesWithClues(int[] clues) {
            IntStream.range(0, SIZE).forEach(x -> verticalLines[x] = new Line(getVerticalLine(x), clues[x], clues[3 * SIZE - 1 - x]));
            synchTableWithVerticalLines();
            IntStream.range(0, SIZE).forEach(y -> horizontalLines[y] = new Line(getHorizontalLine(y), clues[4 * SIZE - 1 - y], clues[SIZE + y]));
            synchTableWithHorizontalLines();
        }

        private void synchTableWithHorizontalLines() {
            for (int x = 0; x < SIZE; x++)
                for (int y = 0; y < SIZE; y++)
                    setValueForPosition(x, y, horizontalLines[y].line[x]);
        }

        private void synchTableWithVerticalLines() {
            for (int x = 0; x < SIZE; x++)
                for (int y = 0; y < SIZE; y++)
                    setValueForPosition(x, y, verticalLines[x].line[y]);
        }

        private int[] getVerticalLine(int x) {
            int[] line = new int[SIZE];
            IntStream.range(0, SIZE).forEach(y -> line[y] = getValueForPosition(x, y));
            return line;
        }

        private int[] getHorizontalLine(int y) {
            int[] line = new int[SIZE];
            IntStream.range(0, SIZE).forEach(x -> line[x] = getValueForPosition(x, y));
            return line;
        }

        private void initTable() {
            table = new int[SIZE][SIZE];
            IntStream.range(0, SIZE).forEach(i -> Arrays.fill(table[i], 0));
        }

    }

    static class Line {
        int[] line;
        int topClue;
        int bottomClue;

        private Set<Integer> allNonInitClues = IntStream.rangeClosed(2, SIZE - 1).boxed().collect(Collectors.toSet());

        Line(int[] line, int topClue, int bottomClue) {
            this.line = line;
            this.topClue = topClue;
            this.bottomClue = bottomClue;
            resolveInitClues();
        }

        private void resolveInitClues() {
            if (topClue == 1) line[0] = SIZE;
            if (bottomClue == 1) line[SIZE - 1] = SIZE;
            if (topClue == SIZE) IntStream.range(0, SIZE).forEach(i -> line[i] = i + 1);
            if (bottomClue == SIZE) IntStream.range(0, SIZE).forEach(i -> line[SIZE - 1 - i] = i + 1);
        }

        Set<Integer> getPossibleValuesForPosition(Integer positionInLine) {
            if (line[positionInLine] != 0)
                throw new IllegalArgumentException("Fatal Error : Trying to discover already discovered value !!"
                        + "\n position = " + positionInLine + "\n line : " + this);
            Set<Integer> alreadyPlacedValues = getAlreadyPlacedValues(positionInLine);
            Set<Integer> possibleValues = IntStream.rangeClosed(1, SIZE).boxed().collect(Collectors.toSet());
            possibleValues.removeAll(alreadyPlacedValues);
            possibleValues.removeIf(valueToTest -> !isCompatibleWithClues(valueToTest, positionInLine));
            return possibleValues;
        }

        private boolean isCompatibleWithClues(Integer valueToTest, int position) {
            line[position] = valueToTest;
            boolean result = true;
            if (this.isComplete()) result = this.isCompatibleWithClues(); // double negation!
            line[position] = 0;
            return result;
        }

        private boolean isComplete() {
            return IntStream.range(0, SIZE).allMatch(i -> line[i] != 0);
        }

        private boolean isCompatibleWithClues() {
            boolean isNotCompatibleWithClues =
                    (allNonInitClues.contains(topClue) && getNumberOfSkyscrapersOnLine(true) != topClue) ||
                            (allNonInitClues.contains(bottomClue) && getNumberOfSkyscrapersOnLine(false) != bottomClue);
            return !isNotCompatibleWithClues;
        }

        private int getNumberOfSkyscrapersOnLine(boolean isAscending) {
            int result = 0;
            int maxValue = 0;
            for (int i = 0; i < SIZE; i++) {
                int positionInLine = isAscending ? i : SIZE - 1 - i;
                if (line[positionInLine] > maxValue) {
                    maxValue = line[positionInLine];
                    result++;
                }
            }
            return result;
        }

        private Set<Integer> getAlreadyPlacedValues(int positionInLine) {
            Set<Integer> alreadyPlacedValues = new HashSet<Integer>();
            for (int i = 0; i < SIZE; i++) {
                if (i == positionInLine) continue;
                if (line[i] != 0) alreadyPlacedValues.add(line[i]);
            }
            return alreadyPlacedValues;
        }

    }
}