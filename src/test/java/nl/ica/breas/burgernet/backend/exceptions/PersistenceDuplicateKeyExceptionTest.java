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
public class PersistenceDuplicateKeyExceptionTest {

	/**
	 * In deze test wordt getest of er succesvol een object van deze klasse kan worden aangemaakt.
	 */
	@Test
	public void testConstructor() {
		PersistenceDuplicateKeyException exception = new PersistenceDuplicateKeyException();
		if (!(exception instanceof PersistenceDuplicateKeyException)) {
			fail("cant initiate exception");
		}
	}
	
	/**
	 * In deze test wordt getest of er succesvol een object van deze klasse kan worden aangemaakt.
	 */
	@Test
	public void testStringConstructor() {
		final String message = "Er is een fout";
		PersistenceDuplicateKeyException exception = new PersistenceDuplicateKeyException(message);
		assertEquals(message, exception.getMessage());
	}
	
	/**
	 * In deze test wordt getest of er succesvol een object van deze klasse kan worden aangemaakt.
	 */
	@Test
	public void testStringEnThrowableConstructor() {
		Throwable throwable = new Throwable();
		PersistenceDuplicateKeyException exception = new PersistenceDuplicateKeyException(throwable);
		assertEquals(throwable, exception.getCause());
	}
}
