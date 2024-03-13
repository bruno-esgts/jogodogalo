package pt.brunojesus.jogodogalo;

public class Application {

	public static void main(String[] args) throws IllegalPlayException {
		
		Board board = new Board(4);
		board.play(0, 0, BoardItemEnum.CROSS);
		board.play(0, 1, BoardItemEnum.CIRCLE);
		board.play(3, 0, BoardItemEnum.CROSS); 
		board.play(0, 3, BoardItemEnum.CIRCLE);
		board.play(1, 0, BoardItemEnum.CROSS);
		board.play(0, 2, BoardItemEnum.CIRCLE);
		board.play(2, 0, BoardItemEnum.CROSS);
		

		
		board.print();
	}

}
