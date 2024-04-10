package pt.brunojesus.jogodogalo.exception;

public class IllegalBoardPositionException extends IllegalPlayException {

	private static final long serialVersionUID = 6793606458404426547L;

	public IllegalBoardPositionException(String message) {
		super(message);
	}	
}
