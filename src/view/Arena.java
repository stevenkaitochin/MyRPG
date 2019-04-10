package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
	private BufferedImage wardenLeftImg, spearmanLeftImg, berserkerLeftImg, raiderLeftImg, knightLeftImg, archerLeftImg,
						  ninjaLeftImg, samuraiLeftImg, wizardLeftImg, mageLeftImg, lichLeftImg,
						  wardenRightImg, spearmanRightImg, berserkerRightImg, raiderRightImg, knightRightImg, archerRightImg,
						  ninjaRightImg, samuraiRightImg, wizardRightImg, mageRightImg, lichRightImg;
	
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
			wardenLeftImg = ImageIO.read(new File("images/wardenLeft.png"));
			wardenRightImg = ImageIO.read(new File("images/wardenRight.png"));
			spearmanLeftImg = ImageIO.read(new File("images/spearmanLeft.png"));
			spearmanRightImg = ImageIO.read(new File("images/spearmanRight.png"));
			berserkerLeftImg = ImageIO.read(new File("images/berserkerLeft.png"));
			berserkerRightImg = ImageIO.read(new File("images/berserkerRight.png"));
			raiderLeftImg = ImageIO.read(new File("images/raiderLeft.png"));
			raiderRightImg = ImageIO.read(new File("images/raiderRight.png"));
			knightLeftImg = ImageIO.read(new File("images/knightLeft.png"));
			knightRightImg = ImageIO.read(new File("images/knightRight.png"));
			archerLeftImg = ImageIO.read(new File("images/archerLeft.png"));
			archerRightImg = ImageIO.read(new File("images/archerRight.png"));
			ninjaLeftImg = ImageIO.read(new File("images/ninjaLeft.png"));
			ninjaRightImg = ImageIO.read(new File("images/ninjaRight.png"));
			samuraiLeftImg = ImageIO.read(new File("images/samuraiLeft.png"));
			samuraiRightImg = ImageIO.read(new File("images/samuraiRight.png"));
			wizardLeftImg = ImageIO.read(new File("images/wizardLeft.png"));
			wizardRightImg = ImageIO.read(new File("images/wizardRight.png"));
			mageLeftImg = ImageIO.read(new File("images/mageLeft.png"));
			mageRightImg = ImageIO.read(new File("images/mageRight.png"));
			lichLeftImg = ImageIO.read(new File("images/lichLeft.png"));
			lichRightImg = ImageIO.read(new File("images/lichRight.png"));
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
		int c = 7;
	    arenaGrid = new JLabel[r][c];    

		for(int i = 0; i < r; i++) {
		   for(int j = 0; j < c; j++) {
		      arenaGrid[i][j] = new JLabel();
		      characterPanel.add(arenaGrid[i][j]);
		   }
		}
		
		arenaGrid[0][1].setFont(new Font(Font.MONOSPACED, 1, 12));
		arenaGrid[0][5].setFont(new Font(Font.MONOSPACED, 1, 12));
		
		leftLabel = new JLabel();
		leftLabel = arenaGrid[1][2];
		
		rightLabel = new JLabel();
		rightLabel = arenaGrid[1][4];

		add(characterPanel);
	}
	
	public void changeArena(Game game, String leftSelection, String rightSelection) {
		if (leftSelection != null && rightSelection != null) {
			leftCharacter = game.getCharacter(leftSelection);
			rightCharacter = game.getCharacter(rightSelection);
			
			switch (leftSelection) {
			case "Warden":
				leftCharacterImage = wardenLeftImg;
				break;
			case "Spearman":
				leftCharacterImage = spearmanLeftImg;
				break;
			case "Berserker":
				leftCharacterImage = berserkerLeftImg;
				break;
			case "Raider":
				leftCharacterImage = raiderLeftImg;
				break;
			case "Knight":
				leftCharacterImage = knightLeftImg;
				break;
			case "Archer":
				leftCharacterImage = archerLeftImg;
				break;
			case "Ninja":
				leftCharacterImage = ninjaLeftImg;
				break;
			case "Samurai":
				leftCharacterImage = samuraiLeftImg;
				break;
			case "Wizard":
				leftCharacterImage = wizardLeftImg;
				break;
			case "Mage":
				leftCharacterImage = mageLeftImg;
				break;
			case "Lich":
				leftCharacterImage = lichLeftImg;
				break;
			}
			
			switch (rightSelection) {
			case "Warden":
				rightCharacterImage = wardenRightImg;
				break;
			case "Spearman":
				rightCharacterImage = spearmanRightImg;
				break;
			case "Berserker":
				rightCharacterImage = berserkerRightImg;
				break;
			case "Raider":
				rightCharacterImage = raiderRightImg;
				break;
			case "Knight":
				rightCharacterImage = knightRightImg;
				break;
			case "Archer":
				rightCharacterImage = archerRightImg;
				break;
			case "Ninja":
				rightCharacterImage = ninjaRightImg;
				break;
			case "Samurai":
				rightCharacterImage = samuraiRightImg;
				break;
			case "Wizard":
				rightCharacterImage = wizardRightImg;
				break;
			case "Mage":
				rightCharacterImage = mageRightImg;
				break;
			case "Lich":
				rightCharacterImage = lichRightImg;
				break;
			}
	    	
			arenaGrid[0][1].setText("<html>Atk:&nbsp;&nbsp;&nbsp;" + BigDecimal.valueOf(leftCharacter.atk).setScale(3, RoundingMode.HALF_UP)
					+ "<br>Def:&nbsp;&nbsp;&nbsp;" + BigDecimal.valueOf(leftCharacter.def).setScale(3, RoundingMode.HALF_UP)
					+ "<br>SpA:&nbsp;&nbsp;&nbsp;" + BigDecimal.valueOf(leftCharacter.sp_atk).setScale(3, RoundingMode.HALF_UP) 
					+ "<br>SpD:&nbsp;&nbsp;&nbsp;" + BigDecimal.valueOf(leftCharacter.sp_def).setScale(3, RoundingMode.HALF_UP)
					+ "<br>Spd:&nbsp;&nbsp;&nbsp;" + BigDecimal.valueOf(leftCharacter.spd).setScale(4, RoundingMode.HALF_UP));
			
			arenaGrid[0][2].setText("<html>" + leftCharacter.type + " lv. " + leftCharacter.lv + "<br><br>" + 
									"HP:&nbsp;&nbsp;&nbsp;" + leftCharacter.hp + " / " + leftCharacter.hp + "</html>");
			
			arenaGrid[0][4].setText("<html>" + rightCharacter.type + " lv. " + rightCharacter.lv + "<br><br>" + 
									"HP:&nbsp;&nbsp;&nbsp;" + rightCharacter.hp + " / " + rightCharacter.hp + "</html>");
			
			arenaGrid[0][5].setText("<html>Atk:&nbsp;&nbsp;&nbsp;" + BigDecimal.valueOf(rightCharacter.atk).setScale(3, RoundingMode.HALF_UP) 
					+ "<br>Def:&nbsp;&nbsp;&nbsp;" + BigDecimal.valueOf(rightCharacter.def).setScale(3, RoundingMode.HALF_UP)
		            + "<br>SpA:&nbsp;&nbsp;&nbsp;" + BigDecimal.valueOf(rightCharacter.sp_atk).setScale(3, RoundingMode.HALF_UP)
		            + "<br>SpD:&nbsp;&nbsp;&nbsp;" + BigDecimal.valueOf(rightCharacter.sp_def).setScale(3, RoundingMode.HALF_UP)
		            + "<br>Spd:&nbsp;&nbsp;&nbsp;" + BigDecimal.valueOf(rightCharacter.spd).setScale(4, RoundingMode.HALF_UP));
			
	    	leftLabel.setIcon(new ImageIcon(leftCharacterImage));
	    	rightLabel.setIcon(new ImageIcon(rightCharacterImage));
	    	
			arenaGrid[2][2].setText("<html>" + "XP:&nbsp;&nbsp;&nbsp;" + leftCharacter.xp + " / " + leftCharacter.lv_xp + "<br><br></html>");
			arenaGrid[2][4].setText("<html>" + "XP:&nbsp;&nbsp;&nbsp;" + rightCharacter.xp + " / " + rightCharacter.lv_xp + "<br><br></html>");
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
		arenaGrid[0][1].setText("<html>Atk:&nbsp;&nbsp;&nbsp;" + BigDecimal.valueOf(leftCharacter.curr_atk).setScale(3, RoundingMode.HALF_UP)
				+ "<br>Def:&nbsp;&nbsp;&nbsp;" + BigDecimal.valueOf(leftCharacter.curr_def).setScale(3, RoundingMode.HALF_UP)
				+ "<br>SpA:&nbsp;&nbsp;&nbsp;" + BigDecimal.valueOf(leftCharacter.curr_sp_atk).setScale(3, RoundingMode.HALF_UP) 
				+ "<br>SpD:&nbsp;&nbsp;&nbsp;" + BigDecimal.valueOf(leftCharacter.curr_sp_def).setScale(3, RoundingMode.HALF_UP)
				+ "<br>Spd:&nbsp;&nbsp;&nbsp;" + BigDecimal.valueOf(leftCharacter.curr_spd).setScale(4, RoundingMode.HALF_UP));
		
		arenaGrid[0][2].setText("<html>" + leftCharacter.type + " lv. " + leftCharacter.lv + "<br><br>" + 
			"HP:&nbsp;&nbsp;&nbsp;" + BigDecimal.valueOf(leftCharacter.curr_hp).setScale(1, RoundingMode.HALF_UP)  + " / " + leftCharacter.hp + "</html>");
		
		arenaGrid[0][4].setText("<html>" + rightCharacter.type + " lv. " + rightCharacter.lv + "<br><br>" + 
				"HP:&nbsp;&nbsp;&nbsp;" + BigDecimal.valueOf(rightCharacter.curr_hp).setScale(1, RoundingMode.HALF_UP)  + " / " + rightCharacter.hp + "</html>");
		
		arenaGrid[0][5].setText("<html>Atk:&nbsp;&nbsp;&nbsp;" + BigDecimal.valueOf(rightCharacter.curr_atk).setScale(3, RoundingMode.HALF_UP) 
				+ "<br>Def:&nbsp;&nbsp;&nbsp;" + BigDecimal.valueOf(rightCharacter.curr_def).setScale(3, RoundingMode.HALF_UP)
	            + "<br>SpA:&nbsp;&nbsp;&nbsp;" + BigDecimal.valueOf(rightCharacter.curr_sp_atk).setScale(3, RoundingMode.HALF_UP)
	            + "<br>SpD:&nbsp;&nbsp;&nbsp;" + BigDecimal.valueOf(rightCharacter.curr_sp_def).setScale(3, RoundingMode.HALF_UP)
	            + "<br>Spd:&nbsp;&nbsp;&nbsp;" + BigDecimal.valueOf(rightCharacter.curr_spd).setScale(4, RoundingMode.HALF_UP));
		
		arenaGrid[2][2].setText("<html>" + "XP:&nbsp;&nbsp;&nbsp;" + leftCharacter.xp + " / " + leftCharacter.lv_xp + "<br><br></html>");
		arenaGrid[2][4].setText("<html>" + "XP:&nbsp;&nbsp;&nbsp;" + rightCharacter.xp + " / " + rightCharacter.lv_xp + "<br><br></html>");
		repaint();
		
		if (leftCharacter.curr_hp == 0 || rightCharacter.curr_hp == 0) {
			fightButton.setEnabled(true);
		}
	}
	
	
	private class ButtonListener implements ActionListener {
	
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton buttonClicked = (JButton) e.getSource();
			
			if (buttonClicked == fightButton) {
				if (buttonGroupLeft.getSelection() != null && buttonGroupRight.getSelection() != null) {
					if (buttonGroupLeft.getSelection().getActionCommand().equals(buttonGroupRight.getSelection().getActionCommand())) {
						System.err.println("You must select unique characters to battle.");
					}
					else {
						changeArena(game, buttonGroupLeft.getSelection().getActionCommand(), buttonGroupRight.getSelection().getActionCommand());
						repaint();
						validate();
						fightButton.setEnabled(false);
						game.startBattle(leftCharacter, rightCharacter);
					}
				}
				else {
					System.err.println("You must select two characters to battle.");
				}
			}
		}
	}	
}
