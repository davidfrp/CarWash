import java.util.Random;

public class Customer {
    private final String name;
    private final int id;
    private final boolean hasFDMMembership;
    private double balance;

    /**
     * A car wash customer.
     * @param name             the name of the customer.
     * @param hasFDMMembership whether the customer has a FDM membership.
     * @param id               the id of the customer.
     */
    public Customer(String name, boolean hasFDMMembership, int id) {
        this.name = name;
        this.hasFDMMembership = hasFDMMembership;
        this.id = id;
    }

    /**
     * Gets the name of the customer.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the customer's current balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Gets the customer id.
     */
    public int getId() {
        return id;
    }

    public boolean hasFDMMembership() {
        return hasFDMMembership;
    }

    /**
     * Sets the current balance for the customer.
     * @param balance the new balance for the customer.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Charges a certain amount from the customer's current balance.
     * @param amount amount of balance to deduct.
     */
    public void chargeMoney(double amount){
        balance -= amount;
    }
}
