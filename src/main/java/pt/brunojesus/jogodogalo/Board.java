package pt.brunojesus.jogodogalo;


public class Board {

	private BoardItemEnum[][] board;
	private Integer size;
	private BoardItemEnum lastPlayedItem = null;

	public Board(int size) {
		this.size = size;
		this.board = new BoardItemEnum[size][size];
	}

	public void play(int x, int y) throws IllegalPlayException {
		final BoardItemEnum item = this.lastPlayedItem == BoardItemEnum.CROSS ? 
				BoardItemEnum.CIRCLE : BoardItemEnum.CROSS;
		play(x, y, item);
	}

	public void play(int x, int y, BoardItemEnum item) throws IllegalPlayException {
		if (x >= this.size || y >= this.size || x < 0 || y < 0) {
			throw new IllegalBoardPositionException("Illegal position");
		}
		if (this.lastPlayedItem == item) {
			throw new ConsecutivePlayException("Cannot play two times in a row");
		}
		if (this.board[y][x] != null) {
			throw new PositionAlreadyInUseException("This place already has an item");
		}
		this.board[y][x] = item;
		this.lastPlayedItem = item;
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
