/**
 * Author: Andy Lin 
 * Date: February 13, 2016
 * 
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Box;

public class Graphics {
	
	private static List<JTextField> textFieldList = new ArrayList<JTextField>();
	private int puzzle[][] = new int[9][9]; 
	private Sudoku sudokuBoard;
	
	/*
	 * Default constructor for the Graphics class that creates a new JFrame and populates the frame with a menu bar, title, Sudoku board, and buttons
	 */
	public Graphics(){
		//Create a new JFrame with the title Sudoku Solver
		JFrame gameFrame = new JFrame("Sudoku Solver");
		
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
		
		sudokuBoard = new Sudoku (puzzle);
		
		//Separation distance of 25 pixels
		container.add(Box.createRigidArea(new Dimension (0,25)));
		
		//Create the buttons
		createButtonPanel(container);

		//Add the panel to the frame
		gameFrame.add(container);
		
		//Make the frame visible
		gameFrame.setVisible(true);
	}
	
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
		//Create a new grid panel layout JPanel that has nine rows and one columns. Create 9 rows and add these 9 rows to the newly
		//created 9x1 grid panel to complete the Sudoku board. Add the Sudoku board to the given JPanel container.
		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(9,1,5,5));
		for(int i= 1; i <= 9; i++){
			JPanel row = createRow();
			gridPanel.add(row);
		}
		container.add(gridPanel);
	}
	
	/**
	 * Create one 1x9 grid of the Sudoku puzzle, and add in text fields for each of the 9 squares
	 * @return
	 * 		The JPanel that contains the 3x3 cube
	 */
	public JPanel createRow(){
		//Create a new JPanel that is of the grid layout, which has one rows and nine columns
		//Add 9 text fields to the newly created grid panel and return the finished 1x9 grid.
		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(1,9,5,5));
		addTextFields(gridPanel);
		//gridPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		return gridPanel;
	}
	
	/**
	 * Adds nine text fields to the given JPanel 
	 * @param gridPanel
	 * 		The JPanel that the text fields will be added to
	 */
	public void addTextFields(JPanel gridPanel){
		//Iterate through the loop 9 times and for each iteration, make a new text field, set the font to size 45 Calibri
		//and center the text in the text field. Add the text field to the given JPanel
		for (int i = 1; i <= 9 ; i++){
			JTextField textField = new JTextField();
			textField.setFont(new Font("Calibri", Font.BOLD, 45));
			textField.setHorizontalAlignment(JTextField.CENTER);
			gridPanel.add(textField);
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
		//Create a new JLabel that acts as the title of the application and set the font and alignment of the JLabel
		//The font is set to size 28 Calibri and the title is centered
		//Add the title to the given JPanel
		JLabel title = new JLabel("Sudoku Validator and Solver");
		title.setFont(new Font("Calibri",Font.BOLD,28));
		title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		container.add(title);
	}
	
	/**
	 * Creates a button panel and adds the button panel to the given JPanel
	 * @param container
	 * 		The JPanel that the button panel will be added to 
	 */
	public void createButtonPanel(JPanel container){
		//Create a new JPanel with the FlowLayout that will be used to store all buttons
		//Create three buttons and add the buttons to the newly created JPanel
		JPanel buttons = new JPanel(new FlowLayout());
		createButton("Solve Puzzle",buttons);
		createButton("Validate Puzzle",buttons);
		createButton("New Puzzle",buttons);
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
		//Add the button to the given button panel and add an action listener for the button.
		JButton button = new JButton(buttonName);
		button.setPreferredSize(new Dimension (300,50));
		button.setFont(new Font ("Calibri", Font.BOLD,20));
		buttonPanel.add(button);
		
		//Action listener for all the buttons
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Solve Puzzle")){
					JOptionPane.showMessageDialog(null, "Solving...");
					updatePuzzle();
					sudokuBoard.updatePuzzle(puzzle);
					sudokuBoard.solveSudokuPuzzle();
					JOptionPane.showMessageDialog(null,"Puzzle Solved!");
				}
				else if (e.getActionCommand().equals("Validate Puzzle")){
					JOptionPane.showMessageDialog(null, "Validating...");
					updatePuzzle();
					if(sudokuBoard.isValidSolution()){
						JOptionPane.showMessageDialog(null,"Valid Solution!");
					}
					else{
						JOptionPane.showMessageDialog(null,"Invalid Solution!");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Clearing...");
					clearPuzzle();
				}
			}
		});
	}
	
	/**
	 * Creates and initializes a new menu bar within the given frame
	 * @param frame
	 * 		The main frame of the application
	 */
	public void createMenuBar(JFrame frame){
		//Create a new JMenuBar and inside that JMenuBar, create a new menu called File, with a "New Puzzle" menu item in it.
		//Create a new menu called About, with a "About Sudoku Validator and Solver" menu item in it.
		//Set the menu bar to the newly created menu bar.
		JMenuBar menuBar = new JMenuBar();									
		createMenu("File","New Puzzle",menuBar);							
		createMenu("About", "About Sudoku Validator and Solver", menuBar);	
		frame.setJMenuBar(menuBar);											
		
	}
	
	/**
	 * Method to create a menu and a menu item given a JMenuBar
	 * @param menuName
	 * 		The name of the menu
	 * @param menuItemName
	 * 		The name of the menu item
	 * @param menuBar
	 * 		The menu bar that this menu will be added to
	 */
	public void createMenu(String menuName, String menuItemName, JMenuBar menuBar){
		//Create a new JMenu with the given menu name, and then create a new JMenuItem with the given menu item name
		//Add the menu item to the menu and set the font of the menu to size 15 Calibri. Add the newly created menu to the given menu bar
		JMenu menu = new JMenu(menuName);					
		JMenuItem menuItem = new JMenuItem(menuItemName);	
		menu.add(menuItem);									
		menu.setFont(new Font("Calibri", Font.PLAIN,15));	
		menuBar.add(menu);									
	}
	
	/**
	 * Update the two dimensional array with the current values in the text fields. 
	 * If the text field is not a numerical value that is between 1 and 9, inform the user that only values between 1 and 9 can be entered. 
	 * If the value is empty, set the array value to 0 to indicate unassigned. 
	 * If the number entered is not a numerical value and is not empty, inform the user to enter a numerical value.
	 */
	public void updatePuzzle(){
		int count = 0;
		for (int i = 0; i < 9; i++){
			for (int j = 0 ; j < 9 ; j++){
				try{
					int value = Integer.parseInt(textFieldList.get(count).getText());
					count++;
					if (value > 0 && value < 10){
						puzzle[i][j] = value;
					}
					else {
						JOptionPane.showMessageDialog(null,"Values must be between 1 and 9!");
						return;
					}
				}
				catch(NumberFormatException e){
					if (textFieldList.get(count++).getText().equals("")){
						puzzle[i][j] = 0;
					}
					else {
						JOptionPane.showMessageDialog(null,"Please enter a numerical value!");
						puzzle[i][j] = 0;
						return;
					}
				}
			}
		}
	}
	
	/**
	 * Clear the puzzle board by setting the values in the text fields to empty strings.
	 */
	public void clearPuzzle(){
		for (JTextField textField: textFieldList){
			textField.setText("");
		}
	}
	
	public Sudoku getSudokuBoard(){
		return sudokuBoard;
	}
}