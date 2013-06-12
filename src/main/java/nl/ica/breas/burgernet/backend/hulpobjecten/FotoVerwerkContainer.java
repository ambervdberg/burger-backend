package nl.ica.breas.burgernet.backend.hulpobjecten;

import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * De klasse FotoVerwerkContainer.
 * 
 * @author Amber en Samuel
 * @version 0.01
 * @since 10/12/2012
 */
public class FotoVerwerkContainer {
	
	/** De foto base64. */
	@JsonProperty
	@NotEmpty
	@Size(max = 2000000)
	private String fotoBase64;
	/** Bestands Type. */
	@JsonProperty
	@NotEmpty
	private String contentType;
	/** De melding id. */
	@JsonProperty
	@NotEmpty
	private String meldingID;
	/**
	 * Haalt de foto in base64 formaat op.
	 *
	 * @return the fotoBase64
	 */
	public final String getFotoBase64() {
		return fotoBase64;
	}

	/**
	 * Zet de foto in base64 formaat.
	 *
	 * @param fotoBase64 the fotoBase64 to set
	 */
	public final void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
	}

	/**
	 * Haalt de melding id op.
	 *
	 * @return the meldingID
	 */
	public final String getMeldingID() {
		return meldingID;
	}

	/**
	 * Zet de melding id.
	 *
	 * @param meldingID the meldingID to set
	 */
	public final void setMeldingID(String meldingID) {
		this.meldingID = meldingID;
	}

	/**
	 * Haalt het bestandsformaat op.
	 *
	 * @return the content type
	 */
	public final String getContentType() {
		return contentType;
	}

	/**
	 * Zet het bestandsformaat.
	 *
	 * @param contentType the new content type
	 */
	public final void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
