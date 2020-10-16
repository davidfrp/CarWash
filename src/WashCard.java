import java.util.Random;

public class WashCard {
    private int ownerId;
    private long cardId;

    /**
     * A car wash WashCard.
     * @param ownerId the customer id of the owner.
     */
    public WashCard(int ownerId) {
        this.ownerId = ownerId;
        this.cardId = generateCardId();
    }

    /**
     * Generates a random id.
     * TODO: make id generation return unique ids.
     * @return random id.
     */
    private long generateCardId() {
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
