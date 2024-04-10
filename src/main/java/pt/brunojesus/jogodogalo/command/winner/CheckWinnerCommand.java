package pt.brunojesus.jogodogalo.command.winner;

import pt.brunojesus.jogodogalo.Board;

@FunctionalInterface
public interface CheckWinnerCommand {
	boolean check(int x, int y, Board board);
}
