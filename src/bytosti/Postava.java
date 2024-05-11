package bytosti;

import fri.shapesge.Obrazok;
import hra.Smer;
import objekty.Naboj;
import objekty.Prak;
import objekty.Zbran;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.List;
import java.util.function.Supplier;

/**
 * Vytvára postavu ktorú hrač dokaže ovladať podľa inputu z klávesnice.
 * 
 * @author Matej Ostrožovič 
 * @version 
 */
public class Postava {
    private HashMap<Smer, Supplier<Naboj>> smerToNaboj;
    private Zbran typZbrane;
    private boolean premennaPosunHore;
    private boolean premennaPosunDolu;
    private boolean premennaPosunVpravo;
    private boolean premennaPosunVlavo;
    private Obrazok postava;
    private int poziciaX;
    private int poziciaY;
    private List<Naboj> strely;
    private Smer smer;
    private Naboj naboj;
    private Random random;
    private int zivoty;
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
        this.zivoty = 10;
        this.postava.zobraz();

        this.premennaPosunHore = false;
        this.premennaPosunDolu = false;
        this.premennaPosunVpravo = false;
        this.premennaPosunVlavo = false;


        this.typZbrane = new Prak("prak", 1, 1);

        this.smerToNaboj = new HashMap<>();
        this.smerToNaboj.put(Smer.HORE, () -> new Naboj(this.getPoziciaX() + 15, this.getPoziciaY(), this.typZbrane));
        this.smerToNaboj.put(Smer.DOLE, () -> new Naboj(this.getPoziciaX() + 15, this.getPoziciaY() + 15, this.typZbrane));
        this.smerToNaboj.put(Smer.VLAVO, () -> new Naboj(this.getPoziciaX(), this.getPoziciaY() + 15, this.typZbrane));
        this.smerToNaboj.put(Smer.VPRAVO, () -> new Naboj(this.getPoziciaX() + 30, this.getPoziciaY() + 15, this.typZbrane));
        this.smerToNaboj.put(Smer.HORE_VPRAVO, () -> new Naboj(this.getPoziciaX() + 30, this.getPoziciaY() + 15, this.typZbrane));
        this.smerToNaboj.put(Smer.HORE_VLAVO, () -> new Naboj(this.getPoziciaX(), this.getPoziciaY() + 15, this.typZbrane));
        this.smerToNaboj.put(Smer.DOLE_VPRAVO, () -> new Naboj(this.getPoziciaX() + 30, this.getPoziciaY() + 15, this.typZbrane));
        this.smerToNaboj.put(Smer.DOLE_VLAVO, () -> new Naboj(this.getPoziciaX(), this.getPoziciaY() + 15, this.typZbrane));
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
                this.posunPostavy("Obrazky\\lovec_vpravo.png", 1, -1, Smer.HORE_VPRAVO);
            } else if (this.premennaPosunVlavo) {
                this.posunPostavy("Obrazky\\lovec_vlavo.png", -1, -1, Smer.HORE_VLAVO);
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
                this.posunPostavy("Obrazky\\lovec_vpravo.png", 1, 1, Smer.DOLE_VPRAVO);
            } else if (this.premennaPosunVlavo) {
                this.posunPostavy("Obrazky\\lovec_vlavo.png", -1, 1, Smer.DOLE_VLAVO);
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
                this.posunPostavy("Obrazky\\lovec_vpravo.png", 1, -1, Smer.HORE_VPRAVO);
            } else if (this.premennaPosunDolu) {
                this.posunPostavy("Obrazky\\lovec_vpravo.png", 1, 1, Smer.DOLE_VPRAVO);
            } else {
                this.posunPostavy("Obrazky\\lovec_vpravo.png", 3, 0, Smer.VPRAVO);
            }
            if (this.poziciaX > 800) {
                this.poziciaX = 0;
                this.postava.posunVodorovne(-800);
            }
        }
        if (this.premennaPosunVlavo) {
            if (this.premennaPosunHore) {
                this.posunPostavy("Obrazky\\lovec_vlavo.png", -1, -1, Smer.HORE_VLAVO);
            } else if (this.premennaPosunDolu) {
                this.posunPostavy("Obrazky\\lovec_vlavo.png", -1, 1, Smer.DOLE_VLAVO);
            } else {
                this.posunPostavy("Obrazky\\lovec_vlavo.png", -3, 0, Smer.VLAVO);
            }
            if (this.poziciaX < 0) {
                this.poziciaX = 800;
                this.postava.posunVodorovne(800);
            }
        }
    }

    public void setTypZbrane(Zbran typZbrane) {
        this.typZbrane = typZbrane;
    }

    /**
     * Metóda na vytvorenie inštancie objekty.Sip po stlačení klávesy "Space".
     * Pridanie danej inštancie do ArrayListu "strely" a posun určený podľa ENUMU "hra.Smer".
     */
    public void aktivuj() {
        this.naboj = this.smerToNaboj.get(this.getSmer()).get();
        this.naboj.vystrel(this.getSmer());
        this.strely.add(this.naboj);
        // TODO: urobit zasobnik a prebijanie do metody aktivuj, pocitadlo na zosobnik a potom pocitadlo na prebytie
    }
}