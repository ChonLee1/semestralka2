package hra;

import bytosti.*;
import fri.shapesge.Manazer;
import java.util.ArrayList;
import java.util.List;
import fri.shapesge.BlokTextu;
import fri.shapesge.StylFontu;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;

/**
 * Trieda vytvára kompletnú hru.
 * 
 * @author Matej Ostrožovič 
 * @version (a version number or a date)
 */
public class Hra {
    private Postava postava;
    private Jelen jelen;
    private Srnka srnka;
    private Manazer manazer;
    private Obtiaznost obtiaznost;
    private List<Zver> zvere;
    private List<Zver> mrtveZvere;
    private int skore;
    private BlokTextu skoreHry;
    private BlokTextu casHry;
    private int casovac;
    private int cas;
    private int casovacJelena;
    private String prezyvkaHraca;
    private Pozadie pozadie;
    /**
     * Konštruktor triedy hra.Hra. Pridáva parametre do premenných.
     * Vytvára inštancie tried bytosti.Postava, Manazer, bytosti.Zver.
     * Vytvára prázdny Arraylist "zvere".
     * 
     * @param obtiaznost určuje čas hry
     * @param prezyvka určuje meno hráča.
     */
    public Hra(int obtiaznost, String prezyvka) {
        this.manazer = new Manazer();
        this.pozadie = new Pozadie();
        this.postava = new Postava();
        this.jelen = new Jelen();
        this.srnka = new Srnka();
        this.zvere = new ArrayList();
        this.mrtveZvere = new ArrayList(); 
        
        this.zvere.add(this.jelen);
        this.manazer.spravujObjekt(this.postava);
        this.manazer.spravujObjekt(this);
        
        this.skore = 0;
        this.skoreHry = new BlokTextu("Skore: " + this.skore, 630, 55);
        this.skoreHry.zmenFarbu("white");
        this.skoreHry.zmenFont("Impact", StylFontu.PLAIN, 30);
        this.skoreHry.zobraz();
        
        this.casovac = 0;
        this.casovacJelena = 0;
        
        this.cas = obtiaznost;
        this.casHry = new BlokTextu("" + this.cas, 380, 55);
        this.casHry.zmenFarbu("white");
        this.casHry.zmenFont("Impact", StylFontu.PLAIN, 30);
        this.casHry.zobraz();
        
        this.prezyvkaHraca = prezyvka;
    }
    
    /**
     * Metóda na aktualizáciu skóre
     */
    public void aktualizaciaSkore() {
        this.skoreHry.zmenText("Skore: " + this.skore);
    }
    
    /**
     * Metóda na kontrolu stavu strely
     */
    public void mazanieStriel() {
        for (int i = 0; i < this.postava.getStrely().size(); i++ ) {
            if (!this.postava.getStrely().get(i).getStav()) {
                this.postava.getStrely().remove(i);
            }
        }
    }
    
    /**
     * Metóda na kontrolu stavu zvery.
     */
    public void mazanieZvere() {
        for (int j = 0; j < this.zvere.size(); j++) {
            if (!this.zvere.get(j).getStav()) {
                this.mrtveZvere.add(this.zvere.get(j));
                this.zvere.remove(j);
            }
        }
    }
    
    /**
     * Metóda na kontrolu kolízii inštancií tried objekty.Sip a bytosti.Zver.
     */
    public void kolizie() {
        for (int i = 0; i < this.postava.getStrely().size(); i++ ) {
            for (int j = 0; j < this.zvere.size(); j++) {
                Zver aktualnaZver = this.zvere.get(j);
                if (aktualnaZver.getPoziciaX() <= this.postava.getStrely().get(i).getPoziciaSipX() && this.postava.getStrely().get(i).getPoziciaSipX() <= aktualnaZver.getPoziciaX() + 30
                        && aktualnaZver.getPoziciaY() <= this.postava.getStrely().get(i).getPoziciaSipY() && this.postava.getStrely().get(i).getPoziciaSipY() <= aktualnaZver.getPoziciaY() + 30) {
                    aktualnaZver.zabitaZver();
                    this.postava.getStrely().get(i).vymazSip();
                    this.skore += 10;
                    break;
                }
            }
        }
    }
    /**
     * Tik nastavený na 250ms, ktorý vyvoláva metódy na čistenie ArrayListov, kontroluv kolízii a aktualizáciu skóre.
     * Každé 2 sekundy vytvára inštanciu triedy bytosti.Zver a pridáva ju do ArrayListu "zvere".
     * A schová inštancie v Liste "mrtvaZver".
     */
    public void tikHra() {
        this.casovac += 1;
        this.casovacJelena += 1;
        
        if (this.casovac == 8) {
            this.zvere.add(new Jelen());
            this.zvere.add(new Srnka());
        }
        if (this.casovac == 16) {
            this.zvere.add(new Vlk());
            this.casovac = 0;
        }
        /*if (this.skore %  50 == 0 ) {
            this.zvere.add(new Vlkodlak());
        }*/

    }
    public void tikPohyb() {
        this.postava.pohyb();
        this.mazanieStriel();
        this.mazanieZvere();
        this.kolizie();
        this.aktualizaciaSkore();
        if (this.casovacJelena % 8 == 0) {
            for (Zver mrtvaZver : this.mrtveZvere) {
                mrtvaZver.skryZver();
            }
        }
    }
    
    /**
     * Metóda na odpočitanie času. Po uplynutí času vypne celý program.
     * Po vypnutí sa používateľa zobrazí okno s jeho prezývkou a nahratím skóre a s možnostou vypnutia programu.
     */
    public void odpocitanieCasu() throws IOException {
        this.cas -= 1;
        
        String[] moznosti = {"Koniec hry"};
        if (this.cas == 0) {
            this.zapis();
            int moznost = JOptionPane.showOptionDialog(null,  
                "Meno: " + this.prezyvkaHraca + " nahral: " + this.skore, "Vyprsal cas", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, 
                null, moznosti, moznosti[0]);
            if (moznost == 0) {
                System.exit(0);
            } 
        }
    }
    
    /**
     * Metóda určená na zápis do suboru.
     */
    public void zapis() throws IOException {
        File subor = new File("PoradieHracov.txt");
        Scanner prezerac = new Scanner(subor);
        PrintWriter zapisovac = new PrintWriter(new FileWriter(subor, true));
        
        zapisovac.println("Meno: " + this.prezyvkaHraca + " a nahral " + this.skore + " bodov");
        
        prezerac.close();
        zapisovac.close();
    }
    
    /**
     * Tik nastavený na 1000ms, ktorý každú sekundu vyvoláva metódu odpocitanieCasu a následne aktualizuje aktuálny čas. 
     */
    public void tikCas() throws IOException {
        this.odpocitanieCasu();
        this.casHry.zmenText("" + this.cas);
    }
    
    /**
     * Metóda, ktorá po stlačení klávesy "Esc" ukončí program.
     */
    public void zrus() {
        System.exit(0);
    }
}