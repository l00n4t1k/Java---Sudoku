package sudokuModel;

public interface Validating {
	//Is completed, but maybe not correct.
    public boolean isCompleted(); // no zeros
    public boolean isLegal(int cellValue); // no duplicates
    //Is completed and correct
    public boolean isFinished(); // no zeros AND no duplicates
}

