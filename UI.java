import java.util.Scanner;

public class UI {
	void print() {
	       System.out.println();
		}
		
	void print(String Message) {
        System.out.println(Message);
	}
		
	void showTitle(){
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
	}
	
	void showStartMessage(){
        print();
        print();
        print("ゲームを始めます！");
        print();
	}
	
    void showHand(Hand hand,String member) {
        print("==="+member+"の手札...===");
        for(Card eachcards:hand.cardsInHand) {
        	eachcards.printCard();
        }
        System.out.println("===================");
        print();
    };
	
	void showPlayerHand(Hand hand) {
        showHand(hand,"あなた");
        print();
}
	
	void showDealerHand(Hand hand) {
        showHand(hand,"ディーラー");
        print();
	}
	
	void showDealerHandLimited(Hand hand) {
	    System.out.println("===ディーラーの手札...===");
	    hand.cardsInHand.get(0).printCard();
	    for(int i=1;i<hand.cardsInHand.size();i++) {
	    	System.out.println("suit:?,rank:?,value?");
	    }
        System.out.println("===================");
	    print();
	}
	
    void showTurnResult(Hand playerHand,Hand dealerHand,String result,Chip chip) {
    	int playerScore=playerHand.countScore();
    	int dealerScore=dealerHand.countScore();
    	
        showDealerHand(dealerHand);
        
       	print("ディーラー:"+dealerScore);
       	print("プレイヤー:"+playerScore);
        print();
    	if(playerHand.isBust())showBust("プレイヤー");
    	if(dealerHand.isBust())showBust("ディーラー");
        print();
        print("結果："+result);
        print("チップ："+chip.chipCount);
        print();
    }
	
	boolean askYN(Scanner scanner,String message) {
        print(message);
        
        while(true) {
            String playerInput = scanner.nextLine();

	        if(playerInput.equalsIgnoreCase("y")) {
	        	return true;
	        }
	        else if(playerInput.equalsIgnoreCase("n")) {
	        	return  false;
	        }
	        else {
	        	print("y/nで回答してください");
	        }
        }
	}
	void showBust(String who) {
    	print(who+"BUST!");
	}
}
