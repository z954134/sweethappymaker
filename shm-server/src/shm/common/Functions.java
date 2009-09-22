/*
 * Copyright 2004-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package shm.common;



public final class Functions {

    private static final String BASE_URL = "/common/";
    private static final String CSS_BASE = BASE_URL + "css/";
    private static final String JS_BASE = BASE_URL + "js/";
    private static final String JQUERY_NAME = "jquery-1.3.2.min";
    private Functions() {
    }
    
    public static String css(String cssName) {
        Utils.notEmpty(cssName);
        StringBuilder cssTag = new StringBuilder();
        cssTag.append("<link rel='stylesheet' type='text/css' href='");
        cssTag.append(CSS_BASE).append(cssName).append(".css'/>");
        return cssTag.toString();
    }

    
    public static String js(String jsName) {
        Utils.notEmpty(jsName);
        StringBuilder cssTag = new StringBuilder();
        cssTag.append("<script type='text/javascript' src='");
        cssTag.append(JS_BASE).append(jsName).append(".js' ></script>");
        return cssTag.toString();
    }
    
    public static String jquery() {
        return js(JQUERY_NAME);
    }

}