import java.util.ArrayList;
import java.util.Arrays;

public class Database {
    int currentID = 0;
    ArrayList<Customer> customerList = new ArrayList<Customer>();
    ArrayList<Admin> adminList = new ArrayList<Admin>();
    ArrayList<WashCard> washCardList = new ArrayList<WashCard>();

    public void makeTestData(){
        customerList.addAll(Arrays.asList(
                new Customer("Jonas", true, setUniqueID()),
                new Customer("Jacob", false, setUniqueID()),
                new Customer("David", false, setUniqueID())

        ));

        adminList.addAll(Arrays.asList(
                new Admin("Hackerman")
        ));

        for(Customer customer : customerList){
         washCardList.add(new WashCard(customer.getId()));
        }

        getCustomerFromID(1).setBalance(1000.0);
        getCustomerFromID(2).setBalance(1000.0);
    }

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


    private int setUniqueID(){
        return currentID++;
    }
}
