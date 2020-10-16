package backend.montyhall;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class MontyHallGameTest {

    private MontyHallGame montyHallGame = new MontyHallGame();

    @Test
    @DisplayName("Should create three doors with one containing a car")
    public void shouldReturnDoorsWithPrizes() {
        var doors = montyHallGame.doors();
        var values = doors.stream().map(Door::getPrize).collect(Collectors.toList());

        assertThat(doors).hasSize(3);
        assertThat(values).containsOnlyOnce(Prize.CAR);
    }

    @Test
    @DisplayName("Shown doors should contain a goat and not be the same door that the user chose")
    public void shouldShowDoorWithGoat() {
        var doors = montyHallGame.doors();
        var shownDoor = montyHallGame.shownDoor(1, doors);

        assertThat(shownDoor.getNumber()).isNotEqualTo(1);
        assertThat(shownDoor.getPrize()).isEqualTo(Prize.GOAT);
    }

    @Test
    @DisplayName("Should choose correct door")
    public void chooseDoor() {
        var doors = List.of(
                new Door(1, Prize.GOAT),
                new Door(2, Prize.CAR),
                new Door(3, Prize.GOAT));

        var chosenDoor = montyHallGame.chosenDoor(1, doors);
        assertThat(chosenDoor.getNumber()).isEqualTo(1);
    }

    @Test
    @DisplayName("Should evaluate a door with a car to a win")
    public void shouldEvaluateWin() {
        var winningDoor = new Door(1, Prize.CAR);
        assertThat(montyHallGame.evaluate(winningDoor)).isEqualTo(1);
    }

    @Test
    @DisplayName("Should evaluate a door with a goat to a lost")
    public void shouldEvaluateLoose() {
        var loosingDoor = new Door(2, Prize.GOAT);
        assertThat(montyHallGame.evaluate(loosingDoor)).isEqualTo(0);
    }


    @Test
    @DisplayName("Should return win if a player chooses the correct door and stays")
    public void simulateWin() {
        var doors = List.of(
                new Door(1, Prize.GOAT),
                new Door(2, Prize.CAR),
                new Door(3, Prize.GOAT));

        assertThat(montyHallGame.simulateGame(doors, 2, false)).isEqualTo(1);
    }
}