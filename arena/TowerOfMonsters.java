import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TowerOfMonsters {
	
	public static void main(String[] args) throws Exception {
		//If a file was not specified
		if(args.length != 1) {
			throw new Exception("Please provide the file configuration e.g. java TowerOfMonsters {monsters.txt}");
		}
		try {
			//Creating a file object and trying to read its context
			File obj = new File(args[0]);
			Scanner sc = new Scanner(obj);
			//Creating two empty teams
			MonsterTeam monsterTeam = new MonsterTeam("newMonsterTeam");

			//Creating students guild
			Guild guild = new Guild();

			//Creating students (2x Each subject)
			Character studentA = new AIStudent("AIStudent1");
			Character studentB = new AIStudent("AIStudent2");
			Character studentC = new CyberStudent("CyberStudent1");
			Character studentD = new CyberStudent("CyberStudent2");
			Character studentE = new SEStudent("SEStudent1");
			Character studentF = new SEStudent("SEStudent1");
			Character studentG = new CSStudent("CSStudent1");
			Character studentH = new CSStudent("CSStudent2");

			//Adding students into guild
			guild.addMember(studentA);
			guild.addMember(studentB);
			guild.addMember(studentC);
			guild.addMember(studentD);
			guild.addMember(studentE);
			guild.addMember(studentF);
			guild.addMember(studentG);
			guild.addMember(studentH);

			int games = 1;

			while(sc.hasNextLine()) {

				System.out.println();
				System.out.println("*********************************************************************************");
				System.out.println(" 	 			     Game: " + games);
				System.out.println("*********************************************************************************");				TowerOfMonsters tom = new TowerOfMonsters();
				//Creating monsters team by parsing the input file
				monsterTeam = tom.parser(sc.nextLine());
				while(true) {
					//Creating students battle team from guild
					StudentTeam battleTeam = (StudentTeam)guild.getTeam(monsterTeam);
					if(battleTeam.getMembers().length == 0) {
						System.out.println("_______________________________________________");
						System.out.println("Game Over, students team lost after " + games + " games :(");
						System.out.println("-----------------------------------------------");
						return;
					}
					else {
						for(Character c : battleTeam.getMembers()) {
							c.setTeam(battleTeam);
						}
					}
					Battle b = new Battle((Team)battleTeam, (Team)monsterTeam);
					Team winner = b.fight();
					if(winner instanceof StudentTeam){
						break;
					}
				}
				games++;
			}
			System.out.println("Students Team WON !!!");

		}
		catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	}

	public MonsterTeam parser(String line) {
		MonsterTeam monsters = new MonsterTeam("MonstersTeam");
		
		String team[] = line.split(";");

		for(String monster: team) {
			//Parsing the team
			String s[] = monster.split("\\(");
			String name = s[0];
			String type = s[1].split(",")[0];
			String levelString = s[1].split(",")[1].split("\\)")[0];
			//Converting strin to integer
			Integer level = Integer.parseInt(levelString);

			if(type.equals("Minion")) {
				//Creating new minion
				Minion m = new Minion(name);
				//Setting minion team
				m.setTeam(monsters);
				//adding minion to monsters team
				monsters.addMember(m);
				//increasing level
				for(int i = 1; i < level; i++) {
					m.increaseEP(m.getTargetEP());
				}
			}
			else if(type.equals("Boss")) {
				//Creating new minion
				Boss b = new Boss(name);
				//Setting minion team
				b.setTeam(monsters);
				//adding minion to monsters team
				monsters.addMember(b);
				//increasing level
				for(int i = 1; i < level; i++) {
					b.increaseEP(b.getTargetEP());
				}
			}
		}
		return monsters;
	}
}