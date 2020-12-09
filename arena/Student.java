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

		System.out.println(this.getName() + " uses javaProgramming to attack " + enemy.getName());
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
			System.out.println("Enemy killed +4 EPs");						
		}
	}

	public void selfStudy() {
		System.out.println(" uses selfStudy to heal himself");
		System.out.println("Increasing HP by +2");
		this.increaseHP(2);
		this.increaseEP(6);
		this.increaseKP(2);
	}

	//Returns true when a student has reached the full KPs and can perform a special attack
	public boolean MaxKPReached(){
		if(currentKP >= maxKP) {
			return true;
		}
		return false;
	}	

	//Is called by StudentsTeam to chose which attack to perform
	public void strike(Character enemy) {
		Random r = new Random();
		int n = r.nextInt(100);
		//If KP is max -> do a special move
		if (MaxKPReached() == true){
			try{
				specialMove(enemy.getTeam());
			}
			catch(Exception e) {
				System.out.println(e);
			}
		}
		else if(n < 70) {	// Else perform a basic attack with probability 70% towards javaProgramming
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