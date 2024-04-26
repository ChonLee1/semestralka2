package bytosti;

public interface Zver {
    void pohybHore();
    void pohybDolu();
    void pohybVpravo();
    void pohybVlavo();
    void pohyb();
    int getPoziciaX();
    int getPoziciaY();
    boolean getStav();
    void zabitaZver();
    void skryZver();

}
