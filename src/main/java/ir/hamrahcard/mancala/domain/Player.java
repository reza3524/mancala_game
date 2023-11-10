package ir.hamrahcard.mancala.domain;

import ir.hamrahcard.mancala.base.BaseEntity;
import ir.hamrahcard.mancala.enumeration.PitSide;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serial;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "tbl_player", uniqueConstraints = @UniqueConstraint(columnNames = "side"))
@SequenceGenerator(sequenceName = "TBL_PLAYER_SEQ", name = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Player extends BaseEntity<Long> {

    @Serial
    private static final long serialVersionUID = 5705498573275985149L;

    @Column(name = "side", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private PitSide side;

    @Column(name = "turn")
    private boolean turn;
}
