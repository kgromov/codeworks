package task34;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by konstantin on 30.03.2020.
 */
public class BufferParser {
    private static final Map<String, Integer> keywords = new HashMap<>();

    static {
        keywords.put("zero", 0);
        keywords.put("ten", 10);
        keywords.put("hundred", 100);
        keywords.put("one", 1);
        keywords.put("eleven", 11);
        keywords.put("thousand", 1000);
        keywords.put("two", 2);
        keywords.put("twelve", 12);
        keywords.put("twenty", 20);
        keywords.put("million", 1000000);
        keywords.put("three", 3);
        keywords.put("thirteen", 13);
        keywords.put("thirty", 30);
        keywords.put("four", 4);
        keywords.put("fourteen", 14);
        keywords.put("forty", 40);
        keywords.put("five", 5);
        keywords.put("fifteen", 15);
        keywords.put("fifty", 50);
        keywords.put("six", 6);
        keywords.put("sixteen", 16);
        keywords.put("sixty", 60);
        keywords.put("seven", 7);
        keywords.put("seventeen", 17);
        keywords.put("seventy", 70);
        keywords.put("eight", 8);
        keywords.put("eighteen", 18);
        keywords.put("eighty", 80);
        keywords.put("nine", 9);
        keywords.put("nineteen", 19);
        keywords.put("ninety", 90);
    }

    public static int parseInt(String numStr) {
        int res = 0, buffer = 0; //buffer is for cases like "x hundred"

        for (String word : numStr.split("[\\s|-]")) {
            int i = keywords.getOrDefault(word, -1);

            if (i == -1) continue;

            if (i == 100) {
                res += buffer * 100;
                buffer = 0;
            } else if (i == 1000) {
                res = (res + buffer) * 1000;
                buffer = 0;
            } else if (i == 1_000_000) {
                res = (res + buffer) * 1_000_000;
                buffer = 0;
            } else {
                buffer += i;
            }
        }
        return res + buffer;
    }
}
