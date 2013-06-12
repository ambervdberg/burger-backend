package nl.ica.breas.burgernet.backend.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nl.ica.breas.burgernet.backend.exceptions.PersistenceConnectionException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceInternalException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceNotFoundException;
import nl.ica.breas.burgernet.backend.model.Burger;
import nl.ica.breas.burgernet.backend.model.AbstractCategorie;
import nl.ica.breas.burgernet.backend.model.CategorieenMap;
import nl.ica.breas.burgernet.backend.model.IMelding;
import nl.ica.breas.burgernet.backend.model.Locatie;
import nl.ica.breas.burgernet.backend.persistence.AbstractPersistenceAdapter;

import org.apache.log4j.Logger;

/**Dit is de controller die de meldingen verwerkt.
 * 
 * @author Bram, Amber, Eric
 * @version 0.3
 * @since 08-01-2013
 */
/**
 * @author Amber
 *
 */
public class HaalMeldingOpController implements IHaalMeldingOpController {

    /**
     * Hierin wordt de IPersistanceAdapter opgeslagen.
     */
    private AbstractPersistenceAdapter adapter;

    /**
     * In deze HashMap staan de voraf gedefinieerde categorien (de key is Categorie.naam).
     */
    private CategorieenMap categorieenMap;

    /**
     * Deze wordt gebruikt om de errors te loggen.
     */
    private static final Logger LOGGER = Logger.getLogger("InfoLogging");

    /**
     * met deze constructor word de controller geinitialiseerd.
     *
     * @param categorieenMap de Map met categorieën.
     * @param adapter de instantie van de DatabaseAdapter.
     */
    public HaalMeldingOpController(final CategorieenMap categorieenMap, final AbstractPersistenceAdapter adapter) {
        this.categorieenMap = categorieenMap;
        this.adapter = adapter;
    }

    /**
     * Een constructor.
     */
    public HaalMeldingOpController() {
        //nodig voor de reflection in de factory.
    }
    /**
     * Met deze methode worden meldingID verwerkt.
     * 
     * @param meldingID De meldingID die verwerkt moet worden.
     * @return Als de melding succesvol verwerkt is wordt er true gereturned.
     * Als de melding niet juist is verwerkt, wordt er false gereturned.
     */
    public final IMelding haalMeldingOp(final String meldingID) {
        try {
            return adapter.haalMeldingOp(meldingID);
        } catch (PersistenceConnectionException | IOException | IllegalArgumentException | PersistenceNotFoundException e) { 
            LOGGER.trace(e);
        }
        return null;
    }

    /**
     * Hiermee kan de database adapter worden ingesteld;.
     *
     * @param adapter de adapter die gebruikt moet worden.
     */
    public final void setAdapter(final AbstractPersistenceAdapter adapter) {
        this.adapter = adapter;
    }
    /**
     * Hiermee wordt een lijst van meldingen opgehaald voor een burger.
     * @param burgerID dit is de id van de Burger waarvoor Meldingen moeten worden opgehaald.
     * @return een lijst met Meldingen.
     */
    public final List<IMelding> haalMeldingenOp(final String burgerID) {
        try {
            Burger burger = adapter.getBurger(burgerID);
            return meldingenVoorLocatie(burger.getLocatie());
        } catch (PersistenceConnectionException |
                IOException | IllegalArgumentException | PersistenceNotFoundException e) {
            LOGGER.trace(e);
        } 
        return null;
    }

    /**
     * Hiermee kan de categorie lijst worden veranderd.
     * @param categorieenMap de map met categorieën
     */
    public final void setCategorieenMap(final CategorieenMap categorieenMap) {
        this.categorieenMap = categorieenMap;
    }

    /**
     * Gets the categorieen map.
     *
     * @return de categorieenMap
     */
    public final CategorieenMap getCategorieenMap() {
        return categorieenMap;
    }

    /**
     * Gets the adapter.
     *
     * @return de adapter
     */
    public final    AbstractPersistenceAdapter getAdapter() {
        return adapter;
    }

    /**
     * Haal meldingen op.
     *
     * @param burgerID dit is de id van de Burger waarvoor Meldingen moeten worden opgehaald.
     * @param locatie deze wordt gebruikt om de meldingen op een locatie op te halen.
     * @return een lijst met meldingen.
     */
    public final List<IMelding> haalMeldingenOp(final String burgerID, final Locatie locatie) {
        return meldingenVoorLocatie(locatie);
    }

    /**
     * Meldingen voor locatie ophalen.
     *
     * @param  locatie deze wordt gebruikt om de meldingen op een locatie op te halen.
     * @return de lijst met meldingen
     */
    private List<IMelding> meldingenVoorLocatie(final Locatie locatie) {
        List<IMelding> meldingen = new ArrayList<IMelding>();
        Map<String, AbstractCategorie> map = categorieenMap.getCategorieen();
        try {
            for (Entry<String, AbstractCategorie> entry: map.entrySet()) {
                AbstractCategorie categorie = entry.getValue();
                meldingen.addAll(adapter.haalMeldingenOp(locatie, categorie.getNaam(), categorie.getStraal()));

            }
            meldingen.addAll(adapter.haalAangepasteStraalMeldingenOp(locatie));
        } catch (PersistenceInternalException | IOException | RuntimeException e) {
            LOGGER.trace(e);
        }
        return meldingen;        
    }
}
