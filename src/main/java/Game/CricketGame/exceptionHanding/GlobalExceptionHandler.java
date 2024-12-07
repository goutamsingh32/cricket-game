package Game.CricketGame.exceptionHanding;

import Game.CricketGame.ResponseHandling.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<CustomResponse<?>> handleBadRequest(BadRequestException ex){
        CustomResponse<?> response = CustomResponse.failure(ex.getMessage(),null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<CustomResponse<?>> handleConflict(ConflictException ex){
        CustomResponse<?> response = CustomResponse.failure(ex.getMessage(),null, HttpStatus.CONFLICT);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse<?>> handleGeneralException(Exception ex){
        CustomResponse<?> response = CustomResponse.failure("Something went wrong", null, HttpStatus.INTERNAL_SERVER_ERROR);
        return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
