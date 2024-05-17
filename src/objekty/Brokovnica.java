package objekty;

import bytosti.Postava;
import hra.HerneData;
import hra.Smer;

import java.util.List;
import java.util.Map;


public class Brokovnica extends Zbran {
    private HerneData data;
    private Map<Smer, HerneData.NabojSupplier> smerToNaboj;
    private List<Postava> charakter;
    private Naboj naboj;

    public Brokovnica() {
        super(Zbrane.BROKOVNICA.getMeno(), Zbrane.BROKOVNICA.getPoskodenie(), Zbrane.BROKOVNICA.getZasobnik());
        this.data = HerneData.getInstance();
        this.smerToNaboj = this.data.getSmerToNaboj();
        this.charakter = this.data.getPostava();
    }

    @Override
    public void vystrel() {
        this.naboj = this.smerToNaboj.get(this.charakter.get(0).getSmer()).get();
        this.naboj.setPolomer(10);
//        this.naboj.zobrazenie();
        this.naboj.vystrel(this.charakter.get(0).getSmer());
    }
}
