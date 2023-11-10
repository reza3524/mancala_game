package ir.hamrahcard.mancala.service.impl.factory;

import ir.hamrahcard.mancala.domain.Pit;
import ir.hamrahcard.mancala.domain.Player;
import ir.hamrahcard.mancala.dto.PitDto;
import ir.hamrahcard.mancala.dto.PlayerDto;
import ir.hamrahcard.mancala.enumeration.PitSide;

public class PlayerFactory extends AbstractFactory<Player, PlayerDto, PlayerDto.Transfer> {

    @Override
    public Player getEntity() {
        Player player = new Player();
        player.setId(1L);
        player.setSide(PitSide.PLAYER_A);
        player.setTurn(true);
        return player;
    }

    @Override
    public PlayerDto getDto() {
        return null;
    }

    @Override
    public PlayerDto.Transfer getTransferDto() {
        return null;
    }
}
