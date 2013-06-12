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
public class PersistenceInternalExceptionTest {

	/**
	 * In deze test wordt getest of er succesvol een object van deze klasse kan worden aangemaakt.
	 */
	@Test
	public void testConstructor() {
		PersistenceInternalException exception = new PersistenceInternalException();
		if (!(exception instanceof PersistenceInternalException)) {
			fail("cant initiate exception");
		}
	}
	
	/**
	 * In deze test wordt getest of er succesvol een object van deze klasse kan worden aangemaakt.
	 */
	@Test
	public void testStringConstructor() {
		final String message = "Er is een fout";
		PersistenceInternalException exception = new PersistenceInternalException(message);
		assertEquals(message, exception.getMessage());
	}
	
	/**
	 * In deze test wordt getest of er succesvol een object van deze klasse kan worden aangemaakt.
	 */
	@Test
	public void testThrowableConstructor() {
		Throwable throwable = new Throwable();
		PersistenceInternalException exception = new PersistenceInternalException(throwable);
		assertEquals(throwable, exception.getCause());
	}
}
