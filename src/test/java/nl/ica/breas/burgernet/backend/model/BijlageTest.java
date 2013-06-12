/**
 * 
 */
package nl.ica.breas.burgernet.backend.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 * @author Samuel van Oostveen
 *
 */
public class BijlageTest {

	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.model.Bijlage#Bijlage()}.
	 */
	@Test
	public void testBijlage() {
		Bijlage bijlage = new Bijlage();
		if (!(bijlage instanceof Bijlage)) {
			fail("Niet de juiste klasse");
		}
	}

	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.model.Bijlage#Bijlage(java.lang.String)}.
	 */
	@Test
	public void testBijlageString() {
		Bijlage bijlage = new Bijlage("test", ".jpeg");
		if (!(bijlage instanceof Bijlage)) {
			fail("Niet de juiste klasse");
		}
		assertEquals("test", bijlage.getInhoud());
	}

	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.model.Bijlage#getInhoud()}.
	 */
	@Test
	public void testGetBijlage() {
		Bijlage bijlage = new Bijlage("test", ".jpeg");
		String bijlageString = "testje";
		bijlage.setInhoud(bijlageString);
		assertEquals(bijlageString, bijlage.getInhoud());
	}
	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.model.Bijlage#getContentType()}.
	 */
	@Test
	public void testGetContentType() {
		Bijlage bijlage = new Bijlage("test", ".jpeg");
		String bijlageString = "testje";
		bijlage.setContentType(bijlageString);
		assertEquals(bijlageString, bijlage.getContentType());
	}

}
