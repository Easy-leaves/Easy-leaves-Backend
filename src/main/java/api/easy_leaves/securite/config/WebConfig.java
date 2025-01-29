package api.easy_leaves.securite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration pour gérer les règles de CORS (Cross-Origin Resource Sharing) dans l'application.
 * Cette classe permet de configurer les paramètres de CORS pour toutes les routes HTTP.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configure les règles de CORS pour l'application.
     * 
     * Les paramètres incluent :
     * - Les routes autorisées à utiliser CORS.
     * - Les origines autorisées (ex. : "http://localhost:4200").
     * - Les méthodes HTTP autorisées (GET, POST, PUT, DELETE, OPTIONS).
     * - Les en-têtes autorisés.
     * - L'autorisation de l'envoi de cookies ou de tokens.
     *
     * @param registry l'objet {@link CorsRegistry} utilisé pour configurer les règles CORS.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Autorise CORS sur toutes les routes de l'application
                .allowedOrigins("http://localhost:4200")  // Autorise localhost:4200
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Autorise certaines méthodes HTTP
                .allowedHeaders("*")  // Autorise tous les en-têtes
                .allowCredentials(true);  // Si vous avez besoin de gérer des cookies ou des tokens
    }
}
