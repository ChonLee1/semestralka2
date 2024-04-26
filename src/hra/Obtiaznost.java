package hra;

/**
 * Enumericka trieda hra.Obtiaznost - obsahuje 3 obtažnosti pre triedu hra.Hra.
 * 
 * @author Matej Ostrožovič
 * @version (version number or date here)
 */
public enum Obtiaznost {
    LAHKA(60),

    STREDNA(55),

    TAZKA(50);

    private final int cas;

    /**
     * Konštruktor triedy hra.Obtiaznost.
     */
    Obtiaznost (int novyCas) {
        this.cas = novyCas;
    }

    /**
     * Metóda na vrátenie čiselného vyjadrenia obtažnosti.
     * 
     * @return int vráti čas pre danú obťažnosť
     */
    public int getCas() {
        return this.cas;
    }
}