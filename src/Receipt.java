import java.text.SimpleDateFormat;
import java.util.Date;

public class Receipt {
    private CreditCard creditCard;
    private Wash wash;
    private double rechargeAmount;
    private boolean isWashReceipt;
    private final int DESIRED_LENGTH = 56;

    /**
     * A car wash receipt.
     * @param wash          represent a car wash.
     * @param isWashReceipt whether the receipt is for a car wash.
     */
    public Receipt(Wash wash, boolean isWashReceipt) {
        this.isWashReceipt = isWashReceipt;
        this.wash = wash;
    }

    /**
     * A car wash receipt.
     * @param rechargeAmount amount that was recharged.
     * @param creditCard     credit card used.
     * @param isWashReceipt  whether the receipt is for a car wash.
     */
    public Receipt(double rechargeAmount, CreditCard creditCard, boolean isWashReceipt) {
        this.isWashReceipt = isWashReceipt;
        this.rechargeAmount = rechargeAmount;
        this.creditCard = creditCard;
    }

    /**
     * Gets a thank you notice.
     * @return a "thank you" message.
     */
    private String makeThankYou() {
        if (isWashReceipt) {
            return "\t\t\tThank you for washing your car at\n";
        }

        return "\t\t\tThank you for your purchase.\n";
    }

    /**
     * Gets the SuperShine logo in full on ASCII art style.
     * @return the supershine logo as a String.
     */
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

    /**
     * Makes a separator for the receipt.
     * @param desiredLength wanted length of the separator.
     * @return              a String containing the separator line.
     */
    private String makeSeparator(int desiredLength) {
        return "=".repeat(Math.max(0, desiredLength));
    }

    /**
     * Gets the receipt.
     * @return a String containing the receipt.
     */
    @Override
    public String toString() {
        if(isWashReceipt) {
            return "" + makeThankYou() + makeLogo() + "\n" + makeSeparator(DESIRED_LENGTH) + "\n" +
                    "Type of Wash:" + wash.getType().toString() +                       "\n" +
                    "Price: " + wash.getPrice() + "DKK" +                               "\n" +
                    "Your purchase was made at: " + getPrintTime();
        }
        else {
            return ""+ makeThankYou() + makeLogo() + "\n" + makeSeparator(DESIRED_LENGTH) + "\n" +
                    "" + getPrintTime()                                              + "\n" +
                    "\nPurchase\tDKK:\t" + rechargeAmount                            + "\n\n" +
                    "CREDIT CARD: XXXX XXXX XXXX " + creditCard.getCreditCardNumber()    + "\n" +
                    makeSeparator(DESIRED_LENGTH)                                     + "\n";
        }
    }

    /**
     * Gets the current time and returns it in a human-readable format.
     * @return a String containing the date and time of execution.
     */
    private String getPrintTime() {
        return new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(new Date());
    }
}
