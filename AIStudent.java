public class AIStudent extends Student {

	//Creating the constructor
	public AIStudent (String name) {
		super(name,6,7,7,5,3);
	}

	//declaring the special abilities
	public void machineLearning(Character enemy) throws Exception {
		
		/****************************** Initial Checks *********************************/
		//Checking if the character can use this special ability
		if(currentKP < maxKP) {
			throw new Exception("Special ability cannot be used -- Not enough KPs yet :(");
		}
		//If the enemy is already dead, don't do nothing
		if(enemy.getHP() == 0) {
			throw new Exception("Enemy already dead - you just lost a move");
		}
		/******************************************************************************/

		this.increaseEP(4);
		this.currentKP = 0;

		//double damage of the basic attack of the character
		int damage = (2 * (100 * this.getAttack()) / (100 + enemy.getDefence()));
		enemy.decreaseHP(damage);
		
		System.out.println(this.getName() + " uses machineLearning to attack "+ enemy.getName());
		System.out.println("Damage: -" + damage);

		//If target was killed, get 4 extra EP points
		if(enemy.getHP() == 0) {
			this.increaseEP(4);
			System.out.println("Enemy killed +4 EPs");			
		}
	}

	public void naturalLanguageProcessing() throws Exception {
		
		/****************************** Initial Checks *********************************/
		//Checking if the character can use this special ability
		if(currentKP < maxKP) {
			throw new Exception("Special ability cannot be used -- Not enough KPs yet :(");
		}
		/******************************************************************************/

		this.increaseEP(4);
		this.currentKP = 0;
		this.increaseHP(this.getDefence());
		
		System.out.println(this.getName() + " uses naturalLanguageProcessing secial skill");
		System.out.println("Increasing HP by +" + this.getDefence());
	}

	//AI students only heal themselves if they are the weaker players of the team using the naturalLanguageProcessing method
	//Otherways, they attack using their machine learning skills
	public void specialMove(Team enemyTeam) throws Exception {
		if(this.getTeam().minPlayerHP(this).getHP() > this.getHP()) {
			naturalLanguageProcessing();
		}
		else {
			machineLearning(enemyTeam.maxPlayerHP());
		}
	}
}