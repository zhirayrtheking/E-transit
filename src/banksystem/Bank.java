package banksystem;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private String name;
    private Map<String, Double> accountBalances; // Map to store account balances (username as key, balance as value)


    // Constructor
    public Bank(String name) {
        this.name = name;
        this.accountBalances = new HashMap<>();
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

    public String getName() {
        return name;
    }


}
