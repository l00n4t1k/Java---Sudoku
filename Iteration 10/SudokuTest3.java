package sudokuModel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SudokuTest3 {

	Board playArea;
	Getter myGetter;
	Model myModel;
	GameDigit myGameDigit;
	Row[] theRows;
	Column[] theColumns;

	@Before
	public void setUp() throws Exception {
		myModel = new Model();
		// playArea = new Board(9, 9);
		myModel.start(9, 9);
		theRows = myModel.playArea.getRow();
		theColumns = myModel.playArea.getColumn();
		myGameDigit = myModel.myGetter.getByRow(theRows[4], theColumns[4]);

	}

	// Testing that the isCompleted function returns true when there are no
	// blanks.
	//KEITH KINGSBURY
	@Test
	public void testIsCompleted() {
		// Setting data
		int[] finishedBoard = { 5, 3, 4, 6, 7, 8, 9, 1, 2, 6, 4, 2, 1, 9, 5, 3, 4, 8, 1, 9, 8, 3, 4, 2, 5, 6, 7, 8, 5,
				4, 7, 6, 1, 4, 4, 3, 4, 2, 6, 8, 5, 3, 7, 9, 1, 7, 1, 3, 9, 2, 4, 8, 5, 6, 9, 6, 1, 5, 3, 7, 4, 8, 4, 2,
				8, 4, 4, 1, 9, 6, 3, 5, 3, 4, 5, 2, 8, 6, 1, 7, 9 };
		myModel.setBoardLayout(finishedBoard);
		// Calling start so that it updates the boardLayout information to the
		// playArea / board.
		myModel.start(9, 9);
		boolean expected = true;
		boolean actual = myModel.isCompleted();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// Testing that the isCompleted function returns false when there is 1 blank
	// (zero).
	//KEITH KINGSBURY
	@Test
	public void testIsNotCompleted() {
		// Setting data
		int[] finishedBoard = { 5, 3, 4, 6, 7, 8, 9, 1, 2, 6, 7, 2, 1, 9, 5, 3, 4, 8, 1, 9, 8, 3, 4, 2, 5, 6, 7, 8, 0,
				4, 7, 4, 1, 4, 2, 3, 4, 2, 6, 8, 5, 3, 7, 9, 1, 7, 1, 3, 9, 2, 4, 8, 5, 6, 9, 6, 1, 5, 3, 7, 2, 8, 4, 2,
				4, 7, 4, 1, 9, 6, 3, 5, 3, 4, 5, 2, 8, 6, 1, 7, 9 };
		myModel.setBoardLayout(finishedBoard);
		// Calling start so that it updates the boardLayout information to the
		// playArea / board.
		myModel.start(9, 9);
		boolean expected = false;
		boolean actual = myModel.isCompleted();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}
	
	//KEITH KINGSBURY
	// Testing that the isFinished function returns true
		@Test
		public void testIsFinished() {
			// Setting data
			int[] finishedBoard = { 5, 3, 4, 6, 7, 8, 9, 1, 2, 6, 7, 2, 1, 9, 5, 3, 4, 8, 1, 9, 8, 3, 4, 2, 5, 6, 7, 8, 5,
					9, 7, 6, 1, 4, 2, 3, 4, 2, 6, 8, 5, 3, 7, 9, 1, 7, 1, 3, 9, 2, 4, 8, 5, 6, 9, 6, 1, 5, 3, 7, 2, 8, 4, 2,
					8, 7, 4, 1, 9, 6, 3, 5, 3, 4, 5, 2, 8, 6, 1, 7, 9 };
			myModel.setBoardLayout(finishedBoard);
			// Calling start so that it updates the boardLayout information to the
			// playArea / board.
			myModel.start(9, 9);
			myModel.updateDuplicateStatus();
			boolean expected = true;
			boolean actual = myModel.isFinished();
			String errorMsg = "Expected: " + expected + " but got " + actual;
			assertTrue(errorMsg, expected == actual);
		}
		
		//KEITH KINGSBURY
		// Testing that the isFinished function returns false when it is not finished.
		@Test
		public void testIsNotFinished() {
			// Setting data
			int[] finishedBoard = { 5, 3, 4, 6, 7, 8, 9, 1, 2, 6, 7, 2, 1, 9, 5, 3, 4, 8, 1, 9, 8, 3, 4, 2, 5, 6, 7, 8, 5,
					9, 7, 6, 1, 5, 2, 3, 4, 2, 6, 8, 5, 5, 7, 9, 5, 7, 1, 3, 5, 2, 4, 8, 5, 6, 9, 6, 1, 5, 3, 7, 2, 8, 4, 2,
					8, 7, 4, 1, 9, 6, 5, 5, 3, 4, 5, 2, 8, 6, 1, 7, 9 };
			myModel.setBoardLayout(finishedBoard);
			// Calling start so that it updates the boardLayout information to the
			// playArea / board.
			myModel.start(9, 9);
			myModel.updateDuplicateStatus();
			boolean expected = false;
			boolean actual = myModel.isFinished();
			String errorMsg = "Expected: " + expected + " but got " + actual;
			assertTrue(errorMsg, expected == actual);
		}
}
