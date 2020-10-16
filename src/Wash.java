import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Wash {

    private WashType type = null;
    private Machine machine;
    private double price = 0.0;
    private final double discount = 0.2;


    public Wash(String washType, Machine machine) {
        this.machine = machine;
        try {
            type = WashType.valueOf(washType.trim().toUpperCase());
        } catch (IllegalArgumentException e){
            System.out.println("No valid wash type given.");
        }
        makePrice();
    }

    public Wash(WashType type, Machine machine) {
        this.machine = machine;
        this.type = type;
        makePrice();
    }


    private void makePrice(){
        switch (type) {
            case ECONOMY -> price = 50.0;
            case STANDARD -> price = 80.0;
            case DELUXE -> price = 120.0;
            default -> System.err.println("Error with making prices");
        }
        applyDiscount();
    }

    /* Could possible be in Machine */
    private void applyDiscount(){
        if(checkForFDMDiscount(machine)){
            System.out.println("As a member of FDM, you will get a special FDM discount.");
            price -= price * discount;  //Apply discount
        }
        if(checkForEarlyBirdDiscount()){
            System.out.println("Congratulations! You are eligible for our early bird discount!");
            price -= price * discount;  //Apply discount
        }
    }

    private boolean checkForFDMDiscount(Machine machine){
        if(machine.getCurrentCustomer().isHasFDMMembership()){                 //If customer has FDM membership
            return true;
        }
        return false;
    }
    private boolean checkForEarlyBirdDiscount(){
        if(type != WashType.DELUXE){
            SimpleDateFormat formatter = new SimpleDateFormat("HH");    //Add clock object with only hours
            Date date =  new Date();                                           //Create date object
            int currentTime = Integer.parseInt(formatter.format(date));        //Parse the formatted time to an int
            Calendar cal = Calendar.getInstance();                             //get instance of calender
            cal.setTime(date);                                                 //use date object to get time
            int day = cal.get(Calendar.DAY_OF_WEEK);                           //Get the day of week 1 = sunday, 7 = saturday

            // day = 2;                                                         /* USED FOR DEBUG */
            // currentTime = 13;                                                /* USED FOR DEBUG */
            if(day > 1 && day < 7){                                            //if weekday
                if(currentTime < 14){                                          //If before 14:00
                    return true;
                }
            }
        }
        return false;
    }


    //Getters
    public WashType getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    //Setters
    public void setPrice(double price){
        this.price = price;
    }
}
