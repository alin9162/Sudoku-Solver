/**
 * Author: Andy Lin 
 * Date: February 18, 2016
 * Class that contains all of the sample Sudoku Puzzles that the user can try to solve and then verify their answer.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PuzzleBank {

	private List<int[][]> exampleBank = new ArrayList<int[][]>();
	
	//10 example Sudoku puzzles
	private int[][] example1 = {{0,0,8,0,0,0,3,0,0},{0,2,0,3,7,6,0,5,0},{6,0,0,0,0,0,0,0,1},{0,3,0,5,0,8,0,4,0},{0,6,0,0,0,0,0,7,0},
			{0,8,0,2,0,7,0,3,0},{4,0,0,0,0,0,0,0,3},{0,5,0,8,1,4,0,6,0},{0,0,6,0,0,0,4,0,0}};
	private int[][] example2 = {{0,0,0,0,0,0,0,0,0},{0,9,5,1,0,8,6,2,0},{0,3,0,0,5,0,0,1,0},{0,2,0,9,0,4,0,3,0},{0,0,8,0,0,0,9,0,0},
			{0,4,0,2,0,7,0,5,0},{0,5,0,0,6,0,0,7,0},{0,6,4,5,0,2,1,8,0},{0,0,0,0,0,0,0,0,0}};
	private int[][] example3 = {{0,0,0,7,5,6,0,0,0},{0,1,7,0,0,0,2,5,0},{4,5,0,0,0,0,0,9,7},{0,0,0,0,3,0,0,0,0},{2,0,3,0,0,0,7,0,8},
			{0,0,0,0,2,0,0,0,0},{3,7,0,0,0,0,0,8,5},{0,4,1,0,0,0,9,2,0},{0,0,0,5,9,4,0,0,0}};
	private int[][] example4 = {{0,7,0,0,1,0,3,5,0},{0,0,0,0,0,0,4,2,0},{9,3,0,5,4,0,0,0,0},{7,8,0,0,0,0,0,0,0},{0,0,4,0,0,0,7,0,0},
			{0,0,0,0,0,0,0,1,5},{0,0,0,0,3,1,0,9,6},{0,2,5,0,0,0,0,0,0},{0,9,3,0,8,0,0,7,0}};
	private int[][] example5 = {{0,0,6,9,0,0,3,4,0},{0,2,0,7,0,0,0,0,0},{0,0,0,0,0,0,2,8,0},{1,7,0,0,0,4,0,2,0},{6,0,0,8,0,5,0,0,9},
			{0,5,0,1,0,0,0,6,4},{0,1,3,0,0,0,0,0,0},{0,0,0,0,0,1,0,3,0},{0,6,2,0,0,8,1,0,0}};
	private int[][] example6 = {{2,0,0,0,0,5,0,7,0},{0,0,4,3,0,0,0,0,2},{0,7,0,0,9,6,0,0,0},{7,0,0,0,5,0,4,0,8},{0,1,0,0,0,3,7,0,0},
			{0,8,0,0,0,0,0,3,0},{8,0,9,0,0,0,0,1,0},{0,3,0,1,6,0,2,0,0},{0,0,1,0,0,8,0,0,7}};
	private int[][] example7 = {{0,0,9,0,5,0,1,0,0},{0,6,0,0,3,0,0,2,0},{0,7,0,0,0,0,0,8,0},{7,0,2,0,0,0,8,0,9},{0,0,0,4,0,2,0,0,0},
			{5,0,6,0,0,0,2,0,7},{0,2,0,0,0,0,0,9,0},{6,0,0,0,8,0,0,0,2},{0,3,7,0,2,0,5,6,0}};
	private int[][] example8 = {{0,4,3,0,0,0,0,0,0},{5,0,0,4,0,0,9,0,8},{0,0,8,0,0,1,0,4,0},{9,0,0,5,0,0,0,0,1},{0,1,0,3,0,6,0,7,0},
			{3,0,0,0,0,4,0,0,2},{0,5,0,6,0,0,8,0,0},{8,0,7,0,0,9,0,0,4},{0,0,0,0,0,0,1,9,0}};
	private int[][] example9 = {{0,3,0,0,0,0,0,5,0},{6,0,0,0,4,0,0,0,9},{5,0,9,0,0,0,1,0,2},{0,8,0,0,0,0,0,1,0},{0,7,0,5,0,4,0,8,0},
			{3,0,5,0,7,0,2,0,6},{7,0,0,0,0,0,0,0,1},{0,6,0,0,0,0,0,7,0},{0,0,3,2,0,7,4,0,0}};
	private int[][] example10 ={{0,0,0,0,0,0,5,7,0},{0,3,5,0,0,8,0,0,9},{0,0,0,5,0,4,1,0,0},{4,0,0,3,0,0,2,0,0},{0,5,0,0,6,0,0,4,0},
			{0,0,2,0,0,5,0,0,3},{0,0,8,9,0,7,0,0,0},{3,0,0,1,0,0,7,2,0},{0,6,7,0,0,0,0,0,0}};
	
	/**
	 * Constructor to add all of the example puzzles to the puzzle bank list.
	 */
	public PuzzleBank(){
		addPuzzle(example1);
		addPuzzle(example2);
		addPuzzle(example3);
		addPuzzle(example4);
		addPuzzle(example5);
		addPuzzle(example6);
		addPuzzle(example7);
		addPuzzle(example8);
		addPuzzle(example9);
		addPuzzle(example10);
	}
	
	/**
	 * Adds a puzzle to the puzzle bank list
	 * @param puzzle
	 * 		The puzzle to be added
	 */
	public void addPuzzle(int[][] puzzle){
		exampleBank.add(puzzle);
	}
	
	/**
	 * Returns a random puzzle from the puzzle bank list
	 * @return
	 * 		A random puzzle from the puzzle bank list.
	 */
	public int[][] getRandomPuzzle(){
		Random rand = new Random();
		//Random is exclusive of the top value so the index will always be inside the bounds, with the lower bound being 0
		int index = rand.nextInt(exampleBank.size());
		return exampleBank.get(index);
	}
}