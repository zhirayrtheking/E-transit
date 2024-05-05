import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class LoginUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    ImageIcon logo = new ImageIcon("LOGO.jpg");

    public LoginUI() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 250);
        setResizable(false);
        Color backgroundColor = new Color(52, 152, 219);
        Color buttonColor = new Color(46, 204, 113);
        Color textColor = Color.WHITE;

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(backgroundColor); // Set background color
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(textColor); // Set text color
        usernameField = new JTextField(20);
        usernameField.setBorder(new EmptyBorder(0,0,0,0));
        panel.add(usernameLabel, gbc);
        gbc.gridx++;
        panel.add(usernameField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(textColor); // Set text color
        passwordField = new JPasswordField(20);
        passwordField.setBorder(new EmptyBorder(0,0,0,0));
        panel.add(passwordLabel, gbc);
        gbc.gridx++;
        panel.add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(buttonColor); // Set background color
        loginButton.setForeground(textColor); // Set text color
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bank bank = new Bank("Test");
                String databaseFilePath = "database/users.txt";
                Map<String, User> users = User.loadUsers(databaseFilePath, bank);
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                AuthenticationSystem authenticationSystem = new AuthenticationSystem(users, databaseFilePath, bank);
                if (!(authenticationSystem.loginUser(username, password))){
                    JOptionPane.showMessageDialog(null, "Incorrect username or password!",
                            "E-Transit", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        add(panel);
        setLocationRelativeTo(null);
        setIconImage(logo.getImage());
        setVisible(true);
    }


}
