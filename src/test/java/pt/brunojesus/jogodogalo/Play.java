package pt.brunojesus.jogodogalo;

public class Play {

	private int x;
	private int y;
	
	public Play(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public static Play of(int x, int y) {
		return new Play(x, y);
	}
}
