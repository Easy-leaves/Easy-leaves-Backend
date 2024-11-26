package api.easy_leaves.securite.config;

import lombok.Builder;
import lombok.Data;

/**
 * Représente la réponse d'authentification contenant le jeton d'accès.
 * Cette classe est utilisée pour renvoyer un jeton d'authentification à l'utilisateur après une authentification réussie.
 * Elle est construite en utilisant le pattern Builder pour faciliter la création de l'objet.
 */
@Data
@Builder
public class AuthenticationResponse {
	/**
	 * Le jeton d'accès généré après une authentification réussie.
	 * Ce jeton est utilisé pour authentifier l'utilisateur dans les requêtes suivantes.
	 */
    private String token;

	/**
	 * Constructeur privé pour forcer l'utilisation du Builder.
	 * Ce constructeur est privé pour empêcher la création d'objets de cette classe sans utiliser le Builder.
	 */
    private AuthenticationResponse() {
    }

	/**
	 * Récupère le jeton d'authentification.
	 * @return le jeton d'authentification
	 */
    public String getToken() {
        return token;
    }

	/**
	 * Permet de construire un objet {@link AuthenticationResponse} en utilisant le Builder.
	 * Le Builder permet de créer facilement une instance de {@link AuthenticationResponse} en spécifiant uniquement le jeton d'authentification.
	 * @return le builder pour construire l'objet {@link AuthenticationResponse}
	 */
    public static AuthenticationResponseBuilder builder() {
        return new AuthenticationResponseBuilder();
    }

	/**
	 * Le Builder pour créer une instance de {@link AuthenticationResponse}.
	 * Ce builder permet de spécifier un jeton d'authentification et de construire l'objet {@link AuthenticationResponse} de manière fluide.
	 */
    public static class AuthenticationResponseBuilder {
        private String token;

		/**
		 * Définit le jeton d'authentification.
		 * @param token le jeton d'authentification à définir
		 * @return le builder avec le jeton défini
		 */
        public AuthenticationResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

		/**
		 * Construit l'objet {@link AuthenticationResponse} avec les valeurs définies dans le builder.
		 * @return l'objet {@link AuthenticationResponse} créé
		 */
        public AuthenticationResponse build() {
            AuthenticationResponse response = new AuthenticationResponse();
            response.token = this.token;
            return response;
        }
    }
}
