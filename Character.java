import java.lang.Math;
import java.util.ArrayList;

public class Character implements Comparable<Character> {

	//Declaring the member variables
	private String name;
	private int baseHP, baseAtk, baseDef, baseSpd;
	private int level;
	private Team team;


	//additional properties
	private int currentHP, currentEP=0;

	//Creating the constructor method
	public Character(String name, int baseHP, int baseAtk, int baseDef, int baseSpd) {
		//Initialising the member variables of the Character Class
		this.name = name;
		this.baseHP = baseHP;
		this.baseAtk = baseAtk;
		this.baseDef = baseDef;
		this.baseSpd = baseSpd;
		this.level = 1;
		this.currentHP = baseHP;
		this.team = null;
	}


	//Defining getter methods

	public String getName() {
		return this.name;
	}	

	public int getMaxHP() {
		return (int)Math.round( baseHP * Math.pow( level, 1.2 ));
	}

	public int getAttack() {
		return (int)Math.round( baseAtk * Math.pow( level, 1.2 ));
	}

	public int getDefence() {
		return (int)Math.round( baseDef * Math.pow( level, 1.2 ));
	}

	public int getSpeed() {
		return (int)Math.round( baseSpd * Math.pow( level, 1.2 ));
	}

	public int getTargetEP() {
		return (int)Math.round( 10 * Math.pow( level, 1.5 ));
	}

	//Defining the accessor methods

	public int getHP() {
		return currentHP;
	}

	public int getEP() {
		return currentEP;
	}

	//Defining the mutators

	public void increaseHP(int amount) {
		//Increasing the HP value by the amount given if it is less than the maxHP
		if(currentHP + amount <= getMaxHP()) {
			currentHP += amount;
		}
		else{
			//If the value is more than the maxHP, assign it the value of the maxHP
			currentHP = getMaxHP();
		}
	}

	public void decreaseHP(int amount) {
		//Decreasing the HP value by the amount given
		if(currentHP - amount >= 0) {
			currentHP -= amount;
		}
		else{
			//If the value is negative, make it 0
			currentHP = 0;
		}
	}

	public void increaseEP(int amount) {
		currentEP += amount;
		if(currentEP >= getTargetEP()) {
			level += 1;
			currentEP = 0;
			if(currentHP > 0) {
				currentHP = getMaxHP();
			}
		}
	}

	//Defining Team setter
	public void setTeam(Team team) {
		this.team = team;
	}

	//Defining Team getter
	public Team getTeam() {
		return this.team;
	}

	//Used to sort the characters of a team based on their speed
	public int compareTo(Character c) {
		if(this.baseSpd == c.getSpeed()) {
			return 0;
		}
		if(this.baseSpd > c.getSpeed()) {
			return 1;
		}
		return -1;
	}
}