package pt.brunojesus.jogodogalo.validator.play;

import pt.brunojesus.jogodogalo.Board;
import pt.brunojesus.jogodogalo.BoardItemEnum;
import pt.brunojesus.jogodogalo.exception.IllegalBoardPositionException;
import pt.brunojesus.jogodogalo.exception.IllegalPlayException;

public class BoardPositionValidator implements PlayValidator {

	@Override
	public IllegalPlayException validatePlay(int x, int y, BoardItemEnum item, Board board) {
		if (x >= board.getSize() || y >= board.getSize() || x < 0 || y < 0) {
			return new IllegalBoardPositionException("Illegal position");
		}
		
		return null;
	}

}
