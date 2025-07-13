import java.util.*;

// ========== ENUMS ==========
enum UserType {
    SUPER_ADMIN,
    ADMIN,
    USER
}

// ========== BOOK ==========
class Book {
    String bookId;
    String title;
    String author;
    Date publishedDate;
    boolean isAvailable;

    public Book(String title, String author, Date publishedDate) {
        this.bookId = UUID.randomUUID().toString();
        this.title = title;
        this.author = author;
        this.publishedDate = publishedDate;
        this.isAvailable = true;
    }

    public String toString() {
        return "[" + (isAvailable ? "Available" : "Borrowed") + "] " + title + " by " + author;
    }
}

// ========== USER ==========
class User {
    String userId;
    String name;
    String email;
    UserType type;
    String phone;
    String encryptedPassword;

    public User(String name, String email, UserType type, String phone, String password) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.type = type;
        this.phone = phone;
        this.encryptedPassword = EncryptionUtil.encrypt(password, "secret");
    }

    public boolean checkPassword(String inputPassword) {
        return EncryptionUtil.decrypt(this.encryptedPassword, "secret").equals(inputPassword);
    }
}

// ========== BORROW RECORD ==========
class BorrowRecord {
    String recordId;
    Book book;
    User borrower;
    Date borrowDate;
    Date returnDate;

    public BorrowRecord(Book book, User borrower) {
        this.recordId = UUID.randomUUID().toString();
        this.book = book;
        this.borrower = borrower;
        this.borrowDate = new Date();
        this.returnDate = null;
    }

    public void returnBook() {
        this.returnDate = new Date();
        this.book.isAvailable = true;
    }

    public String toString() {
        return borrower.name + " borrowed '" + book.title + "' on " + borrowDate +
                (returnDate == null ? " [Not Returned]" : " | Returned: " + returnDate);
    }
}

// ========== ENCRYPTION ==========
class EncryptionUtil {
    public static String encrypt(String text, String key) {
        StringBuilder encrypted = new StringBuilder();
        key = key.toUpperCase();
        int keyLength = key.length();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            int shift = key.charAt(i % keyLength) % 26;
            if (Character.isUpperCase(ch)) {
                encrypted.append((char)((ch - 'A' + shift) % 26 + 'A'));
            } else if (Character.isLowerCase(ch)) {
                encrypted.append((char)((ch - 'a' + shift) % 26 + 'a'));
            } else encrypted.append(ch);
        }
        return encrypted.toString();
    }

    public static String decrypt(String text, String key) {
        StringBuilder decrypted = new StringBuilder();
        key = key.toUpperCase();
        int keyLength = key.length();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            int shift = key.charAt(i % keyLength) % 26;
            if (Character.isUpperCase(ch)) {
                decrypted.append((char)((ch - 'A' - shift + 26) % 26 + 'A'));
            } else if (Character.isLowerCase(ch)) {
                decrypted.append((char)((ch - 'a' - shift + 26) % 26 + 'a'));
            } else decrypted.append(ch);
        }
        return decrypted.toString();
    }
}

// ========== LIBRARY ==========
class Library {
    List<User> users = new ArrayList<>();
    List<Book> books = new ArrayList<>();
    List<BorrowRecord> borrowRecords = new ArrayList<>();

    public void registerUser(User user) {
        users.add(user);
        System.out.println("User added: " + user.name + " [" + user.type + "]");
    }

    public void addBook(Book book, User addedBy) {
        if (addedBy.type == UserType.SUPER_ADMIN || addedBy.type == UserType.ADMIN) {
            books.add(book);
            System.out.println("Book added: " + book.title);
        } else {
            System.out.println("You don’t have permission to add books.");
        }
    }

