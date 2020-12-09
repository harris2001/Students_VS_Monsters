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

		this.currentKP = 0;

		//double damage of the basic attack of the character
		int damage = (2 * (100 * this.getAttack()) / (100 + enemy.getDefence()));
		
		System.out.println(this.getName() + " uses machineLearning to attack "+ enemy.getName());
		enemy.decreaseHP(damage);
		

		//If target was killed, get 4 extra EP points
		if(enemy.getHP() == 0) {
			System.out.println("An enemy has been killed");			
			this.increaseEP(4);
		}

	}

	public void naturalLanguageProcessing() throws Exception {
		
		/****************************** Initial Checks *********************************/
		//Checking if the character can use this special ability
		if(currentKP < maxKP) {
			throw new Exception("Special ability cannot be used -- Not enough KPs yet :(");
		}
		/******************************************************************************/

		this.currentKP = 0;
		
		System.out.println(this.getName() + " uses naturalLanguageProcessing secial skill");
		this.increaseHP(this.getDefence());

	}

	//AI students only heal themselves if they are the weaker players of the team using the naturalLanguageProcessing method
	//Otherways, they attack using their machine learning skills
	public void specialMove(Team enemyTeam, Integer difficulty) throws Exception {
		if(this.getTeam().minPlayerHP(this).getHP() > this.getHP()) {
			naturalLanguageProcessing();
		}
		else {
			machineLearning(enemyTeam.maxPlayerHP());
		}
		
		/********************Give aknowledge points********************/
		this.increaseEP(4);
		/***************************************************************/
	}
}