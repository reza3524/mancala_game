package ir.hamrahcard.mancala.base;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

    private final Object[] parameters;

    protected BaseException(String message, Object[] parameters) {
        super(message);
        this.parameters = parameters;
    }
}
