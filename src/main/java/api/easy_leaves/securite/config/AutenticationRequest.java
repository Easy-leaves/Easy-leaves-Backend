package api.easy_leaves.securite.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AutenticationRequest {
	private String email;
	
	private String password;
	
	public AutenticationRequest() {
	}
	
	public AutenticationRequest(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
