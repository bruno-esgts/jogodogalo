package pt.brunojesus.jogodogalo.command.winner;

import pt.brunojesus.jogodogalo.Board;
import pt.brunojesus.jogodogalo.BoardItemEnum;

public class CheckDiagonalWinnerCommand implements CheckWinnerCommand {

	@Override
	public boolean check(int x, int y, Board board) {
		if (x != y) {
			return false;
		}

		BoardItemEnum item = board.getItemInPosition(0, 0);
		if (item == null) {
			return false;
		}
		
		for (int i = 1; i < board.getSize(); i++) {
			if (board.getItemInPosition(i, i) != item) {
				return false;
			}
		}
		
		return true;
	}

}
