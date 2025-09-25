import java.util.ArrayList;
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
	
	void refillDeck(Deck deck) {
		print("山札を補充　残り："+deck.cardsInDeck.size()+"枚");
	}
	
	/* 文字形式のトランプ表示
    void showHand(Hand hand,String member) {
        print("=="+member+"の手札==");
        for(Card eachcards:hand.cardsInHand) {
        	//System.out.print("   ");
        	eachcards.printCard();
        }
        print("====================");
        print();
    };
    */
    
    void printCards(ArrayList<Card> cards, boolean hidden) {
        StringBuilder line1 = new StringBuilder();
        StringBuilder line2 = new StringBuilder();
        StringBuilder line3 = new StringBuilder();
        StringBuilder line4 = new StringBuilder();

        for(int i=0;i<cards.size();i++) {
        	
        	if(i==1 && hidden==true) {
	            line1.append("┌─┐");
	            line2.append("│? │");
	            line3.append("│ ?│");
	            line4.append("└─┘");	
        	}else {      	
	        	String rank = cards.get(i).rank;
	        	String suit = cards.get(i).suit;
	        	
	        	String rank_fixed=rank+(rank.length()==2?"":" ");
	        	
	            line1.append("┌─┐");
	            line2.append("│").append(rank_fixed).append("│");
	            line3.append("│ ").append(suit).append("│");
	            line4.append("└─┘");	
        	}
        }
        
        print(line1.toString());
        print(line2.toString());
        print(line3.toString());
        print(line4.toString());
    }
	
	void showPlayerHand(Hand hand) {
	    print("==プレイヤーの手札==");
        printCards(hand.cardsInHand,false);
        print("====================");
        print();
	}
	
	
	void showDealerHand(Hand hand) {
	    print("==ディーラーの手札==");
        printCards(hand.cardsInHand,false);
        print("====================");
        if(hand.isBust()) {
    		dealerTalk("dealerBust");
        }
        print();
	}
	
	void showDealerHandLimited(Hand hand) {
	    print("==ディーラーの手札==");
        printCards(hand.cardsInHand,true);
        print("====================");
	    print();
	}
	
    void showTurnResult(Hand playerHand,Hand dealerHand,String result,Chip chip, int chipDelta) {
    	int playerScore=playerHand.countScore();
    	int dealerScore=dealerHand.countScore();
    	
        showDealerHand(dealerHand);

       	print("ディーラー:"+dealerScore);
       	print("プレイヤー:"+playerScore);
    	if(playerHand.isBust())showBust("プレイヤー");
    	if(dealerHand.isBust())showBust("ディーラー");
        print();
        print("結果："+result);
        
        String sign = result.equals("DEALER_WINS") ? "-":"+";        
        print("チップ変動: " + sign + chipDelta);
        print("現在のチップ："+chip.chipCount);
        showLoading(300);
        print();
    }
    
    int askBet(Scanner scanner, Chip chip) {
    	print("賭けるチップの枚数を入力してください（10～"+chip.chipCount+"枚）");
    	int betAmount=0;
    	
        while(true) {
        	try {
        		betAmount = Integer.parseInt(scanner.nextLine());
        		if(10<=betAmount && betAmount<=chip.chipCount) {
        			break;
        		}else {
        			print("10枚以上"+chip.chipCount+"枚以下の整数を入力してください");
        		}
        	}catch(NumberFormatException e) {
        		print("正しい数値を入力してください。");
        	}      	
	        
        }
        
        return betAmount;
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
	
	void showBJ() {
		print(
			"██████╗      ██╗██╗██╗\n"+
			"██╔══██╗     ██║██║██║\n"+
			"██████╔╝     ██║██║██║\n"+
			"██╔══██╗██   ██║╚═╝╚═╝\n"+
			"██████╔╝╚█████╔╝██╗██╗\n"+
			"╚═════╝  ╚════╝ ╚═╝╚═╝\n"                     
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
	
	void showOver1000() {
		print(
			" █████╗ ███╗   ███╗ █████╗ ███████╗██╗███╗   ██╗ ██████╗ ██╗██╗\n"+
			"██╔══██╗████╗ ████║██╔══██╗╚══███╔╝██║████╗  ██║██╔════╝ ██║██║\n"+
			"███████║██╔████╔██║███████║  ███╔╝ ██║██╔██╗ ██║██║  ███╗██║██║\n"+
			"██╔══██║██║╚██╔╝██║██╔══██║ ███╔╝  ██║██║╚██╗██║██║   ██║╚═╝╚═╝\n"+
			"██║  ██║██║ ╚═╝ ██║██║  ██║███████╗██║██║ ╚████║╚██████╔╝██╗██╗\n"+
			"╚═╝  ╚═╝╚═╝     ╚═╝╚═╝  ╚═╝╚══════╝╚═╝╚═╝  ╚═══╝ ╚═════╝ ╚═╝╚═╝\n"	
		);
		
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
	        case "naturalBJ":
	            lines = new String[] {
	                "おや... これはこれは",
	            };
	            break;
	        case "playerTurn":
	            lines = new String[] {
	                "どうされますかな...",
	                "ほほう...",
	                "ほう......",
	                "慎重に参りましょうか...",
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
	                "ふむ...",
	                "では...",
	                "私も参りますぞ...",
	                "少々手加減はせぬといけませんな",
	                "じっくり行きますぞ"
	            };
	            break;
	        case "playerWin":
	            lines = new String[] {
	                "うむ、見事な腕前。感心しましたな",
	                "お見事、まだまだ油断は禁物ですぞ",
	                "フフフ...見事な勝利ですな",
	                "よくやられましたな",
	                "勘が冴えておりますな",
	            };
	            break;
	        case "dealerWin":
	            lines = new String[] {
	                "ほほう......運が味方しておるようです",
	                "ほほほ、運も実力のうち......",
	                "勝負とは最後までわからぬものですな",
	                "フフフ...残念でしたな",
	                "次に期待しましょう",
	            };
	            break;
	        case "draw":
	            lines = new String[] {
	                "まだまだ楽しめそうですな、フフフ......",
	                "なかなか拮抗しておりますな",
	                "フフフ、良い勝負でしたぞ",
	                "油断はできませんな...",
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
	                "おおっと...これはこれは......",
	                "少々熱が入りすぎましたかな...？",
	                "うむ、やはり油断は禁物ですな",
	            };
	            break;
	        case "endGame":
	            lines = new String[] {
	                "また、お待ちしておりますぞ...",
	                "それではまた...",
	                "楽しませていただきました",
	                "またのお越しを",
	                "ゆっくりお休みくださいませ"
	            };
	            break;
	        case "endOver100":
	        	lines = new String[] {
	        		"御見逸れしました",
	        		"お見事!",
	        	};
	        	break;
	        case "endOver1000":
	        	lines = new String[] {
	        		"これはこれは...",
	        		"あっぱれにございます",
	        		"...素晴らしいものをお持ちのようですな"
	        	};
	    }
		print("「"+lines[rand.nextInt(lines.length)]+"」");
		print("");
	}
	
	void endGame(Chip chip) {
		print("今回のゲームを終了します!");
		if(chip.chipCount>=1000) {
			print();
			showOver1000();
			dealerTalk("endOver1000");
		}
		else if(chip.chipCount>=100) {
			dealerTalk("endOver100");
		}
    	dealerTalk("endGame");
	}
}
