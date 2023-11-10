package ir.hamrahcard.mancala.exception;

import ir.hamrahcard.mancala.base.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
import java.util.Collections;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends BaseException {

    @Serial
    private static final long serialVersionUID = -3975674508272106659L;

    public NotFoundException(String message) {
        super(message, Collections.emptyList().toArray());
    }

    public NotFoundException(String message, Object[] parameters) {
        super(message, parameters);
    }
}
