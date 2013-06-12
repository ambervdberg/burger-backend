/**
 * 
 */
package nl.ica.breas.burgernet.backend.exceptions;

/**
 * @author Samuel van Oostveen
 *
 */
@SuppressWarnings("serial")
public class PersistenceConnectionException extends Exception {

	/**
	 * Geen connectie met de database exceptie.
	 */
	public PersistenceConnectionException() {
		super();
	}
	

	/**
	 * Geen connectie met de database exceptie.
	 * 
	 * @param throwable de oorzaak van de exceptie
	 */
	public PersistenceConnectionException(Throwable throwable) {
		super(throwable);
	}
	
	/**
	 * Geen connectie met de database exceptie.
	 * 
	 * @param message het meegegeven bericht
	 */
	public PersistenceConnectionException(String message) {
		super(message);
	}
}
