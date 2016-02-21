/**
 * Author: Andy Lin 
 * Date: February 13, 2016
 * Graphics class for the Sudoku Solver and Validator application created using Java's Swing and AWT Libraries.
 */

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.Box;

public class Graphics {
	
	private static List<JTextField> textFieldList = new ArrayList<JTextField>();
	private int puzzle[][] = new int[9][9];
	private Sudoku sudokuBoard;
	private PuzzleBank exampleBank;
	int[][] blankPuzzle = {{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}
			,{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}};
	
	/**
	 *  Default constructor for the Graphics class that creates a new JFrame and populates the frame with a menu bar, title, Sudoku board, and buttons
	 */
	public Graphics(){
		//Create a new JFrame with the title Sudoku Solver
		JFrame gameFrame = new JFrame("Sudoku Solver and Validator");
		
		//Set the size of the frame to full screen and exit when the program is closed
		gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Make a new JPanel using the vertical BoxLayout
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
		
		//Create the menu bar
		createMenuBar(gameFrame);
		
		//Separation distance of 25 pixels
		container.add(Box.createRigidArea(new Dimension (0,25)));
		
		//Create the title
		createTitle(container);
		
		//Separation distance of 25 pixels
		container.add(Box.createRigidArea(new Dimension (0,25)));
		
		//Create the Sudoku Board
		createBoard(container);
		
		sudokuBoard = new Sudoku(puzzle);
		exampleBank = new PuzzleBank();
		
		//Separation distance of 25 pixels
		container.add(Box.createRigidArea(new Dimension (0,25)));
		
		//Create the buttons
		createButtonPanel(container);

		//Add the panel to the frame
		gameFrame.add(container);
		
		//Make the frame visible
		gameFrame.setVisible(true);
	}
	
	/**
	 * Update the values in the text fields based on the values passed from the two dimensional array
	 * If the value in the array is a non zero value, update the text field value to the same value, otherwise, set the text field 
	 * value to empty.
	 * @param puzzle
	 * 		Given two dimensional array that the text field values will be updated to 
	 */
	public static void updateGraphics(int[][] puzzle){
		int count = 0;
		for (int i = 0; i < 9; i++){
			for (int j = 0 ; j < 9 ; j++){
				 if (puzzle[i][j] == 0){
					 textFieldList.get(count++).setText("");
				 }
				 else{
					 textFieldList.get(count++).setText(Integer.toString(puzzle[i][j]));
				 }
			}
		}
	}
	
	/**
	 * Create the entire Sudoku board, which consists of nine 3x3 cubes. Add the Sudoku board to the JPanel after it is created
	 * @param container
	 * 		The JPanel that the Sudoku board will be added to
	 */
	public void createBoard(JPanel container){
		//Create a new grid panel layout JPanel that has nine rows and one column. 
		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(9,1,5,5));
		
		//Create 9 columns and add these 9 columns to the newly created 9x1 grid panel to complete the Sudoku board. 
		for(int i= 1; i <= 9; i++){
			JPanel row = createColumn();
			gridPanel.add(row);
		}
		
