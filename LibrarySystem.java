import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Library Management System
 * This program allows users to add, borrow, and return books in a library system.
 * 
 * @author Student
 * @version 1.0
 */
public class LibrarySystem {
    
    // HashMap to store book records: Key = book title, Value = Book object
    private static Map<String, Book> library = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Book class to represent a book with title, author, and quantity
     */
    static class Book {
        private String title;
        private String author;
        private int quantity;
        
        public Book(String title, String author, int quantity) {
            this.title = title;
            this.author = author;
            this.quantity = quantity;
        }
        
        public String getTitle() {
            return title;
        }
        
        public String getAuthor() {
            return author;
        }
        
        public int getQuantity() {
            return quantity;
        }
        
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
        
        @Override
        public String toString() {
            return "Title: " + title + ", Author: " + author + ", Quantity: " + quantity;
        }
    }
    
    /**
     * Main method - entry point of the program
     */
    public static void main(String[] args) {
        System.out.println("=== WELCOME TO THE LIBRARY MANAGEMENT SYSTEM ===\n");
        
        boolean exit = false;
        
        while (!exit) {
            displayMenu();
            int choice = getMenuChoice();
            
            switch (choice) {
                case 1:
                    addBooks();
                    break;
                case 2:
                    borrowBooks();
                    break;
                case 3:
                    returnBooks();
                    break;
                case 4:
                    System.out.println("\nThank you for using the Library Management System. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("\nInvalid option. Please select a number between 1 and 4.");
            }
        }
        
        scanner.close();
    }
    
    /**
     * Displays the main menu options
     */
    private static void displayMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Add Books");
        System.out.println("2. Borrow Books");
        System.out.println("3. Return Books");
        System.out.println("4. Exit");
        System.out.print("\nEnter your choice (1-4): ");
    }
    
    /**
     * Gets and validates the user's menu choice
     * @return validated integer choice
     */
    private static int getMenuChoice() {
        int choice = 0;
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 4) {
                    break;
                } else {
                    System.out.print("Invalid choice. Please enter a number between 1 and 4: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number (1-4): ");
            }
        }
        return choice;
    }
    
    /**
     * Handles adding books to the library
     */
    private static void addBooks() {
        System.out.println("\n--- ADD BOOKS ---");
        
        try {
            System.out.print("Enter book title: ");
            String title = scanner.nextLine().trim();
            
            if (title.isEmpty()) {
                System.out.println("Error: Book title cannot be empty.");
                return;
            }
            
            System.out.print("Enter author name: ");
            String author = scanner.nextLine().trim();
            
            if (author.isEmpty()) {
                System.out.println("Error: Author name cannot be empty.");
                return;
            }
            
            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine().trim());
            
            if (quantity <= 0) {
                System.out.println("Error: Quantity must be a positive number.");
                return;
            }
            
            // Check if book already exists
            if (library.containsKey(title.toLowerCase())) {
                Book existingBook = library.get(title.toLowerCase());
                int newQuantity = existingBook.getQuantity() + quantity;
                existingBook.setQuantity(newQuantity);
                System.out.println("Book updated successfully! New quantity: " + newQuantity);
            } else {
                Book newBook = new Book(title, author, quantity);
                library.put(title.toLowerCase(), newBook);
                System.out.println("Book added successfully! " + newBook);
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid quantity format. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
    
    /**
     * Handles borrowing books from the library
     */
    private static void borrowBooks() {
        System.out.println("\n--- BORROW BOOKS ---");
        
        try {
            System.out.print("Enter book title: ");
            String title = scanner.nextLine().trim();
            
            if (title.isEmpty()) {
                System.out.println("Error: Book title cannot be empty.");
                return;
            }
            
            // Check if book exists
            String key = title.toLowerCase();
            if (!library.containsKey(key)) {
                System.out.println("Error: Book \"" + title + "\" not found in the library.");
                return;
            }
            
            System.out.print("Enter number of books to borrow: ");
            int borrowCount = Integer.parseInt(scanner.nextLine().trim());
            
            if (borrowCount <= 0) {
                System.out.println("Error: Borrow count must be a positive number.");
                return;
            }
            
            Book book = library.get(key);
            int currentQuantity = book.getQuantity();
            
            // Check availability
            if (borrowCount > currentQuantity) {
                System.out.println("Error: Not enough copies available. Only " + currentQuantity + " copy(ies) available.");
            } else {
                int newQuantity = currentQuantity - borrowCount;
                book.setQuantity(newQuantity);
                System.out.println("Successfully borrowed " + borrowCount + " copy(ies) of \"" + title + "\".");
                System.out.println("Remaining quantity: " + newQuantity);
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
    
    /**
     * Handles returning books to the library
     */
    private static void returnBooks() {
        System.out.println("\n--- RETURN BOOKS ---");
        
        try {
            System.out.print("Enter book title: ");
            String title = scanner.nextLine().trim();
            
            if (title.isEmpty()) {
                System.out.println("Error: Book title cannot be empty.");
                return;
            }
            
            // Check if book exists in the library
            String key = title.toLowerCase();
            if (!library.containsKey(key)) {
                System.out.println("Error: Book \"" + title + "\" does not belong to this library system.");
                return;
            }
            
            System.out.print("Enter number of books to return: ");
            int returnCount = Integer.parseInt(scanner.nextLine().trim());
            
            if (returnCount <= 0) {
                System.out.println("Error: Return count must be a positive number.");
                return;
            }
            
            Book book = library.get(key);
            int newQuantity = book.getQuantity() + returnCount;
            book.setQuantity(newQuantity);
            
            System. out.println("Successfully returned " + returnCount + " copy(ies) of \"" + title + "\".");
            System. out.println("New total quantity: " + newQuantity);
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
