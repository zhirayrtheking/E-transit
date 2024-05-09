package banksystem;
import java.io.*;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.List;

public class AuthenticationSystem {
    private final Map<String, User> users;
    private final String databaseFilePath;
    private final Bank bank;

    public AuthenticationSystem(Map<String, User> users, String databaseFilePath, Bank bank) {
        this.users = users;
        this.databaseFilePath = databaseFilePath;
        this.bank = bank;
    }


    private void printAsciiArt(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading ASCII art file: " + e.getMessage());
        }
    }

    public boolean loginUser(String username, String password) {

        if (users.containsKey(username)) {
            User user = users.get(username);
            if (user.authenticate(password)) {
                return true;
            }
        }
        return false; // Return false if login fails
    }

    public void registerUser(String firstName, String lastName, String password) {

        String username = firstName + "_" + lastName;

        users.put(username, new User(username, password, "RegularUser", bank));

        // Update users.txt file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(databaseFilePath, true))) {
            writer.write(username + ":" + password + ":RegularUser\n");

        } catch (IOException e) {
            System.out.println("Error occurred while updating database file.");
            e.printStackTrace();
        }
    }

}