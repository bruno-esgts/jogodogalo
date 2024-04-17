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

public class Board {

	private BoardItemEnum[][] board;
	private Integer size;
	private BoardItemEnum lastPlayedItem = null;
	private BoardItemEnum winner = null;
	private List<CheckWinnerCommand> winnerCheckCommands = null;
	private List<PlayValidator> playValidators = null;

	public Board(int size) {
		this.size = size;
		this.board = new BoardItemEnum[size][size];
		
		this.winnerCheckCommands = List.of(
				new CheckLineWinnerCommand(),
				new CheckColumnWinnerCommand(),
				new CheckDiagonalWinnerCommand(),
				new CheckInverseDiagonalWinnerCommand()
		);
		
		this.playValidators = List.of(
				new GameAlreadyFinishedPlayValidator(),
				new BoardPositionValidator(),
				new ConsecutivePlayValidator(),
				new PositionInUseValidator()
		);
	}

	public void play(int x, int y) throws IllegalPlayException {
		final BoardItemEnum item = this.lastPlayedItem == BoardItemEnum.CROSS ? 
				BoardItemEnum.CIRCLE : BoardItemEnum.CROSS;
		
		play(x, y, item);
	}

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
	
	public BoardItemEnum getItemInPosition(int x, int y) {
		return this.board[y][x];
	}
	
	public BoardItemEnum getWinner() {
		return winner;
	}
	
	public BoardItemEnum getLastPlayedItem() {
		return lastPlayedItem;
	}
	
	public int getSize() {
		return size;
	}

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
