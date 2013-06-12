package nl.ica.breas.burgernet.backend.rest;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.ica.breas.burgernet.backend.controller.IBurgerController;
import nl.ica.breas.burgernet.backend.hulpobjecten.Status;
import nl.ica.breas.burgernet.backend.model.Burger;

/**
 * @author Rick de Weerd
 * @since 9/1/13
 *
 */
@Path("/burger/registreer")
@Produces(MediaType.APPLICATION_JSON)
public class BurgerResource {
    /**
     * Hier wordt de IBurgerController gedeclareerd.
     */
    private IBurgerController ibc;
    /**
     * De constructor, hierin wordt de IBurgerController geïnitialiseerd.
     * @param ibc De doorgegeven IBurgerController wordt lokaal opgeslagen.
     */
    public BurgerResource(IBurgerController ibc) {
        this.ibc = ibc;
    }
    /**
     * De ontvangen burger wordt doorgegeven aan de BurgerController.
     * Deze registreert vervolgens de burger.
     * 
     * @param burger De burger die is doorgegeven door de client.
     * @return Er wordt een boolean terug gegeven.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public final Status registreer(@Valid final Burger burger) {
        return ibc.registreer(burger);
    }
}
