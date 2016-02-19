/**
 * Author: Andy Lin
 * Date: February 13, 2016
 * Representation of a single cell in the Sudoku Puzzle, characterized by its unique row and column number.
 */

public class Square {
	private int row;
	private int column;
	
	/**
	 * Constructor to initialize a new Square, given its row and column number.
	 * @param row
	 * 		Row number that the square is in.
	 * @param column
	 * 		Column number that the square is in.
	 */
	public Square (int row, int column){
		this.row = row;
		this.column = column;
	}
	
	/**
	 * Get the row that the square is in.
	 * @return
	 * 		Row number that the square is in.
	 */
	public int getRow(){
		return row;
	}
	
	/**
	 * Get the column that the square is in 
	 * @return
	 * 		Column number that the square is in 
	 */
	public int getColumn(){
		return column;
	}
}