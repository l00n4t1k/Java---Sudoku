package sudokuModel;
//Sets the values on the board for the game.
public interface Sets {
	//Sets individual cells, takes in a value, so it knows the value to set, takes in a columnIndex & rowIndex so it
	//knows where to set it.
//	public void setByColumn(Digit value, Index columnIndex, Index rowIndex);
	//Similar logic as above, but looks at the rowIndex first. (takes in same information as above)
	public void setByRow(Digit value, Index rowIndex, Index columnIndex);
	//Takes in a value so it knows what to set, squareIndex represents the sector(box of 9 values) and 
	//positionIndex represents a cells/squares index within the sector.
//	public void setBySquare(Digit value, Index squareIndex, Index positionIndex);

}
