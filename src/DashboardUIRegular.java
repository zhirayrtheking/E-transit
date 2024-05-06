import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class DashboardUIRegular extends JFrame {
    private JLabel usernameLabel;
    private JLabel balanceLabel;
    private JButton transferButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JTextArea transactionHistory;
    private JButton backButton;

    public DashboardUIRegular(User user, Account userAccount) {
        setTitle(user.getUsername() + " System Dashboard");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        // Upper left corner: Username
        JPanel usernamePanel = new JPanel();
        usernamePanel.setBackground(new Color(230, 230, 250));
        usernameLabel = new JLabel("Welcome " + user.getUsername());
        usernamePanel.add(usernameLabel);
        add(usernamePanel, BorderLayout.NORTH);

        // Center: Balance and buttons
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 1));

        balanceLabel = new JLabel("Balance: $" +  userAccount.getBalance());
        centerPanel.add(balanceLabel);

        transferButton = new JButton("Transfer");
        centerPanel.add(transferButton);

        depositButton = new JButton("Deposit");
        centerPanel.add(depositButton);

        withdrawButton = new JButton("Withdraw");
        centerPanel.add(withdrawButton);

        add(centerPanel, BorderLayout.CENTER);

        // Lower right corner: Transaction history
        JPanel transactionPanel = new JPanel();
        transactionPanel.setLayout(new BorderLayout());

        JLabel historyLabel = new JLabel("Transaction History:");
        transactionPanel.add(historyLabel, BorderLayout.NORTH);

        transactionHistory = new JTextArea(10, 20);
        transactionHistory.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(transactionHistory);
        transactionPanel.add(scrollPane, BorderLayout.CENTER);

        add(transactionPanel, BorderLayout.EAST);

        // Lower left corner: Back button
        JPanel backButtonPanel = new JPanel();
        backButtonPanel.setBackground(new Color(240, 248, 255));
        backButton = new JButton("Back");
        backButtonPanel.add(backButton);
        add(backButtonPanel, BorderLayout.SOUTH);

        setVisible(true);

        // Action listeners for buttons
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle transfer button action
                // Example: Open transfer window/dialog
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle deposit button action
                // Example: Open deposit window/dialog
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle withdraw button action
                // Example: Open withdraw window/dialog
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle back button action
                // Example: Close current window and return to previous window
            }
        });
    }
}