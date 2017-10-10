package sudokuModel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SudokuTest2 {
	Getter myGetter;
	Model myModel;
	Board playArea;
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

	// ANGUS WHITEHEAD
	// Testing isLegal boolean changes
	@Test
	public void testLegalityOfGameDigit() {
		// Hardcoded data
		int[] existingValues = { 1, 2, 0, 0, 5, 6, 7, 8, 0 };
		int userInput = 5;
		// Setting data
		myModel.currentDigit = myGameDigit;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		boolean expected = false;
		boolean actual = myGameDigit.getIsLegal();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// ANGUS WHITEHEAD
	@Test
	public void testLegalityOfGameDigit2() {
		// Hardcoded data
		int[] existingValues = { 1, 2, 0, 0, 5, 6, 7, 8, 0 };
		int userInput = 4;
		// Setting data
		myModel.currentDigit = myGameDigit;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		boolean expected = true;
		boolean actual = myGameDigit.getIsLegal();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// ANGUS WHITEHEAD
	// Testing that the GameDigit's isLegal attribute is not legal (false) after
	// the user performs an illegal input.
	@Test
	public void testLegalityOfGameDigit3() {
		// Hardcoded data
		int[] existingValues = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int userInput = 7;
		// Setting data
		myModel.currentDigit = myGameDigit;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		boolean expected = false;
		boolean actual = myGameDigit.getIsLegal();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// ANGUS WHITEHEAD
	// Successfully sets an illegal value of 8
	@Test
	public void testIllegalUserInput1() {
		// Hardcoded data
		int[] existingValues = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int userInput = 8;
		// Setting data
		myModel.currentDigit = myGameDigit;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		int expected = 8;
		int actual = myGameDigit.getValue();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// ANGUS WHITEHEAD
	// Test that the value of a gameDigit is 2 after the user performs a input
	// of 2 on said digit.
	@Test
	public void testUserInputOfTwo() {
		// Hardcoded data
		int[] existingValues = { 1, 0, 3, 4, 5, 6, 7, 8, 9 };
		int userInput = 2;
		// Setting data
		myModel.currentDigit = myGameDigit;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		int expected = 2;
		int actual = myGameDigit.getValue();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// ANGUS WHITEHEAD
	@Test
	public void testMoveCount1() {
		// Hardcoded data
		int[] existingValues = { 1, 2, 3, 4, 0, 6, 7, 8, 9 };
		int userInput = 2;
		// Setting data
		myModel.currentDigit = myGameDigit;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		// Update the array with new value
		existingValues[1] = 2;
		userInput = 7;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		int expected = 2;
		int actual = myModel.moves;
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// KEITH KINGSBURY
	// Test duplicate Row equals false on start up.
	@Test
	public void testDuplicateRow() {
		// Hardcoded data
		boolean expected = false;
		boolean actual = myGameDigit.myRow.getHasDuplicates();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// KEITH KINGSBURY
	// Test duplicate Column equals false on start up.
	@Test
	public void testDuplicateColumn() {
		// Hardcoded data
		boolean expected = false;
		boolean actual = myGameDigit.myColumn.getHasDuplicates();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// KEITH KINGSBURY
	// Test duplicate Square equals false on start up.
	@Test
	public void testDuplicateSquare() {
		// Hardcoded data
		boolean expected = false;
		boolean actual = myGameDigit.mySquare.getHasDuplicates();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// KEITH KINGSBURY
	// Test duplicate Square equals true with duplicates in square.
	@Test
	public void testDuplicateSquare2() {
		// Hardcoded data
		int[] existingValues = { 1, 2, 3, 4, 0, 6, 7, 8, 9 };

		// Setting data
		myModel.currentDigit = myGameDigit;
		int userInput = 2;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		boolean expected = true;
		boolean actual = myGameDigit.mySquare.getHasDuplicates();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// KEITH KINGSBURY
	// Test duplicate Row equals true with duplicates in square.
	@Test
	public void testDuplicateRow1() {
		// Hardcoded data
		int[] existingValues = { 1, 2, 3, 4, 0, 6, 7, 8, 9 };

		// Setting data
		myModel.currentDigit = myGameDigit;
		int userInput = 4;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		boolean expected = true;
		boolean actual = myGameDigit.myRow.getHasDuplicates();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// KEITH KINGSBURY
	// Test duplicate Column equals true with duplicates in square.
	@Test
	public void testDuplicateColumn1() {
		// Hardcoded data
		int[] existingValues = { 1, 2, 3, 4, 0, 6, 7, 8, 9 };

		// Setting data
		myModel.currentDigit = myGameDigit;
		int userInput = 2;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		boolean expected = true;
		boolean actual = myGameDigit.myColumn.getHasDuplicates();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// KEITH KINGSBURY
	// Test duplicate Square equals false after input of no duplicate.
	@Test
	public void testNotDuplicateSquare() {
		// Hardcoded data
		int[] existingValues = { 1, 2, 3, 4, 0, 6, 7, 8, 9 };

		// Setting data
		myModel.currentDigit = myGameDigit;
		int userInput = 5;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		boolean expected = false;
		boolean actual = myGameDigit.mySquare.getHasDuplicates();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// KEITH KINGSBURY
	// Test duplicate Row equals false after input of no duplicate.
	@Test
	public void testNotDuplicateRow() {
		// Hardcoded data
		int[] existingValues = { 1, 2, 3, 4, 0, 6, 7, 8, 9 };

		// Setting data
		myModel.currentDigit = myGameDigit;
		int userInput = 2;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		boolean expected = false;
		boolean actual = myGameDigit.myRow.getHasDuplicates();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// KEITH KINGSBURY
	// Test duplicate Column equals false after input of no duplicate.
	@Test
	public void testNotDuplicateColumn() {
		// Hardcoded data
		int[] existingValues = { 1, 2, 3, 4, 0, 6, 7, 8, 9 };

		// Setting data
		myModel.currentDigit = myGameDigit;
		int userInput = 3;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		boolean expected = false;
		boolean actual = myGameDigit.myColumn.getHasDuplicates();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// ANDREW MILLER
	// Starts a game, edits a digit, restarts and then checks that the digit is
	// back to it's default value as based on hardcoded data.
	@Test
	public void testBlankAfterRestart() {
		// Hardcoded data
		int[] existingValues = { 1, 2, 3, 4, 0, 6, 7, 8, 9 };
		int userInput = 5;
		// Setting data
		myModel.currentDigit = myGameDigit;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		myModel.restart();
		myGameDigit = myModel.myGetter.getByRow(theRows[4], theColumns[4]);
		int expected = 0;
		int actual = myGameDigit.getValue();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// MICHAEL ELCOCK
	// Testing that the attribute isFixed changes to false when using the
	// setIsFixed method passing in false.
	@Test
	public void testIsFixedChange() {
		playArea = new Board(9, 9);
		myGameDigit = myModel.myGetter.getByRow(theRows[4], theColumns[3]);
		boolean expected = false;
		myGameDigit.setIsFixed(false);
		boolean actual = myGameDigit.getIsFixed();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// MICHAEL ELCOCK
	// The game digit at row 4 & column 4 Should start as not fixed, checking to
	// see if we can fix it.
	@Test
	public void testIsFixedChange2() {
		playArea = new Board(9, 9);
		boolean expected = true;
		myGameDigit.setIsFixed(true);
		boolean actual = myGameDigit.getIsFixed();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// ANGUS WHITEHEAD
	// Testing the GameDigit history has a length of 1 after the user has
	// finished one turn.
	@Test
	public void testGameDigitHistoryOfOne() {
		// Hardcoded data
		int[] existingValues = { 1, 2, 3, 4, 0, 6, 7, 8, 9 };
		int userInput = 2;
		// Setting data
		myModel.currentDigit = myGameDigit;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		int expected = 1;
		int actual = myModel.getDigitHistory().size();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// Test a history of zero, after performing a move (which should increase it
	// to 1 as proved above) and then executing undo, which should purge the
	// history value
	// Resulting in a length of zero.
	// ANGUS WHITEHEAD
	@Test
	public void testDigitHistoryOfZeroWithUndo() {
		// Hardcoded data
		int[] existingValues = { 1, 2, 3, 4, 0, 6, 7, 8, 9 };
		int userInput = 2;
		// Setting data
		myModel.currentDigit = myGameDigit;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		myModel.undo();
		int expected = 0;
		int actual = myModel.getDigitHistory().size();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// ANDREW MILLER
	// Check that the GameDigit located at 4/4 returns the correct hint of 5
	// (working with default starting cells).
	@Test
	public void testCorrectHints() {
		// Setting data
		String expected = "5.";
		int[] result = myModel.getHint(myGameDigit);
		String actual = "";
		for (int value : result) {
			actual += value + ".";
		}
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected.equals(actual));
	}

	// ANDREW MILLER
	// Testing the return of multiple hints
	@Test
	public void testCorrectHints2() {
		myGameDigit = myModel.myGetter.getByRow(theRows[3], theColumns[3]);
		// Setting data
		String expected = "5.7.9.";
		int[] result = myModel.getHint(myGameDigit);
		String actual = "";
		for (int value : result) {
			actual += value + ".";
		}
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected.equals(actual));
	}

	// ANDREW MILLER
	// Testing the return of existing values for a row. (0s for blanks)
	@Test
	public void testExistingValuesRow() {
		// Setting data
		String expected = "4.0.0.8.0.3.0.0.1.";
		int[] result = myModel.getRCS(myGameDigit, "row");
		// Just initializing for now.
		String actual = "";
		for (int value : result) {
			actual += value + ".";
		}
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected.equals(actual));
	}

	// ANDREW MILLER
	// Testing the return of existing values for a column. (0s for blanks)
	@Test
	public void testExistingValuesColumn() {
		// Setting data
		String expected = "7.9.0.6.0.2.0.1.8.";
		int[] result = myModel.getRCS(myGameDigit, "column");
		String actual = "";
		for (int value : result) {
			actual += value + ".";
		}
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected.equals(actual));
	}

	// ANDREW MILLER
	// Testing the return of existing values for a square. (0s for blanks)
	@Test
	public void testExistingValuesSquare() {
		// Setting data
		String expected = "0.6.0.8.0.3.0.2.0.";
		int[] result = myModel.getRCS(myGameDigit, "square");

		String actual = "";
		for (int value : result) {
			actual += value + ".";
		}
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected.equals(actual));
	}

	// ANDREW MILLER
	// 4, 2, 6, 8, 5, 3, 7, 9, 1
	// Testing that the row has all unique numbers and no zeros.
	@Test
	public void testRowHasAllUnique() {
		// Hardcoded data
		GameDigit currentGameDigit = myModel.myGetter.getByRow(theRows[4], theColumns[4]);
		myModel.setCurrentDigit(currentGameDigit);
		int[] existingValues = { 1, 2, 3, 4, 0, 6, 7, 8, 9 };
		int userInput = 5;
		// Setting data
		myModel.currentDigit = myGameDigit;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		currentGameDigit = myModel.myGetter.getByRow(theRows[4], theColumns[1]);
		myModel.setCurrentDigit(currentGameDigit);
		// Update the array with new value
		userInput = 2;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		currentGameDigit = myModel.myGetter.getByRow(theRows[4], theColumns[2]);
		myModel.setCurrentDigit(currentGameDigit);
		// Update the array with new value
		userInput = 6;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		currentGameDigit = myModel.myGetter.getByRow(theRows[4], theColumns[6]);
		myModel.setCurrentDigit(currentGameDigit);
		// Update the array with new value
		userInput = 7;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		currentGameDigit = myModel.myGetter.getByRow(theRows[4], theColumns[7]);
		myModel.setCurrentDigit(currentGameDigit);
		// Update the array with new value
		userInput = 9;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		boolean expected = true;
		boolean actual = myModel.isRCSUnique(currentGameDigit, "row");
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// ANDREW MILLER
	// FIXED 7, FIXED 9, 4, FIXED 6, 5, FIXED 2, 3, FIXED 1, FIXED 8.
	// Testing that the column has all unique numbers and no zeros.
	@Test
	public void testColumnHasAllUnique() {
		// Hardcoded data
		GameDigit currentGameDigit = myModel.myGetter.getByRow(theRows[4], theColumns[4]);
		
		myModel.setCurrentDigit(currentGameDigit);
		int[] existingValues = { 1, 2, 3, 4, 0, 6, 7, 8, 9 };
		int userInput = 5;
		// Setting data
		myModel.currentDigit = myGameDigit;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		currentGameDigit = myModel.myGetter.getByRow(theRows[2], theColumns[4]);
		myModel.setCurrentDigit(currentGameDigit);
		// Update the array with new value
		userInput = 4;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		currentGameDigit = myModel.myGetter.getByRow(theRows[6], theColumns[4]);
		myModel.setCurrentDigit(currentGameDigit);
		// Update the array with new value
		userInput = 3;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		boolean expected = true;
		boolean actual = myModel.isRCSUnique(myGameDigit, "column");
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// ANDREW MILLER
	// 7,FIXED 6,1,FIXED 8,5,FIXED 3,9,FIXED 2,4
	// Testing that the square has all unique numbers and no zeros.
	@Test
	public void testSquareHasAllUnique() {
		// Hardcoded data
		GameDigit currentGameDigit = myModel.myGetter.getByRow(theRows[4], theColumns[4]);
		;
		myModel.setCurrentDigit(currentGameDigit);
		int[] existingValues = { 1, 2, 3, 4, 0, 6, 7, 8, 9 };
		int userInput = 5;
		// Setting data
		myModel.currentDigit = myGameDigit;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		currentGameDigit = myModel.myGetter.getByRow(theRows[3], theColumns[3]);
		myModel.setCurrentDigit(currentGameDigit);
		// Update the array with new value
		userInput = 7;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		currentGameDigit = myModel.myGetter.getByRow(theRows[3], theColumns[5]);
		myModel.setCurrentDigit(currentGameDigit);
		// Update the array with new value
		userInput = 1;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		currentGameDigit = myModel.myGetter.getByRow(theRows[5], theColumns[3]);
		myModel.setCurrentDigit(currentGameDigit);
		// Update the array with new value
		userInput = 9;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		currentGameDigit = myModel.myGetter.getByRow(theRows[5], theColumns[5]);
		myModel.setCurrentDigit(currentGameDigit);
		// Update the array with new value
		userInput = 4;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		boolean expected = true;
		boolean actual = myModel.isRCSUnique(myGameDigit, "square");
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// ANDREW MILLER
	// Tests that when the square/sector is not all unique the isRCSUnique
	// function will return false.
	@Test
	public void testSquareNotAllUnique() {
		// Hardcoded data
		GameDigit currentGameDigit = myModel.myGetter.getByRow(theRows[4], theColumns[4]);
		myModel.setCurrentDigit(currentGameDigit);
		int[] existingValues = { 1, 2, 3, 4, 0, 6, 7, 8, 9 };
		int userInput = 5;
		// Setting data
		myModel.currentDigit = myGameDigit;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		currentGameDigit = myModel.myGetter.getByRow(theRows[3], theColumns[3]);
		myModel.setCurrentDigit(currentGameDigit);
		// Update the array with new value
		userInput = 3;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		currentGameDigit = myModel.myGetter.getByRow(theRows[3], theColumns[5]);
		myModel.setCurrentDigit(currentGameDigit);
		// Update the array with new value
		userInput = 1;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		currentGameDigit = myModel.myGetter.getByRow(theRows[5], theColumns[3]);
		myModel.setCurrentDigit(currentGameDigit);
		// Update the array with new value
		userInput = 3;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		currentGameDigit = myModel.myGetter.getByRow(theRows[5], theColumns[5]);
		myModel.setCurrentDigit(currentGameDigit);
		// Update the array with new value
		userInput = 8;
		myModel.setUserInput(userInput);
		myModel.set(existingValues);
		boolean expected = false;
		boolean actual = myModel.isRCSUnique(myGameDigit, "square");
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// ANGUS WHITEHEAD
	// Testing the ability to get and set
	@Test
	public void testGettersAndSettersUserInput() {
		myModel.setUserInput(5);
		int expected = 5;
		int actual = myModel.getUserInput();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// ANGUS WHITEHEAD
	// Testing userInput can be set to 10. User input of 10 signifies that the
	// computer is executing a set.
	@Test
	public void testInput() {
		myModel.setUserInput(10);
		int expected = 10;
		int actual = myModel.getUserInput();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// MICHAEL ELCOCK
	// Returns an int array of 0,0 when passed in 0
	@Test
	public void testSquareScanPosition1() {
		// It is passed until proven otherwise.
		boolean result = true;
		boolean expected = true;
		// The int array passed back from squareScanPosition, this is iterated
		// on to make sure it contains the correct information.
		int[] actual = myModel.squareScanPosition(0);
		// We are expecting to get back an int array of 0s, so therefore, if you
		// don't find any zeros, result = false and break.
		for (int value : actual) {
			if (value != 0) {
				result = false;
				break;
			}
		}
		String errorMsg = "Expected: " + expected + " but got " + result;
		assertTrue(errorMsg, expected == result);
	}

	// MICHAEL ELCOCK
	// Returns an int array of 3,6 when passed in 5
	@Test
	public void testSquareScanPosition2() {
		// It is passed until proven otherwise.
		boolean result = true;
		boolean expected = true;
		// The int array passed back from squareScanPosition, this is iterated
		// on to make sure it contains the correct information.
		int[] actual = myModel.squareScanPosition(5);
		// We are expecting to get back an int array of 0s, so therefore, if you
		// don't find any zeros, result = false and break.
		// The first value we are expecting is 3
		int test = 3;
		for (int value : actual) {
			if (value != test) {
				result = false;
				break;
				// if the first value you get is 3, then change test to 6 as we
				// are expecting a 6 next.
			} else {
				test = 6;
			}
		}
		String errorMsg = "Expected: " + expected + " but got " + result;
		assertTrue(errorMsg, expected == result);
	}

	// MICHAEL ELCOCK
	// Returns an int array of -1,-1 when passed in 9
	@Test
	public void testSquareScanPosition3() {
		// It is passed until proven otherwise.
		boolean result = true;
		boolean expected = true;
		// The int array passed back from squareScanPosition, this is iterated
		// on to make sure it contains the correct information.
		int[] actual = myModel.squareScanPosition(9);
		// We are expecting to get back an int array of -1s, so therefore, if you
		// don't find any -1s, result = false and break.
		for (int value : actual) {
			if (value != -1) {
				result = false;
				break;
			}
		}
		String errorMsg = "Expected: " + expected + " but got " + result;
		assertTrue(errorMsg, expected == result);
	}

	// MICHAEL ELCOCK
	// Test that the getUniqueNumbers function returns an int array of no 0s
	// when we pass in an array containing values of 1 to 9 at least once.
	@Test
	public void testUniqueNumbers1() {
		// It is passed until proven otherwise.
		boolean result = true;
		boolean expected = true;
		// The data that getUniqueNumbers will work on.
		int[] hardCodedData = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] actual = myModel.getUniqueNumbers(hardCodedData);
		// We are expecting back an int array: 1, 2, 3, 4, 5, 6, 7, 8, 9 in that
		// order. So Compare count to the int array in a for each
		// If count is not equal to the value, it lacks the expected data in set
		// format, return false, if it is equal to the right value, increment
		// count and continue checking
		int count = 1;
		for (int value : actual) {
			if (value != count) {
				result = false;
				break;
			}
			count++;
		}
		String errorMsg = "Expected: " + expected + " but got " + result;
		assertTrue(errorMsg, expected == result);
	}

	// MICHAEL ELCOCK
	// Test that uniqueNumbers returns an int array with zeros.
	@Test
	public void testUniqueNumbers2() {
		// There are no 0s until proven. If we find a zero, flip result to true.
		boolean result = false;
		boolean expected = true;
		// The data that getUniqueNumbers will work on.
		int[] hardCodedData = { 0, 2, 3, 4, 0, 6, 0, 8, 9, 0, 2, 3, 4, 0, 6, 7, 8, 9, 0, 2, 3, 4, 0, 6, 7, 8, 9 };
		int[] actual = myModel.getUniqueNumbers(hardCodedData);

		// If we find a zero, flip the result boolean to true to signify the
		// actual array contains zeros.
		for (int value : actual) {
			if (value == 0) {
				result = true;
			}
		}
		String errorMsg = "Expected: " + expected + " but got " + result;
		assertTrue(errorMsg, expected == result);
	}

	// MICHAEL ELCOCK
	// Testing that the Squares constructor will construct an instance of square
	// with default boolean false for hasDuplicates
	@Test
	public void testSquareConstructor() {
		Square theSquare = new Square(5);
		boolean expected = false;
		boolean actual = theSquare.getHasDuplicates();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// MICHAEL ELCOCK
	// Testing that the Column constructor will construct an instance of column
	// with default boolean false for hasDuplicates
	@Test
	public void testColumnConstructor() {
		Column theColumn = new Column(2);
		boolean expected = false;
		boolean actual = theColumn.getHasDuplicates();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	// MICHAEL ELCOCK
	// Testing that the Row constructor will construct an instance of row with
	// default boolean false for hasDuplicates
	@Test
	public void testRowConstructor() {
		Row theRow = new Row(8);
		boolean expected = false;
		boolean actual = theRow.getHasDuplicates();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}
	
	//ANDREW MILLER
	//Tests that the getter returns the correct GameDigit within the myGameDigit array.
	//Expecting that the digit at row 4 & column 4 is index 40 of the myGameDigits array.
	@Test
	public void testGetter() {
		// Hardcoded data
		myGameDigit = myModel.myGetter.getByRow(theRows[4], theColumns[4]);
		
		//initializing actual to -1, if it's still negative 1 after the for loop, we know it didn't find the gameDigit.
		int actual = -1;
		//Calculating the position the returned GameDigit is within the myGameDigit array
		for(int i = 0; i < myModel.playArea.myGameDigits.length; i++ ){
			if(myGameDigit == myModel.playArea.myGameDigits[i]){
				actual = i;
				break;
			}
		}
		int expected = 40;
		//int actual = myGameDigit.getValue();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}
	
	// ANDREW MILLER
		// Tests that the arrayMerger will return a combined int array of the 3 int[] arrays passed to it.
		//This test tests that not only do the arrays merge into one, but they merge in the expected order.
		@Test
		public void testArrayMerger() {
			//Hardcoded data
			int[] testArray1 = {1,2,3};
			int[] testArray2 = {4,5,6};
			int[] testArray3 = {7,8,9};
			// Setting data
			String expected = "1.2.3.4.5.6.7.8.9.";
			int[] result = myModel.arrayMerger(testArray1, testArray2, testArray3);
			String actual = "";
			for (int value : result) {
				actual += value + ".";
			}
			String errorMsg = "Expected: " + expected + " but got " + actual;
			assertTrue(errorMsg, expected.equals(actual));
		}
		
		// ANDREW MILLER
		// Tests that the arrayMerger will return a combined int array of the 3 int[] arrays passed to it.
		//This test tests that not only do the arrays merge into one, but they merge in the expected order.
		@Test
		public void testArrayMerger2() {
			//Hardcoded data
			int[] testArray1 = {1,2,3};
			int[] testArray2 = {4,5,6};
			int[] testArray3 = {7,8,9};
			// Setting data
			String expected = "4.5.6.1.2.3.7.8.9.";
			int[] result = myModel.arrayMerger(testArray2, testArray1, testArray3);
			String actual = "";
			for (int value : result) {
				actual += value + ".";
			}
			String errorMsg = "Expected: " + expected + " but got " + actual;
			assertTrue(errorMsg, expected.equals(actual));
		}

}
