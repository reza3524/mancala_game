package ir.hamrahcard.mancala.repository;

import ir.hamrahcard.mancala.base.BaseRepository;
import ir.hamrahcard.mancala.domain.Pit;
import ir.hamrahcard.mancala.enumeration.PitName;
import ir.hamrahcard.mancala.enumeration.PitSide;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PitRepository extends BaseRepository<Pit, Long> {

    List<Pit> findAllByOrderByPositionAsc();

    Optional<Pit> findByPlayerSideAndName(PitSide side, PitName name);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Pit> findAllByPositionIn(Collection<Integer> position);

    List<Pit> findAllByPositionBetween(Integer start, Integer end);
}
