package com.example.Recysell.security;

import com.example.Recysell.security.exceptionhandling.JwtAccessDeniedHandler;
import com.example.Recysell.security.exceptionhandling.JwtAuthenticationEntryPoint;
import com.example.Recysell.security.jwt.access.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint  authenticationEntryPoint;
    private final JwtAccessDeniedHandler accessDeniedHandler;

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{

        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        AuthenticationManager authenticationManager =
                authenticationManagerBuilder.authenticationProvider(authenticationProvider())
                        .build();

        return authenticationManager;
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider p = new DaoAuthenticationProvider();

        p.setUserDetailsService(userDetailsService);
        p.setPasswordEncoder(passwordEncoder);
        return p;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());
        http.cors(Customizer.withDefaults());
        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.exceptionHandling(excepz -> excepz
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
        );
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(HttpMethod.POST, "/auth/register", "/auth/login", "/auth/refresh/token", "/activate/account/", "/error").permitAll()
                .requestMatchers(HttpMethod.GET, "/me").permitAll()
                .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "v3/api-docs/**").permitAll()
                .requestMatchers("/me/admin").hasRole("ADMIN")
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/trabajador/**").hasRole("TRABAJADOR")
                .requestMatchers( "/cliente/register", "/cliente" ).permitAll()
                .requestMatchers(HttpMethod.GET, "/cliente/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/cliente/**").hasRole("CLIENTE")
                .requestMatchers(HttpMethod.GET, "/producto").permitAll()
                .requestMatchers(HttpMethod.GET, "/producto/**").hasRole("CLIENTE")
                .requestMatchers(HttpMethod.GET, "/producto/**").hasRole("TRABAJADOR")
                .requestMatchers(HttpMethod.GET, "/producto/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/producto/**").hasRole("CLIENTE")
                .requestMatchers(HttpMethod.GET, "/producto/cliente/**").hasRole("CLIENTE")
                .requestMatchers(HttpMethod.GET, "/producto/cliente/**").hasRole("TRABAJADOR")
                .requestMatchers(HttpMethod.GET, "/producto/cliente/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/categoria").hasRole("TRABAJADOR")
                .requestMatchers(HttpMethod.POST, "/categoria").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/categoria").permitAll()
                .anyRequest().authenticated());


        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        http.headers(headers ->
                headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }

}
