package ir.hamrahcard.mancala.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class BaseTransfer<I extends Serializable> implements Serializable {

    @Serial
    private static final long serialVersionUID = -6846260597195358029L;

    private I id;

}
