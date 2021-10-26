public class HandlerHandJoker {
    private final int HAND_SIZE = 5;

	/**
     * Evaluates the hand
     * 
	 * @return int
     * @param Card[] hand
     */
	public int evaluate(Card[] hand)
	{
        int evaluation=0;
		if (this.royalFlush(hand))
		{
			System.out.println(" Have a royal flush!");
            evaluation=10;
		}
		else if (this.straightFlush(hand))
		{
			System.out.println("You have a straight flush!");
            evaluation=9;
        }
		else if (this.fourOfaKind(hand))
		{
			System.out.println("You have four of a kind!");
            evaluation=8;
        }
		else if (this.fullHouse(hand))
		{
			System.out.println("You have a full house!");
            evaluation=7;
		}
		else if (this.flush(hand))
		{
			System.out.println("You have a flush!");
            evaluation=6;
		}
		else if (this.straight(hand))
		{
			System.out.println("You have a straight!");
            evaluation=5;
		}
		else if (this.triple(hand))
		{
			System.out.println("You have a triple!");
            evaluation=4;
		}
		else if (this.twoPairs(hand))
		{
			System.out.println("You have two pairs!");
            evaluation=3;
		}
		else if (this.pair(hand))
		{
			System.out.println("You have a pair!");
            evaluation=2;
		}
		else
		{
			int highCard = this.highCard(hand);
			if(highCard==1) System.out.println("Your highest card is Ace");
			else if(highCard==11) System.out.println("Your highest card is Jack");
			else if(highCard==12) System.out.println("Your highest card is Queen");
			else if(highCard==13) System.out.println("Your highest card is King");
			else System.out.println("Your highest card is " + highCard);
            evaluation=1;
		}
        return evaluation;
	}

	/**
     * Tells player cards in hand
     * 
     * @param Card[] hand
     */
	public void checkHand(Card[] hand)
	{
		for (int handCounter = 0; handCounter < HAND_SIZE; handCounter++)
		{
			this.display(hand[handCounter]);
		}
	}
	

    /**
     * Checks for a royal flush
     * 
	 * @return boolean
     * @param Card[] hand
     */
	public boolean FiveKind(Card[] hand)
	{
        for(int counter2=2;counter2<HAND_SIZE;counter2++){
            if(existJoker(hand) && hand[counter2-1] != hand[counter2] ){
                return false;
            }
        }
        return true;
	}

	/**
     * Checks for a royal flush
     * 
	 * @return boolean
     * @param Card[] hand
     */
	public boolean royalFlush(Card[] hand)
	{
		if (this.flush(hand) && (hand[0].getValue() == 1 || hand[0].getValue() == 0) && 
            (hand[1].getValue() == 10) &&
            (hand[2].getValue() == 11) &&
			(hand[3].getValue() == 12) &&
            (hand[4].getValue() == 13))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	

	/**
     * Checks for a straight flush
     * 
	 * @return boolean
     * @param Card[] hand
     */
	public boolean straightFlush(Card[] hand)
	{
		if(!this.flush(hand)) return false;
		if(!this.straight(hand)) return false;
		return true;
		
	}

	/**
     * Checks for a four of a kind 
     * 
	 * @return boolean
     * @param Card[] hand
     */
	public boolean fourOfaKind(Card[] hand)
	{
		if ((hand[0].getValue() != hand[3].getValue() || hand[0].getValue() != 0) && hand[1].getValue() != hand[4].getValue())
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/**
     * Checks for full house
     * Creo que esta mal
	 * @return boolean
     * @param Card[] hand
     */
	public boolean fullHouse(Card[] hand)
	{
		int comparison = 0;
		for (int counter = 1; counter < HAND_SIZE; counter++)
		{
			if (hand[counter - 1].getValue() == hand[counter].getValue())
			{
				comparison++;
			}
            else if(existJoker(hand)){
                comparison++;
            }
		}
		if (comparison == 3)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
     * Checks for flush
     * 
	 * @return boolean
     * @param Card[] hand
     */
	public boolean flush(Card[] hand)
	{
        if(existJoker(hand)){
            for (int counter = 2; counter < HAND_SIZE; counter++)
		        {
			if (hand[1].getSuit() != hand[counter].getSuit())
			{
				return false;
			}
		}
		    return true;
        }else{
            for (int counter = 1; counter < HAND_SIZE; counter++)
            {
                if (hand[0].getSuit() != hand[counter].getSuit())
                {
                    return false;
                }
            }
            return true;
        }
		
	}
	
	/**
     * Checks for straight
     * 
	 * @return boolean
     * @param Card[] hand
     */
	public boolean straight(Card[] hand)
	{
        int chance=0;
        if(existJoker(hand)){
            chance=1;
        }
		for (int counter2 = 1; counter2 < HAND_SIZE; counter2++)
		{
			if (hand[counter2 - 1].getValue() != (hand[counter2].getValue() - 1) )
			{
                if(chance > 0) chance--;
				else return false;
			}
				
		}
		return true;
	}

	/**
     * Checks for triple
     * 
	 * @return boolean
     * @param Card[] hand
     */
	public boolean triple(Card[] hand)
	{
        if(existJoker(hand)){//And a pair is triple
            int check = 0;
            for(int counter = 1; counter < HAND_SIZE; counter++)
            {
                if (hand[counter - 1].getValue() == hand[counter].getValue())
                {
                    check++;
                }
            }
            if (check == 1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
		else
		{
			
            for(int counter = 0; counter < HAND_SIZE; counter++)
            {
                int check = 0;
                int card=hand[counter].getValue();
                for(int counter2=counter+1; counter2 <HAND_SIZE; counter2++){
                    if(hand[counter2].getValue()==card){
                        check++;
                    }
                }
                if (check == 2)
                {
                    return true;
                }
            }
            return false;
		}
	}
	
	/**
     * Checks for two pairs
     * 
	 * @return boolean
     * @param Card[] hand
     */
	public boolean twoPairs(Card[] hand)
	{
		int check = 0;
		for(int counter = 1; counter < HAND_SIZE; counter++)
		{
			if (hand[counter - 1].getValue() == hand[counter].getValue())
			{
				check++;
			}
		}
		if (check == 2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
     * Checks for pair
     * 
	 * @return boolean
     * @param Card[] hand
     */
	public boolean pair(Card[] hand)
	{
        if(existJoker(hand)){
            return true;
        }
		int check = 0;
		for(int counter = 1; counter < HAND_SIZE; counter++)
		{
			if (hand[counter - 1].getValue() == hand[counter].getValue())
			{
				check++;
			}
		}
		if (check == 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
     * Find highest card
     * 
	 * @return boolean
     * @param Card[] hand
     */
	public int highCard(Card[] hand)
	{
		int highCard = 0;
		for (int counter = 0; counter < HAND_SIZE; counter++)
		{
			if (hand[counter].getValue() > highCard)
			{
				highCard = hand[counter].getValue();
			}
		}
		return highCard;
	}

    /**
     * Find joker in the hand
     * 
	 * @return boolean
     * @param Card[] hand
     */
	public boolean existJoker(Card[] hand)
	{
        if(hand[0].getValue()==0){
                return true;
        }
		return false;
	}


	/**
     * Generates string for each card in hand
     * 
     * @param Card[] hand
     */
	public void display(Card card)
	{
        if(card.getValue()== 0){
			System.out.println("|     JOKER      |");
		}
		if (card.getValue() == 1)
		{
			System.out.print("| Ace of ");
		}
		if (card.getValue() == 2)
		{
			System.out.print("| 2   of ");
		}
		if (card.getValue() == 3)
		{
			System.out.print("| 3   of ");
		}
		if (card.getValue() == 4)
		{
			System.out.print("| 4   of ");
		}
		if (card.getValue() == 5)
		{
			System.out.print("| 5   of ");
		}
		if (card.getValue() == 6)
		{
			System.out.print("| 6   of ");
		}
		if (card.getValue() == 7)
		{
			System.out.print("| 7   of ");
		}
		if (card.getValue() == 8)
		{
			System.out.print("| 8   of ");
		}
		if (card.getValue() == 9)
		{
			System.out.print("| 9   of ");
		}
		if (card.getValue() == 10)
		{
			System.out.print("| 10  of ");
		}
		if (card.getValue() == 11)
		{
			System.out.print("| J   of ");
		}
		if (card.getValue() == 12)
		{
			System.out.print("| Q   of ");
		}
		if (card.getValue() == 13)
		{
			System.out.print("| K   of ");
		}
		if (card.getSuit() == 1)
		{
			System.out.print(" Spades |");
			System.out.println();
		}
		if (card.getSuit() == 2)
		{
			System.out.print(" Hearts |");
			System.out.println();
		}
		if (card.getSuit() == 3)
		{
			System.out.print("Diamonds|");
			System.out.println();
		}
		if (card.getSuit() == 4)
		{
			System.out.print("  Clubs |");
			System.out.println();
		}
		
	}
}




