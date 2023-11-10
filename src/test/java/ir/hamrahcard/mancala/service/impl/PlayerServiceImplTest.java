package ir.hamrahcard.mancala.service.impl;

import ir.hamrahcard.mancala.domain.Player;
import ir.hamrahcard.mancala.enumeration.PitSide;
import ir.hamrahcard.mancala.exception.BadRequestException;
import ir.hamrahcard.mancala.repository.PlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PlayerServiceImplTest {

    @InjectMocks
    private PlayerServiceImpl service;

    @Mock
    private PlayerRepository repository;

    @Test
    void testGetPlayerById() {
        Player player = new Player();
        player.setId(1L);
        player.setSide(PitSide.PLAYER_A);
        player.setTurn(true);
        when(repository.findBySide(PitSide.PLAYER_A)).thenReturn(Optional.of(player));
        Player result = service.findBySide(PitSide.PLAYER_A);
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertTrue(result.isTurn());
    }

    @Test
    void testCheckTurn_gameOver_scenario() {
        List<Player> players = new ArrayList<>();
        Player player1 = new Player();
        player1.setId(1L);
        player1.setSide(PitSide.PLAYER_A);
        player1.setTurn(false);
        Player player2 = new Player();
        player2.setId(2L);
        player2.setSide(PitSide.PLAYER_B);
        player2.setTurn(false);
        players.add(player1);
        players.add(player2);
        when(repository.findAll()).thenReturn(players);
        when(repository.findBySide(PitSide.PLAYER_B)).thenReturn(Optional.of(player2));
        Assertions.assertThrows(BadRequestException.class, () -> service.checkTurn(player1));
    }

    @Test
    void testCheckTurn_otherSideTurn_scenario() {
        List<Player> players = new ArrayList<>();
        Player player1 = new Player();
        player1.setId(1L);
        player1.setSide(PitSide.PLAYER_A);
        player1.setTurn(false);
        Player player2 = new Player();
        player2.setId(2L);
        player2.setSide(PitSide.PLAYER_B);
        player2.setTurn(true);
        players.add(player1);
        players.add(player2);
        when(repository.findAll()).thenReturn(players);
        when(repository.findBySide(PitSide.PLAYER_B)).thenReturn(Optional.of(player2));
        Assertions.assertThrows(BadRequestException.class, () -> service.checkTurn(player1));
    }

    @Test
    void testCheckTurn_success_scenario() {
        List<Player> players = new ArrayList<>();
        Player player1 = new Player();
        player1.setId(1L);
        player1.setSide(PitSide.PLAYER_A);
        player1.setTurn(true);
        Player player2 = new Player();
        player2.setId(2L);
        player2.setSide(PitSide.PLAYER_B);
        player2.setTurn(false);
        players.add(player1);
        players.add(player2);
        when(repository.findAll()).thenReturn(players);
        Assertions.assertEquals(PitSide.PLAYER_A, player1.getSide());
    }
}
