package backend.montyhall;

enum Prize {
    GOAT,
    CAR
}

public class Door {
    private final int number;
    private Prize value;

    public Door(int number, Prize value) {
        this.number = number;
        this.value = value;
    }

    public void setValue(Prize value) {
        this.value = value;
    }

    public Prize getValue() {
        return value;
    }

    public int getNumber() {
        return number;
    }
}
