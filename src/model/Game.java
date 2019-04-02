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
import java.util.Timer;
import java.util.TimerTask;

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
		characters.add(new Character("Knight", 		1, 250.0, 	10.0, 	25.0, 	1.0, 	10.0, 	0.25, 20, "images/knight.png"));
		// Weapon: Bow + Arrow			Ability1: CriticalStrike - Next attack ignores armor and deals 50% more damage
		characters.add(new Character("Archer", 		1, 100.0, 	25.0, 	2.5, 	0.0, 	5.0, 	0.5, 10, "images/archer.png"));
		// Weapon: Daggers				Ability1: NinjaShurikens - Attacks twice in quick succession and lowers target's speed by 50% for 3 seconds
		characters.add(new Character("Ninja", 		1, 75.0, 	15.0, 	1.75, 	0.0, 	5.0, 	1.25, 15, "images/ninja.png"));
		// Weapon: TantoKatana			Ability1: MagicCloak - Blocks 50% magic damage for 3 seconds
		characters.add(new Character("Samurai", 	1, 125.0, 	20.0, 	5.0, 	2.5, 	12.5, 	0.5, 20, "images/samurai.png"));
		// Weapon: Staff				Ability1: AncientKnowledge - Heals self by 50% total health 
		characters.add(new Character("Wizard", 		1, 100.0, 	2.5, 	1.75, 	7.5, 	15.0, 	0.75, 10, "images/wizard.png"));
		// Weapon: Elemental			Ability1: FireBlast - Deals double magic damage and lowers target's magic defense by 25% for 3 seconds
		characters.add(new Character("Mage", 		1, 75.0, 	2.5,	1.0, 	15.0, 	10.0, 	1.0, 15, "images/mage.png"));
		// Weapon: Mace					Ability1: UndeadCurse - Steals 10% of target's attack, defense, spAtk, spDef, and speed permanently at the cost of 20% health
		characters.add(new Character("Lich", 		1, 500.0, 	5.0,	1.0,	5.0,	1.0,	0.25, 25, "images/lich.png"));
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
		leftCharacter.curr_hp = leftCharacter.hp;
		leftCharacter.curr_atk = leftCharacter.atk;
		leftCharacter.curr_def = leftCharacter.def;
		leftCharacter.curr_sp_atk = leftCharacter.sp_atk;
		leftCharacter.curr_sp_def = leftCharacter.sp_def;
		leftCharacter.curr_spd = leftCharacter.spd;
		
		rightCharacter.curr_hp = rightCharacter.hp;
		rightCharacter.curr_atk = rightCharacter.atk;
		rightCharacter.curr_def = rightCharacter.def;
		rightCharacter.curr_sp_atk = rightCharacter.sp_atk;
		rightCharacter.curr_sp_def = rightCharacter.sp_def;
		rightCharacter.curr_spd = rightCharacter.spd;
		
		Timer timer = new Timer();
		
		MyTimer myTimer = new MyTimer(leftCharacter, rightCharacter, leftCharacter.curr_hp, leftCharacter.curr_atk, leftCharacter.curr_def, leftCharacter.curr_sp_atk, leftCharacter.curr_sp_def, leftCharacter.curr_spd,
				rightCharacter.curr_hp, rightCharacter.curr_atk, rightCharacter.curr_def, rightCharacter.curr_sp_atk, rightCharacter.curr_sp_def, rightCharacter.curr_spd);
		
		timer.schedule(myTimer, 1000, 1000);
	}
	
	private static double calculateDamage(double attack, double spec_attack, double defense, double spec_defense) {
		double damage = 0;
		double physicalReduction = 100 / (100 + defense);
		double magicReduction = 100 / (100 + spec_defense);
		
		damage += attack * physicalReduction;
		damage += spec_attack * magicReduction;
		
		return damage;
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    double factor = (double) Math.pow(10, places);
	    value = value * factor;
	    double tmp = Math.round(value);
	    return (double) tmp / factor;
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
	
	private static class MyTimer extends TimerTask {
		double left_curr_hp, left_curr_atk, left_curr_def, left_curr_sp_atk, left_curr_sp_def, left_curr_spd, 
		 right_curr_hp, right_curr_atk, right_curr_def, right_curr_sp_atk, right_curr_sp_def, right_curr_spd;
		
		private Character leftCharacter, rightCharacter;
		
		public MyTimer(Character leftCharacter, Character rightCharacter, double left_curr_hp, double left_curr_atk, double left_curr_def, double left_curr_sp_atk, double left_curr_sp_def, double left_curr_spd, 
				double right_curr_hp, double right_curr_atk, double right_curr_def, double right_curr_sp_atk, double right_curr_sp_def, double right_curr_spd) {
			this.leftCharacter = leftCharacter;
			this.rightCharacter = rightCharacter;
			
			this.left_curr_hp = left_curr_hp;
			this.left_curr_atk = left_curr_atk;
			this.left_curr_def = left_curr_def;
			this.left_curr_sp_atk = left_curr_sp_atk;
			this.left_curr_sp_def = left_curr_sp_def;
			this.left_curr_spd = left_curr_spd;
			
			this.right_curr_hp = right_curr_hp;
			this.right_curr_atk = right_curr_atk;
			this.right_curr_def = right_curr_def;
			this.right_curr_sp_atk = right_curr_sp_atk;
			this.right_curr_sp_def = right_curr_sp_def;
			this.right_curr_spd = right_curr_spd;
			
		}
		public void run() {
			if (left_curr_hp > 0 && right_curr_hp > 0) {
				right_curr_hp -= calculateDamage(left_curr_atk, left_curr_sp_atk, right_curr_def, right_curr_sp_def);
				if (right_curr_hp < 0) {
					right_curr_hp = 0;
				}
				System.out.println("RIGHT " + round(right_curr_hp, 2));
				left_curr_hp -= calculateDamage(right_curr_atk, right_curr_sp_atk, left_curr_def, left_curr_sp_def);
				if (left_curr_hp < 0) {
					left_curr_hp = 0;
				}
				System.out.println("LEFT " + round(left_curr_hp, 2));
			}
			else {
				if (left_curr_hp == 0 && right_curr_hp == 0) {
					int leftRemainder = leftCharacter.lv_xp - leftCharacter.xp;
					if (leftRemainder <= 3) {
						levelUpCharacter(leftCharacter);
						leftCharacter.xp = leftRemainder;
					}
					else {
						leftCharacter.xp += 3;
					}
					int rightRemainder = rightCharacter.lv_xp - rightCharacter.xp;
					if (rightRemainder <= 3) {
						levelUpCharacter(rightCharacter);
						rightCharacter.xp = rightRemainder;
					}
					else {
						rightCharacter.xp += 3;	
					}
					this.cancel();
				}
				else if (left_curr_hp == 0 && right_curr_hp > 0) {
					int leftRemainder = leftCharacter.lv_xp - leftCharacter.xp;
					if (leftRemainder <= 1) {
						levelUpCharacter(leftCharacter);
						leftCharacter.xp = leftRemainder;
					}
					else {
						leftCharacter.xp += 1;
					}
					int rightRemainder = rightCharacter.lv_xp - rightCharacter.xp;
					if (rightRemainder <= 5) {
						levelUpCharacter(rightCharacter);
						rightCharacter.xp = rightRemainder;
					}
					else {
						rightCharacter.xp += 5;	
					}
					this.cancel();
				}
				else {
					int leftRemainder = leftCharacter.lv_xp - leftCharacter.xp;
					if (leftRemainder <= 5) {
						levelUpCharacter(leftCharacter);
						leftCharacter.xp = leftRemainder;
					}
					else {
						leftCharacter.xp += 5;
					}
					int rightRemainder = rightCharacter.lv_xp - rightCharacter.xp;
					if (rightRemainder <= 1) {
						levelUpCharacter(rightCharacter);
						rightCharacter.xp = rightRemainder;
					}
					else {
						rightCharacter.xp += 1;	
					}
					this.cancel();
				}
			}
		}
	}
}
