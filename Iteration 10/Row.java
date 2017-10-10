package sudokuModel;
//MICHAEL ELCOCK (whole class).
public class Row implements Index{
	private int index;
	private boolean hasDuplicates;
	
	public Row(int theIndex){
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
