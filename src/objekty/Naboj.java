package objekty;

import bytosti.Bytosti;
import bytosti.GameObjects;
import fri.shapesge.Kruh;
import fri.shapesge.Manazer;
import hra.Kolizie;
import hra.Smer;

import java.util.List;

/**
 * Trieda ktora vytvara sip.
 * 
 * @author Matej Ostrožovič
 * @version (a version number or a date)
 */
public class Naboj implements GameObjects {
    private Kolizie kolizia;
    private Kruh naboj;
    private int poziciaX;
    private int poziciaY;
    private Smer smer;
    private Manazer let;
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
        this.let = new Manazer();
        this.let.spravujObjekt(this);
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
    }

    /**
     * Metóda, ktorá vykresľuje šíp do zadaneho smeru a kontroluje hranice.
     */
    public void vystrel(Smer smer) {
        this.smer = smer;
        if (smer.equals(Smer.HORE)) {
            if (this.getPoziciaY() < 100) {
                this.vymazSip();
            } else {
                this.naboj.posunZvisle(-1);
                this.poziciaY -= 1;
            }
        } else if (smer.equals(Smer.DOLE)) {
            if (this.getPoziciaY() > 600) {
                this.vymazSip();
            } else {
                this.naboj.posunZvisle(1);
                this.poziciaY += 1;
            }
        } else if (smer.equals(Smer.VPRAVO)) {
            if (this.getPoziciaX() > 800) {
                this.vymazSip();
            } else {
                this.naboj.posunVodorovne(1);
                this.poziciaX += 1;
            }
        } else if (smer.equals(Smer.VLAVO)) {
            if (this.getPoziciaX() < 0) {
                this.vymazSip();
            } else {
                this.naboj.posunVodorovne(-1);
                this.poziciaX -= 1;
            }
        } else if (smer.equals(Smer.HORE_VPRAVO)) {
            if (this.getPoziciaX() < 0 || this.getPoziciaY() < 100) {
                this.vymazSip();
            } else {
                this.naboj.posunZvisle(-1);
                this.naboj.posunVodorovne(1);
                this.poziciaX += 1;
                this.poziciaY -= 1;
            }
        } else if (smer.equals(Smer.HORE_VLAVO)) {
            if (this.getPoziciaX() < 0 || this.getPoziciaY() < 100) {
                this.vymazSip();
            } else {
                this.naboj.posunZvisle(-1);
                this.naboj.posunVodorovne(-1);
                this.poziciaX -= 1;
                this.poziciaY -= 1;
            }
        } else if (smer.equals(Smer.DOLE_VPRAVO)) {
            if (this.getPoziciaX() > 800 || this.getPoziciaY() > 600) {
                this.vymazSip();
            } else {
                this.naboj.posunZvisle(1);
                this.naboj.posunVodorovne(1);
                this.poziciaX += 1;
                this.poziciaY += 1;
            }
        } else if (smer.equals(Smer.DOLE_VLAVO)) {
            if (this.getPoziciaX() > 800 || this.getPoziciaY() > 600) {
                this.vymazSip();
            } else {
                this.naboj.posunZvisle(1);
                this.naboj.posunVodorovne(-1);
                this.poziciaX -= 1;
                this.poziciaY += 1;
            }
        }
    }
    /**
     * Tik nastaveny na 8ms, ktory vyvoláva metódu vystrel
     */
    public void tikSip() {
        this.vystrel(this.smer);
    }

    /**
     * Metóda na vrátenie pozície x.
     * 
     * @return int vracia X-ovú pozíciu.
     */
    public int getPoziciaX() {
        return this.poziciaX;
    }

    /**
     * Metóda na vrátenie pozície y.
     * 
     * @return int vracia Y-ovú pozíciu.
     */
    public int getPoziciaY() {
        return this.poziciaY;
    }

    public int getPoskodenie() {
        return this.poskodenie;
    }

    public void setPoskodenie(int poskodenie) {
        this.poskodenie = poskodenie;
    }
//    public void kolizia(List<Bytosti> zvere) {
//        for (Bytosti zver : zvere) {
//            if (this.kolizia.kolizia(zver, this)) {
//                zver.setZivoty(this.poskodenie);
//                if (zver.getZivoty() == 0) {
//                    zver.zabitaZver();
//                    this.vymazSip();
//                    break;
//                }
//                this.vymazSip();
//                break;
//            }
//        }
//
//    }

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
        this.let.prestanSpravovatObjekt(this);
        this.stav = false;
    }

}