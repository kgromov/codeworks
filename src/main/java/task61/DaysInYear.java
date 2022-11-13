package task61;

/**
 * Created by konstantin on 14.06.2020.
 */

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * What is your favourite day of the week? Check if it's the most frequent day of the week in the year.
 * You are given a year as integer (e. g. 2001). You should return the most frequent day(s) of the week in that year.
 * The result has to be a list of days sorted by the order of days in week
 * (e. g. ['Monday', 'Tuesday'], ['Saturday', 'Sunday'], ['Monday', 'Sunday']).
 * Week starts with Monday.
 * <p>
 * Input: Year as an int.
 * Output: The list of most frequent days sorted by the order of days in week (from Monday to Sunday).
 * <p>
 * Preconditions:
 * Week starts on Monday.
 * Year is between 1583 and 4000.
 * Calendar is Gregorian.
 */
public class DaysInYear {

    private static final int DAYS_IN_WEEK = 7;
    private static final int DAYS_IN_YEAR = 365;
    private static final int DAYS_IN_LEAP_YEAR = DAYS_IN_YEAR + 1;

    private static final Comparator<AtomicInteger> COMPARATOR = Comparator.comparingInt(AtomicInteger::get);

    public static Collection<String> getMostFrequentDaysOfTheWeekLocalDate(int year) {
        if (year < 1583 || year > 4000) {
            throw new IllegalArgumentException(String.format("Year should in range: [1583; 4000], but was %d", year));
        }
        Map<DayOfWeek, AtomicInteger> daysOfTheWeekOccurrences = new EnumMap<>(DayOfWeek.class);
        Map<DayOfWeek, Integer> daysOfTheWeekOccurrences2 = new EnumMap<>(DayOfWeek.class);

        int daysInYear = Year.isLeap(year) ? DAYS_IN_LEAP_YEAR : DAYS_IN_YEAR;
        for (int day = 1; day <= daysInYear; day++) {
            daysOfTheWeekOccurrences.computeIfAbsent(LocalDate.ofYearDay(year, day).getDayOfWeek(),
                    frequency -> new AtomicInteger()).incrementAndGet();
        }

        NavigableMap<Integer, Collection<String>> result = new TreeMap<>();
        daysOfTheWeekOccurrences.forEach(((dayOfWeek, frequency) ->
                result.computeIfAbsent(frequency.get(), days -> new ArrayList<>()).add(dayOfWeek.name())));
//                        days -> new TreeSet<>(Comparator.comparing(DayOfWeek::getValue))).add(dayOfWeek)));

        // 2nd approach
       /* Map<AtomicInteger, Set<DayOfWeek>> collect = daysOfTheWeekOccurrences.entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toSet())
                ));

        collect.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(COMPARATOR))
                .limit(1)
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());*/
        return result.lastEntry().getValue();
    }

    public static Collection<String> getMostFrequentDaysOfTheWeekCustom(int year) {
        if (year < 1583 || year > 4000) {
            throw new IllegalArgumentException(String.format("Year should in range: [1583; 4000], but was %d", year));
        }
        Map<DayOfWeek, AtomicInteger> daysOfTheWeekOccurrences = new EnumMap<>(DayOfWeek.class);
        int daysInYear = Year.isLeap(year) ? DAYS_IN_LEAP_YEAR : DAYS_IN_YEAR;
        DayOfWeek firstDayOfTheYear = LocalDate.ofYearDay(year, 1).getDayOfWeek();

        int firstDayValue = firstDayOfTheYear.getValue();
        // all days till next monday (if 1st day of the year is not Monday)
        for (int i = firstDayValue; i <= DAYS_IN_WEEK && firstDayOfTheYear != DayOfWeek.MONDAY; i++) {
            daysOfTheWeekOccurrences.put(DayOfWeek.of(i), new AtomicInteger(1));
        }
        // common full weeks
        int startFullWeekDay = (8 + 1 - firstDayValue) % DAYS_IN_WEEK;
        int fullWeeksInYear = (daysInYear - startFullWeekDay) / DAYS_IN_WEEK;
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            daysOfTheWeekOccurrences.computeIfAbsent(dayOfWeek,
                    frequency -> new AtomicInteger()).addAndGet(fullWeeksInYear);
        }
        // left over (if last day of the year is not Sunday)
        int lastDayOfFullWeekValue = fullWeeksInYear * DAYS_IN_WEEK;
        int leftOver = firstDayOfTheYear != DayOfWeek.MONDAY ? DAYS_IN_WEEK - firstDayValue + 1 : 0;
        for (int i = 1; i <=daysInYear - lastDayOfFullWeekValue - leftOver; i++) {
            daysOfTheWeekOccurrences.computeIfAbsent(DayOfWeek.of(i),
                    frequency -> new AtomicInteger()).incrementAndGet();
        }
        NavigableMap<Integer, Collection<String>> result = new TreeMap<>();
        daysOfTheWeekOccurrences.forEach(((dayOfWeek, frequency) ->
                result.computeIfAbsent(frequency.get(), days -> new ArrayList<>()).add(dayOfWeek.name())));
        return result.lastEntry().getValue();
    }

    public static void main(String[] args) {
        Calendar calendar = new Calendar.Builder().set(Calendar.YEAR, 1971).build();

        System.out.println(getMostFrequentDaysOfTheWeekLocalDate(1971));
        System.out.println(getMostFrequentDaysOfTheWeekCustom(1971));


        System.out.println(getMostFrequentDaysOfTheWeekLocalDate(2001));
        System.out.println(getMostFrequentDaysOfTheWeekCustom(2001));

        System.out.println(getMostFrequentDaysOfTheWeekLocalDate(2020));
        System.out.println(getMostFrequentDaysOfTheWeekCustom(2020));
    }
}
