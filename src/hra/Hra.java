package hra;

import fri.shapesge.Manazer;

public class Hra {
    private HerneData data;
    private Manazer manazer;
    private boolean stav;
    private Hunter hunter;

    public Hra() {
        this.stav = false;
        VyberObtiaznosti vyber = new VyberObtiaznosti();
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        this.hunter = new Hunter(vyber.getObtiaznost(), vyber.getMenoHRaca());
//        this.data = HerneData.getInstance();
    }
    public void stopni() {
        this.stav = !this.stav;
        if (this.stav) {
            this.hunter.stop();
        } else {
            this.hunter.start();
        }
    }
}
