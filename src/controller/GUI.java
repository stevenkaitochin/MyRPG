package controller;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import view.Arena;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	public GUI() {
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(150, 50);
		this.setSize(1600, 900);
		this.setTitle("MyRPG");
		
		setupMenu();
		//loadCharacters();
		
		Arena arena = new Arena();
	}
	
	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.setVisible(true);
	}

	private void setupMenu() {
		JMenuItem mainMenu = new JMenu("Options");
		JMenuItem resetCharacters = new JMenuItem("Reset Characters");
		JMenuItem saveCharacters = new JMenuItem("Save Characters");
		JMenuItem loadCharacters = new JMenuItem("Load Characters");
		mainMenu.add(resetCharacters);
		mainMenu.add(saveCharacters);
		mainMenu.add(loadCharacters);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(mainMenu);
		
	    MenuItemListener menuListener = new MenuItemListener();
	    resetCharacters.addActionListener(menuListener);
	    saveCharacters.addActionListener(menuListener);
	    loadCharacters.addActionListener(menuListener);
	}
}
