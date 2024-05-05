import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Bank {
    private String name;
    private Map<String, Double> accountBalances; // Map to store account balances (username as key, balance as value)
    private List<Transaction> transactions; // List to store transactions

    // Constructor
    public Bank(String name) {
        this.name = name;
        this.accountBalances = new HashMap<>();
        this.transactions = new ArrayList<>();
    }

    // Method to get the balance of a user's account
    public double getAccountBalance(String username) {
        return accountBalances.getOrDefault(username, 0.0);
    }

    // Method to deposit funds into a user's account
    public void deposit(String username, double amount) {
        double currentBalance = accountBalances.getOrDefault(username, 0.0);
        double newBalance = currentBalance + amount;
        accountBalances.put(username, newBalance);
        System.out.println("Deposit of " + amount + " made to account " + username);
    }

    // Method to withdraw funds from a user's account
    public boolean withdraw(String username, double amount) {
        double currentBalance = accountBalances.getOrDefault(username, 0.0);
        if (currentBalance >= amount) {
            double newBalance = currentBalance - amount;
            accountBalances.put(username, newBalance);
            System.out.println("Withdrawal of " + amount + " made from account " + username);
            return true; // Withdrawal successful
        } else {
            System.out.println("Insufficient funds in account " + username);
            return false; // Insufficient funds
        }
    }

    // Method to process a transaction between two users within the same bank
    public boolean processTransaction(Transaction transaction) {
        User sourceUser = transaction.getSourceUser();
        User destinationUser = transaction.getDestinationUser();
        double amount = transaction.getAmount();

        double sourceBalance = accountBalances.getOrDefault(sourceUser.getUsername(), 0.0);
        double destinationBalance = accountBalances.getOrDefault(destinationUser.getUsername(), 0.0);

        if (sourceBalance >= amount) {
            // Withdraw from source account
            double newSourceBalance = sourceBalance - amount;
            accountBalances.put(sourceUser.getUsername(), newSourceBalance);
            // Deposit into destination account
            double newDestinationBalance = destinationBalance + amount;
            accountBalances.put(destinationUser.getUsername(), newDestinationBalance);
            System.out.println("Transaction of " + amount + " from " + sourceUser.getUsername() + " to " + destinationUser.getUsername() + " processed successfully.");
            transactions.add(transaction); // Add transaction to list
            return true; // Transaction successful
        } else {
            System.out.println("Insufficient funds in account " + sourceUser.getUsername() + " for transaction.");
            return false; // Insufficient funds for transaction
        }
    }

    // Other methods for managing transactions and account balances can be added here
}
