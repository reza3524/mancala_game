package ir.hamrahcard.mancala.service.facade;

import ir.hamrahcard.mancala.enumeration.PitName;
import ir.hamrahcard.mancala.enumeration.PitSide;

public interface PitService {

    void createNewGame();

    String showBoard();

    String move(PitSide side, PitName name);
}
