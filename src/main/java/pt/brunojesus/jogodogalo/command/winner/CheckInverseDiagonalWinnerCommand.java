package pt.brunojesus.jogodogalo.command.winner;

import pt.brunojesus.jogodogalo.Board;
import pt.brunojesus.jogodogalo.BoardItemEnum;

public class CheckInverseDiagonalWinnerCommand implements CheckWinnerCommand {

	@Override
	public boolean check(int x, int y, Board board) {
		if (x + y != board.getSize() - 1) {
			return false;
		}
		
		BoardItemEnum item = board.getItemInPosition(
				board.getSize() - 1, 
				0
		);
		
		if (item == null) {
			return false;
		}
		
		for (int i = 1; i < board.getSize(); i++) {
			if (board.getItemInPosition(board.getSize() - 1 - i, i) != item) {
				return false;
			}
		}
		
		return true;
	}

}