    public void viewBooks(User viewer) {
        System.out.println("Books in Library:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void borrowBook(String bookId, User user) {
        for (Book book : books) {
            if (book.bookId.equals(bookId) && book.isAvailable) {
                book.isAvailable = false;
                borrowRecords.add(new BorrowRecord(book, user));
                System.out.println(user.name + " borrowed: " + book.title);
                return;
            }
        }
        System.out.println("Book not available or invalid ID.");
    }

    public void returnBook(String bookId, User user) {
        for (BorrowRecord record : borrowRecords) {
            if (record.book.bookId.equals(bookId) && record.borrower.userId.equals(user.userId) && record.returnDate == null) {
                record.returnBook();
                System.out.println(user.name + " returned: " + record.book.title);
                return;
            }
        }
        System.out.println("No matching borrow record found.");
    }

    public void viewBorrowRecords(User viewer) {
        if (viewer.type == UserType.ADMIN || viewer.type == UserType.SUPER_ADMIN) {
            System.out.println("Borrow Records:");
            for (BorrowRecord record : borrowRecords) {
                System.out.println(record);
            }
        } else {
            System.out.println("You don’t have permission to view borrow records.");
        }
    }
}

// ========== MAIN ==========

public class LibraryManagement {
    static Scanner scanner = new Scanner(System.in);
    static Library library = new Library();
    static User currentUser = null;

    public static void main(String[] args) {
        seedUsersAndBooks();

        while (true) {
            showAuthMenu();
        }
    }

    static void showAuthMenu() {
        System.out.println("\n========== Library System ==========");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Select option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                registerUser();
                break;
            case 2:
                showLogin();
                showMainMenu();
                break;
            case 3:
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    static void registerUser() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.println("Select Role: 1. USER  2. ADMIN");
        int roleChoice = Integer.parseInt(scanner.nextLine());
        UserType type = (roleChoice == 2) ? UserType.ADMIN : UserType.USER;
        User user = new User(name, email, type, phone, password);
        library.registerUser(user);
    }

    static void showLogin() {
        System.out.println("\n========== Login ==========");
        while (true) {
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            for (User user : library.users) {
                if (user.email.equalsIgnoreCase(email) && user.checkPassword(password)) {
                    currentUser = user;
                    System.out.println("Login successful! Welcome, " + currentUser.name + " (" + currentUser.type + ")");
                    return;
                }
            }
            System.out.println("Invalid credentials. Try again.");
        }
    }

    static void showMainMenu() {
        while (true) {
            System.out.println("\n========== Main Menu ==========");
            System.out.println("1. View All Books");
            System.out.println("2. Add Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Borrow Records");
            System.out.println("6. Logout");
            System.out.print("Select option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    library.viewBooks(currentUser);
                    break;
                case 2:
                    if (currentUser.type == UserType.ADMIN || currentUser.type == UserType.SUPER_ADMIN) {
                        System.out.print("Book Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Author: ");
                        String author = scanner.nextLine();
                        library.addBook(new Book(title, author, new Date()), currentUser);
                    } else {
                        System.out.println("You don't have permission to add books.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Book ID to borrow: ");
                    String bookIdBorrow = scanner.nextLine();
                    library.borrowBook(bookIdBorrow, currentUser);
                    break;
                case 4:
                    System.out.print("Enter Book ID to return: ");
                    String bookIdReturn = scanner.nextLine();
                    library.returnBook(bookIdReturn, currentUser);
                    break;
                case 5:
                    library.viewBorrowRecords(currentUser);
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    static void seedUsersAndBooks() {
        User superAdmin = new User("Rafi", "rafi@mail.com", UserType.SUPER_ADMIN, "012345", "admin123");
        User admin = new User("Mitu", "mitu@mail.com", UserType.ADMIN, "012346", "admin456");
        User user = new User("Tuhin", "tuhin@mail.com", UserType.USER, "012347", "user123");

        library.registerUser(superAdmin);
        library.registerUser(admin);
        library.registerUser(user);

        library.addBook(new Book("Java Basics", "James Gosling", new Date()), superAdmin);
        library.addBook(new Book("Spring Boot Guide", "Josh Long", new Date()), admin);
    }
}
