package bytosti;

public abstract class GameObjects {
    private int poziciaX;
    private int poziciaY;

    public GameObjects() {
        this.poziciaX = 0;
        this.poziciaY = 0;
    }

    public int getPoziciaY() {
        return this.poziciaY;
    }

    public int getPoziciaX() {
        return this.poziciaX;
    }
}
