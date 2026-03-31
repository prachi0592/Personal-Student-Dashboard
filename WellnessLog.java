import java.util.*;
import java.io.*;

// WellnessLog.java
// Author: Prachi Pande
// Purpose: Allows the user to log daily mood and wellness notes
// Uses file handling to save logs between sessions

public class WellnessLog {
    // File where wellness logs are saved
    private static final String FILE = "wellness.txt";

    // Main menu for the Wellness Log module
    public static void menu(Scanner scanner) {
        int choice = 0;

        while (choice != 3) {
            System.out.println("\n--- Wellness Log ---");
            System.out.println("1. Add a wellness log");
            System.out.println("2. View all logs");
            System.out.println("3. Go back");
            System.out.print("Enter choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addLog(scanner);
                    break;
                case 2:
                    viewLogs();
                    break;
                case 3:
                    System.out.println("Going back...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Add a new wellness log entry to the file
    private static void addLog(Scanner scanner) {
        System.out.print("Enter today's date (e.g. 31 March 2026): ");
        String date = scanner.nextLine();
        System.out.print("How are you feeling today? (Happy/Okay/Stressed/Tired/Sad): ");
        String mood = scanner.nextLine();
        System.out.print("Write a short note about your day: ");
        String note = scanner.nextLine();

        // Save log entry to file
        try (FileWriter fw = new FileWriter(FILE, true)) {
            fw.write(date + "," + mood + "," + note + "\n");
            System.out.println("Log saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving log.");
        }
    }

    // Display all wellness logs saved in the file
    private static void viewLogs() {
        System.out.println("\nYour Wellness Logs:");
        System.out.println("--------------------------------");

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            int count = 1;

            // Read each line and display log details
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                System.out.println(count + ". Date: " + parts[0] +
                        " | Mood: " + parts[1] +
                        " | Note: " + parts[2]);
                count++;
            }
        } catch (IOException e) {
            System.out.println("No logs found.");
        }
    }
}