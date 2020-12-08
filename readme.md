# Basic implementation #

## Part 1 ##
- I have implemented the Character and Team classes following the coursework specification
- The Character class has a compareTo() as required by the specification at part 5 for sorting
- The Team class has an abstract method move() as required by the specification at part 4
- I have also added 5 additional methods:
maxPlayerHP(), maxPlayerHP(Character me), minPlayerHP(Character me), totalTeamHP(), strongestPlayer()

## Part 2 ##	
- I have implemented the Student, AIStudents, CSStudents, CyberStudents and SEStudents classes, following the coursework specification
- Additional methods in Student Class: getKP(), MaxKPReached(), strike(), specialMove()
- Additional methods in AIStudent Class: specialMove()
- Additional methods in CSStudent Class: specialMove()
- Additional methods in CyberStudent Class: specialMove()
- Additional methods in SEStudent Class: specialMove()
* These methods are used by the students to select a special move

## Part 3 ##
- I have implemented the Monster interface, Minion and Boss Classes following the coursework specification
- I have added an additional enumeration class Attacks to enumerate the possible attacks
- In the Minion and Boss classes I added the chooseAttack() method

## Part 4 ##
- I have implemented the StudentTeam, MonsterTeam, and Guild Classes following the coursework specification

* The Guild: Selecting Students with most KP first and then complete the team with the rest

The strategy for the different students is as folllows.
* Student: If students has reached the maxKP then use a special attack otherways, perform either javaProgramming skill or selfStudy on a 70:30 proportion
* AIStudent: AI students only heal themselves if they are the weaker players of the team using the naturalLanguageProcessing method. Otherways, they attack using their machine learning skills
* CSStudent: CSStudents perform one of their normal attacks at random if they can't perform any special move when no friends are still alive. Otherways chose a special attack with 50:50 proportion to the strongest player of the enemy team
* SEStudent: SES students see the general image of the game: if the students' team has less HP that the enemies, they heal themselves. Otherways, they combine their powers to take down the strongest enemy
* CyberStudent: They have only one special attack to use

## Part 5 ##
- I have implemented the Battle Class following th coursework specification
- I also added the sorting() method which sorts the players of each round based on their attack speed.

## Part 6 ##
- I have implemented the TowerOfMonster Class following the coursework specification.
- I used a parser() method to parse the input from the given file and to return a MonsterTeam with the monsters of each tower of monsters.
- I also created 2 teams (one StudentsTeam and one MonstersTeam)

# Extension #

To run the program with the extension, please use the following command
java TowerOfMonsters [input_file] [level(1-3)]
