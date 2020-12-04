import java.util.Random;

public class StudentTeam extends Team {
	
	//Defining the constructor
	public StudentTeam(String name) {
		super(name);
	}

	public void move(Character member, Team enemyTeam) throws Exception {

		//this boolean remains false if all members of a team are dead
		boolean aliveMembers = false;

		Student student = (Student)member;

		for(Character enemy : enemyTeam.getMembers()) {
			if(enemy.getHP() > 0) {
				if(student.MaxKPReached()) {
					student.specialMove(enemyTeam);
				}
				else {
					Random r = new Random();
					int n = r.nextInt(2);
					if(n == 1)
						student.strike(enemyTeam.strongestPlayer());
					else
						student.strike(enemy);
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