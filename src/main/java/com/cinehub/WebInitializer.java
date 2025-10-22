package com.cinehub;

import com.cinehub.config.AppConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * This class is automatically detected by the Servlet 3.0+ container (Tomcat)
 * and is responsible for configuring the DispatcherServlet.
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // 1. Specifies the configuration classes for the root application context (non-web beans).
    // For simplicity, we keep everything in AppConfig for now.
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    // 2. Specifies the configuration classes for the DispatcherServlet (web beans).
    // This is where we point to our main AppConfig.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { AppConfig.class };
    }

    // 3. Specifies the URL pattern that the DispatcherServlet should handle.
    // "/" means it handles ALL incoming requests.
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}