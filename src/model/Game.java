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
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("serial")
public class Game extends Observable implements Serializable {

	private static List<Character> characters;
	
	private boolean finished;
	
	public Game() {
		// Try to read from file
		// Else 
		characters = new ArrayList<>();
										//  Level,  Health, Attack, Defense, SpAtk, SpDef, Speed, Level XP
		// Weapon: Sword				Ability1: BrutalStrike - Next attack deals 100% more damage and heals half the amount
		characters.add(new Character("Warden",   	1, 150.0, 	10.0, 	10.0, 	0.0, 	10.0, 	0.66, 10));
		// Weapon: Spear				Ability1: VitalStrike - Next attack deals 50% more damage and lowers target defense by 50% for 3 seconds
		characters.add(new Character("Spearman", 	1, 125.0, 	12.5, 	7.5,  	0.0, 	7.5,  	0.50, 10));
		// Weapon: Hatchets				Ability1: Flurry - Four successive attacks in same attack cycle
		characters.add(new Character("Berserker", 	1, 100.0, 	15.0, 	5.0, 	0.0, 	10.0, 	0.75, 10));
		// Weapon: Large axe			Ability1: WarCry - Raises attack by 50% for 3 seconds
		characters.add(new Character("Raider", 		1, 200.0, 	17.5, 	10.0, 	0.0, 	5.0, 	0.35, 15));
		// Weapon: Sword + Shield		Ability1: Paladin'sBlessing - Increases defense by 25% for 3 seconds and heals 25% total health
		characters.add(new Character("Knight", 		1, 250.0, 	10.0, 	25.0, 	1.0, 	10.0, 	0.25, 20));
		// Weapon: Bow + Arrow			Ability1: CriticalStrike - Next attack ignores armor and deals 50% more damage
		characters.add(new Character("Archer", 		1, 100.0, 	20.0, 	2.5, 	0.0, 	5.0, 	0.33, 10));
		// Weapon: Daggers				Ability1: NinjaShurikens - Attacks twice in quick succession and lowers target's speed by 50% for 3 seconds
		characters.add(new Character("Ninja", 		1, 75.0, 	7.5, 	1.75, 	0.0, 	5.0, 	1.25, 15));
		// Weapon: TantoKatana			Ability1: MagicCloak - Blocks 50% magic damage for 3 seconds
		characters.add(new Character("Samurai", 	1, 100.0, 	15.0, 	5.0, 	2.5, 	12.5, 	0.50, 20));
		// Weapon: Staff				Ability1: AncientKnowledge - Heals self by 50% total health 
		characters.add(new Character("Wizard", 		1, 125.0, 	2.5, 	1.75, 	7.5, 	15.0, 	0.75, 10));
		// Weapon: Elemental			Ability1: FireBlast - Deals double magic damage and lowers target's magic defense by 25% for 3 seconds
		characters.add(new Character("Mage", 		1, 75.0, 	2.5,	1.0, 	15.0, 	10.0, 	1.00, 15));
		// Weapon: Mace					Ability1: UndeadCurse - Steals 10% of target's attack, defense, spAtk, spDef, and speed permanently at the cost of 20% health
		characters.add(new Character("Lich", 		1, 500.0, 	5.0,	1.0,	5.0,	1.0,	0.25, 25));
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
	
	public void startBattle(Character leftCharacter, Character rightCharacter) {
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
		
		finished = false;
		
		Timer leftTimer = new Timer();
		Timer rightTimer = new Timer();
		
		MyTimer myLeftTimer = new MyTimer(1, leftCharacter, rightCharacter, leftCharacter.curr_hp, leftCharacter.curr_atk, leftCharacter.curr_def, leftCharacter.curr_sp_atk, 
				leftCharacter.curr_sp_def, rightCharacter.curr_hp, rightCharacter.curr_atk, rightCharacter.curr_def, rightCharacter.curr_sp_atk, rightCharacter.curr_sp_def);
		
		MyTimer myRightTimer = new MyTimer(2, leftCharacter, rightCharacter, leftCharacter.curr_hp, leftCharacter.curr_atk, leftCharacter.curr_def, leftCharacter.curr_sp_atk, 
				leftCharacter.curr_sp_def, rightCharacter.curr_hp, rightCharacter.curr_atk, rightCharacter.curr_def, rightCharacter.curr_sp_atk, rightCharacter.curr_sp_def);
		
		leftTimer.schedule(myLeftTimer, (long)(1000 / leftCharacter.curr_spd), (long)(1000 / leftCharacter.curr_spd));
		rightTimer.schedule(myRightTimer, (long)(1000 / rightCharacter.curr_spd), (long)(1000 / rightCharacter.curr_spd));
	}
	
	private static double calculateDamage(double attack, double spec_attack, double defense, double spec_defense) {
		double damage = 0;
		double physicalReduction = 100 / (100 + defense);
		double magicReduction = 100 / (100 + spec_defense);
		
		damage += attack * physicalReduction;
		damage += spec_attack * magicReduction;
		
		return damage;
	}
	
	
	private class MyTimer extends TimerTask {
		double left_curr_hp, left_curr_atk, left_curr_def, left_curr_sp_atk, left_curr_sp_def, 
		 right_curr_hp, right_curr_atk, right_curr_def, right_curr_sp_atk, right_curr_sp_def;
		
		int whichCharacter;
		
		private Character leftCharacter, rightCharacter;
		
		public MyTimer(int whichCharacter, Character leftCharacter, Character rightCharacter, double left_curr_hp, double left_curr_atk, double left_curr_def, double left_curr_sp_atk, 
				double left_curr_sp_def, double right_curr_hp, double right_curr_atk, double right_curr_def, double right_curr_sp_atk, double right_curr_sp_def) {
			this.whichCharacter = whichCharacter;
			
			this.leftCharacter = leftCharacter;
			this.rightCharacter = rightCharacter;
			
			this.left_curr_hp = left_curr_hp;
			this.left_curr_atk = left_curr_atk;
			this.left_curr_def = left_curr_def;
			this.left_curr_sp_atk = left_curr_sp_atk;
			this.left_curr_sp_def = left_curr_sp_def;
			
			this.right_curr_hp = right_curr_hp;
			this.right_curr_atk = right_curr_atk;
			this.right_curr_def = right_curr_def;
			this.right_curr_sp_atk = right_curr_sp_atk;
			this.right_curr_sp_def = right_curr_sp_def;
		}
		public void run() {
			if (left_curr_hp > 0 && right_curr_hp > 0 && !finished) {
				if (whichCharacter == 1) {
					right_curr_hp -= calculateDamage(left_curr_atk, left_curr_sp_atk, right_curr_def, right_curr_sp_def);
					if (right_curr_hp < 0) {
						right_curr_hp = 0;
						finished = true;
					}
					Object newValues[] = {1, round(left_curr_hp, 2), round(right_curr_hp,2), leftCharacter.xp, rightCharacter.xp};
					setChanged();
					notifyObservers(newValues);
				}
				else if (whichCharacter == 2) {
					left_curr_hp -= calculateDamage(right_curr_atk, right_curr_sp_atk, left_curr_def, left_curr_sp_def);
					if (left_curr_hp < 0) {
						left_curr_hp = 0;
						finished = true;
					}
					Object newValues[] = {2, round(left_curr_hp, 2), round(right_curr_hp,2), leftCharacter.xp, rightCharacter.xp};
					setChanged();
					notifyObservers(newValues);
				}
			}
			else if (finished) {
				if (left_curr_hp == 0 && right_curr_hp == 0) {
					int leftRemainder = leftCharacter.lv_xp - leftCharacter.xp;
					if (leftCharacter.type.equals(rightCharacter.type)) {
						if (leftRemainder <= 3) {
							levelUpCharacter(leftCharacter);
							leftCharacter.xp = 3 - leftRemainder;
						}
						else {
							leftCharacter.xp += 3;
						}
						Object newValues[] = {1, round(left_curr_hp, 2), round(right_curr_hp,2), leftCharacter.xp, rightCharacter.xp};
						finished = true;
						setChanged();
						notifyObservers(newValues);
						this.cancel();
					}
					else {
						if (leftRemainder <= 3) {
							levelUpCharacter(leftCharacter);
							leftCharacter.xp = 3 - leftRemainder;
							Object newValues[] = {1, round(left_curr_hp, 2), round(right_curr_hp,2), leftCharacter.xp, rightCharacter.xp};
							finished = true;
							setChanged();
							notifyObservers(newValues);
						}
						else {
							leftCharacter.xp += 3;
							Object newValues[] = {1, round(left_curr_hp, 2), round(right_curr_hp,2), leftCharacter.xp, rightCharacter.xp};
							finished = true;
							setChanged();
							notifyObservers(newValues);
						}
						int rightRemainder = rightCharacter.lv_xp - rightCharacter.xp;
						if (rightRemainder <= 3) {
							levelUpCharacter(rightCharacter);
							rightCharacter.xp = 3 - rightRemainder;
							Object newValues[] = {2, round(left_curr_hp, 2), round(right_curr_hp,2), leftCharacter.xp, rightCharacter.xp};
							finished = true;
							setChanged();
							notifyObservers(newValues);
						}
						else {
							rightCharacter.xp += 3;	
							Object newValues[] = {2, round(left_curr_hp, 2), round(right_curr_hp,2), leftCharacter.xp, rightCharacter.xp};
							finished = true;
							setChanged();
							notifyObservers(newValues);
						}
						this.cancel();
					}
				}
				else if (left_curr_hp == 0 && right_curr_hp > 0) {
					int leftRemainder = leftCharacter.lv_xp - leftCharacter.xp;
					if (leftRemainder <= 1) {
						levelUpCharacter(leftCharacter);
						leftCharacter.xp = 1 - leftRemainder;
						Object newValues[] = {1, round(left_curr_hp, 2), round(right_curr_hp,2), leftCharacter.xp, rightCharacter.xp};
						finished = true;
						setChanged();
						notifyObservers(newValues);
					}
					else {
						leftCharacter.xp += 1;
						Object newValues[] = {1, round(left_curr_hp, 2), round(right_curr_hp,2), leftCharacter.xp, rightCharacter.xp};
						finished = true;
						setChanged();
						notifyObservers(newValues);
					}
					int rightRemainder = rightCharacter.lv_xp - rightCharacter.xp;
					if (rightRemainder <= 5) {
						levelUpCharacter(rightCharacter);
						rightCharacter.xp = 5 - rightRemainder;
						Object newValues[] = {2, round(left_curr_hp, 2), round(right_curr_hp,2), leftCharacter.xp, rightCharacter.xp};
						finished = true;
						setChanged();
						notifyObservers(newValues);
					}
					else {
						rightCharacter.xp += 5;	
						Object newValues[] = {2, round(left_curr_hp, 2), round(right_curr_hp,2), leftCharacter.xp, rightCharacter.xp};
						finished = true;
						setChanged();
						notifyObservers(newValues);
					}
					this.cancel();
				}
				else if (left_curr_hp > 0 && right_curr_hp == 0){
					int leftRemainder = leftCharacter.lv_xp - leftCharacter.xp;
					if (leftRemainder <= 5) {
						levelUpCharacter(leftCharacter);
						leftCharacter.xp = 5 - leftRemainder;
						Object newValues[] = {1, round(left_curr_hp, 2), round(right_curr_hp,2), leftCharacter.xp, rightCharacter.xp};
						finished = true;
						setChanged();
						notifyObservers(newValues);
					}
					else {
						leftCharacter.xp += 5;
						Object newValues[] = {1, round(left_curr_hp, 2), round(right_curr_hp,2), leftCharacter.xp, rightCharacter.xp};
						finished = true;
						setChanged();
						notifyObservers(newValues);
					}
					int rightRemainder = rightCharacter.lv_xp - rightCharacter.xp;
					if (rightRemainder <= 1) {
						levelUpCharacter(rightCharacter);
						rightCharacter.xp = 1 - rightRemainder;
						Object newValues[] = {2, round(left_curr_hp, 2), round(right_curr_hp,2), leftCharacter.xp, rightCharacter.xp};
						finished = true;
						setChanged();
						notifyObservers(newValues);
					}
					else {
						rightCharacter.xp += 1;	
						Object newValues[] = {2, round(left_curr_hp, 2), round(right_curr_hp,2), leftCharacter.xp, rightCharacter.xp};
						finished = true;
						setChanged();
						notifyObservers(newValues);
					}
					this.cancel();
				}
			}
		}
	}
}
