import static org.junit.Assert.*;

import org.junit.Test;

public class SudokuTest {

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
}
