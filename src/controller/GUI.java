package controller;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.Game;
import view.Arena;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	
	private Game game;
	private Arena arena;
	private JButton fightButton;
	private ButtonGroup buttonGroupLeft, buttonGroupRight;
	
	public GUI() {
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(150, 50);
		this.setSize(1600, 900);
		this.setTitle("MyRPG");
		this.setLayout(new BorderLayout());
		
		game = new Game();
		
		setupMenu();
		addButtons(this);
		addArenaView(this);
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
	
	private void addButtons(GUI gui) {
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new FlowLayout());
		
		JPanel jRadioPanelLeft = new JPanel();
		jRadioPanelLeft.setLayout(new GridLayout(0, 2));
		
		List<JRadioButton> radioButtonsLeft = new ArrayList<JRadioButton>();
		radioButtonsLeft.add(new JRadioButton("Warden"));
		radioButtonsLeft.add(new JRadioButton("Spearman"));
		radioButtonsLeft.add(new JRadioButton("Berserker"));
		radioButtonsLeft.add(new JRadioButton("Raider"));
		radioButtonsLeft.add(new JRadioButton("Knight"));
		radioButtonsLeft.add(new JRadioButton("Archer"));
		radioButtonsLeft.add(new JRadioButton("Ninja"));
		radioButtonsLeft.add(new JRadioButton("Samurai"));
		radioButtonsLeft.add(new JRadioButton("Wizard"));
		radioButtonsLeft.add(new JRadioButton("Mage"));
		radioButtonsLeft.add(new JRadioButton("Lich"));
	
		buttonGroupLeft = new ButtonGroup();
		for (JRadioButton button: radioButtonsLeft) {
			button.setActionCommand(button.getText());
			buttonGroupLeft.add(button);
			jRadioPanelLeft.add(button);
		}
		
		JPanel jRadioPanelRight = new JPanel();
		jRadioPanelRight.setLayout(new GridLayout(0, 2));
		
		List<JRadioButton> radioButtonsRight = new ArrayList<JRadioButton>();
		radioButtonsRight.add(new JRadioButton("Warden"));
		radioButtonsRight.add(new JRadioButton("Spearman"));
		radioButtonsRight.add(new JRadioButton("Berserker"));
		radioButtonsRight.add(new JRadioButton("Raider"));
		radioButtonsRight.add(new JRadioButton("Knight"));
		radioButtonsRight.add(new JRadioButton("Archer"));
		radioButtonsRight.add(new JRadioButton("Ninja"));
		radioButtonsRight.add(new JRadioButton("Samurai"));
		radioButtonsRight.add(new JRadioButton("Wizard"));
		radioButtonsRight.add(new JRadioButton("Mage"));
		radioButtonsRight.add(new JRadioButton("Lich"));
	
		buttonGroupRight = new ButtonGroup();
		for (JRadioButton button: radioButtonsRight) {
			button.setActionCommand(button.getText());
			buttonGroupRight.add(button);
			jRadioPanelRight.add(button);
		}
		
		fightButton = new JButton();
		fightButton.setText("Battle");
		fightButton.setSize(200, 50);
		fightButton.setLocation(500, 0);
		
		ButtonListener buttonListener = new ButtonListener();
		fightButton.addActionListener(buttonListener);
		
		jpanel.add(jRadioPanelLeft);
		jpanel.add(fightButton);
		jpanel.add(jRadioPanelRight);
		gui.add(jpanel, BorderLayout.NORTH);
	}

	private void addArenaView(GUI gui) {
		arena = new Arena(game, null, null);
		gui.add(arena.getArena(), BorderLayout.SOUTH);
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
	
	private class ButtonListener implements ActionListener {
	
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton buttonClicked = (JButton) e.getSource();
			
			if (buttonClicked == fightButton) {
				if (buttonGroupLeft.getSelection() != null && buttonGroupRight.getSelection() != null) {
					arena = new Arena(game, buttonGroupLeft.getSelection().getActionCommand(), buttonGroupRight.getSelection().getActionCommand());
				}
				else {
					System.err.println("You must select two characters to battle.");
				}
			}
		}
	}	
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
