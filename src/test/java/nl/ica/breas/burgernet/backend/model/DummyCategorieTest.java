/**
 * 
 */
package nl.ica.breas.burgernet.backend.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Samuel van Oostveen
 *
 */
public class DummyCategorieTest {

    /**
     * Test method for {@link nl.ica.breas.burgernet.backend.model.DummyCategorie#copy()}.
     */
    @Test
    public void testCopy() {
        DummyCategorie categorie = new DummyCategorie();
        assertEquals(true, categorie instanceof DummyCategorie);
    }
}
