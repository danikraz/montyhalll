package backend.montyhall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MontyHallController {

    @Autowired
    private MontyHallGame montyHallGame;

    @GetMapping("/wins")
    public int getWins(@RequestParam int numberOfSimulations, @RequestParam boolean switchDoors) {
        return montyHallGame.wins(numberOfSimulations, switchDoors);
    }
}
