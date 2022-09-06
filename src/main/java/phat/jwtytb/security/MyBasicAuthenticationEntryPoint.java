package phat.jwtytb.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import phat.jwtytb.model.ResponseObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class MyBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx) throws IOException {
        response.setHeader("Error", "Unauthorized");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(APPLICATION_JSON_VALUE);

        new ObjectMapper()
                .writeValue(
                        response.getOutputStream(),
                        new ResponseObject("forbidden", "Unauthorized", ""));
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("Jwt");
        super.afterPropertiesSet();
    }
}
