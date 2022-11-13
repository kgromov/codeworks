package task51;

/**
 * Created by konstantin on 30.04.2020.
 */

/**
 * Logic Examples:
 * If a user ranked -8 completes an activity ranked -7 they will receive 10 progress
 * If a user ranked -8 completes an activity ranked -6 they will receive 40 progress
 * If a user ranked -8 completes an activity ranked -5 they will receive 90 progress
 * If a user ranked -8 completes an activity ranked -4 they will receive 160 progress,
 * resulting in the user being upgraded to rank -7 and having earned 60 progress towards their next rank
 * If a user ranked -1 completes an activity ranked 1 they will receive 10 progress (remember, zero rank is ignored)
 *
 * The formula is 10 * d * d where d equals the difference in ranking between the activity and the user.
 *
 * Code Usage Examples:
 * User user = new User();
 * user.rank; // => -8
 * user.progress; // => 0
 * user.incProgress(-7);
 * user.progress; // => 10
 * user.incProgress(-5); // will add 90 progress
 * user.progress; // => 0 // progress is now zero
 * user.rank; // => -7 // rank was upgraded to -7
 *
 * Note: In Java some methods may throw an IllegalArgumentException.
 */
public class User {
    public int rank = -8;
    public int progress = 0;

    public void incProgress(int rank) {
        if (rank == -9 || rank == 0 || rank == 9) throw new IllegalArgumentException();
        progress += calculateProgress(rank);
        while(progress >= 100){
            if (this.rank + 1 == 0) this.rank = 0;
            this.rank += 1;
            progress -= 100;
        }
        if (this.rank == 8) progress = 0;
    }

    private int calculateProgress(int rank){
        int val = 0;
        int inc = Math.abs(this.rank - rank);
        if ((this.rank < 0 && rank > 0) || (this.rank > 0 && rank < 0)) inc -= 1;
        if (this.rank < rank) val = 10 * inc * inc;
        else if (this.rank > rank && inc == 1) val = 1;
        else if (this.rank == rank) val = 3;
        return val;
    }
}
