package org.itstep;

import javax.servlet.ServletContext;
import java.io.*;

public interface PageError {
    static String showPage404(ServletContext servletContext, String html) throws IOException {
        StringBuilder webXmlBuilder = new StringBuilder();
        String error = "";
        try (InputStream stream = servletContext.getResourceAsStream(html);
             InputStreamReader streamReader = new InputStreamReader(stream);
             BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                webXmlBuilder.append(line);
            }
            return error = webXmlBuilder.toString();
        }
    }
}