		//Add the Sudoku board to the given JPanel container.
		container.add(gridPanel);
	}
	
	/**
	 * Create one 1x9 grid of the Sudoku puzzle, and add in text fields for each of the 9 squares
	 * @return
	 * 		The JPanel that contains the 3x3 cube
	 */
	public JPanel createColumn(){
		//Create a new JPanel that is of the grid layout, which has one row and nine columns
		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(1,9,5,5));
		
		//Add 9 text fields to the newly created grid panel and return the finished 1x9 grid.
		addTextFields(gridPanel);
		return gridPanel;
	}
	
	/**
	 * Adds nine text fields to the given JPanel and set the font and alignment of the text in the text field
	 * @param gridPanel
	 * 		The JPanel that the text fields will be added to
	 */
	public void addTextFields(JPanel gridPanel){
		//Iterate through the loop 9 times and for each iteration, add a new text field to the given JPanel.
		for (int i = 1; i <= 9 ; i++){
			//Make a new text field, set the font to size 45 Calibri and center the text in the text field. 
			JTextField textField = new JTextField();
			textField.setFont(new Font("Calibri", Font.BOLD, 45));
			textField.setHorizontalAlignment(JTextField.CENTER);
			
			//Add the text field to the given JPanel
			gridPanel.add(textField);
			
			//Add an action listener for each of the nine text fields.
			TextFieldListener listener = new TextFieldListener();
			textField.addActionListener(listener);
			textFieldList.add(textField);
		}
	}
	
	/**
	 * Creates the title and adds the title to the given JPanel
	 * @param container
	 * 		The JPanel that the title will be added to
	 */
	public void createTitle(JPanel container){
		//Create a new JLabel that acts as the title of the application.
		JLabel title = new JLabel("Sudoku Validator and Solver");
		
		//Set the font to size 28 Calibri and center the title
		title.setFont(new Font("Calibri",Font.BOLD,28));
		title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		//Add the title to the given JPanel
		container.add(title);
	}
	
	/**
	 * Creates a button panel and adds the button panel to the given JPanel
	 * Also adds a scroll pane in order to let the user select which example puzzle they would like to load
	 * @param container
	 * 		The JPanel that the button panel will be added to 
	 */
	public void createButtonPanel(JPanel container){
		//Create a new JPanel with the FlowLayout that will be used to store all buttons
		JPanel buttons = new JPanel(new FlowLayout());
		
		//Create four buttons and add the buttons to the newly created JPanel
		createButton("Solve Puzzle",buttons);
		createButton("Validate Puzzle",buttons);
		createButton("Random Puzzle", buttons);
		createButton("New Puzzle",buttons);
		
		//Add a new JList with a JScrollPane to let the user select which example puzzle they would like to load
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		//Add the list elements
		for (int i = 1 ; i <= 10 ; i++){
			listModel.addElement("Example Puzzle " + i);
		}
		JList<String> list = new JList<String>(listModel);
		
		//Add a List Selection Listener to recognize when the user has made a selection
		list.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				//Identify which list element has been selected
				JList source = (JList) e.getSource();
				String item = source.getSelectedValue().toString();
				JOptionPane.showMessageDialog(null,item + " loading...");
				
				//Split the string to isolate the integer value, which is the puzzle number and load that specific puzzle from the puzzle bank
				String itemArray[] = item.split(" ");
				sudokuBoard.updatePuzzle(exampleBank.getPuzzleNumber(Integer.parseInt(itemArray[2]) - 1));
			}
			
		});
		
		//Set the font and dimensions of the list and scroll pane and add the scroll pane to the button panel
		list.setFont(new Font("Calibri", Font.BOLD, 15));
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension (150,150));
		buttons.add(scrollPane);
		
		//Add the button panel to the given JPanel
		container.add(buttons);
	}
	
	/**
	 * Creates a button with the given name and adds the button to the given button panel
	 * @param buttonName
	 * 		The name of the button to be created
	 * @param buttonPanel
	 * 		The button panel that the button will be added to 
	 */
	public void createButton(String buttonName, JPanel buttonPanel){
		//Create a new button with a set size of 300 by 50 pixels and set the font to size 20 Calibri 
		JButton button = new JButton(buttonName);
		button.setPreferredSize(new Dimension (300,50));
		button.setFont(new Font ("Calibri", Font.BOLD,20));
		
		//Add the button to the given button panel
		buttonPanel.add(button);
		
		//Add an action listener for all the buttons
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Solve Puzzle")){
					solvePuzzle();
				}
				else if (e.getActionCommand().equals("Validate Puzzle")){
					validatePuzzle();
				}
				else if (e.getActionCommand().equals("Random Puzzle")){
					getRandomPuzzle();
				}
				else {
					clearPuzzle();
				}
			}
		});
	}
	
	/**
	 * Update the two dimensional array with the current values in the text fields. 
	 * If the text field is not a numerical value that is between 1 and 9, inform the user that only values between 1 and 9 can be entered and return false. 
	 * If the value is empty, set the array value to 0 to indicate unassigned. 
	 * If the number entered is not a numerical value and is not empty, inform the user to enter a numerical value and return false.
	 * @return
	 * 		True if the puzzle is a valid puzzle (all text fields either empty or has an integer between 1 and 9)
	 * 		False if the puzzle is invalid
	 */
	public boolean updatePuzzle(){
		int count = 0;
		for (int i = 0; i < 9; i++){
			for (int j = 0 ; j < 9 ; j++){
				try{
					//Try to cast the string in the text field to an integer
					int value = Integer.parseInt(textFieldList.get(count).getText());
					count++;
					//If the value is in the range of possible values, then we assign that value to the array
					if (value > 0 && value < 10){
						puzzle[i][j] = value;
					}
					//Otherwise, we tell the user that the value is invalid and return false
					else {
						JOptionPane.showMessageDialog(null,"Values must be between 1 and 9!");
						return false;
					}
				}
				//If the value the user entered was not a number, then we check if the text field was empty 
				catch(NumberFormatException e){
					//If the text field is empty, then assign 0 to the index of the array to indicate unassigned
					if (textFieldList.get(count++).getText().equals("")){
						puzzle[i][j] = 0;
					}
					//Otherwise, the text field value is a symbol and we tell the user to enter a numerical value and return false
					else {
						JOptionPane.showMessageDialog(null,"Please enter a numerical value!");
						puzzle[i][j] = 0;
						return false;
					}
				}
			}
		}
		//Return true if the entire puzzle has valid entries
		return true;
	}
	
	/**
	 * Update the current puzzle based on the values given in the text fields. Try to solve the puzzle in the given state.
	 */
	public void solvePuzzle(){
		JOptionPane.showMessageDialog(null, "Solving...");
		//Check to see if the puzzle has valid entries first, if it does then attempt to solve the puzzle
		if (updatePuzzle()){
			sudokuBoard.updatePuzzle(puzzle);
			sudokuBoard.solveSudokuPuzzle();
		}
		//If the puzzle has invalid entries, then tell the user that the puzzle is invalid
		else{
			JOptionPane.showMessageDialog(null,"Invalid Puzzle!");
		}
	}
	
	/**
	 * Update the current puzzle based on the values given in the text fields. Validate the solution and if the solution is valid
	 * show a message saying "Valid Solution", otherwise, show a message saying "Invalid Solution"
	 */
	public void validatePuzzle(){
		JOptionPane.showMessageDialog(null, "Validating...");
		//Check to see if the puzzle has invalid entries first, if it does not, then try to validate the puzzle
		if (updatePuzzle()){
			if(sudokuBoard.isValidSolution()){
				JOptionPane.showMessageDialog(null,"Valid Solution!");
			}
			else {
				JOptionPane.showMessageDialog(null,"Invalid Solution!");
			}
		}
		//If the puzzle has invalid entries, then tell the user that the puzzle is invalid
		else{
			JOptionPane.showMessageDialog(null,"Invalid Puzzle!");
		}
	}
	
	/**
	 * Load a random puzzle from the example bank and update the current Sudoku puzzle with the random puzzle
	 */
	public void getRandomPuzzle(){
		JOptionPane.showMessageDialog(null, "Loading random puzzle...");
		sudokuBoard.updatePuzzle(exampleBank.getRandomPuzzle());
	}
	
	/**
	 * Clear the puzzle board by setting the values in the text fields to empty strings and updating 
	 * the current Sudoku puzzle to the blank puzzle.
	 */
	public void clearPuzzle(){
		JOptionPane.showMessageDialog(null, "Clearing...");
		for (JTextField textField: textFieldList){
			textField.setText("");
		}
		sudokuBoard.updatePuzzle(blankPuzzle);
	}
	
	/**
	 * Creates and initializes a new menu bar and adds new menus to the newly created menu bar.
	 * Set the menu bar to the given frame
	 * @param frame
	 * 		The main frame of the application
	 */
	public void createMenuBar(JFrame frame){
		//Create a new menu bar
		JMenuBar menuBar = new JMenuBar();			
		
		//Create two new menus
		JMenu file = new JMenu("File");
		JMenu about = new JMenu("About");
		
		//Create menu items within the menus
		createMenuItem("Solve Puzzle", file);
		createMenuItem("Validate Puzzle", file);
		createMenuItem("Random Puzzle", file);
		createMenuItem("New Puzzle",file);
		createMenuItem("About", about);
		
		//Add the menus to the menu bar
		menuBar.add(file);
		menuBar.add(about);
		
		//Set the newly created menu bar as the menu bar within the frame
		frame.setJMenuBar(menuBar);												
	}
	
	/**
	 * Method to create a new menu item given a menu and add the menu item to the menu
	 * @param menuItemName
	 * 		The name of the menu item
	 * @param menu
	 * 		The menu that the menu item will be added to
	 */
	public void createMenuItem(String menuItemName, JMenu menu){
		//Create a new JMenuItem with the given menu item name inside the given menu
		//Add the menu item to the menu and set the font of the menu to size 15 Calibri. 
		JMenuItem menuItem = new JMenuItem(menuItemName);	
		menu.add(menuItem);					
		menu.setFont(new Font("Calibri", Font.PLAIN,15));	
		
		//Action Listener for all the menu items
		menuItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Solve Puzzle")){
					solvePuzzle();
				}
				else if (e.getActionCommand().equals("Validate Puzzle")){
					validatePuzzle();
				}
				else if (e.getActionCommand().equals("Random Puzzle")){
					getRandomPuzzle();
				}
				else if (e.getActionCommand().equals("New Puzzle")){
					clearPuzzle();
				}
				else{
					JOptionPane.showMessageDialog(null, "Author: Andy Lin ");
				}
			}
		});
	}
	
	/**
	 * Getter method to get the reference to the Sudoku object
	 * @return
	 * 		Reference to the Sudoku object
	 */
	public Sudoku getSudokuBoard(){
		return sudokuBoard;
	}
}