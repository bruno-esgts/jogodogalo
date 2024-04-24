package pt.brunojesus.jogodogalo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import pt.brunojesus.jogodogalo.command.winner.CheckColumnWinnerCommand;
import pt.brunojesus.jogodogalo.command.winner.CheckDiagonalWinnerCommand;
import pt.brunojesus.jogodogalo.command.winner.CheckInverseDiagonalWinnerCommand;
import pt.brunojesus.jogodogalo.command.winner.CheckLineWinnerCommand;
import pt.brunojesus.jogodogalo.command.winner.CheckWinnerCommand;
import pt.brunojesus.jogodogalo.exception.IllegalPlayException;
import pt.brunojesus.jogodogalo.validator.play.BoardPositionValidator;
import pt.brunojesus.jogodogalo.validator.play.ConsecutivePlayValidator;
import pt.brunojesus.jogodogalo.validator.play.GameAlreadyFinishedPlayValidator;
import pt.brunojesus.jogodogalo.validator.play.PlayValidator;
import pt.brunojesus.jogodogalo.validator.play.PositionInUseValidator;

/**
 * The Class Board.
 * <br/>
 * Represents a Tic-Tac-Toe board.
 * 
 * @author Bruno Jesus
 * @version 1.0
 * @since 1.0
 */
public class Board {

	/** The board. */
	private BoardItemEnum[][] board;
	
	/** The size. */
	private Integer size;
	
	/** The last played item. */
	private BoardItemEnum lastPlayedItem = null;
	
	/** The winner. */
	private BoardItemEnum winner = null;
	
	/** The winner check commands. */
	private List<CheckWinnerCommand> winnerCheckCommands = null;
	
	/** The play validators. */
	private List<PlayValidator> playValidators = null;

	
	/**
	 * Instantiates a new board with the default
	 * {@link CheckWinnerCommand}s and {@link PlayValidator}s.
	 *
	 * @param size the size of the board on width x height
	 * @see CheckWinnerCommand
	 * @see PlayValidator
	 * @see CheckLineWinnerCommand
	 * @see CheckColumnWinnerCommand
	 * @see CheckDiagonalWinnerCommand
	 * @see CheckInverseDiagonalWinnerCommand
	 * @see GameAlreadyFinishedPlayValidator
	 * @see BoardPositionValidator
	 * @see ConsecutivePlayValidator
	 * @see PositionInUseValidator
	 */
	public Board(int size) {
		this(
				size,
				List.of(
						new CheckLineWinnerCommand(),
						new CheckColumnWinnerCommand(),
						new CheckDiagonalWinnerCommand(),
						new CheckInverseDiagonalWinnerCommand()
				), 
				List.of(
						new GameAlreadyFinishedPlayValidator(),
						new BoardPositionValidator(),
						new ConsecutivePlayValidator(),
						new PositionInUseValidator()
				)
		);
	}
	
	/**
	 * Instantiates a new board with the provided {@link java.util.List} of
	 * {@link CheckWinnerCommand} and {@link PlayValidator}
	 *
	 * @param size the size
	 * @param winnerCheckCommands the winner check commands
	 * @param playValidators the play validators
	 */
	public Board(
			int size, 
			List<CheckWinnerCommand> winnerCheckCommands,
			List<PlayValidator> playValidators) {
		
		this.size = size;
		this.board = new BoardItemEnum[size][size];
		this.winnerCheckCommands = winnerCheckCommands;
		this.playValidators = playValidators;
	}

	/**
	 * Play on the desired position.
	 * 
	 * If the last played {@link BoardItemEnum} is <b>CROSS</b> then 
	 * a circle will be played, and vice versa.
	 * 
	 * @param x the x coordinate to play
	 * @param y the y coordinate to play
	 * @throws IllegalPlayException if an invalid play occurs.
	 */
	public void play(int x, int y) throws IllegalPlayException {
		final BoardItemEnum item = this.lastPlayedItem == BoardItemEnum.CROSS ? 
				BoardItemEnum.CIRCLE : BoardItemEnum.CROSS;
		
		play(x, y, item);
	}

	/**
	 * Play on the desired position.
	 *
	 * @param x the x coordinate to play
	 * @param y the y coordinate to play
	 * @param item the item to be played
	 * @return the winner if exists, otherwise returns <b>null</b>
	 * @throws IllegalPlayException if an invalid play occurs.
	 */
	public BoardItemEnum play(int x, int y, BoardItemEnum item) throws IllegalPlayException {
		Optional<IllegalPlayException> exception = this.playValidators.stream()
		.map((validator) -> validator.validatePlay(x, y, item, this))
		.filter(Objects::nonNull)
		.findFirst();
		
		if (exception.isPresent()) {
			throw exception.get();
		}

		this.board[y][x] = item;
		this.lastPlayedItem = item;
		
		boolean hasWinner = this.winnerCheckCommands.stream()
			.map((command) -> command.check(x, y, this))
			.filter((result) -> result == true)
			.findFirst().isPresent();
		if (hasWinner) {
			this.winner = lastPlayedItem;
		}

//		Alternativa em Java 7
//		for (int i = 0; i < this.winnerCheckCommands.size(); i++) {
//			boolean hasWinner = this.winnerCheckCommands.get(i).check(x, y, this);
//			if (hasWinner) {
//				this.winner = lastPlayedItem;
//				break;
//			}
//		}
		

		return this.winner;
	}
	
	
	/**
	 * Gets the item in position.
	 *
	 * @param x the x
	 * @param y the y
	 * @return the item in position
	 */
	public BoardItemEnum getItemInPosition(int x, int y) {
		return this.board[y][x];
	}
	
	/**
	 * Gets the winner.
	 *
	 * @return the winner
	 */
	public BoardItemEnum getWinner() {
		return winner;
	}
	
	/**
	 * Gets the last played item.
	 *
	 * @return the last played item
	 */
	public BoardItemEnum getLastPlayedItem() {
		return lastPlayedItem;
	}
	
	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Prints the.
	 */
	public void print() {
		for (int y = 0; y < this.size; y++) {
			for (int x = 0; x < this.size; x++) {
				String item = " ";
				if (this.board[y][x] != null) {
					item = this.board[y][x].getSymbol();
				}
				System.out.print(item);
			}
			System.out.println();
		}
	}
}
