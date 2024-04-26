import fri.shapesge.Obdlznik;
import fri.shapesge.Manazer;
/**
 * Trieda ktora vytvara sip.
 * 
 * @author Matej Ostrožovič
 * @version (a version number or a date)
 */
public class Sip {
    private Obdlznik sip;
    private int poziciaX;
    private int poziciaY;
    private Smer smer;
    private Manazer let;
    private boolean stav;
    /**
     * Konštruktor triedy Sip na pozícii zadanej z parametrov x a y.
     * 
     * @param int zadáva pozíciu, kde sa má zobraziť na ose x.
     * @param int zadáva pozíciu, kde sa má zobraziť na ose y.
     */
    public Sip(int x, int y) {
        this.sip = new Obdlznik();
        this.let = new Manazer();
        this.let.spravujObjekt(this);
        this.sip.zmenStrany(2, 10);
        this.sip.zmenFarbu("brown");
        this.sip.posunVodorovne(-60 + x);
        this.sip.posunZvisle(-50 + y);
        this.stav = true;
        this.poziciaX = x;
        this.poziciaY = y;
        this.sip.zobraz();
    }

    /**
     * Metóda, ktorá vykresľuje šíp do zadaneho smeru a kontroluje hranice.
     */
    public void vystrel(Smer smer) {
        this.smer = smer;
        if (smer.equals(Smer.HORE)) {
            if (this.getPoziciaSipY() < 100) {
                this.vymazSip();
            } else {
                this.sip.posunZvisle(-1);
                this.poziciaY -= 1;
            }
        } else if (smer.equals(Smer.DOLE)) {
            if (this.getPoziciaSipY() > 600) {
                this.vymazSip();
            } else {
                this.sip.posunZvisle(1);
                this.poziciaY += 1;
            }
        } else if (smer.equals(Smer.VPRAVO)) {
            if (this.getPoziciaSipX() > 800) {
                this.vymazSip();
            } else {
                this.sip.posunVodorovne(1);
                this.poziciaX += 1;
            }
        } else if (smer.equals(Smer.VLAVO)) {
            if (this.getPoziciaSipX() < 0) {
                this.vymazSip();
            } else {
                this.sip.posunVodorovne(-1);
                this.poziciaX -= 1;
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
     * Metóda, ktorá vymení strany obdĺžnika. 
     */
    public void zmenaStranySipu() {
        this.sip.zmenStrany(10, 2);
    }

    /**
     * Metóda na vrátenie pozície x.
     * 
     * @return int vracia X-ovú pozíciu.
     */
    public int getPoziciaSipX() {
        return this.poziciaX;
    }

    /**
     * Metóda na vrátenie pozície y.
     * 
     * @return int vracia Y-ovú pozíciu.
     */
    public int getPoziciaSipY() {
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
        this.sip.skry();
        this.let.prestanSpravovatObjekt(this);
        this.stav = false; 
    }
}