package sudokuModel;
//MICHAEL ELCOCK (whole class).
public class GameDigit implements Digit {
	protected Row myRow;
	protected Column myColumn;
	// Represents a sector (not a cell)
	protected Square mySquare;
	// Represents the values of the cell 1 to 9, or zero if it's not entered.
	private int value;
	// These are the numbers that are fixed in place at the start of the game,
	// the user cannot change these.
	private boolean isFixed;
	// Default value for isLegal is true. When the user has entered a value, the
	// input is validated using isLegal()
	// currently implemented in the Model class. If the move is illegal, then
	// isLegal is set to false.
	private boolean isLegal;

	public GameDigit(int theValue, Row theRow, Column theColumn, Square theSquare) {
		this.value = theValue;
		this.myRow = theRow;
		this.myColumn = theColumn;
		this.mySquare = theSquare;
		// LOGIC DISCUSSED BELOW, COVERING MUST NUMBER 3
		// if isFixed is not passed in, then the user must have initiated the
		// call to this method, therefore
		// we do not want to fix the value, as the user must be allowed to
		// change their own values.
		this.isFixed = false;
	}
	//constructor used for fixed numbers.
	// overriding constructor
	public GameDigit(int theValue, Row theRow, Column theColumn, Square theSquare, boolean fixed) {
		this.value = theValue;
		this.myRow = theRow;
		this.myColumn = theColumn;
		this.mySquare = theSquare;
		this.isFixed = fixed;
	}
	
	//This is not the best way to do it, just having to do it to implement existing interfaces.
	//When the user updates the value on the board for a single cell, a GameDigit is instantiated with just a value, it is then sent to a setter method
	//That setter method then adjusts existing cell with the value from this instantiation. User input logic would be in the model.
	/*public GameDigit(int theValue){
		this.value = theValue;
	}*/
	
	protected void setIsLegal(boolean legality){
		this.isLegal = legality;
	}
	
	protected void setValue(int theValue){
		this.value = theValue;
	}
	
	protected int getValue(){
		return this.value;
	}
	
	public Digit getDigit(){
		return this;
	}
	
	public boolean getIsLegal(){
		return this.isLegal;
	}
	
	public void setIsFixed(boolean theFixity){
		this.isFixed = theFixity;
	}
	
	public boolean getIsFixed(){
		return this.isFixed;
	}
	
	public int getRowIndex(){
		return this.myRow.getIndex();
	}
	
	public int getColumnIndex(){
		return this.myColumn.getIndex();
	}
	
	public int getSquareIndex(){
		return this.mySquare.getIndex();
	}
}


