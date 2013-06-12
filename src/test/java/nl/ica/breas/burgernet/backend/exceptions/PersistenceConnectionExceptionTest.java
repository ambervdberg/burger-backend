/**
 * 
 */
package nl.ica.breas.burgernet.backend.exceptions;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author samuel
 *
 */
public class PersistenceConnectionExceptionTest {

	/**
	 * In deze test wordt getest of er succesvol een object van deze klasse kan worden aangemaakt.
	 */
	@Test
	public void testConstructor() {
		PersistenceConnectionException exception = new PersistenceConnectionException();
		if (!(exception instanceof PersistenceConnectionException)) {
			fail("cant initiate exception");
		}
	}
	
	/**
	 * In deze test wordt getest of er succesvol een object van deze klasse kan worden aangemaakt.
	 */
	@Test
	public void testStringConstructor() {
		final String message = "Er is een fout";
		PersistenceConnectionException exception = new PersistenceConnectionException(message);
		assertEquals(message, exception.getMessage());
	}
	
	/**
	 * In deze test wordt getest of er succesvol een object van deze klasse kan worden aangemaakt.
	 */
	@Test
	public void testThrowableConstructor() {
		Throwable throwable = new Throwable();
		PersistenceConnectionException exception = new PersistenceConnectionException(throwable);
		assertEquals(throwable, exception.getCause());
	}
}
