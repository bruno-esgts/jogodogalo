package pt.brunojesus.jogodogalo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import pt.brunojesus.jogodogalo.exception.ConsecutivePlayException;
import pt.brunojesus.jogodogalo.exception.IllegalBoardPositionException;
import pt.brunojesus.jogodogalo.exception.IllegalPlayException;
import pt.brunojesus.jogodogalo.exception.PositionAlreadyInUseException;

class BoardTest {

	private Board subject;

	@Test
	void testPositions() throws IllegalPlayException {
		subject = new Board(3);

		this.subject.play(0, 0);
		this.subject.play(0, 1);
		this.subject.play(0, 2);

		this.subject.play(1, 0);
		this.subject.play(1, 2);

		this.subject.play(2, 1);
		this.subject.play(2, 0);
		this.subject.play(2, 2);

		assertEquals(BoardItemEnum.CROSS, this.subject.getItemInPosition(0, 0));
		assertEquals(BoardItemEnum.CIRCLE, this.subject.getItemInPosition(0, 1));
		assertEquals(BoardItemEnum.CROSS, this.subject.getItemInPosition(0, 2));
		assertEquals(BoardItemEnum.CIRCLE, this.subject.getItemInPosition(1, 0));
		assertEquals(BoardItemEnum.CROSS, this.subject.getItemInPosition(1, 2));
		assertEquals(BoardItemEnum.CIRCLE, this.subject.getItemInPosition(2, 1));
		assertEquals(BoardItemEnum.CROSS, this.subject.getItemInPosition(2, 0));
		assertNull(subject.getItemInPosition(1, 1));
	}

	static Stream<Arguments> illegalPositionsProvider() {
		return Stream.of(Arguments.of(-1, 0), Arguments.of(0, -1), Arguments.of(-1, -1), Arguments.of(-10, -20),
				Arguments.of(3, 0), Arguments.of(0, 3), Arguments.of(3, 3), Arguments.of(10, 0), Arguments.of(2, 10));
	}

	@ParameterizedTest
	@MethodSource("illegalPositionsProvider")
	void testIllegalBoardPosition(int x, int y) throws IllegalPlayException {
		subject = new Board(3);

//		Executable ex = new Executable() {
//			@Override
//			public void execute() throws Throwable {
//				subject.play(-1, 0);
//			}
//		};

		// Como Executable é uma @FunctionalInterface, podemos utilizar uma sintaxe
		// simplificada.
//		Executable ex = () -> {
//			subject.play(-1, 0);
//		};

		assertThrows(IllegalBoardPositionException.class, () -> subject.play(x, y));

	}

	static Stream<Arguments> legalPositionsProvider() {
		return Stream.of(Arguments.of(0, 0), Arguments.of(0, 1), Arguments.of(0, 2), Arguments.of(1, 0),
				Arguments.of(1, 1), Arguments.of(1, 2), Arguments.of(2, 0), Arguments.of(2, 1), Arguments.of(2, 2));
	}

	@ParameterizedTest
	@MethodSource("legalPositionsProvider")
	void testPositionAlreadyInUse(int x, int y) throws IllegalPlayException {
		subject = new Board(3);

		subject.play(x, y);

		assertThrows(PositionAlreadyInUseException.class, () -> subject.play(x, y));
	}
	
	static Stream<Arguments> consecutivePlaysProvider() {
		return Stream.of(
					Arguments.of(0, 0, 1, 1, BoardItemEnum.CROSS),
					Arguments.of(0, 0, 1, 1, BoardItemEnum.CIRCLE),
					Arguments.of(0, 1, 2, 1, BoardItemEnum.CROSS),
					Arguments.of(0, 1, 2, 1, BoardItemEnum.CIRCLE)
				);
	}

	@ParameterizedTest
	@MethodSource("consecutivePlaysProvider")
	void testConsecutivePlay(int x1, int y1, int x2, int y2, BoardItemEnum item) throws IllegalPlayException {
		subject = new Board(3);

		subject.play(x1, y1, item);

		assertThrows(
				ConsecutivePlayException.class, 
				() -> subject.play(x2, y2, item)
		);
	}
	
	@Test
	void testGameAlreadyFinished() {
		//TODO: trabalho de casa
	}
	
	static Stream<Arguments> winningPlaysProvider() {
		return Stream.of(
					// Horizontal
					Arguments.of(List.of(
							Play.of(1,1), //X
							Play.of(0,0), //O
							Play.of(0,1), //X
							Play.of(1,0), //O
							Play.of(2,1) //X
					)),
					// Vertical
					Arguments.of(List.of(
							Play.of(0, 0), // X
							Play.of(2, 2), // O
							Play.of(0, 1), // X
							Play.of(1, 1), // O
							Play.of(0, 2) // X
					)),
					// Diagonal
					Arguments.of(List.of(
							Play.of(1, 1), // X
							Play.of(2, 0), // O
							Play.of(0, 0), // X
							Play.of(2, 1), // O
							Play.of(2, 2) // X
					)),
					//Inverse Diagonal
					Arguments.of(List.of(
							Play.of(2, 0), // X
							Play.of(0, 0), // O
							Play.of(1, 1), // X
							Play.of(2, 2), // O
							Play.of(0, 2) // X
					))
				);
	}
	
	
	@ParameterizedTest
	@MethodSource("winningPlaysProvider")
	void testWinner(List<Play> plays) throws IllegalPlayException {
		Board board = new Board(3);
		
		assertNull(board.getWinner());
		
		for (int i = 0; i < plays.size(); i++) {
			Play play = plays.get(i);
			board.play(play.getX(), play.getY());
		}
		System.out.println();
		board.print();
		
		assertNotNull(board.getWinner());
	}
}
