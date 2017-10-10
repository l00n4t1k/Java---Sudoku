package sudokuModel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SudokuTest {

	Board playArea;

	@Before
	public void setUp() throws Exception {
		playArea = new Board(9, 9);
	}

	/*
	 * @Test public void test() { fail("Not yet implemented"); }
	 */
	// Testing that there is enough rows instantiated in a 3 by 3.
	//KEITH KINGSBURY
	@Test
	public void testRowInstances3by3() {
		int expected = 9;
		int actual = playArea.row.length;
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	//KEITH KINGSBURY
	// Testing that there is enough columns instantiated in a 3 by 3.
	@Test
	public void testColumnInstances3by3() {
		int expected = 9;
		int actual = playArea.column.length;
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	//KEITH KINGSBURY
	// Testing that there is enough squares/sectors instantiated in a 3 by 3.
	@Test
	public void testSquareInstances3by3() {
		int expected = 9;
		int actual = playArea.square.length;
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	//KEITH KINGSBURY
	// Testing that there is enough Digits instantiated in a 3 by 3.
	@Test
	public void testDigitInstances3by3() {
		int expected = 81;
		int actual = playArea.myGameDigits.length;
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	//KEITH KINGSBURY
	// Testing that there is enough Columns instantiated in a 3 by 2.
	@Test
	public void testColumnInstances3by2() {
		playArea = new Board(6, 6);
		int expected = 6;
		int actual = playArea.column.length;
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	//KEITH KINGSBURY
	// Testing that there is enough Rows instantiated in a 3 by 2.
	@Test
	public void testRowInstances3by2() {
		playArea = new Board(6, 6);
		int expected = 6;
		int actual = playArea.row.length;
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	//KEITH KINGSBURY
	// Testing that there is enough Squares/sectors instantiated in a 3 by 2.
	@Test
	public void testSquareInstances3by2() {
		playArea = new Board(6, 6);
		int expected = 6;
		int actual = playArea.square.length;
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	//KEITH KINGSBURY
	// Testing that there is enough Digits instantiated in a 3 by 2.
	@Test
	public void testDigitInstances3by2() {
		playArea = new Board(6, 6);
		int expected = 36;
		int actual = playArea.myGameDigits.length;
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	//KEITH KINGSBURY
	// Test that a fixed GameDigit has it's attribute isFixed set to true.
	@Test
	public void testIsFixedTrue() {
		playArea = new Board(9, 9);
		boolean expected = true;
		boolean actual = playArea.myGameDigits[80].getIsFixed();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}

	//KEITH KINGSBURY
	// Test that a non-fixed GameDigit has it's attribute isFixed set to false.
	@Test
	public void testIsFixedFalse() {
		playArea = new Board(9, 9);
		boolean expected = false;
		boolean actual = playArea.myGameDigits[78].getIsFixed();
		String errorMsg = "Expected: " + expected + " but got " + actual;
		assertTrue(errorMsg, expected == actual);
	}
	

}
