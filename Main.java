public class Main {

	public static void main(String[] args) {
        
		boolean gameContinueFlag = true;
		boolean roundContinueFlag = true;
        GameManager gameManager = new GameManager();

		UI ui = new UI();

        while(gameContinueFlag) {
	    	// === ゲーム開始メッセージ ===
			ui.showTitle();
	        ui.showStartMessage();

	        Chip chip = new Chip();
            Deck deck = new Deck();
            deck.shuffle();
	        
	        do{		
	            // === 手札の準備 ===           y
				ui.showLoading();
		        Hand playerHand = new Hand();
		        Hand dealerHand = new Hand();
		        gameManager.setUpHands(deck, playerHand, dealerHand);
		        
		        // === 初期手札の表示 ===
		        ui.showDealerHandLimited(dealerHand);	        		    
		        ui.showPlayerHand(playerHand);
		        
		        // === プレイヤー/ディーラーのターン ===
		    	ui.showLoading();
		        gameManager.playerTurn(deck, playerHand);
		        gameManager.dealerTurn(dealerHand, deck);
		
		        // === 勝敗判定・状態更新 ===
		        gameManager.applyResult(playerHand,dealerHand,chip);
		        
		        // === 続行処理 ===
		        roundContinueFlag = gameManager.askNextRound(chip);
	        }while(roundContinueFlag) ;
	        
	    	ui.endGame(chip);
	    	gameContinueFlag = gameManager.askNextGame();
        }
	}
}
