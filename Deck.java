import java.util.ArrayList;
import java.util.Collections;

class Deck{
	ArrayList<Card> cardsInDeck = new ArrayList<>();
    String[] suits = {"♠", "♥", "♦", "♣"};
    String[] ranks = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    Integer[] values   = {1,2,3,4,5,6,7,8,9,10,10,10,10};
    
    Deck(){
    	refill();
    }
    
    void refill() {
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
        //山札が１デッキの以下なら１デッキ分補充する
    	boolean needsRefill= cardsInDeck.size() <= 52/2;
        if(needsRefill) {
        	refill();
        	shuffle();
        	System.out.println("山札を補充　残り："+cardsInDeck.size()+"枚");
        }
        if (cardsInDeck.isEmpty()) {
        	System.out.println("エラー：山札がなくなりました！");
        	return null;
        }
        
        Card topcard = cardsInDeck.get(0);
        cardsInDeck.remove(0);
        return topcard;
    }
}
