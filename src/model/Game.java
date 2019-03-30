package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Game implements Serializable {

	private static List<Character> characters;
	
	public Game() {
		// Try to read from file
		// Else 
		characters = new ArrayList<>();
										//  Level,  Health, Attack, Defense, SpAtk, SpDef, Speed, Level XP
		// Weapon: Sword				Ability1: BrutalStrike - Next attack deals 100% more damage and heals half the amount
		characters.add(new Character("Warden",   	1, 150.0, 	10.0, 	10.0, 	0.0, 	10.0, 	0.5, 10, "images/warden.png"));
		// Weapon: Spear				Ability1: VitalStrike - Next attack deals 50% more damage and lowers target defense by 50% for 3 seconds
		characters.add(new Character("Spearman", 	1, 150.0, 	15.0, 	7.5,  	0.0, 	7.5,  	0.5, 10, "images/spearman.png"));
		// Weapon: Hatchets				Ability1: Flurry - Four successive attacks in same attack cycle
		characters.add(new Character("Berserker", 	1, 100.0, 	20.0, 	5.0, 	0.0, 	10.0, 	0.75, 10, "images/berserker.png"));
		// Weapon: Large axe			Ability1: WarCry - Raises attack by 50% for 3 seconds
		characters.add(new Character("Raider", 		1, 200.0, 	15.0, 	10.0, 	0.0, 	5.0, 	0.35, 15, "images/raider.png"));
		// Weapon: Sword + Shield		Ability1: Paladin'sBlessing - Increases defense by 25% for 3 seconds and heals 25% total health
		characters.add(new Character("Knight", 		1, 250.0, 	10.0, 	25.0, 	0.0, 	10.0, 	0.25, 20, "images/knight.png"));
		// Weapon: Bow + Arrow			Ability1: CriticalStrike - Next attack ignores armor and deals 50% more damage
		characters.add(new Character("Archer", 		1, 100.0, 	25.0, 	2.5, 	0.0, 	5.0, 	0.5, 10, "images/archer.png"));
		// Weapon: Daggers				Ability1: NinjaShurikens - Attacks twice in quick succession and lowers target's speed by 50% for 3 seconds
		characters.add(new Character("Ninja", 		1, 75.0, 	15.0, 	1.75, 	0.0, 	5.0, 	1.25, 15, "images/ninja.png"));
		// Weapon: TantoKatana			Ability1: MagicCloak - Blocks 50% magic damage for 3 seconds
		characters.add(new Character("Samurai", 	1, 125.0, 	20.0, 	5.0, 	0.0, 	12.5, 	0.5, 20, "images/samurai.png"));
		// Weapon: Staff				Ability1: AncientKnowledge - Heals self by 50% total health 
		characters.add(new Character("Wizard", 		1, 100.0, 	1.0, 	1.75, 	7.5, 	15.0, 	0.75, 10, "images/wizard.png"));
		// Weapon: Elemental			Ability1: FireBlast - Deals double magic damage and lowers target's magic defense by 25% for 3 seconds
		characters.add(new Character("Mage", 		1, 75.0, 	2.5,	1.0, 	15.0, 	10.0, 	1.0, 15, "images/mage.png"));
		// Weapon: Mace					Ability1: UndeadCurse - Steals 10% of target's attack, defense, spAtk, spDef, and speed permanently at the cost of 20% health
		characters.add(new Character("Lich", 		1, 500.0, 	5.0,	0.0,	5.0,	0.0,	0.25, 25, "images/lich.png"));
	}
	
	public Character getCharacter(String selection) {
		int index;
		for (index = 0; index < characters.size() - 1; index++) {
			if (characters.get(index).type.equals(selection)) {
				break;
			}
		}
		return characters.get(index);
	}
	
	public static void startBattle(Character leftCharacter, Character rightCharacter) {
		
	}
	
	public static void levelUpCharacter(Character character) {
		character.changeLevel(character, 1);
	}
	
	public static void resetLevels() {
		for (Character character: characters) {
			character.changeLevel(character, 0);
		}
	}
	
	public static void saveCharacters() {
		File file = new File("save.txt");
		try {
			FileOutputStream saveFile = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(saveFile);
			
			out.writeObject(characters);
			out.close();
			saveFile.close();
		} catch (IOException ex) {
			System.err.println("IOException on Save");
			ex.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void loadCharacters() {
		try {
			FileInputStream saveFile = new FileInputStream("save.txt");
			ObjectInputStream in = new ObjectInputStream(saveFile);

			characters = (List<Character>)in.readObject();
			
			in.close();
			saveFile.close();
		} catch (IOException ex) {
			System.err.println("IOException on Load");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
	}
}
