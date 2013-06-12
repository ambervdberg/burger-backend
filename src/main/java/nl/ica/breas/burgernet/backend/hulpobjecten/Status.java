package nl.ica.breas.burgernet.backend.hulpobjecten;
/**
 * 
 * @author Eric
 * @version 0.01
 * @since 28-11-2012
*/ 
public class Status {

	/**
	 * Hierin wordt de waarde true of false gestored.
	 */
	private final boolean bevestiging;
	
	/**
	 * Hierin word het id van het geplaatste bericht gezet.
	 */
	private final String id;
	
	/**
	 * constructor van status waarbij de waarde van status wordt geïnitialiseerd.
	 * @param bevestiging Hierin word de status van de bevestiging gegeven 
	 * true als het request geslaagd is, false als het request is gefaald.
	 * @param id de melding voor welke de status is.
	 */
	public Status(final boolean bevestiging, String id) {
		this.bevestiging = bevestiging;
		this.id = id;
	}
	
	/**
	 * De getter om de waarde van de boolean op te halen.
	 * @return De status van de verwerking als dit true is betekend het dat de bevestiging succesvol was.
	 */
	public final boolean isBevestiging() {
		return bevestiging;
	}
	/**
	 * De getter om het meldingId op te halen.
	 * @return De bij de status behorende meldingId
	 */
	public final String getId() {
		return id;
	}
}
