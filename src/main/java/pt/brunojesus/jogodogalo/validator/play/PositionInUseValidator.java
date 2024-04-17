package pt.brunojesus.jogodogalo.validator.play;

import pt.brunojesus.jogodogalo.Board;
import pt.brunojesus.jogodogalo.BoardItemEnum;
import pt.brunojesus.jogodogalo.exception.IllegalPlayException;
import pt.brunojesus.jogodogalo.exception.PositionAlreadyInUseException;

public class PositionInUseValidator implements PlayValidator {

	@Override
	public IllegalPlayException validatePlay(int x, int y, BoardItemEnum item, Board board) {
		if (board.getItemInPosition(x, y) != null) {
			return new PositionAlreadyInUseException("This place already has an item");
		}
		return null;
	}

}
