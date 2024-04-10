package pt.brunojesus.jogodogalo.command.winner;

import pt.brunojesus.jogodogalo.Board;
import pt.brunojesus.jogodogalo.BoardItemEnum;

public class CheckColumnWinnerCommand implements CheckWinnerCommand {

	@Override
	public boolean check(int x, int y, Board board) {
		BoardItemEnum item = board.getItemInPosition(x, 0);
		if (item == null) {
			return false;
		}
		
		for (int i = 1; i < board.getSize(); i++) {
			if (board.getItemInPosition(x, i) != item) {
				return false;
			}
		}
		
		return true;
	}

}
