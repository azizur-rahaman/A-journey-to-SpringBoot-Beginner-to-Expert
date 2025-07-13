// 🔹 1. Generic Class
class Box<T> {
    private T item;

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }
}

// 🔹 2. Generic Method
class ArrayPrinter {
    public static <T> void printArray(T[] array) {
        for (T item : array) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}

// 🔹 3. Bounded Type Example
class MathUtils {
    public static <T extends Number> double square(T number) {
        return number.doubleValue() * number.doubleValue();
    }
}

public class GenericsDemo {

    public static void main(String[] args) {

        // Generic Class Example
        Box<String> stringBox = new Box<>();
        stringBox.setItem("Hello Generics");
        System.out.println("🔹 Generic Class (String): " + stringBox.getItem());

        Box<Integer> intBox = new Box<>();
        intBox.setItem(123);
        System.out.println("🔹 Generic Class (Integer): " + intBox.getItem());

        // Generic Method Example
        System.out.print("\n🔹 Generic Method (Array of Integers): ");
        Integer[] intArray = {1, 2, 3};
        ArrayPrinter.printArray(intArray);

        System.out.print("🔹 Generic Method (Array of Strings): ");
        String[] strArray = {"Java", "C++", "Python"};
        ArrayPrinter.printArray(strArray);

        // Bounded Type Example
        System.out.println("\n🔹 Bounded Type:");
        System.out.println("Square of 5: " + MathUtils.square(5));
        System.out.println("Square of 3.5: " + MathUtils.square(3.5));
    }
}
