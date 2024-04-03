package pt.brunojesus.jogodogalo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
		return Stream.of(
					Arguments.of(-1, 0),
					Arguments.of(0, -1),
					Arguments.of(-1, -1),
					Arguments.of(-10, -20),
					Arguments.of(3, 0),
					Arguments.of(0, 3),
					Arguments.of(3,3),
					Arguments.of(10, 0),
					Arguments.of(2, 10)
				);
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

}
