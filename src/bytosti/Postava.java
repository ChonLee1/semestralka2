package bytosti;

import fri.shapesge.Obrazok;
import hra.Smer;
import objekty.Prak;
import objekty.Zbran;



/**
 * Vytvára postavu ktorú hrač dokaže ovladať podľa inputu z klávesnice.
 * 
 * @author Matej Ostrožovič 
 * @version 
 */
public class Postava extends GameObjects {

    private Zbran typZbrane;
    private boolean premennaPosunHore;
    private boolean premennaPosunDolu;
    private boolean premennaPosunVpravo;
    private boolean premennaPosunVlavo;
    private Obrazok postava;
    private int poziciaX;
    private int poziciaY;

    private Smer smer;
    private int zivoty;
    /**
     * Konštruktor triedy bytosti.Postava, ktory vytvori obrazok lovca na poziciach x= 200 a y= 200
     * a prazdny Arraylist striel.
     */
    public Postava() {
        super();
        this.postava = new Obrazok("Obrazky\\lovec_hore.png", 200, 200);
        this.poziciaX = 200;
        this.poziciaY = 200;
        this.smer = Smer.HORE;
        this.zivoty = 10;
        this.postava.zobraz();

        this.premennaPosunHore = false;
        this.premennaPosunDolu = false;
        this.premennaPosunVpravo = false;
        this.premennaPosunVlavo = false;

        this.typZbrane = new Prak("prak", 1, 1);
    }
    /**
     * Metóda na vrátenie smeru.
     * 
     * @return hra.Smer vracia smer postavi.
     */
    public Smer getSmer() {
        return this.smer;
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
    private void kontrolaHranic() {
        if (this.poziciaX < 0) {
            this.poziciaX = 800;
            this.postava.posunVodorovne(800);
        } else if (this.poziciaX > 800) {
            this.poziciaX = 0;
            this.postava.posunVodorovne(-800);
        }

        if (this.poziciaY < 100) {
            this.poziciaY = 600;
            this.postava.posunZvisle(500);
        } else if (this.poziciaY > 600) {
            this.poziciaY = 100;
            this.postava.posunZvisle(-500);
        }
    }
    public void posunPostavy(String obrazok, int x, int y, Smer smer) {
        this.smer = smer;
        this.postava.zmenObrazok(obrazok);
        this.poziciaY += y;
        this.poziciaX += x;
        this.postava.posunVodorovne(x);
        this.postava.posunZvisle(y);
        this.kontrolaHranic();
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
        }
        if (this.premennaPosunDolu) {
            if (this.premennaPosunVpravo) {
                this.posunPostavy("Obrazky\\lovec_vpravo.png", 1, 1, Smer.DOLE_VPRAVO);
            } else if (this.premennaPosunVlavo) {
                this.posunPostavy("Obrazky\\lovec_vlavo.png", -1, 1, Smer.DOLE_VLAVO);
            } else {
                this.posunPostavy("Obrazky\\lovec_dole.png", 0, 3, Smer.DOLE);
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
        }
        if (this.premennaPosunVlavo) {
            if (this.premennaPosunHore) {
                this.posunPostavy("Obrazky\\lovec_vlavo.png", -1, -1, Smer.HORE_VLAVO);
            } else if (this.premennaPosunDolu) {
                this.posunPostavy("Obrazky\\lovec_vlavo.png", -1, 1, Smer.DOLE_VLAVO);
            } else {
                this.posunPostavy("Obrazky\\lovec_vlavo.png", -3, 0, Smer.VLAVO);
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

    public int getZivoty() {
        return this.zivoty;
    }

    public void setZivoty(int zivoty) {
        this.zivoty = zivoty;
    }

    public Zbran getTypZbrane() {
        return this.typZbrane;
    }

    public void setTypZbrane(Zbran typZbrane) {
        this.typZbrane = typZbrane;
    }
    public void zobraz() {
        this.postava.zobraz();
     }
}