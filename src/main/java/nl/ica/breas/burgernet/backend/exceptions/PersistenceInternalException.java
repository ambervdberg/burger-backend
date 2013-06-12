/**
 * 
 */
package nl.ica.breas.burgernet.backend.exceptions;

/**
 * @author Samuel van Oostveen
 *
 */
@SuppressWarnings("serial")
public class PersistenceInternalException extends Exception {

	/**
	 * Geen connectie met de database exceptie.
	 */
	public PersistenceInternalException() {
		super();
	}
	

	/**
	 * Geen connectie met de database exceptie.
	 * 
	 * @param throwable de oorzaak van de exceptie
	 */
	public PersistenceInternalException(Throwable throwable) {
		super(throwable);
	}
	
	/**
	 * Geen connectie met de database exceptie.
	 * 
	 * @param message het meegegeven bericht
	 */
	public PersistenceInternalException(String message) {
		super(message);
	}
}
