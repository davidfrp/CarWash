import java.text.SimpleDateFormat;
import java.util.Date;

public class Receipt {
    private CreditCard creditCard;
    private Wash wash;
    private double rechargeAmount;
    private boolean washReceipt;
    private final int desiredLength = 56;

    public Receipt(Wash wash, boolean washReceipt){
        this.washReceipt = washReceipt;
        this.wash = wash;
    }

    public Receipt(double rechargeAmount, CreditCard creditCard, boolean washReceipt){
        this.washReceipt = washReceipt;
        this.rechargeAmount = rechargeAmount;
        this.creditCard = creditCard;
    }

    private String thankYou(){
        if(washReceipt){
            return "\t\t\tThank you for washing your car at\n";
        }
        else return "\t\t\tThank you for your purchase.\n";
    }

    private String makeLogo() {
        return  "   _____                       _____ _     _            \n" +
                "  / ____|                     / ____| |   (_)           \n" +
                " | (___  _   _ _ __   ___ _ _| (___ | |__  _ _ __   ___ \n" +
                "  \\___ \\| | | | '_ \\ / _ \\ '__\\___ \\| '_ \\| | '_ \\ / _ \\\n" +
                "  ____) | |_| | |_) |  __/ |  ____) | | | | | | | |  __/\n" +
                " |_____/ \\__,_| .__/ \\___|_| |_____/|_| |_|_|_| |_|\\___|\n" +
                "              | |                                       \n" +
                "              |_|";
    }

    private String makeSeparator(int desiredLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < desiredLength; i++) {
            sb.append("=");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        if(washReceipt) {
            return "" + thankYou() + makeLogo() + "\n" + makeSeparator(desiredLength) + "\n" +
                    "Type of Wash:" + wash.getType().toString() +                       "\n" +
                    "Price: " + wash.getPrice() + "DKK" +                               "\n" +
                    "Your purchase was made at: " + getPrintTime();
        }
        else{
            return ""+ thankYou() + makeLogo() + "\n" + makeSeparator(desiredLength) + "\n" +
                    "" + getPrintTime()                                              + "\n" +
                    "\nPurchase\tDKK:\t" + rechargeAmount                            + "\n\n" +
                    "CREDIT CARD: XXXX XXXX XXXX " + creditCard.getCreditCardNo()    + "\n" +
                    makeSeparator(desiredLength)                                     + "\n";
        }
    }

    private String getPrintTime(){
        return new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(new Date());
    }
}
