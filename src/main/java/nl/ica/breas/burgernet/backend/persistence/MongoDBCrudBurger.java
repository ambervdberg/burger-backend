package nl.ica.breas.burgernet.backend.persistence;

import java.io.IOException;
import java.util.List;

import nl.ica.breas.burgernet.backend.exceptions.PersistenceConnectionException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceDuplicateKeyException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceInternalException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceNotFoundException;
import nl.ica.breas.burgernet.backend.model.Burger;
import nl.ica.breas.burgernet.backend.model.IMelding;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.ObjectId;
/** Deze class is create, read, update en delete voor burgers.
 * @author Rick de Weerd
 * @since 10/12/12
 * @version 0.1
 */
public final class MongoDBCrudBurger {
    /** Hier wordt de instantie opgeslagen.*/
    private static MongoDBCrudBurger instance = null;
    /** De MongoDB  Burgers collectie. */
    private DBCollection burgerCollectie;
    /** De limiet van query's */
    private static final int LIMIET = 16000000;

    /**
     * De private constructor voor MongoDBCrudBurger voor de Singleton.
     */
    private MongoDBCrudBurger()  {
    }
    /**
     * De initialisator van MongoDBCrudBurger.
     * @return De instantie van de MongoDBCrudBurger.
     */
    public static MongoDBCrudBurger getInstance() {
        synchronized (MongoDBCrudBurger.class) {
            if (instance == null) {
                instance = new MongoDBCrudBurger();
            }
        }
        return instance;
    }
    /**
     * Sla een burger op in de database.
     * @param burger de Burger die moet worden opgeslagen.
     * @return De ID van de Burger.
     * @throws IOException Fout bij het omvormen van of naar JSON.
     * @throws PersistenceConnectionException Kan geen verbinding maken met de database.
     * @throws PersistenceDuplicateKeyException Er staat al een object met deze key in de database.
     */
    public String slaBurgerOp(final Burger burger) throws IOException, PersistenceConnectionException, PersistenceDuplicateKeyException {
        try {
            DBObject dbBurger = MongoDBObjectOmzetter.getInstance().objectNaarDBObject(burger);
            this.burgerCollectie.save(dbBurger);
            return ((ObjectId) dbBurger.get("_id")).toString();
        } catch (MongoException.Network e) {
            throw new PersistenceConnectionException(e);
        } catch (MongoException.DuplicateKey e) {
            throw new PersistenceDuplicateKeyException(e);
        }
    }
    /**
     * Haal de Burger objecten op voor één Melding.
     * @param melding waarvan de Burger objecten opgehaald moeten worden.
     * @return een lijst met gevonden burgers.
     * @throws IOException Fout bij het omvormen van of naar JSON.
     * @throws PersistenceConnectionException Kan geen verbinding maken met de database.
     * @throws PersistenceDuplicateKeyException Er staat al een object met deze key in de database.
     * @throws PersistenceInternalException Wanneer MongoDB een InternalException geeft.
     */	
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Burger> selecteerBurgers(final IMelding melding) throws IOException,
    PersistenceConnectionException, PersistenceDuplicateKeyException, PersistenceInternalException {

        BasicDBObject query = 
                new BasicDBObject("locatie",
                        new BasicDBObject("$within", 
                                new BasicDBObject("$centerSphere", 
                                        MongoDBObjectOmzetter.getInstance().centerSphereArray(melding.getLocatie(), 
                                                melding.getCategorie().getStraal()))));
        DBCursor cursor = burgerCollectie.find(query).limit(LIMIET);
        return (List) MongoDBObjectOmzetter.getInstance().cursorNaarList(cursor, Burger.class);
    }

    /** De setter voor de BurgerCollectie.
     * @param burgerCollectie the burgerCollectie to set
     */
    public void setBurgerCollectie(DBCollection burgerCollectie) {
        this.burgerCollectie = burgerCollectie;
    }	
    /**
     * @param burgerId het id van de bruger die moet worden gevonden
     * @return de burger die gezocht word.
     * @throws IOException 
     * @throws PersistenceConnectionException 
     * @throws IllegalArgumentException Ongeldige database ID
     * @throws PersistenceNotFoundException De burger is niet gevonden.
     */
    public Burger getBurger(String burgerId) throws IOException, PersistenceConnectionException, 
    IllegalArgumentException, PersistenceNotFoundException {
        BasicDBObject query = new BasicDBObject("_id", new ObjectId(burgerId));
        Burger result = (Burger) MongoDBObjectOmzetter.getInstance().dBObjectNaarObject(this.burgerCollectie.findOne(query), Burger.class);
        if (result == null) {
            throw new PersistenceNotFoundException("De burger bestaat niet");
        }
        return result;
    }
}
