package hakaton.webcommit.webCommit.controller.advice;

import hakaton.webcommit.webCommit.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApplicationError> handleBadRequestException(BadRequestException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(
                new ApplicationError(
                        e.getMessage(),
                        HttpStatus.BAD_REQUEST.value()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(TeamConflictException.class)
    public ResponseEntity<ApplicationError> handleTeamConflictException(TeamConflictException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(
                new ApplicationError(
                        e.getMessage(),
                        HttpStatus.CONFLICT.value()
                ),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApplicationError> handleResourceNotFoundException(ResourceNotFoundException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(
                new ApplicationError(
                        e.getMessage(),
                        HttpStatus.NOT_FOUND.value()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApplicationError> handleSQLException(DataAccessException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(
                new ApplicationError(
                        e.getCause().getMessage(),
                        HttpStatus.CONFLICT.value()
                ),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApplicationError> handleAccessDeniedException(AccessDeniedException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(
                new ApplicationError(
                        e.getMessage(),
                        HttpStatus.FORBIDDEN.value()
                ),
                HttpStatus.FORBIDDEN
        );
    }
}
