import java.util.Random;

public class Boss extends Character implements Monster {
	
	//Creating the constructor
	public Boss(String name) {
		super(name,8,7,8,7);
	}

	//A method for deciding what attack the minion will perform based on a Pseudo random generator and the given probabilities
	//  50%SyntaxError, 15% NullPointerException, 15% ArrayIndexOutOfBoundException,
	//  10% NoneTermination, 10% ConcurrentModificationException
	public Attacks chooseAttack() {

		//Creating a random integer between 0 and 100 - the probability
		Random r = new Random();
		int n = r.nextInt(100);

		Attacks a;

		if(n < 50) {
			a = Attacks.SyntaxError;
		}
		else if(n < 65) {
			a = Attacks.NullPointerException;
		}
		else if(n < 80) {
			a = Attacks.ArrayIndexOutOfBoundException;
		}
		else if(n < 90) {
			a = Attacks.NoneTermination;
		}
		else {
			a = Attacks.ConcurrentModificationException;
		}
		System.out.print(" uses special attack " + a.toString());
		return a;
	}

	//Providing an implementation for the strike method declared by the Monster Interface
	public void strike(Character enemy) throws Exception {
		
		//If the enemy is already dead, don't do nothing
		if(enemy.getHP() == 0) {
			throw new Exception("Enemy already dead - you just lost a move");
		}

		//Choosing which attack the minion will use
		Attacks a = chooseAttack();
		int damage = 0;

		//Checking the type of attack using a switch statement
		switch(a) {		
			case SyntaxError:
				//typical attack on enemies
				damage = (100 * this.getDefence() / (100 + enemy.getDefence()));
				enemy.decreaseHP(damage);
				System.out.println(" to attack " + enemy.getName());
				System.out.println("Damage: -" + damage);
				/********************Give aknowledge points********************/
				this.increaseEP(3);
				enemy.increaseEP(3);
				if(enemy instanceof Student) {
					((Student)enemy).increaseKP(3);
				}
				if(enemy.getHP() == 0) {
					this.increaseEP(4);
					System.out.println("Enemy killed +4 EPs");			
				}
				/***************************************************************/
				break;
			
			case NullPointerException:
				//Healing
				System.out.println(" to heal");
				System.out.println("Increasing HP by +" + this.getDefence());
				this.increaseHP(this.getDefence());
				/********************Give aknowledge points********************/
				this.increaseEP(3);
				/***************************************************************/
				break;
			
			case ArrayIndexOutOfBoundException:
				//Double the power
				damage = (2 * 100 * this.getDefence() / (100 + enemy.getDefence()));
				enemy.decreaseHP(damage);
				System.out.println(" to attack " + enemy.getName());
				System.out.println("Damage -" + damage);
				/********************Give aknowledge points********************/
				this.increaseEP(3);
				enemy.increaseEP(3);
				if(enemy instanceof Student) {
					((Student)enemy).increaseKP(3);
				}
				if(enemy.getHP() == 0) {
					this.increaseEP(4);
					System.out.println("Enemy killed +4 EPs");			
				}
				/***************************************************************/
				break;
			
			case NoneTermination:
				//Revive dead allies
				System.out.println(" to revive dead allies");
				
				Character[] team = this.getTeam().getMembers();

				for(int i = 0; i < team.length; i++){
					Character ally = team[i];
					if(ally.getHP() == 0) {
						ally.increaseHP(this.getMaxHP());
						System.out.println("Reviving ally " + ally.getName() + ": +" + this.getMaxHP());
					}
				}
				/********************Give aknowledge points********************/
				this.increaseEP(3);
				/***************************************************************/
				break;

			case ConcurrentModificationException:
				//Attacking alive enemies
				System.out.println("to attack all alive enemies");

				Character[] enemies = enemy.getTeam().getMembers();
			
				for(int i = 0; i < enemies.length; i++){
					Character target = enemies[i];
					if(target.getHP() > 0) {
						target.decreaseHP(this.getAttack());
						System.out.println("Attacking enemy " + enemy.getName() + ": -" + this.getAttack());
						enemy.increaseEP(3);
						if(enemy instanceof Student) {
							((Student)enemy).increaseKP(3);
						}
						if(enemy.getHP() == 0) {
							this.increaseEP(4);
							System.out.println("Enemy killed +4 EPs");			
						}
					}
				}
				/********************Give aknowledge points********************/
				this.increaseEP(3);
				/***************************************************************/
				
				break;
			default:
				throw new Exception("There is no such attack");
		}
	}

}