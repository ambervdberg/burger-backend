package nl.ica.breas.burgernet.backend.rest;

import static org.junit.Assert.assertEquals;
import nl.ica.breas.burgernet.backend.model.AbstractCategorie;
import nl.ica.breas.burgernet.backend.model.HerhalendeCategorie;
import org.junit.Test;

/**
 * 
 * @author Rick
 *
 */
public class MeldingConfigurationTest {

	private BurgernetConfiguration mc = new BurgernetConfiguration();
	/**
	 * Hier wordt de constructor getest
	 */
	@Test
	public void test() {
		BurgernetConfiguration mc = new BurgernetConfiguration();
		assertEquals("BurgernetConfiguration", mc.getClass().getSimpleName());
	}
	/**
	 * Hier worden de getter en setter getest.
	 */
	@Test
	public void testGetSetCategorieLijst() {
		AbstractCategorie[] categorieLijst = new AbstractCategorie[1];
		categorieLijst[0] = new HerhalendeCategorie("test");
		mc.setCategorieLijst(categorieLijst);
		assertEquals("test", mc.getCategorieLijst()[0].getNaam());
	}
	@Test
	public void testGetSetDatabaseAdapter() {
		String databaseAdapter = "nl.ica.breas.burgernet.backend.persistence.MongoDb2";
		mc.setDatabaseAdapter(databaseAdapter);
		assertEquals("nl.ica.breas.burgernet.backend.persistence.MongoDb2", mc.getDatabaseAdapter());
	}
	@Test
	public void testGetSetFotoController() {
		String insert = "nl.ica.breas.burgernet.backend.controller.FotoController2";
		mc.setFotoController(insert);
		assertEquals("nl.ica.breas.burgernet.backend.controller.FotoController2", mc.getFotoController());
	}
	@Test
	public void testGetSetHaalMeldingOpController() {
		String insert = "nl.ica.breas.burgernet.backend.controller.HaalMeldingOpController2";
		mc.setHaalMeldingOpController(insert);
		assertEquals("nl.ica.breas.burgernet.backend.controller.HaalMeldingOpController2", mc.getHaalMeldingOpController());
	}
	@Test
	public void testGetSetMeldingController() {
		String insert = "nl.ica.breas.burgernet.backend.controller.MeldingController2";
		mc.setMeldingController(insert);
		assertEquals("nl.ica.breas.burgernet.backend.controller.MeldingController2", mc.getMeldingController());
	}
	@Test
	public void testGetSetPushServer() {
		String insert = "nl.ica.breas.burgernet.backend.push.PushServer2";
		mc.setPushServer(insert);
		assertEquals("nl.ica.breas.burgernet.backend.push.PushServer2", mc.getPushServer());
	}
}
