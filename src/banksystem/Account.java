package banksystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String accountNumber;
    private double balance;
    private final List<String> transactionHistory;
    private final String accountFilePath;
    private Bank bank;

    public Account(String accountNumber, Bank bank) {
        this.accountNumber = accountNumber;
        this.transactionHistory = new ArrayList<>();
        this.accountFilePath = accountNumber + ".txt"; // Update account file path
        createAccountFile(); // Ensure account file is created when account is instantiated
        this.balance = readBalanceFromFile();
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }

    private double readBalanceFromFile() {
        try {
            if (accountFilePath != null) { // Check if accountFilePath is not null
                Path path = Paths.get(accountFilePath);
                if (Files.exists(path)) {
                    List<String> lines = Files.readAllLines(path);
                    for (int i = lines.size() - 1; i >= 0; i--) {
                        String line = lines.get(i);
                        if (line.startsWith("Balance:")) {
                            return Double.parseDouble(line.substring(8));
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading account file: " + e.getMessage());
        }
        return 0.0; // Return 0.0 if balance not found or error occurs
    }

    private void createAccountFile() {
        try {
            Path path = Paths.get(accountFilePath);
            if (!Files.exists(path)) {
                Files.createFile(path);
                System.out.println("Account file created successfully.");
            }
        } catch (IOException e) {
            System.out.println("Error creating account file: " + e.getMessage());
        }
    }

    public void updateAccountFile() {
        // Write updated balance and new transactions to the account file
        try (PrintWriter writer = new PrintWriter(accountFilePath)) {
            // Write new transactions
            for (String transaction : transactionHistory) {
                writer.println(transaction);
            }

            // Update balance (overwrite existing balance)
            writer.println("Balance: " + balance);


        } catch (IOException e) {
            System.out.println("Error updating account file: " + e.getMessage());
        }
    }

    public double getBalance() {
        return balance;
    }

    private List<String> readTransactionHistoryFromFile() {
        List<String> history = new ArrayList<>();
        try {
            Path path = Paths.get(accountFilePath);
            if (Files.exists(path)) {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    if (!line.startsWith("Balance:")) {
                        history.add(line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading account file: " + e.getMessage());
        }
        return history;
    }

    public List<String> getTransactionHistory() {
        return readTransactionHistoryFromFile();
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit: +" + amount);
        updateAccountFile(); // Update account file immediately after deposit

    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add("Withdrawal: -" + amount);
            updateAccountFile(); // Update account file immediately after withdrawal

            return true; // Withdrawal successful
        } else {
            return false; // Insufficient funds
        }
    }

    public boolean transferTo(Account source, Account destination, double amountToTransfer){
        String sourceBankName = source.getBank().getName();
        String destinationBankName = destination.getBank().getName();

        double sourceBalance = source.getBalance();
        double destinationBalance = destination.getBalance();
        double commission = 0.05;

        if (sourceBankName.equals(destinationBankName)){
            destinationBalance += amountToTransfer;
            return true;
        }else if (!(sourceBankName.equals(destinationBankName))){
            destinationBalance += amountToTransfer * commission;
            return true;
        }
        return false;
    }
}