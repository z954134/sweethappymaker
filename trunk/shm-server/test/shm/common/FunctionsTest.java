package shm.common;

import junit.framework.TestCase;

public class FunctionsTest extends TestCase {

    public void testCss() throws Exception {
        String actual = Functions.css("globals");
        String expected = "<link rel='stylesheet' type='text/css' href='/common/css/globals.css'/>";
        assertEquals(expected, actual);
    }
    
    public void testJs() throws Exception {
        String actual = Functions.js("example");
        String expected = "<script type='text/javascript' src='/common/js/example.js' ></script>";
        assertEquals(expected, actual);
    }
    
    public void testJquery() throws Exception {
        String actual = Functions.jquery();
        String expected = "<script type='text/javascript' src='/common/js/jquery-1.3.2.min.js' ></script>";
        assertEquals(expected, actual);
    }
}
