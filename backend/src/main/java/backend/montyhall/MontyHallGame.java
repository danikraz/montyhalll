package backend.montyhall;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Service
public class MontyHallGame {
    private final Random random = new Random();

    public int wins(int numberOfSimulatins, boolean switchDoors) {
        AtomicInteger timesPlayerWin = new AtomicInteger();

        IntStream.range(0, numberOfSimulatins).parallel().forEach(simulation -> {
            var doors = shuffledDoors();
            var randomlyChosenDoorNumber = random.nextInt(3) + 1;
            timesPlayerWin.addAndGet(simulateGame(doors, randomlyChosenDoorNumber, switchDoors));
        });

        return timesPlayerWin.get();
    }

    protected int simulateGame(List<Door> doors, int chosenDoorNumber, boolean switchDoors) {
        var chosenDoor = chosenDoor(chosenDoorNumber, doors);
        var shownDoor = shownDoor(chosenDoorNumber, doors);

        if (switchDoors) {
            chosenDoor = doors.stream()
                    .filter(door -> door.getNumber() != chosenDoorNumber && door.getNumber() != shownDoor.getNumber())
                    .findFirst().orElseThrow(NoSuchElementException::new);
        }
        return evaluate(chosenDoor);
    }

    protected int evaluate(Door door) {
        if (door.getPrize() == Prize.CAR) {
            return 1;
        } else return 0;
    }

    protected Door chosenDoor(int chosenDoorNumber, List<Door> doors) {
        return doors.stream().filter(door -> door.getNumber() == chosenDoorNumber).findFirst().orElseThrow(NoSuchElementException::new);
    }

    protected Door shownDoor(int excludedDoorNumber, List<Door> doors) {
        return doors.stream().filter(door -> door.getPrize() == Prize.GOAT && door.getNumber() != excludedDoorNumber).findFirst().orElseThrow(NoSuchElementException::new);
    }

    protected List<Door> shuffledDoors() {
        var doors = List.of(
                new Door(1, Prize.GOAT),
                new Door(2, Prize.GOAT),
                new Door(3, Prize.GOAT));
        doors.get(random.nextInt(3)).setValue(Prize.CAR);
        return doors;
    }
}


