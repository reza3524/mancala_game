package ir.hamrahcard.mancala.base;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseConstant {

    public static final Integer DEFAULT_SIZE = 6;
    public static final Integer PLAYER_A_MAIN_PIT_POSITION = 6;
    public static final Integer PLAYER_A_START_PIT_POSITION = 0;
    public static final Integer PLAYER_A_END_PIT_POSITION = 5;
    public static final Integer PLAYER_B_MAIN_PIT_POSITION = 13;
    public static final Integer PLAYER_B_START_PIT_POSITION = 7;
    public static final Integer PLAYER_B_END_PIT_POSITION = 12;
}
