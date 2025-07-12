package cadastroclientes.casdastrodeclientes.infra;

import cadastroclientes.casdastrodeclientes.exception.ClienteDuplicadoException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintDeclarationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<String> handleClienteNoDataFoundException() {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente nao encontrado!");

    }

    @ExceptionHandler(ConstraintDeclarationException.class)
    private ResponseEntity<String> handleClienteDuplicatedException(ClienteDuplicadoException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Já existe um cliente com CPF, nome ou e-mail informado.");

    }
}
