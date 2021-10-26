import java.util.Scanner;
import java.util.Arrays;

public class Game {

    // instantiates
	final int HAND_SIZE = 5;
    final int NUM_JOKERS = 2;
    char joker='N';
    int chipsPlayer=1;
	Scanner scan = new Scanner(System.in);
    
    HandlerHandJoker handlerJK= new HandlerHandJoker();

	Deck deck = new Deck();
	Player player = new Player(chipsPlayer);
    Player crupier = new Player(chipsPlayer);
   

    public void play() throws InterruptedException
	{
        this.insertTitle();
        do{//Jokers or without jokers
            System.out.print("Would you like to play with jokers?(Y/N): ");
            char c = scan.next().charAt(0);            
            joker= Character.toUpperCase(c);
        }while (joker!='N' && joker!='Y');
        
        do{//Amount of chips
            System.out.print("How many chips would you like to play?: ");
            chipsPlayer = scan.nextInt();
        }while (chipsPlayer<0);
        
        player.setChips(chipsPlayer);
        crupier.setChips(chipsPlayer);

        if(joker=='Y'){
            while (player.getChips() > 0 && crupier.getChips() >0){
                //fill and shuffle deck
                deck.setDeck(deck.fillDeckJoker(NUM_JOKERS));
                //Start Game
                this.startGame();
            }
                
        }else{//Without jokers
            while (player.getChips() > 0 && crupier.getChips() >0){
                //fill deck
                deck.fillDeck();
                
                //shuffle
                deck.shuffle();
                //Start game
                this.startGame();
            }
        }
        if(this.player.getChips()==0) System.out.println("THE WINNER IS CRUPIER!!");
        else System.out.println("YOU ARE THE WINNER!!");
        System.out.println("**********************************************");
	}


    private void startGame() throws InterruptedException{
        int pot=0;
        System.out.println("SHUFFLING...\n");
        Thread.sleep(1000);
        
        //players draws
        player.setHand(player.draw(deck));
        crupier.setHand(crupier.draw(deck));
        

        //Look our cards
        System.out.println("PLAYER'S CARDS:");
        handlerJK.checkHand(player.getHand());
        System.out.println();
        int playerHand = handlerJK.evaluate(player.getHand());
        System.out.println();

        //Redraw new cards
        System.out.print("Would you like to change some your cards?(1 for yes, 0 for no): ");
        int redrawans = scan.nextInt();
        if(redrawans>=1){
            this.redraw(this.player.getHand());
            //Look our new cards
            System.out.println();
            System.out.println("NEW PLAYER'S CARDS:");
            Arrays.sort(player.getHand());
            handlerJK.checkHand(player.getHand());
            System.out.println();
            playerHand = handlerJK.evaluate(player.getHand());
            System.out.println();
        }

        int answerBet=0;
        do{
            //BET, CHECK OR HOLD
            System.out.print("Would you like to bet (2), to check (1) or to hold (0)?: ");
            answerBet = scan.nextInt();
        }while(answerBet > 0 && answerBet > 2);
        


        if(answerBet>0){
            boolean chipCorrect=true;
            if(answerBet==1) chipCorrect=false; //CHECK
            
            while(chipCorrect){//BET
                System.out.print("How many chips would you like to bet? ");
                int chips = scan.nextInt();
                
                if(chips <=0 || chips > player.getChips() || chips > crupier.getChips()){
                    System.out.println("The amount entered is less than 0 or more than your token limit("+player.getChips()+ "/" +crupier.getChips()+"). Please try again");
                }else{
                    player.bet(chips);
                    crupier.bet(chips);
                    pot= chips*2;
                    chipCorrect=false;
                }
            }
        // display hand again
        Thread.sleep(1000);
        System.out.println();
        System.out.println("CRUPIER'S CARDS:");
        handlerJK.checkHand(crupier.getHand());
        System.out.println();
        Thread.sleep(1000);
        
        System.out.println("SHOW CARDS...\n");
        // evaluate the hand
        System.out.print("Player: ");
        playerHand = handlerJK.evaluate(player.getHand());
        System.out.print("Crupier: ");
        int crupierHand = handlerJK.evaluate(crupier.getHand());
        Thread.sleep(1000);

        checkBet(playerHand, crupierHand, pot);
        

        System.out.println("Player's chips: "+player.getChips());
        System.out.println("Crupier's chips: "+crupier.getChips());
        System.out.println("**********************************************");

        }else if (answerBet == 0){
            winChips(crupier,pot);
        }

        if(this.player.getChips() >0 && this.crupier.getChips() > 0){
            System.out.println();
            System.out.println("NEW TURN!");
        }
    }

