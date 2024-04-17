package pt.brunojesus.jogodogalo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import pt.brunojesus.jogodogalo.command.winner.CheckWinnerCommand;
import pt.brunojesus.jogodogalo.exception.IllegalPlayException;
import pt.brunojesus.jogodogalo.validator.play.PlayValidator;

class BoardTest2 {
	
	Board subject;
	
//	@BeforeEach
//	void setUp() {
//	}

	@Test
	void testPlay_FailsOnValidation() {
//		PlayValidator dummyPlayValidator = new PlayValidator() {
//			@Override
//			public IllegalPlayException validatePlay(int x, int y, BoardItemEnum item, Board board) {
//				return new GameAlreadyFinishedException("Dummy exception");
//			}
//		};
		
		IllegalPlayException expected = new IllegalPlayException("dummy");
		PlayValidator playValidatorMock1 = Mockito.mock(PlayValidator.class);
		PlayValidator playValidatorMock2 = Mockito.mock(PlayValidator.class);
		PlayValidator playValidatorMock3 = Mockito.mock(PlayValidator.class);

		Mockito.when(
				playValidatorMock1.validatePlay(
						Mockito.anyInt(), Mockito.anyInt(),
						Mockito.any(), Mockito.any())
		).thenReturn(null);
		
		Mockito.when(
				playValidatorMock2.validatePlay(
						Mockito.anyInt(), Mockito.anyInt(),
						Mockito.any(), Mockito.any())
		).thenReturn(expected);
		
		Mockito.when(
				playValidatorMock3.validatePlay(
						Mockito.anyInt(), Mockito.anyInt(),
						Mockito.any(), Mockito.any())
		).thenReturn(null);
		
		subject = new Board(3, null, List.of(
				playValidatorMock1, playValidatorMock2, playValidatorMock3)
		);
		
		assertThrows(IllegalPlayException.class,
				() -> subject.play(2, 2)
		);
		
		Mockito.verify(playValidatorMock1, Mockito.times(1)).validatePlay(
				Mockito.anyInt(), 
				Mockito.anyInt(), 
				Mockito.any(),
				Mockito.any()
		);
		
		Mockito.verify(playValidatorMock2, Mockito.times(1)).validatePlay(
				Mockito.anyInt(), 
				Mockito.anyInt(), 
				Mockito.any(),
				Mockito.any()
		);
		
		
		Mockito.verify(playValidatorMock3, Mockito.times(0)).validatePlay(
				Mockito.anyInt(), 
				Mockito.anyInt(), 
				Mockito.any(),
				Mockito.any()
		);
	}
	
	@Test
	void validatePlay_HasWinner() throws IllegalPlayException {
		PlayValidator playValidatorMock = Mockito.mock(PlayValidator.class);
		
		CheckWinnerCommand checkWinnerMock1 = Mockito.mock(CheckWinnerCommand.class);
		CheckWinnerCommand checkWinnerMock2 = Mockito.mock(CheckWinnerCommand.class);
		CheckWinnerCommand checkWinnerMock3 = Mockito.mock(CheckWinnerCommand.class);

		subject = new Board(
				3, 
				List.of(checkWinnerMock1, checkWinnerMock2, checkWinnerMock3),
				List.of(playValidatorMock)
		);
		
		Mockito.when(
				playValidatorMock.validatePlay(
						Mockito.anyInt(), Mockito.anyInt(),
						Mockito.any(), Mockito.any())
		).thenReturn(null);
		
		Mockito.when(
				checkWinnerMock1.check(
						Mockito.anyInt(), Mockito.anyInt(), Mockito.any()
				)
		).thenReturn(false);
		
		Mockito.when(
				checkWinnerMock2.check(
						Mockito.anyInt(), Mockito.anyInt(), Mockito.any()
				)
		).thenReturn(true);
		
		Mockito.when(
				checkWinnerMock3.check(
						Mockito.anyInt(), Mockito.anyInt(), Mockito.any()
				)
		).thenReturn(false);
		
		assertNull(subject.getWinner());
		assertNull(subject.getLastPlayedItem());
		
		subject.play(0, 0);
		
		assertNotNull(subject.getWinner());
		assertNotNull(subject.getLastPlayedItem());
		
		Mockito.verify(playValidatorMock, Mockito.times(1)).validatePlay(
				Mockito.anyInt(), 
				Mockito.anyInt(), 
				Mockito.any(),
				Mockito.any()
		);
		
		Mockito.verify(checkWinnerMock1, Mockito.times(1)).check(
				Mockito.anyInt(), Mockito.anyInt(), Mockito.any()
		);
		
		Mockito.verify(checkWinnerMock2, Mockito.times(1)).check(
				Mockito.anyInt(), Mockito.anyInt(), Mockito.any()
		);
		
		Mockito.verify(checkWinnerMock3, Mockito.times(0)).check(
				Mockito.anyInt(), Mockito.anyInt(), Mockito.any()
		);
	}
	
	@Test
	void validatePlay_NoWinner() throws IllegalPlayException {
		PlayValidator playValidatorMock = Mockito.mock(PlayValidator.class);
		CheckWinnerCommand checkWinnerMock = Mockito.mock(CheckWinnerCommand.class);

		subject = new Board(
				3, 
				List.of(checkWinnerMock),
				List.of(playValidatorMock)
		);
		
		Mockito.when(
				playValidatorMock.validatePlay(
						Mockito.anyInt(), Mockito.anyInt(),
						Mockito.any(), Mockito.any())
		).thenReturn(null);
		
		Mockito.when(
				checkWinnerMock.check(
						Mockito.anyInt(), Mockito.anyInt(), Mockito.any()
				)
		).thenReturn(false);
		
		assertNull(subject.getWinner());
		assertNull(subject.getLastPlayedItem());
		
		subject.play(0, 0);
		
		assertNull(subject.getWinner());
		assertNotNull(subject.getLastPlayedItem());
		
		Mockito.verify(playValidatorMock, Mockito.times(1)).validatePlay(
				Mockito.anyInt(), 
				Mockito.anyInt(), 
				Mockito.any(),
				Mockito.any()
		);
		
		Mockito.verify(checkWinnerMock, Mockito.times(1)).check(
				Mockito.anyInt(), Mockito.anyInt(), Mockito.any()
		);
	}

}
