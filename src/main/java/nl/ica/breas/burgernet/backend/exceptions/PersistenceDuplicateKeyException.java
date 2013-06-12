/**
 * 
 */
package nl.ica.breas.burgernet.backend.exceptions;

/**
 * @author Samuel van Oostveen
 *
 */
@SuppressWarnings("serial")
public class PersistenceDuplicateKeyException extends Exception {
	
	/**
	 * Er bestaat al een item met dit id in de database.
	 */
	public PersistenceDuplicateKeyException() {
		super();
	}
	
	/**
	 * Er bestaat al een item met dit id in de database.
	 * 
	 * @param throwable de oorzaak van de exceptie
	 */
	public PersistenceDuplicateKeyException(Throwable throwable) {
		super(throwable);
	}
	
	/**
	 * Er bestaat al een item met dit id in de database.
	 * 
	 * @param message het meegegeven bericht
	 */
	public PersistenceDuplicateKeyException(String message) {
		super(message);
	}
}
