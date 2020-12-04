import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
	public static void main(String args[]) {
		Team steam = new StudentTeam("Student Team");
		Team mteam = new MonsterTeam("Monster Team");

		Character studentA = new AIStudent("AIStudent1");
		Character studentB = new AIStudent("AIStudent2");
		Character studentC = new CyberStudent("CyberStudent1");
		Character studentD = new CyberStudent("CyberStudent2");
		Character studentE = new SEStudent("SEStudent1");
		Character studentF = new SEStudent("SEStudent1");
		Character studentG = new CSStudent("CSStudent1");
		Character studentH = new CSStudent("CSStudent2");


		steam.addMember(studentA);
		steam.addMember(studentB);
		steam.addMember(studentC);
		steam.addMember(studentD);
		steam.addMember(studentE);
		steam.addMember(studentF);
		steam.addMember(studentG);
		steam.addMember(studentH);

studentA.setTeam(steam);
studentB.setTeam(steam);
studentC.setTeam(steam);
studentD.setTeam(steam);
studentE.setTeam(steam);
studentF.setTeam(steam);
studentG.setTeam(steam);
studentH.setTeam(steam);

		Character monster1 = new Minion("Minion1");
		Character monster2 = new Minion("Minion2");
		Character monster3 = new Minion("Minion3");
		Character monster4 = new Boss("Boss1");
		Character monster5 = new Boss("Boss2");
		Character monster6 = new Boss("Boss3");
		Character monster7 = new Boss("Boss4");


		mteam.addMember(monster1);
		mteam.addMember(monster3);
		mteam.addMember(monster2);
		mteam.addMember(monster4);
		mteam.addMember(monster5);
		mteam.addMember(monster6);
		mteam.addMember(monster7);

monster1.setTeam(mteam);
monster2.setTeam(mteam);
monster3.setTeam(mteam);
monster4.setTeam(mteam);
monster5.setTeam(mteam);
monster6.setTeam(mteam);
monster7.setTeam(mteam);


//Creating a guild
		Guild g = new Guild();
		g.addMember((AIStudent)studentA);
		g.addMember((AIStudent)studentB);
		g.addMember((CyberStudent)studentC);
		g.addMember((CyberStudent)studentD);
		g.addMember((SEStudent)studentE);
		g.addMember((SEStudent)studentF);
/*
		ArrayList<Character> a = new ArrayList<Character>();
		a = g.getTeam(mteam);
		System.out.println(a.size());
		try {
			mteam.move((Character)monster1,steam);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		for(Character c : a){
			System.out.println(c.getName()+" "+c.getHP());
		}*/
		Team teamStudents = g.getTeam(mteam);
		Team teamMonsters = (Team)mteam;
		Battle b = new Battle(teamStudents, teamMonsters);
		b.fight();
/*
		Team team_awesome = new Team("awesome"); 

		System.out.println(studentB.getName());
		System.out.println(studentB.getMaxHP());
		System.out.println(studentB.getAttack());
		System.out.println(studentB.getDefence());
		System.out.println(studentB.getSpeed());
		System.out.println(studentB.getHP());
		System.out.println(studentB.getEP());
		studentA.increaseHP(10);
		System.out.println(studentA.getHP());
		studentA.decreaseHP(10);
		System.out.println(studentA.getHP());
		studentA.increaseEP(1);
		System.out.println(studentA.getEP());

		System.out.println(team_awesome.getName());
		team_awesome.addMember(studentA);
		team_awesome.addMember(studentB);
		Monster[] team_members = team_awesome.getMembers();

		for(int i=0; i<team_members.length; i++){
			System.out.println(team_members[i].getName());
		}
		studentA.increaseHP((int)Math.round(10 * Math.pow( 1, 1.2 )));
		System.out.println(studentA.getHP());
		studentA.increaseHP((int)Math.round(10 * Math.pow( 2, 1.2 )));
		System.out.println(studentA.getHP());
		studentA.increaseHP((int)Math.round(10 * Math.pow( 3, 1.2 )));
		System.out.println(studentA.getHP());*/
	}
}