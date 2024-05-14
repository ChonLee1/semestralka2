package bytosti;

public class Vlk extends Bytosti implements Zver {
    public Vlk(String obrazok, int zivoty) {
        super(obrazok, zivoty);
    }

    @Override
    public void zareaguj(int x, int y, String smer) {
        int smerX = x - this.getPoziciaX();
        int smerY = y - this.getPoziciaY();

        if (smerX > 0) {
            if (smerY > 0) {
                this.pohybDoleVpravo();
            } else if (smerY < 0) {
                this.pohybHoreVpravo();
            } else {
                this.pohybVpravo();
            }
        } else if (smerX < 0) {
            if (smerY > 0) {
                this.pohybDoleVlavo();
            } else if (smerY < 0) {
                this.pohybHoreVlavo();
            } else {
                this.pohybVlavo();
            }
        } else {
            if (smerY > 0) {
                this.pohybDolu();
            } else if (smerY < 0) {
                this.pohybHore();
            }
        }
    }
}
