import java.util.ArrayList;
import java.util.Arrays;

public class Database {
    ArrayList<Customer> customerList = new ArrayList<Customer>();
    ArrayList<Administrator> administratorList = new ArrayList<Administrator>();
    ArrayList<WashCard> washCardList = new ArrayList<WashCard>();

    public void makeTestData(){
        customerList.addAll(Arrays.asList(
                new Customer("Jonas", true, 1),
                new Customer("Jacob", true, 2),
                new Customer("David", false, 3),
                new Customer("Andreas", false, 4)
        ));

        administratorList.addAll(Arrays.asList(
                new Administrator("Hackerman"),
                new Administrator("Neo")
        ));

        for(Customer customer : customerList){
         washCardList.add(new WashCard(customer.getId()));
        }

        getCustomerFromID(1).setBalance(1000.0);
        getCustomerFromID(2).setBalance(500.0);
        getCustomerFromID(3).setBalance(12.0);

    }
    //SETS the balance.
    public void setBalanceOfCustomer(int ID, double newBalance){
        getCustomerFromID(ID).setBalance(newBalance);
    }

    public Customer getCustomerFromID(int id) {
        for (Customer customer : customerList) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        System.out.println("No customer found!");
        return null;
    }

    public ArrayList<Customer> getCustomersByName(String name){
        ArrayList<Customer> result = new ArrayList<>();
        for(Customer customer : customerList){
            if(customer.getName().equals(name)){
                result.add(customer);
            }
        }
        return result;
    }
}
