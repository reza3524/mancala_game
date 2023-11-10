package ir.hamrahcard.mancala.exception;

import ir.hamrahcard.mancala.base.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
import java.util.Collections;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends BaseException {

    @Serial
    private static final long serialVersionUID = -7086598872309479899L;

    public BadRequestException(String message) {
        super(message, Collections.emptyList().toArray());
    }

    public BadRequestException(String message, Object[] parameters) {
        super(message, parameters);
    }
}
