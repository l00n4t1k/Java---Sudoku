package sudokuModel;
//ANDREW MILLER (whole class.)
public class Getter implements Gets{

	protected Model myModel;
	public Getter(Model theModel){
		this.myModel = theModel;
	}
	@Override
	public GameDigit getByRow(Row rowIndex, Column columnIndex) {
		//Row[] rows = Model.playArea.getRow();
		int digitLocation = rowIndex.getIndex() * myModel.playArea.getRowCount() + columnIndex.getIndex();
		return myModel.playArea.myGameDigits[digitLocation];
	}

	/*@Override
	public Digit getBySquare(Index squareIndex, Index positionIndex) {
		// TODO Auto-generated method stub
		return null;
	}*/

}
