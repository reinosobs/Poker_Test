import java.util.Arrays;

public class Player {
    
    private int chips;

    private Card[] hand;

    public Player(int chips){
        this.chips=chips;
        this.hand=null;
    }

    /**
     * Player bet the chips
     * 
     * @param integer $chips
     * @return integer
     */
    public int bet(int chips){
        this.chips-=chips;
        return chips;
    }

    /**
     * Returns the chips of this player
     * 
     * @return integer
     */
    public int getChips(){
        return this.chips;
    }
    /**
     * Returns the hand of this player
     * 
     * @return Card[]
     */
    public Card[] getHand(){
        return this.hand;
    }
    /**
     * Set the chips of this player
     * 
     * @param integer $chips
     */
    public void setChips(int chips){
        this.chips=chips;
    }
    /**
     * Set the hand of this player
     * 
     * @param Card[] $hand
     */
    public void setHand(Card[] hand){
        this.hand=hand;
    }


    /**
     * Get 5 cards of the deck
     * 
     * @param integer $chips
     */
	public Card[] draw(Deck deck)
	{
		Card[] hand = deck.deal();
        Arrays.sort(hand);
		return hand;
	}

    /**
     * switches card for a new card
     * 
     * @param integer $chips
     */
	public Card redraw(int counter, Deck deck)
	{
		Card card = deck.redeal();
		return card;
	}

    
}
