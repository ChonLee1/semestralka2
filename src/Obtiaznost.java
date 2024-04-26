
/**
 * Enumericka trieda Obtiaznost - obsahuje 3 obtažnosti pre triedu Hra.
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
     * Konštruktor triedy Obtiaznost.
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