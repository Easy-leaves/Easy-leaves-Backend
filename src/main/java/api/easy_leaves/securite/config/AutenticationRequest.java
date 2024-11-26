package api.easy_leaves.securite.config;

import lombok.Builder;
import lombok.Data;

/**
 * Représente une requête d'authentification contenant les informations nécessaires pour l'authentification d'un utilisateur.
 * Cette classe contient deux propriétés principales : l'email de l'utilisateur et son mot de passe.
 * Elle est utilisée pour recevoir les informations de connexion d'un utilisateur lors d'une tentative d'authentification.
 */
@Data
@Builder
public class AutenticationRequest {
	/**
	 * L'email de l'utilisateur.
	 * Ce champ est utilisé pour identifier l'utilisateur lors de la tentative de connexion.
	 */
	private String email;
	
	/**
	 * Le mot de passe de l'utilisateur.
	 * Ce champ est utilisé pour valider l'identité de l'utilisateur lors de la tentative de connexion.
	 */
	private String password;
	
	/**
	 * Constructeur par défaut.
	 * Ce constructeur est utilisé pour initialiser un objet sans valeur pour les attributs.
	 */
	public AutenticationRequest() {
	}
	
	/**
	 * Constructeur avec paramètres pour initialiser un objet avec un email et un mot de passe spécifiques.
	 * @param email l'email de l'utilisateur à utiliser pour l'authentification
	 * @param password le mot de passe de l'utilisateur à utiliser pour l'authentification
	 */
	public AutenticationRequest(String email, String password) {
		this.email = email;
		this.password = password;
	}

	/**
	 * Récupère l'email de l'utilisateur.
	 * @return l'email de l'utilisateur
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Définit l'email de l'utilisateur.
	 * @param email l'email à définir pour l'utilisateur
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Récupère le mot de passe de l'utilisateur.
	 * @return le mot de passe de l'utilisateur
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Définit le mot de passe de l'utilisateur.
	 * @param password le mot de passe à définir pour l'utilisateur
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
