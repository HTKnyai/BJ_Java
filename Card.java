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
        System.out.println("suit:"+suit + ",rank:"+rank);
    }
}
