public class Transaction {
    private final User sourceUser;
    private final User destinationUser;
    private final double amount;
    private TransactionStatus status;

    public Transaction(User sourceUser, User destinationUser, double amount) {
        this.sourceUser = sourceUser;
        this.destinationUser = destinationUser;
        this.amount = amount;
        this.status = TransactionStatus.PENDING;
    }

    // Enum to represent transaction status
    public enum TransactionStatus {
        PENDING,
        APPROVED,
        DENIED
    }

    // Getter methods for transaction properties
    public User getSourceUser() {
        return sourceUser;
    }

    public User getDestinationUser() {
        return destinationUser;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    // Method to approve the transaction
    public void approve() {
        this.status = TransactionStatus.APPROVED;
    }

    // Method to deny the transaction
    public void deny() {
        this.status = TransactionStatus.DENIED;
    }
}