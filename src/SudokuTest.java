import static org.junit.Assert.*;

import org.junit.Test;

public class SudokuTest {

	//First 10 tests are used to test the 10 example puzzles
	@Test
	public void test1() {
		Graphics graphics = new Graphics();
		int[][] example1 = {{0,0,8,0,0,0,3,0,0},{0,2,0,3,7,6,0,5,0},{6,0,0,0,0,0,0,0,1}, {0,3,0,5,0,8,0,4,0}, {0,6,0,0,0,0,0,7,0},
				{0,8,0,2,0,7,0,3,0},{4,0,0,0,0,0,0,0,3},{0,5,0,8,1,4,0,6,0},{0,0,6,0,0,0,4,0,0}};
		graphics.getSudokuBoard().updatePuzzle(example1);
		graphics.getSudokuBoard().solvePuzzle();
		assertTrue(graphics.getSudokuBoard().isValidSolution());
	}
	
	@Test
	public void test2() {
		Graphics graphics = new Graphics();
		int[][] example2 = {{0,0,0,0,0,0,0,0,0},{0,9,5,1,0,8,6,2,0},{0,3,0,0,5,0,0,1,0},{0,2,0,9,0,4,0,3,0},{0,0,8,0,0,0,9,0,0},
				{0,4,0,2,0,7,0,5,0},{0,5,0,0,6,0,0,7,0},{0,6,4,5,0,2,1,8,0},{0,0,0,0,0,0,0,0,0}};
		graphics.getSudokuBoard().updatePuzzle(example2);
		graphics.getSudokuBoard().solvePuzzle();
		assertTrue(graphics.getSudokuBoard().isValidSolution());
	}
	
	@Test
	public void test3() {
		Graphics graphics = new Graphics();
		int[][] example3 = {{0,0,0,7,5,6,0,0,0},{0,1,7,0,0,0,2,5,0},{4,5,0,0,0,0,0,9,7},{0,0,0,0,3,0,0,0,0},{2,0,3,0,0,0,7,0,8},
				{0,0,0,0,2,0,0,0,0},{3,7,0,0,0,0,0,8,5},{0,4,1,0,0,0,9,2,0},{0,0,0,5,9,4,0,0,0}};
		graphics.getSudokuBoard().updatePuzzle(example3);
		graphics.getSudokuBoard().solvePuzzle();
		assertTrue(graphics.getSudokuBoard().isValidSolution());
	}
	
	@Test
	public void test4() {
		Graphics graphics = new Graphics();
		int[][] example4 = {{0,7,0,0,1,0,3,5,0},{0,0,0,0,0,0,4,2,0},{9,3,0,5,4,0,0,0,0},{7,8,0,0,0,0,0,0,0},{0,0,4,0,0,0,7,0,0},
				{0,0,0,0,0,0,0,1,5},{0,0,0,0,3,1,0,9,6},{0,2,5,0,0,0,0,0,0},{0,9,3,0,8,0,0,7,0}};
		graphics.getSudokuBoard().updatePuzzle(example4);
		graphics.getSudokuBoard().solvePuzzle();
		assertTrue(graphics.getSudokuBoard().isValidSolution());
	}
	
	@Test
	public void test5() {
		Graphics graphics = new Graphics();
		int[][] example5 = {{0,0,6,9,0,0,3,4,0},{0,2,0,7,0,0,0,0,0},{0,0,0,0,0,0,2,8,0},{1,7,0,0,0,4,0,2,0},{6,0,0,8,0,5,0,0,9},
				{0,5,0,1,0,0,0,6,4},{0,1,3,0,0,0,0,0,0},{0,0,0,0,0,1,0,3,0},{0,6,2,0,0,8,1,0,0}};
		graphics.getSudokuBoard().updatePuzzle(example5);
		graphics.getSudokuBoard().solvePuzzle();
		assertTrue(graphics.getSudokuBoard().isValidSolution());
	}
	
	@Test
	public void test6() {
		Graphics graphics = new Graphics();
		int[][] example6 = {{2,0,0,0,0,5,0,7,0},{0,0,4,3,0,0,0,0,2},{0,7,0,0,9,6,0,0,0},{7,0,0,0,5,0,4,0,8},{0,1,0,0,0,3,7,0,0},
				{0,8,0,0,0,0,0,3,0},{8,0,9,0,0,0,0,1,0},{0,3,0,1,6,0,2,0,0},{0,0,1,0,0,8,0,0,7}};
		graphics.getSudokuBoard().updatePuzzle(example6);
		graphics.getSudokuBoard().solvePuzzle();
		assertTrue(graphics.getSudokuBoard().isValidSolution());
	}
	
	@Test
	public void test7() {
		Graphics graphics = new Graphics();
		int[][] example7 = {{0,0,9,0,5,0,1,0,0},{0,6,0,0,3,0,0,2,0},{0,7,0,0,0,0,0,8,0},{7,0,2,0,0,0,8,0,9},{0,0,0,4,0,2,0,0,0},
				{5,0,6,0,0,0,2,0,7},{0,2,0,0,0,0,0,9,0},{6,0,0,0,8,0,0,0,2},{0,3,7,0,2,0,5,6,0}};
		graphics.getSudokuBoard().updatePuzzle(example7);
		graphics.getSudokuBoard().solvePuzzle();
		assertTrue(graphics.getSudokuBoard().isValidSolution());
	}
	
