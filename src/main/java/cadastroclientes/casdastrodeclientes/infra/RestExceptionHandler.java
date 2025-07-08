package cadastroclientes.casdastrodeclientes.infra;

import cadastroclientes.casdastrodeclientes.exception.ClienteDuplicatedException;
import cadastroclientes.casdastrodeclientes.exception.ClienteNoDataFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClienteNoDataFoundException.class)
    private ResponseEntity<String> handleClienteNoDataFoundException(ClienteNoDataFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

    }

    @ExceptionHandler(ClienteDuplicatedException.class)
    private ResponseEntity<String> handleClienteDuplicatedException(ClienteDuplicatedException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

    }
}
