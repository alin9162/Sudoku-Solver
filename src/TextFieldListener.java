import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class TextFieldListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			int valueEntered = Integer.parseInt(e.getActionCommand());
			if (valueEntered < 10 && valueEntered > 0){
				JOptionPane.showMessageDialog(null, "You typed " + e.getActionCommand() + ".\n" + "Please press Validate Puzzle or Solve Puzzle"
						+ " when you finish entering in all the values.");
			}
			else {
				JOptionPane.showMessageDialog(null, e.getActionCommand() + " is an invalid input, please type a number between 1 and 9 inclusive");
			}
		}
		catch(NumberFormatException exception){
			JOptionPane.showMessageDialog(null, e.getActionCommand() + " is an invalid input, please type a number between 1 and 9 inclusive");
		}
	}
}
