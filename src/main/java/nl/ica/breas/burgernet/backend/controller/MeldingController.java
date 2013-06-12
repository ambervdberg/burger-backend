package nl.ica.breas.burgernet.backend.controller;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import nl.ica.breas.burgernet.backend.exceptions.CategorieNotFoundException;
import nl.ica.breas.burgernet.backend.exceptions.OnvolledigeMeldingException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceConnectionException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceDuplicateKeyException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceInternalException;
import nl.ica.breas.burgernet.backend.hulpobjecten.Status;
import nl.ica.breas.burgernet.backend.model.AbstractCopyableCategorie;
import nl.ica.breas.burgernet.backend.model.Burger;
import nl.ica.breas.burgernet.backend.model.AbstractCategorie;
import nl.ica.breas.burgernet.backend.model.CategorieenMap;
import nl.ica.breas.burgernet.backend.model.DummyCategorie;
import nl.ica.breas.burgernet.backend.model.IMelding;
import nl.ica.breas.burgernet.backend.persistence.AbstractPersistenceAdapter;
import nl.ica.breas.burgernet.backend.push.IPushServer;
/**Dit is de controller die de meldingen verwerkt.
 * 
 * @author Eric
 * @version 0.01
 * @since 27-11-2012
 */
public class MeldingController implements IMeldingController {

    /**
     * Hierin wordt de IPersistanceAdapter opgeslagen.
     */
    private AbstractPersistenceAdapter adapter;

    /**
     * Deze wordt gebruikt om de errors te loggen.
     */
    private static final Logger LOGGER = Logger.getLogger("InfoLogging");
    /**
     * In deze HashMap staan de voraf gedefinieerde categorien (de key is Categorie.naam).
     */
    private CategorieenMap categorieenMap;
    /**
     * Dit is de pushserver die de push messages gaat versturen.
     */
    private IPushServer pushServer;

    /**
     * Een constructor
     */
    public MeldingController() {
        //nodig voor de reflection in de factory.
    }
    /**
     * met deze constructor word de controller geinitialiseerd.
     * @param categorieenMap de Map met categorieën
     * @param adapter de instantie van de databaseAdapter.
     * @param pushServer de instantie van de PushServer
     */
    public MeldingController(final CategorieenMap categorieenMap,
            final AbstractPersistenceAdapter adapter,
            final IPushServer pushServer) {
        this.categorieenMap = categorieenMap;
        this.adapter = adapter;
        this.pushServer = pushServer;
    }

    /**
     * Met deze methode worden berichten verwerkt die van de overheid komen.
     *
     * @param melding De melding die verwerkt moet worden
     * @return Als de melding succesvol verwerkt is wordt er een status
     * met de waarde true gereturnt en het meldingid van de gemaakte melding.
     */
    public final Status verwerkMeldingOverheid(final IMelding melding) {
        try {
            DummyCategorie dummy = (DummyCategorie) melding.getCategorie();
            zetCategorie(melding);
            melding.laadAangepasteParamters(dummy);
            return afrondenVerwerkMelding(melding);
        } catch (CategorieNotFoundException e) {
            return new Status(false, null);
        }
    }
    /**
     * Met deze methode worden berichten verwerkt.
     *
     * @param melding De melding die verwerkt moet worden
     * @return Als de melding succesvol verwerkt is wordt er een status
     * met de waarde true gereturnt en het meldingid van de gemaakte melding.
     */
    public final Status verwerkMeldingBurger(final IMelding melding) {
        try {
            zetCategorie(melding);
            return afrondenVerwerkMelding(melding);
        } catch (CategorieNotFoundException e) {
            return new Status(false, null);
        }
    }

    /**
     * Hiermee kan de categorie lijst worden veranderd.
     * @param categorieenMap de map met categorieën
     */
    public final void setCategorieenMap(final CategorieenMap categorieenMap) {
        this.categorieenMap = categorieenMap;
    }
    /**
     * Hiermee kan de database adapter worden ingesteld;
     * @param adapter de adapter die gebruikt moet worden.
     */
    public final void setAdapter(final AbstractPersistenceAdapter adapter) {
        this.adapter = adapter;
    }
    /**
     * Hiermee kan de pushsercer adapter worden ingesteld;
     * @param pushserver de pushserver die gebruikt moet worden.
     */
    public final void setPushServer(final IPushServer pushserver) {
        this.pushServer = pushserver;
    }
    /**
     * In deze methode worden de acties uitgevoerd om het verwerken van een melding af te ronden.
     * @param melding de melding waarvan de verwerking afgerond moet worden.
     * @return de status met het melding id.
     */
    private Status afrondenVerwerkMelding(IMelding melding) {  
        try {
            melding.zetVerloopDatumVast(); 
            sendPushMessages(adapter.selecteerBurgers(melding), melding);
            return new Status(true, adapter.slaMeldingOp(melding));
        } catch (IOException | PersistenceConnectionException
                | PersistenceDuplicateKeyException | PersistenceInternalException | OnvolledigeMeldingException e) {
            LOGGER.trace(e);
        } 
        return new Status(false, null);
    }
    /**
     * Deze methode zet de categorie voor een melding gezet aan de hand van de info in de dummy categorie.
     * @param melding de melding waarvan de categorie moet worden gezet.
     * @throws CategorieNotFoundException deze fout word gegooid als er een onbekende categorie in de melding zit.
     */
    private void zetCategorie(IMelding melding) throws CategorieNotFoundException {
        if (categorieenMap.getCategorieen().containsKey(melding.getCategorie().getNaam())) {
            DummyCategorie dummy = (DummyCategorie) melding.getCategorie();
            AbstractCategorie standaardCat = categorieenMap.getCategorieen().get(dummy.getNaam()); 
            melding.setCategorie(((AbstractCopyableCategorie) standaardCat).copy());

        } else {
            throw new CategorieNotFoundException();
        }
    }
    /**
     * Met deze methode word voor elke burger die in de lijst zit een opdracht naar de pushserver gestuurd.
     * @param brugers de lijst met burgers die de push message moeten ontvangen
     * @param melding de melding waarover de burgers een push message moeten ontvangen
     */
    private void sendPushMessages(final List<Burger> brugers, final IMelding melding) {
        for (Burger b: brugers) {
            pushServer.verstuurPushMelding(b, melding);
        }
    }
}
