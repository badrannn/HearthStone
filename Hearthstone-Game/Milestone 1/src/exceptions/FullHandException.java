package exceptions;

import model.cards.Card;

public class FullHandException extends HearthstoneException {
    private Card burned;

    public FullHandException(Card b){
    	this.burned=b;
    }

    public FullHandException(String message, Card b) {
        super(message);
        this.burned = b;
    }

    public Card getBurned() {
        return this.burned;
    }
}