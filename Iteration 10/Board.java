package sudokuModel;
//KEITH KINGSBURY (whole class).
//Represents the field of play.
public class Board {
	protected Column[] column;
	protected Row[] row;
	// Not a cell, is a sector (square).
	protected Square[] square;
	// Stores the BoardIndexes Digits, these digits contain information about
	// the values of the cell.
	// The array size is calculated when BoardIndex is instantiated, as the
	// amount of Digits a board contains is based on board size.
	// based on history, has always been protected for instances of other
	// classes.
	protected GameDigit[] myGameDigits;

	// Passed in values of the column and row sizes, these are just used for
	// calculations for other methods
	// of type X. Therefore they are not saved as class level variables.
	public Board(int theRow, int theColumn) {
		// Gets a method to calculate an array Digits containing the correct
		// information in the instantiation process.
		// The below method call grabs a return of the correct data.
		//myGameDigits = 
		//Instantiating the arrays with elements to prevent null pointer exceptions.
		this.row = new Row[theRow];
		this.column = new Column[theColumn];
		this.square = new Square[theColumn];
		this.myGameDigits = new GameDigit[theColumn*theRow];
		createBoardSize(theRow, theColumn);
	}


	// The first for loop is going to create 9 instances of row inside the Row
	// Array.
	// The second one is going to create 9 instances of columns in Column Array.
	public void createBoardSize(int rowCount, int colCount) {
		for (int i = 0; i < rowCount; i++) {
			this.row[i] = new Row(i);

		}
		for (int j = 0; j < colCount; j++) {
			this.column[j] = new Column(j);
		}
		for (int k = 0; k < colCount; k++) {
			this.square[k] = new Square(k);
		}

		int count = 0;
		for (Row theRow : this.row) {
			for (Column theColumn : this.column) {

				this.myGameDigits[count] = new GameDigit(Model.boardLayout[count], theRow, theColumn,
						calcSquare(theRow, theColumn), determineFixed(Model.boardLayout[count]));
				count++;
			}
		}
	}
	
	//All the zeros in the data are open for change, as they are just blank cells. Non zeros are the fixed numbers which can't be touched, thus
	//If it's greater than zero, instantiate it's attribute to fixed.
	public boolean determineFixed(int theValue){
		if(theValue > 0){
			return true;
		}
		return false;
	}

	// Refactor the used code later if time.
	// Calculates the square/sector for the Digit based on the column and row
	// passed to it.
	//Assigns the correct square based on the information that is passed in, this is required because
	//when a Digit is instantiated it needs to know what sector it belongs in, thus this method is called to calculate that.
	public Square calcSquare(Row theRow, Column theColumn) {
		// int[] squareReference = { 0, 1, 2 };

		if (theRow.getIndex() < 3) {
			if (theColumn.getIndex() < 3) {
				return square[0];

			} else if (theColumn.getIndex() < 6) {
				return square[1];
			} else {
				return square[2];
			}
		} else if (theRow.getIndex() < 6) {
			if (theColumn.getIndex() < 3) {
				return square[3];

			} else if (theColumn.getIndex() < 6) {
				return square[4];
			} else {
				return square[5];
			}
		} else {
			if (theColumn.getIndex() < 3) {
				return square[6];

			} else if (theColumn.getIndex() < 6) {
				return square[7];
			} else {
				return square[8];
			}
		}

	}
	
	public Row[] getRow(){
		return this.row;
	}
	
	public int getRowCount(){
		return this.row.length;
	}
	
	public Column[] getColumn(){
		return this.column;
	}
	//The next 3 methods allows you to access the row, column and square that a digit resides within.
	public Row getRowByIndex(int theIndex){
		return this.row[theIndex];
	}
	
	public Column getColumnByIndex(int theIndex){
		return this.column[theIndex];
	}
	
	public Square getSquareByIndex(int theIndex){
		return this.square[theIndex];
	}
	
	
}

// cell sizes based on boards.
// 2*2 = 16.
// 3*2 = 36
// 3*3 = 81