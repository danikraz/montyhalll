package backend.montyhall;

public class Door {
    private final int number;
    private Prize prize;

    public Door(int number, Prize value) {
        this.number = number;
        this.prize = value;
    }

    public void setValue(Prize value) {
        this.prize = value;
    }

    public Prize getPrize() {
        return prize;
    }

    public int getNumber() {
        return number;
    }
}
