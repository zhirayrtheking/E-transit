import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPageUI extends JFrame {
    private JLabel welcomeLabel;
    private JButton loginButton;
    private JButton signupButton;

    ImageIcon logo = new ImageIcon("LOGO.jpg");

    public MainPageUI() {
        setTitle("Main Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setIconImage(logo.getImage());
        setLocationRelativeTo(null); // Center the frame on the screen
        setResizable(false);

        setLayout(new BorderLayout());

        // Welcome Label
        welcomeLabel = new JLabel("Welcome to E-Transit", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Gardona", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        JPanel welcomePanel = new JPanel(new BorderLayout());
        welcomePanel.setBackground(new Color(52, 152, 219)); // Background color
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);

        // Company Logo
        ImageIcon logoIcon = new ImageIcon("LOGO_Main.jpg"); // Replace with your company logo path
        JLabel logoLabel = new JLabel(logoIcon);
        JPanel logoPanel = new JPanel(new BorderLayout());
        logoPanel.setBackground(new Color(41, 128, 185)); // Background color
        logoPanel.add(logoLabel, BorderLayout.CENTER);

        // Buttons Panel
        loginButton = new JButton("Login");
        signupButton = new JButton("Signup");
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonsPanel.setBackground(new Color(52, 73, 94)); // Background color
        buttonsPanel.add(loginButton);
        buttonsPanel.add(signupButton);

        // Add components to the main panel
        add(welcomePanel, BorderLayout.NORTH);
        add(logoPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        // Set button colors and font
        loginButton.setBackground(new Color(46, 204, 113));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Gardona", Font.BOLD, 16));
        loginButton.setFocusPainted(false);
        signupButton.setBackground(new Color(241, 196, 15));
        signupButton.setForeground(Color.WHITE);
        signupButton.setFont(new Font("Gardona", Font.BOLD, 16));
        signupButton.setFocusPainted(false);

        // Button action listeners (replace with your action code)
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginUI();
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SignUpUI();
            }
        });
    }
}