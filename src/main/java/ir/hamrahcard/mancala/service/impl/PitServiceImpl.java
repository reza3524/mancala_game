package ir.hamrahcard.mancala.service.impl;

import ir.hamrahcard.mancala.domain.Pit;
import ir.hamrahcard.mancala.domain.Player;
import ir.hamrahcard.mancala.enumeration.PitName;
import ir.hamrahcard.mancala.enumeration.PitSide;
import ir.hamrahcard.mancala.exception.BadRequestException;
import ir.hamrahcard.mancala.exception.NotFoundException;
import ir.hamrahcard.mancala.repository.PitRepository;
import ir.hamrahcard.mancala.service.facade.PitService;
import ir.hamrahcard.mancala.service.facade.PlayerService;
import ir.hamrahcard.mancala.util.BoardUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static ir.hamrahcard.mancala.base.BaseConstant.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class PitServiceImpl implements PitService {


    private final PitRepository repository;
    private final PlayerService playerService;

    @Override
    @Transactional
    public void createNewGame() {
        log.debug("Request to create new game");
        deleteAll();
        initNewGame();
        playerService.setDefault();
    }

    @Override
    @Transactional(readOnly = true)
    public String showBoard() {
        log.debug("Request to show current board");
        Map<String, Integer> map = repository.findAllByOrderByPositionAsc().stream()
                .collect(Collectors.toMap(
                        pit -> String.valueOf(pit.getPosition()), Pit::getSize));
        return StringSubstitutor.replace(BoardUtil.getTemplate(), map, "${", "}");
    }

    @Override
    @Transactional
    public String move(PitSide side, PitName name) {
        log.debug("Request to move from {} and pit {}", side, name);
        Pit currentPit = getPitBySideAndName(side, name);
        playerService.checkTurn(currentPit.getPlayer());
        handlePits(currentPit);
        return showBoard();
    }

    protected void deleteAll() {
        repository.deleteAll();
    }

    protected void initNewGame() {
        Player playerA = playerService.findBySide(PitSide.PLAYER_A);
        Player playerB = playerService.findBySide(PitSide.PLAYER_B);
        List<Pit> pits = List.of(
                new Pit(0, DEFAULT_SIZE, PitName.A, playerA, false),
                new Pit(1, DEFAULT_SIZE, PitName.B, playerA, false),
                new Pit(2, DEFAULT_SIZE, PitName.C, playerA, false),
                new Pit(3, DEFAULT_SIZE, PitName.D, playerA, false),
                new Pit(4, DEFAULT_SIZE, PitName.E, playerA, false),
                new Pit(5, DEFAULT_SIZE, PitName.F, playerA, false),
                new Pit(6, 0, PitName.G, playerA, true),
                new Pit(7, DEFAULT_SIZE, PitName.A, playerB, false),
                new Pit(8, DEFAULT_SIZE, PitName.B, playerB, false),
                new Pit(9, DEFAULT_SIZE, PitName.C, playerB, false),
                new Pit(10, DEFAULT_SIZE, PitName.D, playerB, false),
                new Pit(11, DEFAULT_SIZE, PitName.E, playerB, false),
                new Pit(12, DEFAULT_SIZE, PitName.F, playerB, false),
                new Pit(13, 0, PitName.G, playerB, true)
        );
        repository.saveAll(pits);
    }

    protected Pit getPitBySideAndName(PitSide side, PitName name) {
        return repository.findByPlayerSideAndName(side, name)
                .orElseThrow(() -> new NotFoundException("err.pit.not-found"));
    }

    protected void handlePits(Pit pit) {
        checkPitSize(pit);
        List<Integer> pits = BoardUtil.getPits(pit.getPosition(), pit.getSize());
        pit.setSize(0);
        increasePitSize(pits, pit);
    }

    protected void checkPitSize(Pit pit) {
        if (pit.getSize().equals(0)) {
            throw new BadRequestException("err.pit.size.empty");
        }
    }

    protected void increasePitSize(List<Integer> pits, Pit pit) {
        AtomicReference<Pit> last = new AtomicReference<>();
        repository.findAllByPositionIn(pits)
                .parallelStream()
                .forEach(existPit -> {
                    existPit.setSize(existPit.getSize() + 1);
                    if (Objects.equals(existPit.getPosition(), pits.get(pits.size() - 1))) {
                        last.set(existPit);
                    }
                });
        handleOppositeSideSide(pit, last.get());
        boolean isGameOver = checkGameOver();
        changeTurn(last.get(), isGameOver);
    }

    /**
     * When the last piece is placed in an empty hole,
     * that piece and all the pieces of the opposite hole are stored in the main hole of the player.
     */
    protected void handleOppositeSideSide(Pit pit, Pit lastPit) {
        if (!lastPit.isMain() &&
                lastPit.getSize() - 1 == 0 &&
                lastPit.getPlayer().getSide().name().equals(pit.getPlayer().getSide().name())) {

            AtomicInteger total = new AtomicInteger();
            Pit oppositePitSide = getPitBySideAndName(PitSide.swap(lastPit.getPlayer().getSide()), PitName.getOpposite(lastPit.getName()));
            total.addAndGet(oppositePitSide.getSize());
            oppositePitSide.setSize(0);

            total.getAndAdd(lastPit.getSize());
            lastPit.setSize(0);

            Pit mainPit = getPitBySideAndName(lastPit.getPlayer().getSide(), PitName.G);
            mainPit.setSize(mainPit.getSize() + total.get());
        }
    }

    protected boolean checkGameOver() {
        Integer sideA = getTotalPitSideSize(PLAYER_A_START_PIT_POSITION, PLAYER_A_END_PIT_POSITION);
        Integer sideB = getTotalPitSideSize(PLAYER_B_START_PIT_POSITION, PLAYER_B_END_PIT_POSITION);
        if (sideA == 0 || sideB == 0) {
            if (sideB == 0) {
                calculateMainPit(PLAYER_A_START_PIT_POSITION, PLAYER_A_END_PIT_POSITION, sideA);
            } else {
                calculateMainPit(PLAYER_B_START_PIT_POSITION, PLAYER_B_END_PIT_POSITION, sideB);
            }
            return true;
        }
        return false;
    }

    protected Integer getTotalPitSideSize(Integer start, Integer end) {
        return getAllByPositionBetween(start, end).stream()
                .map(Pit::getSize)
                .reduce(0, Integer::sum);
    }

    protected List<Pit> getAllByPositionBetween(Integer start, Integer end) {
        return repository.findAllByPositionBetween(start, end);
    }

    protected void calculateMainPit(Integer start, Integer end, Integer totalPitSize) {
        getAllByPositionBetween(start, end + 1)
                .parallelStream()
                .forEach(pit -> {
                    if (pit.getPosition() == end + 1) {
                        pit.setSize(pit.getSize() + totalPitSize);
                    } else {
                        pit.setSize(0);
                    }
                });
    }

    //TODO Handle player turn with cache!
//    @CachePut(value = "player", key = "#name")
    protected void changeTurn(Pit pit, boolean isGameOver) {
        if (Objects.equals(pit.getPosition(), PLAYER_A_MAIN_PIT_POSITION) ||
                Objects.equals(pit.getPosition(), PLAYER_B_MAIN_PIT_POSITION)) {
            return;
        }
        playerService.changeTurn(isGameOver);
    }
}
