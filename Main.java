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
		        System.out.println("suit:"+suit + ",rank:"+rank + ",value"+value);
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
		
		//カードシャッフル済みの山札(deck)を作成
        Deck deck = new Deck();
        deck.shuffle();
        for(Card card:deck.cardsInDeck) {
        	card.printCard();
        }
        
        class Hand{
        	ArrayList<Card> cardsInHand = new ArrayList<>();
        	
        	void hit(Deck deck){
        		Card newcard = deck.draw();
                if (newcard != null) {
                    cardsInHand.add(newcard);
                    newcard.printCard();   // 追加したカードを表示（後で消す）
                }
        	};
        	
        	void stand(){};
        	
            int countScore() {
            	int score=0;
            	for(Card card:cardsInHand) {
            		score += card.value;
            	}
            	return score;
            }
    	}
        
        class PlayerHand extends Hand{
        	
        }
        class DealerHand extends Hand{
        	
        }
	}
}
