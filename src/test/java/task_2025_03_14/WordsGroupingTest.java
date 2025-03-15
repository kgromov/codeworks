package task_2025_03_14;

import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertTrue;

public class WordsGroupingTest {
    private String[] theSameLengthInput = {"eat", "tea", "tan", "ate", "nat", "bat"};
    private String[] differentLengthInput = {"eat", "spoon", "milk", "tea", "breakfast"};
    private String[] duplicatesInput = {"eat", "tea", "tan", "ate", "nat", "eat"};

    @Test
    public void testListSolution() {
        var grouping = WordsGrouping.listSolution(theSameLengthInput);
    }

    @Test
    public void testLinkedSetSolution() {
    }

    @Test
    public void testMapSolution() {
    }
}