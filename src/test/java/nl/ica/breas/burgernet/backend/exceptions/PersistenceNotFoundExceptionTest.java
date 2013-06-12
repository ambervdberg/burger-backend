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
public class PersistenceNotFoundExceptionTest {

	/**
	 * In deze test wordt getest of er succesvol een object van deze klasse kan worden aangemaakt.
	 */
	@Test
	public void testConstructor() {
		PersistenceNotFoundException exception = new PersistenceNotFoundException();
		if (!(exception instanceof PersistenceNotFoundException)) {
			fail("cant initiate exception");
		}
	}
	
	/**
	 * In deze test wordt getest of er succesvol een object van deze klasse kan worden aangemaakt.
	 */
	@Test
	public void testStringConstructor() {
		final String message = "Er is een fout";
		PersistenceNotFoundException exception = new PersistenceNotFoundException(message);
		assertEquals(message, exception.getMessage());
	}
	
	/**
	 * In deze test wordt getest of er succesvol een object van deze klasse kan worden aangemaakt.
	 */
	@Test
	public void testStringEnThrowableConstructor() {
		Throwable throwable = new Throwable();
		PersistenceNotFoundException exception = new PersistenceNotFoundException(throwable);
		assertEquals(throwable, exception.getCause());
	}
}
