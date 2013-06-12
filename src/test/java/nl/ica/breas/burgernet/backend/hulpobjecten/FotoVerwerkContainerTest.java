/**
 * 
 */
package nl.ica.breas.burgernet.backend.hulpobjecten;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Samuel van Oostveen
 *
 */
public class FotoVerwerkContainerTest {

	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.hulpobjecten.FotoVerwerkContainer#FotoVerwerkContainer()}.
	 */
	@Test
	public void testFotoVerwerkContainer() {
		FotoVerwerkContainer fotoVerwerkContainer = new FotoVerwerkContainer();
		if (!(fotoVerwerkContainer instanceof FotoVerwerkContainer)) {
			fail("niet de juiste klasse");
		}
	}

	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.hulpobjecten.FotoVerwerkContainer#getFotoBase64()}.
	 */
	@Test
	public void testSetGetFotoBase64() {
		FotoVerwerkContainer fotoVerwerkContainer = new FotoVerwerkContainer();
		String base64String = "erjerljfjrejf";
		fotoVerwerkContainer.setFotoBase64(base64String);
		assertEquals(base64String, fotoVerwerkContainer.getFotoBase64());
	}

	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.hulpobjecten.FotoVerwerkContainer#getMeldingID()}.
	 */
	@Test
	public void testSetGetMeldingID() {
		FotoVerwerkContainer fotoVerwerkContainer = new FotoVerwerkContainer();
		String meldingID = "ewfr3r34rj32jr23";
		fotoVerwerkContainer.setMeldingID(meldingID);
		assertEquals(meldingID, fotoVerwerkContainer.getMeldingID());
	}
	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.hulpobjecten.FotoVerwerkContainer#getContentType()}.
	 */
	@Test
	public void testSetGetContentType() {
		FotoVerwerkContainer fotoVerwerkContainer = new FotoVerwerkContainer();
		String meldingID = "ewfr3r34rj32jr23";
		fotoVerwerkContainer.setContentType(meldingID);
		assertEquals(meldingID, fotoVerwerkContainer.getContentType());
	}
}
