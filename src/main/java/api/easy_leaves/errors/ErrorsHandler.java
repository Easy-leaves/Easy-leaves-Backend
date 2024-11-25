package api.easy_leaves.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorsHandler {

	@ExceptionHandler(DataBaseError.class)
    public ResponseEntity<String> handleDataBaseError(DataBaseError ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
	
	@ExceptionHandler(IncoherenceDateError.class)
    public ResponseEntity<String> handleDataBaseError(IncoherenceDateError ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
