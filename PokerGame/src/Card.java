public class Card implements Comparable<Card>{
    /**
     * Suit card
     * 
     * @var integer
     */
    private int suit;

    /**
     * Value card
     * 
     * @var integer
     */
    private int value;

    /**
     * Initialize a card
     * 
     * @param integer $suit
     * @param integer $value
     */
    public Card(int suit, int value){
        this.suit=suit;
        this.value=value;
    }

    public String getDescription(){
        return this.value+ " of " + this.suit;
    }
    /**
     * Returns the suit of this card
     * 
     * @return integer
     */
    public int getSuit(){
        return suit;
    }
    /**
     * Returns the value of this card
     * 
     * @return integer
     */
    public int getValue(){
        return value;
    }
    /**
     * Set the suit of this card
     * 
     * @param integer $suit
     */
    public void setSuit(int suit){
        this.suit=suit;
    }
    /**
     * Set the value of this card
     * 
     * @param integer $value
     */
    public void setValue(int value){
        this.value=value;
    }

    /**
     * Returns the value and the suit of this card
     * 
     * @return integer[2]
     */
    public int[] getCard(){
        int[] card= {this.value,this.suit};
        return card;
    }

    public int compareTo(Card compareCard) {
	
		int compareQuantity = compareCard.getValue(); 
		
		//ascending order
		return this.value - compareQuantity;
		
		//descending order
		//return compareQuantity - this.quantity;
		
	}
}
