package phat.jwtytb.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import phat.jwtytb.model.ResponseObject;
import phat.jwtytb.model.Role;
import phat.jwtytb.model.User;
import phat.jwtytb.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/jwt")
@Log4j2
@RequiredArgsConstructor
public class JwtTokenController {
    private final UserService userService;

    @GetMapping("/refresh_token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("mySecret".getBytes());
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);

                String username = decodedJWT.getSubject();

                User user = userService.getUserByUsername(username);


                String accessToken = JWT.create().withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString()).withClaim("roles",
                                user.getRoles().stream().map(Role::getName).collect(Collectors.joining(";")))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>() {{
                    put("access_token", accessToken);
                    put("refresh_token", refreshToken);
                }};
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                response.setContentType(APPLICATION_JSON_VALUE);

                new ObjectMapper().writeValue(response.getOutputStream(),
                        new ResponseObject("ok", "authenticate successfully", tokens));


            } catch (Exception e) {
                log.error("Authorization error with message: {}", e.getMessage());

                response.setHeader("Error", e.getMessage());
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType(APPLICATION_JSON_VALUE);

                new ObjectMapper().writeValue(response.getOutputStream(),
                        new ResponseObject("forbidden", "not authorization", e));

            }
        } else {
            throw new RuntimeException("Refresh tokes is missing");
        }
    }
}
