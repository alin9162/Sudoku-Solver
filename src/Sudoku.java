/**
 * Author: Andy Lin 
 * Date: February 13, 2016
 * 
 * This application is a Sudoku Solver and Validator that utilizes a backtracking algorith to solve Sudoku puzzles. 
 * Features include puzzle solver, which generates a solution to the given Sudoku puzzle, puzzle validator, which validates a given 
 * solution to the given Sudoku puzzle, and random puzzle generator, which generates a random Sudoku puzzle for users to solve.
 * 
 * References:
 * 		https://en.wikipedia.org/wiki/Sudoku_solving_algorithms
 * 		http://codefordummies.blogspot.ca/2014/01/backtracking-solve-sudoku-in-java.html
 */

import java.util.Stack;
import javax.swing.JOptionPane;

public class Sudoku {
	private int[][] puzzle = new int[9][9];
	private Stack<Square> emptySquareStack;
	
	public Sudoku(int[][] start){
		puzzle = start;
	}
	
	//Show the current puzzle
	public void showPuzzle(){
		for (int i = 0; i < 9 ; i++){
			System.out.println();
			if (i == 3 || i == 6){
				System.out.println("-----------");
			}
			for (int j = 0 ; j < 9; j++){
				//System.out.println("The item at row " + i + " and column " + j + " is " + puzzle[i][j]);
				if (j == 3 || j == 6){
					System.out.print("|");
				}
				if (puzzle[i][j] == 0){
					System.out.print(".");
				}
				else {
					System.out.print(puzzle[i][j]);
				}
				Graphics.updateGraphics(puzzle);
			}
		}
	}
	
	/**
	 * Update the current puzzle status with the given puzzle and initialize the list of empty squares on the puzzle.
	 * @param puzzle
	 * 		The current puzzle status 
	 */
	public void updatePuzzle(int[][] puzzle){
		this.puzzle = puzzle;
		emptySquareStack = new Stack<Square>();
		initializeEmptySquareToStack();
	}
	
	//Set the current puzzle with the values in the given array
	public void setPuzzle(int[] numbers){
		int count = 0;
		for (int i = 0; i < 9 ; i++){
			for (int j = 0 ; j < 9; j++){
				puzzle[i][j] = numbers[count++];
			}
		}
	}
	
