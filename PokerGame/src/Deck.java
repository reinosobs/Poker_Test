import java.util.Random;

public class Deck {
    private final int DECK_SIZE = 52;
    private final int SHUFFLE_EXCHANGES = 2000;
    private final int HAND_SIZE = 5;
	public int restOfDeck = 10;
	
	Card[] deck = new Card[DECK_SIZE];
	Random r = new Random();

    /**
     * Fill deck with various cards
     * 
     */
    public void fillDeck()
	{
		int counter = 0;
		for (int suit = 1; suit <= 4; suit++)
		{
			for (int value = 1; value <= 13; value++)
			{
				deck[counter] = new Card(suit, value);
				counter++;
			}
		}
	}

    /**
     * Fill deck with various cards and jokers
     * 
     * @param int numJokers
     */
    public Card[] fillDeckJoker(int jokers)
	{
        Card[] deckJoker = new Card[DECK_SIZE+jokers];
		int counter = 0;
		for (int suit = 1; suit <= 4; suit++)
		{
			for (int value = 1; value <= 13; value++)
			{
				deckJoker[counter] = new Card(suit, value);
				counter++;
			}
		}
        for(int counterJ=0;counterJ<jokers;counterJ++){
            deckJoker[counter]=new Card(0,0);
            counter++;
        }

        //SHUFFLE
        for (int x = 0; x <= SHUFFLE_EXCHANGES; x++)
		{
			int number1 = r.nextInt(DECK_SIZE+jokers);
			int number2 = r.nextInt(DECK_SIZE+jokers);
			Card temp = deckJoker[number1];
			deckJoker[number1] = deckJoker[number2];
			deckJoker[number2] = temp;
		}

        return deckJoker;
	}

    /**
     * Shuffle deck
     * 
     */
    public void shuffle()
	{
		for (int x = 0; x <= SHUFFLE_EXCHANGES; x++)
		{
			int number1 = r.nextInt(DECK_SIZE);
			int number2 = r.nextInt(DECK_SIZE);
			Card temp = deck[number1];
			deck[number1] = deck[number2];
			deck[number2] = temp;
		}
    }

    /**
     * Create a full hand dealing cards of the deck.
     * 
     */
    public Card[] deal()
	{
		Card[] hand = new Card[HAND_SIZE];
		for (int deckPosition = 0; deckPosition < HAND_SIZE; deckPosition++)
		{
			hand[deckPosition] = this.deck[deckPosition];
            for(int counter=deckPosition; counter < this.deck.length-1; counter++){//Remove first card of the deck
                this.deck[counter]=this.deck[counter+1];
            }
		}
		return hand;
	}

    /**
     * Deals cards for redraw
     * 
     */
	public Card redeal()
	{
		Card nextCard = deck[restOfDeck];
		restOfDeck++;
		return nextCard;
	}
	 
    /**
     * Refreshes deck position to 10 (5 player + 5 crupier) for next hand
     * 
     */
	public void refreshDeckPosition()
	{
		restOfDeck = 10;
	}

    /**
     * Set new deck with new cards
     * 
     * @param Card[] hand
     */
    public void setDeck(Card[] deck){
        this.deck=deck;
    }

    /**
     * Retrieves the array of cards in this deck
     * 
     * @return Card[]
     */
    public Card[] getCards()
    {
        return this.deck;
    }

}
