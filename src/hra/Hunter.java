package hra;

import bytosti.*;
import fri.shapesge.Manazer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import objekty.Naboj;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.function.Supplier;

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
    private List<Bytosti> zvere;
    private List<Bytosti> mrtveZvere;
    private List<Naboj> strely;
    private int skore;
    private int casovac;
    private int cas;
    private int casovacJelena;
    private String prezyvkaHraca;
    private Pozadie pozadie;
    private Kolizie kolizie;
    private KontrolaOkolia kontrolaOkolia;
    private Naboj naboj;
    private HashMap<Smer, Supplier<Naboj>> smerToNaboj;
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
        this.pozadie = new Pozadie(this.cas, this.skore);
        this.postava = new Postava();
        this.kolizie = new Kolizie();
        this.kontrolaOkolia = new KontrolaOkolia();

        this.zvere = new ArrayList();
        this.mrtveZvere = new ArrayList();
        this.strely = new ArrayList();

        this.manazer.spravujObjekt(this.postava);
        this.manazer.spravujObjekt(this);
        
        this.skore = 0;

        this.casovac = 0;
        this.casovacJelena = 0;
        
        this.cas = 0;

        this.prezyvkaHraca = prezyvka;

        this.smerToNaboj = new HashMap<>();
        this.smerToNaboj.put(Smer.HORE, () -> new Naboj(this.postava.getPoziciaX() + 15, this.postava.getPoziciaY()));
        this.smerToNaboj.put(Smer.DOLE, () -> new Naboj(this.postava.getPoziciaX() + 15, this.postava.getPoziciaY() + 15));
        this.smerToNaboj.put(Smer.VLAVO, () -> new Naboj(this.postava.getPoziciaX(), this.postava.getPoziciaY() + 15));
        this.smerToNaboj.put(Smer.VPRAVO, () -> new Naboj(this.postava.getPoziciaX() + 30, this.postava.getPoziciaY() + 15));
        this.smerToNaboj.put(Smer.HORE_VPRAVO, () -> new Naboj(this.postava.getPoziciaX() + 30, this.postava.getPoziciaY() + 15));
        this.smerToNaboj.put(Smer.HORE_VLAVO, () -> new Naboj(this.postava.getPoziciaX(), this.postava.getPoziciaY() + 15));
        this.smerToNaboj.put(Smer.DOLE_VPRAVO, () -> new Naboj(this.postava.getPoziciaX() + 30, this.postava.getPoziciaY() + 15));
        this.smerToNaboj.put(Smer.DOLE_VLAVO, () -> new Naboj(this.postava.getPoziciaX(), this.postava.getPoziciaY() + 15));

    }
    /**
     * Metóda na kontrolu stavu strely
     */
    public void mazanieStriel() {
        for (int i = 0; i < this.strely.size(); i++ ) {
            if (!this.strely.get(i).getStav()) {
                this.strely.remove(i);
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
    public void kolizieStrelyAZvery() {
        for (int i = 0; i < this.strely.size(); i++) {
            Naboj aktualnaStrela = this.strely.get(i);
            for (Bytosti aktualnaZver : this.zvere) {
                if (this.kolizie.kolizia(aktualnaZver, aktualnaStrela)) {
                    aktualnaZver.setZivoty(aktualnaStrela.getPoskodenie());
                    if (aktualnaZver.getZivoty() == 0) {
                        aktualnaZver.zabitaZver();
                        this.manazer.prestanSpravovatObjekt(aktualnaZver);
                        aktualnaStrela.vymazSip();
                        this.skore += 10;
                        break;
                    }
                    aktualnaStrela.vymazSip();
                    break;
                }
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
        this.mazanieStriel();
        this.mazanieZvere();
        this.kontrolaOkolia();
        this.kolizieStrelyAZvery();
//        if (this.naboj != null) {
//            this.naboj.kolizia(this.zvere);
//        }
        this.pozadie.zmenSkore(this.skore);
    }

    public void tikSpawn() {
        this.casovac += 1;
        this.casovacJelena += 1;

        if (this.casovac % 4 == 0) {
            var jelen = new Jelen("Obrazky\\jelen", 2);
            this.zvere.add(jelen);
            this.manazer.spravujObjekt(jelen);
        }

//        if (this.casovac % 8 == 0) {
//            var srnka = new Srnka("Obrazky\\srnka", 1);
//            this.zvere.add(srnka);
//            this.manazer.spravujObjekt(srnka);
//        }
//
        if (this.casovac == 4) {
            var vlk = new Vlk("Obrazky\\vlk", 3);
            this.zvere.add(vlk);
            this.manazer.spravujObjekt(vlk);
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

    /**
     * Metóda na odpočitanie času. Po uplynutí času vypne celý program.
     * Po vypnutí sa používateľa zobrazí okno s jeho prezývkou a nahratím skóre a s možnostou vypnutia programu.
     */
    public void odpocitanieCasu() throws IOException {
        this.cas += 1;
        /**
         * Metóda na zápis do súboru.
         */
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
        
        zapisovac.println("Meno: " + this.prezyvkaHraca + " a nahral " + this.skore + " bodov");
        
        prezerac.close();
        zapisovac.close();
    }
    /**
     * Tik nastavený na 1000ms, ktorý každú sekundu vyvoláva metódu odpocitanieCasu a následne aktualizuje aktuálny čas. 
     */
    public void tikCas() throws IOException {
        this.odpocitanieCasu();
        this.pozadie.zmenCas(this.cas);
    }

    public void aktivuj() {
        this.naboj = this.smerToNaboj.get(this.postava.getSmer()).get();
        this.naboj.vystrel(this.postava.getSmer());
        this.strely.add(this.naboj);
        // TODO: urobit zasobnik a prebijanie do metody aktivuj, pocitadlo na zosobnik a potom pocitadlo na prebytie
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