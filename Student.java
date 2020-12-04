import java.util.Random;

public abstract class Student extends Character {
	
	//Declaring the member variables
	protected int maxKP, currentKP;

	//Creating the constructor
	public Student(String name, int baseHP, int baseAtk, int baseDef, int baseSpd, int maxKP) {
			super(name, baseHP, baseAtk, baseDef, baseSpd);
			this.maxKP = maxKP;
			currentKP = 0;
	}

	public void increaseKP(int amount) {
		this.currentKP += amount;
	}

	public int getKP() {
		return currentKP;
	}

	public void javaProgramming(Character enemy) {
		this.increaseEP(3);
		this.increaseKP(1);
		//Decreasing enemy's HP using characters attacking power and enemy's defence
		int damage = (100 * this.getAttack()) / (100 + enemy.getDefence());
		enemy.decreaseHP(damage);

		System.out.println(this.getName() + " uses javaProgramming() to attack " + enemy.getName());
		System.out.println("Damage: -" + damage);
		
		//Enemy gains 2 experience points
		enemy.increaseEP(2);
		//If the enemy is a student increase its Knowledge points by 3
		if(enemy instanceof Student) {
			//Making reference en to point to the typecasted enemy Student object
			Student en = (Student) enemy;
			en.increaseKP(3);
		}
		//if the enemy is dead, the character gains 4 experience points
		if(enemy.getHP() == 0) {
			this.increaseEP(4);
		}
	}

	public void selfStudy() {
		System.out.println(" uses selfStudy() to heal himself");
		this.increaseHP(2);
		this.increaseEP(6);
		this.increaseKP(2);
	}

	//Returns true when a student has reached the full KPs and can perform a special attack
	public boolean MaxKPReached(){
		if(this.currentKP == this.maxKP) {
			return true;
		}
		return false;
	}	

	public void strike(Character enemy) {
		Random r = new Random();
		int n = r.nextInt(100);
		if(n < 70) {
			javaProgramming(enemy);
		}
		else {
			selfStudy();
		}
	}

	//Each students decides which special skill to execute and how
	// => specialSkill should be provided by every student subclass (its defined as abstract here)
	public abstract void specialMove(Team enemyTeam) throws Exception;
}