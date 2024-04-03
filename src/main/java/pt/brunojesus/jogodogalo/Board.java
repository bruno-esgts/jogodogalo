package pt.brunojesus.jogodogalo;


public class Board {

	private BoardItemEnum[][] board;
	private Integer size;
	private BoardItemEnum lastPlayedItem = null;
	private BoardItemEnum winner = null;

	public Board(int size) {
		this.size = size;
		this.board = new BoardItemEnum[size][size];
	}

	public void play(int x, int y) throws IllegalPlayException {
		final BoardItemEnum item = this.lastPlayedItem == BoardItemEnum.CROSS ? 
				BoardItemEnum.CIRCLE : BoardItemEnum.CROSS;
		
		play(x, y, item);
	}

	public BoardItemEnum play(int x, int y, BoardItemEnum item) throws IllegalPlayException {
		if (winner != null) {
			throw new GameAlreadyFinishedException("There's already a winner: " + winner.getSymbol());
		}
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
		
		if (
				checkLineWinner(y) ||
				checkColumnWinner(x) ||
				checkDiagonalWinner(x, y) ||
				checkInverseDiagonalWinner(x, y)
		) {
			return lastPlayedItem;
		}
		
		return null;
	}
	
	public BoardItemEnum getItemInPosition(int x, int y) {
		return this.board[y][x];
	}
	
	public boolean checkLineWinner(int y) {
		BoardItemEnum item = this.board[y][0];
		if (item == null) {
			return false;
		}
		
		for (int i = 1; i < this.size; i++) {
			if (this.board[y][i] != item) {
				return false;
			}
		}
				
		return true;
	}
	
	public boolean checkColumnWinner(int x) {
		BoardItemEnum item = this.board[0][x];
		if (item == null) {
			return false;
		}
		
		for (int i = 1; i < this.size; i++) {
			if (this.board[i][x] != item) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean checkDiagonalWinner(int x, int y) {
		if (x != y) {
			return false;
		}

		BoardItemEnum item = this.board[0][0];
		if (item == null) {
			return false;
		}
		
		for (int i = 1; i < this.size; i++) {
			if (this.board[i][i] != item) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean checkInverseDiagonalWinner(int x, int y) {
		if (x + y != this.size - 1) {
			return false;
		}
		
		BoardItemEnum item = this.board[0][this.size -1];
		if (item == null) {
			return false;
		}
		
		for (int i = 1; i < this.size; i++) {
			if (this.board[i][this.size - 1 - i] != item) {
				return false;
			}
		}
		
		return true;
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
