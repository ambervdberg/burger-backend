/**
 * 
 */
package nl.ica.breas.burgernet.backend.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * De klasse ContollerFactoryTest.
 *
 * @author Amber
 * @version 0.1
 * @since 14/12/2012
 */
public class ContollerFactoryTest {
	
	/** De controller factory. */
	private static ControllerFactory controllerFactory= ControllerFactory.getInstantie();
	

	/**
	 * Test of er een instantie van de controller factory wordt aangemaakt.
	 */
	@Test
	public void testGetInstantie() {
		assertEquals(true, ControllerFactory.getInstantie() instanceof ControllerFactory);
	}

	/**
	 * Test of er een melding controller wordt aangemaakt.
	 */
	@Test
	public void testCreateMeldingController() {
		controllerFactory.setsMeldingController("nl.ica.breas.burgernet.backend.controller.MeldingController");
		assertEquals(true, ControllerFactory.getInstantie().createMeldingController() instanceof MeldingController);
	}

	/**
	 * Test de ClassNotFoundException.
	 */
	@Test
	public void testClassNotFoundException() {
		controllerFactory.setsMeldingController("nl.ica.breas.burgernet.backend.controller.MeldingControllerbla");
		assertEquals(null, ControllerFactory.getInstantie().createMeldingController());
	}
	
	/**
	 * Test de IllegalAccessException.
	 */
	@Test
	public void testIllegalAccessException() {
		controllerFactory.setsMeldingController("nl.ica.breas.burgernet.backend.model.Categorie");
		assertEquals(null, ControllerFactory.getInstantie().createMeldingController());
	}
	
	/**
	 * Test de InstantiationException.
	 */
	@Test
	public void testInstantiationException() {
		controllerFactory.setsMeldingController("nl.ica.breas.burgernet.backend.model.IMelding");
		assertEquals(null, ControllerFactory.getInstantie().createMeldingController());
	}
	
	/**
	 * Test of er een foto controller wordt aangemaakt.
	 */
	@Test
	public void testCreateFotoController() {
		controllerFactory.setsFotoController("nl.ica.breas.burgernet.backend.controller.FotoController");
		assertEquals(true, ControllerFactory.getInstantie().createFotoController() instanceof FotoController);
	}

	/**
	 * Test of er een melding op haal controller wordt aangemaakt.
	 */
	@Test
	public void testCreateOphaalController() {
		controllerFactory.setsHaalMeldingOpController("nl.ica.breas.burgernet.backend.controller.HaalMeldingOpController");
		assertEquals(true, ControllerFactory.getInstantie().createOphaalController() instanceof HaalMeldingOpController);
	}
}
