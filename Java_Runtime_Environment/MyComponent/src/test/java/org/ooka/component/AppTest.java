package org.ooka.component;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */

public class AppTest {

    @Test
    public void testStopMethod() {
        App app = new App();
        int result = app.stop();
        assertEquals(0, result);
    }

    @Test
    public void testTestMethod() {
        App app = new App();
        int result = app.test();
        assertEquals(0, result);
    }
}
