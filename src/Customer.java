import java.util.Random;

public class Customer {
    //Fields
    private final String name;
    private final int id;
    private final boolean hasFDMMembership;
    private double balance;

    //Getters
    public String getName() {
        return name;
    }

    public double getBalance(){
        return balance; }

    public int getId() {
        return id;
    }

    public boolean isHasFDMMembership() {
        return hasFDMMembership;
    }

    //Setters
    //*sets* the balance
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Customer(String name, boolean hasFDMMembership, int id) {    //Constructor
        this.name = name;
        this.hasFDMMembership = hasFDMMembership;
        this.id = id;
    }

    public void chargeMoney(double amount){
        balance -= amount;
    }
}
