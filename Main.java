import java.util.ArrayList;
import java.util.Collections;


public class Main {

	public static void main(String[] args) {
		
		class Card{
			String suit;
			String rank;
			int value;
			
		    Card(String suit, String rank, int value) {
		        this.suit = suit;
		        this.rank = rank;
		        this.value = value; //valueはなくてもいいかも
		    }
		    
		    void printCard() {
		        System.out.println("suit:"+suit + ",rank:"+rank);
		    }
		}
		
		class Deck{
			ArrayList<Card> cardsInDeck = new ArrayList<>();
	        String[] suits = {"♠", "♥", "♦", "♣"};
	        String[] ranks = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	        Integer[] values   = {1,2,3,4,5,6,7,8,9,10,10,10,10};
	        
	        Deck(){
		        for (String suit : suits) {
		            for (int i=0; i<ranks.length; i++) {
		                cardsInDeck.add(new Card(suit, ranks[i], values[i]));
		            }
		        }
	        }
	        
	        void shuffle() {
	            Collections.shuffle(cardsInDeck);
	        }
	        
	        Card draw() {              
	            if (cardsInDeck.isEmpty()) return null;
	            Card topcard = cardsInDeck.get(0);
	            cardsInDeck.remove(0);
	            return topcard;
	        }
		}
        
        class Hand{
        	ArrayList<Card> cardsInHand = new ArrayList<>();
        	
        	void hit(Deck deck){
        		Card newcard = deck.draw();
                if (newcard != null) {
                    cardsInHand.add(newcard);
                    //newcard.printCard();
                }
        	};
        	
        	void stand(){};
        	
            int countScore() {
            	int score=0;
            	int aceCount=0;
            	
            	for(Card card:cardsInHand) {
            		score += card.value;
            		if (card.rank.equals("A")) {
            			aceCount++;
            		}
            	}
            	
            	while (aceCount>0&&score+10<=21) {
            		score += 10;
            		aceCount --;
            	}
            	
            	return score;
            }
            
            void showHand() {
                for(Card eachcards:this.cardsInHand) {
                	eachcards.printCard();
                }
            };
    	}
        
        class DealerHand extends Hand{
			void showLimitedHand() {
			    System.out.println("===ディーラーの手札...===");
			    this.cardsInHand.get(0).printCard();
			    for(int i=1;i<this.cardsInHand.size();i++) {
			    	System.out.println("suit:?,rank:?,value?");
			    }
                System.out.println("===================");
			}
        	
        }
        
        class PlayerHand extends Hand{
        	void showHand() {
                System.out.println("===あなたの手札...===");
                super.showHand();
                System.out.println("===================");
        	}
        	
        }

        
        // ==========
        //	ゲームの動作部分
        // ==========
        System.out.println("======================================================================================================");
        System.out.println("======================================================================================================");        
        System.out.println(
        " ,ggggggggggg,                                               gg\n" +
        "dP\"\"\"88\"\"\"\"\"\"Y8, ,dPYb,                       ,dPYb,        dP8,                           ,dPYb,\n" +
        "Yb,  88      `8b IP'`Yb                       IP'`Yb       dP Yb                           IP'`Yb\n" +
        " `\"  88      ,8P I8  8I                       I8  8I      ,8  `8,                          I8  8I\n" +
        "     88aaaad8P\"  I8  8'                       I8  8bgg,   I8   Yb                          I8  8bgg,\n" +
        "     88\"\"\"\"Y8ba  I8 dP    ,gggg,gg    ,gggg,  I8 dP\" \"8   `8b, `8,     ,gggg,gg    ,gggg,  I8 dP\" \"8\n" +
        "     88      `8b I8dP    dP\"  \"Y8I   dP\"  \"Yb I8d8bggP\"    `\"Y88888   dP\"  \"Y8I   dP\"  \"Yb I8d8bggP\"\n" +
        "     88      ,8P I8P    i8'    ,8I  i8'       I8P' \"Yb,        \"Y8   i8'    ,8I  i8'       I8P' \"Yb,\n" +
        "     88_____,d8',d8b,_ ,d8,   ,d8b,,d8,_    _,d8    `Yb,        ,88,,d8,   ,d8b,,d8,_    _,d8    `Yb,\n" +
        "    88888888P\"  8P'\"Y88P\"Y8888P\"`Y8P\"\"Y8888PP88P      Y8    ,ad88888P\"Y8888P\"`Y8P\"\"Y8888PP88P      Y8\n" +
        " =========================================================,dP\"'   Yb===================================\n" +
        " ========================================================,8'      I8===================================\n" +
        "                                                        ,8'       I8\n" +
        "                                                        I8,      ,8'\n" +
        "                                                        `Y8,___,d8'\n" +
        "                                                          \"Y888P\"");

        //カードシャッフル済みの山札(deck)を作成
        Deck deck = new Deck();
        deck.shuffle();
        for(Card card:deck.cardsInDeck) {
        	//card.printCard();
        }
        
        boolean continueFlag = true;
        
        while(continueFlag) {
	        PlayerHand playerHand = new PlayerHand();
	        DealerHand dealerHand = new DealerHand();
	        
	        //開始時処理
	        playerHand.hit(deck);
	        dealerHand.hit(deck);
	        playerHand.hit(deck);
	        dealerHand.hit(deck);
	        
	        System.out.println("");
	        System.out.println("");
	        System.out.println("ゲームを始めます！");
	        System.out.println("");
	        
	        dealerHand.showLimitedHand();
		    System.out.println("");
	        playerHand.showHand();
	        
	        int dealerScore = dealerHand.countScore();
	        int playerScore = playerHand.countScore();
	        
	        //プレイヤーの行動分岐
	        while(true) {
		        System.out.println("追加でカードを引きますか？y/n");
		        String hitInput = new java.util.Scanner(System.in).nextLine();
		        if(hitInput.equalsIgnoreCase("y")) {
		        	playerHand.hit(deck);
		        	playerHand.showHand();
		        }else if(hitInput.equalsIgnoreCase("n")) {
		        	break;
		        }
		        playerScore = playerHand.countScore();//ださいかも
		        if (playerScore>21) {
		        	System.out.println("BUST!");
		        	break;
		        }
	        }
	        
	        //ディーラーの行動分岐
	        while(dealerScore<=16) {
	    		dealerHand.hit(deck);
	        	dealerScore = dealerHand.countScore();
		        if (dealerScore>21) {
		        	System.out.println("ディーラー BUST!");
		        	break;
		        }
	        }
	
	        //勝敗判定ロジック
		    System.out.println("===ディーラーの手札...===");
	        dealerHand.showHand();
	        System.out.println("===================");
	        
	        System.out.println("");
	       	System.out.println("ディーラー:"+dealerScore);
	       	System.out.println("プレイヤー:"+playerScore);
	        System.out.println("");
	        
	        if (playerScore > 21) {
	        	System.out.println("dealer wins!");
	        }else if(dealerScore > 21) {
	        	System.out.println("player wins!");
	        }else if(playerScore == dealerScore) {
	        	System.out.println("draw!");
	        }else if(playerScore < dealerScore){
	        	System.out.println("dealer wins!");
	        }else if(playerScore > dealerScore){
	        	System.out.println("player wins!");
	        }else {
	        	System.out.println("想定外の事態！");
	        }
	        
	        //続行処理
        	System.out.println("ゲームを続けますか!y/n");
	        String continueInput = new java.util.Scanner(System.in).nextLine();
	        if(continueInput.equalsIgnoreCase("y")) {
	        }else if(continueInput.equalsIgnoreCase("n")) {
	        	System.out.println("ゲームを終了します!");
	        	continueFlag =false;
	        	break;
	        }
        }
	}
}
