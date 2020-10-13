public class Customer {
    //Fields
    private String name;
    private double balance;
    private int id;
    private boolean hasFDMMembership;

    //Getters
    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }

    public boolean isHasFDMMembership() {
        return hasFDMMembership;
    }

    //Setters
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Customer(String name, double balance, boolean hasFDMMembership) {    //Constructor
        this.name = name;
        this.balance = balance;
        this.hasFDMMembership = hasFDMMembership;
        //TODO : Generate random id
    }

}
