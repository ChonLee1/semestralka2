import fri.shapesge.Obrazok;
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
    private Obrazok postava;
    private int poziciaX;
    private int poziciaY;
    private List<Sip> strely;
    private Smer smer;
    private Sip sip;
    private Random random;
    /**
     * Konštruktor triedy Postava, ktory vytvori obrazok lovca na poziciach x= 200 a y= 200
     * a prazdny Arraylist striel.
     */
    public Postava() {
        this.postava = new Obrazok("Obrazky\\lovec_hore.png", 200, 200);
        this.poziciaX = 200;
        this.poziciaY = 200;
        this.strely = new ArrayList();
        this.smer = Smer.HORE;
        this.postava.zobraz();
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
     * @return Smer vracia smer postavi.
     */
    public Smer getSmer() {
        return this.smer;
    }

    /**
     * Metóda na vrátenie ArrayListu strely.
     * 
     * @return List<Sip> vracia list striel.
     */
    public List<Sip> getStrely() {
        return this.strely;
    }

    /**
     * Metóda na posun "Postavy" smerom hore.
     */
    public void posunHore() {
        this.smer = Smer.HORE;
        this.postava.zmenObrazok("obrazky\\lovec_hore.png");
        if (this.poziciaY < 100) {
            this.poziciaY = 600;
            this.postava.posunZvisle(500);
        } else {
            this.poziciaY -= 4;
            this.postava.posunZvisle(-4);
        }
    }

    /**
     * Metóda na posunu "Postavy" smerom dolu.
     */
    public void posunDole() {
        this.smer = Smer.DOLE;
        this.postava.zmenObrazok("obrazky\\lovec_dole.png");
        if (this.poziciaY > 600) {
            this.poziciaY = 100;
            this.postava.posunZvisle( -500);
        } else {
            this.poziciaY += 4;
            this.postava.posunZvisle(4);
        }
    }

    /**
     * Metóda na posun "Postavy" smerom vľavo.
     */
    public void posunVlavo() {
        this.smer = Smer.VLAVO;
        this.postava.zmenObrazok("obrazky\\lovec_vlavo.png");
        if ( this.poziciaX < 0) {
            this.poziciaX = 800;
            this.postava.posunVodorovne(this.poziciaX);
        } else {
            this.poziciaX -= 4;
            this.postava.posunVodorovne(-4);
        }
    }

    /**
     * Metóda na posunu "Postavy" smerom vpravo.
     */
    public void posunVpravo() {
        this.smer = Smer.VPRAVO;
        this.postava.zmenObrazok("obrazky\\lovec_vpravo.png");
        if (this.poziciaX > 800) {
            this.poziciaX = 0;
            this.postava.posunVodorovne( -800);
        } else {
            this.poziciaX += 4;
            this.postava.posunVodorovne(4);
        }
    }

    /**
     * Metóda na vytvorenie inštancie Sip po stlačení klávesy "Space".
     * Pridanie danej inštancie do ArrayListu "strely" a posun určený podľa ENUMU "Smer".
     */
    public void aktivuj() {
        if (this.getSmer().equals(Smer.HORE)) {
            this.sip = new Sip(this.getPoziciaX() + 15, this.getPoziciaY());
            this.sip.vystrel(this.getSmer());
            this.strely.add(this.sip);
        } else if (this.getSmer().equals(Smer.DOLE)) {
            this.sip = new Sip(this.getPoziciaX() + 15, this.getPoziciaY());
            this.sip.vystrel(this.getSmer());
            this.strely.add(this.sip);
        } else if (this.getSmer().equals(Smer.VLAVO)) {
            this.sip = new Sip(this.getPoziciaX(), this.getPoziciaY() + 15);
            this.sip.zmenaStranySipu();
            this.sip.vystrel(this.getSmer());
            this.strely.add(this.sip);
        } else if (this.getSmer().equals(Smer.VPRAVO)) {
            this.sip = new Sip(this.getPoziciaX(), this.getPoziciaY() + 15);
            this.sip.zmenaStranySipu();
            this.sip.vystrel(this.getSmer());
            this.strely.add(this.sip);
        }
    }
}