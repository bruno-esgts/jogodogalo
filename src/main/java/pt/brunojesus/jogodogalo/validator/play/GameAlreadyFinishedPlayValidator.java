package pt.brunojesus.jogodogalo.validator.play;

import pt.brunojesus.jogodogalo.Board;
import pt.brunojesus.jogodogalo.BoardItemEnum;
import pt.brunojesus.jogodogalo.exception.GameAlreadyFinishedException;
import pt.brunojesus.jogodogalo.exception.IllegalPlayException;


public class GameAlreadyFinishedPlayValidator implements PlayValidator {

	@Override
	public IllegalPlayException validatePlay(int x, int y, BoardItemEnum item, Board board) {
		if (board.getWinner() != null) {
			return new GameAlreadyFinishedException(
					"There's already a winner: " + board.getWinner().getSymbol()
			);
		}
		return null;
	}

}
