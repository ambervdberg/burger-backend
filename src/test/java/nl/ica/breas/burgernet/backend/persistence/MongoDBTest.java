/**
 * 
 */
package nl.ica.breas.burgernet.backend.persistence;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import nl.ica.breas.burgernet.backend.exceptions.PersistenceConnectionException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceDuplicateKeyException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceInternalException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceNotFoundException;
import nl.ica.breas.burgernet.backend.model.Adres;
import nl.ica.breas.burgernet.backend.model.Bijlage;
import nl.ica.breas.burgernet.backend.model.Burger;
import nl.ica.breas.burgernet.backend.model.AbstractCategorie;
import nl.ica.breas.burgernet.backend.model.HerhalendeCategorie;
import nl.ica.breas.burgernet.backend.model.IMelding;
import nl.ica.breas.burgernet.backend.model.Locatie;
import nl.ica.breas.burgernet.backend.model.Melding;
import nl.ica.breas.burgernet.backend.model.NietHerhalendeCategorie;
import nl.ica.breas.burgernet.backend.persistence.MongoDB;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.ObjectId;
import com.mongodb.util.JSON;

/**
 * De test klasse van MongoDB.
 *
 * @author Samuel van Oostveen en Amber Schühmacher
 */
public class MongoDBTest {
    /** Het ipadres van de MongoDB server. */
    private final String IPADRES = "149.5.47.170";

    /** Het poortnummer van de MongoDB server. */
    private final int POORTNUMMER = 27017;
    private final String databaseNaam = "burgernet";
    private final String databaseInlogNaam = "burgernet";
    private final String databaseInlogWachtwoord = "breas2012";

    /** De collectie. */
    private DBCollection collectie;
    /** Deze wordt gebruikt om de erros te loggen. */
    private static final Logger LOGGER = Logger.getLogger("InfoLogging");
    /**
     * Maakt verbinding met de database.
     *
     * @throws Exception de exceptie
     */
    @Before
    public void setUp() throws Exception {
        AbstractPersistenceAdapter.setIpadres("149.5.47.170");
        AbstractPersistenceAdapter.setPoort(27017);
        AbstractPersistenceAdapter.setDatabaseNaam(databaseInlogNaam);
        AbstractPersistenceAdapter.setDatabaseInlogWachtwoord(databaseInlogWachtwoord);
        // Maak verbindig met de database
        Mongo m = new Mongo("149.5.47.170", 27017);
        // Open de database "Burgernet"
        DB db = m.getDB(databaseNaam);
        // Open de meldingCollectie "Meldingen"
        this.collectie = db.getCollection("Meldingen");
    }

    @Test
    public void testStraal() throws UnknownHostException, IOException, PersistenceConnectionException, PersistenceDuplicateKeyException, PersistenceInternalException {
        AbstractCategorie categorie = new NietHerhalendeCategorie("naam");
        categorie.setStraal((double) 50);
        List<Burger> burgerList =  new MongoDB().selecteerBurgers(new Melding("test", new HerhalendeCategorie("test"), new Locatie(51.2253,5.5334), new Bijlage(),  Calendar.getInstance()));
        burgerList.clear();
    }
    /**
     * Hier wordt een IMelding object in de database gezet. Daarna wordt gekeken of dit object ook echt in de database is gekomen.
     * @throws PersistenceDuplicateKeyException 
     * @throws PersistenceConnectionException 
     * @throws IOException 
     * @throws PersistenceNotFoundException 
     * @throws IllegalArgumentException 
     */
    @Test
    public void test() throws IOException, PersistenceConnectionException, PersistenceDuplicateKeyException, IllegalArgumentException, PersistenceNotFoundException {   
        MongoDB db = new MongoDB();
        IMelding melding = new Melding("test", new HerhalendeCategorie("test"), new Locatie(51.2253,5.5334), new Bijlage(), Calendar.getInstance());
        String id = db.slaMeldingOp(melding);
        IMelding gevondeMelding = db.haalMeldingOp(id);
        assertEquals(melding.getBeschrijving(), gevondeMelding.getBeschrijving());
        assertEquals(melding.getCategorie().getNaam(), gevondeMelding.getCategorie().getNaam());
    }
    /**
     * Hier wordt een Burger object in de database gezet. Daarna wordt gekeken of dit object ook echt in de database is gekomen.
     * @throws PersistenceDuplicateKeyException 
     * @throws PersistenceConnectionException 
     * @throws IOException 
     * @throws UnknownHostException 
     */
    @Test
    public void testBurger() throws UnknownHostException, IOException, PersistenceConnectionException, PersistenceDuplicateKeyException {

        Burger burger = new Burger();
        new MongoDB().slaBurgerOp(burger);

    }
    /**
     * Hier wordt een IMelding object in de database gezet. Daarna wordt gekeken of dit object ook echt in de database is gekomen.
     * @throws PersistenceInternalException 
     * @throws PersistenceDuplicateKeyException 
     * @throws PersistenceConnectionException 
     * @throws IOException 
     */
    @Test
    public void straalTest() throws IOException, PersistenceConnectionException, PersistenceDuplicateKeyException, PersistenceInternalException {
        AbstractCategorie categorie = new HerhalendeCategorie("test");
        categorie.setStraal((double) 100);
        IMelding melding =new Melding("test", new HerhalendeCategorie("vermissing"), new Locatie(50.0,50.0), new Bijlage(), Calendar.getInstance());

        MongoDB db = new MongoDB();
        List<Burger> burgerList =  new MongoDB().selecteerBurgers(melding);
        assertEquals(true, burgerList instanceof ArrayList);
        for(double x = 20.00; x<70.00; x+=10.0 ){
            Burger burger = new Burger("Piet", "Paulisma", new Locatie(x,x), new Adres("PietjesStraat", "Hilversum", 23, "9332ER"), "test@test.nl");
            db.slaBurgerOp(burger);
            System.out.println(x);
        }
        burgerList = db.selecteerBurgers(melding);
        System.out.println(burgerList.size());
        assertEquals(true, burgerList.size()>0);
        //assertEquals(melding.getCategorie().getNaam(), gevondeMelding.getCategorie().getNaam());
        //verwijderMelding(melding.getBeschrijving());
    }

