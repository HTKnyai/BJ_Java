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
			ArrayList<Card> cards = new ArrayList<>();
	        String[] suits = {"♠", "♥", "♦", "♣"};
	        String[] ranks = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	        Integer[] values   = {1,2,3,4,5,6,7,8,9,10,10,10,10}; 
	        Deck(){
		        for (String suit : suits) {
		            for (int i=0; i<ranks.length; i++) {
		                cards.add(new Card(suit, ranks[i], values[i]));
		            }
		        }
	        }
	        void shuffle() {
	            Collections.shuffle(cards);
	        }
		}
		
		//カードシャッフル済みの山札(deck)を作成
        Deck deck = new Deck();
        deck.shuffle();
        for(Card card:deck.cards) {
        	card.printCard();
        }
        
        
	}
}
