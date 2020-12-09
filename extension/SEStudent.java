import java.util.Random;

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
		
		System.out.println(" uses groupWork attack:");

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
				System.out.println("Damage -" + damage + " to " + enemy.getName());
			}
		}

		//If target was killed, get 4 extra EP points
		if(enemy.getHP() == 0) {
			this.increaseEP(4);
			System.out.println("An enemy has been killed");			
		}
		this.increaseEP(4);
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

		System.out.println(" uses groupDiscussion attack:");

		//Create a characters array of the character team
		Character[] team_members = ((Character)this).getTeam().getMembers();
		//Iterate over all members of the team
		for(int i = 0; i < team_members.length; i++){
			Character ally = team_members[i];
			//If team member is still alive 
			if(ally.getHP() > 0){
				//increase his HP by half the value of this player Defence
				System.out.println("Healing ally: " + ally.getName());
				ally.increaseHP(this.getDefence() / 2);
			}
		}
		this.increaseEP(4);
	}

	//SES students see the general image of the game: if the students' team has less HP that the enemies, they heal themselves 
	//Otherways, they combine their powers to take down the strongest enemy
	public void specialMove(Team enemyTeam, Integer difficulty) throws Exception {
		//Playing with difficulty level
		Random r = new Random();
		int n = r.nextInt(100);
		if(difficulty == 1 && n > 50 || difficulty == 2 && n > 70 || n > 20) {
			groupWork(enemyTeam.maxPlayerHP());
		}
		else {
			groupDiscussion();
		}
	}
}