	/**
	 * Determines if the current puzzle is a valid solution to the Sudoku
	 * @return
	 * 		True if the current puzzle is a valid solution and false otherwise
	 */
	public boolean isValidSolution(){
		for (int i = 0; i < 9 ; i++){
			if (!validateAll(i)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks if the row and column number are valid rows and columns. Return true if both the row and column number are valid 
	 * and false otherwise
	 * @param rowAndColumnNumber
	 * 		The row and column number that is to be checked
	 * @return
	 * 		True if the row and column number are valid and false otherwise
	 */
	public boolean validateAll(int rowAndColumnNumber){
		for (int i = 0; i < 9 ; i++){
			for (int j = i + 1 ; j < 9 ; j++){
				//Check row 
				if (puzzle[rowAndColumnNumber][i] == puzzle[rowAndColumnNumber][j]){
					System.out.println("Failed at column number " + i + " when checking row " + rowAndColumnNumber);
					return false;
				}
				//Check column
				if (puzzle[i][rowAndColumnNumber] == puzzle[j][rowAndColumnNumber]){
					System.out.println("Failed at row number " + i + " when checking column " + rowAndColumnNumber);
					return false;
				}
			}
		}
		return true;
	}
	 
	/**
	 * Determines if the given row is a valid row by checking for duplicates inside the row
	 * @param rowNumber
	 * 		The row number that is to be checked
	 * @return
	 * 		True if the given row is a valid row and false otherwise
	 */
	public boolean validateRow(int rowNumber){
		for (int i = 0; i < 9 ; i++){
			for (int j = i + 1 ; j < 9 ; j++){
				if (puzzle[rowNumber][i] == puzzle[rowNumber][j]){
					System.out.println("Failed at column number " + i + " when checking row " + rowNumber);
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Determines if the given column is a valid column by checking for duplicates inside the column.
	 * @param columnNumber
	 * 		The column number that is to be checked.
	 * @return
	 * 		True if the column is a valid column and false otherwise.
	 */
	public boolean validateColumn(int columnNumber){
		for (int i = 0; i < 9 ; i++){
			for (int j = i + 1 ; j < 9 ; j++){
				if (puzzle[i][columnNumber] == puzzle[j][columnNumber]){
					System.out.println("Failed at row number " + i + " when checking column " + columnNumber);
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Solves the current state of the Sudoku puzzle using the backtracking algorithm.
	 * Updates the graphics to show the solution if a valid solution can be found, shows and error message if no solution can be found.
	 */
	public void solveSudokuPuzzle(){
		if (solvePuzzle()){
			showPuzzle();
		}
		else{
			JOptionPane.showMessageDialog(null,"Puzzle could not be solved...");
		}
		
	}
	
	/**
	 * Utilizes a backtracking algorithm to test all the different possible solutions until a valid number is found. Once a valid number is 
	 * found, initialize that empty square to the valid number and move on to the next empty square. If nothing can go into that next empty
	 * square, then we backtrack to the original empty square and try another value.
	 * @return
	 * 		True if the stack is empty and there are no more empty values in the puzzle
	 * 		False if none of the possible values fit into the current empty square
	 */
	public boolean solvePuzzle(){
		//Check to see if the entire puzzle is solved by checking if there are any more empty squares.
		//If there are no more empty squares, then return true.
		if (emptySquareStack.isEmpty()){
			return true;
		}
		
		//Loop through all the valid numbers that can be entered into the empty square and check if that value causes a conflict in either
		//the row, column, or cube. If no conflict arises, pop the empty square off of the stack and assign the valid number to the square.
		//Recursively call solvePuzzle to continue solving the puzzle with one less empty square. If this call returns false, then we have reached 
		//an empty square that cannot hold any of the possible values and we must backtrack, setting the current square to empty and pushing the current square 
		//onto the stack to indicate it is empty. If the recursive call returns true, then we can continue with the next empty square. 
		
		for (int i = 1 ; i <= 9 ; i++){
			Square nextEmptySquare = emptySquareStack.peek();
			int row = nextEmptySquare.getRow();
			int column = nextEmptySquare.getColumn();
			
			//Check if there is a conflict in either the row , column or cube with the current number
			if (checkRow(row,i) && checkColumn(column,i) && checkCube(row,column,i)){
				
				//No conflict, so we pop the empty square off of the stack and assign the value to the square.
				emptySquareStack.pop();
				puzzle[row][column] = i;
				
				//Continue solving the puzzle with the next empty square
				if (solvePuzzle()){
					return true;
				}
				
				//The recursive call to solvePuzzle() returned false, which means we have reached a dead end and must backtrack.
				//Assign the current square's value to 0 and push the square to the stack to show that the square is empty
				puzzle[row][column] = 0;
				emptySquareStack.push(nextEmptySquare);
			}
		}
		//No valid number can fit in the square
		return false;
	}
	
	/**
	 * Pushes empty squares onto the stack in reverse order, starting from the bottom right of the puzzle 
	 * all the way to the top left of the puzzle. 
	 */
	public void initializeEmptySquareToStack(){
		for (int i = 8 ;  i >= 0 ; i--){
			for (int j = 8 ; j >= 0 ; j--){
				//If the value at that index is 0, then that square is an empty square and we push that square onto the stack.
				if (puzzle[i][j] == 0){
					emptySquareStack.push(new Square(i,j));
				}
			}
		}
	}
	
	/**
	 * Checks if the given number can be safely inserted into the given row number. 
	 * Returns true if the number is not in the given row, false otherwise.
	 * @param rowNumber
	 * 		The row number that the value is to be inserted in if safe.
	 * @param num
	 * 		The value to be inserted
	 * @return
	 * 		True if the value can be inserted safely into the given row, false otherwise
	 */
	public boolean checkRow (int rowNumber, int num){
		for (int i = 0 ; i < 9 ; i++){
			if (puzzle[rowNumber][i] == num){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks if the given number can be inserted into the given column number.
	 * Return true if the number is not in the given column, false otherwise.
	 * @param columnNumber
	 * 		The column number that the value is to be inserted in if safe.
	 * @param num
	 * 		The value to be inserted
	 * @return
	 * 		True if the value can be inserted safely into the given column, false otherwise
	 */
	public boolean checkColumn (int columnNumber, int num){
		for (int i = 0 ; i < 9 ; i++){
			if (puzzle[i][columnNumber] == num){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks if the given number can be inserted into the given cube.
	 * Returns true if the number is not in the given cube, false otherwise.
	 * References:
	 * 		http://codefordummies.blogspot.ca/2014/01/backtracking-solve-sudoku-in-java.html
	 * 
	 * @param rowNumber
	 * 		The row number that the cube is in.
	 * @param columnNumber
	 * 		The column number that the cube is in.
	 * @param num
	 * 		The number that is to be inserted if possible.
	 * @return
	 * 		True if the value can be inserted safely into the given cube, false otherwise
	 */
	public boolean checkCube(int rowNumber, int columnNumber, int num){
		int rowStart = 3 * (rowNumber / 3);
		int columnStart = 3 * (columnNumber / 3);
		int rowEnd = rowStart + 2 ;
		int columnEnd = columnStart + 2 ;
		
		for (int row = rowStart ; row <= rowEnd ; row ++){
			for (int column = columnStart ; column <= columnEnd ; column++){
				if (puzzle[row][column] == num){
					return false;
				}
			}
		}
		return true;
	}
	
	public int[][] getPuzzle(){
		return puzzle;
	}
}