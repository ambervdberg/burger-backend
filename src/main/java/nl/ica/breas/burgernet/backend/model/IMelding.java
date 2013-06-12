package nl.ica.breas.burgernet.backend.model;

import java.util.Calendar;
import nl.ica.breas.burgernet.backend.exceptions.OnvolledigeMeldingException;


/**
 * @author Eric en Samuel
 * @version 0.02
 * @since 27-11-2012
 */
public interface IMelding {
	
	/**Met deze methode kan de beschrijving van een bericht worden opgehaald.
	 * 
	 * @return de beschrijving van de melding.
	 */
	String getBeschrijving();
	/**Met deze methode kan de beschrijving van een bericht worden veranderd.
	 * 
	 * @param beschrijving De nieuwe beschrijving voor de melding.
	 */
	void setBeschrijving(String beschrijving);
	/**
	 * met deze methode kan de categorie worden opgehaald.
	 * 
	 * @return de Categorie.
	 */
	AbstractCategorie getCategorie();
	/**
	 * Met deze methode kan de categorie worden veranderd.
	 * @param categorie de categorie waarnaar het bericht moet veranderen.
	 */
	void setCategorie(AbstractCategorie categorie);
	/**
	 * Met deze methode kan de locatie worden opgehaald.
	 * @return de locatie
	 */
	Locatie getLocatie();
	/**
	 * Met deze methode kan de locatie worden veranderd.
	 * @param locatie de locatie die het bericht moet krijgen.
	 */
	void setLocatie(Locatie locatie);
	/**hiermee kan je de bijlage van de melding ophalen.
	 * 
	 * @return de bijlage.
	 */
	Bijlage getBijlage();
	/**Hiermee kan je de bijlage van de melding aanpassen.
	 * 
	 * @param bijlage de nieuwe bijlage voor de melding.
	 */
	void setBijlage(Bijlage bijlage);
	/**Hiermee kan je de datum wanneer de melding verloopt ophalen.
	 * 
	 * @return de datum wanneer de melding verloopt
	 */
	Calendar getVerloopDatumTijd();
	/**Hiermee kan je de datum wanneer de melding verloopt wijzigen.
	 * 
	 * @param verloopDatumTijd De nieuwe datum voor de melding.
	 */
	void setVerloopDatumTijd(Calendar verloopDatumTijd);
    /**
     * @return the aangepasteStraal
     */
    boolean isAangepasteStraal();
    /**
     * @param aangepasteStraal the aangepasteStraal to set
     */
    void setAangepasteStraal(boolean aangepasteStraal);
    /** 
     * Hiermee kan je de verloop datum vast zetten aan de hand van de categorie.
     * @throws OnvolledigeMeldingException 
     */
    void zetVerloopDatumVast() throws OnvolledigeMeldingException;
    /**
     * @return the burgerId
     */
    String getBurgerId();
    /**
     * @param burgerId the burgerId to set
     */
    void setBurgerId(String burgerId);
    /**
     * Met deze methode worden de aangepaste waardes uitgelzen 
     * op melding en doorgegeven aan de categorie.
     * @param dummy de dummy categorie met aangepaste waardes.
     */
    void laadAangepasteParamters(DummyCategorie dummy);

}
