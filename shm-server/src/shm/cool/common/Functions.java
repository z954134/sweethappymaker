package shm.cool.common;

import org.slim3.util.StringUtil;


public final class Functions {

    private static final String BASE_URL = "/common/";
    private static final String CSS_BASE = BASE_URL + "css/";
    private static final String JS_BASE = BASE_URL + "js/";
    private static final String IMG_BASE = BASE_URL + "images/";
    private static final String JQUERY_NAME = "jquery-1.3.2.min";
    private static final String TOOLTIP_IMG = IMG_BASE + "help.ico";
    
    private Functions() {
    }
    
    public static String css(String cssName) {
        Utils.notEmpty(cssName);
        StringBuilder cssTag = new StringBuilder("<link ");
        cssTag.append("rel='stylesheet' type='text/css' href='");
        cssTag.append(CSS_BASE).append(cssName).append(".css'");
        cssTag.append("/>");
        return cssTag.toString();
    }

    
    public static String js(String jsName) {
        Utils.notEmpty(jsName);
        StringBuilder cssTag = new StringBuilder("<script ");
        cssTag.append("type='text/javascript' ");
        cssTag.append("src='").append(JS_BASE).append(jsName).append(".js' >");
        cssTag.append("</script>");
        return cssTag.toString();
    }
    
    public static String jquery() {
        return js(JQUERY_NAME);
    }
    
    public static String img(String imgName, String append) {
        Utils.notEmpty(imgName);
        StringBuilder imgTag = new StringBuilder("<img ");
        imgTag.append("src='").append(IMG_BASE).append(imgName).append("' ");
        imgTag.append(append);
        imgTag.append("/>");
        return imgTag.toString();
    }
    
}

