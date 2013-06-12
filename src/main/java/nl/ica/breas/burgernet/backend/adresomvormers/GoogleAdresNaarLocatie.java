/**
 * 
 */
package nl.ica.breas.burgernet.backend.adresomvormers;

import java.io.IOException;

import net.sf.json.JSONObject;
import nl.ica.breas.burgernet.backend.exceptions.VerkeerdeAdresExceptie;
import nl.ica.breas.burgernet.backend.hulpobjecten.RestHelper;
import nl.ica.breas.burgernet.backend.model.Adres;
import nl.ica.breas.burgernet.backend.model.Locatie;

/**
 * @author Samuel van Oostveen
 *
 */
public class GoogleAdresNaarLocatie extends AbstractAdresNaarLocatieAdapter {

    /** Maximale lengte van de get methode. */
    private static final int MAXLENGTH = 10000;
    
    /** Dit is de url voor google te benaderen. */ 
    private String url = "http://maps.googleapis.com/maps/api/geocode/json?address";
    /**
     * Dit wordt gebruikt om een adres naar een locatie om te vormen.
     * @param adres het adres wat omgevormt moet worden. 
     * @return een locatie wat bij het adres hoort.
     * @throws VerkeerdeAdresExceptie 
     * @throws IOException 
     */
    public final Locatie adresToLocatie(Adres adres) throws IOException, VerkeerdeAdresExceptie {
        String result = doCallGoogle(adres);
        JSONObject jsonObject = JSONObject.fromObject(result);
        return convertGoogleNaarLocatie(jsonObject);
    }  
    /**
     * Deze methode roept google aan en returned de uitkomst.
     * @param adres het adres van google.
     * @return de waardes die google geeft.
     * @throws IOException deze wordt gegooid als RestHelper faalt.
     */
    private String doCallGoogle(Adres adres) throws IOException {
        String result;
        result = RestHelper.doGet(url 
                + "=" + adres.getHuisnummer() + "+"
                + adres.getStraat().replaceAll(" ", "\\+") + "+"
                + adres.getPlaats().replaceAll(" ", "\\+")
                + ",+Netherlands"               
                + "&sensor=false", MAXLENGTH);
        return result;
    }

    /**
     * Deze methode wordt gebruikt om de google json om te vormen naar een locatie.
     * @param jsonObject het jsonobject van google.
     * @return een locatie.
     * @throws VerkeerdeAdresExceptie 
     */
    private Locatie convertGoogleNaarLocatie(JSONObject jsonObject) throws VerkeerdeAdresExceptie  {
        if (!jsonObject.toString().equalsIgnoreCase("{\"results\":[],\"status\":\"ZERO_RESULTS\"}")
                && !jsonObject.toString().equalsIgnoreCase("{\"results\":[],\"status\":\"OVER_QUERY_LIMIT\"}")) {
            double lat = googleToLatitude(jsonObject);
            double lon = googleToLongitude(jsonObject);
            return new Locatie(lat, lon);

        } else {
            throw new VerkeerdeAdresExceptie("Dit is geen bestaand adres");
        }
    }
    /** Deze methode vormt het jsonObject om naar een longitude Coordinaat.
     * @param jsonObject het JsonObject wat omgevormd moet worden naar een longitude.
     * @return een longitude coordinaat.
     */
    private double googleToLongitude(JSONObject jsonObject) {
        double lon = jsonObject.getJSONArray("results").
                getJSONObject(0).
                getJSONObject("geometry").
                getJSONObject("location").
                getDouble("lng");
        return lon;
    }
    /** Deze methode vormt het jsonObject om naar een latitude Coordinaat.
     * @param jsonObject het JsonObject wat omgevormd moet worden naar een latitude.
     * @return een latitude coordinaat.
     */
    private double googleToLatitude(JSONObject jsonObject) {
        double lat = jsonObject.getJSONArray("results").
                getJSONObject(0).
                getJSONObject("geometry").
                getJSONObject("location").
                getDouble("lat");
        return lat;
    }
    /**
     * @return the uRL
     */
    public final String getURL() {
        return url;
    }
    /**
     * @param uRL the uRL to set
     */
    public final void setURL(final String uRL) {
        url = uRL;
    }
}