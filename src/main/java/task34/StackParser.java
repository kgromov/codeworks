package task34;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by konstantin on 30.03.2020.
 */
public class StackParser {
    private final static Map<String,Integer> map = new HashMap<String,Integer>() {{
        put("zero", 0);   put("ten", 10);        put("twenty", 20);    put("hundred", 100);
        put("one", 1);    put("eleven", 11);     put("thirty", 30);    put("thousand", 1000);
        put("two", 2);    put("twelve", 12);     put("forty", 40);     put("million", 1000000);
        put("three", 3);  put("thirteen", 13);   put("fifty", 50);
        put("four", 4);   put("fourteen", 14);   put("sixty", 60);
        put("five", 5);   put("fifteen", 15);    put("seventy", 70);
        put("six", 6);    put("sixteen", 16);    put("eighty", 80);
        put("seven", 7);  put("seventeen", 17);  put("ninety", 90);
        put("eight", 8);  put("eighteen", 18);
        put("nine", 9);   put("nineteen", 19);
    }};

    public static int parseInt(String numStr) {
        // Tokenize and change to numbers
        String tokens[] = numStr.replace("-"," ").replace(" and","").split(" ");
        int ary[] = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++)
        {
            ary[i] = map.get(tokens[i]);
        }
        // Do my magic
        Stack<Integer> stk = new Stack<>();
        for (int k : ary) {
            if (!stk.empty() && k > stk.peek()) {
                int sum = 0;
                while (!stk.empty() && sum + stk.peek() < k)
                {
                    sum += stk.pop();
                }
                k *= sum;
            }
            stk.push(k);
        }
        // Result is sum of everything left on the stack
        int ret = 0;
        while (!stk.empty())
        {
            ret += stk.pop();
        }
        return ret;
    }
}
