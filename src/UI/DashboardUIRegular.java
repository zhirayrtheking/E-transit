package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import banksystem.*;

public class DashboardUIRegular extends JFrame {
    private JLabel usernameLabel;
    private JLabel balanceLabel;
    private JButton transferButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JTextArea transactionHistory;
    private JButton backButton;
    ImageIcon logo = new ImageIcon("LOGO.jpg");



    public DashboardUIRegular(User user, Account userAccount) {
        userAccount.updateAccountFile();
        setTitle(user.getUsername() + " System Dashboard");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setBackground(new Color(41, 128, 185));
        setIconImage(logo.getImage());
        setResizable(false);
        // Upper left corner: Username
        JPanel usernamePanel = new JPanel();
        usernamePanel.setBackground(new Color(52, 152, 219));
        usernameLabel = new JLabel("Welcome " + user.getUsername());
        usernameLabel.setFont(new Font("Gardona", Font.BOLD, 20));
        usernameLabel.setForeground(Color.WHITE);
        usernamePanel.add(usernameLabel);
        add(usernamePanel, BorderLayout.NORTH);

        // Center: Balance and buttons
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 1));

        balanceLabel = new JLabel("Balance: $" + userAccount.getBalance());
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(balanceLabel);

        transferButton = new JButton("Transfer");
        transferButton.setBackground(new Color(44, 62, 80)); // Dark blue
        transferButton.setForeground(Color.WHITE);
        transferButton.setFocusPainted(false);
        centerPanel.add(transferButton);

        depositButton = new JButton("Deposit");
        depositButton.setBackground(new Color(39, 174, 96)); // Green
        depositButton.setForeground(Color.WHITE);
        depositButton.setFocusPainted(false);
        centerPanel.add(depositButton);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBackground(new Color(231, 76, 60)); // Red
        withdrawButton.setForeground(Color.WHITE);
        withdrawButton.setFocusPainted(false);
        centerPanel.add(withdrawButton);

        add(centerPanel, BorderLayout.CENTER);



        // Lower left corner: Back button
        JPanel backButtonPanel = new JPanel();
        backButtonPanel.setBackground(new Color(52, 73, 94));
        backButton = new JButton("Exit");
        backButton.setBackground(new Color(192, 57, 43)); // Dark red
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButtonPanel.add(backButton);
        add(backButtonPanel, BorderLayout.SOUTH);

        setVisible(true);



        // Action listeners for buttons
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TransferUI(userAccount);
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DepositUI(userAccount, user);

            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new WithdrawUI(userAccount, user);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainPageUI mainPage = new MainPageUI();
                mainPage.setVisible(true);
            }
        });
    }
}
