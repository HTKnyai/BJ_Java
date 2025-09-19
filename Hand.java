import java.util.ArrayList;

public class Hand{
	ArrayList<Card> cardsInHand = new ArrayList<>();
	
	boolean isBust() {
		return countScore()>21;
	}
	
	void hit(Deck deck){
		Card newcard = deck.draw();
        if (newcard != null) {
            cardsInHand.add(newcard);
            //newcard.printCard();
        }
	};
		
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
}
