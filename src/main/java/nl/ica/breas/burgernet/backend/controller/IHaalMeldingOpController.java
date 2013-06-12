package nl.ica.breas.burgernet.backend.controller;

import java.util.List;

import nl.ica.breas.burgernet.backend.model.CategorieenMap;
import nl.ica.breas.burgernet.backend.model.IMelding;
import nl.ica.breas.burgernet.backend.model.Locatie;
import nl.ica.breas.burgernet.backend.persistence.AbstractPersistenceAdapter;
/**
 * @since 03-12-2012
 * @author bramiejo
 *
 */
public interface IHaalMeldingOpController {
	/**
	 * 
	 * @param meldingID die wordt doorgegeven om de melding op te halen.
	 * @return een Imelding om een melding aan te maken.
	 */
	IMelding haalMeldingOp(String meldingID);
	 /**
     * 
     * @param burgerID die wordt doorgegeven om de meldingen op te halen.
     * @return een List met meldingen die voor de gebruiker klaar staan.
     */
    List<IMelding> haalMeldingenOp(String burgerID);
    /**
    * 
    * @param burgerID die wordt doorgegeven om de meldingen op te halen.
    * @param locatie dit is de locatie die wordt doorgegeven om meldingen op te halen.
    * @return een List met meldingen die voor de gebruiker klaar staan.
    */
   List<IMelding> haalMeldingenOp(String burgerID, Locatie locatie);
	/**
	 * Hiermee kan de database adapter worden ingesteld;
	 * @param adapter de adapter die gebruikt moet worden.
	 */
	void setAdapter(AbstractPersistenceAdapter adapter);
	 /**
     * Hiermee kan de categorie lijst worden veranderd.
     * @param categorieenMap de map met categorieën
     */
    void setCategorieenMap(CategorieenMap categorieenMap);
}
