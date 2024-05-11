package hra;

import fri.shapesge.BlokTextu;
import fri.shapesge.Obrazok;
import fri.shapesge.StylFontu;
/**
 * Trieda hra.Pozadie generuje graficky background pre hru.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pozadie {
    private Obrazok kolonka;
    private Obrazok pozadie;
    private BlokTextu casHry;
    private BlokTextu skoreHry;
    /**
     * Konštruktor triedy hra.Pozadie.
     */
    public Pozadie(int cas, int skore) {
        this.pozadie = new Obrazok("Obrazky\\Pozadie.jpg", 0, 100);
        this.pozadie.zobraz();
        
        this.kolonka = new Obrazok("Obrazky\\kolonka.png", 0, 0);
        this.kolonka.zobraz();

        this.casHry = new BlokTextu("" + cas, 380, 55);
        this.casHry.zmenFarbu("white");
        this.casHry.zmenFont("Impact", StylFontu.PLAIN, 30);
        this.casHry.zobraz();

        this.skoreHry = new BlokTextu("Skore: " + skore, 630, 55);
        this.skoreHry.zmenFarbu("white");
        this.skoreHry.zmenFont("Impact", StylFontu.PLAIN, 30);
        this.skoreHry.zobraz();
    }

    public void zmenCas(int cas) {
        this.casHry.zmenText("" + cas);
    }

    public void zmenSkore(int skore) {
        this.skoreHry.zmenText("Skore: " + skore);
    }
}
