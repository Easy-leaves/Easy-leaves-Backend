package api.easy_leaves.securite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import api.easy_leaves.repository.UtilisateurRepository;

@Configuration
public class ApplicationConfig {
	/**
	 * Référentiel des utilisateurs.
	 * Ce champ est injecté par le constructeur.
	 */
	private final UtilisateurRepository repository;
	
	/**
	 * Constructeur de la classe ApplicationConfig.
	 * Injecte le repository des utilisateurs afin de permettre la gestion des utilisateurs dans l'application.
	 * @param repository le référentiel des utilisateurs à injecter
	 */
	public ApplicationConfig(UtilisateurRepository repository) {
		this.repository = repository;
	}

	/**
	 * Bean de service de détails utilisateur.
	 * Ce bean permet de charger les détails d'un utilisateur à partir du référentiel en utilisant son email comme identifiant unique.
	 * @return le service de détails utilisateur qui charge un utilisateur par son email
	 * @throws UsernameNotFoundException si l'utilisateur n'est pas trouvé dans le référentiel
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		return username -> repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));
	}
	
	/**
	 * Bean de fournisseur d'authentification.
	 * Ce bean configure le fournisseur d'authentification en utilisant un DaoAuthenticationProvider, qui utilise le service de détails utilisateur
	 * et l'encodeur de mots de passe pour authentifier les utilisateurs.
	 * @return un fournisseur d'authentification configuré
	 */
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	/**
	 * Bean de gestionnaire d'authentification.
	 * Ce bean fournit un gestionnaire d'authentification à partir de la configuration d'authentification de Spring Security.
	 * @param config la configuration d'authentification à utiliser pour obtenir le gestionnaire
	 * @return le gestionnaire d'authentification
	 * @throws Exception si une erreur se produit lors de la récupération du gestionnaire
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	/**
	 * Bean d'encodeur de mots de passe.
	 * Ce bean fournit un encodeur de mots de passe basé sur l'algorithme BCrypt.
	 * Il est utilisé pour encoder les mots de passe des utilisateurs avant de les stocker.
	 * @return l'encodeur de mots de passe configuré
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
