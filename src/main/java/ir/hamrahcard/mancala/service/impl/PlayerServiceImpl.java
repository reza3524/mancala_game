package ir.hamrahcard.mancala.service.impl;

import ir.hamrahcard.mancala.domain.Player;
import ir.hamrahcard.mancala.enumeration.PitSide;
import ir.hamrahcard.mancala.exception.BadRequestException;
import ir.hamrahcard.mancala.exception.NotFoundException;
import ir.hamrahcard.mancala.repository.PlayerRepository;
import ir.hamrahcard.mancala.service.facade.PlayerService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository repository;

    @PostConstruct
    private void init() {
        List<Player> players = List.of(
                new Player(PitSide.PLAYER_A, true),
                new Player(PitSide.PLAYER_B, false)
        );
        repository.saveAll(players);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void setDefault() {
        getAllPlayers().forEach(player ->
                player.setTurn(player.getSide().name().equals(PitSide.PLAYER_A.name())));
    }

    @Override
    @Transactional(readOnly = true)
//TODO Handle player turn with cache!
//    @Cacheable(cacheNames = {"Player"})
    public Player findBySide(PitSide side) {
        return repository.findBySide(side)
                .orElseThrow(() -> new NotFoundException("err.player.not-found"));
    }

    @Override
    @Transactional(readOnly = true)
    public void checkTurn(Player player) {
        Player otherPlayer = findBySide(PitSide.swap(player.getSide()));
        if (!otherPlayer.isTurn() && !player.isTurn()) {
            throw new BadRequestException("err.player.game-over");
        }
        if (!player.isTurn()) {
            throw new BadRequestException("err.player.turn.not-correct");
        }
    }

    @Override
    @Transactional
    public void changeTurn(boolean isGameOver) {
        getAllPlayers().forEach(player -> player.setTurn(!isGameOver && !player.isTurn()));
    }

    private List<Player> getAllPlayers() {
        return repository.findAll();
    }
}
