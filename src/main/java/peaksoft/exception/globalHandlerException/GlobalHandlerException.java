package peaksoft.exception.globalHandlerException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import peaksoft.exception.ExceptionResponse;
import peaksoft.exception.InvalidEmailException;
import peaksoft.exception.InvalidNameException;
import peaksoft.exception.NotFoundException;
@RestControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse notFound(NotFoundException n) {
        return new ExceptionResponse(HttpStatus.NOT_FOUND, n.getClass().getName(), n.getMessage());
    }
    @ExceptionHandler(InvalidNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse exists(InvalidNameException i){
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, i.getClass().getName(), i.getMessage());
    }
    @ExceptionHandler(InvalidEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse exists(InvalidEmailException i){
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, i.getClass().getName(), i.getMessage());
    }
}
