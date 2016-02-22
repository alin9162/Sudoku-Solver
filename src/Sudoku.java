/**
 * Author: Andy Lin 
 * Date: February 13, 2016
 * 
 * This application is a Sudoku Solver and Validator that utilizes a backtracking algorithm to solve Sudoku puzzles. 
 * Features include puzzle solver, which generates a solution to the given Sudoku puzzle, puzzle validator, which validates a given 
 * solution to the given Sudoku puzzle, and random puzzle generator, which generates a random Sudoku puzzle for users to solve.
 * 
 * References:
 * 		https://en.wikipedia.org/wiki/Sudoku_solving_algorithms
 * 		http://codefordummies.blogspot.ca/2014/01/backtracking-solve-sudoku-in-java.html
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import javax.swing.JOptionPane;

public class Sudoku {
	private int[][] puzzle = new int[9][9];
	private Stack<Square> emptySquareStack;
	
	private List<Set<Integer>> rowPossibleValueList; 
	private List<Set<Integer>> columnPossibleValueList; 
	private List<Set<Integer>> cubePossibleValueList; 
	
	public Sudoku(int[][] start){
		puzzle = start;
	}
	
	/**
	 * Update the current puzzle status with the given puzzle and initialize the list of empty squares on the puzzle.
	 * This method also updates the graphics that the user sees with the given puzzle
	 * @param puzzle
	 * 		The current puzzle status 
	 */
	public void updatePuzzle(int[][] puzzle){
		this.puzzle = puzzle;
		emptySquareStack = new Stack<Square>();
		initializeEmptySquareToStack();
		initializeSets();
		Graphics.updateGraphics(puzzle);
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
	 * Determines if the given input is a valid input
	 * Returns false if there are duplicates in the row, column or cube
	 * @return
	 * 		True if the given puzzle input is a valid input
	 * 		False if there is a duplicate number in the row, column or cube
	 */
	public boolean isValidInputPuzzle(){
		for (int i = 0 ; i < 9 ; i++){
			for (int j = 0; j < 9 ; j++){
				if (puzzle[i][j] != 0){
					if (!rowPossibleValueList.get(i).remove(puzzle[i][j])){
						return false;
					}
					if(!columnPossibleValueList.get(j).remove(puzzle[i][j])){
						return false;
					}
					if(!cubePossibleValueList.get(determineCube(i,j)).remove(puzzle[i][j])){
						return false;
					}			
				}
			}
		}
		return true;
	}
	
	/**
	 * Determines the cube number that the square is in given the row number and the column number
	 * @param rowNumber
	 * 		The row number that the square is in
	 * @param columnNumber
	 * 		The column number that the square is in
	 * @return
	 * 		The cube number that the square is in. Cubes are arbitrarily numbered from 0-8 starting from the top left to bottom right.
	 * 		0 	1	2
	 * 		3	4	5	
	 * 		6	7	8
	 */
	public int determineCube(int rowNumber, int columnNumber){
		int rowStart = rowNumber / 3;
		int columnStart = columnNumber / 3;
		
		//If rowStart is 0, then the square is in the first row of cubes and is only dependent on columnStart
		if (rowStart == 0){
			return columnStart;
		}
		//If rowStart is 1, then the square is in the second row of cubes and the correct cube number can be found using columnStart + 3
		else if (rowStart == 1){
			return columnStart + 3;
		}
		//If rowStart is 2, then the square is in the third row of cubes and the correct cube number can be found using columnStart + 6
		else{
			return columnStart + 6;
		}
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
				//Check if the row is a valid row 
				if (puzzle[rowAndColumnNumber][i] == puzzle[rowAndColumnNumber][j]){
					return false;
				}
				//Check if the column is a valid column
				if (puzzle[i][rowAndColumnNumber] == puzzle[j][rowAndColumnNumber]){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Solves the current state of the Sudoku puzzle using the backtracking algorithm.
	 * Updates the graphics to show the solution if a valid solution can be found, shows an error message if no solution can be found.
	 */
	public void solveSudokuPuzzle(){
		if (isValidInputPuzzle()){
			if (solvePuzzle()){
				Graphics.updateGraphics(puzzle);
				JOptionPane.showMessageDialog(null,"Puzzle Solved!");
			}
			else{
				JOptionPane.showMessageDialog(null,"Puzzle could not be solved...");
			}
		}
		else {
			JOptionPane.showMessageDialog(null,"Input puzzle not valid due to duplicate numbers in a row, column or cube");
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
	 * Initialize the three ArrayLists to hold nine sets that contain the current non-used values between 1 and 9.
	 * Each ArrayList is responsible for 9 sets that correspond to the 9 rows, 9 columns and 9 cubes of the puzzle
	 */
	public void initializeSets(){
		//Create the three ArrayLists to hold the possible values for the rows, columns and cubes
		rowPossibleValueList  = new ArrayList<Set<Integer>>();
		columnPossibleValueList  = new ArrayList<Set<Integer>>();
		cubePossibleValueList = new ArrayList<Set<Integer>>();
		
		for (int i = 0 ; i < 27 ; i++){
			//Create a new HashSet and put the values 1-9 inside of the HashSet
			Set<Integer> set = new HashSet<Integer>();
			for (int j = 1 ; j <= 9 ; j++){
				set.add(j);
			}
			//Add 9 sets to each list
			if (i < 9){
				rowPossibleValueList.add(set);
			}
			else if (i < 18){
				columnPossibleValueList.add(set);
			}
			else {
				cubePossibleValueList.add(set);
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
	
	/**
	 * Getter method to return the current puzzle status
	 * @return
	 * 		The current status of the puzzle
	 */
	public int[][] getPuzzle(){
		return puzzle;
	}
}