package api.easy_leaves.securite.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur responsable de la gestion de l'authentification des utilisateurs.
 * Cette classe contient une méthode pour traiter les requêtes d'authentification des utilisateurs.
 * Elle reçoit une requête d'authentification sous forme d'objet {@link AutenticationRequest}, et retourne une réponse d'authentification sous forme d'objet {@link AuthenticationResponse}.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	/**
	 * Service d'authentification qui gère la logique d'authentification de l'utilisateur.
	 */
	private final AuthenticationService service;
	
	/**
	 * Constructeur de la classe AuthenticationController.
	 * Ce constructeur permet d'injecter le service d'authentification dans le contrôleur.
	 * @param service le service d'authentification à injecter
	 */
	public AuthenticationController(AuthenticationService service) {
		super();
		this.service = service;
	}

	/**
	 * Méthode de traitement des requêtes d'authentification des utilisateurs.
	 * Cette méthode prend en entrée une requête d'authentification au format JSON sous forme d'objet {@link AutenticationRequest},
	 * et retourne une réponse d'authentification sous forme d'objet {@link AuthenticationResponse}.
	 * @param request la requête d'authentification contenant l'email et le mot de passe de l'utilisateur
	 * @return une réponse HTTP contenant un objet {@link AuthenticationResponse} avec les informations d'authentification
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AutenticationRequest request){
		return ResponseEntity.ok(service.authenticate(request));
	}
	
}
