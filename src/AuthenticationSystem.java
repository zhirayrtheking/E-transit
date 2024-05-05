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


//    public void run() {
//        Scanner scanner = new Scanner(System.in);
//
//        printAsciiArt("resources/ascii_art.txt");
//
//        // Prompt user for registration or login
//
//        System.out.print("Enter your choice: ");
//        try {
//            int choice = scanner.nextInt();
//            scanner.nextLine(); // Consume newline
//
//            if (choice == 1) {
//                // Login
//                User loggedInUser = loginUser(scanner);
//                if (loggedInUser != null) {
//                    // If login successful, allow user to access account
//                    accessAccount(loggedInUser, scanner);
//                }
//            } else if (choice == 2) {
//                // Register
//                registerUser(scanner);
//            } else {
//                System.out.println("Invalid choice.");
//            }
//        }catch(InputMismatchException e){
//            System.out.println("Invalid input. Please enter a valid number.");
//            scanner.nextLine();
//        }
//    }

    private void accessAccount(User user, Scanner scanner) {
        // Create Account instance for the user (assuming each user has only one account)
        Account userAccount = new Account(user.getUsername());

        // Print border and account management options
        System.out.println("=================================");
        System.out.println("=       Account Management      =");
        System.out.println("=================================");
        System.out.println("= 1. Check Balance              =");
        System.out.println("= 2. Deposit Funds              =");
        System.out.println("= 3. Withdraw Funds             =");
        System.out.println("= 4. View Transaction History   =");
        System.out.println("= 5. Transfer Funds             =");
        System.out.println("= 6. Logout                     =");
        System.out.println("=================================");

        // Process account management options
        while (true) {
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Check Balance
                    System.out.println("Your current balance is: " + userAccount.getBalance());
                    break;
                case 2:
                    // Deposit Funds
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    userAccount.deposit(depositAmount);
                    break;
                case 3:
                    // Withdraw Funds
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    userAccount.withdraw(withdrawAmount);
                    break;
                case 4:
                    // View Transaction History
                    System.out.println("Transaction History:");
                    List<String> history = userAccount.getTransactionHistory();
                    if (!history.isEmpty()) {
                        for (String transaction : history) {
                            System.out.println(transaction);
                        }
                    } else {
                        System.out.println("No transaction history available.");
                    }
                    break;
                case 5:
                    // Transfer Funds
                    System.out.print("Enter recipient's username: ");
                    String recipientUsername = scanner.nextLine();
                    if (users.containsKey(recipientUsername)) {
                        User recipientUser = users.get(recipientUsername);
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        if (userAccount.withdraw(transferAmount)) {
                            recipientUser.getAccount().deposit(transferAmount);
                            System.out.println("Transfer of " + transferAmount + " to " + recipientUsername + " successful.");
                        } else {
                            System.out.println("Transfer failed. Insufficient funds.");
                        }
                    } else {
                        System.out.println("Recipient username not found.");
                    }
                    break;
                case 6:
                    // Logout
                    userAccount.updateAccountFile(); // Update account file before logout
                    System.out.println("Logged out successfully.");
                    return;

                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
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

    boolean loginUser(String username, String password) {

        if (users.containsKey(username)) {
            User user = users.get(username);
            if (user.authenticate(password)) {
                System.out.println("Login successful. Welcome, " + user.getUsername() + "!");
                return true; // Return the logged-in user
            }
        }
        return false; // Return null if login fails
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