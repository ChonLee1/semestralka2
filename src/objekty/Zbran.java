package objekty;

import hra.Smer;

import java.util.HashMap;
import java.util.function.Supplier;

public abstract class Zbran {
    private String meno;
    private int poskodenie;
    private int zasobnik;
    private HashMap<Smer, Supplier<Naboj>> smerToNaboj;

    public Zbran(String meno, int poskodenie, int zasobnik) {
        this.meno = meno;
        this.poskodenie = poskodenie;
        this.zasobnik = zasobnik;
        this.smerToNaboj = new HashMap<>();
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
    public void setZasobnik(int zasobnik) {
        this.zasobnik = zasobnik;
    }
    public void setPoskodenie(int poskodenie) {
        this.poskodenie = poskodenie;
    }
    public void prebyZasobnik() {

    }
    public void vypisZasobnik() {

    }
    public Naboj vystrel() {
        return null;
    }
}