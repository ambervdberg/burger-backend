/**
 * 
 */
package nl.ica.breas.burgernet.backend.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.IOException;
import java.util.List;

import nl.ica.breas.burgernet.backend.exceptions.PersistenceInternalException;
import nl.ica.breas.burgernet.backend.model.Burger;
import nl.ica.breas.burgernet.backend.model.Melding;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoInternalException;

import static org.mockito.Mockito.*;

/**
 * @author Amber
 * @version 0.1
 * @since 11/12/2012
 */

public class MongoDBObjectOmzetterTest {
	private MongoDBObjectOmzetter moo = MongoDBObjectOmzetter.getInstance();

	/**
	 * Test methode om een Object naar een DBObject om te zetten.
	 * Wanneer het Object null is wordt er een null teruggeven.
	 * @throws IOException 
	 */
	@Test
	public void testObjectNaarDBObject() throws IOException {
		Object nullObject = null;
		assertEquals(null, moo.objectNaarDBObject(nullObject));
	}

	/**
	 * Test methode om een Object naar een DBObject om te zetten.
	 * Wanneer het Object niet null is wordt er een DBObject teruggeven.
	 * @throws IOException 
	 */
	@Test
	public void testObjectNaarDBObject2() throws IOException {
		Melding object = new Melding();
		assertEquals(true, moo.objectNaarDBObject(object) instanceof DBObject);
	}

	/**
	 * Test methode get instance. 
	 * Hier wordt er een instantie aangemaakt van MongoDBObjectOmzetter, 
	 * omdat hier nog geen instantie van bestaat.
	 */
	@Test
	public void testGetInstance() {
		assertEquals(true, MongoDBObjectOmzetter.getInstance() instanceof MongoDBObjectOmzetter);
	}

	/**
	 * Test methode get instance. 
	 * Hier wordt er geen instantie aangemaakt van MongoDBObjectOmzetter, 
	 * omdat hier al wel een instantie van bestaat.
	 */
	@Test
	public void testGetInstance2() {
		MongoDBObjectOmzetter instantie = MongoDBObjectOmzetter.getInstance();
		MongoDBObjectOmzetter instantie2 = MongoDBObjectOmzetter.getInstance();
		assertSame(instantie, instantie2);
	}

	/**
	 * Test methode om een DBObject naar een Object om te zetten.
	 * Wanneer het DBObject null is wordt er een null teruggeven.
	 * @throws IOException 
	 */
	@Test
	public void testDBObjectNaarObject() throws IOException {
		DBObject nullDBObject = null;
		assertEquals(null, moo.dBObjectNaarObject(nullDBObject, Object.class));
	}

	/**
	 * Test methode om een DBObject naar een Object om te zetten.
	 * Wanneer het DBObject niet Null is wordt er een Object teruggeven.
	 * @throws IOException 
	 */
	@Test
	public void testDBObjectNaarObject2() throws IOException {
		DBObject nullDBObject = new BasicDBObject();
		assertEquals(true, moo.dBObjectNaarObject(nullDBObject, Object.class) instanceof Object);
	}

	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.persistence.MongoDBObjectOmzetter#cursorNaarBurgerList(com.mongodb.DBCursor)}.
	 * @throws IOException 
	 * @throws PersistenceInternalException 
	 */
	@Test
	public void testCursorNaarBurgerList() throws PersistenceInternalException, IOException {
		//DBCollection collectie = null;
		DBCursor cursor = (mock(DBCursor.class));
		when(cursor.hasNext()).thenReturn(false);
		//when(cursor.slaMeldingOp(any(Melding.class))).thenThrow(new PersistenceDuplicateKeyException("Test2"));		
		assertEquals(true, moo.cursorNaarList(cursor, Burger.class) instanceof List);
		assertEquals(0, moo.cursorNaarList(cursor, Burger.class).size());
	}
	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.persistence.MongoDBObjectOmzetter#cursorNaarBurgerList(com.mongodb.DBCursor)}.
	 * @throws IOException 
	 * @throws PersistenceInternalException 
	 */
	@Test (expected=PersistenceInternalException.class)
	public void testCursorNaarBurgerListInternalException() throws PersistenceInternalException, IOException {
		//DBCollection collectie = null;
		DBCursor cursor = (mock(DBCursor.class));
		when(cursor.hasNext()).thenReturn(true);
		when(cursor.next()).thenThrow(new MongoInternalException("Test2"));
		moo.cursorNaarList(cursor, Burger.class);
	}
}
