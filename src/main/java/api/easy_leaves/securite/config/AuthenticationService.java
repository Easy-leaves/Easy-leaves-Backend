package api.easy_leaves.securite.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import api.easy_leaves.repository.UtilisateurRepository;

/**
 * Service d'authentification des utilisateurs.
 * Cette classe est responsable de la gestion de l'authentification des utilisateurs.
 * Elle utilise le {@link AuthenticationManager} pour valider les informations d'identification,
 * le {@link JwtService} pour générer un jeton JWT, et le {@link UtilisateurRepository} pour récupérer les informations utilisateur.
 */
@Service
public class AuthenticationService {
	
	/**
	 * Référentiel des utilisateurs pour récupérer les informations sur les utilisateurs.
	 */
	private final UtilisateurRepository repository;
	
	/**
	 * Encodeur de mot de passe pour vérifier les mots de passe des utilisateurs.
	 */
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * Service pour la génération de jetons JWT.
	 */
	private final JwtService jwtService;
	
	/**
	 * Gestionnaire d'authentification pour valider les informations d'identification.
	 */
	private final AuthenticationManager authenticationManager; //final
	
	/**
	 * Constructeur de la classe AuthenticationService.
	 * Ce constructeur permet d'injecter les services nécessaires pour l'authentification.
	 * @param repository le référentiel des utilisateurs à injecter
	 * @param passwordEncoder l'encodeur de mot de passe à injecter
	 * @param jwtService le service JWT à injecter
	 * @param authenticationManager le gestionnaire d'authentification à injecter
	 */
	public AuthenticationService(UtilisateurRepository repository, PasswordEncoder passwordEncoder,
			JwtService jwtService, AuthenticationManager authenticationManager) {
		super();
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}

	/**
	 * Authentifie un utilisateur en validant ses informations d'identification et en générant un jeton JWT.
	 * Cette méthode prend une requête d'authentification contenant l'email et le mot de passe de l'utilisateur,
	 * elle les valide en utilisant le gestionnaire d'authentification et génère un jeton JWT si l'authentification réussit.
	 * @param request la requête d'authentification contenant l'email et le mot de passe
	 * @return un objet {@link AuthenticationResponse} contenant le jeton JWT généré
	 */
	public AuthenticationResponse authenticate(AutenticationRequest request) {
	    // Authentifie l'utilisateur avec ses informations d'identification
	    authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
	    );
	    // Récupère l'utilisateur à partir de la base de données
	    var user = repository.findByEmail(request.getEmail()).orElseThrow();
	    
	    // Ajouter des informations supplémentaires dans les réclamations
	    Map<String, Object> extraClaims = new HashMap<>();
	    extraClaims.put("id", user.getIdUtilisateur());
	    extraClaims.put("role", user.getRole());

	    // Génère le jeton JWT pour l'utilisateur avec les réclamations supplémentaires
	    var jwtToken = jwtService.generateToken(extraClaims, user);

	    // Retourne la réponse d'authentification contenant le jeton
	    return AuthenticationResponse
	            .builder()
	            .token(jwtToken)
	            .build();
	}
}
