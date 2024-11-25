package api.easy_leaves.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe centralisée pour la gestion des exceptions dans l'application.
 * 
 * Cette classe utilise l'annotation {@link ControllerAdvice} pour intercepter
 * et traiter les exceptions spécifiques levées par les contrôleurs.
 * Chaque exception est mappée à une réponse HTTP appropriée.
 *
 * @see ControllerAdvice
 * @see ExceptionHandler
 */
@ControllerAdvice
public class ErrorsHandler {

    /**
     * Gestionnaire pour les exceptions {@link DataBaseError}.
     * 
     * Retourne une réponse HTTP avec le statut {@code 404 Not Found} et le message
     * de l'exception en tant que corps de la réponse.
     * 
     * @param ex l'exception levée indiquant une erreur liée à la base de données
     * @return une réponse HTTP avec le statut {@code 404 Not Found}
     */
	@ExceptionHandler(DataBaseError.class)
	public ResponseEntity<String> handleDataBaseError(DataBaseError ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
    /**
     * Gestionnaire pour les exceptions {@link IncoherenceDateError}.
     * 
     * Retourne une réponse HTTP avec le statut {@code 400 Bad Request} et le message
     * de l'exception en tant que corps de la réponse.
     * 
     * @param ex l'exception levée indiquant une incohérence dans les dates
     * @return une réponse HTTP avec le statut {@code 400 Bad Request}
     */
	@ExceptionHandler(IncoherenceDateError.class)
	public ResponseEntity<String> handleDataBaseError(IncoherenceDateError ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
}
