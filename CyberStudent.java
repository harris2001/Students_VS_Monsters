public class CyberStudent extends Student {

	//Creating the constructor
	public CyberStudent (String name) {
		super(name,7,7,5,6,6);
	}

	//declaring the special abilities
	public void cyberAttack(Team enemyTeam) throws Exception {
		
		System.out.println("cyberAttack on team " + enemyTeam.getName());
		/****************************** Initial Checks *********************************/
		//Checking if the character can use this special ability
		if(currentKP < maxKP) {
			throw new Exception("Special ability cannot be used -- Not enough KPs yet :(");
		}
		/******************************************************************************/
		
		this.increaseEP(4);
		this.currentKP = 0;
		int damage = 0;

		//create a characters array - the enemy team
		Character[] targets = enemyTeam.getMembers();
		//attack every member of the enemy team
		for(int i = 0; i < targets.length; i++){
			Character enemy = targets[i];
			//If enemy is still alive
			if(enemy.getHP() > 0) {
				//Attack him
				damage = ((100 * this.getAttack()) / (100 + enemy.getDefence()));
				enemy.decreaseHP(damage);

				System.out.println("Damage -" + damage + " to " + enemy.getName());

				//If target was killed, get 4 extra EP points
				if(enemy.getHP() == 0) {
					this.increaseEP(4);
					System.out.println("Enemy killed +4 EPs");			
				}
			}
		}
	}

	public void specialMove(Team enemyTeam) throws Exception {
		cyberAttack(enemyTeam);
	}
}