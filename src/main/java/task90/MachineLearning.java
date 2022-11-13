package task90;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by konstantin on 13.11.2022.
 */
public class MachineLearning {
    private int currentCmd;
    private Map<Integer, Integer> cmdMap = new HashMap<>();

    public int command (int cmd, int num) {
        currentCmd = cmd;
        if (!cmdMap.containsKey(cmd)) {
            cmdMap.put(cmd, 0);
        }
        int action = cmdMap.get(cmd);
        return Actions.FUNCTIONS.get(action).apply(num);
    }

    public void response(boolean result) {
        if (!result) {
            int action = cmdMap.get(currentCmd);
            cmdMap.put(currentCmd, action + 1);
        }
    }
}
