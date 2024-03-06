package pt.brunojesus.jogodogalo;

public enum BoardItemEnum {
	CROSS("X"), CIRCLE("O");
	
	private String symbol;
	
	private BoardItemEnum(String symbol) {
		this.symbol = symbol;
	}
	
	public String getSymbol() {
		return this.symbol;
	}
}
