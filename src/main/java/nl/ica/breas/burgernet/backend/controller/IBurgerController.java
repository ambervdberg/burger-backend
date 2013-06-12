package nl.ica.breas.burgernet.backend.controller;

import nl.ica.breas.burgernet.backend.adresomvormers.AbstractAdresNaarLocatieAdapter;
import nl.ica.breas.burgernet.backend.hulpobjecten.Status;
import nl.ica.breas.burgernet.backend.model.Burger;
import nl.ica.breas.burgernet.backend.persistence.AbstractPersistenceAdapter;
/**
 * This is the interface for the BurgerController.
 * @author bramiejo
 *
 */
public interface IBurgerController {
    /**
     * Hiermee kan de Burger geregistreerd worden.
     * @param burger het burgerobject wat wordt gebruikt.
     * @return een true of false.
     */
    Status registreer(Burger burger);
    /**
     * Hiermee kan de database adapter worden ingesteld;
     * @param persistenceAdapter de persistence adapter die gebruikt moet worden.
     * @param adresAdapter de adres adapter die gebruikt moet worden.
     */
    void setAdapter(AbstractPersistenceAdapter persistenceAdapter, AbstractAdresNaarLocatieAdapter adresAdapter);
}
