package nl.ica.breas.burgernet.backend.persistence;

import java.io.IOException;
import java.util.List;

import nl.ica.breas.burgernet.backend.exceptions.PersistenceConnectionException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceDuplicateKeyException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceInternalException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceNotFoundException;
import nl.ica.breas.burgernet.backend.model.IMelding;
import nl.ica.breas.burgernet.backend.model.Locatie;
import nl.ica.breas.burgernet.backend.model.Melding;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.ObjectId;

/** Deze class is create, read, update en delete voor Meldingen.
 * @author Rick de Weerd
 * @since 10/12/12
 * @version 0.2
 */
public final class MongoDBCrudMelding {

    /** De MongoDB Meldingen collectie. */
    private DBCollection meldingCollectie;
    /** Hierin zit de instantie van MongoDBObjectOmzetter. */
    private MongoDBObjectOmzetter mongoDBObjectOmzetter = MongoDBObjectOmzetter.getInstance();
    /** Hier wordt de instantie opgeslagen.*/
    private static MongoDBCrudMelding instance = null;

    /**
     * De private constructor. Dit zorgt er voor dat andere classes de getInstance methode moeten aanroepen.
     */
    private MongoDBCrudMelding() {
    }
    /**
     * De initialisator van MongoDBCrudMelding.
     * @return De instantie van de MongoDBCrudMelding.
     */
    public static MongoDBCrudMelding getInstance() {
        synchronized (MongoDBCrudMelding.class) {	
            if (instance == null) {
                instance = new MongoDBCrudMelding();
            }
        }
        return instance;
    }
    /**
     * Sla een melding op in de database.
     * @param melding de melding die moet worden opgeslagen.
     * @return de ID van de Burger.
     * @throws IOException Fout bij het omvormen van of naar JSON.
     * @throws PersistenceConnectionException Kan geen verbinding maken met de database.
     * @throws PersistenceDuplicateKeyException Er staat al een object met deze key in de database.
     */
    public String slaMeldingOp(final IMelding melding) throws IOException, PersistenceConnectionException, PersistenceDuplicateKeyException {
        try {
            DBObject dbMelding = mongoDBObjectOmzetter.objectNaarDBObject(melding);
            dbMelding.put("verloopDatumTijd", melding.getVerloopDatumTijd().getTime());
            this.meldingCollectie.save(dbMelding);
            return ((ObjectId) dbMelding.get("_id")).toString();
        } catch (MongoException.Network e) {
            throw new PersistenceConnectionException(e);
        } catch (MongoException.DuplicateKey e) {
            throw new PersistenceDuplicateKeyException(e);
        }
    }
    /**
     * Haalt melding op van de database.
     * @param meldingId het ID waarmee de melding gevonden kan worden.
     * @return de Melding
     * @throws IOException Fout bij het omvormen van of naar JSON.
     * @throws PersistenceConnectionException Kan geen verbinding maken met de database.
     * @throws IllegalArgumentException Ongeldige database ID
     * @throws PersistenceNotFoundException De Melding is niet gevonden.
     */
    public IMelding haalMeldingOp(String meldingId) throws IOException,
    PersistenceConnectionException, IllegalArgumentException, PersistenceNotFoundException {
        BasicDBObject query = new BasicDBObject("_id", new ObjectId(meldingId));
        IMelding melding = (IMelding) mongoDBObjectOmzetter.dBObjectNaarObject(this.meldingCollectie.findOne(query), Melding.class);
        if (melding == null) {
            throw new PersistenceNotFoundException("De Melding bestaat niet");
        }
        return melding;
    }

    /**
     * Deze methode vervangt de bewuste melding door de nieuwe melding.
     * @param meldingId Het ID van de melding die vervangen moet worden.
     * @param melding Dit is de melding die de oude melding vervangt.
     * @return een true of false als de melding wijziging geslaagd is.
     * @throws IOException 
     */
    public boolean wijzigMelding(String meldingId, IMelding melding) throws IOException {
        BasicDBObject query = new BasicDBObject("_id", new ObjectId(meldingId));
        DBObject dbMelding;
        dbMelding = mongoDBObjectOmzetter.objectNaarDBObject(melding);
        dbMelding.put("verloopDatumTijd", melding.getVerloopDatumTijd().getTime());
        this.meldingCollectie.update(query, dbMelding);
        return true;
    }
    /** Setter voor de meldingCollectie.
     * @param meldingCollectie the meldingCollectie to set
     */
    public void setMeldingCollectie(DBCollection meldingCollectie) {
        this.meldingCollectie = meldingCollectie;
    }
    /**
     * 
     * @param loc De locatie die als centrum geld voor de straal berekeningen.
     * @param categorieNaam De categorie die gezocht word.
     * @param straal De straal waarbinnen gezocht word.
     * @return een lijst met meldingen die aan de eisen voldoen.
     * @throws IOException 
     * @throws PersistenceInternalException 
     * @throws RuntimeException word gegooid als de coordinaten boven de 180 of onder de -180 zijn.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Melding> haalMeldingenOp(Locatie loc, String categorieNaam,
            double straal) throws PersistenceInternalException, IOException, RuntimeException {
        BasicDBObject query = new BasicDBObject("aangepasteStraal", false)
        .append("locatie",
                new BasicDBObject("$within", 
                        new BasicDBObject("$centerSphere", 
                                MongoDBObjectOmzetter.getInstance().centerSphereArray(loc, straal))));
        query.put("categorie.naam", categorieNaam);
        DBCursor cursor = meldingCollectie.find(query);
        return (List) MongoDBObjectOmzetter.getInstance().cursorNaarList(cursor, Melding.class);     
    }
    /**
     * haal de meldingenmet een aangepaste straal op voor een locatie.
     * @author Samuel van Oostveen
     * @param loc De locatie die als centrum geld voor de straal berekeningen.
     * @return een lijst met meldingen die aan de eisen voldoen.
     * @throws IOException 
     * @throws PersistenceInternalException 
     * @throws RuntimeException word gegooid als de coordinaten boven de 180 of onder de -180 zijn.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Melding> haalAangepasteStraalMeldingenOp(Locatie loc) 
            throws PersistenceInternalException, IOException, RuntimeException {
        BasicDBObject query = new BasicDBObject("aangepasteStraal", true)
        .append("$where", "Geo.sphereDistance( this.locatie, [" + loc.getLongitude() + "," + loc.getLatitude() + "] ) < this.categorie.straal/6378.137");
        DBCursor cursor = meldingCollectie.find(query);
        return (List) MongoDBObjectOmzetter.getInstance().cursorNaarList(cursor, Melding.class);       
    }
}
