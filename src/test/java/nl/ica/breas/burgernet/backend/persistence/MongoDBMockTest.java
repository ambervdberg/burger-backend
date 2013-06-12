/**
 * 
 */
package nl.ica.breas.burgernet.backend.persistence;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import nl.ica.breas.burgernet.backend.model.Burger;
import nl.ica.breas.burgernet.backend.model.Melding;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author samuel
 *
 */
public class MongoDBMockTest {
	static MongoDB mongodb;
	static Melding melding;
	static Burger burger;

	/** Deze wordt gebruikt om de erros te loggen. */
	private static final Logger LOGGER = Logger.getLogger("InfoLogging");
	/**
	 * 
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		try {
		    AbstractPersistenceAdapter.setIpadres("149.5.47.170");
		    AbstractPersistenceAdapter.setPoort(27017);
		    AbstractPersistenceAdapter.setDatabaseNaam("burgernet");
	        AbstractPersistenceAdapter.setDatabaseInlogWachtwoord("breas2012");
			mongodb = new MongoDB();
		} catch (UnknownHostException e) {
			LOGGER.trace(e);
		}
		melding = new Melding();
		burger = new Burger();
	}
	
	/**
	 * Met deze test word de constructor van MongoDB getest.
	 */
	@Test
	public void testConstructor() {
		assertEquals(true, mongodb instanceof AbstractPersistenceAdapter);
	}
	/*
	@Test
	public void testSlaMeldingOp() {
		try {
			DBCollection meldingCollectie = mock(DBCollection.class);
			//when(meldingCollectie.save(any(DBObject.class)));
			//when(adapter.slaMeldingOp(any(Melding.class))).thenReturn(true);
			mongodb.setMeldingCollectie(meldingCollectie);
			assertEquals(true, mongodb.slaMeldingOp(melding));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	*/
	/*
	@Test
	public void testSlaBurgerOp() {
		try {
			DBCollection burgerCollectie = mock(DBCollection.class);
			//when(burgerCollectie.save(any(DBObject.class))).;
			//when(adapter.slaMeldingOp(any(Melding.class))).thenReturn(true);
			mongodb.setBurgerCollectie(burgerCollectie);
			assertEquals(true, mongodb.slaBurgerOp(burger));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	/*
	 * 
	 
	@Test
	public void testSelecteerBurgers() {
		try {
			DBCollection burgerCollectie = mock(DBCollection.class);
			//when(meldingCollectie.save(any(DBObject.class)));
			when(burgerCollectie.find(any(DBObject.class))).thenReturn(new DBCursor(null, null, null));
			mongodb.setBurgerCollectie(burgerCollectie);
			assertEquals(true, mongodb.selecteerBurgers(melding));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
}
