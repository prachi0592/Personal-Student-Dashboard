import java.util.Scanner;

// Main.java
// Author: Prachi Pande
// Purpose: Entry point for the Personal Student Dashboard
// This class displays the main menu and routes the user to each module

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        System.out.println("================================");
        System.out.println("   Personal Student Dashboard");
        System.out.println("================================");

        // Keep showing the menu until the user chooses to exit
        while (choice != 5) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Book Tracker");
            System.out.println("2. Task Planner");
            System.out.println("3. Budget Tracker");
            System.out.println("4. Wellness Log");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Route to the correct module based on user choice
            switch (choice) {
                case 1:
                    BookTracker.menu(scanner);
                    break;
                case 2:
                    TaskPlanner.menu(scanner);
                    break;
                case 3:
                    BudgetTracker.menu(scanner);
                    break;
                case 4:
                    WellnessLog.menu(scanner);
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
        scanner.close();
    }
}