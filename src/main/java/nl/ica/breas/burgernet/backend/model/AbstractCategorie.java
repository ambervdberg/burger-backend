package nl.ica.breas.burgernet.backend.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.hibernate.validator.constraints.NotEmpty;

/**Dit is de abstracte klasse categorie.
 * 
 * @author Eric
 * @since 28-11-2012
 * @version 0.01
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
	@JsonSubTypes.Type(value = HerhalendeCategorie.class, name = "herhalendeCategorie"), 
	@JsonSubTypes.Type(value = NietHerhalendeCategorie.class, name = "nietHerhalendeCategorie"),
	@JsonSubTypes.Type(value = DummyCategorie.class, name = "DummyCategorie")
	})
public abstract class AbstractCategorie {
	
	/**
	 * Dit is de naam van de categorie.
	 */
	@JsonProperty
	@NotEmpty
	private String naam;
	/**
	 * Dit is de straal van de categorie.
	 */
	@JsonProperty
	private double straal;
	/**
	 * Dit is de time to live van de categorie.
	 */
	@JsonProperty
	private int timeToLive;
	/**
	 * met deze constructor kan een categorie worden maken.
	 * @param naam de naam van de categorie
	 */
	protected AbstractCategorie(String naam) {
		this.naam = naam;
	}
	/**
	 * De constructor om een categorie te maken.
	 */
	protected AbstractCategorie() {
		// deze constructor is nodig voor de jackson object omzetter.
	}
	/**Met deze methode kan je de naam van de categorie ophalen.
	 * 
	 * @return de naam van de categorie.
	 */
	public final String getNaam() {
		return naam;
	}
	/**Met deze methode kan de naam van de categorie worden gezet.
	 * 
	 * @param naam De nieuwe naam voor de categorie.
	 */
	public final void setNaam(String naam) {
		this.naam = naam;
	}
	/**Met deze methode kan je de straal van de categorie ophalen.
	 * 
	 * @return de straal van de categorie.
	 */
	public final double getStraal() {
		return straal;
	}
	/**Met deze methode kan de straal van de categorie worden gezet.
	 * 
	 * @param straal De nieuwe straal voor de categorie.
	 */
	public final void setStraal(double straal) {
		this.straal = straal;
	}
	/**Met deze methode kan je de time to live van de categorie ophalen.
	 * 
	 * @return de time to live van de categorie.
	 */
	public final int getTimeToLive() {
		return timeToLive;
	}
	/**Met deze methode kan de time to live van de categorie worden gezet.
	 * 
	 * @param timeToLive De nieuwe time to live voor de categorie.
	 */
	public final void setTimeToLive(int timeToLive) {
		this.timeToLive = timeToLive;
	}
}
