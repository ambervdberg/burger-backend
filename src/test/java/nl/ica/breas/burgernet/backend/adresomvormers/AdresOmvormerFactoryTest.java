/**
 * 
 */
package nl.ica.breas.burgernet.backend.adresomvormers;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Samuel van Oostveen
 *
 */
public class AdresOmvormerFactoryTest {
	
	private final String adresOmvormer = "nl.ica.breas.burgernet.backend.adresomvormers.GoogleAdresNaarLocatie";

    /**
     * Test method for {@link nl.ica.breas.burgernet.backend.adresomvormers.PersistenceFactory.
     */
	@Test
	public void testGetInstance() {
	    AdresOmvormerFactory adresOmvormerFactory = AdresOmvormerFactory.getInstance();
		assertEquals(true, adresOmvormerFactory instanceof AdresOmvormerFactory);
		assertEquals(adresOmvormerFactory, AdresOmvormerFactory.getInstance());
	}

	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.adresomvormers.PersistenceFactory.
	 */
	@Test
	public void testCreateAdresAdapter() {
	    AdresOmvormerFactory.setAdresNaarLocatieAdapter(adresOmvormer);
	    AbstractAdresNaarLocatieAdapter adresNaarLocatieAdapter = AdresOmvormerFactory.getInstance().createAdresNaarLocatieAdapter();
		assertEquals(true, adresNaarLocatieAdapter instanceof AbstractAdresNaarLocatieAdapter);
	}
    /**
     * Test method for {@link nl.ica.breas.burgernet.backend.adresomvormers.PersistenceFactory.
     */
	@Test
	public void testCreateAdresNaarLocatieAdapterClassNotFoundException() {
	    AdresOmvormerFactory.setAdresNaarLocatieAdapter(adresOmvormer + "bla");
	    AbstractAdresNaarLocatieAdapter adresNaarLocatieAdapter = AdresOmvormerFactory.getInstance().createAdresNaarLocatieAdapter();
		assertEquals(null, adresNaarLocatieAdapter);
	}
    /**
     * Test method for {@link nl.ica.breas.burgernet.backend.adresomvormers.PersistenceFactory.
     */
	@Test
	public void testCreateAdresNaarLocatieAdapterIllegalAccessException() {
	    AdresOmvormerFactory.setAdresNaarLocatieAdapter("nl.ica.breas.burgernet.backend.model.Categorie");
	    AbstractAdresNaarLocatieAdapter adresNaarLocatieAdapter = AdresOmvormerFactory.getInstance().createAdresNaarLocatieAdapter();
		assertEquals(null, adresNaarLocatieAdapter);
	}
    /**
     * Test method for {@link nl.ica.breas.burgernet.backend.adresomvormers.PersistenceFactory.
     */
	@Test
	public void testCreateAdresNaarLocatieAdapterInstantiationException() {
	    AdresOmvormerFactory.setAdresNaarLocatieAdapter("nl.ica.breas.burgernet.backend.model.IMelding");
	    AbstractAdresNaarLocatieAdapter adresNaarLocatieAdapter = AdresOmvormerFactory.getInstance().createAdresNaarLocatieAdapter();
		assertEquals(null, adresNaarLocatieAdapter);
	}
	
    /**
     * Test method for {@link nl.ica.breas.burgernet.backend.adresomvormers.PersistenceFactory.
     */
	@Test
	public void testSetGetAdresNaarLocatieAdapter() {
	    AdresOmvormerFactory.setAdresNaarLocatieAdapter(adresOmvormer);
		assertEquals(adresOmvormer, AdresOmvormerFactory.getAdresNaarLocatieAdapter());
	}
}
