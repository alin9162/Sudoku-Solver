//Representation of a single cell in the Sudoku Puzzle, characterized by its unique row and column number.
public class Square {
	private int row;
	private int column;
	
	public Square (int row, int column){
		this.row = row;
		this.column = column;
	}
	
	public int getRow(){
		return row;
	}
	
	public int getColumn(){
		return column;
	}
}