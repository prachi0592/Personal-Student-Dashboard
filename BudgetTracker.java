import java.util.*;
import java.io.*;

// BudgetTracker.java
// Author: Prachi Pande
// Purpose: Allows the user to log daily expenses and view total spending
// Uses file handling to save expenses between sessions

public class BudgetTracker {
    // File where expenses are saved
    private static final String FILE = "budget.txt";

    // Main menu for the Budget Tracker module
    public static void menu(Scanner scanner) {
        int choice = 0;

        while (choice != 4) {
            System.out.println("\n--- Budget Tracker ---");
            System.out.println("1. Add an expense");
            System.out.println("2. View all expenses");
            System.out.println("3. View total spent");
            System.out.println("4. Go back");
            System.out.print("Enter choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addExpense(scanner);
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    viewTotal();
                    break;
                case 4:
                    System.out.println("Going back...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Add a new expense to the file
    private static void addExpense(Scanner scanner) {
        System.out.print("Enter expense description: ");
        String description = scanner.nextLine();
        System.out.print("Enter amount spent (in Rs): ");
        String amount = scanner.nextLine();
        System.out.print("Enter category (Food/Transport/Books/Other): ");
        String category = scanner.nextLine();

        // Save expense to file
        try (FileWriter fw = new FileWriter(FILE, true)) {
            fw.write(description + "," + amount + "," + category + "\n");
            System.out.println("Expense added successfully!");
        } catch (IOException e) {
            System.out.println("Error saving expense.");
        }
    }

    // Display all expenses saved in the file
    private static void viewExpenses() {
        System.out.println("\nYour Expense List:");
        System.out.println("--------------------------------");

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            int count = 1;

            // Read each line and display expense details
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                System.out.println(count + ". " + parts[0] +
                        " | Amount: Rs " + parts[1] +
                        " | Category: " + parts[2]);
                count++;
            }
        } catch (IOException e) {
            System.out.println("No expenses found.");
        }
    }

    // Calculate and display total amount spent
    private static void viewTotal() {
        double total = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;

            // Read each line and add up the amounts
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                total += Double.parseDouble(parts[1]);
            }
            System.out.println("--------------------------------");
            System.out.println("Total spent: Rs " + total);
            System.out.println("--------------------------------");
        } catch (IOException e) {
            System.out.println("No expenses found.");
        }
    }
}
