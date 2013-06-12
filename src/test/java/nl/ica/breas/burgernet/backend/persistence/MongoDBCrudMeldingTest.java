package nl.ica.breas.burgernet.backend.persistence;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Calendar;

import nl.ica.breas.burgernet.backend.exceptions.PersistenceConnectionException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceDuplicateKeyException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceNotFoundException;
import nl.ica.breas.burgernet.backend.model.HerhalendeCategorie;
import nl.ica.breas.burgernet.backend.model.Locatie;
import nl.ica.breas.burgernet.backend.model.Melding;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class MongoDBCrudMeldingTest {
    private Mongo mongo;
    private DB db;
    /** Deze wordt gebruikt om de erros te loggen. */
    private static final Logger LOGGER = Logger.getLogger("InfoLogging");

    @Before
    public void setup() throws Exception {
        mongo = PowerMockito.mock(Mongo.class);
        db = PowerMockito.mock(DB.class);
        DBCollection dbCollection = PowerMockito.mock(DBCollection.class);
        AbstractPersistenceAdapter.setIpadres("149.5.47.170");
        AbstractPersistenceAdapter.setPoort(27017);
        AbstractPersistenceAdapter.setDatabaseNaam("burgernet");
        AbstractPersistenceAdapter.setDatabaseInlogWachtwoord("breas2012");

    }
    @Test
    public void testWijzigMelding() throws UnknownHostException, IllegalArgumentException, PersistenceNotFoundException {
        new MongoDB();
        MongoDBCrudMelding mongoDBCrudMelding = MongoDBCrudMelding.getInstance();
        Melding melding = new Melding("Mijn zoon Bram is vermoord door mij",
                new HerhalendeCategorie("vermissing"), 
                new Locatie(51.0, 5.234),
                null, Calendar.getInstance());
        try {
            String meldingID = mongoDBCrudMelding.slaMeldingOp(melding);
            Melding newMelding = new Melding("M'n zoon bram is vermoord door mij",
                    new HerhalendeCategorie("vermissing"), 
                    new Locatie(51.0, 5.234),
                    null, Calendar.getInstance());
            mongoDBCrudMelding.wijzigMelding(meldingID, newMelding);
            assertEquals("M'n zoon bram is vermoord door mij", mongoDBCrudMelding.haalMeldingOp(meldingID).getBeschrijving());
        } catch (IOException e) {
            LOGGER.trace(e);
        } catch (PersistenceConnectionException e) {
            LOGGER.trace(e);
        } catch (PersistenceDuplicateKeyException e) {
            LOGGER.trace(e);
        }

    }
    @Test(expected = PersistenceNotFoundException.class)
    public void haalMeldingOpTestFouteId() throws IllegalArgumentException, IOException, PersistenceConnectionException, PersistenceNotFoundException {
        AbstractPersistenceAdapter.setIpadres("149.5.47.170");
        AbstractPersistenceAdapter.setPoort(27017);
        new MongoDB();

        MongoDBCrudMelding.getInstance().haalMeldingOp("50d07c53aa65773f01500000");

    }
}
