package nl.ica.breas.burgernet.backend.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Dit is de categorie die herhaald kan worden.
 * @since 28-11-2012	
 * @author bramiejo
 * @version 0.1
 */
public class HerhalendeCategorie extends AbstractCopyableCategorie {
	/**
	 * Hier staat in hoevaak de meldingen met deze categorie herhaald moeten worden.
	 */
	@JsonProperty
	@NotEmpty
	private int aantalKeerHerhalen;
	/**
	 * Dit is tijd die tussen de verschillende herhalingen in zit.
	 */
	@JsonProperty
	@NotEmpty
	private int herhaalTijd;
    /**De constructor om een herhalende categorie te maken.
     * 
     * @param naam De naam van de nieuwe categorie.
     */
	public HerhalendeCategorie(String naam) {
		super(naam);
	}
	/**
	 * De constructor om een herhalende categorie te maken.
	 */
	public HerhalendeCategorie() {
		// deze constructor word gebruikt voor de jackson object omzetter.
		super();
	}
	/**
	 * @return de aantalKeerHerhalen.
	 */
	public final int getAantalKeerHerhalen() {
		return aantalKeerHerhalen;
	}
	/**
	 * @param aantalKeerHerhalen zet de aantalKeerHerhalen.
	 */
	public final void setAantalKeerHerhalen(int aantalKeerHerhalen) {
		this.aantalKeerHerhalen = aantalKeerHerhalen;
	}
	/**
	 * @return de herhaalTijd.
	 */
	public final int getHerhaalTijd() {
		return herhaalTijd;
	}
	/**
	 * @param herhaalTijd zet de herhaalTijd.
	 */
	public final void setHerhaalTijd(int herhaalTijd) {
		this.herhaalTijd = herhaalTijd;
	}
    /**
     * copieer de categorie naar een nieuw opject.
     * @return een copie van dit object
     */
    @Override
    public final AbstractCopyableCategorie copy() {
        HerhalendeCategorie nieuweCategorie = new HerhalendeCategorie();
        nieuweCategorie.setNaam(this.getNaam());
        nieuweCategorie.setStraal(this.getStraal());
        nieuweCategorie.setTimeToLive(this.getTimeToLive());
        nieuweCategorie.setAantalKeerHerhalen(this.getAantalKeerHerhalen());
        nieuweCategorie.setHerhaalTijd(this.getHerhaalTijd());
        return nieuweCategorie;
    }
}
