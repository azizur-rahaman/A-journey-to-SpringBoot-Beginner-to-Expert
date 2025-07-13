import java.util.*;

public class CollectionsDemo {

    public static void main(String[] args) {

        //  1. List Example (ordered, allows duplicates)
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Alice"); // duplicate allowed

        System.out.println("List (allows duplicates):");
        names.forEach(System.out::println);

        //  2. Set Example (unordered, no duplicates)
        Set<String> items = new HashSet<>();
        items.add("Apple");
        items.add("Banana");
        items.add("Apple"); // duplicate ignored

        System.out.println("\nSet (removes duplicates):");
        items.forEach(System.out::println);

        //  3. Map Example (key-value pairs, keys must be unique)
        Map<String, Integer> marks = new HashMap<>();
        marks.put("Alice", 90);
        marks.put("Bob", 85);
        marks.put("Alice", 95); // replaces old value

        System.out.println("\nMap (key-value):");
        marks.forEach((k, v) -> System.out.println(k + ": " + v));

        //  4. Queue Example (FIFO)
        Queue<String> tasks = new LinkedList<>();
        tasks.add("Task 1");
        tasks.add("Task 2");

        System.out.println("\nQueue (FIFO):");
        while (!tasks.isEmpty()) {
            System.out.println(tasks.poll()); // removes head
        }

        //  5. Stack Example (LIFO)
        Stack<String> pages = new Stack<>();
        pages.push("Home");
        pages.push("About");
        pages.push("Contact");

        System.out.println("\nStack (LIFO):");
        while (!pages.isEmpty()) {
            System.out.println(pages.pop()); // removes top
        }
    }
}
