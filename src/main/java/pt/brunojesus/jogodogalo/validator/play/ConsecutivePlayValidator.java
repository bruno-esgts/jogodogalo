package pt.brunojesus.jogodogalo.validator.play;

import pt.brunojesus.jogodogalo.Board;
import pt.brunojesus.jogodogalo.BoardItemEnum;
import pt.brunojesus.jogodogalo.exception.ConsecutivePlayException;
import pt.brunojesus.jogodogalo.exception.IllegalPlayException;

public class ConsecutivePlayValidator implements PlayValidator {

	@Override
	public IllegalPlayException validatePlay(int x, int y, BoardItemEnum item, Board board) {
		if (board.getLastPlayedItem() == item) {
			return new ConsecutivePlayException("Cannot play two times in a row");
		}
		
		return null;
	}

}
