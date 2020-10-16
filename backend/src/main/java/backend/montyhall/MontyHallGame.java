package backend.montyhall;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class MontyHallGame {

    protected int wins(int simulations, boolean switchDoors) {
        var random = new Random();
        var timesPlayerWin = 0;

        for (int plays = 0; plays < simulations; plays++) {
            var doors = doors();

            int firstChosenDoorNumber = random.nextInt(3);
            var shownDoor = shownDoor(firstChosenDoorNumber, doors);
            var chosenDoor = doors.get(firstChosenDoorNumber);

            if (switchDoors) {
                chosenDoor = doors.stream()
                        .filter(door -> door.getNumber() != firstChosenDoorNumber && door.getNumber() != shownDoor.getNumber())
                        .findFirst().orElse(null);
            }

            if (chosenDoor != null && chosenDoor.getValue().equals(Prize.CAR)) {
                timesPlayerWin++;
            }
        }

        return timesPlayerWin;
    }

    protected Door shownDoor(int chosenDoorNumber, List<Door> doors) {
        return doors.stream().filter(door -> door.getValue().equals(Prize.GOAT) && door.getNumber() != chosenDoorNumber).findFirst().orElse(null);
    }

    protected List<Door> doors() {
        Random random = new Random();
        List<Door> doors = List.of(
                new Door(1, Prize.GOAT),
                new Door(2, Prize.GOAT),
                new Door(3, Prize.GOAT));
        doors.get(random.nextInt(3)).setValue(Prize.CAR);
        return doors;
    }
}


