package phat.jwtytb.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import phat.jwtytb.filter.CustomAuthenticationFilter;

public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
    @Override
    public void configure(HttpSecurity http) {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager);

        customAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");

        http.addFilter(customAuthenticationFilter);
    }

    public static MyCustomDsl customDsl() {
        return new MyCustomDsl();
    }
}
