package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
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
	private BufferedImage wardenImg, spearmanImg, berserkerImg, raiderImg, knightImg, archerImg,
						  ninjaImg, samuraiImg, wizardImg, mageImg, lichImg;
	
	private JButton fightButton;
	private ButtonGroup buttonGroupLeft, buttonGroupRight;
	
	private JLabel arenaGrid[][];
	
	private JPanel characterPanel;
	private JLabel leftLabel, rightLabel;

	public Arena(Game game) {
		this.game = game;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		addButtons();
		loadImages();
		addArena(game);
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
	
	private void loadImages() {
		try {
			wardenImg = ImageIO.read(new File("images/warden.png"));
			spearmanImg = ImageIO.read(new File("images/spearman.png"));
			berserkerImg = ImageIO.read(new File("images/berserker.png"));
			raiderImg = ImageIO.read(new File("images/raider.png"));
			knightImg = ImageIO.read(new File("images/knight.png"));
			archerImg = ImageIO.read(new File("images/archer.png"));
			ninjaImg = ImageIO.read(new File("images/ninja.png"));
			samuraiImg = ImageIO.read(new File("images/samurai.png"));
			wizardImg = ImageIO.read(new File("images/wizard.png"));
			mageImg = ImageIO.read(new File("images/mage.png"));
			lichImg = ImageIO.read(new File("images/lich.png"));
		} catch (IOException e) {
			System.out.println("Cannot find image path");
		}
	}
	
	public void addArena(Game game) {
		setBackground(Color.WHITE);
		
		characterPanel = new JPanel();
		characterPanel.setPreferredSize(new Dimension(1200, 600));
		characterPanel.setBackground(Color.WHITE);
		characterPanel.setLayout(new GridLayout(3, 4));
		
		int r = 3;
		int c = 5;
	    arenaGrid = new JLabel[r][c];    

		for(int i = 0; i < r; i++) {
		   for(int j = 0; j < c; j++) {
		      arenaGrid[i][j] = new JLabel();
		      characterPanel.add(arenaGrid[i][j]);
		   }
		}
		
		leftLabel = new JLabel();
		leftLabel = arenaGrid[1][1];
		
		rightLabel = new JLabel();
		rightLabel = arenaGrid[1][3];

		add(characterPanel);
	}
	
	public void changeArena(Game game, String leftSelection, String rightSelection) {
		if (leftSelection != null && rightSelection != null) {
			leftCharacter = game.getCharacter(leftSelection);
			rightCharacter = game.getCharacter(rightSelection);
			
			switch (leftSelection) {
			case "Warden":
				leftCharacterImage = wardenImg;
				break;
			case "Spearman":
				leftCharacterImage = spearmanImg;
				break;
			case "Berserker":
				leftCharacterImage = berserkerImg;
				break;
			case "Raider":
				leftCharacterImage = raiderImg;
				break;
			case "Knight":
				leftCharacterImage = knightImg;
				break;
			case "Archer":
				leftCharacterImage = archerImg;
				break;
			case "Ninja":
				leftCharacterImage = ninjaImg;
				break;
			case "Samurai":
				leftCharacterImage = samuraiImg;
				break;
			case "Wizard":
				leftCharacterImage = wizardImg;
				break;
			case "Mage":
				leftCharacterImage = mageImg;
				break;
			case "Lich":
				leftCharacterImage = lichImg;
				break;
			}
			
			switch (rightSelection) {
			case "Warden":
				rightCharacterImage = wardenImg;
				break;
			case "Spearman":
				rightCharacterImage = spearmanImg;
				break;
			case "Berserker":
				rightCharacterImage = berserkerImg;
				break;
			case "Raider":
				rightCharacterImage = raiderImg;
				break;
			case "Knight":
				rightCharacterImage = knightImg;
				break;
			case "Archer":
				rightCharacterImage = archerImg;
				break;
			case "Ninja":
				rightCharacterImage = ninjaImg;
				break;
			case "Samurai":
				rightCharacterImage = samuraiImg;
				break;
			case "Wizard":
				rightCharacterImage = wizardImg;
				break;
			case "Mage":
				rightCharacterImage = mageImg;
				break;
			case "Lich":
				rightCharacterImage = lichImg;
				break;
			}
	    	
			arenaGrid[0][1].setText(leftCharacter.type + " " + Double.toString(leftCharacter.hp));
			arenaGrid[0][3].setText(rightCharacter.type + " " + Double.toString(rightCharacter.hp));
					
	    	leftLabel.setIcon(new ImageIcon(leftCharacterImage));
	    	rightLabel.setIcon(new ImageIcon(rightCharacterImage));
	    	repaint();
		}
	}
	
	public JPanel getArena() {
		return this;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		if (leftCharacterImage != null && rightCharacterImage != null) {
			g2.drawImage(leftCharacterImage, 0, 0, null);
			g2.drawImage(rightCharacterImage, 0, 0, null);

		}
	}

	@Override
	public void update(Observable o, Object arg) {
		arenaGrid[0][1].setText("Updated");
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
					Game.startBattle(leftCharacter, rightCharacter);
				}
				else {
					System.err.println("You must select two characters to battle.");
				}
			}
		}
	}	
}
