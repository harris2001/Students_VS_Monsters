enum Attacks{
	SyntaxError,
	NullPointerException,
	ArrayIndexOutOfBoundException,
	NoneTermination,
	ConcurrentModificationException,

}

public abstract interface Monster {
	
	public void strike(Character enemy) throws Exception;

}