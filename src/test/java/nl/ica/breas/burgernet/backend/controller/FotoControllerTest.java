/**
 * 
 */
package nl.ica.breas.burgernet.backend.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.UnknownHostException;

import nl.ica.breas.burgernet.backend.exceptions.PersistenceConnectionException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceDuplicateKeyException;
import nl.ica.breas.burgernet.backend.exceptions.PersistenceNotFoundException;
import nl.ica.breas.burgernet.backend.hulpobjecten.FotoVerwerkContainer;
import nl.ica.breas.burgernet.backend.hulpobjecten.Status;
import nl.ica.breas.burgernet.backend.model.IMelding;
import nl.ica.breas.burgernet.backend.model.Melding;
import nl.ica.breas.burgernet.backend.persistence.AbstractPersistenceAdapter;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

/**
 * De Klasse FotoControllerTest.
 *
 * @author Amber en Eric
 * @version 0.1
 * @since 11/12/2012
 */
public class FotoControllerTest {

	/** De adapter. */
	private AbstractPersistenceAdapter adapter = mock(AbstractPersistenceAdapter.class);
	/** De FotoController. */
	private FotoController fc = new FotoController(adapter);
	/** De melding. */
	private Melding melding = new Melding();
	/** De FotoVerwerkContainer. */
	private FotoVerwerkContainer f = new FotoVerwerkContainer();
	/** Het argument. */
	private ArgumentCaptor<Melding> argument = ArgumentCaptor.forClass(Melding.class);
	/** Het argument2. */
	private ArgumentCaptor<String> argument2 = ArgumentCaptor.forClass(String.class);

	/** De LOGGER. */
	private static final Logger LOGGER = Logger.getLogger("InfoLogging");

	/**
	 * Instantieert een niewe FotoController test.
	 *
	 * @throws UnknownHostException de unknown host exception
	 */
	public FotoControllerTest() throws UnknownHostException {
		//Nodig voor het gooien van de exception
	}

	/**
	 * Before.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws PersistenceConnectionException de persistence connection exception
	 * @throws PersistenceNotFoundException 
	 * @throws IllegalArgumentException 
	 */
	@Before
	public void before() throws IOException, PersistenceConnectionException, IllegalArgumentException, PersistenceNotFoundException {
		when(adapter.haalMeldingOp(any(String.class))).thenReturn(melding);
		when(adapter.wijzigMelding(any(String.class), any(IMelding.class))).thenReturn(true);
		f.setFotoBase64("fotobase64formaat");
		f.setMeldingID("MeldingIdbla");
		f.setContentType(".jpeg");
	}

	/**
	 * Test of de foto verwerkt wordt. Er moet een Status worden teruggeven.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws PersistenceConnectionException the persistence connection exception
	 */
	@Test
	public void testVerwerkFoto() throws IOException, PersistenceConnectionException {
		assertEquals(true, fc.verwerkFoto(f) instanceof Status);
	}

	/**
	 * Test of de foto goed verwerkt wordt.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws PersistenceConnectionException the persistence connection exception
	 * @throws PersistenceDuplicateKeyException the persistence duplicate key exception
	 */
	@Test
	public void testVerwerkFoto2() 
			throws IOException, PersistenceConnectionException, PersistenceDuplicateKeyException {
		fc.verwerkFoto(f);
		verify(adapter).wijzigMelding(any(String.class), argument.capture());
		assertEquals("fotobase64formaat", argument.getValue().getBijlage().getInhoud());
	}

	/**
	 * Test of de meldingID goed is verwerkt.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws PersistenceConnectionException the persistence connection exception
	 */
	@Test
	public void testVerwerkFoto3() 
			throws IOException, PersistenceConnectionException {
		assertEquals("MeldingIdbla", fc.verwerkFoto(f).getId());
	}

	/**
	 * Test of het bestnadsformaat goed is verwerkt.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws PersistenceConnectionException the persistence connection exception
	 */
	@Test
	public void testVerwerkFoto4() 
			throws IOException, PersistenceConnectionException {
		fc.verwerkFoto(f);
		verify(adapter).wijzigMelding(any(String.class), argument.capture());
		assertEquals(".jpeg", argument.getValue().getBijlage().getContentType());
	}

	/**
	 * Test of de juiste melding wordt gebruikt.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws PersistenceConnectionException the persistence connection exception
	 */
	@Test
	public void testVerwerkFoto5() 
			throws IOException, PersistenceConnectionException {
		fc.verwerkFoto(f);
		verify(adapter).wijzigMelding(argument2.capture(), any(Melding.class));
		assertEquals("MeldingIdbla", argument2.getValue());
	}

	/**
	 * Met deze test word de constructor van controller getest.
	 */
	@Test
	public void testController() {
		assertEquals(true, fc instanceof FotoController);
	}

	/**
	 * Test een IOException.
	 */
	@Test
	public void testIOException() {
		try {
			when(adapter.haalMeldingOp(any(String.class))).thenReturn(melding);
			when(adapter.wijzigMelding(any(String.class), any(IMelding.class))).thenThrow(new IOException("Test"));
		} catch (Exception e) {
			LOGGER.trace(e);
		}
		assertEquals(false, fc.verwerkFoto(f).isBevestiging());
	}
	/**
	 * Test een PersistenceInternalException.
	 * @throws PersistenceConnectionException 
	 * @throws IOException 
	 * @throws PersistenceNotFoundException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testPersistenceConnectionException() throws IOException, PersistenceConnectionException, IllegalArgumentException, PersistenceNotFoundException {
		when(adapter.haalMeldingOp(any(String.class))).thenThrow(new PersistenceConnectionException());
		assertEquals(false, fc.verwerkFoto(f).isBevestiging());
	}
}
