package api.easy_leaves.securite.config;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filtre personnalisé pour l'authentification JWT (JSON Web Token).
 * Ce filtre est utilisé pour intercepter les requêtes HTTP et extraire le jeton JWT
 * de l'en-tête "Authorization". Si un jeton valide est trouvé, il est utilisé
 * pour authentifier l'utilisateur dans le contexte de sécurité de Spring.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	/**
	 * Service de chargement des détails de l'utilisateur (utilisé pour la validation du jeton).
	 */
	private final UserDetailsService userDetailsService;
	
	/**
	 * Service pour la gestion du JWT, y compris l'extraction du nom d'utilisateur et la validation du jeton.
	 */
	private final JwtService jwtService;
	
	/**
	 * Constructeur du filtre d'authentification JWT.
	 * Ce constructeur permet d'injecter les services nécessaires pour l'authentification basée sur JWT.
	 * @param userDetailsService le service qui charge les détails de l'utilisateur
	 * @param jwtService le service qui gère les opérations liées au JWT
	 */
	public JwtAuthenticationFilter(UserDetailsService userDetailsService, JwtService jwtService) {
		super();
		this.userDetailsService = userDetailsService;
		this.jwtService = jwtService;
	}

	/**
	 * Filtre interne qui est appelé pour chaque requête HTTP.
	 * Ce filtre extrait le jeton JWT de l'en-tête "Authorization", vérifie sa validité,
	 * puis, si le jeton est valide, l'utilisateur est authentifié dans le contexte de sécurité de Spring.
	 * @param request la requête HTTP entrante
	 * @param response la réponse HTTP à renvoyer
	 * @param filterChain la chaîne de filtres qui suit ce filtre
	 * @throws ServletException si une erreur se produit lors du traitement de la requête
	 * @throws IOException si une erreur d'entrée/sortie survient
	 */
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain)
			throws ServletException, IOException {
		// Récupère l'en-tête "Authorization"
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		// Si l'en-tête n'est pas présent ou ne commence pas par "Bearer ", on passe au filtre suivant
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		// Extrait le jeton JWT à partir de l'en-tête
		jwt = authHeader.substring(7);
		userEmail = jwtService.extractUsername(jwt);
		// Si un email d'utilisateur est extrait et qu'aucune authentification n'est en cours, on vérifie le jeton
		if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
			// Si le jeton est valide, on crée un token d'authentification
			if (jwtService.isTokenValid(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						userDetails,
						null,
						userDetails.getAuthorities()
				);
				authToken.setDetails(
					new WebAuthenticationDetailsSource().buildDetails(request)
				);
				// On place l'authentification dans le contexte de sécurité
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		// Passe au filtre suivant dans la chaîne
		filterChain.doFilter(request, response);
	}

}
