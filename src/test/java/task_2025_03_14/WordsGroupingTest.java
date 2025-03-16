package task_2025_03_14;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class WordsGroupingTest {
    private String[] theSameLengthInput = {"eat", "tea", "tan", "ate", "nat", "bat"};
    private String[][] theSameLengthGroupings = {{"eat", "tea", "ate"}, {"tan", "nat"}, {"bat"}};
    private String[] differentLengthInput = {"eat", "spoon", "milk", "tea", "breakfast"};
    private String[][] differentLengthGroupings = {{"eat", "tea"}, {"spoon"}, {"milk"}, {"breakfast"}};
    private String[] duplicatesInput = {"eat", "tea", "tan", "ate", "nat", "eat", "nat"};
    private String[][] duplicatesGroupings = {{"eat", "tea", "ate"}, {"tan", "nat"}};

    @Test
    public void testLinkedSetSolution() {
        var grouping1 = WordsGrouping.linkedSetSolution(theSameLengthInput);
        verifyGrouping(grouping1, theSameLengthGroupings);

        var grouping2 = WordsGrouping.linkedSetSolution(differentLengthInput);
        verifyGrouping(grouping2, differentLengthGroupings);

        var grouping3 = WordsGrouping.linkedSetSolution(duplicatesInput);
        verifyGrouping(grouping3, duplicatesGroupings);
    }

    @Test
    public void testMapSolution() {
        var grouping1 = WordsGrouping.mapSolution(theSameLengthInput);
        verifyGrouping(grouping1, theSameLengthGroupings);

        var grouping2 = WordsGrouping.mapSolution(differentLengthInput);
        verifyGrouping(grouping2, differentLengthGroupings);

        var grouping3 = WordsGrouping.mapSolution(duplicatesInput);
        verifyGrouping(grouping3, duplicatesGroupings);
    }

    private void verifyGrouping(
            Collection<? extends Collection<String>> grouping,
            String[][] expectedGroupings
    ) {
        assertThat(grouping.size()).isEqualTo(expectedGroupings.length);
        int gropingIndex = 0;
        for (var group : grouping) {
            var groupAsList = new ArrayList<>(group);
            assertThat(groupAsList).containsExactly(expectedGroupings[gropingIndex]);
            gropingIndex++;
        }
    }
}