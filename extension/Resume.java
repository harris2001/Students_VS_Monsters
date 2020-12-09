import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class Resume {

	public static void main(String[] args) throws Exception {
		
		try {
			//Creating a file object and trying to read its context
			File obj = new File("log.txt");
			Scanner sc = new Scanner(obj);
			//Creating two empty teams
			MonsterTeam monsterTeam = new MonsterTeam("newMonsterTeam");
			StudentTeam studentTeam = new StudentTeam("newStudentTeam");

			//Creating students guild
			Guild guild = new Guild();

			//Creating students (2x Each subject)
			Character studentA = new AIStudent("AIStudent1");
			Character studentB = new AIStudent("AIStudent2");
			Character studentC = new CyberStudent("CyberStudent1");
			Character studentD = new CyberStudent("CyberStudent2");
			Character studentE = new SEStudent("SEStudent1");
			Character studentF = new SEStudent("SEStudent2");
			Character studentG = new CSStudent("CSStudent1");
			Character studentH = new CSStudent("CSStudent2");

			ArrayList<Character> students = new ArrayList<Character>();

			students.add(studentA);
			students.add(studentB);
			students.add(studentC);
			students.add(studentD);
			students.add(studentE);
			students.add(studentF);
			students.add(studentG);
			students.add(studentH);

			//adding students to the guild
			for(int i = 0; i < students.size(); i++) {
				guild.addMember(students.get(i));
			}
			
			Resume res = new Resume(); 
			int games = 0;
			int roundd = 0;
			//reading the name of the file used as input to the original program
			String line = sc.nextLine();
			String argv = line.split(":")[0];
			int difficulty = Integer.parseInt(line.split(":")[1]);
			if(argv.equals("No records of the last fight")){
				System.out.println(argv);
				return;
			}

			//read the log file
			while(sc.hasNextLine()) {
				//count the rounds in each game
				roundd++;
				line = sc.nextLine();

				//stepping one tower level up - game won by students
				if(line.contains("<>")) {
					roundd = 0;
					games++;
					continue;
				}
				
				//Creating students team by parsing the log file
				StudentTeam st = res.parserStudent(line);
				//Creating monsters team by parsing the log file
				MonsterTeam mt = res.parserMonster(line);

				//If game is finished then return
				if(mt == null) {
					return;
				}

				studentTeam = new StudentTeam("newStudentTeam");

				//Making changes to the students team based on the results of each round
				for(Character student : st.getMembers()) {

					boolean found = false;

					studentTeam.addMember(student);
					//Searching the current student team and updating student
					for(Character c : guild.getMembers()) {
						//If c is the student then update his HP, EP and KP
						if(c.getName().equals(student.getName())) {
							c.setHP(student.getHP());
							c.setEP(student.getEP());
							c.setlevel(student.getLevel());
							Student s = (Student) c;
							Student s1 = (Student) student;
							s.setKP(s1.getKP());
							//Student found
							found = true;
							break;
						}
					}
					//If student is not found, then add it to the guild
					if(found == false) {
						guild.addMember(student);
					}
				}


				//Making changes to the monster team based on the results of each round
				for(Character monster : mt.getMembers()) {

					boolean found = false;

					//Searching the current monster team and updating monster m
					for(Character c : monsterTeam.getMembers()) {
						//If c is the monster m then update its HP and EP
						if(c.getName().equals(monster.getName())) {
							c.setHP(monster.getHP());
							c.setEP(monster.getEP());
							c.setlevel(monster.getLevel());
							//Monster found
							found = true;
							break;
						}
					}
					//If monster is not found, then add it to the monster team
					if(found == false) {
						monsterTeam.addMember(monster);
					}
				}
			}		
		
		/*---------------------Game restored, continue-----------------------*/

			System.out.println();
			System.out.println("              Restored Students Data:");
			System.out.println(" ------------------------------------------------------");
			System.out.println("|      NAME       |   HP   |   EP   |   KP   |  LEVEL  |");
			System.out.println("|_________________|________|________|________|_________|");

			for(Character c1 : guild.getMembers()) {
				Student s = (Student) c1;
				String status = String.format("|%1$15s  | %2$4s   | %3$4s   | %4$4s   | %5$4s    |", c1.getName(), c1.getHP(), c1.getEP(), s.getKP(), c1.getLevel());
				System.out.println(status);
			}

			System.out.println(" ------------------------------------------------------");
			System.out.println();
			System.out.println("           Restored Monsters Data:");
			System.out.println(" --------------------------------------------- ");
			System.out.println("|      NAME       |   HP   |   EP   |  LEVEL  |");
			System.out.println("|_________________|________|________|_________|");

			for(Character c2 : monsterTeam.getMembers()) {
				String status = String.format("|%1$15s  | %2$4s   | %3$4s   | %4$4s    |",c2.getName(),c2.getHP(),c2.getEP(), c2.getLevel());
				System.out.println(status);
			}
			System.out.println(" --------------------------------------------- ");
					

		/*-------------------------------------------------------------------*/
			boolean resume = false;
			//Defining output file
			PrintStream out = null;
			FileOutputStream fout = new FileOutputStream("log.txt",true);
			out = new PrintStream(fout);

			Scanner scanner = new Scanner(new File(argv));
			
			int counter = 1;
			while(scanner.hasNextLine()) {
				//skipping the tower levels the students have already fought.
				if(counter < games) {
					counter++;
					res.parser(scanner.nextLine());
					continue;
				}
				System.out.println();
				System.out.println("*********************************************************************************");
				System.out.println(" 	 			     Game: " + games);
				System.out.println("*********************************************************************************");
				//Creating monsters team by parsing the input file
				monsterTeam = res.parser(scanner.nextLine());
				while(true) {
					//Creating students battle team from guild
					StudentTeam battleTeam = null;
					if(resume == true) {
						battleTeam = studentTeam;
					}
					else{					
						battleTeam = (StudentTeam)guild.getTeam(monsterTeam);
						resume = false;
					}
					if(battleTeam.getMembers().length == 0) {
						System.out.println("_______________________________________________");
						System.out.println("Game Over, students team lost after " + games + " games :(");
						System.out.println("-----------------------------------------------");
						//loggin the win of the monsters' team
						out.print("winner:monsters");
						return;
					}
					else {
						for(Character c : battleTeam.getMembers()) {
							c.setTeam(battleTeam);
						}
					}
					Battle b = new Battle((Team)battleTeam, (Team)monsterTeam);
					Team winner = b.fight(out, true, roundd, difficulty);
					if(winner instanceof StudentTeam){
						break;
					}
				}
				games++;
			}
			System.out.println("Students Team WON !!!");
			//loggin the win of the students' team
			out.print("winner:students");
		}
		catch (FileNotFoundException e) {
	      System.out.println("The game was not saved");
	      e.printStackTrace();
	    }
	}

	public StudentTeam parserStudent(String line) {

		if(line.split(":")[0].equals("winner")) {
			System.out.println("Game already finished");
			System.out.println(line);
			return null;
		}

		StudentTeam battleTeam = new StudentTeam("StudentsTeam");

		String type, name, hps, eps, kps, levels;
		int hp, ep, kp, level;

		String round[] = line.split("---");
		//Students
		for(String ss : round[0].split(";")) {
			name = ss.split(":")[0];
			type = ss.split(":")[1];
			hps = ss.split(":")[2];
			eps = ss.split(":")[3];
			kps = ss.split(":")[4];
			levels = ss.split(":")[5];
			hp = Integer.parseInt(hps);
			ep = Integer.parseInt(eps);
			kp = Integer.parseInt(kps);
			level = Integer.parseInt(levels);

			if(type.equals("AIStudent")) {
				AIStudent s = new AIStudent(name);
				s.setHP(hp);
				s.setEP(ep);
				s.setKP(kp);
				s.setlevel(level);
				s.setTeam(battleTeam);
				battleTeam.addMember(s);
			}
			else if(type.equals("CSStudent")) {
				CSStudent s = new CSStudent(name);
				s.setHP(hp);
				s.setEP(ep);
				s.setKP(kp);
				s.setlevel(level);
				s.setTeam(battleTeam);
				battleTeam.addMember(s);
			}
			else if(type.equals("CyberStudent")) {
				CyberStudent s = new CyberStudent(name);
				s.setHP(hp);
				s.setEP(ep);
				s.setKP(kp);
				s.setlevel(level);
				s.setTeam(battleTeam);
				battleTeam.addMember(s);
			}
			else if(type.equals("SEStudent")) {
				SEStudent s = new SEStudent(name);
				s.setHP(hp);
				s.setEP(ep);
				s.setKP(kp);
				s.setlevel(level);
				s.setTeam(battleTeam);
				battleTeam.addMember(s);
			}
		}
		return battleTeam;
	}

	public MonsterTeam parserMonster(String line) {
		
		if(line.split(":")[0].equals("winner")) {
			return null;
		}

		MonsterTeam monsterTeam = new MonsterTeam("MonsterTeam");

		String type, name, hps, eps, levels;
		int hp, ep, level;

		String round[] = line.split("---");

		//Monsters
		for(String s : round[1].split(";")) {
			
			name = s.split(":")[0];
			type = s.split(":")[1];
			hps = s.split(":")[2];
			eps = s.split(":")[3];
			levels = s.split(":")[4];
			hp = Integer.parseInt(hps);
			ep = Integer.parseInt(eps);
			level = Integer.parseInt(levels);

			if(type.equals("Minion")) {
				Minion m = new Minion(name);
				m.setHP(hp);
				m.setEP(ep);
				m.setTeam(monsterTeam);
				m.setlevel(level);
				monsterTeam.addMember(m);
			}
			else if(type.equals("Boss")) {
				Boss m = new Boss(name);
				m.setHP(hp);
				m.setEP(ep);
				m.setlevel(level);
				m.setTeam(monsterTeam);
				monsterTeam.addMember(m);
			}
		}
		return monsterTeam; 
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