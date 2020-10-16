import java.util.Random;

public class WashCard {
    private int ownerId;
    private long cardId;

    /**
     * A car wash WashCard.
     * @param ownerID the customer id of the owner.
     */
    public WashCard(int ownerID) {
        this.ownerId = ownerID;
        this.cardId = generateCardID();
    }

    /**
     * Generates a random id.
     * TODO: make id generation return unique ids.
     * @return random id.
     */
    private long generateCardID() {
        return new Random().nextLong();
    }

    /**
     * Gets the id of the WashCard owner.
     * @return owner id.
     */
    public int getOwnerId() {
        return ownerId;
    }

    /**
     * Gets the id of the WashCard.
     * @return wash card id.
     */
    public long getCardId() {
        return cardId;
    }
}
