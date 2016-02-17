import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ButtonHandler implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "You pressed " + e.getActionCommand());
		if (e.getActionCommand().equals("Solve Puzzle")){
			JOptionPane.showMessageDialog(null, "Solving...");
			
		}
		else if (e.getActionCommand().equals("Validate Puzzle")){
			JOptionPane.showMessageDialog(null, "Validating...");
		}
		else {
			JOptionPane.showMessageDialog(null, "Clearing...");
		}
	}
}
