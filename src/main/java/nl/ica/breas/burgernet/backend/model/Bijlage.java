package nl.ica.breas.burgernet.backend.model;

import javax.validation.constraints.Size;

/**
 * @author Rick de Weerd
 * @version 0.1
 * @since 10/12/2012
 */

public class Bijlage {
	/**
	 * De inhoud van de bijlage.
	 */
    @Size(max = 2000000)
	private String inhoud;
	/**
	 * Bestandstype
	 */
	private String contentType;
	/**
	 * Dit is de constructor om een bijlage aan te maken.
	 */
	public Bijlage() {
		// de constructor wordt gebruikt voor de jackson object omzetter
	}
	/**
	 * Met deze constructor kan een bijlage worden aangemaakt.
	 * @param bijlage De doorgegeven
	 * @param contentType het bestandsformaat
	 */
	public Bijlage(String bijlage, String contentType) {
		this.inhoud = bijlage;
		this.contentType = contentType;
	}
	/**
	 * Hiermee wordt de bijlage opgehaald.
	 * @return de bijlage
	 */
	public final String getInhoud() {
		return inhoud;
	}
	/**
	 * Met deze methode kan de bijlage worden ingesteld.
	 * @param bijlage de bijlage die geset moet worden.
	 */
	public final void setInhoud(final String bijlage) {
		this.inhoud = bijlage;
	}
	
	/**
	 * Haalt het bestandsformaat op.
	 *
	 * @return het bestandsformaat
	 */
	public final String getContentType() {
		return contentType;
	}
	
	/**
	 * Zet het bestandsformaat.
	 *
	 * @param contentType het bestandsformaat
	 */
	public final void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
