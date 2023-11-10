package ir.hamrahcard.mancala.enumeration;

import lombok.Getter;

@Getter
public enum PitSide {
    PLAYER_A,
    PLAYER_B;

    public static PitSide swap(PitSide side) {
        return side.name().equals(PLAYER_A.name()) ? PLAYER_B : PLAYER_A;
    }
}
