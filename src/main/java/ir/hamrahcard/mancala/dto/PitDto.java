package ir.hamrahcard.mancala.dto;

import ir.hamrahcard.mancala.base.BaseDto;
import ir.hamrahcard.mancala.base.BaseTransfer;
import ir.hamrahcard.mancala.enumeration.PitName;
import ir.hamrahcard.mancala.enumeration.PitSide;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serial;

@Getter
@Setter
@Accessors(chain = true)
public class PitDto extends BaseDto<Long> {

    @Serial
    private static final long serialVersionUID = 6327365413041870003L;

    private Integer position;

    private Integer size;

    private PitName name;

    private PlayerDto player;

    private boolean isMain;

    @Getter
    @Setter
    @Accessors(chain = true)
    public static class Transfer extends BaseTransfer<Long> {

        @Serial
        private static final long serialVersionUID = 5361012077188638158L;

        private Integer position;
        private Integer size;
        private PitName name;
        private PlayerDto.Transfer player;
        private boolean isMain;
    }
}
