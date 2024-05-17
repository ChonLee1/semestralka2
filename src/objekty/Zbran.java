package objekty;

import bytosti.Postava;
import fri.shapesge.Manazer;
import hra.HerneData;
import hra.Smer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Zbran {
    private List<Postava> charakter;
    private String meno;
    private int poskodenie;
    private int zasobnik;
    private Map<Smer, HerneData.NabojSupplier> smerToNaboj;
    private Manazer manazer;
    private HerneData data;
    private Naboj naboj;

    public Zbran(String meno, int poskodenie, int zasobnik) {
        this.meno = meno;
        this.data = HerneData.getInstance();
        this.poskodenie = poskodenie;
        this.zasobnik = zasobnik;
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        this.charakter = this.data.getPostava();
        this.smerToNaboj = this.data.getSmerToNaboj();
    }
    @FunctionalInterface
    public interface NabojSupplier {
        Naboj get();
    }
    public String getMeno() {
        return this.meno;
    }
    public int getZasobnik() {
        return this.zasobnik;
    }
    public List<Postava> getCharakter() {
        return this.charakter;
    }
    public int getPoskodenie() {
        return this.poskodenie;
    }
    public void prebyZasobnik() {

    }
    public void vypisZasobnik() {

    }
    public void tikAktualizacia() {
        this.charakter = this.data.getPostava();
    }
    public void vystrel() {
        this.naboj = this.smerToNaboj.get(this.charakter.get(0).getSmer()).get();
        this.naboj.vystrel(this.charakter.get(0).getSmer());
    }
}