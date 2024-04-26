package bytosti;

import fri.shapesge.Obrazok;
import hra.Smer;
import objekty.Naboj;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;
/**
 * Vytvára postavu ktorú hrač dokaže ovladať podľa inputu z klávesnice.
 * 
 * @author Matej Ostrožovič 
 * @version 
 */
public class Postava {
    private boolean premennaPosunHore;
    private boolean premennaPosunDolu;
    private boolean premennaPosunVpravo;
    private boolean premennaPosunVlavo;
    private Obrazok postava;
    private int poziciaX;
    private int poziciaY;
    private List<Naboj> strely;
    private Smer smer;
    private Naboj sip;
    private Random random;
    /**
     * Konštruktor triedy bytosti.Postava, ktory vytvori obrazok lovca na poziciach x= 200 a y= 200
     * a prazdny Arraylist striel.
     */
    public Postava() {
        this.postava = new Obrazok("Obrazky\\lovec_hore.png", 200, 200);
        this.poziciaX = 200;
        this.poziciaY = 200;
        this.strely = new ArrayList();
        this.smer = Smer.HORE;
        this.postava.zobraz();
        this.premennaPosunHore = false;
        this.premennaPosunDolu = false;
        this.premennaPosunVpravo = false;
        this.premennaPosunVlavo = false;
    }

    /**
     * Metóda na vrátenie pozície y.
     */
    public int getPoziciaY() {
        return this.poziciaY; 
    }

    /**
     * Metóda na vrátenie pozície x.
     */
    public int getPoziciaX() {
        return this.poziciaX;    
    }

    /**
     * Metóda na vrátenie smeru.
     * 
     * @return hra.Smer vracia smer postavi.
     */
    public Smer getSmer() {
        return this.smer;
    }

    /**
     * Metóda na vrátenie ArrayListu strely.
     * 
     * @return List<objekty.Sip> vracia list striel.
     */
    public List<Naboj> getStrely() {
        return this.strely;
    }

    public void zaciatokPohybuHore() {
        this.premennaPosunHore = true;
    }
    public void koniecPohybuHore() {
        this.premennaPosunHore = false;
    }
    public void zaciatokPohybuDole() {
        this.premennaPosunDolu = true;
    }
    public void koniecPohybuDole() {
        this.premennaPosunDolu = false;
    }
    public void zaciatokPohybuVpravo() {
        this.premennaPosunVpravo = true;
    }
    public void koniecPohybuVpravo() {
        this.premennaPosunVpravo = false;
    }
    public void zaciatokPohybuVlavo() {
        this.premennaPosunVlavo = true;
    }
    public void koniecPohybuVlavo() {
        this.premennaPosunVlavo = false;
    }
    public void posunPostavy(String obrazok, int x, int y, Smer smer) {
        this.smer = smer;
        this.postava.zmenObrazok(obrazok);
        this.poziciaY += y;
        this.poziciaX += x;
        this.postava.posunVodorovne(x);
        this.postava.posunZvisle(y);
    }

    public void pohyb() {
        if (this.premennaPosunHore) {
            if (this.premennaPosunVpravo) {
                this.posunPostavy("Obrazky\\lovec_vpravo.png", 3, -3, Smer.HORE_VPRAVO);
            } else if (this.premennaPosunVlavo) {
                this.posunPostavy("Obrazky\\lovec_vlavo.png", -3, -3, Smer.HORE_VLAVO);
            } else {
                this.posunPostavy("Obrazky\\lovec_hore.png", 0, -3, Smer.HORE);
            }
            if (this.poziciaY < 100) {
                this.poziciaY = 600;
                this.postava.posunZvisle(500);
            }
        }

        if (this.premennaPosunDolu) {
            if (this.premennaPosunVpravo) {
                this.posunPostavy("Obrazky\\lovec_vpravo.png", 3, 3, Smer.DOLE_VPRAVO);
            } else if (this.premennaPosunVlavo) {
                this.posunPostavy("Obrazky\\lovec_vlavo.png", -3, 3, Smer.DOLE_VLAVO);
            } else {
                this.posunPostavy("Obrazky\\lovec_dole.png", 0, 3, Smer.DOLE);
            }

            if (this.poziciaY > 600) {
                this.poziciaY = 100;
                this.postava.posunZvisle(-500);
            }
        }
        if (this.premennaPosunVpravo) {
            if (this.premennaPosunHore) {
                this.posunPostavy("Obrazky\\lovec_vpravo.png", 3, -3, Smer.HORE_VPRAVO);
            } else if (this.premennaPosunDolu) {
                this.posunPostavy("Obrazky\\lovec_vpravo.png", 3, 3, Smer.DOLE_VPRAVO);
            } else {

                this.posunPostavy("Obrazky\\lovec_vpravo.png", 3, 0, Smer.VPRAVO);
            }
            if (this.poziciaX > 800) {
                this.poziciaX = 0;
                this.postava.posunVodorovne(-800);
            }
        }

        if (this.premennaPosunVlavo) {
            this.smer = Smer.VLAVO;
            this.postava.zmenObrazok("Obrazky\\lovec_vlavo.png");
            if (this.premennaPosunHore) {
                this.posunPostavy("Obrazky\\lovec_vlavo.png", -3, -3, Smer.HORE_VLAVO);
            } else if (this.premennaPosunDolu) {
                this.posunPostavy("Obrazky\\lovec_vlavo.png", -3, 3, Smer.DOLE_VLAVO);
            } else {
                this.posunPostavy("Obrazky\\lovec_vlavo.png", -3, 0, Smer.VLAVO);
            }
            if (this.poziciaX < 0) {
                this.poziciaX = 800;
                this.postava.posunVodorovne(800);
            }
        }
    }

    /**
     * Metóda na vytvorenie inštancie objekty.Sip po stlačení klávesy "Space".
     * Pridanie danej inštancie do ArrayListu "strely" a posun určený podľa ENUMU "hra.Smer".
     */
    public void aktivuj() {
        if (this.getSmer().equals(Smer.HORE)) {
            this.sip = new Naboj(this.getPoziciaX(), this.getPoziciaY() - 15);
        } else if (this.getSmer().equals(Smer.DOLE)) {
            this.sip = new Naboj(this.getPoziciaX(), this.getPoziciaY() + 15);
        } else if (this.getSmer().equals(Smer.VLAVO)) {
            this.sip = new Naboj(this.getPoziciaX() - 15, this.getPoziciaY());
        } else if (this.getSmer().equals(Smer.VPRAVO)) {
            this.sip = new Naboj(this.getPoziciaX() + 15, this.getPoziciaY());
        } else if (this.getSmer().equals(Smer.HORE_VPRAVO)) {
            this.sip = new Naboj(this.getPoziciaX() + 15, this.getPoziciaY() - 15);
        } else if (this.getSmer().equals(Smer.HORE_VLAVO)) {
            this.sip = new Naboj(this.getPoziciaX() - 15, this.getPoziciaY() - 15);
        } else if (this.getSmer().equals(Smer.DOLE_VPRAVO)) {
            this.sip = new Naboj(this.getPoziciaX() + 15, this.getPoziciaY() + 15);
        } else if (this.getSmer().equals(Smer.DOLE_VLAVO)) {
            this.sip = new Naboj(this.getPoziciaX() - 15, this.getPoziciaY() + 15);
        }

        this.sip.vystrel(this.getSmer());
        this.strely.add(this.sip);
    }
}