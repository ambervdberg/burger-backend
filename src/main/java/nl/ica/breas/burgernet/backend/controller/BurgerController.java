/**
 * 
 */
package nl.ica.breas.burgernet.backend.controller;

import java.io.IOException;

import org.apache.log4j.Logger;

import nl.ica.breas.burgernet.backend.adresomvormers.AbstractAdresNaarLocatieAdapter;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceConnectionException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceDuplicateKeyException;
import nl.ica.breas.burgernet.backend.exceptions.VerkeerdeAdresExceptie;
import nl.ica.breas.burgernet.backend.hulpobjecten.Status;
import nl.ica.breas.burgernet.backend.model.Burger;
import nl.ica.breas.burgernet.backend.persistence.AbstractPersistenceAdapter;

/**
 * @author bramiejo
 *
 */
public class BurgerController implements IBurgerController {

    /** De LOGGER. */
    private static final Logger LOGGER = Logger.getLogger("InfoLogging");
    /**
     * Hierin wordt de AbstractPersistanceAdapter opgeslagen.
     */
    private AbstractPersistenceAdapter persistenceAdapter;
    /**
     * Hierin wordt de AbstractAdresNaarLocatie opgeslagen.
     */
    private AbstractAdresNaarLocatieAdapter adresAdapter;
    /**
     * Een constructor.
     */
    public BurgerController() {
        //nodig voor de reflection in de factory.
    }
    /**
     * @param persistenceAdapter de database adapter.
     * @param adresAdapter de adresNaarLocatieAdapter.
     */
    public BurgerController(AbstractPersistenceAdapter persistenceAdapter, AbstractAdresNaarLocatieAdapter adresAdapter) {
        this.persistenceAdapter = persistenceAdapter;
        this.adresAdapter = adresAdapter;
    }

    /**
     * Hiermee kun je een burger registreren in de database.
     * @param burger de burger die opgeslagen moet worden.
     * @return een status (true of false).
     */
    public final Status registreer(Burger burger) {
        
        try {
            burger.setLocatie(adresAdapter.adresToLocatie(burger.getAdres()));
            return new Status(true, persistenceAdapter.slaBurgerOp(burger));
        } catch (IllegalArgumentException | IOException
                | PersistenceConnectionException
                | PersistenceDuplicateKeyException e) {
            LOGGER.trace(e);
            return new Status(false, null);
        } catch (VerkeerdeAdresExceptie e) {
            return new Status(false, "U heeft geen juiste adres opgegeven");
        }   
    }
    
    /**
     * Hiermee kan de database adapter worden ingesteld;
     * @param persistenceAdapter de adapter die gebruikt moet worden.
     * @param adresAdapter de adapter die gebruikt moet worden.
     */
    public final void setAdapter(AbstractPersistenceAdapter persistenceAdapter, AbstractAdresNaarLocatieAdapter adresAdapter) {
        this.persistenceAdapter = persistenceAdapter;
        this.adresAdapter = adresAdapter;
    }



}
