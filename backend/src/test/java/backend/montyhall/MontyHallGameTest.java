package backend.montyhall;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class MontyHallGameTest {

    @Test
    @DisplayName("Should create three doors with one containing a car")
    public void shouldReturnDoorsWithPrizes() {
        var montyHallGame = new MontyHallGame();
        var doors = montyHallGame.doors();
        var values = doors.stream().map(Door::getValue).collect(Collectors.toList());

        assertThat(doors).hasSize(3);
        assertThat(values).containsOnlyOnce(Prize.CAR);
    }

    @Test
    @DisplayName("Shown doors should contain a goat and not be the same door that the user chose")
    public void shouldShowDoorWithGoat() {
        var montyHallGame = new MontyHallGame();
        var doors = montyHallGame.doors();
        var shownDoor = montyHallGame.shownDoor(1, doors);

        assertThat(shownDoor.getNumber()).isNotEqualTo(1);
        assertThat(shownDoor.getValue()).isEqualTo(Prize.GOAT);
    }

}