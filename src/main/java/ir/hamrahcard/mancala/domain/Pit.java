package ir.hamrahcard.mancala.domain;

import ir.hamrahcard.mancala.base.BaseEntity;
import ir.hamrahcard.mancala.enumeration.PitName;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import jakarta.persistence.*;

import java.io.Serial;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "tbl_pit")
@SequenceGenerator(sequenceName = "TBL_PIT_SEQ", name = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Pit extends BaseEntity<Long> {

    @Serial
    private static final long serialVersionUID = -2081679222622884732L;

    @Column(name = "position")
    private Integer position;

    @Column(name = "size")
    private Integer size;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private PitName name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player", referencedColumnName = "side")
    private Player player;

    @Column(name = "is_main")
    private boolean isMain;
}
