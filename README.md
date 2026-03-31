# Personal Student Dashboard

A Java console application built to help students manage their daily academic and personal life in one place.

## What it does

The Personal Student Dashboard has four modules:

- **Book Tracker** — Add books you are reading, view your list, mark them as read and rate them
- **Task Planner** — Add study tasks with priority and due date, view them, and mark them as done
- **Budget Tracker** — Log daily expenses with categories, view all expenses, and check total amount spent
- **Wellness Log** — Log your daily mood and a short note about your day

All data is saved to local text files so your entries are preserved between sessions.

## How to set it up

Make sure you have Java installed on your system. You can check by running:
```
java -version
```

Clone the repository:
```
git clone https://github.com/prachi0592/personal-student-dashboard.git
```

Navigate into the project folder:
```
cd personal-student-dashboard
```

## How to run it

Compile all the Java files:
```
javac Main.java BookTracker.java TaskPlanner.java BudgetTracker.java WellnessLog.java
```

Run the program:
```
java Main
```

## How to use it

Once the program runs you will see the main menu. Enter a number to go into any module. Each module has its own menu where you can add, view or update entries. Enter the corresponding number to go back to the main menu at any time.

## Dependencies

No external libraries needed. Built using core Java only.

## Author

Prachi Pande
