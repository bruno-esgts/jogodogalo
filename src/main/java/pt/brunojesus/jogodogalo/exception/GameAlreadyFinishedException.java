package pt.brunojesus.jogodogalo.exception;

public class GameAlreadyFinishedException extends IllegalPlayException {

	private static final long serialVersionUID = -561637632358346006L;

	public GameAlreadyFinishedException(String message) {
		super(message);
	}

}
