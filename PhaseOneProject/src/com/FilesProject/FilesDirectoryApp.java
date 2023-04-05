package com.FilesProject;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FilesDirectoryApp {
    private static final String DEVELOPER_NAME = "Venkat Balaga";
    private static final String APPLICATION_NAME = "Files Directory Application(Console based)";
    private static final String WELCOME_MESSAGE = "Welcome to the " + APPLICATION_NAME + " developed by " + DEVELOPER_NAME + "!";

    public static void main(String[] args) {
        System.out.println(WELCOME_MESSAGE);

        while (true) {
            displayMenu();
            int option = getUserOption();

            switch (option) {
                case 1:
                    displayFileNames();
                    break;
                case 2:
                    displayDirectoryOptions();
                    break;
                case 3:
                    System.out.println("Closing " + APPLICATION_NAME + "...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nPlease select an option:");
        System.out.println("1. Display file names");
        System.out.println("2. Directory options");
        System.out.println("3. Close " + APPLICATION_NAME);
    }

    private static int getUserOption() {
        Scanner scanner = new Scanner(System.in); {
			System.out.print("\nEnter your option: ");
			return scanner.nextInt();
		}
    }

    private static void displayFileNames() {
        File root = new File(".");
        File[] files = root.listFiles();

        List<String> fileNames = new ArrayList<>();

        for (File file : files) {
            if (file.isFile()) {
                fileNames.add(file.getName());
            }
        }

        Collections.sort(fileNames);

        System.out.println("\nFile names in ascending order:");
        for (String fileName : fileNames) {
            System.out.println(fileName);
        }
    }

    private static void displayDirectoryOptions() {
        while (true) {
            displayDirectoryMenu();
            int option = getUserOption();

            switch (option) {
                case 1:
                    addFile();
                    break;
                case 2:
                    deleteFile();
                    break;
                case 3:
                    searchFile();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void displayDirectoryMenu() {
        System.out.println("\nDirectory options:");
        System.out.println("1. Add a file");
        System.out.println("2. Delete a file");
        System.out.println("3. Search for a file");
        System.out.println("4. Back to main menu");
    }

    private static void addFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the file name to add: ");
        String fileName = scanner.nextLine();

        File file = new File(fileName);
        if (file.exists()) {
            System.out.println("File already exists.");
            return;
        }

        try {
            boolean created = file.createNewFile();
            if (created) {
                System.out.println("File created successfully.");
            } else {
                System.out.println("File creation failed.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while creating the file: " + e.getMessage());
        }
    }

    private static void deleteFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the file name to delete: ");
        String fileName = scanner.nextLine();

        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("File not found.");
            return;
        }

        boolean deleted = file.delete();
        if (deleted) {
            System.out.println("File deleted successfully.");
        } else {
            System.out.println("File deletion failed.");
        }
    }

    private static void searchFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the file name to search: ");
        String fileName = scanner.nextLine();

        File root = new File(".");
        File[] files = root.listFiles();

        boolean found = false;

        for (File file : files) {
            if (file.isFile() && file.getName().equalsIgnoreCase(fileName)) {
                System.out.println("File found: " + file.getAbsolutePath());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("File not found.");
        }
    }
}

