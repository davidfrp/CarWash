import java.util.Random;

public class WashCard {
    int ownerID;
    long cardID = generateCardID();

    public WashCard(int ownerID) {
        this.ownerID = ownerID;
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
