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
		JMenuItem saveState = new JMenuItem("Save Current State");
		JMenuItem loadState = new JMenuItem("Load Previous State");
		mainMenu.add(saveState);
		mainMenu.add(loadState);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(mainMenu);
		
	    MenuItemListener menuListener = new MenuItemListener();
	    saveState.addActionListener(menuListener);
	}
}
