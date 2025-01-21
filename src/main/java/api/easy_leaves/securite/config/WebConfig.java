package api.easy_leaves.securite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration  // Ajoutez cette annotation pour que Spring Boot la détecte
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Autorise CORS sur toutes les routes de l'application
                .allowedOrigins("http://localhost:4200")  // Autoriser localhost:4200
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Autorise certaines méthodes HTTP
                .allowedHeaders("*")  // Autorise tous les en-têtes
                .allowCredentials(true);  // Si vous avez besoin de gérer des cookies ou des tokens
    }


}
