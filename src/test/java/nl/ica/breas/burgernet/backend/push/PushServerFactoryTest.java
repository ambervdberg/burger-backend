/**
 * 
 */
package nl.ica.breas.burgernet.backend.push;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Samuel van Oostveen
 *
 */
public class PushServerFactoryTest {
	
	private final String pushServerVoorbeeld = "nl.ica.breas.burgernet.backend.push.PushServerVoorbeeld";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.push.PushServerFactory#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		PushServerFactory pushServerFactory = PushServerFactory.getInstance();
		assertEquals(true, pushServerFactory instanceof PushServerFactory);
		assertEquals(pushServerFactory, PushServerFactory.getInstance());
	}

	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.push.PushServerFactory#createPushServerAdapter()}.
	 */
	@Test
	public void testCreatePushServerAdapter() {
		PushServerFactory.setsPushServer(pushServerVoorbeeld);
		IPushServer pushServerAdapter = PushServerFactory.getInstance().createPushServerAdapter();
		assertEquals(true, pushServerAdapter instanceof IPushServer);
	}
	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.push.PushServerFactory#createPushServerAdapter()}.
	 */
	@Test
	public void testCreatePushServerAdapterClassNotFoundException() {
		PushServerFactory.setsPushServer(pushServerVoorbeeld + "bla");
		IPushServer pushServerAdapter = PushServerFactory.getInstance().createPushServerAdapter();
		assertEquals(null, pushServerAdapter);
	}
	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.push.PushServerFactory#createPushServerAdapter()}.
	 */
	@Test
	public void testCreatePushServerAdapterIllegalAccessException() {
		PushServerFactory.setsPushServer("nl.ica.breas.burgernet.backend.model.Categorie");
		IPushServer pushServerAdapter = PushServerFactory.getInstance().createPushServerAdapter();
		assertEquals(null, pushServerAdapter);
	}
	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.push.PushServerFactory#createPushServerAdapter()}.
	 */
	@Test
	public void testCreatePushServerAdapterInstantiationException() {
		PushServerFactory.setsPushServer("nl.ica.breas.burgernet.backend.model.IMelding");
		IPushServer pushServerAdapter = PushServerFactory.getInstance().createPushServerAdapter();
		assertEquals(null, pushServerAdapter);
	}
	
	/**
	 * Test method for {@link nl.ica.breas.burgernet.backend.push.PushServerFactory#getsPushServer()}.
	 */
	@Test
	public void testSetGetPushServer() {
		PushServerFactory.setsPushServer(pushServerVoorbeeld);
		assertEquals(pushServerVoorbeeld, PushServerFactory.getsPushServer());
	}
}
