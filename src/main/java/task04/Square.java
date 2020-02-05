package task04;

/*
isSquare(-1) returns  false
isSquare(0) returns   true
isSquare(3) returns   false
isSquare(4) returns   true
isSquare(25) returns  true
isSquare(26) returns  false
 */
public class Square {
    public static boolean isSquare_(int n) {
        if (n < 0)
        {
            return false;
        }
        double result =  Math.sqrt(n);
        int rounded = (int) result;
        return n <= 1 || Double.compare(rounded, result) == 0;
    }

    public static boolean isSquare(int n) {
        return Math.sqrt(n) % 1 == 0;
    }
}
