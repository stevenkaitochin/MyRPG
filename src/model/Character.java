package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public  class Character {

	String type;
	int lv, hp, xp;
	double atk, def, sp_atk, sp_def, spd;
	Image img;
	
	public Character(String type, int lv, int hp, double atk, double def, double sp_atk, double sp_def, double spd, File file) {
		this.type = type;
		this.lv = lv;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.sp_atk = sp_atk;
		this.sp_def = sp_def;
		this.spd = spd;
		this.xp = 0;
		
		try {
			img = ImageIO.read(file);
		} catch (IOException ex) {
			System.err.println("Error: Could not find file path");
		}
	}
}
