import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WashType {
    //Fields
    private String type;
    private double price;
    private final double discount = 0.2;



    public WashType(String type) {      //Constructor
        this.type = type.toUpperCase().trim();
        makePrice();
    }

    //Getters
    public String getType() {
        return type;
    }

    //Setters
    public void setPrice(double price){
        this.price = price;
    }

    private void makePrice(){   //Make the prices of washes
        switch (type){
            case "ECONOMY":
                setPrice(50);
                break;
            case "STANDARD":
                setPrice(80);
                break;
            case "DELUXE":
                setPrice(120);
                break;
            default:
                System.err.println("Error with making prices");
                break;
        }
    }

    public void applyDiscount(Customer customer){   //Apply discount to washes

        if(!type.equals("DELUXE")){     //If type is not deluxe
            /* TODO: Uncomment when program is done
            SimpleDateFormat formatter = new SimpleDateFormat("HH");    //Add clock object with only hours
            Date date =  new Date();        //Create date object
            int currentTime = Integer.parseInt(formatter.format(date)); //Parse the formatted time to an int
            Calendar cal = Calendar.getInstance();      //get instance of calender
            cal.setTime(date);      //use date object to get time
            int day = cal.get(Calendar.DAY_OF_WEEK);    //Get the day of week 1 = sunday, 7 = saturday

            if(day > 1 && day < 7){     //if weekday
                if(currentTime < 14){   //If before 14:00
                    price -= price * discount;  //apply discount
                }
            }*/
            price -= price * discount;  //apply discount //TODO: Delete when program is done
        }
        if(customer.isHasFDMMembership()){ //If customer has FDM membership
            price -= price * discount;  //Apply discount
        }
    }
}
