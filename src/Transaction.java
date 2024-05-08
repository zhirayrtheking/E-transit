public class Transaction {
    private final User sourceUser;
    private final User destinationUser;
    private final double amount;


    public Transaction(User sourceUser, User destinationUser, double amount) {
        this.sourceUser = sourceUser;
        this.destinationUser = destinationUser;
        this.amount = amount;
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

    public boolean transferTo(User source, User destination){
        Account sourceAcc = new Account(source.getUsername());
        Account destinationAcc = new Account(destination.getUsername());

        double commission = 0.05;
        double sourceBalance = sourceAcc.getBalance();
        double destinationBalance = destinationAcc.getBalance();

        if (source.getBank().getName() != destination.getBank().getName()){
            destinationBalance += sourceBalance * commission;
            return true;
        } else if (source.getBank().getName().equals(destination.getBank().getName())) {
            destinationBalance += sourceBalance;
            return true;
        }
        return false;
    }

}