package nl.ica.breas.burgernet.backend.hulpobjecten;

import static org.junit.Assert.*;

import nl.ica.breas.burgernet.backend.hulpobjecten.Status;

import org.junit.Test;

public class StatusTest {

	/**
	 * Hier wordt getest of de constructor op een juiste wijze wordt gemaakt.
	 */
	@Test
	public void testStatus() {
		Status status = new Status(true,"lfdskj");
		assertEquals(true, status.isBevestiging());
		assertEquals("Status", status.getClass().getSimpleName());
	}
	/**
	 * Hier wordt getest of de juiste waarde wordt gereturned
	 */
	@Test
	public void testIsStatus() {
		Status status = new Status(false,"moi");
		assertEquals(false, status.isBevestiging());
		assertEquals("moi", status.getId());
	}

}
