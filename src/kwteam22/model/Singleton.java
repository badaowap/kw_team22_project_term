package kwteam22.model;

public class Singleton extends Customer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Singleton instance;
	
	private Singleton() {
	}
	
	public static Singleton getInstance() {
		if(instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}
