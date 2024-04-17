package pt.brunojesus.jogodogalo;

import pt.brunojesus.jogodogalo.exception.IllegalPlayException;
import pt.brunojesus.jogodogalo.gui.GameWindow;

public class Application {

	public static void main(String[] args) throws IllegalPlayException {
		
		new GameWindow("Jogo do Galo", new BoardAdapter());
		
//		Board board = new Board(3);
//
//		BoardItemEnum winner = null;
//		winner = board.play(2, 0, BoardItemEnum.CROSS);
//		winner = board.play(1, 0, BoardItemEnum.CIRCLE);
//		winner = board.play(0, 2, BoardItemEnum.CROSS);
//		winner = board.play(0, 0, BoardItemEnum.CIRCLE);
//		winner = board.play(1, 1, BoardItemEnum.CROSS);
//		winner = board.play(2, 2, BoardItemEnum.CIRCLE);
//		
//		if (winner != null) {
//			System.out.println("Winner is: " + winner.getSymbol());
//		}
//
//		
//		board.print();
	}

}
