import java.io.*;
import java.util.Scanner;

public class file {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Text File Editor");
            System.out.println("1. Read File");
            System.out.println("2. Write File");
            System.out.println("3. Modify File");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    readFile(scanner);
                    break;
                case 2:
                    writeFile(scanner);
                    break;
                case 3:
                    modifyFile(scanner);
                    break;
                case 4:
                    System.out.println("Exiting");
                    return;
                default:
                    System.out.println("Invalid");
            }
        }
    }

    private static void readFile(Scanner scanner) {
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void writeFile(Scanner scanner) {
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();
        System.out.print("Enter text to write: ");
        String text = scanner.nextLine();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(text);
            System.out.println("Text written to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void modifyFile(Scanner scanner) {
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();
        System.out.print("Enter text to replace: ");
        String oldText = scanner.nextLine();
        System.out.print("Enter new text: ");
        String newText = scanner.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + ".tmp"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line.replace(oldText, newText));
                writer.newLine();
            }
            reader.close();
            writer.close();
        
            File originalFile = new File(filePath);
            File tempFile = new File(filePath + ".tmp");
            originalFile.delete();
            tempFile.renameTo(originalFile);
            System.out.println(" modified.");
        } catch (IOException e) {
            System.out.println("Error in modifying " + e.getMessage());
        }}}
