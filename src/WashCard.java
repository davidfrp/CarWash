public class WashCard {
    private double balance;
    private String name;

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public WashCard(double balance, String name) {
        this.balance = balance;
        this.name = name;
    }
}
