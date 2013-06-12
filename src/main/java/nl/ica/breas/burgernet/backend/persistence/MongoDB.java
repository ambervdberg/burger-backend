package nl.ica.breas.burgernet.backend.persistence;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.Mongo;

import nl.ica.breas.burgernet.backend.exceptions.PersistenceConnectionException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceDuplicateKeyException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceInternalException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceNotFoundException;
import nl.ica.breas.burgernet.backend.model.Burger;
import nl.ica.breas.burgernet.backend.model.IMelding;
import nl.ica.breas.burgernet.backend.model.Locatie;
import nl.ica.breas.burgernet.backend.model.Melding;


/**
 * De klasse MongoDB.
 * Deze klasse implementeert de interface AbstractPersistenceAdapter
 * en vertaalt dit naar MongoDB.
 *
 * @author Samuel van Oostveen en Amber Schühmacher
 */
public final class MongoDB extends AbstractPersistenceAdapter {

    /**De database voor MongoDB.*/
    private DB db;
    /** De collectie uit de database met Burgers */
    private static final String COLLECTIE_BURGER = "Burgers";
    /** De collectie uit de database met Meldingen */
    private static final String COLLECTIE_MELDING = "Meldingen";
    /**
     * Initialiseerd een instance van MongoDB.
     * Start een verbinding met de MongoDb server
     * @throws UnknownHostException Er kan geen verbinding worden gemaakt met de databaseserver
     */
    public MongoDB() throws UnknownHostException {
        Mongo m = new Mongo(AbstractPersistenceAdapter.getIpadres(), AbstractPersistenceAdapter.getPoortnummer());
        db = m.getDB(getDatabaseNaam());
        db.authenticate(getDatabaseNaam(), getDatabaseInlogWachtwoord().toCharArray());
        
        // Zet de collecties op de CRUD's
        MongoDBCrudMelding.getInstance().setMeldingCollectie(db.getCollection(COLLECTIE_MELDING));
        MongoDBCrudBurger.getInstance().setBurgerCollectie(db.getCollection(COLLECTIE_BURGER));
        

    }
    /**
     * Sla een melding op in de database.
     * @param melding de melding die moet worden opgeslagen.
     * @return de ID van de Burger.
     * @throws IOException Fout bij het omvormen van of naar JSON.
     * @throws PersistenceConnectionException Kan geen verbinding maken met de database.
     * @throws PersistenceDuplicateKeyException Er staat al een object met deze key in de database.
     */
    public String slaMeldingOp(final IMelding melding) 
            throws IOException, PersistenceConnectionException, PersistenceDuplicateKeyException {
        return MongoDBCrudMelding.getInstance().slaMeldingOp(melding);
    }
    /**
     * Haalt melding op van de database.
     * @param meldingId het ID waarmee de melding gevonden kan worden.
     * @return de Melding
     * @throws IOException Fout bij het omvormen van of naar JSON.
     * @throws PersistenceConnectionException Kan geen verbinding maken met de database.
     * @throws IllegalArgumentException Ongeldige object ID
     * @throws PersistenceNotFoundException De Melding is niet gevonden.
     */
    public IMelding haalMeldingOp(String meldingId) throws IOException,
    PersistenceConnectionException, IllegalArgumentException, PersistenceNotFoundException {
        return MongoDBCrudMelding.getInstance().haalMeldingOp(meldingId);
    }

    /**
     * Deze methode vervangt de bewuste melding door de nieuwe melding.
     * @param meldingId Het ID van de melding die vervangen moet worden.
     * @param melding Dit is de melding die de oude melding vervangt.
     * @return een true of false als de wijziging succesvol is.
     * @throws IOException 
     */
    public boolean wijzigMelding(String meldingId, IMelding melding) throws IOException {
        return MongoDBCrudMelding.getInstance().wijzigMelding(meldingId, melding);
    }
    /**
     * Sla een burger op in de database.
     * @param burger de Burger die moet worden opgeslagen.
     * @return De ID van de Burger.
     * @throws IOException Fout bij het omvormen van of naar JSON.
     * @throws PersistenceConnectionException Kan geen verbinding maken met de database.
     * @throws PersistenceDuplicateKeyException Er staat al een object met deze key in de database.
     */
    public String slaBurgerOp(final Burger burger)
            throws IOException, PersistenceConnectionException, PersistenceDuplicateKeyException {
        return MongoDBCrudBurger.getInstance().slaBurgerOp(burger);
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
    public List<Burger> selecteerBurgers(final IMelding melding) throws IOException,
    PersistenceConnectionException, PersistenceDuplicateKeyException, PersistenceInternalException {
        return MongoDBCrudBurger.getInstance().selecteerBurgers(melding);
    }
    /**
     * Deze wordt gebruikt om de instantie van de database op te vragen.
     * @return De database.
     */
    public DB getDb() {
        return db;
    }
    /**
     * Hiermee set je de database.
     * @param db de database om te setten.
     */
    public void setDb(DB db) {
        this.db = db;
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
    public List<Melding> haalMeldingenOp(Locatie loc, String categorieNaam,
            double straal) throws PersistenceInternalException, IOException, RuntimeException {
        return MongoDBCrudMelding.getInstance().haalMeldingenOp(loc,
                categorieNaam,
                straal);
    }
    /**
     * haal de meldingenmet een aangepaste straal op voor een locatie.
     * @param loc De locatie die als centrum geld voor de straal berekeningen.
     * @return een lijst met meldingen die aan de eisen voldoen.
     * @throws IOException 
     * @throws PersistenceInternalException 
     * @throws RuntimeException word gegooid als de coordinaten boven de 180 of onder de -180 zijn.
     */
    public List<Melding> haalAangepasteStraalMeldingenOp(Locatie loc) 
            throws PersistenceInternalException, IOException, RuntimeException {
        return MongoDBCrudMelding.getInstance().haalAangepasteStraalMeldingenOp(loc);
    }

    /**
     * @param burgerId het id van de bruger die moet worden gevonden
     * @return de burger die gezocht word.
     * @throws IOException 
     * @throws IllegalArgumentException Ongeldige database ID
     * @throws PersistenceNotFoundException De burger is niet gevonden.
     * @throws PersistenceConnectionException Kan geen verbinding maken met de database.
     */
    public Burger getBurger(String burgerId) throws IOException, PersistenceConnectionException, 
    IllegalArgumentException, PersistenceNotFoundException {
        return MongoDBCrudBurger.getInstance().getBurger(burgerId);
    }

}