package shm.controller;

import org.slim3.tester.ControllerTestCase;

public class IndexControllerTest extends ControllerTestCase {

    public void testRun() throws Exception {
        final String url = "http://cutenahappymaker.wordpress.com"; 
        
        super.application.setInitParameter(IndexController.HOME_URL_KEY, url);
        
        start("/");
        IndexController controller = getController();
        assertNotNull(controller);
        assertTrue(isRedirect());
        assertEquals(url, getNextPath());
    }
}
