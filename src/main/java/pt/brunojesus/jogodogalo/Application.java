package pt.brunojesus.jogodogalo;

public class Application {

	public static void main(String[] args) throws IllegalPlayException {
		
		Board board = new Board(3);

		BoardItemEnum winner = null;
		winner = board.play(2, 0, BoardItemEnum.CROSS);
		winner = board.play(1, 0, BoardItemEnum.CIRCLE);
		winner = board.play(0, 2, BoardItemEnum.CROSS);
		winner = board.play(0, 0, BoardItemEnum.CIRCLE);
		winner = board.play(1, 1, BoardItemEnum.CROSS);
		
		if (winner != null) {
			System.out.println("Winner is: " + winner.getSymbol());
		}

		
		board.print();
	}

}
