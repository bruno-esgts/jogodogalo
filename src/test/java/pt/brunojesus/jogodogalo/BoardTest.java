package pt.brunojesus.jogodogalo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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

}
