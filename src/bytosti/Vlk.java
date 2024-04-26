package bytosti;

import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;

import java.util.Random;

public class Vlk implements Zver {
    private Obrazok vlk;
    private Manazer manazer;
    private Random random;
    private int x;
    private int y;
    private int smer;
    private boolean stav;
    private int casZmeni;

    public Vlk() {
        this.vlk = new Obrazok("Obrazky\\vlk_hore.png", this.x, this.y);
        this.manazer = new Manazer();
        this.random = new Random();
        this.vlk.zobraz();
        this.manazer.spravujObjekt(this);
        this.x = this.random.nextInt(450) + 100;
        this.y = this.random.nextInt(450) + 100;
        this.vlk.posunVodorovne(this.x);
        this.vlk.posunZvisle(this.y);
        this.smer = this.random.nextInt(4);
        this.stav = true;
        this.casZmeni = 0;
    }

    @Override
    public void pohybHore() {
        this.smer = 0;
        this.vlk.zmenObrazok("Obrazky\\vlk_hore.png");
        if (this.y == 100) {
            this.y = 600;
            this.vlk.posunZvisle(500);
        } else {
            this.vlk.posunZvisle(-1);
            this.y -= 1;
        }
    }

    @Override
    public void pohybDolu() {
        this.smer = 2;
        this.vlk.zmenObrazok("Obrazky\\vlk_dole.png");
        if (this.y == 600) {
            this.y = 100;
            this.vlk.posunZvisle(-500);
        } else {
            this.vlk.posunZvisle(1);
            this.y += 1;
        }
    }

    @Override
    public void pohybVpravo() {
        this.smer = 1;
        this.vlk.zmenObrazok("Obrazky\\vlk_vpravo.png");
        if (this.x == 800) {
            this.x = 0;
            this.vlk.posunVodorovne(-800);
        } else {
            this.vlk.posunVodorovne(1);
            this.x += 1;
        }
    }

    @Override
    public void pohybVlavo() {
        this.smer = 3;
        this.vlk.zmenObrazok("Obrazky\\vlk_vlavo.png");
        if (this.x == 0) {
            this.x = 600;
            this.vlk.posunVodorovne(600);
        } else {
            this.vlk.posunVodorovne(-1);
            this.x -= 1;
        }
    }

    @Override
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
    public void tikZver() {
        this.pohyb();
        this.casZmeni += 1;
        if (this.casZmeni == 100) {
            this.smer = this.random.nextInt(4);
            this.casZmeni = 0;
        }
    }

    @Override
    public int getPoziciaX() {
        return this.x;
    }

    @Override
    public int getPoziciaY() {
        return this.y;
    }

    @Override
    public boolean getStav() {
        return this.stav;
    }

    @Override
    public void zabitaZver() {
        this.vlk.zmenObrazok("Obrazky\\mrtvy_jelen.png");
        this.manazer.prestanSpravovatObjekt(this);
        this.stav = false;
    }

    @Override
    public void skryZver() {
        this.vlk.skry();
    }
}
