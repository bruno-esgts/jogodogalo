package pt.brunojesus.jogodogalo;

public class Board {
	
	private BoardItemEnum[][] board;
	private Integer size;
	
	public Board(int size) {
		this.size = size;
		this.board = new BoardItemEnum[size][size];
	}
	
	public void play(int x, int y, BoardItemEnum item) {
		this.board[y][x] = item;
	}
	
	public void print() {
		for (int y = 0; y < this.size; y++) {
			for (int x = 0; x < this.size; x++) {
				String item = " ";
				if (this.board[y][x] != null) {
					item = this.board[y][x].getSymbol();
				}
				System.out.print(item);
			}
			System.out.println();
		}
	}
}
