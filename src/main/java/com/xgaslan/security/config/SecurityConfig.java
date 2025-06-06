package com.xgaslan.security.config;

import com.xgaslan.security.jwt.AuthEntryPoint;
import com.xgaslan.security.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] PUBLIC_ENDPOINTS = {
            "/authenticate/**"
    };

    private final AppConfig _appConfig;
    private final JwtAuthenticationFilter _jwtAuthenticationFilter;
    private final AuthEntryPoint _authEntryPoint;

    @Autowired
    public SecurityConfig(AppConfig appConfig, JwtAuthenticationFilter jwtAuthenticationFilter, AuthEntryPoint authEntryPoint) {
        _appConfig = appConfig;
        _jwtAuthenticationFilter = jwtAuthenticationFilter;
        _authEntryPoint = authEntryPoint;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(_appConfig.userDetailsService());
        authenticationProvider.setPasswordEncoder(_appConfig.passwordEncoder()); // Set your password encoder here

        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
        csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests ->
                        requests.requestMatchers(PUBLIC_ENDPOINTS)
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(_authEntryPoint)
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(_jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
