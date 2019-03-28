package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable {

	private List<Character> characters;
	
	public Game() {
		// Try to read from file
		// Else 
		characters = new ArrayList<>();
		characters.add(new Character("Warrior", 1, 100, 10, 5, 0, 5, 0.5, "../../images/warrior.png"));
		characters.add(new Character("Trojan", 1, 100, 10, 5, 0, 5, 0.5, "../../images/trojan.png"));
		characters.add(new Character("Spearman", 1, 100, 10, 5, 0, 5, 0.5, "../../images/spearman.png"));
		characters.add(new Character("Paladin", 1, 100, 10, 5, 0, 5, 0.5, "../../images/warrior.png"));
		characters.add(new Character("Champion", 1, 100, 10, 5, 0, 5, 0.5, "../../images/warrior.png"));
		characters.add(new Character("Archer", 1, 100, 10, 5, 0, 5, 0.5, "../../images/archer.png"));
		characters.add(new Character("Monk", 1, 100, 10, 5, 0, 5, 0.5, "../../images/warrior.png"));
		characters.add(new Character("Shaman", 1, 100, 10, 5, 0, 5, 0.5, "../../images/warrior.png"));
		characters.add(new Character("Berserker", 1, 100, 10, 5, 0, 5, 0.5, "../../images/warrior.png"));
		characters.add(new Character("Mage", 1, 100, 10, 5, 0, 5, 0.5, "../../images/warrior.png"));
	}
	public void resetCharacters() {
		for (Character character: characters) {
			character.resetStats();
		}
	}
}