    /**
     * Set chips for the winner
     * 
     * @param Player player 
     * @param int pot
     */
    private void winChips(Player p, int pot){
        int chipCounter =p.getChips();
        p.setChips(chipCounter+pot);
    }

    /**
     * Get the evaluation and give the chips for the winner
     * 
     * @param int player evaluation hand
     * @param int crupier evaluation hand
     * @param int pot
     */
    private void checkBet(int player, int crupier, int pot){
        if(player > crupier){
            winChips(this.player,pot);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("                YOU WIN");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }  
        else if(player < crupier){
            winChips(this.crupier,pot);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("              CRUPIER WIN");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
 
        } 
        else{
            if(player==1){ //Double highCard
                //Get the each highCard
                int highCardPlayer = handlerJK.highCard(this.player.getHand());
                int highCardCrupier = handlerJK.highCard(this.crupier.getHand());

                if(highCardPlayer==1) highCardPlayer=14; //High card is ace
                if(highCardCrupier==1) highCardCrupier=14;

                checkBet(highCardPlayer,highCardCrupier,pot);
            }else if(player==2 || player==3){ //Double pair or triple
                
                int valuePairPlayer = 0;
                int valuePairCrupier = 0;
                for(int counter = 1; counter < HAND_SIZE; counter++)
                {
                    if(handlerJK.existJoker(this.player.getHand())){//JokerHands
                        valuePairPlayer= handlerJK.highCard(this.player.getHand());
                    }
                    if(handlerJK.existJoker(this.crupier.getHand())){//JokerHands
                        valuePairCrupier= handlerJK.highCard(this.crupier.getHand());
                    }
                    if (this.player.getHand()[counter - 1].getValue() == this.player.getHand()[counter].getValue())
                    {
                        valuePairPlayer=this.player.getHand()[counter - 1].getValue();
                    }
                    if (this.crupier.getHand()[counter - 1].getValue() == this.crupier.getHand()[counter].getValue())
                    {
                        valuePairCrupier=this.crupier.getHand()[counter - 1].getValue();
                    }
                    if(valuePairPlayer==1) valuePairPlayer=14; //High pair is double ace
                    if(valuePairCrupier==1) valuePairCrupier=14;//High pair is double ace
                }
                checkBet(valuePairPlayer,valuePairCrupier,pot);
            }else{//Same cards, Nobody win
                winChips(this.crupier,pot/2);
                winChips(this.player,pot/2);
            }
            
        } 
    }

     /**
     * Asks if player wants to redraw
     * 
     * @param Card[] player hand
     */
	private Card[] redraw(Card[] hand)
	{
		for (int counter = 0; counter < HAND_SIZE; counter++)
		{
			System.out.print("Card " + (counter + 1) + "?" +
					" (1 for yes, 0 for no): ");
			int answer = scan.nextInt();
			if (answer == 1)
			{
				hand[counter] = player.redraw(counter, deck);
			}
		}
		deck.refreshDeckPosition();
		return hand;
	}

    
    private void insertTitle() {
        System.out.println("   ____________________");
        System.out.println("  /                   /");
        System.out.println(" / WELCOME TO POKERG / ");
        System.out.println("/___________________/  ");
        System.out.println();
    }

}
