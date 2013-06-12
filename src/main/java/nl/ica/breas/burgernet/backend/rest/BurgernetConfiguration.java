package nl.ica.breas.burgernet.backend.rest;

import javax.validation.constraints.NotNull;

import nl.ica.breas.burgernet.backend.model.AbstractCategorie;
import org.codehaus.jackson.annotate.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

/**
 * De klasse BurgernetConfiguration.
 *
 * @author Rick de Weerd
 * @since 26/11/2012
 * @version 0.1
 */

public class BurgernetConfiguration extends Configuration {
    /**
	 * Hierin wordt de array met Categorieën opgeslagen.
	 */
	@JsonProperty
	@NotNull
	private AbstractCategorie[] categorieLijst;
	/** Hierin wordt de locatie van de DatabaseAdapter opgeslagen. */
	private String databaseAdapter;
	/** Hierin wordt de naam van de AdresNaarLocatieAdapter opgeslagen. */
    private String adresNaarLocatieAdapter;
	/** Hierin wordt de locatie van de FotoController opgeslagen. */
	private String fotoController;
	/** Hierin wordt de locatie van de HaalMeldingOpController opgeslagen. */
	private String haalMeldingOpController;
	/** Hierin wordt de locatie van de MeldingController opgeslagen.*/
	private String meldingController;
	/** Hierin wordt de locatie van de BurgerController opgeslagen. */
	private String burgerController;
	/** Hierin wordt de locatie van de PushServer opgeslagen. */
	private String pushServer;
	/** ipAdres voor mongoDB. */
	private String databaseIpAdres;
	/** ipAdres voor mongoDB. */
    private int databasePoort;
    /** De database inlog naam. */
    private String databaseInlogNaam;
    /** Het database inlog wachtwoord. */
    private String databaseInlogWachtwoord;
    /**
	 * Met deze methode kan je de array ophalen.
	 * @return er wordt een array met Categorieën teruggegeven.
	 */
	public final AbstractCategorie[] getCategorieLijst() {
		return categorieLijst.clone();
	}
	
	/**
	 * Met deze methode kan de array met Categorieën worden ingesteld..
	 * @param categorieLijst de nieuwe array Categorieën.
	 */
	public final void setCategorieLijst(AbstractCategorie[] categorieLijst) {
		this.categorieLijst = categorieLijst;
	}
	/**
	 * Hiermee kan de databaseAdapter worden verkregen.
	 * @return een String met de locatie van de databaseAdapter voor reflectie.
	 */
	public final String getDatabaseAdapter() {
		return databaseAdapter;
	}
	/**
	 * Met deze methode kan de string voor de databaseAdapter worden ingesteld.
	 * @param databaseAdapter de nieuwe String voor de databaseAdapter.
	 */
	public final void setDatabaseAdapter(final String databaseAdapter) {
		this.databaseAdapter = databaseAdapter;
	}
	/**
     * @return the adresNaarLocatieAdapter
     */
    public final String getAdresNaarLocatieAdapter() {
        return adresNaarLocatieAdapter;
    }

    /**
     * @param adresNaarLocatieAdapter the adresNaarLocatieAdapter to set
     */
    public final void setAdresNaarLocatieAdapter(String adresNaarLocatieAdapter) {
        this.adresNaarLocatieAdapter = adresNaarLocatieAdapter;
    }

    /**
	 * Hiermee kan de locatie van de FotoController worden opgevraagd.
	 * @return de locatie van de FotoController.
	 */
	public final String getFotoController() {
		return fotoController;
	}
	/**
	 * Met deze methode kan de String voor de locatie van de FotoController worden ingesteld.
	 * @param fotoController De locatie van de FotoController.
	 */
	public final void setFotoController(final String fotoController) {
		this.fotoController = fotoController;
	}
	/**
	 * Hiermee kan de locatie van de HaalMeldingOpCont worden opgevraagd.
	 * @return de locatie van de HaalmeldingOpController.
	 */
	public final String getHaalMeldingOpController() {
		return haalMeldingOpController;
	}
	/**
	 * Met deze methode kan de String voor de locatie van de HaalMeldingOpController worden ingesteld.
	 * @param haalMeldingOpController De locatie van de HaalMeldingOpController.
	 */
	public final void setHaalMeldingOpController(final String haalMeldingOpController) {
		this.haalMeldingOpController = haalMeldingOpController;
	}
	/**
	 * Hiermee kan de locatie van de MeldingController worden opgevraagd.
	 * @return de locatie van de MeldingController.
	 */
	public final String getMeldingController() {
		return meldingController;
	}
	/**
	 * Met deze methode kan de String voor de locatie van de MeldingController worden ingesteld.
	 * @param meldingController De locatie van de MeldingController.
	 */
	public final void setMeldingController(final String meldingController) {
		this.meldingController = meldingController;
	}
	/**
	 * Hiermee kan de locatie van de PushServer worden opgevraagd.
	 * @return de locatie van de PushServer.
	 */	
	public final String getPushServer() {
		return pushServer;
	}
	/**
	 * Met deze methode kan de String voor de locatie van de PushServer worden ingesteld.
	 * @param pushServer De locatie van de PushServer.
	 */
	public final void setPushServer(final String pushServer) {
		this.pushServer = pushServer;
	}

	/**
	 * Hiermee kun je de ipadres krijgen voor de mongoDB server;.
	 *
	 * @return de ipadres van de mongoDB server.
	 */
	public final String getDatabaseIpAdres() {
	        return databaseIpAdres;
	}
	/**
	 * hiermee kun je de ipadres voor de mongoDB setten.
	 * @param ipAdres van de mongoDb server.
	 */
	public final void setDatabaseIpAdres(final String ipAdres) {
	        this.databaseIpAdres = ipAdres;
	}

    /**
     * Hiermee kun je de poort krijgen voor de mongoDB server;.
     *
     * @return de poort van de mongoDB server.
     */
    public final int getDatabasePoort() {
        return databasePoort;
    }
    /**
     * hiermee kun je de poort voor de mongoDB setten.
     * @param databasePoort van de mongoDb server.
     */
    public final void setDatabasePoort(final int databasePoort) {
        this.databasePoort = databasePoort;
    }

    /** Hiermee kan de BurgerController verkregen worden.
     * @return the burgerController
     */
    public final String getBurgerController() {
        return burgerController;
    }

    /** Hiermee kan de BurgerController worden geset.
     * @param burgerController the burgerController to set
     */
    public final void setBurgerController(final String burgerController) {
        this.burgerController = burgerController;
    }

    /**
     * Haalt de database inlognaam op.
     * @return the databaseNaam
     */
    public final String getDatabaseInlogNaam() {
        return databaseInlogNaam;
    }

    /**
     * Zet de inlognaam van de database.
     *
     * @param databaseInlogNaam the new database naam
     */
    public final void setDatabaseInlogNaam(final String databaseInlogNaam) {
        this.databaseInlogNaam = databaseInlogNaam;
    }

    /**
     * Haalt het wachtwoord van de database op.
     * @return the databaseInlogWachtwoord
     */
    public final String getDatabaseInlogWachtwoord() {
        return databaseInlogWachtwoord;
    }

    /**
     * Zet het wachtwoord van de database.
     * @param databaseInlogWachtwoord the databaseInlogWachtwoord to set
     */
    public final void setDatabaseInlogWachtwoord(final String databaseInlogWachtwoord) {
        this.databaseInlogWachtwoord = databaseInlogWachtwoord;
    }
}