import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class User {
    private final String username;
    private final String password;
    private final String role;
    private final Bank bank;
    private final List<Transaction> transactions;

    public User(String username, String password, String role, Bank bank) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.bank = bank;
        this.transactions = new ArrayList<>();
    }

    public void initiateTransaction(User recipient, double amount) {
        Transaction transaction = new Transaction(this, recipient, amount);
        transactions.add(transaction); // Add the transaction to the user's transaction history
        bank.processTransaction(transaction); // Process the transaction through the bank
    }

    public static Map<String, User> loadUsers(String filePath, Bank bank) {
        Map<String, User> users = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String username = parts[0];
                String password = parts[1];
                String role = parts[2];
                users.put(username, new User(username, password, role, bank));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public String getUsername() {
        return username;
    }

    public Account getAccount() {
        // Assuming each user has only one account
        return new Account(username);
    }

    public boolean authenticate(String enteredPassword) {
        return password.equals(enteredPassword);
    }

    public String getRole() {
        return role;
    }

    public Bank getBank() {
        return bank;
    }

    public boolean hasRole(String roleName) {
        return role.equals(roleName);
    }
}
