package task52;

import java.util.regex.Pattern;

/**
 * Created by konstantin on 11.05.2020.
 */

/**
 * Define a regular expression which tests if a given string
 * representing a binary number is divisible by 5.
 *
 * Examples:
 * // 5 divisible by 5
 * DivisibleByFive.pattern().matcher('101').matches() == true
 *
 * // 666 not divisible by 5
 * DivisibleByFive.pattern().matcher('0000001010011010').matches() == false
 *
 * Note:
 * This can be solved by creating a Finite State Machine that evaluates if a string
 * representing a number in binary base is divisible by given number.
 * The detailed explanation for dividing by 3 is here:
 * https://math.stackexchange.com/questions/140283/why-does-this-fsm-accept-binary-numbers-divisible-by-three
 *
 * The FSM diagram for dividing by 5 is here:
 * http://aswitalski.github.io/img/FSM-binary-divisible-by-five.png
 */
public class DivisibleByFive {

    public static Pattern pattern() {
        // Partial solution:
//        return Pattern.compile("^0|(101(0)*)$");
      /*  return Pattern.compile("^("
                + "0+" //state 0 -> state 0
                + "|"
                + "1(10)*(11|0)" //state 0 -> state 1 -> state 2
                + "(01*0(01)*(1|00))*1" //state 2 -> state 4 -> state 3 -> state 2 -> state 0
                + ")+$");
           }*/
        return Pattern.compile("^(0|1(10)*(0|11)(01*01|01*00(10)*(0|11))*1)+$");
    }
}
