import java.util.Random;

public class Minion extends Character implements Monster {

	//Creating the constructor
	public Minion(String name) {
		super(name,5,5,5,5);
	}

	//A method for deciding what attack the minion will perform based on a Pseudo random generator and the given probabilities
	//75% SyntaxError, 15% NullPointerException, and 10% ArrayIndexOutOfBoundException
	public Attacks chooseAttack(Integer difficulty) {

		//Creating a random integer between 0 and 100 - the probability
		Random r = new Random();
		int n = r.nextInt(100);

		Attacks a;

		if(difficulty == 1 && n < 80 || n < 75) {
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
	public void strike(Character enemy, Integer difficulty) throws Exception {
		

		//If the enemy is already dead, don't do nothing
		if(enemy.getHP() == 0) {
			throw new Exception("Enemy already dead - you just lost a move");
		}

		//Choosing which attack the minion will use
		Attacks a = chooseAttack(difficulty);
		int damage = 0;

		//Checking the type of attack using a switch statement
		switch(a) {		
			
			case SyntaxError:				
				//typical attack on enemies
				
				System.out.println(" to attack " + enemy.getName());
				damage = (100 * this.getDefence() / (100 + enemy.getDefence()));
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
				this.increaseHP(this.getDefence());

				System.out.println(" to heal itself");
				
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