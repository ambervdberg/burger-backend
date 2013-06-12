/**
 * 
 */
package nl.ica.breas.burgernet.backend.persistence;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Samuel van Oostveen
 *
 */
public class PersistenceFactoryTest {
	
	private final String mongoDB = "nl.ica.breas.burgernet.backend.persistence.MongoDB";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.persistence.PersistenceFactory#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		PersistenceFactory persistenceFactory = PersistenceFactory.getInstance();
		assertEquals(true, persistenceFactory instanceof PersistenceFactory);
		assertEquals(persistenceFactory, PersistenceFactory.getInstance());
	}

	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.persistence.PersistenceFactory#createDatabaseAdapter()}.
	 */
	@Test
	public void testCreateDatabaseAdapter() {
		PersistenceFactory.setDbAdapter(mongoDB);
		AbstractPersistenceAdapter.setIpadres("149.5.47.170");
		AbstractPersistenceAdapter.setPoort(27017);
		AbstractPersistenceAdapter.setDatabaseNaam("burgernet");
		AbstractPersistenceAdapter.setDatabaseInlogWachtwoord("breas2012");
		AbstractPersistenceAdapter persistenceAdapter = PersistenceFactory.getInstance().createDatabaseAdapter();
		assertEquals(true, persistenceAdapter instanceof AbstractPersistenceAdapter);
	}
	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.persistence.PersistenceFactory#createDatabaseAdapter()}.
	 */
	@Test
	public void testCreateDatabaseAdapterClassNotFoundException() {
		PersistenceFactory.setDbAdapter(mongoDB + "bla");
		AbstractPersistenceAdapter persistenceAdapter = PersistenceFactory.getInstance().createDatabaseAdapter();
		assertEquals(null, persistenceAdapter);
	}
	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.persistence.PersistenceFactory#createDatabaseAdapter()}.
	 */
	@Test
	public void testCreateDatabaseAdapterIllegalAccessException() {
		PersistenceFactory.setDbAdapter("nl.ica.breas.burgernet.backend.model.Categorie");
		AbstractPersistenceAdapter persistenceAdapter = PersistenceFactory.getInstance().createDatabaseAdapter();
		assertEquals(null, persistenceAdapter);
	}
	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.persistence.PersistenceFactory#createDatabaseAdapter()}.
	 */
	@Test
	public void testCreateDatabaseAdapterInstantiationException() {
		PersistenceFactory.setDbAdapter("nl.ica.breas.burgernet.backend.model.IMelding");
		AbstractPersistenceAdapter persistenceAdapter = PersistenceFactory.getInstance().createDatabaseAdapter();
		assertEquals(null, persistenceAdapter);
	}
	
	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.persistence.PersistenceFactory#getDbAdapter()}.
	 */
	@Test
	public void testSetGetDbAdapter() {
		PersistenceFactory.setDbAdapter(mongoDB);
		assertEquals(mongoDB, PersistenceFactory.getDbAdapter());
	}
}