    /**
     * Hier wordt een IMelding object in de database gezet. Daarna wordt gekeken of dit object ook echt in de database is gekomen.
     * @throws PersistenceConnectionException 
     * @throws IOException 
     * @throws IllegalArgumentException 
     * @throws PersistenceDuplicateKeyException 
     * @throws PersistenceNotFoundException 
     */
    @Test
    public void testHaalMeldingOp() throws IllegalArgumentException, IOException, PersistenceConnectionException, PersistenceDuplicateKeyException, PersistenceNotFoundException {

        IMelding melding = new Melding("test", new HerhalendeCategorie("test"), new Locatie(51.2253,5.5334), new Bijlage(), Calendar.getInstance());
        AbstractPersistenceAdapter.setIpadres("149.5.47.170");
        AbstractPersistenceAdapter.setPoort(27017);
        MongoDB db = new MongoDB();
        String id = db.slaMeldingOp(melding);
        System.out.println("id = " + id);
        assertEquals(melding.getBeschrijving(), db.haalMeldingOp(id).getBeschrijving());
        verwijderMelding(melding.getBeschrijving());
    }	

    /**
     * Zoekt een IMelding met een beschrijving.
     *
     * @param beschrijving de beschrijving
     * @return de gevonden IMelding
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    private IMelding zoekMelding(String beschrijving) throws JsonParseException, JsonMappingException, IOException {
        BasicDBObject query = new BasicDBObject();
        query.put("beschrijving", beschrijving);
        BasicDBObject result = (BasicDBObject) collectie.findOne(query);;
        IMelding melding = null;

        melding = new ObjectMapper().readValue(result.toString(),Melding.class);

        return melding;
    }

    /**
     * Verwijder een IMelding met een beschrijving.
     *
     * @param beschrijving de beschrijving
     */
    private void verwijderMelding(String beschrijving) {
        BasicDBObject query = new BasicDBObject();
        query.put("beschrijving", beschrijving); 
        collectie.remove(query);
    }
    /**
     * Sla een melding op in de database.
     * @param melding de melding die moet worden opgeslagen.
     * @return true of false naarmate het geslaagt is.
     * @throws IOException Fout bij het omvormen van of naar JSON.
     * @throws PersistenceConnectionException Kan geen verbinding maken met de database.
     * @throws PersistenceDuplicateKeyException Er staat al een object met deze key in de database.
     */
    public final String slaMeldingOp(final IMelding melding) throws IOException, PersistenceConnectionException, PersistenceDuplicateKeyException {

        DBObject object = objectNaarDBObject(melding);
        this.collectie.save(object);
        ObjectId id =(ObjectId) object.get("_id");
        return id.toString();

    }
    @Test
    public void testWijzigMelding() throws UnknownHostException, IOException, PersistenceConnectionException, PersistenceDuplicateKeyException, IllegalArgumentException, PersistenceNotFoundException {
        IMelding newMelding = new Melding("test", new HerhalendeCategorie("test"), new Locatie(51.2253,5.5334), new Bijlage(), Calendar.getInstance());	
        IMelding changeMelding = new Melding("test", new HerhalendeCategorie("test"), new Locatie(51.2253,5.5334), new Bijlage(), Calendar.getInstance());

        String meldingId =  new MongoDB().slaMeldingOp(newMelding);
        new MongoDB().wijzigMelding(meldingId, changeMelding);
        assertEquals(changeMelding.getBeschrijving(),  new MongoDB().haalMeldingOp(meldingId).getBeschrijving());
    }
    /**
     * vormt een Object om naar een DBObject.
     *
     * @param object het object
     * @return het DBObject
     * @throws IOException Er was een fout bij het omvormen. (Signals that an I/O exception has occurred).
     */
    private DBObject objectNaarDBObject(final Object object) throws IOException {
        return (DBObject) JSON.parse(new ObjectMapper().writeValueAsString(object));
    }

