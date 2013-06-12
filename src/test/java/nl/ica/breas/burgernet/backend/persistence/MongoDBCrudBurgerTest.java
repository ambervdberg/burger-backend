package nl.ica.breas.burgernet.backend.persistence;

import java.io.IOException;

import nl.ica.breas.burgernet.backend.exceptions.PersistenceConnectionException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceNotFoundException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class MongoDBCrudBurgerTest {
	private Mongo mongo;
	private DB db;
	
    @Before
    public void setup() throws Exception {
    
		mongo = PowerMockito.mock(Mongo.class);
		db = PowerMockito.mock(DB.class);
		DBCollection dbCollection = PowerMockito.mock(DBCollection.class);
	
		PowerMockito.when(mongo.getDB("Burgernet")).thenReturn(db);
		PowerMockito.when(db.getCollection("Meldingen")).thenReturn(dbCollection);

		AbstractPersistenceAdapter.setDatabaseNaam("burgernet");
        AbstractPersistenceAdapter.setDatabaseInlogWachtwoord("breas2012");
    }
    
    @Test(expected = PersistenceNotFoundException.class)
    public void getBurgerTestFouteId() throws IllegalArgumentException, IOException, PersistenceConnectionException, PersistenceNotFoundException {
        AbstractPersistenceAdapter.setIpadres("149.5.47.170");
        AbstractPersistenceAdapter.setPoort(27017);
        new MongoDB();
        
        MongoDBCrudBurger.getInstance().getBurger("50d07c53aa65773f01500000");
        
    }
}
