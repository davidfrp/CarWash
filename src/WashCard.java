import java.util.Random;

public class WashCard {
    private int ownerID;
    private long cardID;

    public WashCard(int ownerID) {
        this.ownerID = ownerID;
        this.cardID = generateCardID();
    }

    private long generateCardID(){
        return new Random().nextLong();
    }

    public int getOwnerID() {
        return ownerID;
    }

    public long getCardID() {
        return cardID;
    }
}
