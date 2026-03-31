import java.util.*;
import java.io.*;

// BookTracker.java
// Author: Prachi Pande
// Purpose: Allows the user to add, view and rate books they are reading
// Uses file handling to save books between sessions

public class BookTracker {
    // File where books are saved
    private static final String FILE = "books.txt";

    // Main menu for the Book Tracker module
    public static void menu(Scanner scanner) {
        int choice = 0;

        while (choice != 4) {
            System.out.println("\n--- Book Tracker ---");
            System.out.println("1. Add a book");
            System.out.println("2. View all books");
            System.out.println("3. Mark book as read");
            System.out.println("4. Go back");
            System.out.print("Enter choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    markAsRead(scanner);
                    break;
                case 4:
                    System.out.println("Going back...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Add a new book to the file
    private static void addBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();

        // Save book to file with status as unread
        try (FileWriter fw = new FileWriter(FILE, true)) {
            fw.write(title + "," + author + ",Unread,Not rated\n");
            System.out.println("Book added successfully!");
        } catch (IOException e) {
            System.out.println("Error saving book.");
        }
    }

    // Display all books saved in the file
    private static void viewBooks() {
        System.out.println("\nYour Book List:");
        System.out.println("--------------------------------");

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            int count = 1;

            // Read each line and display book details
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                System.out.println(count + ". " + parts[0] + " by " + parts[1] +
                        " | Status: " + parts[2] + " | Rating: " + parts[3]);
                count++;
            }
        } catch (IOException e) {
            System.out.println("No books found.");
        }
    }

    // Mark a book as read and add a rating
    private static void markAsRead(Scanner scanner) {
        System.out.print("Enter the title of the book to mark as read: ");
        String title = scanner.nextLine();
        System.out.print("Enter your rating (1-5): ");
        String rating = scanner.nextLine();

        // Read all books and update the matching one
        List<String> lines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.toLowerCase().contains(title.toLowerCase())) {
                    String[] parts = line.split(",");
                    line = parts[0] + "," + parts[1] + ",Read," + rating + "/5";
                    found = true;
                }
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        // Write updated list back to file
        if (found) {
            try (FileWriter fw = new FileWriter(FILE, false)) {
                for (String l : lines) {
                    fw.write(l + "\n");
                }
                System.out.println("Book marked as read!");
            } catch (IOException e) {
                System.out.println("Error updating file.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }
}