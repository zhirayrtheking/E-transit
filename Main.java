import javax.swing.*;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Initialize the bank
        Bank bank = new Bank("Cypros bank");

        // Load users from database file
        String databaseFilePath = "database/users.txt";
        Map<String, User> users = User.loadUsers(databaseFilePath, bank);

//        // Create AuthenticationSystem instance and run
//        AuthenticationSystem authSystem = new AuthenticationSystem(users, databaseFilePath, bank);
//        authSystem.run();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainPageUI main = new MainPageUI();
                main.show();
            }
        });
    }
}
