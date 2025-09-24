class Chip{
    public static final int INITIAL_CHIP_COUNT = 50;
    public static final int CHIP_FLUCTUATION_Base = 10;
    public static final double CHIP_FLUCTUATION_adj_BJ = 1.5;
    public static final double CHIP_FLUCTUATION_adj_DD = 2;
    
	int chipCount=0;
	
	Chip(){
		chipCount = INITIAL_CHIP_COUNT;
	}
	
	int calculateDelta(boolean isBJ, boolean isDD) {
		double multiplier = 1.0;
		if (isBJ) multiplier *= CHIP_FLUCTUATION_adj_BJ;
		if (isDD) multiplier *= CHIP_FLUCTUATION_adj_DD;
		return (int) Math.round(CHIP_FLUCTUATION_Base * multiplier);
	}
	
	void addChip(boolean isBJ, boolean isDD) { //addとremoveはまとめてもいいかも
		int delta = calculateDelta(isBJ, isDD);
		chipCount += delta;	
	}
	
	void removeChip(boolean isBJ, boolean isDD) { //addとremoveはまとめてもいいかも
		int delta = calculateDelta(isBJ, isDD);
		chipCount -= delta;	
	}
	
	void showChipCount() {
		System.out.println("チップ："+chipCount);
	}
}   
