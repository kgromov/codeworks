package tech_tasks.task_2025_04_25;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Implement collections logic as described.
 */
public class Task08Collections {

    static class Student {
        private String name;
        private List<String> languages;

        Student(String name, List<String> languages) {
            this.name = name;
            this.languages = languages;
        }

        public String getName() {
            return name;
        }

        public List<String> getLanguages() {
            return languages;
        }

        @Override
        public String toString() {
            return "\nStudent{" +
                    ",\nname='" + name + '\'' +
                    "\nlanguages=" + languages +
                    "\n}";
        }
    }

    /**
     * Given list of students group them by language.
     */
    public static Map<String, List<Student>> getStudentsByLanguage(List<Student> students) {
        Map<String, List<Student>> groupingByLanguage = new HashMap<>();
        students.forEach(student -> student.getLanguages()
                .forEach(lang -> groupingByLanguage.computeIfAbsent(lang, _ -> new ArrayList<>()).add(student))
        );
        return groupingByLanguage;
    }

    public static Map<String, List<Student>> getStudentsByLanguageStream(List<Student> students) {
        return students.stream()
                .flatMap(student -> student.getLanguages().stream()
                        .map(language -> Map.entry(language, student))
                )
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ));
    }

    /**
     * Given list of students with languages, return list of unique languages.
     */
    public static List<String> getAllLanguages(List<Student> students) {
        return students.stream()
                .flatMap(student -> student.getLanguages().stream())
                .distinct()
                .toList();
    }

    /**
     * Given list of students, remove duplicates by name.
     */
    public static List<Student> removeDuplicates(List<Student> students) {
        Set<String> uniqueNames = new HashSet<>();
        return students.stream()
                .filter(student -> uniqueNames.add(student.getName()))
                .toList();
    }

    public static List<Student> removeDuplicatesStream(List<Student> students) {
        return students.stream()
                .collect(Collectors.toMap(
                                Student::getName,
                                Function.identity(),
                                (existing, replacement) -> existing
                        )
                )
                .values()
                .stream()
                .toList();
    }

    // Data example
    static final List<Student> students = Arrays.asList(
            new Student("Doug Lea", Arrays.asList("Java", "C#", "JavaScript")),
            new Student("Bjarne Stroustrup", Arrays.asList("C", "C++", "Java")),
            new Student("Martin Odersky", Arrays.asList("Java", "Scala", "Smalltalk"))
    );


    public static void main(String[] args) {
        System.out.println(getAllLanguages(students));
        System.out.println("\n" + getStudentsByLanguage(students));
        System.out.println("\n" + removeDuplicates(students));
    }

}
