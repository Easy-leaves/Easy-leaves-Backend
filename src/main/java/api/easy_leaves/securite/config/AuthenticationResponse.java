package api.easy_leaves.securite.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class AuthenticationResponse {
    private String token;

    // Constructeur privé pour forcer l'utilisation du builder
    private AuthenticationResponse() {
    }

    public String getToken() {
        return token;
    }

    // Le builder peut être utilisé pour construire l'objet
    public static AuthenticationResponseBuilder builder() {
        return new AuthenticationResponseBuilder();
    }

    public static class AuthenticationResponseBuilder {
        private String token;

        public AuthenticationResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        public AuthenticationResponse build() {
            AuthenticationResponse response = new AuthenticationResponse();
            response.token = this.token;
            return response;
        }
    }
}
