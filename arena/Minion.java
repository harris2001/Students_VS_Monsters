import java.util.Random;

public class Minion extends Character implements Monster {

	//Creating the constructor
	public Minion(String name) {
		super(name,5,5,5,5);
	}

	//A method for deciding what attack the minion will perform based on a Pseudo random generator and the given probabilities
	//75% SyntaxError, 15% NullPointerException, and 10% ArrayIndexOutOfBoundException
	public Attacks chooseAttack() {

		//Creating a random integer between 0 and 100 - the probability
		Random r = new Random();
		int n = r.nextInt(100);

		Attacks a;

		if(n < 75) {
			a = Attacks.SyntaxError;
		}
		else if(n < 90) {
			a = Attacks.NullPointerException;
		}
		else {
			a = Attacks.ArrayIndexOutOfBoundException;
		}
		System.out.print(" uses " + a);
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
				System.out.println(" to attack " + enemy.getName());
				enemy.decreaseHP(damage);


				/********************Give aknowledge points********************/
				enemy.increaseEP(3);
				if(enemy instanceof Student) {
					((Student)enemy).increaseKP(3);
				}
				if(enemy.getHP() == 0) {
					this.increaseEP(4);
					System.out.println("An enemy has been killed");			
				}
				this.increaseEP(3);
				/***************************************************************/
				break;
			
			case NullPointerException:				
				//Healing
				System.out.println(" to heal itself");
				System.out.println("Increasing HP by +" + this.getDefence());				

				this.increaseHP(this.getDefence());
				
				/********************Give aknowledge points********************/
				this.increaseEP(3);
				/***************************************************************/
				break;
			
			case ArrayIndexOutOfBoundException:
				//Double the power
				System.out.println(" to attack " + enemy.getName());

				damage = (2 * 100 * this.getDefence() / (100 + enemy.getDefence()));
				enemy.decreaseHP(damage);

				/********************Give aknowledge points********************/
				enemy.increaseEP(3);
				if(enemy instanceof Student) {
					((Student)enemy).increaseKP(3);
				}
				if(enemy.getHP() == 0) {
					this.increaseEP(4);
					System.out.println("An enemy has been killed");			
				}
				this.increaseEP(3);
				/***************************************************************/
				break;
			
			case NoneTermination:
			case ConcurrentModificationException:
				throw new Exception("Only Bosses can perform this type of special attack");
				
			default:
				throw new Exception("There is no such attack");
		}
	}

}