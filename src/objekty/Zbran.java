package objekty;

public abstract class Zbran {
    private String meno;
    private int poskodenie;
    private int zasobnik;

    public Zbran(String meno, int poskodenie, int zasobnik) {
        this.meno = meno;
        this.poskodenie = poskodenie;
        this.zasobnik = zasobnik;
    }

    public String getMeno() {
        return this.meno;
    }

    public int getZasobnik() {
        return this.zasobnik;
    }

    public int getPoskodenie() {
        return this.poskodenie;
    }

}
