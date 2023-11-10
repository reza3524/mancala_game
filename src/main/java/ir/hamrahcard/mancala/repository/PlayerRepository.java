package ir.hamrahcard.mancala.repository;

import ir.hamrahcard.mancala.base.BaseRepository;
import ir.hamrahcard.mancala.domain.Player;
import ir.hamrahcard.mancala.enumeration.PitSide;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends BaseRepository<Player, Long> {

    Optional<Player> findBySide(PitSide side);
}
