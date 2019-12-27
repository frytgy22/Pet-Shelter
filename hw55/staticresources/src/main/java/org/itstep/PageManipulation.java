package org.itstep;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * This method for page redirection
 */
public interface PageManipulation {
    static String redirect(String answer, String location) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>demo</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h4>" + answer + "</h4>\n" +
                "<script>\n" +
                "    setTimeout(() => window.location = 'http://localhost:8080" + location + "', 3000);\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>";
    }
}
