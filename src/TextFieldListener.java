/**
 * Author: Andy Lin 
 * Date: February 13, 2016
 * 
 * Text Field Listener class that detects action inside of the text fields.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class TextFieldListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			//Try to cast the value inside the text field to an integer
			int valueEntered = Integer.parseInt(e.getActionCommand());
			
			//If the integer entered is a valid number, then we tell the user what they typed and give them instructions on how to use the application.
			if (valueEntered < 10 && valueEntered > 0){
				JOptionPane.showMessageDialog(null, "You typed " + e.getActionCommand() + ".\n" + "Please press Validate Puzzle or Solve Puzzle"
						+ " when you finish entering in all the values.");
			}
			//Otherwise, the value is out of the range and we tell the user to enter a number in the correct range
			else {
				JOptionPane.showMessageDialog(null, e.getActionCommand() + " is an invalid input, please type a number between 1 and 9 inclusive");
			}
		}
		//If the value the user entered is not a number, then we tell the user to enter a numerical number
		catch(NumberFormatException exception){
			JOptionPane.showMessageDialog(null, e.getActionCommand() + " is an invalid input, please type a number between 1 and 9 inclusive");
		}
	}
}
