package ir.hamrahcard.mancala.util;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class BoardUtil {

    public String getTemplate() {
        return """
                              player 2
                      F   E   D   C   B   A
                     |${12}|,|${11}|,|${10}|,|${9}|,|${8}|,|${7}|
                (${13})                           (${6})
                     |${0}|,|${1}|,|${2}|,|${3}|,|${4}|,|${5}|
                      A   B   C   D   E   F
                              player 1
                """;
    }

    public List<Integer> getPits(Integer position, Integer size) {
        List<Integer> result = new ArrayList<>();
        int counter = position + 1;
        while (result.size() < size) {
            if (counter > 13) {
                counter = 0;
            }
            result.add(counter);
            if ((position > 7 && counter == 6) || (position < 7 && counter == 13)) {
                result.remove(Integer.valueOf(counter));
            }
            counter += 1;
        }
        return result;
    }
}
