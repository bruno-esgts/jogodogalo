package pt.brunojesus.jogodogalo;

public class Application {

	public static void main(String[] args) throws PositionAlreadyInUseException {
		
		Board board = new Board(3);
		board.play(0, 0, BoardItemEnum.CROSS);
		board.play(0, 1, BoardItemEnum.CIRCLE);
		board.play(1, 1, BoardItemEnum.CROSS);
		board.play(2, 2, BoardItemEnum.CROSS);
		board.play(2, 2, BoardItemEnum.CIRCLE);
		
		board.print();
	}

}
