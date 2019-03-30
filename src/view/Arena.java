package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Character;
import model.Game;

@SuppressWarnings("serial")
public class Arena extends JPanel implements Observer {
	
	private Game game;
	private Character leftCharacter, rightCharacter;
	private BufferedImage leftCharacterImage, rightCharacterImage;

	public Arena(Game game, String leftSelection, String rightSelection) {
		JPanel arena = new JPanel();
		
		if (leftSelection == null && rightSelection == null) {
			return;
		}
		else {
			this.game = game;
			leftCharacter = game.getCharacter(leftSelection);
			rightCharacter = game.getCharacter(rightSelection);
			
			leftCharacterImage = game.getCharacter(leftSelection).img;
			rightCharacterImage = game.getCharacter(rightSelection).img;
			
			JLabel leftLabel = new JLabel(new ImageIcon(leftCharacterImage));
			JLabel rightLabel = new JLabel(new ImageIcon(rightCharacterImage));
			
			arena.add(leftLabel);
			arena.add(rightLabel);
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(leftCharacterImage, 0, 0, null);
		g2.drawImage(rightCharacterImage, 0, 0, null);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
