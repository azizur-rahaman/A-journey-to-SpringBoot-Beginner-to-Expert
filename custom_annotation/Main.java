package custom_annotation;

@VeryImportant
public class Main {

    @VeryImportant("Custom message for main method")
    void displayMessage() {
        System.out.println("This is a very important message!");
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.displayMessage();
        
        // Check if the class is annotated with VeryImportant
        if(main.getClass().isAnnotationPresent(VeryImportant.class)){
            System.out.println("The Main class is annotated with @VeryImportant.");
        } else {
            System.out.println("The Main class is not annotated with @VeryImportant.");
        }
    }
}