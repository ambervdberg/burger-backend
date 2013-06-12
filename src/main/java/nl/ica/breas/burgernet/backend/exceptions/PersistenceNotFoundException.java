/**
 * 
 */
package nl.ica.breas.burgernet.backend.exceptions;

/**
 * @author Samuel van Oostveen
 *
 */
@SuppressWarnings("serial")
public class PersistenceNotFoundException extends Exception {

	/**
	 * De Persistence CursorNotFoundException.
	 */
	public PersistenceNotFoundException() {
		super();
	}
	
	/**
	 * De Persistence CursorNotFoundException.
	 * 
	 * @param throwable de oorzaak van de exceptie
	 */
	public PersistenceNotFoundException(Throwable throwable) {
		super(throwable);
	}
	
	/**
	 * De Persistence CursorNotFoundException.
	 * 
	 * @param message het meegegeven bericht
	 */
	public PersistenceNotFoundException(String message) {
		super(message);
	}
}
