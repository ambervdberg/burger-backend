package nl.ica.breas.burgernet.backend.rest;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.core.util.Base64;

//import com.mongodb.util.Base64;

//import org.apache.commons.codec.binary.Base64;

import nl.ica.breas.burgernet.backend.controller.IFotoController;
import nl.ica.breas.burgernet.backend.hulpobjecten.FotoVerwerkContainer;
import nl.ica.breas.burgernet.backend.hulpobjecten.Status;

/**
 * De klasse FotoResource.
 * 
 * @author Amber en Samuel
 * @version 0.01
 * @since 10/12/2012
 */
@Path("/melding/voegfototoe")
@Produces(MediaType.APPLICATION_JSON)
public class FotoResource {
	/**
	 * Hier wordt de IFotoController gedeclareerd.
	 */
	private IFotoController ifc;
	/**
	 * De constructor, hierin wordt de IfotoController geïnitialiseerd.
	 * @param ifc De doorgegeven IFotoController wordt lokaal opgeslagen.
	 */
	public FotoResource(IFotoController ifc) {
		this.ifc = ifc;
	}
	
	/**
	 * Voeg foto toe.
	 * @return status een false status wanneer het faalt.
	 * @param foto the foto
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public final Status voegFotoToe(@Valid FotoVerwerkContainer foto) {
		if (Base64.isBase64(foto.getFotoBase64())) {
			return ifc.verwerkFoto(foto);	
		} else {
			return new Status(false, null);
		}
	}
}
