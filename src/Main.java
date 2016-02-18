public class Main {
	
	public static void main (String args[]){
		//new Sudoku();
		//new TestGraphics();
		
		Graphics graphics = new Graphics();
		int[] numbers = {0,0,0,5,7,4,0,0,0,0,6,9,0,8,0,7,4,0,0,5,0,0,0,0,0,2,0,2,0,0,0,9,0,0,0,4,9,7,0,3,0,5,0,1,8,5,0,0,0,2,0,0,0,7
				,0,4,0,0,0,0,0,5,0,0,3,5,0,1,0,9,7,0,0,0,0,7,5,6,0,0,0};
		graphics.getSudokuBoard().setPuzzle(numbers);
		graphics.getSudokuBoard().showPuzzle();
		graphics.getSudokuBoard().updatePuzzle(graphics.getSudokuBoard().getPuzzle());
	}
}
