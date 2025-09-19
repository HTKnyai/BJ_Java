public class Main {

	public static void main(String[] args) {
        
		boolean continueFlag = true;
        GameManager gameManager = new GameManager();

		UI ui = new UI();
        Chip chip = new Chip();
        
    	// === ゲーム開始メッセージ ===
		ui.showTitle();
        ui.showStartMessage();
        
        while(continueFlag) {		
            // === 山札と手札の準備 ===
            Deck deck = new Deck();
            deck.shuffle();
            
	        Hand playerHand = new Hand();
	        Hand dealerHand = new Hand();
	        gameManager.setUpHands(deck, playerHand, dealerHand);
	        
	        // === 初期手札の表示 ===
	        ui.showDealerHandLimited(dealerHand);	        		    
	        ui.showPlayerHand(playerHand);
	        
	        // === プレイヤー/ディーラーのターン ===
	        gameManager.playerTurn(deck, playerHand);
	        gameManager.dealerTurn(dealerHand, deck);
	
	        // === 勝敗判定・状態更新 ===
	        gameManager.applyResult(playerHand,dealerHand,chip);
	        
	        // === 続行処理 ===
	        continueFlag = gameManager.hasChipsRemain(chip) && gameManager.askNextRound();
        }
    	ui.print("ゲームを終了します!");
	}
}
