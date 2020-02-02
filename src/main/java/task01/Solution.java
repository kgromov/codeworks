package task01;

/**
 * Created by konstantin on 31.01.2020.
 */

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
    toCamelCase("the-stealth-warrior"); // returns "theStealthWarrior"
    toCamelCase("The_Stealth_Warrior"); // returns "TheStealthWarrior"
*/
public class Solution{
    private static final Pattern START_CAPITAL_LETTER_PATTERN = Pattern.compile("^[A-Z]");

    public static String toCamelCaseMySolution(String s){
        if (s == null || s.isEmpty())
        {
            return s;
        }
        String [] parts = s.split("[-_]");
        if (parts.length == 1)
        {
            return s;
        }

        StringBuilder result = new StringBuilder(parts[0]);
        for (int i = 1; i < parts.length; i++)
        {
            String part = parts[i];
            if (START_CAPITAL_LETTER_PATTERN.matcher(part).find())
            {
                result.append(part);
            }
            else
            {
                result.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1));
            }
        }
        return result.toString();
    }

    public static String toCamelCaseAppendReplacement(String s){
        Matcher m = Pattern.compile("[_|-](\\w)").matcher(s);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, m.group(1).toUpperCase());
        }
        return m.appendTail(sb).toString();
    }

    public static String toCamelCaseStream(String str){
        String[] words = str.split("[-_]");
        return Arrays.stream(words, 1, words.length)
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .reduce(words[0], String::concat);
    }

    public static String toCamelCaseCharArray(String s){
        StringBuffer sb = new StringBuffer();

        boolean flToUpperCase = false;
        for ( char ch: s.toCharArray() ) {
            if ( ch == '-' || ch == '_' )
                flToUpperCase = true;
            else {
                sb.append( ( flToUpperCase ) ? Character.toUpperCase(ch) : ch );
                flToUpperCase = false;
            }
        }
        return sb.toString();
    }
}