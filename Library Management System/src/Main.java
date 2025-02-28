import java.util.*;

// Book class representing a book in the library
class Book {
    private final String title;
    private final String author;
    private final String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true; // Initially, the book is available
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
        }
    }

    public void returnBook() {
        isAvailable = true;
    }

    @Override
    public String toString() {
        return "Book[Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Available: " + isAvailable + "]";
    }
}

// User class representing a library user
class User {
    private final String name;
    private final int userId;
    private final List<Book> borrowedBooks;

    public User(String name, int userId) {
        this.name = name;
        this.userId = userId;
        this.borrowedBooks = new ArrayList<>();
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            book.borrowBook();
            borrowedBooks.add(book);
            System.out.println(name + " borrowed " + book.getTitle());
        } else {
            System.out.println("Sorry, " + book.getTitle() + " is already borrowed.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            book.returnBook();
            borrowedBooks.remove(book);
            System.out.println(name + " returned " + book.getTitle());
        } else {
            System.out.println("This book was not borrowed by " + name);
        }
    }

    public void displayBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println(name + " has not borrowed any books.");
        } else {
            System.out.println(name + "'s Borrowed Books:");
            borrowedBooks.forEach(System.out::println);
        }
    }
}

// Librarian class to manage books
class Librarian {
    private final LibrarySystem library;

    public Librarian(LibrarySystem library) {
        this.library = library;
    }

    public void addBook(String title, String author, String isbn) {
        library.addBook(new Book(title, author, isbn));
    }

    public void displayBooks() {
        library.displayAvailableBooks();
    }
}

// LibrarySystem class to manage the library
class LibrarySystem {
    private final List<Book> bookList;
    private final List<User> userList;

    public LibrarySystem() {
        bookList = new ArrayList<>();
        userList = new ArrayList<>();
    }

    public void addBook(Book book) {
        bookList.add(book);
        System.out.println("Added: " + book.getTitle());
    }

    public void registerUser(User user) {
        userList.add(user);
        // Fixed the issue: Using getName() instead of directly accessing private field 'name'
        System.out.println("User Registered: " + user.getUserId() + " - " + user.getName());
    }

    public void borrowBook(int userId, String isbn) {
        User user = findUser(userId);
        Book book = findBook(isbn);
        if (user != null && book != null) {
            user.borrowBook(book);
        }
    }

    public void returnBook(int userId, String isbn) {
        User user = findUser(userId);
        Book book = findBook(isbn);
        if (user != null && book != null) {
            user.returnBook(book);
        }
    }

    private User findUser(int userId) {
        return userList.stream().filter(user -> user.getUserId() == userId).findFirst().orElse(null);
    }

    private Book findBook(String isbn) {
        return bookList.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst().orElse(null);
    }

    public void displayAvailableBooks() {
        System.out.println("\nAvailable Books:");
        bookList.stream().filter(Book::isAvailable).forEach(System.out::println);
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem();
        Librarian librarian = new Librarian(library);

        // Adding books
        librarian.addBook("The Catcher in the Rye", "J.D. Salinger", "123456");
        librarian.addBook("1984", "George Orwell", "789012");
        librarian.addBook("To Kill a Mockingbird", "Harper Lee", "345678");

        // Registering users
        User user1 = new User("Alice", 1);
        User user2 = new User("Bob", 2);
        library.registerUser(user1);
        library.registerUser(user2);

        // Display available books
        librarian.displayBooks();

        // Borrowing books
        library.borrowBook(1, "123456"); // Alice borrows "The Catcher in the Rye"
        library.borrowBook(2, "789012"); // Bob borrows "1984"
        library.borrowBook(1, "345678"); // Alice borrows "To Kill a Mockingbird"

        // Display books after borrowing
        librarian.displayBooks();

        // Display borrowed books for each user
        user1.displayBorrowedBooks();
        user2.displayBorrowedBooks();

        // Returning books
        library.returnBook(1, "123456"); // Alice returns "The Catcher in the Rye"
        library.returnBook(2, "789012"); // Bob returns "1984"

        // Display books after returning
        librarian.displayBooks();
    }
}
