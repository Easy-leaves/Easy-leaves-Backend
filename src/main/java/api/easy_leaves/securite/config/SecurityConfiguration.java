package api.easy_leaves.securite.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Configuration de sécurité pour l'application.
 * Cette classe configure les règles de sécurité pour les requêtes HTTP,
 * définit les filtres d'authentification JWT et spécifie les politiques de gestion de session.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	/**
	 * Le filtre d'authentification JWT.
	 * Ce filtre intercepte les requêtes pour valider les jetons JWT dans les en-têtes "Authorization".
	 */
	private final JwtAuthenticationFilter jwtAuthFilter;
	
	/**
	 * Le fournisseur d'authentification utilisé pour authentifier les utilisateurs.
	 * Ce fournisseur vérifie les informations d'identification de l'utilisateur.
	 */
	private final AuthenticationProvider authenticationProvider;

	/**
	 * Constructeur de la configuration de sécurité.
	 * Le constructeur prend le filtre d'authentification JWT et le fournisseur d'authentification
	 * comme dépendances pour les utiliser dans la configuration de sécurité.
	 * @param jwtAuthenticationFilter le filtre d'authentification JWT
	 * @param authenticationProvider le fournisseur d'authentification
	 */
    @Autowired
    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter,AuthenticationProvider authenticationProvider) {
        this.jwtAuthFilter = jwtAuthenticationFilter;
		this.authenticationProvider = authenticationProvider;
    }
	
	/**
	 * Configure les règles de sécurité pour les requêtes HTTP.
	 * Cette méthode configure la sécurité pour l'application en spécifiant les règles de filtrage des requêtes :
	 * 1. Désactivation de la protection CSRF (Cross-Site Request Forgery).
	 * 2. Autorisation des requêtes vers "/auth/**" sans authentification.
	 * 3. Tous les autres points de terminaison nécessitent une authentification.
	 * 4. La gestion de session est définie sur "stateless" (sans état), ce qui signifie que l'authentification
	 *    est gérée via les jetons JWT sans stocker l'état de la session côté serveur.
	 * 5. Le fournisseur d'authentification est ajouté pour valider les informations d'identification des utilisateurs.
	 * 6. Le filtre d'authentification JWT est ajouté avant le filtre d'authentification standard.
	 * @param http l'objet HttpSecurity permettant de configurer les règles de sécurité HTTP
	 * @return la chaîne de filtres de sécurité configurée
	 * @throws Exception si une erreur se produit lors de la configuration de la sécurité
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf()
			.disable()
			.cors()
			.and()
			.authorizeHttpRequests(auth -> auth
		            // Routes publiques accessibles sans authentification
		            .requestMatchers("/auth/**").permitAll()
		            
		            // Routes accessibles uniquement à ADMIN
		            .requestMatchers("/departements/**").hasAuthority("ADMINISTRATEUR")
		            

		            
		            // Routes accessibles uniquement à MANAGER
		            .requestMatchers("/absences/statut/**").hasAuthority("MANAGER")
		            .requestMatchers("/absences/update/**").hasAuthority("MANAGER")
		            .requestMatchers("/absences/plage/**").hasAuthority("MANAGER")
		            .requestMatchers("/absences/compte/**").hasAuthority("MANAGER")
		            .requestMatchers("/compteurs/**").hasAuthority("MANAGER")
		            .requestMatchers("/utilisateurs/**").hasAuthority("MANAGER")

		            // Toutes les autres routes nécessitent une authentification
		            .anyRequest().authenticated()
		        )
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Définit une gestion de session sans état
			.and()
			.authenticationProvider(authenticationProvider) // Utilise le fournisseur d'authentification personnalisé
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Ajoute le filtre JWT avant le filtre d'authentification standard
		return http.build(); // Retourne la chaîne de filtres configurée
		
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    System.out.println("CORS Configuration Source initialized");
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(List.of("http://localhost:4200"));
	    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	    configuration.setAllowedHeaders(List.of("*"));
	    configuration.setAllowCredentials(true); // Autoriser l'envoi de cookies si nécessaire
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}
}
