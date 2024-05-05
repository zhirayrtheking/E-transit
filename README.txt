Authors: Zhirayr Hakobyan, Aleksandr Sargsyan, Aram Atoyan 


The project is still incomplete, yet the major functionality of the banking system works (login/registration, transaction from one to another account and etc.), only left to create Capturer and Approver functionality, where each transaction between different users must be first veryfied by two users. Finally our e-transit (swift) system for routing transactions between different banks is still pending and can be finalized in several hours due to already functioning banking system.

    Main.java: Entry point of the application. Initializes the bank, loads users from the database, and starts the authentication system.

    Bank.java: Defines the Bank class responsible for managing accounts, processing transactions, and storing account balances.

    User.java: Represents a user of the banking system. Handles user authentication, transaction initiation, and loading users from a database file.

    AuthenticationSystem.java: Manages user authentication, registration, and account management functionalities like balance inquiry, deposit, withdrawal, and viewing transaction history.

    Transaction.java: Defines the Transaction class to represent transactions between users. Includes functionality for capturing, approving, and denying transactions.

    Account.java: Represents a user's bank account. Manages account balance, transaction history, and file operations for storing account information.

    ETransitSystem.java: Placeholder for future enhancements or additional functionalities.
    
Usage

To run the application:

    Compile all Java files.
    Run the Main class.
    Follow the prompts to log in, register, or manage your account.
    
