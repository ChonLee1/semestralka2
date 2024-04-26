package bytosti;

import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
import java.util.Random;
/**
 * Vytvára triedu zver, ktora sa náhodne pohybuje po plátne.
 * 
 * @author Matej Ostrožovič
 * @version (a version number or a date)
 */
public class Jelen implements Zver {
    private Obrazok jelen;
    private Manazer manazer;
    private Random random;
    private int x;
    private int y; 
    private int smer;
    private boolean stav;
    private int casZmeni;
    /**
     * Konštruktor triedy bytosti.Zver na náhodnej pozícii.
     */
    public Jelen() {
        this.jelen = new Obrazok("Obrazky\\jelen_hore.png", this.x, this.y);
        this.random = new Random();
        this.manazer = new Manazer();
        this.jelen.zobraz();
        this.manazer.spravujObjekt(this);
        this.x = this.random.nextInt(450) + 100;
        this.y = this.random.nextInt(450) + 100;
        this.jelen.posunVodorovne(this.x);
        this.jelen.posunZvisle(this.y);
        this.smer = this.random.nextInt(4);
        this.stav = true;
        this.casZmeni = 0;
    }

    /**
     * Metóda na posun smerom hore. 
     */
    public void pohybHore() {
        this.smer = 0;
        this.jelen.zmenObrazok("Obrazky\\jelen_hore.png");
        if (this.y == 100) {
            this.y = 600;
            this.jelen.posunZvisle(500);
        } else {
            this.jelen.posunZvisle(-1);
            this.y -= 1;
        }   
    }

    /**
     * Metóda na posun smerom dolu.
     */
    public void pohybDolu() {
        this.smer = 2;
        this.jelen.zmenObrazok("Obrazky\\jelen_dole.png");
        if (this.y == 600) {
            this.y = 100;
            this.jelen.posunZvisle(-500);
        } else {
            this.jelen.posunZvisle(1);
            this.y += 1;
        }    
    }

    /**
     * Metóda na posun smerom vpravo.
     */
    public void pohybVpravo() {
        this.smer = 1;
        this.jelen.zmenObrazok("Obrazky\\jelen_vpravo.png");
        if (this.x == 800) {
            this.x = 0;
            this.jelen.posunVodorovne(-800);
        } else {
            this.jelen.posunVodorovne(1);
            this.x += 1;
        }
    }

    /**
     * Metóda na posun smerom vľavo.
     */
    public void pohybVlavo() {
        this.smer = 3;
        this.jelen.zmenObrazok("Obrazky\\jelen_vlavo.png");
        if (this.x == 0) {
            this.x = 600;
            this.jelen.posunVodorovne(600);
        } else {
            this.jelen.posunVodorovne(-1);
            this.x -= 1;
        }
    }
    
    /**
     * Metóda, ktorá podľa náhodne vybraného smeru určí, ktorým smerom má zver ísť.
     */
    public void pohyb() {
        switch (this.smer) {
            case 0:
                this.pohybHore();
                break;
            case 1:
                this.pohybVpravo();
                break;
            case 2:
                this.pohybDolu();
                break;
            case 3:
                this.pohybVlavo();
                break;
        }
    }

    /**
     * Tik nastavený na 50ms. Každých 5 sekúnd zmení náhodne smer.
     */
    public void tikZver() {        
        this.pohyb();
        this.casZmeni += 1;
        if (this.casZmeni == 100) {
            this.smer = this.random.nextInt(4);
            this.casZmeni = 0;
        }
    }

    /**
     * Metóda na vrátenie pozicie x.
     * 
     * @return int vracia x-ovú pozíciu zveri.
     */
    public int getPoziciaX() {
        return this.x;
    }

    /**
     * Metóda na vrátenie pozície y.
     * 
     * @return int vracia y-ovú pozíciu zveri.
     */
    public int getPoziciaY() {
        return this.y;
    }

    /**
     * Metóda na vrátenie atribútu stav.
     * 
     * @return boolean vracia boolean hodnotu stavu.
     */
    public boolean getStav() {
        return this.stav;
    }

    /**
     * Metóda, vykoná potrebné kroky na odstránenie zveri.
     */
    public void zabitaZver() {
        this.jelen.zmenObrazok("Obrazky\\mrtvy_jelen.png");
        this.manazer.prestanSpravovatObjekt(this);
        this.stav = false;
    }
    
    /**
     * Metóda, ktorá schová obrázok z plátna.
     */
    public void skryZver() {
        this.jelen.skry();
    }
}