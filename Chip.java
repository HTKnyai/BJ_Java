class Chip{
    public static final int INITIAL_CHIP_COUNT = 50;
    public static final int CHIP_FLUCTUATION = 10;
    public static final int CHIP_FLUCTUATION_BJ = 15;
    
	int chipCount=0;	

	Chip(){
		chipCount = INITIAL_CHIP_COUNT;
	}
	
	void addChip(boolean BJflag) { //addとremoveはまとめてもいいかも
		if(BJflag) {
			chipCount+=CHIP_FLUCTUATION_BJ;
		}else {
			chipCount+=CHIP_FLUCTUATION;
		}
	}
	
	void removeChip(boolean BJflag) {
		if(BJflag) {
			chipCount-=CHIP_FLUCTUATION_BJ;
		}else {
			chipCount-=CHIP_FLUCTUATION;
		}
	}
	
	void showChipCount() {
		System.out.println("チップ："+chipCount);
	}
}   
