package hra;

import bytosti.*;
import fri.shapesge.Manazer;
import java.util.List;
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
public class Hunter {
    private Postava postava;
    private Manazer manazer;
    private Obtiaznost obtiaznost;
    private HerneData data;
    private List<Bytosti> zvere;
    private List<Bytosti> mrtveZvere;
    private int casovac;
    private int casovacJelena;
    private String prezyvkaHraca;
    private Pozadie pozadie;
    private KontrolaOkolia kontrolaOkolia;
    /**
     * Konštruktor triedy hra.Hra. Pridáva parametre do premenných.
     * Vytvára inštancie tried bytosti.Postava, Manazer, bytosti.Zver.
     * Vytvára prázdny Arraylist "zvere".
     * 
     * @param obtiaznost určuje čas hry
     * @param prezyvka určuje meno hráča.
     */
    public Hunter(int obtiaznost, String prezyvka) {
        this.manazer = new Manazer();
        this.data = HerneData.getInstance();
        this.pozadie = new Pozadie(this.data.getCas(), this.data.getSkore());
        this.postava = Postava.getInstance();

        this.kontrolaOkolia = new KontrolaOkolia();

        this.zvere = this.data.getZvere();
        this.mrtveZvere = this.data.getMrtveZvere();
        this.data.setPostava(this.postava);

        this.manazer.spravujObjekt(this.postava);
        this.manazer.spravujObjekt(this);

        this.casovac = 0;
        this.casovacJelena = 0;

        this.prezyvkaHraca = prezyvka;
    }
    /**
     * Metóda na kontrolu stavu zvery.
     */
    public void mazanieZvere() {
        for (int j = 0; j < this.zvere.size(); j++) {
            if (!this.zvere.get(j).getStav()) {
                this.data.pridajMrtvuZver(this.zvere.get(j));
                this.data.odstranZver(this.zvere.get(j));
            }
        }
    }
    public void kontrolaOkolia() {
        for (Bytosti zver : this.zvere) {
            if (this.kontrolaOkolia.kontrolaOkolia(this.postava, zver)) {
                zver.zareaguj(this.postava.getPoziciaX(), this.postava.getPoziciaY(), this.postava.getSmer().toString());
                zver.setUrobene(true);
            } else {
                zver.setUrobene(false);
            }
        }
    }
    /**
     * Tik nastavený na 250ms, ktorý vyvoláva metódy na čistenie ArrayListov, kontroluv kolízii a aktualizáciu skóre.
     * Každé 2 sekundy vytvára inštanciu triedy bytosti.Zver a pridáva ju do ArrayListu "zvere".
     * A schová inštancie v Liste "mrtvaZver".
     */
    public void tikKolizie() {
        this.mazanieZvere();
        this.kontrolaOkolia();
        this.pozadie.zmenSkore(this.data.getSkore());
    }
    public void tikAktualizacia() {
        this.zvere = this.data.getZvere();
        this.mrtveZvere = this.data.getMrtveZvere();
    }
    public void tikSpawn() {
        this.casovac += 1;
        this.casovacJelena += 1;

        if (this.casovac % 4 == 0) {
            var jelen = new Jelen("Obrazky\\jelen", 2);
            this.data.pridajZver(jelen);
        }

//        if (this.casovac % 8 == 0) {
//            var srnka = new Srnka("Obrazky\\srnka", 1);
//            this.zvere.add(srnka);
//        }
//
        if (this.casovac == 4) {
            var vlk = new Vlk("Obrazky\\vlk", 3);
            this.data.pridajZver(vlk);
        }

        if (this.casovacJelena % 8 == 0) {
            for (Bytosti mrtvaZver : this.mrtveZvere) {
                mrtvaZver.skryZver();
            }
        }
    }
    public void tikPohyb() {
        this.postava.pohyb();
    }

    public void odpocitanieCasu() throws IOException {

//        String[] moznosti = {"Koniec hry"};
//        if (this.cas == 0) {
//            this.zapis();
//            int moznost = JOptionPane.showOptionDialog(null,
//                "Meno: " + this.prezyvkaHraca + " nahral: " + this.skore, "Vyprsal cas", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE,
//                null, moznosti, moznosti[0]);
//            if (moznost == 0) {
//                System.exit(0);
//            }
//        }
    }
    /**
     * Metóda určená na zápis do suboru.
     */
    public void zapis() throws IOException {
        File subor = new File("PoradieHracov.txt");
        Scanner prezerac = new Scanner(subor);
        PrintWriter zapisovac = new PrintWriter(new FileWriter(subor, true));
        
        zapisovac.println("Meno: " + this.prezyvkaHraca + " a nahral " + this.data.getSkore() + " bodov");
        
        prezerac.close();
        zapisovac.close();
    }
    /**
     * Tik nastavený na 1000ms, ktorý každú sekundu vyvoláva metódu odpocitanieCasu a následne aktualizuje aktuálny čas. 
     */
    public void tikCas() throws IOException {
        this.data.zvysCas();
        this.pozadie.zmenCas(this.data.getCas());
    }
    public void stop() {
        this.manazer.prestanSpravovatObjekt(this);
        this.manazer.prestanSpravovatObjekt(this.postava);
        for (Bytosti zver : this.zvere) {
            this.manazer.prestanSpravovatObjekt(zver);
        }
    }
    public void start() {
        this.manazer.spravujObjekt(this);
        this.manazer.spravujObjekt(this.postava);
        for (Bytosti zver : this.zvere) {
            this.manazer.spravujObjekt(zver);
        }
    }
}