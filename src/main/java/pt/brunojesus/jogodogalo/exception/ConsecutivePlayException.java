package pt.brunojesus.jogodogalo.exception;

public class ConsecutivePlayException extends IllegalPlayException {

	private static final long serialVersionUID = -1621265378552763256L;

	public ConsecutivePlayException(String message) {
		super(message);
	}	
}
