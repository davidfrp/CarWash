import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class CreditCard implements Serializable {
    static final long serialVersionUID = 3294085755L;
    private int creditCardNo = ThreadLocalRandom.current().nextInt(1000, 9999);
    public int getCreditCardNo(){
        return creditCardNo;
    }
}
