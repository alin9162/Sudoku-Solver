public class Sudoku {
	private int puzzle[][] = new int[9][9];
	
	public Sudoku(int[][] start){
		puzzle = start;
	}
	
	//Show the current puzzle
	public void showPuzzle(int[][] puzzle){
		for (int i = 0; i < 9 ; i++){
			for (int j = 0 ; j < 9; j++){
				System.out.println("The item at row " + i + " and column " + j + " is " + puzzle[i][j]);
			}
		}
	}
	
	public void updatePuzzle(int[][] puzzle){
		this.puzzle = puzzle;
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
	
	//Determines if the current puzzle is a valid solution 
	public boolean isValidSolution(){
		for (int i = 0; i < 9 ; i++){
			if (!validateRow(i) || !validateColumn(i)){
				return false;
			}
		}
		return true;
	}
	
	public void solvePuzzle(){
		
	}
	
	//Determines if the given row is a valid row 
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
	
	//Determines if the given column is a valid column 
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
	
	public boolean validateCube(int cubeNumber){
		return false;
	}
	
	//Function to check if a number can be inserted in the given row
	//Returns true if the number is not in the given row, false otherwise
	public boolean checkRow (int rowNumber, int num){
		for (int i = 0 ; i < 9 ; i++){
			if (puzzle[rowNumber][i] == num){
				System.out.println("Num is "+ num + " column number is " + i);
				return false;
			}
		}
		return true;
	}
	
	//Function to check if a number can be inserted in the given column 
	//Returns true if the number is not in the given column, false otherwise
	public boolean checkColumn (int columnNumber, int num){
		for (int i = 0 ; i < 9 ; i++){
			if (puzzle[i][columnNumber] == num){
				return false;
			}
		}
		return true;
	}
	
	public boolean checkCube(int cubeNumber, int num){
		return false;
	}
}