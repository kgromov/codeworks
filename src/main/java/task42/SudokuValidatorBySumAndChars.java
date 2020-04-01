package task42;

/**
 * Created by konstantin on 01.04.2020.
 */
public class SudokuValidatorBySumAndChars {
    public static boolean check(int[][] sudoku) {
        //do your magic
        //reminder: 3 checks:
        //sum rows, cols and sqr's == 45
        //no 0's
        // in every 3x3 from 1 to 9
        int sum_row;
        int sum_col;

        String sqr1 = "",sqr2 = "",sqr3 = "";


        for(int i = 0; i< sudoku.length; i++){
            sum_row = 0;
            sum_col = 0;

            for(int j = 0; j< sudoku.length; j++){
                if(sudoku[i][j] <1 || sudoku[i][j] > 9){return false;}//check 0's

                sum_row += sudoku[i][j];
                sum_col += sudoku[j][i];

                if(j<3){ sqr1 += Integer.toString(sudoku[i][j]);}
                if(j>2 && j<6){ sqr2 += Integer.toString(sudoku[i][j]);}
                if(j>5){ sqr3 += Integer.toString(sudoku[i][j]);}
            }

            if(sum_row != 45 || sum_col != 45){return false;} //check sum of rows,col

            if( (i+1) % 3 == 0){//chek squares every 3 iterations of i
                for(char c = '1'; c<='9'; c++){
                    if( sqr1.indexOf(c) < 0
                            ||sqr2.indexOf(c) < 0
                            ||sqr3.indexOf(c) < 0){return false;}
                }
            }

        }
        return true;
    }
}
