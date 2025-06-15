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
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.exceptionHandling(excepz -> excepz
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
        );

        http.authorizeHttpRequests(authz -> authz
                // Rutas públicas
                .requestMatchers(HttpMethod.POST, "/auth/register", "/auth/login", "/auth/refresh/token", "/activate/account/", "/error").permitAll()
                .requestMatchers(HttpMethod.GET, "/me").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/cliente/register", "/cliente").permitAll()
                .requestMatchers(HttpMethod.GET, "/cliente/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/producto/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/categoria").permitAll()
                .requestMatchers("/uploads/**").permitAll()

                // Rutas roles específicos
                .requestMatchers("/me/admin").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/trabajador/**").hasRole("TRABAJADOR")
                .requestMatchers(HttpMethod.PUT, "/cliente/**").hasRole("CLIENTE")


                // Producto
                .requestMatchers(HttpMethod.GET, "/producto/cliente/**").hasAnyRole("CLIENTE", "TRABAJADOR", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/producto/**").hasRole("CLIENTE")
                .requestMatchers(HttpMethod.PUT, "/producto/{idProducto}/categoria/{idCategoria}").hasAnyRole("ADMIN", "TRABAJADOR")

                // Categoría
                .requestMatchers(HttpMethod.POST, "/categoria").hasAnyRole("TRABAJADOR", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/categoria/**").hasAnyRole("ADMIN", "TRABAJADOR")
                .requestMatchers(HttpMethod.PUT, "/categoria/**").hasAnyRole("TRABAJADOR", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/categoria/**").hasAnyRole("TRABAJADOR", "ADMIN")

                // Organización
                .requestMatchers(HttpMethod.POST, "/organizacion").hasAnyRole("TRABAJADOR", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/organizacion").permitAll()
                .requestMatchers(HttpMethod.GET, "/organizacion/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/organizacion/**").hasAnyRole("TRABAJADOR", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/organizacion/**").hasAnyRole("TRABAJADOR", "ADMIN")

                //Valora
                .requestMatchers(HttpMethod.POST, "/valora").hasRole("TRABAJADOR")
                .requestMatchers(HttpMethod.GET, "/valora/trabajador/**").hasAnyRole("TRABAJADOR", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/valora/**").hasRole("TRABAJADOR")
                .requestMatchers(HttpMethod.DELETE, "/valora/**").hasRole("TRABAJADOR")

                //Donacion
                .requestMatchers(HttpMethod.POST, "/donacion").hasRole("CLIENTE")
                .requestMatchers(HttpMethod.GET, "/donacion").hasAnyRole("CLIENTE", "TRABAJADOR", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/donacion/**").hasAnyRole("CLIENTE", "TRABAJADOR", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/donacion/**").hasAnyRole("ADMIN", "TRABAJADOR")

                //Favoritos
                .requestMatchers(HttpMethod.PUT, "/cliente/producto/**").hasRole("CLIENTE")
                .requestMatchers(HttpMethod.GET, "/cliente/producto").hasRole("CLIENTE")
                .requestMatchers(HttpMethod.DELETE, "/cliente/producto/**").hasRole("CLIENTE")

                //LineaVenta
                .requestMatchers(HttpMethod.GET, "/linea-venta").hasAnyRole("ADMIN", "TRABAJADOR")
                .requestMatchers(HttpMethod.POST, "/linea-venta").hasRole("CLIENTE")

                //Compra
                .requestMatchers(HttpMethod.GET, "/compra").hasAnyRole( "TRABAJADOR", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/compra/cliente/**").hasAnyRole("CLIENTE", "TRABAJADOR", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/compra").hasRole("CLIENTE")


                .anyRequest().authenticated());

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.headers(headers ->
                headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }


}
