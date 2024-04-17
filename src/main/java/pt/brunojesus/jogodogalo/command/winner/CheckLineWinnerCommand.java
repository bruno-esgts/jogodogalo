package pt.brunojesus.jogodogalo.command.winner;

import pt.brunojesus.jogodogalo.Board;
import pt.brunojesus.jogodogalo.BoardItemEnum;

public class CheckLineWinnerCommand implements CheckWinnerCommand {

	@Override
	public boolean check(int x, int y, Board board) {
		BoardItemEnum item = board.getItemInPosition(0, y);
		if (item == null) {
			return false;
		}
		
		for (int i = 1; i < board.getSize(); i++) {
			if (board.getItemInPosition(i, y) != item) {
				return false;
			}
		}
				
		return true;
	}

}
