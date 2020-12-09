public class SEStudent extends Student {

	//Creating the constructor
	public SEStudent (String name) {
		super(name,8,5,8,4,10);
	}

	//declaring the special abilities
	public void groupWork(Character enemy) throws Exception {
		
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
		int damage = 0;
		
		System.out.println(" uses groupWork attack the enemy team");

		//Create a characters array of the character team
		Character[] team_members = ((Character)this).getTeam().getMembers();
		//Iterate over all members of the team
		for(int i = 0; i < team_members.length; i++){
			Character ally = team_members[i];
			//If team member is still alive
			if(ally.getHP() > 0){
				//Attack the enemy with his basic attack
				damage = ((100 * ally.getAttack()) / (100 + enemy.getDefence()));
				enemy.decreaseHP(damage);
			}
		}

		/********************Give aknowledge points********************/
		//If target was killed, get 4 extra EP points
		if(enemy.getHP() == 0) {
			this.increaseEP(4);
			System.out.println("An enemy has been killed");			
		}
		this.increaseEP(4);
		/***************************************************************/
	}

	public void groupDiscussion() throws Exception {
		
		/****************************** Initial Checks *********************************/
		//Checking if the character can use this special ability
		if(currentKP < maxKP) {
			throw new Exception("Special ability cannot be used -- Not enough KPs yet :(");
		}
		/******************************************************************************/

		this.currentKP = 0;
		int damage = 0;

		System.out.println(" uses groupDiscussion to heal alive allies");

		//Create a characters array of the character team
		Character[] team_members = ((Character)this).getTeam().getMembers();
		//Iterate over all members of the team
		for(int i = 0; i < team_members.length; i++){
			Character ally = team_members[i];
			//If team member is still alive 
			if(ally.getHP() > 0){
				//increase his HP by half the value of this player Defence
				ally.increaseHP(this.getDefence() / 2);
			}
		}
		
		/********************Give aknowledge points********************/
		this.increaseEP(4);
		/***************************************************************/
	}

	//SES students see the general image of the game: if the students' team has less HP that the enemies, they heal themselves 
	//Otherways, they combine their powers to take down the strongest enemy
	public void specialMove(Team enemyTeam) throws Exception {
		if(this.getTeam().totalTeamHP() < enemyTeam.totalTeamHP()) {
			groupDiscussion();
		}
		else {
			groupWork(enemyTeam.maxPlayerHP());
		}
	}
}