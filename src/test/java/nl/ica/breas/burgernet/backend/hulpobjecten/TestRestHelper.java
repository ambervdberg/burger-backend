/**
 * 
 */
package nl.ica.breas.burgernet.backend.hulpobjecten;

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import net.sf.json.JSONObject;
import nl.ica.breas.burgernet.backend.model.Locatie;
import nl.ica.breas.burgernet.backend.hulpobjecten.RestHelper;

import org.junit.Before;
import org.junit.Test;

/**
 * @author bramiejo
 *
 */
public class TestRestHelper {
    @Before
    public void init() {
        java.util.logging.Logger.getLogger("org.apache.http.wire").setLevel(java.util.logging.Level.FINEST);
        java.util.logging.Logger.getLogger("org.apache.http.headers").setLevel(java.util.logging.Level.FINEST);
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
        System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "ERROR");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "ERROR");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.headers", "ERROR");
    }
    /**
     * Test method for {@link nl.ica.breas.burgernet.backend.hulpobjecten.RestHelper#doGet(java.lang.String, int)}.
     * @throws IOException 
     */
    @Test
    public void testDoGetStringInt() throws IOException {
            String result = RestHelper.doGet("http://maps.googleapis.com/maps/api/geocode/json?address" +
                    "=4+Harrie+van+den+berg+straat+Sint+Anthonis,+Netherlands&sensor=false", 10000);
            JSONObject jsonObject = JSONObject.fromObject(result);
            Locatie locatie = new Locatie(jsonObject.getJSONArray("results").
            getJSONObject(0).
            getJSONObject("geometry").
            getJSONObject("location").
            getDouble("lat"),
            jsonObject.getJSONArray("results").
            getJSONObject(0).
            getJSONObject("geometry").
            getJSONObject("location").
            getDouble("lng"));
            assertEquals(51.62396110, locatie.getLatitude(), 0.01);
            assertEquals(5.87987340, locatie.getLongitude(), 0.01);

    }
    /**
     * Test method for {@link nl.ica.breas.burgernet.backend.hulpobjecten.RestHelper#doGet(java.lang.String, int)}.
     */
    @Test(expected = IOException.class)
    public void testDoGetStringIntFout() throws IOException {
            RestHelper.doGet("http://maps.googleapis.com/maps/ap/geocode/json?address" +
                    "=4+Harrie+van+den+berg+straat+Sint+Anthonis,+Netherlands&sensor=false", 10000);
    }
    /**
     * Test method for {@link nl.ica.breas.burgernet.backend.hulpobjecten.RestHelper#doGet(java.lang.String, int)}.
     */
    @Test(expected = IOException.class)
    public void testDoGetFout() throws IOException {
            RestHelper.doGet("http://maps.googleapis.com/maps/ap/geocode/json?address" +
                    "=4+Harrie+van+den+berg+straat+Sint+Anthonis,+Netherlands&sensor=false");
    }
    /**
     * Test method for {@link nl.ica.breas.burgernet.backend.hulpobjecten.RestHelper#doGet(java.lang.String)}.
     * @throws IOException 
     */
    @Test
    public void testDoGetString() throws IOException {
            String result = RestHelper.doGet("http://maps.googleapis.com/maps/api/geocode/json?address" +
                    "=4+Harrie+van+den+berg+straat+Sint+Anthonis,+Netherlands&sensor=false");
            JSONObject jsonObject = JSONObject.fromObject(result);
            Locatie locatie = new Locatie(jsonObject.getJSONArray("results").
            getJSONObject(0).
            getJSONObject("geometry").
            getJSONObject("location").
            getDouble("lat"),
            jsonObject.getJSONArray("results").
            getJSONObject(0).
            getJSONObject("geometry").
            getJSONObject("location").
            getDouble("lng"));
            assertEquals(51.62396110, locatie.getLatitude(), 0.01);
            assertEquals(5.87987340, locatie.getLongitude(), 0.01);
    }
    
    @Test
    public void testInstantition() {
        try {
            Constructor<RestHelper> constructor;
            constructor = RestHelper.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
            assertEquals("nl.ica.breas.burgernet.backend.hulpobjecten.RestHelper", constructor.getName());
        } catch (NoSuchMethodException | SecurityException | InstantiationException 
                | IllegalAccessException | IllegalArgumentException | 
                InvocationTargetException e) {
            
        }

        
    }
}
