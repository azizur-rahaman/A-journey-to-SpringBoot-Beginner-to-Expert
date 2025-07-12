import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Person {
    String name;
    Double inBillion;

    Person(String name, Double inBillion){
        this.name = name;
        this.inBillion = inBillion;
    }

    @Override
    public String toString() {
        return name + " - $" + inBillion + " Billion";
    }
}

public class StreamExample {

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Azizur", 120.2));
        persons.add(new Person("Bill Gates", 100.2));
        persons.add(new Person("Mark Zuckerberg", 110.2));
        persons.add(new Person("Alice", 90.0));
        persons.add(new Person("Bob", 75.5));

        // 1. filter
        List<Person> filtered = persons.stream()
            .filter(p -> p.inBillion >= 100)
            .collect(Collectors.toList());
        System.out.println("Filter (>=100B):");
        filtered.forEach(System.out::println);

        // 2. map
        List<String> names = persons.stream()
            .map(p -> p.name.toUpperCase())
            .collect(Collectors.toList());
        System.out.println("\nMapped Names (Uppercase):");
        names.forEach(System.out::println);

        // 3. sorted
        List<Person> sortedByWealth = persons.stream()
            .sorted(Comparator.comparing(p -> -p.inBillion)) // descending order
            .collect(Collectors.toList());
        System.out.println("\nSorted by Wealth (Descending):");
        sortedByWealth.forEach(System.out::println);

        // 4. distinct (only works if `equals()` and `hashCode()` are properly implemented)
        List<Double> uniqueNetWorths = persons.stream()
            .map(p -> p.inBillion)
            .distinct()
            .collect(Collectors.toList());
        System.out.println("\nDistinct Net Worths:");
        uniqueNetWorths.forEach(System.out::println);

        // 5. limit
        List<Person> top2 = persons.stream()
            .sorted(Comparator.comparing(p -> -p.inBillion))
            .limit(2)
            .collect(Collectors.toList());
        System.out.println("\nTop 2 Richest:");
        top2.forEach(System.out::println);

        // 6. skip
        List<Person> skippedFirst2 = persons.stream()
            .sorted(Comparator.comparing(p -> -p.inBillion))
            .skip(2)
            .collect(Collectors.toList());
        System.out.println("\nAfter Skipping Top 2:");
        skippedFirst2.forEach(System.out::println);

        // 7. count
        long count = persons.stream()
            .filter(p -> p.inBillion >= 100)
            .count();
        System.out.println("\nCount (>=100B): " + count);

        // 8. anyMatch
        boolean hasPoorPerson = persons.stream()
            .anyMatch(p -> p.inBillion < 50);
        System.out.println("\nAny person < 50B? " + hasPoorPerson);

        // 9. allMatch
        boolean allAreBillionaires = persons.stream()
            .allMatch(p -> p.inBillion >= 1);
        System.out.println("All are billionaires? " + allAreBillionaires);

        // 10. noneMatch
        boolean noneIsTrillionaire = persons.stream()
            .noneMatch(p -> p.inBillion > 1000);
        System.out.println("No one is a trillionaire? " + noneIsTrillionaire);

        // 11. findFirst
        Optional<Person> firstPerson = persons.stream().findFirst();
        System.out.println("\nFirst Person: " + firstPerson.orElse(null));

        // 12. findAny
        Optional<Person> anyPerson = persons.stream().findAny();
        System.out.println("Any Person: " + anyPerson.orElse(null));

        // 13. reduce
        double totalWealth = persons.stream()
            .map(p -> p.inBillion)
            .reduce(0.0, Double::sum);
        System.out.println("\nTotal Wealth: $" + totalWealth + " Billion");

        // 14. collect (groupingBy)
        Map<String, List<Person>> groupedByInitial = persons.stream()
            .collect(Collectors.groupingBy(p -> p.name.substring(0, 1)));
        System.out.println("\nGrouped by First Letter of Name:");
        groupedByInitial.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });
    }
}
