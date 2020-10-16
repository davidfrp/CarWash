import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class CreditCard implements Serializable {
    static final long serialVersionUID = 3294085755L;
    private int creditCardNumber = ThreadLocalRandom.current().nextInt(1000, 9999);

    /**
     * Gets the credit card number.
     * @return credit card number.
     */
    public int getCreditCardNumber() {
        return creditCardNumber;
    }
}
