import java.util.*;
import java.io.*;

// TaskPlanner.java
// Author: Prachi Pande
// Purpose: Allows the user to add, view and complete study tasks
// Uses file handling to save tasks between sessions

public class TaskPlanner {
    // File where tasks are saved
    private static final String FILE = "tasks.txt";

    // Main menu for the Task Planner module
    public static void menu(Scanner scanner) {
        int choice = 0;

        while (choice != 4) {
            System.out.println("\n--- Task Planner ---");
            System.out.println("1. Add a task");
            System.out.println("2. View all tasks");
            System.out.println("3. Mark task as done");
            System.out.println("4. Go back");
            System.out.print("Enter choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addTask(scanner);
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    markAsDone(scanner);
                    break;
                case 4:
                    System.out.println("Going back...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Add a new task to the file
    private static void addTask(Scanner scanner) {
        System.out.print("Enter task name: ");
        String task = scanner.nextLine();
        System.out.print("Enter priority (High/Medium/Low): ");
        String priority = scanner.nextLine();
        System.out.print("Enter due date (e.g. 01 April 2026): ");
        String dueDate = scanner.nextLine();

        // Save task to file with status as pending
        try (FileWriter fw = new FileWriter(FILE, true)) {
            fw.write(task + "," + priority + "," + dueDate + ",Pending\n");
            System.out.println("Task added successfully!");
        } catch (IOException e) {
            System.out.println("Error saving task.");
        }
    }

    // Display all tasks saved in the file
    private static void viewTasks() {
        System.out.println("\nYour Task List:");
        System.out.println("--------------------------------");

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            int count = 1;

            // Read each line and display task details
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                System.out.println(count + ". " + parts[0] +
                        " | Priority: " + parts[1] +
                        " | Due: " + parts[2] +
                        " | Status: " + parts[3]);
                count++;
            }
        } catch (IOException e) {
            System.out.println("No tasks found.");
        }
    }

    // Mark a task as done
    private static void markAsDone(Scanner scanner) {
        System.out.print("Enter the task name to mark as done: ");
        String taskName = scanner.nextLine();

        // Read all tasks and update the matching one
        List<String> lines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.toLowerCase().contains(taskName.toLowerCase())) {
                    String[] parts = line.split(",");
                    line = parts[0] + "," + parts[1] + "," + parts[2] + ",Done";
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
                System.out.println("Task marked as done!");
            } catch (IOException e) {
                System.out.println("Error updating file.");
            }
        } else {
            System.out.println("Task not found.");
        }
    }
}