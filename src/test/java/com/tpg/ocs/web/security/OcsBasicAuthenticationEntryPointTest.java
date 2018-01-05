package com.tpg.ocs.web.security;

import lombok.Getter;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Enumeration;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OcsBasicAuthenticationEntryPointTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    private AuthenticationException authenticationException;

    private OcsBasicAuthenticationEntryPoint entryPoint;

    @Before
    public void setUp() {

        authenticationException = new DisabledException("test message");

        entryPoint = new OcsBasicAuthenticationEntryPoint();
    }

    @Test
    public void commence() throws Exception {
        StubPrintWriter printWriter = new StubPrintWriter(new BufferedWriter(new StringWriter()));

        Enumeration<String> names = new Enumeration<String>() {
            @Override
            public boolean hasMoreElements() {
                return false;
            }

            @Override
            public String nextElement() {
                return null;
            }
        };

        when(request.getHeaderNames()).thenReturn(names);

        when(response.getWriter()).thenReturn(printWriter);

        entryPoint.afterPropertiesSet();

        entryPoint.commence(request, response, authenticationException);

        verify(response).addHeader("WWW-Authenticate", "Basic realm=OCS");

        verify(response).setStatus(SC_UNAUTHORIZED);

        assertThat(printWriter.getBuffer().toString(), containsString("HTTP Status 401 - test message"));
    }

    static class StubPrintWriter extends PrintWriter {
        @Getter
        private final StringBuffer buffer = new StringBuffer();

        public StubPrintWriter(Writer out) {
            super(out);
        }

        @Override
        public void println(String value) {
            buffer.append(value);
        }
    }
}
