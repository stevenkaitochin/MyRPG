package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Game;
import view.Arena;

@SuppressWarnings("serial")
public class GUI extends JFrame implements Serializable {
	
	private Game game;
	private Arena arena;
	
	public GUI() {
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(150, 50);
		this.setSize(1600, 900);
		this.setTitle("MyRPG");
		
		game = new Game();
		arena = new Arena(game);
		
		game.addObserver(arena);
		
		setupMenu();
		addArenaView(arena);
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
	
	private void addArenaView(JPanel arena) {
		add(arena);
		arena.repaint();
		validate();
	}

	private class MenuItemListener implements ActionListener {
	
		@Override
		public void actionPerformed(ActionEvent e) {
			// Find out the text of the JMenuItem that was just clicked
			String text = ((JMenuItem) e.getSource()).getText();
	
			if (text.equals("Reset Characters")) {
				int userInput = JOptionPane.showConfirmDialog(null, "Reset all character levels?\n", "Select an Option",
						JOptionPane.YES_NO_OPTION);
				if (userInput == JOptionPane.YES_OPTION) {
					Game.resetLevels();
				}
				else {
					return;
				}
			}
			if (text.equals("Save Characters")) {
				int userInput = JOptionPane.showConfirmDialog(null, "Save and overwrite current save?\n", "Select an Option",
						JOptionPane.YES_NO_OPTION);
				if (userInput == JOptionPane.YES_OPTION) {
					Game.saveCharacters();
				}
				else {
					return;
				}
			}
			if (text.equals("Load Characters")) {
				int userInput = JOptionPane.showConfirmDialog(null, "Load previous save? Unsaved progress will be lost.\n", "Select an Option",
						JOptionPane.YES_NO_OPTION);
				if (userInput == JOptionPane.YES_OPTION) {
					Game.loadCharacters();
				}
				else {
					return;
				}
			}
		}
	}
}