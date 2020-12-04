import java.util.ArrayList;

abstract public class Team {

	//Defining the member variables
	private String name;
	ArrayList<Character> members;

	//Defining constructor
	public Team(String name) {
		
		this.name = name;
		members = new ArrayList<Character>();
	}

	//Creating getters and setters

	public String getName() {
		return this.name;
	}

	public Character[] getMembers() {
		Character[] chr = new Character[members.size()];
		//Iterate over every element of the member array list and add it to the ne character array to be returned
		for(int i = 0; i < members.size(); i++) {
			chr[i] = members.get(i);
		}
		return chr;
	}

	public int addMember(Character member) {
		if(this.members.contains(member)){
			return -1;
		}
		if(this.members.size() == 5){
			return -2;
		}
		this.members.add(member);
		return this.members.size();
	}

	//Defining the abstract method
	abstract void move(Character member, Team enemyTeam) throws Exception;

/******************* Defining a methods for getting the stats of a team *****************/

	//Getting the maximum HP player
	public Character maxPlayerHP() {
		int maxHP = 0;
		Character target = null;
		for(Character teamMember : members) {
			if(teamMember.getHP() > maxHP) {
				maxHP = teamMember.getHP();
				target = teamMember;
			}
		}
		return target;
	}

	//Getting the maximum HP player but not the same student
	public Character maxPlayerHP(Character me) {
		int maxHP = 0;
		Character target = null;
		for(Character teamMember : members) {
			if(teamMember.getHP() > maxHP && teamMember != me) {
				maxHP = teamMember.getHP();
				target = teamMember;
			}
		}
		return target;
	}

	//Getting the player with the least HP but not the same student
	public Character minPlayerHP(Character me) {
		int minHP = 999999999;
		Character player = null;
		for(Character teamMember : members) {
			if(teamMember.getHP() < minHP && teamMember.getHP() > 0 && teamMember != me) {
				minHP = teamMember.getHP();
				player = teamMember;
			}
		}
		return player;
	}

	//Getting the total HP of the team
	public int totalTeamHP() {
		int result = 0;
		for(Character teamMember : members) {
			result += teamMember.getHP();
		}
		return result;
	}

	//Returns the strongest player of the team
	public Character strongestPlayer() {
		int maxAtk = 0;
		Character strongest = null;
		for(Character teamMember : members) {
			if(teamMember.getAttack() > maxAtk && teamMember.getHP() > 0) {
				maxAtk = teamMember.getAttack();
				strongest = teamMember;
			}
		}
		return strongest;
	}
/****************************************************************************************/
}