package ir.hamrahcard.mancala.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum PitName {
    A("F"),
    B("E"),
    C("D"),
    D("C"),
    E("B"),
    F("A"),
    G("G");

    private final String opposite;

    public static PitName getOpposite(PitName name) {
        for (PitName pitName : PitName.values()) {
            if (name.getOpposite().equals(pitName.name())) {
                return pitName;
            }
        }
        return null;
    }
}
