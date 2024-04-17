package pt.brunojesus.jogodogalo.validator.play;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import pt.brunojesus.jogodogalo.Board;
import pt.brunojesus.jogodogalo.BoardItemEnum;
import pt.brunojesus.jogodogalo.exception.IllegalPlayException;

@ExtendWith(MockitoExtension.class)
class ConsecutivePlayValidatorTest {
	
	ConsecutivePlayValidator subject = new ConsecutivePlayValidator();
	
	@Mock
	Board boardMock;

	@Test
	void testValidatePlay_ConsecutivePlay() throws IllegalPlayException {
		Mockito.when(boardMock.getLastPlayedItem()).thenReturn(BoardItemEnum.CIRCLE);
		
		IllegalPlayException result = subject.validatePlay(
				0, 0, BoardItemEnum.CIRCLE, boardMock
		);
		
		assertNotNull(result);
	}
	
	@Test
	void testValidatePlay() throws IllegalPlayException {
		Mockito.when(boardMock.getLastPlayedItem()).thenReturn(BoardItemEnum.CROSS);
		
		IllegalPlayException result = subject.validatePlay(
				0, 0, BoardItemEnum.CIRCLE, boardMock
		);
		
		assertNull(result);
	}

}
