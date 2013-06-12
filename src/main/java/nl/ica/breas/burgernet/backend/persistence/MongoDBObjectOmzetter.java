package nl.ica.breas.burgernet.backend.persistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nl.ica.breas.burgernet.backend.exceptions.PersistenceInternalException;
import nl.ica.breas.burgernet.backend.model.Locatie;

import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoInternalException;
import com.mongodb.util.JSON;
/** Deze wordt gebruikt om objecten om te zetten.
 * @author Rick de Weerd
 * @since 10/12/12
 * @version 0.1
 */
public final class MongoDBObjectOmzetter {
    /** Dit getal is de omtrek van de aarde gebruikend voor de locatie. */
    private static final double OMTREKVANAARDE = 6378.137;
	 /** Hier wordt de instantie opgeslagen. */
	private static MongoDBObjectOmzetter instance = null;
	/** Dit is de private construtor voor de singleton initialisator. */
	private MongoDBObjectOmzetter() {
		//lege private constructor voor singleton.
	}
	/**
	 * De initialisator van MongoDBCrudBurger.
	 * @return De instantie van de MongoDBCrudBurger.
	 */
	public static MongoDBObjectOmzetter getInstance() {
		synchronized (MongoDBObjectOmzetter.class) {
			if (instance == null) {
				instance = new MongoDBObjectOmzetter();
			}
		}
		return instance;
	}
	/**
	 * Vormt een DBObject om naar een Object.
	 * @param dBObject het DBObject
	 * @param klasse de klasse van het Object (Object.class)
	 * @return het Object
	 * @throws IOException Er was een fout bij het omvormen.
	 *         (Signals that an I/O exception has occurred).
	 */
	public Object dBObjectNaarObject(final DBObject dBObject, final Class<?> klasse) throws IOException {
		if (dBObject == null) {
			return null;
		}
		return new ObjectMapper().readValue(dBObject.toString(), klasse);
	}
	/**
	 * Vormt een Object om naar een DBObject.
	 * @param object het object
	 * @return het DBObject
	 * @throws IOException Er was een fout bij het omvormen.
	 * 		   (Signals that an I/O exception has occurred).
	 */
	public DBObject objectNaarDBObject(final Object object) throws IOException {
		if (object == null) {
			return null;
		}
		return (DBObject) JSON.parse(new ObjectMapper().writeValueAsString(object));
	}
	/**
	 * dit zet de inhoud van de database cursor over naar een lijst met burgers
	 * @param cursor de cursor waarmee iets moet gebeuren
	 * @param klasse de soort welke gecursored moet worden.
	 * @return de lijst met burgers
	 * @throws PersistenceInternalException Wanneer MongoDB een InternalException geeft.
	 * @throws IOException Fout bij het omvormen van of naar JSON.
	 */
	public List<Object> cursorNaarList(final DBCursor cursor, final Class<?> klasse) throws PersistenceInternalException, IOException {
		ArrayList<Object> list = new ArrayList<Object>();
		try {
			while (cursor.hasNext()) {
				list.add(dBObjectNaarObject(cursor.next(), klasse));
			}
		} catch (MongoInternalException e) {
			throw new PersistenceInternalException(e);
		}
		return list;
	}
    /**
     * Hiermee word de arraylist gemaakt die nodig is voor de mongodb centersphere query ding.
     * @param locatie de locatie vanwaar de straal moet worden gedaan ding.
     * @param straal de straal van het gebied ding.
     * @return het array ding.
     */
    public List<Object> centerSphereArray(Locatie locatie, double straal) {
        List<Object> outerList = new ArrayList<Object>();
        List<Double> innerList = new ArrayList<Double>();
        innerList.add(locatie.getLongitude());
        innerList.add(locatie.getLatitude());
        outerList.add(innerList);
        outerList.add(straal / OMTREKVANAARDE);
        return outerList;
    }
}
