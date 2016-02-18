import static org.junit.Assert.*;

import org.junit.Test;

public class SudokuTest {

	@Test
	public void test1() {
		Graphics graphics = new Graphics();
		int[] numbers = {6,7,8,1,3,2,9,5,4,5,3,4,8,7,9,6,2,1,1,9,2,5,4,6,7,8,3,3,1,5,6,2,7,4,9,8,7,4,9,3,1,8,5,6,2,
				8,2,6,9,5,4,3,1,7,9,6,3,4,8,1,2,7,5,2,5,1,7,6,3,8,4,9,4,8,7,2,9,5,1,3,6};
		graphics.getSudokuBoard().setPuzzle(numbers);
		assertTrue(graphics.getSudokuBoard().isValidSolution());
	}
	
	@Test
	public void test2() {
		Graphics graphics = new Graphics();
		int[] numbers = {0,0,0,5,7,4,0,0,0,0,6,9,0,8,0,7,4,0,0,5,0,0,0,0,0,2,0,2,0,0,0,9,0,0,0,4,9,7,0,3,0,5,0,1,8,5,0,0,0,2,0,0,0,7
				,0,4,0,0,0,0,0,5,0,0,3,5,0,1,0,9,7,0,0,0,0,7,5,6,0,0,0};
		graphics.getSudokuBoard().setPuzzle(numbers);
		//graphics.getSudokuBoard().solvePuzzle();
	}
}
