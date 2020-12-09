import java.util.Random;

public class StudentTeam extends Team {
	
	//Defining the constructor
	public StudentTeam(String name) {
		super(name);
	}

	public void move(Character member, Team enemyTeam, Integer difficulty) throws Exception {

		//this boolean remains false if all members of a team are dead
		boolean aliveMembers = false;

		Student student = (Student)member;

		for(Character enemy : enemyTeam.getMembers()) {
			if(enemy.getHP() > 0) {
				if(student.MaxKPReached() == true) {
					student.specialMove(enemyTeam, difficulty);
				}
				else {
					//Randomly strike either the strongest player of the team or the next enemy
					Random r = new Random();
					int n = r.nextInt(2);
					if(n == 1 && difficulty < 3)
						student.strike(enemyTeam.strongestPlayer(), difficulty);
					else
						student.strike(enemy, difficulty);
				}
				aliveMembers = true;
				break;
			}
		}
		if(aliveMembers == false) {
			throw new Exception("Enemy team dead");
		}
	}

}