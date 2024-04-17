package pt.brunojesus.jogodogalo;

import pt.brunojesus.jogodogalo.gui.IBoard;

public class BoardAdapter implements IBoard {
	
	Board board;
	
	public BoardAdapter() {
		this.board = new Board(3);
	}

	@Override
	public String getItemInPosition(int x, int y) {
		BoardItemEnum boardItem = board.getItemInPosition(x, y);
		
		if (boardItem != null) {
			return boardItem.getSymbol();
		}
		
		return null;
	}

	@Override
	public String getWinner() {
		BoardItemEnum boardItem = board.getWinner();
		
		if (boardItem != null) {
			return boardItem.getSymbol();
		}
		
		return null;
	}

	@Override
	public void play(int x, int y) throws Exception {
		this.board.play(x, y);
	}

	@Override
	public void reset() {
		this.board = new Board(3);
	}

}
