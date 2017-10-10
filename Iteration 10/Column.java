package sudokuModel;
//MICHAEL ELCOCK (whole class).
//Column, Row, and Square could all inherit from a superclass, with shared 
public class Column implements Index {
	private int index;
	private boolean hasDuplicates;
	
	public Column(int theIndex) {
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
