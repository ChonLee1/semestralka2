package objekty;

public enum Zbrane {
    BROKOVNICA("brokovnica", 3, 2),
    MUSKETA("musketa", 2, 3),
    PRAK("prak", 1, 999);

    private final String meno;
    private final int poskodenie;
    private final int zasobnik;

    Zbrane(String meno, int poskodenie, int zasobnik) {
        this.meno = meno;
        this.poskodenie = poskodenie;
        this.zasobnik = zasobnik;
    }

    public String getMeno() {
        return this.meno;
    }

    public int getPoskodenie() {
        return this.poskodenie;
    }

    public int getZasobnik() {
        return this.zasobnik;
    }
}
