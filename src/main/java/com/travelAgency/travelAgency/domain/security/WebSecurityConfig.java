package com.travelAgency.travelAgency.domain.security;


import com.travelAgency.travelAgency.domain.jwt.JwtAuthenticationFilter;
import com.travelAgency.travelAgency.domain.jwt.JwtService;
import com.travelAgency.travelAgency.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;



    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
            .requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
            .httpBasic(HttpBasicConfigurer::disable)
            .csrf(CsrfConfigurer::disable)
            .cors(CorsConfigurer::disable)
            .formLogin(FormLoginConfigurer::disable)
            .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers(AntPathRequestMatcher.antMatcher("/login")).permitAll()
                .requestMatchers(AntPathRequestMatcher.antMatcher("/register")).permitAll()
                .requestMatchers(AntPathRequestMatcher.antMatcher("/error")).permitAll()
                .anyRequest().authenticated()
            )
            .authenticationProvider(authenticationProvider)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

}
