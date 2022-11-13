package task_2022_11_12;

import java.util.*;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * There are no given candidates. An elector can vote for anyone.
 * Each ballot contains only one name and represents one vote for this name.
 * A name is an arbitrary string, e.g. "A", "B", or "XJDHD".
 * <p>
 * There are no spoiled ballots.
 * <p>
 * The ballot-box is represented by an unsorted list of names.
 * Each entry in the list corresponds to one vote for this name.
 * You do not know the names in advance (because there are no candidates).
 * <p>
 * A name wins the election if it gets more than n/2 votes (n = number of all votes, i.e. n is equal to the size of the given list).
 * <p>
 * Please keep in mind the list of votes can be large (n <= 1,200,000).
 * The given list is immutable, i.e. you cannot modify the list (otherwise this could lead to vote rigging).
 * <p>
 * Good luck and have fun.
 */
public class BallotsCounter {

    public static String getWinner(final List<String> listOfBallots) {
        int majority = listOfBallots.size() / 2;
        Map<String, AtomicInteger> voteResults = new HashMap<>();
        for (String ballot : listOfBallots) {
            if (voteResults.computeIfAbsent(ballot, votes -> new AtomicInteger()).incrementAndGet() > majority) {
                return ballot;
            }
        }
        //  listOfBallots.stream().collect(Collectors.groupingBy(Function::identity(), Collectors.counting()));
        return voteResults.entrySet().stream()
//                .sorted(Map.Entry.comparingByValue((o1, o2) -> -Integer.compare(o1.get(), o2.get())))
                .sorted(Map.Entry.comparingByValue(Comparator.comparing(AtomicInteger::get).reversed()))
                .limit(1)
                .filter(entry -> entry.getValue().get() > majority)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    public static String getWinnerMap2(final List<String> listOfBallots) {
        int majority = listOfBallots.size() / 2;
        Map<String, AtomicInteger> voteResults = new HashMap<>();
        for (String ballot : listOfBallots) {
            if (voteResults.computeIfAbsent(ballot, votes -> new AtomicInteger()).incrementAndGet() > majority) {
                return ballot;
            }
        }
        for(Map.Entry<String, AtomicInteger> vote : voteResults.entrySet()) {
            if (vote.getValue().get() > majority) {
                return vote.getKey();
            }
        }
        return null;
    }


    public static String getWinnerBySet(final List<String> listOfBallots) {
        int majority = listOfBallots.size() / 2;
        Set<String> uniqueBallots = new HashSet<>(listOfBallots);
        for (String ballot : uniqueBallots) {
            if (Collections.frequency(listOfBallots, ballot) > majority) {
                return ballot;
            }
        }
        return null;
    }
}
