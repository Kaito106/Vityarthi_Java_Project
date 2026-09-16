# Problem Statement

Students and academic records need to be tracked and managed in an organized way. Manually maintaining student information (names, ages, grades, contact details) becomes error-prone and hard to manage as the number of records grows. There is a need for a simple tool that allows a user to create, read, update, and delete student records with data that persists between sessions — without requiring a database setup or complex software.

This project addresses that by providing a lightweight, console-based Java application that manages student records through an interactive menu and stores the data in a local JSON file.

# Scope of the Project

## What the project covers

- A fully functional CRUD (Create, Read, Update, Delete) system for student records
- An interactive terminal-based menu interface for performing all operations
- Local data persistence using a JSON file — no database or server required
- Support for storing: student ID (auto-generated), name, age, grade, email, and address
- Input handling with basic validation (e.g., checking that IDs are numeric)

## What users can do

- Add new students with required and optional fields
- View all student records in a formatted table
- Update specific fields of an existing student without affecting other fields
- Delete a student by their ID
- Save changes to disk or exit without saving

## Limitations

- **Single-user only** — the application runs locally in one terminal session and does not support concurrent access.
- **No search or filter** — users cannot search for a specific student by name or other fields; they can only list all records.
- **Hardcoded file path** — the data file path is fixed in the source code and must be manually changed if the project is moved.
- **No input validation beyond ID checks** — the application does not validate email format, age ranges, or other field-specific constraints.
- **No GUI** — the entire interaction happens through the command line.
- **Manual JSON handling** — JSON is parsed and written without a library, which may not handle edge cases (e.g., special characters in field values).

# Target Users

- **Students** learning Java who want a reference project demonstrating file I/O, data structures, and console-based interaction.
- **Instructors or TAs** evaluating a student project for a Java programming course.
- **Anyone** needing a quick, no-setup tool to manage a small list of student records locally.

# High-Level Features

1. **Student Record Management** — Add, view, update, and delete student records from the terminal.
2. **Persistent Storage** — Data is saved to a JSON file and loaded automatically on startup, so records survive between sessions.
3. **Interactive Menu** — A numbered menu guides the user through all available operations.
4. **Selective Updates** — When updating a student, only the fields you fill in are changed; the rest remain untouched.
5. **Auto-Generated IDs** — Each new student gets a unique ID without any manual input.
6. **No External Dependencies** — Runs with just a standard Java installation; no libraries, frameworks, or database setup needed.
