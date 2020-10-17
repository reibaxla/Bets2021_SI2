package exceptions;

public class ApustuaAlreadyExist extends Exception {
	private static final long serialVersionUID = 1L;
	 
	 public ApustuaAlreadyExist()
	  {
	    super();
	  }
	  /**This exception is triggered if the question already exists 
	  *@param s String of the exception
	  */
	  public ApustuaAlreadyExist(String s)
	  {
	    super(s);
	  }
	}