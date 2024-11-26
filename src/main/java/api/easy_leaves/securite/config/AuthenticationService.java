package api.easy_leaves.securite.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import api.easy_leaves.repository.UtilisateurRepository;

@Service
public class AuthenticationService {
	private final UtilisateurRepository repository; //final
	private final PasswordEncoder passwordEncoder; //final
	private final JwtService jwtService; //final
	private final AuthenticationManager authenticationManager; //final
	
	public AuthenticationService(UtilisateurRepository repository, PasswordEncoder passwordEncoder,
			JwtService jwtService, AuthenticationManager authenticationManager) {
		super();
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}



	public AuthenticationResponse authenticate(AutenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
		);
		var user = repository.findByEmail(request.getEmail()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse
				.builder()
				.token(jwtToken)
				.build();
	}
}
