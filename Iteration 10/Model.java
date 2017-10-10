package sudokuModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*
1.	Fix entered cells so they cannot be changed
2.	Clear the game to start again
3.	Unfix the cells 1
4.	Enter a value for a cell
5.	Keep track of # of moves
6.	Keep a history of past moves
7.	Undo a move (repeatedly)
8.	Hint of possible values for a cell
9.	Get by row
10.	Get by column
11.	Get by square
12.	Check that there no duplicates in a row
13.	Check that there no duplicates in a column
14.	Check that there no duplicates in a square
15.	Check that the game is solved
16.	Hint of impossible values for a cell
17.	Get all used values for a row
18.	Get all used values for a column
19.	Get all used values for a square
20.	Check if row has one of each number
21.	Check if column has one of each number
22.	Check if square has one of each number
 */
public class Model implements Validating, Game, HintPossible, HintImpossible {

	public static int[] boardLayout = { 5, 3, 0, 0, 7, 0, 0, 0, 0, 6, 0, 0, 1, 9, 5, 0, 0, 0, 0, 9, 8, 0, 0, 0, 0, 6, 0,
			8, 0, 0, 0, 6, 0, 0, 0, 3, 4, 0, 0, 8, 0, 3, 0, 0, 1, 7, 0, 0, 0, 2, 0, 0, 0, 6, 0, 6, 0, 0, 0, 0, 2, 8, 0,
			0, 0, 0, 4, 1, 9, 0, 0, 5, 0, 0, 0, 0, 8, 0, 0, 7, 9 };

	public void setBoardLayout(int[] theBoardLayout) {
		boardLayout = theBoardLayout;
	}

	// The below function might be replaced with jUnit logic. (e.g. hardcoded
	// information in a jUnit test instead of setting the user input below).
	// The logic stores the userInput, so that it may latter be sent to the set
	// methods in the setter class, so that the Digit value can update.
	/*
	 * public void setUserInput(int theUserInput){ GameDigit userInput = new
	 * GameDigit(theUserInput);
	 * 
	 * }
	 */
	protected Board playArea;
	// gets updated every time the user performs input on a cell. Allows the
	// program to reference this variable to perform checks on the userInput.
	// ANGUS WHITEHEAD
	private int userInput = 0;
	// Is updated to the correct currentDigit in tests, as we don't have a
	// userInterface to refer to, e.g user1 touched button 24.
	// ANGUS WHITEHEAD
	protected GameDigit currentDigit;
	protected Getter myGetter;
	protected int moves;
	// ANGUS WHITEHEAD
	protected List<Integer> valueHistory = new ArrayList<Integer>();
	protected List<GameDigit> digitHistory = new ArrayList<GameDigit>();
	protected int[] latestCellValues = new int[9];

	// ANGUS WHITEHEAD
	public List<Integer> getValueHistory() {
		return this.valueHistory;
	}

	// ANGUS WHITEHEAD
	public List<GameDigit> getDigitHistory() {
		return this.digitHistory;
	}

	// ANDREW MILLER
	// This will be called by the main / controller.
	public void start(int theRow, int theColumn) {
		playArea = new Board(theRow, theColumn);
		myGetter = new Getter(this);
		moves = 0;
		// Empty the history
		valueHistory.clear();
		// Removing a part of history that was not removed.
		digitHistory.clear();
	}

	// ANGUS WHITEHEAD
	public void setUserInput(int theInput) {
		userInput = theInput;
	}

	// ANGUS WHITEHEAD
	public int getUserInput() {
		return this.userInput;
	}

	// ANGUS WHITEHEAD
	public void setCurrentDigit(GameDigit theDigit) {
		this.currentDigit = theDigit;
	}

