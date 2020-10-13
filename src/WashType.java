public class WashType {
    private double economy = 50;
    private double standard = 80;
    private double deLuxe = 120;


    public double getEconomy() {
        return economy;
    }

    public double getStandard() {
        return standard;
    }

    public double getDeLuxe() {
        return deLuxe;
    }

    public void applyDiscount (int type, double discount){
        discount /= 100;

        if(type == 1){
            economy -= (discount * economy);
        }
        else if(type == 2){
            standard -= (discount * standard);
        }
        else if(type == 3){
            deLuxe -= (discount * deLuxe);
        }
        else if(type == 4){
            economy -= (discount * economy);
            standard -= (discount * standard);
            deLuxe -= (discount * deLuxe);
        }else {
            System.out.println("Wrong type number");
        }
    }

    public void changePrice (int type, double newPrice){
        if(type == 1){
            economy = newPrice;
        }
        else if(type == 2){
            standard *= newPrice;
        }
        else if(type == 3){
            deLuxe *= newPrice;
        }
        else if(type == 4){
            economy *= newPrice;
            standard *= newPrice;
            deLuxe *= newPrice;
        }else {
            System.out.println("Wrong type number");
        }
    }
}
