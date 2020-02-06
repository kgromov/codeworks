package task07;

import java.util.List;
import java.util.regex.Pattern;

/*
countSmileys([':)', ';(', ';}', ':-D']);       // should return 2;
countSmileys([';D', ':-(', ':-)', ';~)']);     // should return 3;
countSmileys([';]', ':[', ';*', ':$', ';-D']); // should return 1;
 */
public class SmileFaces {
    private static final Pattern PATTERN = Pattern.compile("[:|;][-|~]?[)|D]");

    public static int countSmileys(List<String> arr) {
        String regExp = "[:|;][-|~]?[)|D]";
        return (int) arr.stream().filter(i -> i.matches(regExp)).count();
    }

    public static int countSmileysRegexp(List<String> arr) {
        return (int) arr.stream().filter(i -> PATTERN.matcher(i).matches()).count();
    }
}
