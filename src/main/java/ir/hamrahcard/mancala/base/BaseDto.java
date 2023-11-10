package ir.hamrahcard.mancala.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class BaseDto<I extends Serializable> implements Serializable {

    @Serial
    private static final long serialVersionUID = -520210366503027537L;

    private I id;
}
