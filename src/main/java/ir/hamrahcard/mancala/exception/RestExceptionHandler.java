package ir.hamrahcard.mancala.exception;

import ir.hamrahcard.mancala.base.ApiError;
import ir.hamrahcard.mancala.base.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.util.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    private <E extends BaseException> ResponseEntity<Object> buildResponseEntity(E exception, HttpStatus status) {
        ApiError apiError = new ApiError();
        Locale locale = new Locale("fa", "PERSIAN");
        String message = messageSource.getMessage(exception.getMessage(), exception.getParameters(), locale);
        apiError.setStatus(status)
                .setMessage(message)
                .setParameters(exception.getParameters())
                .setDateTime(new Timestamp(new GregorianCalendar().getTime().getTime()));
        return new ResponseEntity<>(apiError, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        List<ApiError> apiErrors = new ArrayList<>();
        List<FieldError> errors = exception.getBindingResult().getFieldErrors();
        errors.forEach(error -> apiErrors.add(
                new ApiError()
                        .setStatus(BAD_REQUEST)
                        .setField(error.getField())
                        .setMessage(error.getDefaultMessage())
                        .setDateTime(new Timestamp(new GregorianCalendar().getTime().getTime())))
        );
        return new ResponseEntity<>(apiErrors, BAD_REQUEST);
    }


    @ExceptionHandler({NotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException exception) {
        return buildResponseEntity(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class})
    protected ResponseEntity<Object> handleBadRequestException(BadRequestException exception) {
        return buildResponseEntity(exception, HttpStatus.BAD_REQUEST);
    }
}
