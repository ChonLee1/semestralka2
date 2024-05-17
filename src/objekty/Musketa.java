package objekty;

import bytosti.Postava;
import hra.HerneData;
import hra.Smer;

import java.util.List;
import java.util.Map;

public class Musketa extends Zbran {
    private HerneData data;
    private Map<Smer, HerneData.NabojSupplier> smerToNaboj;
    private List<Postava> charakter;
    private Naboj naboj;

    public Musketa() {
        super(Zbrane.MUSKETA.getMeno(), Zbrane.MUSKETA.getPoskodenie(), Zbrane.MUSKETA.getZasobnik());
        this.data = HerneData.getInstance();
        this.smerToNaboj = this.data.getSmerToNaboj();
        this.charakter = this.data.getPostava();
    }
}
