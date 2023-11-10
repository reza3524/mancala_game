package ir.hamrahcard.mancala.service.facade;

import ir.hamrahcard.mancala.domain.Player;
import ir.hamrahcard.mancala.enumeration.PitSide;

public interface PlayerService {

    void setDefault();

    Player findBySide(PitSide side);

    void checkTurn(Player player);

    void changeTurn(boolean isGameOver);
}
