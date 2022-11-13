package task90;

import java.util.*;

/**
 * Created by konstantin on 13.11.2022.
 */
public class MachineLearning2 {

    Map<Integer, List<Integer>> memory = new HashMap<>();
    List<Integer> currentList;

    public int command(int cmd, int num) {
        if (!memory.containsKey(cmd)) {
            memory.put(cmd, new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4)));
        }
        currentList = memory.get(cmd);

        return Actions.FUNCTIONS.get(currentList.get(0)).apply(num);
    }

    public void response(boolean result) {
        if (!result) {
            currentList.remove(0);
        }
    }
}
