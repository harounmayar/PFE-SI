package PFE.PFE_SI.Security;

import PFE.PFE_SI.Security.JwtAuthenticationFilter;
import PFE.PFE_SI.Security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    // Configuration CORS : autorise le front-end Angular à communiquer avec le backend
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200")); // Frontend Angular
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true); // Permet d'envoyer des cookies/token

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // Configuration principale de la sécurité
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationProvider authenticationProvider) throws Exception {
        JwtAuthenticationFilter jwtFilter = new JwtAuthenticationFilter(jwtService, userDetailsService);

        http
                .cors() // Active le CORS avec la configuration définie ci-dessus
                .and()
                .csrf().disable() // Désactive CSRF (utile en API REST stateless)
                .authorizeRequests()
                .requestMatchers("/api/auth/**").permitAll() // Auth libre (login/register)
                .anyRequest().authenticated() // Toute autre route nécessite une authentification
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Pas de session
                .and()
                .authenticationProvider(authenticationProvider) // Fournisseur d'authentification (Dao)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Filtre JWT avant UsernamePassword
                .formLogin().disable(); // Pas de page login Spring Security par défaut

        return http.build();
    }
}
