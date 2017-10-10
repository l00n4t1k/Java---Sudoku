package sudokuModel;

import java.util.Set;

public interface HintPossible {
    //public boolean[] isPossible(int gridIndex);
    //public int[] possibles(int gridIndex);
    //OR
    public Set<Integer> rowPossibilities( int rowIndex);
    public Set<Integer> columnPossibilities( int columnIndex);
    public Set<Integer> squarePossibilities( int squareIndex);
}

