import java.util.Scanner;

public class GameManager {
    Scanner scanner = new java.util.Scanner(System.in);
	UI ui = new UI();
    
	void setUpHands(Deck deck, Hand playerHand,Hand dealerHand) {
        playerHand.hit(deck);
        dealerHand.hit(deck);
        playerHand.hit(deck);
        dealerHand.hit(deck);
	}
    
    enum Result {DEALER_WINS,PLAYER_WINS,DRAW}
    
    Result judge(Hand playerHand,Hand dealerHand) {
    	int playerScore=playerHand.countScore();
    	int dealerScore=dealerHand.countScore();
    	
        if (playerScore > 21) return Result.DEALER_WINS;
        else if(dealerScore > 21) return Result.PLAYER_WINS;
        else if(playerScore == dealerScore) return Result.DRAW;
        else if(playerScore < dealerScore) return Result.DEALER_WINS;
        else if(playerScore > dealerScore) return Result.PLAYER_WINS;
        else return Result.DRAW;
    }
    
    void applyResult(Hand playerHand,Hand dealerHand,Chip chip) {
    	Result result = judge(playerHand,dealerHand);
    		
        boolean isDealerBJ = dealerHand.countScore() == 21;
        boolean isPlayerBJ = playerHand.countScore() == 21;
    	
        switch(result) {
        	case DRAW:
        		break;
        	case DEALER_WINS:
        		chip.removeChip(isDealerBJ);
        		break;
        	case PLAYER_WINS:
        		chip.addChip(isPlayerBJ);
        		break;
        }
        ui.showTurnResult(playerHand, dealerHand,result.toString(),chip);
    }
    
    void playerTurn(Deck deck,Hand playerHand) {
        while(true) {
	        boolean hit = ui.askYN(scanner, "追加でカードを引きますか？y/n");
	        if(!hit)return;
	        playerHand.hit(deck);
	        ui.showPlayerHand(playerHand);     

	       	if (playerHand.isBust()) break;
        }
    }
    
    void dealerTurn(Hand dealerHand,Deck deck) {
    	int dealerScore=dealerHand.countScore();

        while(dealerScore<=16) {
    		dealerHand.hit(deck);
        	dealerScore = dealerHand.countScore();
        	
	        if (dealerHand.isBust()) break;
        }
    }
    
    /*
    void takeTurn(Deck deck,Hand hand,String who) {
    	int handScore = hand.countScore();
    	
    	if(who.equals("Player")) {
	        boolean hit = ui.askYN(scanner, "追加でカードを引きますか？");
	        if(!hit)return;
	        hand.hit(deck);
	        ui.showPlayerHand(hand);
	       	if (hand.isBust()) {
	        	ui.showBust("プレイヤー");
	        }
    	}
    	else {
    		
    	}
    }
    */
    
    boolean hasChipsRemain(Chip chip) {
    	boolean chipRemain = chip.chipCount > 0;
        if(!chipRemain) ui.print("GAMEOVER!!");
    	return chipRemain;
    }
    
    boolean askNextRound() {
    	System.out.println("ゲームを続けますか!y/n");
        while(true) {
	        String continueInput = scanner.nextLine();
	        if(continueInput.equalsIgnoreCase("y")) {
	        	return true;
	        }else if(continueInput.equalsIgnoreCase("n")) {
	        	return false;
	        }else {
	        	System.out.println("y/nで入力してください。");
	        }
        }
    }    
}
