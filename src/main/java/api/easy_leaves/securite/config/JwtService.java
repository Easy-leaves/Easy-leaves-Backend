package api.easy_leaves.securite.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * Service de gestion des JSON Web Tokens (JWT).
 * Ce service permet de générer, extraire et valider des jetons JWT.
 * Il offre des méthodes pour extraire les informations du jeton, comme le nom d'utilisateur,
 * vérifier la validité du jeton, et générer de nouveaux jetons pour un utilisateur.
 */
@Service
public class JwtService {
	/**
	 * Clé secrète utilisée pour signer et valider les jetons JWT.
	 * Il est important de sécuriser cette clé et de ne pas la stocker en clair dans le code.
	 */
	private static final String SECRET_KEY = "e9cb729670a935ecdd72f7601b83b75882a8e9572743c765783a8093239fb17c";
	
	/**
	 * Extrait le nom d'utilisateur (subject) du jeton JWT.
	 * @param token le jeton JWT à partir duquel extraire le nom d'utilisateur
	 * @return le nom d'utilisateur extrait du jeton
	 */
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	/**
	 * Extrait une réclamation spécifique du jeton JWT.
	 * Cette méthode permet d'extraire n'importe quelle réclamation à partir du jeton JWT
	 * en utilisant une fonction qui résout la réclamation (comme le nom d'utilisateur ou l'expiration).
	 * @param token le jeton JWT à partir duquel extraire la réclamation
	 * @param claimsResolver fonction qui définit quelle réclamation extraire
	 * @param <T> le type de la réclamation extraite
	 * @return la réclamation extraite du jeton
	 */
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	/**
	 * Génère un jeton JWT pour un utilisateur donné.
	 * @param userDetails les détails de l'utilisateur pour lequel générer le jeton
	 * @return un jeton JWT généré pour l'utilisateur
	 */
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}
	
	/**
	 * Génère un jeton JWT avec des réclamations supplémentaires pour un utilisateur donné.
	 * @param extraClaims des réclamations supplémentaires à inclure dans le jeton
	 * @param userDetails les détails de l'utilisateur pour lequel générer le jeton
	 * @return un jeton JWT généré avec les réclamations supplémentaires
	 */
	public String generateToken(Map<String, Object> exteraClaims, UserDetails userDetails) {
		
		return Jwts
				.builder()
				.setClaims(exteraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	/**
	 * Vérifie si le jeton JWT est valide.
	 * Un jeton est valide si son nom d'utilisateur correspond à celui de l'utilisateur
	 * et si le jeton n'est pas expiré.
	 * @param token le jeton JWT à vérifier
	 * @param userDetails les détails de l'utilisateur pour lequel vérifier le jeton
	 * @return true si le jeton est valide, sinon false
	 */
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}
	
	/**
	 * Vérifie si le jeton JWT est expiré.
	 * @param token le jeton JWT à vérifier
	 * @return true si le jeton est expiré, sinon false
	 */
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	/**
	 * Extrait la date d'expiration du jeton JWT.
	 * @param token le jeton JWT à partir duquel extraire la date d'expiration
	 * @return la date d'expiration du jeton
	 */
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	/**
	 * Extrait toutes les réclamations du jeton JWT.
	 * @param token le jeton JWT à partir duquel extraire les réclamations
	 * @return les réclamations extraites du jeton
	 */
	private Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	/**
	 * Récupère la clé secrète utilisée pour signer et valider les jetons JWT.
	 * Cette clé est utilisée pour assurer l'intégrité du jeton.
	 * @return la clé secrète utilisée pour signer et valider les jetons
	 */
	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
