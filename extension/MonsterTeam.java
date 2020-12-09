public class MonsterTeam extends Team {
	
	//Defining the constructor
	public MonsterTeam(String name) {
		super(name);
	}

	public void move(Character member, Team enemyTeam, Integer difficulty) throws Exception {
		
		//this boolean remains false if all members of a team are dead
		boolean aliveMembers = false;
		
		for(Character s : enemyTeam.getMembers()) {
			if(s.getHP() > 0) {
				Monster monster = (Monster) member;
				monster.strike(s, difficulty);
				aliveMembers = true;
				break;
			}
		}
		if(aliveMembers == false) {
			throw new Exception("Enemy team dead");
		}
	}
}