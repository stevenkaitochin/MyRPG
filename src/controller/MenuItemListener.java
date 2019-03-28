package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

class MenuItemListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// Find out the text of the JMenuItem that was just clicked
		String text = ((JMenuItem) e.getSource()).getText();

		if (text.equals("Reset Characters")) {
			//TODO Tell Game to reset character stats
		}
	}
}
