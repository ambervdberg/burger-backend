package nl.ica.breas.burgernet.backend.model;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length.List;
	/**
	 * @since 03-12-2012
	 * @author bramiejo
	 * @version 0.1
	 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Adres {
	/**
	 * Dit is de straat van de gebruiker.
	 */
    @JsonProperty
    @NotEmpty
    @List({
        @Length(min = 2, message = "The field must be at least 2 characters"),
        @Length(max = 100, message = "The field must be less than 100 characters")
    })
	private String straat;
	/**
	 *  Dit is de plaats van de gebruiker.
	 */
    @JsonProperty
    @NotEmpty
    @List({
        @Length(min = 2, message = "The field must be at least 2 characters"),
        @Length(max = 100, message = "The field must be less than 100 characters")
    })
	private String plaats;
	/**
	 * dit is het huisnummer horende bij de straat. 
	 */
    @JsonProperty
    @NotNull
	private int huisnummer;
	/**
	 *  Dit is de postcode van de gebruiker.
	 */
    @JsonProperty
    @NotEmpty
    @List({
        @Length(min = 4, message = "The field must be at least 4 characters"),
        @Length(max = 10, message = "The field must be less than 10 characters")
    })
	private String postcode;
	/**
	 * dit is de constructor zonder parameters voor jackson.
	 */
	public Adres() {
		// deze constructor word gebruikt voor de jackson object omzetter.
	}
	/**
	 * Dit is de constructor van Adres met parameters.
	 * @param straat de straat waar de gebruiker woont.
	 * @param plaats de plaats waar deze straat ligt.
	 * @param huisnummer het huisnummer voor deze straat
	 * @param postcode waar de plaats en straat ligt.
	 */
	public Adres(String straat, String plaats, int huisnummer, String postcode) {
		this.straat = straat;
		this.plaats = plaats;
		this.huisnummer = huisnummer;
		this.postcode = postcode;
	}
	/** Hiermee get je de straat.
	 * @return de straat van de gebruiker.
	 */
	public final String getStraat() {
		return straat;
	}
	/** hiermee set je de straat.
	 * @param straat de straat van de gebruiker.
	 */
	public final void setStraat(String straat) {
		this.straat = straat;
	}
	/** Hiermee get je de plaats.
	 * @return the plaats van de gebruiker.
	 */
	public final String getPlaats() {
		return plaats;
	}
	/** Hiermee set je de straat.
	 * @param plaats de plaats van de gebruiker.
	 */
	public final void setPlaats(String plaats) {
		this.plaats = plaats;
	}
	/** Hiermee get je het huisnummer.
	 * @return het huisnummer van de gebruiker.
	 */
	public final int getHuisnummer() {
		return huisnummer;
	}
	/** Hiermee set je het huisnummer.
	 * @param huisnummer Het huisnummer van de gebruiker.
	 */
	public final void setHuisnummer(int huisnummer) {
		this.huisnummer = huisnummer;
	}
	/** Hiermee get je de postcode.
	 * @return the postcode van de gebruiker.
	 */
	public final String getPostcode() {
		return postcode;
	}
	/** Hiermee set je de postcode.
	 * @param postcode van de gebruiker.
	 */
	public final void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
}
