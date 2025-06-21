package PFE.PFE_SI.Security;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final  UserDetailsService userDetailsService;

     // Injection du JwtService dans le constructeur
    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;

    }

    @Override
    protected void doFilterInternal(@NonNull  HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        // Récupérer l'en-tête Authorization de la requête HTTP
        final String authHeader = request.getHeader("Authorization");

        // Si l'en-tête n'existe pas ou ne commence pas par "Bearer ", passer au prochain filtre
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extraire le token JWT de l'en-tête
        final String jwt = authHeader.substring(7);  // enlever "Bearer " du début
        final String userEmail = jwtService.extractUsername(jwt); // Extraire l'email du token

        // Vérifier si l'utilisateur est déjà authentifié dans le contexte de sécurité
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            // Valider le token
            if (jwtService.isTokenValid(jwt, userDetails)) {
                // Créer l'authentification (ici, à adapter selon ton modèle de UserDetails)
                // Je vais supposer que tu as un UserDetailsService et un utilisateur valide
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()); // Ajouter des autorités si nécessaire

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Enregistrer l'authentification dans le contexte de sécurité
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Passer au prochain filtre
        filterChain.doFilter(request, response);
    }
}
