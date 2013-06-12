package nl.ica.breas.burgernet.backend.controller;
/**
 * Deze interface is voor de controller van de meldingen.
 */

import nl.ica.breas.burgernet.backend.hulpobjecten.Status;
import nl.ica.breas.burgernet.backend.model.CategorieenMap;
import nl.ica.breas.burgernet.backend.model.IMelding;
import nl.ica.breas.burgernet.backend.persistence.AbstractPersistenceAdapter;
import nl.ica.breas.burgernet.backend.push.IPushServer;

/**
 * @author Eric Bonestroo
 * @since 27/11/12
 * @version 0.1
 */
public interface IMeldingController {
    /**
     * Met deze methode worden berichten verwerkt.
     * 
     * @param melding De melding die verwerkt moet worden
     * @return Als de melding succesvol verwerkt is word er true gereturnt als de melding niet juist is verwerkt wordt er false gereturnt
     */
    Status verwerkMeldingBurger(IMelding melding);
    /**
     * Met deze methode worden berichten verwerkt die van de overhied komen.
     * 
     * @param melding De melding die verwerkt moet worden
     * @return Als de melding succesvol verwerkt is word er true gereturnt als de melding niet juist is verwerkt wordt er false gereturnt
     */
    Status verwerkMeldingOverheid(IMelding melding);
    /**
     * Hiermee kan de database adapter worden ingesteld;
     * @param adapter de adapter die gebruikt moet worden.
     */
    void setAdapter(AbstractPersistenceAdapter adapter);
    /**
     * Hiermee kan de pushsercer adapter worden ingesteld;
     * @param pushserver de pushserver die gebruikt moet worden.
     */
    void setPushServer(IPushServer pushserver);
    /**
     * Hiermee kan de categorie lijst worden veranderd.
     * @param categorieenMap de map met categorieën
     */
    void setCategorieenMap(CategorieenMap categorieenMap);
}
