package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.Character;
import model.Game;

@SuppressWarnings("serial")
public class Arena extends JPanel implements Observer {
	
	private Game game;
	private Character leftCharacter, rightCharacter;
	private BufferedImage leftCharacterImage, rightCharacterImage;
	private JButton fightButton;
	private ButtonGroup buttonGroupLeft, buttonGroupRight;

	public Arena(Game game) {
		this.game = game;
		setSize(1600, 900);
		
		addButtons();
	}
	
	private void addButtons() {
		JPanel jButtonPanel = new JPanel();
		jButtonPanel.setLayout(new FlowLayout());
		
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
		
		jButtonPanel.add(jRadioPanelLeft);
		jButtonPanel.add(fightButton);
		jButtonPanel.add(jRadioPanelRight);
		add(jButtonPanel);
	}
	
	public void changeArena(Game game, String leftSelection, String rightSelection) {
		if (leftSelection != null && rightSelection != null) {
			leftCharacter = game.getCharacter(leftSelection);
			rightCharacter = game.getCharacter(rightSelection);
			
			leftCharacterImage = game.getCharacter(leftSelection).img;
			rightCharacterImage = game.getCharacter(rightSelection).img;
			
			JLabel leftLabel = new JLabel(new ImageIcon(leftCharacterImage));
			JLabel rightLabel = new JLabel(new ImageIcon(rightCharacterImage));

            add(leftLabel);
			add(rightLabel);
		}
	}
	
	public JPanel getArena() {
		return this;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		if (leftCharacterImage != null && rightCharacterImage != null) {
			g2.drawImage(leftCharacterImage, 0, 50, null);
			g2.drawImage(rightCharacterImage, 0, 100, null);	
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	
	private class ButtonListener implements ActionListener {
	
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton buttonClicked = (JButton) e.getSource();
			
			if (buttonClicked == fightButton) {
				if (buttonGroupLeft.getSelection() != null && buttonGroupRight.getSelection() != null) {
					changeArena(game, buttonGroupLeft.getSelection().getActionCommand(), buttonGroupRight.getSelection().getActionCommand());
					repaint();
					validate();
				}
				else {
					System.err.println("You must select two characters to battle.");
				}
			}
		}
	}	
}
