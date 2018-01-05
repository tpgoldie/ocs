package com.tpg.ocs.web.security;

import com.tpg.ocs.context.WebSecurityConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@Component
public class OcsBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    private static Logger LOGGER = LoggerFactory.getLogger(OcsBasicAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
            throws IOException, ServletException {

        response.addHeader("WWW-Authenticate", String.format("Basic realm=%s", getRealmName()));

        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {

            String key = names.nextElement();

            String value = request.getHeader(key);

            LOGGER.debug("Http header - {} = {}", key, value);

            response.addHeader(key, value);
        }

        response.setStatus(SC_UNAUTHORIZED);

        PrintWriter writer = response.getWriter();

        String msg = String.format("HTTP Status %d - %s", SC_UNAUTHORIZED, authEx.getMessage());

        LOGGER.info(msg);

        writer.println(msg);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        setRealmName(WebSecurityConfig.REALM_NAME);

        super.afterPropertiesSet();
    }
}
