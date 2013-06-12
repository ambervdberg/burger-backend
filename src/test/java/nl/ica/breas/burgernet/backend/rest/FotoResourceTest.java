/**
 * 
 */
package nl.ica.breas.burgernet.backend.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import nl.ica.breas.burgernet.backend.controller.IFotoController;
import nl.ica.breas.burgernet.backend.hulpobjecten.FotoVerwerkContainer;
import nl.ica.breas.burgernet.backend.hulpobjecten.Status;
import org.junit.Test;

/**
 * @author Amber
 * @version 0.1
 * @since 11/12/2012
 */
public class FotoResourceTest {
	private IFotoController ifc = mock(IFotoController.class);
	private FotoResource fr = new FotoResource(ifc);
	private FotoVerwerkContainer fvc = new FotoVerwerkContainer();

	/**
	 * Test methode voor de constructor van FotoResource.
	 */
	@Test
	public void testFotoResource() {
		assertEquals(true, fr instanceof FotoResource);
	}

	/**
	 * Test methode voor VoegFotoToe met een ongeldige Base64.
	 * Wanneer er een ongeldige Base64 wordt meegegeven, geeft de bevestiging false terug.
	 */
	@Test
	public void testVoegFotoToe() {
		fvc.setFotoBase64("bl a");
		assertEquals(false, fr.voegFotoToe(fvc).isBevestiging());
	}

	/**
	 * Test methode voor VoegFotoToe met een geldige Base64.
	 * Wanneer er een geldige Base64 wordt meegegeven, geeft de bevestiging true terug.
	 */
	@Test
	public void testVoegFotoToe2() {
		when(ifc.verwerkFoto(any(FotoVerwerkContainer.class))).thenReturn(new Status(true, null));
		fvc.setFotoBase64("bla");
		assertEquals(true, fr.voegFotoToe(fvc).isBevestiging());
	}
}