package pt.brunojesus.jogodogalo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTest {
	
	private Board subject;
	
	@Test
	void testPositions() throws IllegalPlayException {
		subject = new Board(3);
		
		subject.play(0, 0);
		subject.play(0, 1);
		
		assertEquals(BoardItemEnum.CROSS, subject.getItemInPosition(0, 0));	
		assertEquals(BoardItemEnum.CIRCLE, subject.getItemInPosition(0, 1));
		assertNull(subject.getItemInPosition(1, 1));
	}

}
