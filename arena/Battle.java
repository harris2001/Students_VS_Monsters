import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class Battle {

	private Team students, monsters;

	public Battle(Team students, Team monsters) {
		this.students = students;
		this.monsters = monsters;
	}

	public Team fight() {
		for(int i = 0; i < 30; i++) {
			try {
				Thread.sleep(500);
				System.out.println();
				System.out.println("          Round #" + (i + 1) +":");
				System.out.println(" -----------------------------------");
				
				ArrayList<Character> players = sorting();

				for(Character c : players) {
					if(c.getHP() > 0) {
						try {
							if(c instanceof Student) {
								System.out.print(c.getName() + "(student):");
								students.move(c, monsters);
							}
							else {
								System.out.print(c.getName() + "(monster):");
								monsters.move(c,students);
							}
						}
						catch(Exception e) {
							System.out.println(e);
						}
					}
				}


				//this boolean remains false if all members of the students' team are dead
				boolean aliveMembers1 = false;

				//this boolean remains false if all members of the monsters' are dead
				boolean aliveMembers2 = false;

				//Round Results
				System.out.println();
				System.out.println("                     Students team:");
				System.out.println(" ------------------------------------------------------");
				System.out.println("|      NAME       |   HP   |   EP   |   KP   |  LEVEL  |");
				System.out.println("|_________________|________|________|________|_________|");

				for(Character c1 : students.getMembers()) {
					Student s = (Student) c1;
					String status = String.format("|%1$15s  | %2$4s   | %3$4s   | %4$4s   | %5$4s    |", c1.getName(), c1.getHP(), c1.getEP(), s.getKP(), c1.getLevel());
					System.out.println(status);
					if(c1.getHP() > 0) {
						aliveMembers1 = true;
					}
				}
				System.out.println(" ------------------------------------------------------");
				System.out.println();
				System.out.println("          Monsters team:");
				System.out.println(" --------------------------------------------- ");
				System.out.println("|      NAME       |   HP   |   EP   |  LEVEL  |");
				System.out.println("|_________________|________|________|_________|");

				for(Character c2 : monsters.getMembers()) {
					String status = String.format("|%1$15s  | %2$4s   | %3$4s   | %4$4s    |",c2.getName(),c2.getHP(),c2.getEP(), c2.getLevel());
					System.out.println(status);
					if(c2.getHP() > 0) {
						aliveMembers2 = true;
					}
				}
				System.out.println(" --------------------------------------------- ");

				if(aliveMembers2 == false) {
					System.out.println();
					System.out.println("  ==>  Students Team wins after " + (i + 1) + " rounds");
					System.out.println();
					return students;
				}

				if(aliveMembers1 == false) {
					System.out.println();
					System.out.println("  ==>  Monsters Team wins after " + (i + 1) + " rounds");
					System.out.println();
					return monsters;
				}
			}
			catch (InterruptedException e) {
				System.out.println(e);
			} 
		}
		return null;
	}

	public ArrayList<Character> sorting() {
		//Creating a big arraylist containing players from both teams
		ArrayList<Character> players = new ArrayList<Character>();

		//adding alive members to the players arraylist
		for(Character member : this.students.getMembers()) {
			if(member.getHP() > 0) {
				players.add(member);
			}
		}

		for(Character member : this.monsters.getMembers()) {
			if(member.getHP() > 0) {
				players.add(member);
			}
		}

		//sorting array based on the players speed
		Collections.sort(players);
		
		return players;
	}
}