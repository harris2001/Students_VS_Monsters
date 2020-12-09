import java.util.Random;

public class CSStudent extends Student {

	//Creating the constructor
	public CSStudent (String name) {
		super(name,7,6,6,6,4);
	}

	//Declaring the special abilities
	public void pairWorking(Character friend, Character enemy) throws Exception {
		
		/****************************** Initial Checks *********************************/
		//Checking if the character can use this special ability
		if(currentKP < maxKP) {
			throw new Exception("Special ability cannot be used -- Not enough KPs yet :(");
		}
		//If the enemy is already dead, don't do nothing
		if(enemy.getHP() == 0) {
			throw new Exception("Enemy already dead - you just lost a move");
		}
		//If the friend is already dead, don't do nothing
		if(friend.getHP() == 0) {
			throw new Exception("Dead friends cannot help you here...");
		}
		/******************************************************************************/

		this.increaseEP(4);
		this.currentKP = 0;

		//combine the basic attack of the character and its friend
		int damage = (100 * (this.getAttack() + friend.getAttack() )) / (100 + enemy.getDefence());
		enemy.decreaseHP(damage);

		System.out.println(this.getName() + " uses pairWorking with friend: " + friend.getName());
		System.out.println("Together they attack " + enemy.getName() + " => Damage: -" + damage);
		
		//If target was killed, get 4 extra EP points
		if(enemy.getHP() == 0) {
			this.increaseEP(4);
			System.out.println("Enemy killed +4 EPs");
		}
	}

	public void support(Character friend) throws Exception {
		
		/****************************** Initial Checks *********************************/
		//Checking if the character can use this special ability
		if(currentKP < maxKP) {
			throw new Exception("Special ability cannot be used -- Not enough KPs yet :(");
		}
		//If the friend is already dead, don't do nothing
		if(friend.getHP() == 0) {
			throw new Exception("We all miss our dead friends, but there is nothing you can do now...");
		}
		/******************************************************************************/

		this.currentKP = 0;
		friend.increaseHP(this.getDefence());
		this.increaseEP(4);
	}

	//Providing implementation for the abstract method in Students class
	public void specialMove(Team enemyTeam, Integer difficulty) throws Exception {
		Team allies = this.getTeam();
		Random r = new Random();
		int n = r.nextInt(100);
		Character powerfulFriend = allies.maxPlayerHP(this);
		Character friendInNeed = allies.minPlayerHP(this);
		Character enemy = enemyTeam.strongestPlayer();
		//CSStudents perform one of their normal attacks at random if they can't perform any special move when no friends are still alive
		if(powerfulFriend == null) {
			System.out.println("Last one standing, must perform classic move");
			if(r.nextInt(2) == 0) {
				this.selfStudy();
			}
			else {
				this.javaProgramming(enemyTeam.strongestPlayer());
			}
		}
		else {
			//Choose randomly and attack the strongest player 
			if(difficulty == 1 && n < 50 || n < 30) {
				pairWorking(powerfulFriend,enemy);
			}
			else {
				support(friendInNeed);
			}
		
		}
	}
}