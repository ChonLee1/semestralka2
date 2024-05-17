package hra;

import bytosti.Bytosti;
import bytosti.Postava;
import objekty.Naboj;

import java.util.*;

public class HerneData {
    private static HerneData instance;
    private Map<Smer, NabojSupplier> smerToNaboj;
    private List<Bytosti> zvere;
    private List<Bytosti> mrtveZvere;
    private int skore;
    private int cas;
    private List<Postava> postava;




    private HerneData() {
        this.zvere = new ArrayList<>();
        this.mrtveZvere = new ArrayList<>();
        this.skore = 0;
        this.cas = 0;
        this.postava = new ArrayList<>();
        this.smerToNaboj = new HashMap<>();
        this.smerToNaboj.put(Smer.HORE, () -> new Naboj(this.postava.get(0).getPoziciaX() + 15, this.postava.get(0).getPoziciaY()));
        this.smerToNaboj.put(Smer.DOLE, () -> new Naboj(this.postava.get(0).getPoziciaX() + 15, this.postava.get(0).getPoziciaY() + 15));
        this.smerToNaboj.put(Smer.VLAVO, () -> new Naboj(this.postava.get(0).getPoziciaX(), this.postava.get(0).getPoziciaY() + 15));
        this.smerToNaboj.put(Smer.VPRAVO, () -> new Naboj(this.postava.get(0).getPoziciaX() + 30, this.postava.get(0).getPoziciaY() + 15));
        this.smerToNaboj.put(Smer.HORE_VPRAVO, () -> new Naboj(this.postava.get(0).getPoziciaX() + 30, this.postava.get(0).getPoziciaY() + 15));
        this.smerToNaboj.put(Smer.HORE_VLAVO, () -> new Naboj(this.postava.get(0).getPoziciaX(), this.postava.get(0).getPoziciaY() + 15));
        this.smerToNaboj.put(Smer.DOLE_VPRAVO, () -> new Naboj(this.postava.get(0).getPoziciaX() + 30, this.postava.get(0).getPoziciaY() + 15));
        this.smerToNaboj.put(Smer.DOLE_VLAVO, () -> new Naboj(this.postava.get(0).getPoziciaX(), this.postava.get(0).getPoziciaY() + 15));
    }
    public static HerneData getInstance() {
        if (instance == null) {
            instance = new HerneData();
        }
        return instance;
    }
    @FunctionalInterface
    public interface NabojSupplier {
        Naboj get();
    }
    public Map<Smer, NabojSupplier> getSmerToNaboj() {
        return Collections.unmodifiableMap(this.smerToNaboj);
    }
    public List<Bytosti> getZvere() {
        return Collections.unmodifiableList(this.zvere);
    }
    public List<Bytosti> getMrtveZvere() {
        return Collections.unmodifiableList(this.mrtveZvere);
    }
    public List<Postava> getPostava() {
        return Collections.unmodifiableList(this.postava);
    }
    public void setPostava(Postava postava) {
        this.postava.add(postava);
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
