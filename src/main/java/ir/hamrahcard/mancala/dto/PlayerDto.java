package ir.hamrahcard.mancala.dto;

import ir.hamrahcard.mancala.base.BaseDto;
import ir.hamrahcard.mancala.base.BaseTransfer;
import ir.hamrahcard.mancala.enumeration.PitSide;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serial;

@Getter
@Setter
@Accessors(chain = true)
public class PlayerDto extends BaseDto<Long> {

    @Serial
    private static final long serialVersionUID = -749654593806679089L;

    private PitSide side;

    private boolean turn;

    @Getter
    @Setter
    @Accessors(chain = true)
    public static class Transfer extends BaseTransfer<Long> {

        @Serial
        private static final long serialVersionUID = -7940635932570585405L;

        private PitSide side;
        private boolean turn;
    }
}
