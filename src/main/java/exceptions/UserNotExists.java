package exceptions;

public class UserNotExists extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserNotExists() {
		super();
	}
	public UserNotExists(String s) {
		super(s);
	}
}
