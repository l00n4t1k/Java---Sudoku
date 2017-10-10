package sudokuModel;
//MICHAEL ELCOCK (whole class).
//Is a sector (not a cell)
public class Square implements Index {
	private int index;
	private boolean hasDuplicates;

	public Square(int theIndex) {
		this.index = theIndex;
		this.hasDuplicates = false;
	}

	public int getIndex() {
		return this.index;
	}
	
	public void setHasDuplicates(boolean isDuplicated){
		this.hasDuplicates = isDuplicated;
	}
	
	public boolean getHasDuplicates(){
		return this.hasDuplicates;
	}
}
