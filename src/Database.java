import java.util.ArrayList;
import java.util.Arrays;

public class Database {
    int currentID = 0;
    ArrayList<Customer> customerList = new ArrayList<Customer>();   //Arraylist to store all customers
    ArrayList<Admin> adminList = new ArrayList<Admin>();    //Arraylist to store all admins
    ArrayList<WashCard> washCardList = new ArrayList<>();

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
    }

    public void setBalanceOfCustomer(int ID){

    }

    public Customer getCustomerFromID(int id) {
        for (Customer customer : customerList) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    public String getNameFromID(int id){
            return getCustomerFromID(id).getName();
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
