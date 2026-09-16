import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class s {

    static String saveFile = "C:\\JAVA VITyarthi\\studentdata.json";
    static String[] headers = { "id", "name", "age", "grade", "email", "address" };

    public static void main(String[] args) {
        List<Map<String, String>> data = loadData(saveFile);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1) List all");
            System.out.println("2) Add student");
            System.out.println("3) Update student");
            System.out.println("4) Delete student");
            System.out.println("5) Save and exit");
            System.out.println("6) Exit without saving");
            System.out.print("Enter an option: ");

            if (!scanner.hasNextLine()) break;
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                printStudents(data);

            } else if (choice.equals("2")) {
                System.out.print("Enter name: ");
                String name = scanner.nextLine().trim();

                System.out.print("Enter age (optional): ");
                String age = scanner.nextLine().trim();
                if (age.isEmpty()) age = null;

                System.out.print("Enter grade (optional): ");
                String grade = scanner.nextLine().trim();
                if (grade.isEmpty()) grade = null;

                System.out.print("Enter email (optional): ");
                String email = scanner.nextLine().trim();
                if (email.isEmpty()) email = null;

                System.out.print("Enter address (optional): ");
                String address = scanner.nextLine().trim();
                if (address.isEmpty()) address = null;

                data = addStudent(data, name, age, grade, email, address);
                saveData(data, saveFile);
                System.out.println("Student added :)");

            } else if (choice.equals("3")) {
                System.out.print("Enter student ID to update: ");
                String idStr = scanner.nextLine().trim();

                if (!isDigit(idStr)) {
                    System.out.println("Invalid Input");
                    continue;
                }

                int studentId = Integer.parseInt(idStr);
                Map<String, String> fields = new HashMap<>();

                for (String field : new String[] { "name", "age", "grade", "email", "address" }) {
                    System.out.print("Enter new " + field + " (leave blank to skip): ");
                    String val = scanner.nextLine().trim();
                    if (!val.isEmpty()) {
                        fields.put(field, val);
                    }
                }

                try {
                    data = updateStudent(data, studentId, fields);
                    saveData(data, saveFile);
                    System.out.println("Student updated :)");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            } else if (choice.equals("4")) {
                System.out.print("Enter student ID to delete: ");
                String idStr = scanner.nextLine().trim();

                if (!isDigit(idStr)) {
                    System.out.println("Invalid Input");
                    continue;
                }

                int studentId = Integer.parseInt(idStr);

                try {
                    data = deleteStudent(data, studentId);
                    saveData(data, saveFile);
                    System.out.println("Student deleted :)");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            } else if (choice.equals("5")) {
                saveData(data, saveFile);
                System.out.println("Data saved :D");
                break;

            } else if (choice.equals("6")) {
                System.out.println("Exiting without saving :)");
                break;

            } else {
                System.out.println("Invalid option, try again :(");
            }
        }

        scanner.close();
    }

    // checks if a string is a valid positive integer
    static boolean isDigit(String s) {
        if (s == null || s.isEmpty()) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    static List<Map<String, String>> loadData(String path) {
        List<Map<String, String>> data = new ArrayList<>();
        File file = new File(path);

        if (!file.exists()) return data;

        try {
            StringBuilder sb = new StringBuilder();
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                sb.append(fileScanner.nextLine());
            }
            fileScanner.close();

            String json = sb.toString().trim();
            if (json.length() < 2) return data;

            // strip the outer [ ] brackets
            json = json.substring(1, json.length() - 1);
            String[] objects = json.split("},\\s*\\{");

            for (String obj : objects) {
                obj = obj.replace("{", "").replace("}", "").trim();
                if (obj.isEmpty()) continue;

                Map<String, String> row = new HashMap<>();
                // split on commas that aren't inside quotes
                String[] pairs = obj.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
                for (String pair : pairs) {
                    String[] kv = pair.split(":", 2);
                    if (kv.length == 2) {
                        String key = kv[0].replace("\"", "").trim();
                        String value = kv[1].replace("\"", "").trim();
                        if (value.equals("null")) value = null;
                        row.put(key, value);
                    }
                }

                // make sure all expected fields exist
                for (String h : headers) {
                    if (!row.containsKey(h)) {
                        row.put(h, null);
                    }
                }
                data.add(row);
            }
            return data;

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    static int getNextId(List<Map<String, String>> data) {
        if (data.isEmpty()) return 1;
        try {
            int maxId = 0;
            for (Map<String, String> row : data) {
                int id = Integer.parseInt(row.get("id"));
                if (id > maxId) maxId = id;
            }
            return maxId + 1;
        } catch (Exception e) {
            return 1;
        }
    }

    static List<Map<String, String>> addStudent(List<Map<String, String>> data, String name, String age,
            String grade, String email, String address) {
        int newId = getNextId(data);
        Map<String, String> student = new HashMap<>();
        student.put("id", String.valueOf(newId));
        student.put("name", name);
        student.put("age", age);
        student.put("grade", grade);
        student.put("email", email);
        student.put("address", address);
        data.add(student);
        return data;
    }

    static void saveData(List<Map<String, String>> data, String path) {
        try {
            File file = new File(path);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }

            FileWriter fw = new FileWriter(file);
            fw.write("[\n");

            for (int i = 0; i < data.size(); i++) {
                Map<String, String> row = data.get(i);
                fw.write("  {\n");

                int count = 0;
                for (String key : headers) {
                    String val = row.get(key);
                    String valStr;
                    if (key.equals("id")) {
                        valStr = val; // id is a number, no quotes
                    } else {
                        valStr = (val == null) ? "null" : "\"" + val + "\"";
                    }

                    fw.write("    \"" + key + "\": " + valStr);
                    if (count < headers.length - 1) {
                        fw.write(",\n");
                    } else {
                        fw.write("\n");
                    }
                    count++;
                }

                if (i < data.size() - 1) {
                    fw.write("  },\n");
                } else {
                    fw.write("  }\n");
                }
            }

            fw.write("]\n");
            fw.close();
            System.out.println("Saved to " + path);

        } catch (Exception e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    static List<Map<String, String>> updateStudent(List<Map<String, String>> data, int studentId,
            Map<String, String> fields) throws Exception {
        boolean found = false;

        for (Map<String, String> row : data) {
            if (row.get("id") != null && row.get("id").equals(String.valueOf(studentId))) {
                found = true;
                for (Map.Entry<String, String> entry : fields.entrySet()) {
                    row.put(entry.getKey(), entry.getValue());
                }
                break;
            }
        }

        if (!found) {
            throw new Exception("Student id " + studentId + " not found");
        }
        return data;
    }

    static void printStudents(List<Map<String, String>> data) {
        if (data.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        for (String h : headers) {
            System.out.print(h + "\t\t");
        }
        System.out.println();

        for (Map<String, String> row : data) {
            for (String h : headers) {
                System.out.print(row.get(h) + "\t\t");
            }
            System.out.println();
        }
    }

    static List<Map<String, String>> deleteStudent(List<Map<String, String>> data, int studentId)
            throws Exception {
        boolean found = false;

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).get("id") != null && data.get(i).get("id").equals(String.valueOf(studentId))) {
                data.remove(i);
                found = true;
                break;
            }
        }

        if (!found) {
            throw new Exception("Student id " + studentId + " not found");
        }
        return data;
    }
}
