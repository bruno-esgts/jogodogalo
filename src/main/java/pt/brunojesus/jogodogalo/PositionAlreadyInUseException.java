package pt.brunojesus.jogodogalo;

public class PositionAlreadyInUseException extends Exception {

	private static final long serialVersionUID = -1761050534845024071L;

	public PositionAlreadyInUseException(String message) {
		super(message);
	}
}
