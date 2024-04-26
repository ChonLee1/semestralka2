package hra;

import fri.shapesge.Obrazok;
/**
 * Trieda hra.Pozadie generuje graficky background pre hru.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pozadie {
    private Obrazok kolonka;
    private Obrazok pozadie;
    /**
     * Konštruktor triedy hra.Pozadie.
     */
    public Pozadie() {
        this.pozadie = new Obrazok("Obrazky\\Pozadie.jpg", 0, 100);
        this.pozadie.zobraz();
        
        this.kolonka = new Obrazok("Obrazky\\kolonka.png", 0, 0);
        this.kolonka.zobraz();
    }
}
