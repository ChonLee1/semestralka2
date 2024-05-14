package bytosti;

public class Srnka extends Bytosti implements Zver {
    public Srnka(String obrazok, int zivoty) {
        super(obrazok, zivoty);
    }
    public void zmenaSmeru(String smer) {
        switch (smer) {
            case "HORE":
                this.setSmer(0);
                break;
            case "HORE_VPRAVO":
                this.setSmer(1);
                break;
            case "VPRAVO":
                this.setSmer(2);
                break;
            case "DOLE_VPRAVO":
                this.setSmer(3);
                break;
            case "DOLE":
                this.setSmer(4);
                break;
            case "DOLE_VLAVO":
                this.setSmer(5);
                break;
            case "VLAVO":
                this.setSmer(6);
                break;
            case "HORE_VLAVO":
                this.setSmer(7);
                break;
        }
    }

    @Override
    public void zareaguj(int x, int y, String smer) {
        if (!this.isUrobene()) {
            this.zmenaSmeru(smer);
            System.out.println("kolizia");
        }
    }
}
