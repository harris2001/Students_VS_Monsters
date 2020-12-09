import java.util.ArrayList;
import java.util.Random;

public class Guild {
	
	private ArrayList <Character> guildArray;

	//Defining Constructor 
	public Guild() {
		guildArray = new ArrayList <Character>();
	}

	//Defining getters and setters
	public void addMember(Character c) {
		guildArray.add(c);
	}

	public ArrayList<Character> getMembers() {
		//creating a new array wich is a copy of the guildArray so that users cannot modify its elements
		ArrayList<Character> lista = new ArrayList<Character>();
		for(Character chr : guildArray) {
			lista.add(chr);
		}
		return lista;
	}

	public Team getTeam(Team enemyTeam) {
		//creating the battle team from the guild to face the enemy team 
		ArrayList<Character>battleArray = new ArrayList<Character>();
		boolean complete = false;
		
		for(Character c : guildArray) {
			Student s = (Student) c;
			//Choosing alive students whose KP is max thus they can perform stronger attacks
			if(s.MaxKPReached() && s.getHP() > 0){
				battleArray.add(s);
			}
			//if the size of the team is 5, then the team is completed
			if(battleArray.size() == 5){
				complete = true;
				break;
			}
		}

		//If the team still needs members search for members
		for(int i = 0; i < guildArray.size() && complete == false; i++) {
			//Check if the student is not already in the battle team and alive
			Student student = (Student)guildArray.get(i);
			if(!battleArray.contains(student) && student.getHP() > 0){ 
				battleArray.add(student);
			}
			if(battleArray.size() == 5) {
				complete = true;
				break;
			}
		}
		Team battleTeam = new StudentTeam("BattleTeam");
		for(Character c : battleArray) {
			battleTeam.addMember(c);
		}

		return battleTeam;
	}

}