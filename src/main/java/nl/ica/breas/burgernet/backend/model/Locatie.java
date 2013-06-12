package nl.ica.breas.burgernet.backend.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Rick de Weerd, Eric Bonestroo , Samuel iets
 * @since 29/11/2012
 * @version 0.1
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Locatie {
    
    /**
     * Hierin wordt de doorgegeven longitude lokaal opgeslagen.
     */
    @JsonProperty
    @NotEmpty
    private double longitude;
	/**
	 * Hierin wordt de doorgegeven latitude lokaal opgeslagen.
	 */
	@JsonProperty 
	@NotEmpty
	private double latitude;
	
	/**
	 * Dit is de constructor om een locatie aan te maken.
	 */
	public Locatie() {
		//dit is een constructor
	}
	
	/**
	 * Met deze constructor kan een locatie worden aangemaakt.
	 * @param latitude de breedtegraad van de doorgegeven locatie.
	 * @param longitude de lengtegraad van de doorgegeven locatie.
	 */
	public Locatie(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
    /**
     * Met deze methode kan je de breedtegraad van de locatie ophalen.
     * @return de breedtegraad van de doorgegeven locatie.
     */
    public final double getLongitude() {
        return longitude;
    }
    
    /**
     * Met deze methode kan de breedtegraad van de locatie worden ingesteld.
     * @param longitude de breedtegraad van de doorgegeven locatie
     */
    public final void setLongitude(double longitude) {
        this.longitude = longitude;
    }   	
	/**
	 * Met deze methode kan je de lengtegraad van de locatie ophalen.
	 * @return de lengtegraad van de doorgegeven locatie.
	 */
	public final double getLatitude() {
		return latitude;
	}

	/**
	 * Met deze methode kan de lengtegraad van de locatie worden ingesteld.
	 * @param latitude de lengtegraad van de doorgegeven locatie
	 */
	public final void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}
