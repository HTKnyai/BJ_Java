import java.util.Random;
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
        print("ゲームを始めます");
        print();
		dealerTalk("startGame");

	}
	
    void showHand(Hand hand,String member) {
        print("=="+member+"の手札==");
        for(Card eachcards:hand.cardsInHand) {
        	//System.out.print("   ");
        	eachcards.printCard();
        }
        print("====================");
        print();
    };
	
	void showPlayerHand(Hand hand) {
        showHand(hand,"プレイヤー");
        print();
}
	
	void showDealerHand(Hand hand) {
        showHand(hand,"ディーラー");
        if(hand.isBust()) {
    		dealerTalk("dealerBust");
        }
        print();
	}
	
	void showDealerHandLimited(Hand hand) {
	    print("==ディーラーの手札==");
    	//System.out.print("   ");
	    hand.cardsInHand.get(0).printCard();
	    for(int i=1;i<hand.cardsInHand.size();i++) {
        	//System.out.print("   ");
	    	print("suit:?,rank:?");
	    }
        print("====================");
	    print();
	}
	
    void showTurnResult(Hand playerHand,Hand dealerHand,String result,Chip chip) {
    	int playerScore=playerHand.countScore();
    	int dealerScore=dealerHand.countScore();
    	
        showDealerHand(dealerHand);
        

       	print("ディーラー:"+dealerScore);
       	print("プレイヤー:"+playerScore);
    	if(playerHand.isBust())showBust("プレイヤー");
    	if(dealerHand.isBust())showBust("ディーラー");
        print();
        print("結果："+result);
        print("チップ："+chip.chipCount);
        showLoading(300);
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
	
	void showBustEffect() {
	    print(
	        "██████╗ ██╗   ██╗███████╗████████╗██╗██╗\n" +
	        "██╔══██╗██║   ██║██╔════╝╚══██╔══╝██║██║\n" +
	        "██████╔╝██║   ██║███████╗   ██║   ██║██║\n" +
	        "██╔══██╗██║   ██║╚════██║   ██║   ╚═╝╚═╝\n" +
	        "██████╔╝╚██████╔╝███████║   ██║   ██╗██╗\n" +
	        "╚═════╝  ╚═════╝ ╚══════╝   ╚═╝   ╚═╝╚═╝\n"
	    );
	}
	
	void showLoading() {
	    showLoading(500);
	}
	
	void showLoading(int delayTime) {
		delayTime = Math.max(delayTime, 1);//0入力時の例外対策
		for(int i=0;i<10;i++) {
			System.out.print("◆");
			try {
				Thread.sleep(delayTime/10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		print();
	}
	
	Random rand = new Random();
	
	void dealerTalk(String Situation) {
		String lines[]=null;
	    switch(Situation) {
	        case "startGame":
	            lines = new String[] {
	                "どうぞお手柔らかに...",
	                "準備はよろしいですかな？",
	                "運試しと参りましょう...",
	                "まずは軽く肩慣らしですな",
	                "落ち着いて参りましょう"
	            };
	            break;
	        case "playerTurn":
	            lines = new String[] {
	                "どうされますかな...",
	                "ヒット？それともステイか...",
	                "ほう……",
	                "慎重に参りましょうか…",
	                "焦らずに判断されよ"
	            };
	            break;
	        case "playerHit":
	            lines = new String[] {
	                "承知しました...",
	                "ほほう...",
	                "左様で",
	                "ええ...",
	                "ふむ、良い選択かも知れませんな"
	            };
	            break;
	        case "dealerTurn":
	            lines = new String[] {
	                "ふむ…",
	                "では…",
	                "私も参りますぞ…",
	                "少々手加減はせぬといけませんな",
	                "じっくり行きますぞ"
	            };
	            break;
	        case "playerWin":
	            lines = new String[] {
	                "うむ、見事な腕前。感心しましたな",
	                "お見事、まだまだ油断は禁物ですぞ",
	                "フフフ…見事な勝利ですな",
	                "よくやられましたな",
	                "勘が冴えておりますな",
	            };
	            break;
	        case "dealerWin":
	            lines = new String[] {
	                "ほほう……運が味方しておるようです",
	                "ほほほ、運も実力のうち……",
	                "勝負とは最後までわからぬものですな",
	                "フフフ…残念でしたな",
	                "次に期待しましょう",
	            };
	            break;
	        case "draw":
	            lines = new String[] {
	                "まだまだ楽しめそうですな、フフフ……",
	                "なかなか拮抗しておりますな",
	                "フフフ、良い勝負でしたぞ",
	                "油断はできませんな…",
	                "これぞ運と実力のせめぎ合いですな"
	            };
	            break;
	        case "playerBust":
	            lines = new String[] {
	                "勇気ある選択でしたな",
	                "これはこれは...",
	                "惜しい結果ですな",
	                "焦りは禁物ですぞ",
	                "次は落ち着いて参りましょう",
	                "フフフ、まだ望みはありますぞ"
	            };
	            break;
	        case "dealerBust":
	            lines = new String[] {
	                "おおっと…これはこれは……",
	                "少々熱が入りすぎましたかな…？",
	                "うむ、やはり油断は禁物ですな",
	            };
	            break;
	        case "endGame":
	            lines = new String[] {
	                "また、お待ちしておりますぞ…",
	                "それではまた…",
	                "楽しませていただきました",
	                "またのお越しを",
	                "ゆっくり休んでくださいませ"
	            };
	            break;
	        case "endOver100":
	        	lines = new String[] {
	        		"これはこれは... 見事というほかありませんな",
	        		"あっぱれにございます",
	        		"...素晴らしいものをお持ちのようですな"
	        	};
	        	break;

	    }
		print("「"+lines[rand.nextInt(lines.length)]+"」");
		print("");
	}
	
	void endGame(Chip chip) {
		print("今回のゲームを終了します!");
		if(chip.chipCount>=100) {
			dealerTalk("endOver100");
		}
    	dealerTalk("endGame");
	}
}
