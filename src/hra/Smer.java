package hra;

/**
 * Enumericka trieda hra.Smer - vracia smer pre triedy objekty.Sip a bytosti.Postava.
 * 
 * @author Matej Ostrožovič
 * @version (version number or date here)
 */
public enum Smer {
    HORE("hore"),
    VPRAVO("vpravo"),
    DOLE("dole"),
    VLAVO("vlavo"),
    HORE_VLAVO("horeVlavo"),
    HORE_VPRAVO("horeVpravo"),
    DOLE_VLAVO("doleVlavo"),
    DOLE_VPRAVO("doleVpravo");
    
    private final String smer;
    
    /**
     * Konštruktor triedy hra.Smer.
     */
    Smer(String smer) {
        this.smer = smer;
    }
}