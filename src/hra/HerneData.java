package hra;

import bytosti.Bytosti;

import java.util.ArrayList;
import java.util.List;

public class HerneData {
    private static HerneData instance;
    private List<Bytosti> zvere;
    private List<Bytosti> mrtveZvere;


    private HerneData() {
        this.zvere = new ArrayList<>();
        this.mrtveZvere = new ArrayList<>();
    }
    public static HerneData getInstance() {
        if (instance == null) {
            instance = new HerneData();
        }
        return instance;
    }
    public List<Bytosti> getZvere() {
        return this.zvere;
    }
    public List<Bytosti> getMrtveZvere() {
        return this.mrtveZvere;
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
}
