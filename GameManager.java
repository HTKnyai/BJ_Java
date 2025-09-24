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
        boolean isDoubleDown = playerHand.isDoubleDown;

        String dealerLine="";
    	
        switch(result) {
        	case DRAW:
        		dealerLine="draw";
        		break;
        	case DEALER_WINS:
        		dealerLine="dealerWin";
        		chip.removeChip(isDealerBJ,isDoubleDown);
        		break;
        	case PLAYER_WINS:
        		dealerLine="playerWin";
        		chip.addChip(isPlayerBJ,isDoubleDown);
        		break;
        }
        ui.showTurnResult(playerHand, dealerHand,result.toString(),chip);
        ui.dealerTalk(dealerLine);
    }
    
    void playerTurn(Deck deck,Hand playerHand) {
    	ui.dealerTalk("playerTurn");
        boolean doubleDown = ui.askYN(scanner,"ダブルダウンを行いますか？y/n");
        if(doubleDown) {
        	playerHitOnce(deck, playerHand);
        	playerHand.isDoubleDown = true;
            return;
        }  	
      
        while(!playerHand.isBust()){
            boolean hit = ui.askYN(scanner, "追加でカードを引きますか？y/n");
            if(!hit) return;

            playerHitOnce(deck, playerHand);
        }
    }
    
    void playerHitOnce(Deck deck,Hand playerHand) {
        ui.dealerTalk("playerHit");
        ui.showLoading();
        playerHand.hit(deck);
        ui.showPlayerHand(playerHand);
        if(playerHand.isBust()) {
            ui.showBustEffect();
            ui.dealerTalk("playerBust");
        }
    }
    
    void dealerTurn(Hand dealerHand,Deck deck) {
    	ui.dealerTalk("dealerTurn");
    	ui.showLoading(800);
    	while(true) {
	    	if((dealerHand.isBust())) {
	    		break;
	    	}else if(dealerHand.countScore()<=16) {
	    		dealerHand.hit(deck);        	        
	    	}else {
	    		break;
	    	}
		}	
    }
    
    boolean askNextRound(Chip chip){
    	if(chip.chipCount<=0) {
    		ui.print("GAME OVER!!");
    		return false;
    	}else {
        	return ui.askYN(scanner, "次のラウンドに進みますか？y/n");

    	}
    }
    
    boolean askNextGame() {
    	ui.showLoading();
    	return ui.askYN(scanner, "新しいゲームを開始しますか？y/n");
    }
}
