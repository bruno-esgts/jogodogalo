package pt.brunojesus.jogodogalo.validator.play;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import pt.brunojesus.jogodogalo.Board;
import pt.brunojesus.jogodogalo.BoardItemEnum;
import pt.brunojesus.jogodogalo.exception.IllegalPlayException;

@ExtendWith(MockitoExtension.class)
class BoardPositionValidatorTest {
	
	BoardPositionValidator subject = new BoardPositionValidator();
	
	@Mock
	Board boardMock;
	
	static Stream<Arguments> illegalPositionsProvider() {
		return Stream.of(Arguments.of(-1, 0), Arguments.of(0, -1), Arguments.of(-1, -1), Arguments.of(-10, -20),
				Arguments.of(3, 0), Arguments.of(0, 3), Arguments.of(3, 3), Arguments.of(10, 0), Arguments.of(2, 10));
	}

	@ParameterizedTest
	@MethodSource("illegalPositionsProvider")
	void testValidatePlay_IllegalPositions(int x, int y) {
		Mockito.when(boardMock.getSize()).thenReturn(3);
		
		IllegalPlayException result = subject.validatePlay(
				x, y, BoardItemEnum.CIRCLE, boardMock
		);
		
		Mockito.verify(boardMock, Mockito.times(1)).getSize();
		
		assertNotNull(result);
	}
	
	@Test
	void testValidatePlay() {
		Mockito.when(boardMock.getSize()).thenReturn(3);
		
		IllegalPlayException result = subject.validatePlay(
				1, 1, BoardItemEnum.CIRCLE, boardMock
		);
		
		Mockito.verify(boardMock, Mockito.times(1)).getSize();

		assertNull(result);
	}

}
