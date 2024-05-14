package bytosti;

import fri.shapesge.Obrazok;
import hra.GeneratorObrazkov;
import java.util.ArrayList;
import java.util.Random;

public abstract class Bytosti implements GameObjects, Zver {
    private boolean urobene;
    private GeneratorObrazkov generatorObrazkov;
    private Obrazok obrazok;
    private Random random;
    private int x;
    private int y;
    private int smer;
    private boolean stav;
    private int casZmeni;
    private int zivoty;
    private ArrayList<String> zoznamObrazkov;

    public Bytosti(String obrazok, int zivoty) {
        this.generatorObrazkov = new GeneratorObrazkov();
        this.zoznamObrazkov = this.generatorObrazkov.generujObrazok(obrazok);
        this.random = new Random();
        this.x = this.random.nextInt(450) + 100;
        this.y = this.random.nextInt(450) + 100;
        this.obrazok = new Obrazok(this.zoznamObrazkov.get(0), this.x, this.y);
        this.smer = this.random.nextInt(8);
        this.stav = true;
        this.casZmeni = 0;
        this.zivoty = zivoty;
        this.urobene = false;
        this.obrazok.zobraz();
    }

    private void checkBorders() {
        if (this.x < 0) {
            this.x = 800;
            this.obrazok.posunVodorovne(800);
        } else if (this.x > 800) {
            this.x = 0;
            this.obrazok.posunVodorovne(-800);
        }

        if (this.y < 100) {
            this.y = 600;
            this.obrazok.posunZvisle(500);
        } else if (this.y > 600) {
            this.y = 100;
            this.obrazok.posunZvisle(-500);
        }
    }

    private void pohyb(int x, int y, int smer) {
        this.obrazok.posunVodorovne(x);
        this.obrazok.posunZvisle(y);
        this.x += x;
        this.y += y;
        this.smer = smer;
    }

    public void pohybHore() {
        this.obrazok.zmenObrazok(this.zoznamObrazkov.get(0));
        this.pohyb(0, -1, 0);
        this.checkBorders();
    }

    public void pohybHoreVpravo() {
        this.obrazok.zmenObrazok(this.zoznamObrazkov.get(1));
        this.pohyb(1, -1, 1);
        this.checkBorders();
    }

    public void pohybVpravo() {
        this.obrazok.zmenObrazok(this.zoznamObrazkov.get(1));
        this.pohyb(1, 0, 2);
        this.checkBorders();
    }

    public void pohybDoleVpravo() {
        this.obrazok.zmenObrazok(this.zoznamObrazkov.get(1));
        this.pohyb(1, 1, 3);
        this.checkBorders();
    }

    public void pohybDolu() {
        this.obrazok.zmenObrazok(this.zoznamObrazkov.get(2));
        this.pohyb(0, 1, 4);
        this.checkBorders();
    }

    public void pohybDoleVlavo() {
        this.obrazok.zmenObrazok(this.zoznamObrazkov.get(3));
        this.pohyb(-1, 1, 5);
        this.checkBorders();
    }

    public void pohybVlavo() {
        this.obrazok.zmenObrazok(this.zoznamObrazkov.get(3));
        this.pohyb(-1, 0, 6);
        this.checkBorders();
    }

    public void pohybHoreVlavo() {
        this.obrazok.zmenObrazok(this.zoznamObrazkov.get(3));
        this.pohyb(-1, -1, 7);
        this.checkBorders();
    }

    public void randomPohyb() {
        switch (this.smer) {
            case 0:
                this.pohybHore();
                break;
            case 1:
                this.pohybHoreVpravo();
                break;
            case 2:
                this.pohybVpravo();
                break;
            case 3:
                this.pohybDoleVpravo();
                break;
            case 4:
                this.pohybDolu();
                break;
            case 5:
                this.pohybDoleVlavo();
                break;
            case 6:
                this.pohybVlavo();
                break;
            case 7:
                this.pohybHoreVlavo();
                break;
        }
    }

    public void tikZver() {
        this.randomPohyb();
        if (!this.urobene) {
            this.casZmeni += 1;
            if (this.casZmeni == 100) {
                this.smer = this.random.nextInt(8);
                this.casZmeni = 0;
            }
        }
    }

    public boolean isUrobene() {
        return this.urobene;
    }

    public void setUrobene(boolean urobene) {
        this.urobene = urobene;
    }

    public int getSmer() {
        return this.smer;
    }

    public void setSmer(int smer) {
        this.smer = smer;
    }
    @Override
    public int getPoziciaX() {
        return this.x;
    }
    @Override
    public int getPoziciaY() {
        return this.y;
    }

    public boolean getStav() {
        return this.stav;
    }

    public void zabitaZver( ) {
        this.obrazok.zmenObrazok("Obrazky\\mrtvy_jelen.png");
        this.stav = false;
    }

    public void skryZver() {
        this.obrazok.skry();
    }

    public int getZivoty() {
        return this.zivoty;
    }

    public void setZivoty(int zivoty) {
        this.zivoty -= zivoty;
    }

    @Override
    public void zareaguj(int x, int y, String smer) {
        System.out.println("kolizia");
    }
}