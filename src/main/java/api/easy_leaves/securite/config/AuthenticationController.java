package api.easy_leaves.securite.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	private final AuthenticationService service; //final
	
	public AuthenticationController(AuthenticationService service) {
		super();
		this.service = service;
	}



	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AutenticationRequest request){
		return ResponseEntity.ok(service.authenticate(request));
	}
	
}
