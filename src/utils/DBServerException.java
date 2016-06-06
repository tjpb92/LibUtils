package utils;

/**
 * Class that defines an Exception to throws during the instantiation of a
 * DBServer object when a fatal error is encountered.
 * @author Thierry Baribaud.
 * @version 1.0
 */
public class DBServerException extends Exception {

	private final static String ERRMSG = 
		"Problem during instantiation of a DBServer object";

	public DBServerException() {
		System.out.println(ERRMSG);
	}

	public DBServerException(String ErrMsg) {
		System.out.println(ERRMSG + " : " + ErrMsg);
	}
}
