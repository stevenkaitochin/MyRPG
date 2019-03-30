package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class Character implements Serializable {

	public String type;
	public int lv, xp, lv_xp;
	public double hp, atk, def, sp_atk, sp_def, spd;
	public BufferedImage img;
	
	public Character(String type, int lv, double hp, double atk, double def, double sp_atk, double sp_def, double spd, int lv_xp, String fileStr) {
		this.type = type;
		this.lv = lv;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.sp_atk = sp_atk;
		this.sp_def = sp_def;
		this.spd = spd;
		this.xp = 0;
		this.lv_xp = lv_xp;
		
		try {
			File file = new File(fileStr);
			img = ImageIO.read(file);
		} catch (IOException ex) {
			System.err.println("Error: Could not find file path");
		}
	}
	public void changeLevel(Character character, int change) {
		if (change == 0) {
			// Reset character level
			switch(type) {
			case "Warden":
				this.lv = 1;
				this.hp = 150;
				this.atk = 10;
				this.def = 10;
				this.sp_atk = 0;
				this.sp_def = 10;
				this.spd = 0.5;
				this.xp = 0;
				this.lv_xp = 10;
				break;
			case "Spearman":
				this.lv = 1;
				this.hp = 150;
				this.atk = 15;
				this.def = 7.5;
				this.sp_atk = 0;
				this.sp_def = 7.5;
				this.spd = 0.5;
				this.xp = 0;
				this.lv_xp = 10;
				break;
			case "Berserker":
				this.lv = 1;
				this.hp = 100;
				this.atk = 20;
				this.def = 5;
				this.sp_atk = 0;
				this.sp_def = 10;
				this.spd = 0.75;
				this.xp = 0;
				this.lv_xp = 10;
				break;
			case "Raider":
				this.lv = 1;
				this.hp = 200;
				this.atk = 15;
				this.def = 10;
				this.sp_atk = 0;
				this.sp_def = 5;
				this.spd = 0.35;
				this.xp = 0;
				this.lv_xp = 15;
				break;
			case "Knight":
				this.lv = 1;
				this.hp = 250;
				this.atk = 10;
				this.def = 25;
				this.sp_atk = 0;
				this.sp_def = 10;
				this.spd = 0.25;
				this.xp = 0;
				this.lv_xp = 20;
				break;
			case "Archer":
				this.lv = 1;
				this.hp = 100;
				this.atk = 10;
				this.def = 5;
				this.sp_atk = 0;
				this.sp_def = 10;
				this.spd = 0.5;
				this.xp = 0;
				this.lv_xp = 10;
				break;
			case "Ninja":
				this.lv = 1;
				this.hp = 75;
				this.atk = 15;
				this.def = 1.75;
				this.sp_atk = 0;
				this.sp_def = 5;
				this.spd = 1.25;
				this.xp = 0;
				this.lv_xp = 15;
				break;
			case "Samurai":
				this.lv = 1;
				this.hp = 125;
				this.atk = 20;
				this.def = 5;
				this.sp_atk = 0;
				this.sp_def = 12.5;
				this.spd = 0.5;
				this.xp = 0;
				this.lv_xp = 20;
				break;
			case "Wizard":
				this.lv = 1;
				this.hp = 100;
				this.atk = 1;
				this.def = 1.75;
				this.sp_atk = 7.5;
				this.sp_def = 15.;
				this.spd = 0.75;
				this.xp = 0;
				this.lv_xp = 10;
				break;
			case "Mage":
				this.lv = 1;
				this.hp = 75;
				this.atk = 2.5;
				this.def = 1;
				this.sp_atk = 15;
				this.sp_def = 10;
				this.spd = 1;
				this.xp = 0;
				this.lv_xp = 15;
				break;
			case "Lich":
				this.lv = 1;
				this.hp = 500;
				this.atk = 5;
				this.def = 0;
				this.sp_atk = 5;
				this.sp_def = 0;
				this.spd = 0.25;
				this.xp = 0;
				this.lv_xp = 25;
				break;
			}
		} else {
			// Level up character
			switch(type) {
			case "Warden":
				this.lv += 1;
				this.hp += 15;
				this.atk += 1;
				this.def += 1;
				this.sp_def += 1;
				this.spd += 0.05;
				this.xp = 0;
				break;
			case "Spearman":
				this.lv += 1;
				this.hp += 15;
				this.atk += 1.5;
				this.def += 0.75;
				this.sp_def += 0.75;
				this.spd += 0.05;
				this.xp = 0;
				break;
			case "Berserker":
				this.lv += 1;
				this.hp += 10;
				this.atk += 2;
				this.def += 0.5;
				this.sp_def += 1;
				this.spd += 0.075;
				this.xp = 0;
				break;
			case "Raider":
				this.lv += 1;
				this.hp += 20;
				this.atk += 1.5;
				this.def += 1;
				this.sp_def += 0.5;
				this.spd += 0.035;
				this.xp = 0;
				break;
			case "Knight":
				this.lv += 1;
				this.hp += 25;
				this.atk += 1;
				this.def += 2.5;
				this.sp_def += 1;
				this.spd += 0.025;
				this.xp = 0;
				break;
			case "Archer":
				this.lv += 1;
				this.hp += 10;
				this.atk += 1;
				this.def += 0.5;
				this.sp_def += 1;
				this.spd += 0.05;
				this.xp = 0;
				break;
			case "Ninja":
				this.lv += 1;
				this.hp += 7.5;
				this.atk += 1.5;
				this.def += 0.175;
				this.sp_def += 0.5;
				this.spd += 0.125;
				this.xp = 0;
				break;
			case "Samurai":
				this.lv += 1;
				this.hp += 12.5;
				this.atk += 2;
				this.def += 0.5;
				this.sp_def += 1.25;
				this.spd += 0.05;
				this.xp = 0;
				break;
			case "Wizard":
				this.lv += 1;
				this.hp += 10;
				this.atk += 0.1;
				this.def += 0.175;
				this.sp_atk += 0.75;
				this.sp_def += 1.5;
				this.spd += 0.075;
				this.xp = 0;
				break;
			case "Mage":
				this.lv += 1;
				this.hp += 7.5;
				this.atk += 0.25;
				this.def += 0.1;
				this.sp_atk += 1.5;
				this.sp_def += 1;
				this.spd += 0.1;
				this.xp = 0;
				break;
			case "Lich":
				this.lv += 1;
				this.hp += 50;
				this.atk += 0.5;
				this.sp_atk += 0.5;
				this.spd += 0.025;
				this.xp = 0;
				break;
			}
		}
	}
}
