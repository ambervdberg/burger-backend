package nl.ica.breas.burgernet.backend.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length.List;

/**
 * @since 03-12-2012
 * @author bramiejo
 * @version 0.01
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Burger {

	/**
	 * Naam van de gebruiker.
	 */
    @JsonProperty
    @NotEmpty
    @List({
        @Length(min = 1, message = "The field must be at least 1 characters"),
        @Length(max = 35, message = "The field must be less than 50 characters")
    })
	private String voornaam;
	/**
	 * Achternaam van de gebruiker.
	 */
    @JsonProperty
    @NotEmpty
    @List({
        @Length(min = 2, message = "The field must be at least 2 characters"),
        @Length(max = 50, message = "The field must be less than 50 characters")
    })
	private String achternaam;
	
	/**
	 * Dit is de locatie waarop gebruiker meldingen wil ontvangen.
	 */
	private Locatie locatie;
	/**
	 * Het adres waar de gebruiker woont.
	 */
    @JsonProperty
    @NotNull
    @Valid
	private Adres adres;
	/**
	 * Het emailadres van de gebruiker.
	 */
       @JsonProperty
       @NotEmpty
       @List({
            @Length(min = 10, message = "The field must be at least 10 characters"),
            @Length(max = 100, message = "The field must be less than 100 characters")
        })
	private String emailAdres;
	/**
	 * De constructor voor een burger zonder parameters. 
	 */
	public Burger() {
		// deze constructor word gebruikt voor de jackson object omzetter.
	}
	/**
	 * dit is de constructor voor een burger met parameters.
	 * @param voornaam de voor naam van de gebruikerr.
	 * @param achternaam de achternaam van de gebruiker.
	 * @param locatie de ontvangstlocatie van de gebruiker.
	 * @param adres de adres van de gebruiker.
	 * @param emailAdres het emailadres van de gebruiker.
	 */
	public Burger(String voornaam, String achternaam, 
			Locatie locatie, Adres adres, String emailAdres) {
		this.voornaam = voornaam;
		this.achternaam = achternaam;
		this.locatie = locatie;
		this.adres = adres;
		this.emailAdres = emailAdres;
	}
	/** Hiermee kan de voornaam van de gebruiker worden gekregen.
	 * @return the voornaam van de gebruiker
	 */
	public final String getVoornaam() {
		return voornaam;
	}
	/** Hiermee kun je de voornaam setten van de gebruiker.
	 * @param voornaam van de gebruiker om te setten.
	 */
	public final void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	/** Hiermee kan de achternaam van de gebruiker worden gekregen.
	 * @return the achternaam van de gebruiker
	 */
	public final String getAchternaam() {
		return achternaam;
	}
	/** Hiermee kun je de achternaam setten van de gebruiker.
	 * @param achternaam van de gebruiker om te setten.
	 */
	public final void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}
	/** Hiermee kan de locatie van de gebruiker worden gekregen.
	 * @return the locatie van de gebruiker
	 */
	public final Locatie getLocatie() {
		return locatie;
	}
	/** Hiermee kan de locatie van de gebruiker worden geset.
	 * @param locatie van de gebruiker om te setten.
	 */
	public final void setLocatie(Locatie locatie) {
		this.locatie = locatie;
	}
	/** Hiermee kan het adres van de gebruiker worden gekregen.
	 * @return the adres van de gebruiker.
	 */
	public final Adres getAdres() {
		return adres;
	}
	/** Hiermee kan het adres van de gebruiker worden geset.
	 * @param adres van de gebruiker om te setten.
	 */
	public final void setAdres(Adres adres) {
		this.adres = adres;
	}
    /**
     * @return the emailAdres
     */
    public final String getEmailAdres() {
        return emailAdres;
    }
    /**
     * @param emailAdres the emailAdres to set
     */
    public final void setEmailAdres(String emailAdres) {
        this.emailAdres = emailAdres;
    }
}
