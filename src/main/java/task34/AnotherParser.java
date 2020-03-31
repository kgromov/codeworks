package task34;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by konstantin on 30.03.2020.
 */
public class AnotherParser {
    private static final Pattern PATTERN = Pattern.compile(
            "^((.*) millions?)?( and )?" +
                    "(((.*) hundreds?)?( and )?(.*) thousands?)?( and )?" +
                    "((.*) hundreds?)?( and )?" +
                    "(.*)?$");

    public static int parseInt(String numStr) {
        int number = 0;

        Matcher matcher = PATTERN.matcher(numStr);
        if (matcher.matches()) {
            number += parseGroup(matcher.group(2)) * 1_000_000;
            number += parseGroup(matcher.group(6)) * 100_000;
            number += parseGroup(matcher.group(8)) * 1000;
            number += parseGroup(matcher.group(11)) * 100;
            number += parseGroup(matcher.group(13));
        }

        return number;
    }

    private static int parseGroup(String group) {
        if (group == null) { return 0; }

        int hyphen = group.indexOf('-');
        if (hyphen >= 0) {
            return parseGroup(group.substring(0, hyphen))
                    + parseGroup(group.substring(hyphen + 1));
        }

        switch (group.trim()) {
            case "": return 0;
            case "zero": return 0;
            case "one": return 1;
            case "two": return 2;
            case "three": return 3;
            case "four": return 4;
            case "five": return 5;
            case "six": return 6;
            case "seven": return 7;
            case "eight": return 8;
            case "nine": return 9;
            case "ten": return 10;
            case "eleven": return 11;
            case "twelve": return 12;
            case "thirteen": return 13;
            case "fourteen": return 14;
            case "fifteen": return 15;
            case "sixteen": return 16;
            case "seventeen": return 17;
            case "eighteen": return 18;
            case "nineteen": return 19;
            case "twenty": return 20;
            case "thirty": return 30;
            case "forty": return 40;
            case "fifty": return 50;
            case "sixty": return 60;
            case "seventy": return 70;
            case "eighty": return 80;
            case "ninety": return 90;
            default:
                System.out.println("Unhandled: " + group);
                return 0;
        }
    }
}
