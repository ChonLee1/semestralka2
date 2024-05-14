package hra;

import bytosti.Bytosti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HerneData {
    private static HerneData instance;
    private List<Bytosti> zvere;
    private List<Bytosti> mrtveZvere;
    private int skore;
    private int cas;


    private HerneData() {
        this.zvere = new ArrayList<>();
        this.mrtveZvere = new ArrayList<>();
        this.skore = 0;
        this.cas = 0;
    }
    public static HerneData getInstance() {
        if (instance == null) {
            instance = new HerneData();
        }
        return instance;
    }
    public List<Bytosti> getZvere() {
        return Collections.unmodifiableList(this.zvere);
    }
    public List<Bytosti> getMrtveZvere() {
        return Collections.unmodifiableList(this.mrtveZvere);
    }
    public void pridajZver(Bytosti zver) {
        this.zvere.add(zver);
    }
    public void pridajMrtvuZver(Bytosti zver) {
        this.mrtveZvere.add(zver);
    }
    public void odstranZver(Bytosti zver) {
        this.zvere.remove(zver);
    }
    public void odstranMrtvuZver(Bytosti zver) {
        this.mrtveZvere.remove(zver);
    }
    public void zvysSkore(int skore) {
        this.skore += skore;
    }
    public int getSkore() {
        return this.skore;
    }
    public void zvysCas() {
        this.cas++;
    }
    public int getCas() {
        return this.cas;
    }
}
