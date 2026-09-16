# Student Record Management System

## Project Overview

A simple console-based student record management system built in Java. It lets you add, view, update, and delete student records through an interactive command-line menu. All data is stored in a local JSON file (`studentdata.json`), so records persist between sessions.

The entire application is contained in a single Java file with no external dependencies — it handles JSON reading and writing manually using standard Java I/O.

## Features

- **Add Student** — Add a new student with a name (required) and optional fields: age, grade, email, and address. Each student is automatically assigned a unique ID.
- **List All Students** — View all stored student records in a tab-separated table format.
- **Update Student** — Update any combination of fields for an existing student by their ID. Fields left blank are not changed.
- **Delete Student** — Remove a student record by ID.
- **Save & Exit** — Save current data to the JSON file and exit.
- **Exit Without Saving** — Quit the program without writing changes to disk.
- **Persistent Storage** — Student data is saved to and loaded from `studentdata.json` as a JSON array.
- **Auto-Incrementing IDs** — New student IDs are generated based on the highest existing ID.

## Technologies / Tools Used

- **Language:** Java (standard JDK, no specific version requirement beyond Java 8+)
- **Storage:** JSON flat file (`studentdata.json`) — parsed and written manually without any JSON library
- **Build:** No build tool (Maven, Gradle, etc.) — compiled directly with `javac`

## Installation & Running

### Prerequisites

- Java Development Kit (JDK) 8 or higher installed
- `javac` and `java` available on your system PATH

### Steps

1. **Clone or download** the project folder.

2. **Navigate** to the project directory:
   ```
   cd "JAVA VITyarthi"
   ```

3. **Compile** the source file:
   ```
   javac "Student Record.java"
   ```

4. **Run** the application:
   ```
   java s
   ```

5. The interactive menu will appear in the terminal:
   ```
   --- Student Management ---
   1) List all
   2) Add student
   3) Update student
   4) Delete student
   5) Save and exit
   6) Exit without saving
   Enter an option:
   ```

### Important Notes

- The data file path is **hardcoded** to `C:\JAVA VITyarthi\studentdata.json`. If your project is located in a different directory, you will need to update the `saveFile` variable in the source code.
- The main class is named `s`. The compiled file will be `s.class`, so the run command is `java s`, not `java "Student Record"`.

## Testing

There are no automated tests in this project. Testing is done manually through the CLI.

### Manual Testing Steps

1. **Run the application** using the instructions above.
2. **Add a student** (option 2) — enter a name and optionally fill in age, grade, email, and address.
3. **List all students** (option 1) — verify the new student appears with an auto-assigned ID.
4. **Update a student** (option 3) — enter an existing student ID and modify one or more fields. Confirm changes with option 1.
5. **Delete a student** (option 4) — enter a student ID and confirm removal with option 1.
6. **Save and exit** (option 5) — then re-run the program and list students to verify data persisted.
7. **Exit without saving** (option 6) — re-run and verify that unsaved changes were discarded.
8. **Test invalid input** — try entering non-numeric values when an ID is expected, and try updating/deleting a student ID that doesn't exist.

### Key Things to Verify

| Action | Expected Result |
|---|---|
| Add student with only a name | Student is added; optional fields show as `null` |
| Update with some blank fields | Only the filled-in fields are changed |
| Delete a non-existent ID | Error message: "Student id X not found" |
| Enter a non-numeric ID | Error message: "Invalid Input" |
| Save and re-launch | Data persists across sessions |
| Exit without saving | Changes are discarded |
