package pt.brunojesus.jogodogalo.validator.play;

import pt.brunojesus.jogodogalo.Board;
import pt.brunojesus.jogodogalo.BoardItemEnum;
import pt.brunojesus.jogodogalo.exception.IllegalPlayException;

@FunctionalInterface
public interface PlayValidator {
	IllegalPlayException validatePlay(int x, int y, BoardItemEnum item, Board board);
}
