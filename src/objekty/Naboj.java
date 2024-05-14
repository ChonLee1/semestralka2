package objekty;

import bytosti.Bytosti;
import bytosti.GameObjects;
import fri.shapesge.Kruh;
import fri.shapesge.Manazer;
import hra.HerneData;
import hra.Kolizie;
import hra.Smer;

import java.util.List;

/**
 * Trieda ktora vytvara sip.
 * 
 * @author Matej Ostrožovič
 * @version (a version number or a date)
 */
public class Naboj extends GameObjects {
    private List<Bytosti> zver;
    private HerneData data;
    private Kolizie kolizia;
    private Kruh naboj;
    private int poziciaX;
    private int poziciaY;
    private Smer smer;
    private Manazer manazer;
    private boolean stav;
    private int poskodenie;
    /**
     * Konštruktor triedy objekty.Sip na pozícii zadanej z parametrov x a y.
     * 
     * @param int zadáva pozíciu, kde sa má zobraziť na ose x.
     * @param int zadáva pozíciu, kde sa má zobraziť na ose y.
     */
    public Naboj(int x, int y) {
        this.naboj = new Kruh();
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        this.naboj.zmenPriemer(5);
        this.naboj.zmenFarbu("brown");
        this.naboj.posunVodorovne(-20 + x);
        this.naboj.posunZvisle(-60 + y);
        this.stav = true;
        this.poziciaX = x;
        this.poziciaY = y;
        this.naboj.zobraz();
        this.poskodenie = 1;
        this.kolizia = new Kolizie();
        this.data = HerneData.getInstance();
        this.zver = this.data.getZvere();
    }
    public void pohyb(int x, int y) {
        this.naboj.posunVodorovne(x);
        this.naboj.posunZvisle(y);
        this.poziciaX += x;
        this.poziciaY += y;
        this.sledovanieHranic();
    }
    private void sledovanieHranic() {
        if (this.poziciaX < 0 || this.poziciaX > 800 || this.poziciaY < 100 || this.poziciaY > 600) {
            this.vymazSip();
        }
    }
    public void vystrel(Smer smer) {
        this.kolizia(this.zver);
        this.smer = smer;
        switch (smer) {
            case HORE:
                this.pohyb(0, -1);
                break;
            case DOLE:
                this.pohyb(0, 1);
                break;
            case VPRAVO:
                this.pohyb(1, 0);
                break;
            case VLAVO:
                this.pohyb(-1, 0);
                break;
            case HORE_VPRAVO:
                this.pohyb(1, -1);
                break;
            case HORE_VLAVO:
                this.pohyb(-1, -1);
                break;
            case DOLE_VPRAVO:
                this.pohyb(1, 1);
                break;
            case DOLE_VLAVO:
                this.pohyb(-1, 1);
                break;
        }
    }
    /**
     * Tik nastaveny na 8ms, ktory vyvoláva metódu vystrel
     */
    public void tikSip() {
        this.zver = this.data.getZvere();
        this.vystrel(this.smer);
    }
    public int getPoskodenie() {
        return this.poskodenie;
    }
    public void setPoskodenie(int poskodenie) {
        this.poskodenie = poskodenie;
    }
    public void kolizia(List<Bytosti> zvere) {
        for (Bytosti zviera : zvere) {
            if (this.kolizia.kolizia(zviera, this)) {
                zviera.setZivoty(this.poskodenie);
                if (zviera.getZivoty() == 0) {
                    if (zviera instanceof bytosti.Vlk) {
                        this.data.zvysSkore(20);
                    } else if (zviera instanceof bytosti.Jelen) {
                        this.data.zvysSkore(10);
                    } else if (zviera instanceof bytosti.Srnka) {
                        this.data.zvysSkore(5);
                    } else if (zviera instanceof bytosti.Vlkodlak) {
                        this.data.zvysSkore(30);
                    }
                    zviera.zabitaZver();
                    this.vymazSip();
                    break;
                }
                this.vymazSip();
                break;
            }
        }
    }

    @Override
    public int getPoziciaX() {
        return this.poziciaX;
    }

    @Override
    public int getPoziciaY() {
        return this.poziciaY;
    }

    /**
     * Metóda na vrátenie atribútu stav.
     *
     * @return boolean vracia stav šípu.
     */
    public boolean getStav() {
        return this.stav;
    }
    /**
     * Metóda, ktorá vymaže šíp.
     */
    public void vymazSip() {
        this.naboj.skry();
        this.manazer.prestanSpravovatObjekt(this);
        this.stav = false;
    }
    public void pause() {
        this.manazer.prestanSpravovatObjekt(this);
    }
    public void play() {
        this.manazer.spravujObjekt(this);
    }
    public void zobrazenie() {
        this.naboj.zobraz();
    }
}