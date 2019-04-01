package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import model.Game;
import view.Arena;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	
	private Game game;
	private Arena arena;
	
	public GUI() {
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(150, 50);
		this.setSize(1600, 900);
		this.setTitle("MyRPG");
		
		game = new Game();
		arena = new Arena(game);
		
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
				Game.resetLevels();
			}
			if (text.equals("Save Characters")) {
				Game.saveCharacters();
			}
			if (text.equals("Load Characters")) {
				Game.loadCharacters();
			}
		}
	}
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
