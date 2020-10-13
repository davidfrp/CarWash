import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Wash {

    private WashType type = null;
    private double price;
    private final double discount = 0.2;

    public Wash(String washType) {
        try {
            type = WashType.valueOf(washType.trim().toUpperCase());
        } catch (IllegalArgumentException e){
            System.out.println("No valid wash type given.");
        }
        makePrice();
    }

    public Wash(WashType type) {
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
    }


    /* SKAL MÅSKE FLYTTES */
    private void applyDiscount(){
        price -= price * discount;  //Apply discount
    }

    public void checkForDiscount(Customer customer){

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
                    applyDiscount();
                }
            }
        }
        if(customer.isHasFDMMembership()){ //If customer has FDM membership
            applyDiscount();
        }
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