	@Override
	public boolean[] isImpossible(int gridIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] impossibles(int gridIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Integer> rowPossibilities(int rowIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Integer> columnPossibilities(int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Integer> squarePossibilities(int squareIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMaxValue(int maximum) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getMaxValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	// ANGUS WHITEHEAD
	// Takes in an int array of the possible values, this is used to compare
	// possible values to the user input to determine if the user is making an
	// illegal move or not, set the boolean isLegal variable accordingly based
	// on comparison.
	@Override
	public void set(int[] cellValues) {
		// If it's not fixed, then allow the user to perform input duties.
		if (!(currentDigit.getIsFixed())) {
			boolean isLegal = true;
			for (int i = 0; i < cellValues.length; i++) {
				// If it is NOT legal then set isLegal to false.
				if (!(isLegal(cellValues[i]))) {
					isLegal = false;
					break;
				}
			}
			// Computer Input (an undo move)
			if (userInput == 10) {
				setCurrentDigit(digitHistory.get(digitHistory.size() - 1));
				currentDigit.setValue(valueHistory.get(valueHistory.size() - 1));
				valueHistory.remove(valueHistory.size() - 1);
				digitHistory.remove(digitHistory.size() - 1);
				currentDigit.setIsLegal(isLegal);
				latestCellValues = getCellValues(currentDigit, true, false);
				moves--;

				// Users Input
			} else {
				// Save the state of the digit before a user makes changes to
				// it, so that the state can be reverted back to the way it was.
				// history.put(currentDigit, currentDigit.getValue());
				valueHistory.add(getUserInput());
				digitHistory.add(currentDigit);
				currentDigit.setIsLegal(isLegal);
				currentDigit.setValue(userInput);
				// Saves the current values associated with latest GameDigit in
				// a variable for reference, and results in side effects of
				// function
				// Side effect = hasDuplicate checks for row, column, square.
				latestCellValues = getCellValues(currentDigit, true, false);

			}

			moves++;

		}
	}

	// Checks a column to make sure that all it's values are unqiue (no
	// duplicates) and contain zero zeros.
	// Type is to check whether the caller wants a return based on a row, column
	// or square.
	// ANDREW MILLER
	public boolean isRCSUnique(GameDigit theGameDigit, String type) {
		int[] existingValues = getRCS(theGameDigit, type);
		// If the length of the return of cloneArray when passing in Existing
		// values is not equal to nine, there must have been some zeros trimmed
		// off,
		// therefore the row/column or square is not complete, thus return
		// false.
		// If duplicates are found, the row is not unique, thus there is still
		// work for the user to do, return false.
		/*
		 * if (cloneArray(existingValues).length != 10 ||
		 * hasDuplicates(existingValues)) { return false; }
		 */

		// If you find any zeros, it is not complete/finished, return false.
		for (int value : existingValues) {

			if (value == 0) {
				return false;

			}
		}
		// After verifying there are no zeros, verify that there are no
		// duplicates. If there are, return false since it's not completed.
		if (hasDuplicates(existingValues, false)) {
			return false;
		}
		// If no zeros or duplicates, the function will return true here.
		return true;
	}

	// This helps the software determine that the move being performed is by the
	// system itself.userInput = 10;
	// ANGUS WHITEHEAD
	public void undo() {
		setUserInput(10);
		set(getCellValues(digitHistory.get(digitHistory.size() - 1), true, false));

	}

	// This function can be used for hints, the 0s represents the valid numbers
	// that can be used for that GameDigit Value, convert 0s to index position
	// +1;
	// Takes in a digit and returns an array containing numbers that exist the
	// digits column, row and square.
	// Performs checks for digits.
	// the isUnique boolean determines whether this function will return just
	// the unique values from 0 to 9 in column, square and row, or the
	// total values, starting at row values, column, then the square.
	public int[] getCellValues(GameDigit theGameDigit, boolean isUnique, boolean getDigitValue) {
		// Allow the program to figure out where the Digit sits in a column,
		// row, square.
		int rowIndex = theGameDigit.getRowIndex();
		int columnIndex = theGameDigit.getColumnIndex();
		int squareIndex = theGameDigit.getSquareIndex();
		// check existing values in the row. For every element in the cellValues
		// that is not equal to 0, check it's equivalent value
		// For example a number in index position 5 would have an equivalent
		// value of 6, if it's equal to 0, check for other 6s and change that
		// spot in the cellValues array to a 6 if you find a 6. Sorts through
		// all the other GameDigit values in the row, by changing the column
		// Index
		int[] existingValuesRow = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int[] existingValuesColumn = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int[] existingValuesSquare = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int lastCheckedValue = 0;

		for (int i = 0; i < 9; i++) {

			// ~~~~PURGED THE IF CHECK ON THE IF CHECK, USED OR INSTEAD.~~~~
			// Don't check yourself, only check the other Digits in the
			// row.
			// if it is not equal to itself then add it to existing values, OR
			// if we want getDigitValue, add it to
			// existing values regardless of what it is. If getDigitValue is
			// passed in as false, then it will only
			// add to existing values when the condition on the left evaluates
			// to true
			if (i != columnIndex || getDigitValue) {
				lastCheckedValue = myGetter.getByRow(playArea.getRowByIndex(rowIndex), playArea.getColumnByIndex(i))
						.getValue();

				existingValuesRow[i] = lastCheckedValue;

			}
			// In this situation, you do want to check yourself.

		}
		// KEITH KINGSBURY LINE OF CODE
		theGameDigit.myRow.setHasDuplicates(hasDuplicates(existingValuesRow, true));

		for (int i = 0; i < 9; i++) {

			if (i != rowIndex || getDigitValue) {
				lastCheckedValue = myGetter.getByRow(playArea.getRowByIndex(i), playArea.getColumnByIndex(columnIndex))
						.getValue();

				existingValuesColumn[i] = lastCheckedValue;
			}

		}
		// KEITH KINGSBURY LINE OF CODE
		theGameDigit.myColumn.setHasDuplicates(hasDuplicates(existingValuesColumn, true));

		// MICHAEL ELCOCK
		int[] scanPosition = squareScanPosition(squareIndex);

		int squareCount = 0;

		// Outer loop equals rows, inner loop equals columns.
		for (int i = scanPosition[0]; i < scanPosition[0] + 3; i++) {
			for (int j = scanPosition[1]; j < scanPosition[1] + 3; j++) {

				if (!(i == rowIndex && j == columnIndex) || getDigitValue) {
					lastCheckedValue = myGetter.getByRow(playArea.getRowByIndex(i), playArea.getColumnByIndex(j))
							.getValue();

					existingValuesSquare[squareCount] = lastCheckedValue;
				}

				squareCount++;
			}
		}
		// KEITH KINGSBURY LINE OF CODE
		theGameDigit.mySquare.setHasDuplicates(hasDuplicates(existingValuesSquare, true));

		int[] totalValues = arrayMerger(existingValuesRow, existingValuesColumn, existingValuesSquare);
		if (isUnique) {
			int[] cellValues = getUniqueNumbers(totalValues);

			return cellValues;
		} else {
			return totalValues;
		}

	}

	// Grabs values relating to a digits column, square, and row. It returns the
	// unique numbers, zero if the number isn't found, e.g., if no 5s found,
	// return 0
	// in the appropriate array element.
	// MICHAEL ELCOCK
	public int[] getUniqueNumbers(int[] totalValues) {
		int[] cellValues = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		for (int i = 0; i < 9; i++) {
			for (int value : totalValues) {
				if (value == i + 1) {
					cellValues[i] = i + 1;
					break;
				}
			}
		}

		return cellValues;
	}

	// Takes in 3 arrays and returns a single array with length equal to the 3
	// arrays it took in. Also contains all values.
	// This is used to assist in iterating over multiple collections/arrays.
	// ANDREW MILLER
	public int[] arrayMerger(int[] existingValuesRow, int[] existingValuesColumn, int[] existingValuesSquare) {
		int[] totalValues = new int[existingValuesRow.length + existingValuesColumn.length
				+ existingValuesSquare.length];
		int mergeCount = 0;

		for (int value : existingValuesRow) {
			totalValues[mergeCount] = value;
			mergeCount++;
		}

		for (int value : existingValuesColumn) {
			totalValues[mergeCount] = value;
			mergeCount++;
		}

		for (int value : existingValuesSquare) {
			totalValues[mergeCount] = value;
			mergeCount++;
		}
		return totalValues;
	}

	// only works for 3by3
	// MICHAEL ELCOCK
	public int[] squareScanPosition(int theSquare) {
		// First value in array represents the row, the second value in array
		// represents the column.
		int[] result = new int[2];
		switch (theSquare) {
		case 0:
			// row position
			result[0] = 0;
			// column position
			result[1] = 0;
			return result;
		case 1:
			result[0] = 0;
			result[1] = 3;
			return result;
		case 2:
			result[0] = 0;
			result[1] = 6;
			return result;
		case 3:
			result[0] = 3;
			result[1] = 0;
			return result;
		case 4:
			result[0] = 3;
			result[1] = 3;
			// System.out.println("Result 0: " + result[0] + " Result 1: " +
			// result[1]);
			return result;
		case 5:
			result[0] = 3;
			result[1] = 6;
			return result;
		case 6:
			result[0] = 6;
			result[1] = 0;
			return result;
		case 7:
			result[0] = 6;
			result[1] = 3;
			return result;
		case 8:
			result[0] = 6;
			result[1] = 6;
			return result;
			//when passing in an invalid int, it returns an array of negative 1s
		default:
			result[0] = -1;
			result[1] = -1;
		}

		// should never reach this line of code.
		return result;
	}

	// Takes an array, and returns a cloned array sans zeros.
	// KEITH KINGSBURY
	public int[] cloneArray(int[] theArray) {
		int count = 0;
		for (int i = 0; i < theArray.length; i++) {
			if (theArray[i] > 0) {
				count++;
			}
		}
		int[] clonedArray = new int[count + 1];
		// Adding userInput to the Collection, else it won't be compared to
		// everything else in the collection, which is important when doing
		// checks for duplicates.
		clonedArray[0] = userInput;
		count = 1;
		for (int value : theArray) {
			if (value > 0) {
				clonedArray[count] = value;
				count++;
			}
		}
		return clonedArray;
	}

	// if addUserInput equals true, the array passed in will be stripped of
	// zeros and userInput will be added to it.
	// Else if it's false, the array passed in will be evaluated immediately as
	// it is for duplicates.
	// KEITH KINGSBURY
	public boolean hasDuplicates(int[] existingValuesX, boolean addUserInput) {
		int[] checkedArray;
		if (addUserInput) {
			checkedArray = cloneArray(existingValuesX);
		} else {
			checkedArray = existingValuesX;
		}
		boolean hasDuplicates = false;
		for (int j = 0; j < checkedArray.length; j++) {
			for (int k = j + 1; k < checkedArray.length; k++) {
				if (k == j) {
				} else if (checkedArray[k] == checkedArray[j]) {
					hasDuplicates = true;
					break;
				}
			}
		}

		return hasDuplicates;
	}

	// Takes a game digit, returns the possible numbers that could go in this
	// cell. The impossible numbers are the ones that do not show up in the
	// return.
	// ANDREW MILLER
	public int[] getHint(GameDigit theGameDigit) {
		int[] existingValues = getCellValues(theGameDigit, true, false);
		// Figuring out the length for the results Array
		int arrayLength = 0;
		for (int i = 0; i < existingValues.length; i++) {
			if (existingValues[i] == 0) {
				arrayLength++;
			}
		}

		int[] results = new int[arrayLength];
		int count = 0;
		for (int i = 0; i < existingValues.length; i++) {
			if (existingValues[i] == 0) {
				results[count] = i + 1;
				count++;
			}

		}

		return results;
	}

	// RCS = Row/Column/Square
	// Type represents whether the function is grabbing a row, column or
	// square/sector.
	// This function will return existing values for a row, column or square for
	// a GameDigit based on the type passed it.
	// ANDREW MILLER
	public int[] getRCS(GameDigit theGameDigit, String type) {
		int[] existingValues = getCellValues(theGameDigit, false, true);
		int count = 0;
		if (type.equals("column")) {
			count = 9;
		} else if (type.equals("square")) {
			count = 18;
		}

		int[] result = new int[9];
		int c = 0;
		for (int i = count; i < count + 9; i++) {
			result[c] = existingValues[i];
			c++;
		}
		return result;
	}

	@Override
	public void setSquareWidth(int squareWidth) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSquareHeight(int squareHeight) {
		// TODO Auto-generated method stub

	}

	// Overriding the current game with a new game by instantiating a new board
	// over the old board.
	// This covers the must have requirement: "Clear the game to start again."
	// ANDREW MILLER
	@Override
	public void restart() {
		start(playArea.row.length, playArea.column.length);
	}

	// A KEITH KINGSBURY FUNCTION
	@Override
	public boolean isCompleted() {
		// isCompleted until proven otherwise
		boolean result = true;
		for (GameDigit theGameDigit : playArea.myGameDigits) {
			// If any zeros are found in the GameDigit Array the game is not
			// completed, break and return false.
			if (theGameDigit.getValue() == 0) {
				result = false;
				break;
			}
		}
		return result;
	}

	// A KEITH KINGSBURY FUNCTION
	@Override
	public boolean isFinished() {
		// The game is finished until proven otherwise.
		boolean result = true;
		// If the board is not completed, the game is not finished. Return
		// false.
		if (!(isCompleted())) {
			result = false;
		} else {

			// If when scanning the GameDigits you come across a digit that has
			// duplicates in it's row, column or square, then return false, the
			// game is not finished.
			for (GameDigit theGameDigit : playArea.myGameDigits) {
				if (theGameDigit.myRow.getHasDuplicates() || theGameDigit.myColumn.getHasDuplicates()
						|| theGameDigit.mySquare.getHasDuplicates()) {
					result = false;
					break;
				}
			}
		}
		return result;
	}

	// A KEITH KINGSBURY FUNCTION
	// Used to force update on duplicate status for all the GameDigits (this was
	// only used for testing purposes).
	public void updateDuplicateStatus() {
		for (GameDigit theGameDigit : playArea.myGameDigits) {
			latestCellValues = getCellValues(theGameDigit, true, true);
		}
	}

	// Passed in a digit from existing int values, if this is the same as the
	// users Input, it is illegal, thus return false, else it's not illegal,
	// return true.
	// ANGUS WHITEHEAD
	@Override
	public boolean isLegal(int cellValue) {
		if (cellValue == userInput) {
			return false;
		}
		return true;
	}

}
