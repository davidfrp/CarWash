public class WashType {
    private String type;
    private double price;
    private final double discount = 0.2;



    public WashType(String type) {
        this.type = type.toUpperCase().trim();
        makePrice();
    }

    public String getType() {
        return type;
    }

    public void setPrice(double price){
        this.price = price;
    }

    private void makePrice(){
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

    public void applyDiscount(Customer customer){

        if(!type.equals("DELUXE")){
            price -= price * discount;
            //TODO: add calender with time
        }
        if(customer.isHasFDMMembership()){
            price -= price * discount;
        }

    }

}