    @Test
    public void haalMeldingenopTest() throws PersistenceInternalException, IOException, RuntimeException {
        //AbstractPersistenceAdapter.setIpadres("149.5.47.170");
        //AbstractPersistenceAdapter.setPoort(27017);
        MongoDB m = new MongoDB();
        Burger b = new Burger("Bram", "Arts", new Locatie(2.3,4.3), new Adres(
                "Harrie", "sint", 4, "5867ah"), "test@test.nl");
        List<Melding> test = m.haalMeldingenOp(b.getLocatie(), "vermissing" , 10);
        assertEquals(true, test instanceof ArrayList);
    }

    @Test
    public void getBurgerTest() throws IllegalArgumentException, IOException, PersistenceConnectionException, PersistenceNotFoundException {

        MongoDB m = new MongoDB();
        Burger b = m.getBurger("50d07c53aa65773feb574563");
        assertEquals(true, b instanceof Burger);
    }

    @Test
    public void testGetAndSetDb() throws UnknownHostException {
        MongoDB db = new MongoDB();
        Mongo m = new Mongo(IPADRES, POORTNUMMER);
        db.setDb(m.getDB("burgernet"));
        assertEquals(m.getDB("burgernet"), db.getDb());
    }
    @Test
    public void testHaalMeldingenOp() throws IllegalArgumentException, PersistenceInternalException, IOException, RuntimeException, PersistenceConnectionException, PersistenceNotFoundException {

        //IMelding melding = new Melding("test", new HerhalendeCategorie("test"), new Locatie(51.2253,5.5334), "vandalisme", new Bijlage(), new Date());
        //AbstractPersistenceAdapter.setIpadres("149.5.47.170");
        //AbstractPersistenceAdapter.setPoort(27017);
        MongoDB db = new MongoDB();
        List<Melding> l = db.haalMeldingenOp(db.getBurger("50d07c53aa65773f01584563").getLocatie(), "test", 1);
        /*for(Melding m:l){
               System.out.println(m.getCategorie().getNaam());
            }*/
        assertEquals(1, l.size());
        assertEquals("randgeval binnen", l.get(0).getBeschrijving());  

    }   
    @Test

    public void testhaalAangepasteStraalMeldingenOp() throws IllegalArgumentException, PersistenceInternalException, IOException, RuntimeException, PersistenceConnectionException, PersistenceNotFoundException {

        //AbstractPersistenceAdapter.setIpadres("149.5.47.170");
        //AbstractPersistenceAdapter.setPoort(27017);
        //AbstractPersistenceAdapter.setDatabaseNaam("burgernet");
        //AbstractPersistenceAdapter.setDatabaseInlogWachtwoord("breas2012");
        MongoDB db = new MongoDB();
        List<Melding> l = db.haalAangepasteStraalMeldingenOp(db.getBurger("50d07c53aa65773f01584563").getLocatie());


        boolean gevonden = false;
        for(Melding m:l){
            if (m.getBeschrijving().equals("randgeval binnen. aangepasteStraal")) {
                gevonden = true;
            } else if (m.getBeschrijving() == "randgeval buiten. aangepasteStraal") {
                fail("de Melding 'randgeval buiten. aangepasteStraal' is gevonden. DIT IS FOUT!");
            }
        }
        if(gevonden == false) {
            fail("Melding 'randgeval binnen. aangepasteStraal' niet gevonden");
        }            
    }   
}