	@Test
	public void test8() {
		Graphics graphics = new Graphics();
		int[][] example8 = {{0,4,3,0,0,0,0,0,0},{5,0,0,4,0,0,9,0,8},{0,0,8,0,0,1,0,4,0},{9,0,0,5,0,0,0,0,1},{0,1,0,3,0,6,0,7,0},
				{3,0,0,0,0,4,0,0,2},{0,5,0,6,0,0,8,0,0},{8,0,7,0,0,9,0,0,4},{0,0,0,0,0,0,1,9,0}};
		graphics.getSudokuBoard().updatePuzzle(example8);
		graphics.getSudokuBoard().solvePuzzle();
		assertTrue(graphics.getSudokuBoard().isValidSolution());
	}
	
	@Test
	public void test9() {
		Graphics graphics = new Graphics();
		int[][] example9 = {{0,3,0,0,0,0,0,5,0},{6,0,0,0,4,0,0,0,9},{5,0,9,0,0,0,1,0,2},{0,8,0,0,0,0,0,1,0},{0,7,0,5,0,4,0,8,0},
				{3,0,5,0,7,0,2,0,6},{7,0,0,0,0,0,0,0,1},{0,6,0,0,0,0,0,7,0},{0,0,3,2,0,7,4,0,0}};
		graphics.getSudokuBoard().updatePuzzle(example9);
		graphics.getSudokuBoard().solvePuzzle();
		assertTrue(graphics.getSudokuBoard().isValidSolution());
	}
	
	@Test
	public void test10() {
		Graphics graphics = new Graphics();
		int[][] example10 ={{0,0,0,0,0,0,5,7,0},{0,3,5,0,0,8,0,0,9},{0,0,0,5,0,4,1,0,0},{4,0,0,3,0,0,2,0,0},{0,5,0,0,6,0,0,4,0},
				{0,0,2,0,0,5,0,0,3},{0,0,8,9,0,7,0,0,0},{3,0,0,1,0,0,7,2,0},{0,6,7,0,0,0,0,0,0}};
		graphics.getSudokuBoard().updatePuzzle(example10);
		graphics.getSudokuBoard().solvePuzzle();
		assertTrue(graphics.getSudokuBoard().isValidSolution());
	}
	
	//Test error checking with an invalid puzzle with duplicate numbers in the row
	@Test
	public void test11() {
		Graphics graphics = new Graphics();
		int[][] invalidPuzzleRow ={{1,0,0,1,0,0,5,7,0},{0,3,5,0,0,8,0,0,9},{0,0,0,5,0,4,1,0,0},{4,0,0,3,0,0,2,0,0},{0,5,0,0,6,0,0,4,0},
				{0,0,2,0,0,5,0,0,3},{0,0,8,9,0,7,0,0,0},{3,0,0,1,0,0,7,2,0},{0,6,7,0,0,0,0,0,0}};
		graphics.getSudokuBoard().updatePuzzle(invalidPuzzleRow);
		assertFalse(graphics.getSudokuBoard().isValidInputPuzzle());
	}
	
	//Test error checking with an invalid puzzle with duplicate numbers in the column
	@Test
	public void test12() {
		Graphics graphics = new Graphics();
		int[][] invalidPuzzleColumn ={{1,0,0,0,0,0,5,7,0},{1,3,5,0,0,8,0,0,9},{0,0,0,5,0,4,1,0,0},{4,0,0,3,0,0,2,0,0},{0,5,0,0,6,0,0,4,0},
				{0,0,2,0,0,5,0,0,3},{0,0,8,9,0,7,0,0,0},{3,0,0,1,0,0,7,2,0},{0,6,7,0,0,0,0,0,0}};
		graphics.getSudokuBoard().updatePuzzle(invalidPuzzleColumn);
		assertFalse(graphics.getSudokuBoard().isValidInputPuzzle());
	}
	
	//Test error checking with an invalid puzzle with duplicate numbers in the cube
	@Test
	public void test13() {
		Graphics graphics = new Graphics();
		int[][] invalidPuzzleCube ={{1,0,0,0,0,0,5,7,0},{0,3,5,0,0,8,0,0,9},{0,1,0,5,0,4,1,0,0},{4,0,0,3,0,0,2,0,0},{0,5,0,0,6,0,0,4,0},
				{0,0,2,0,0,5,0,0,3},{0,0,8,9,0,7,0,0,0},{3,0,0,1,0,0,7,2,0},{0,6,7,0,0,0,0,0,0}};
		graphics.getSudokuBoard().updatePuzzle(invalidPuzzleCube);
		assertFalse(graphics.getSudokuBoard().isValidInputPuzzle());
	}	
}