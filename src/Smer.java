
/**
 * Enumericka trieda Smer - vracia smer pre triedy Sip a Postava.
 * 
 * @author Matej Ostrožovič
 * @version (version number or date here)
 */
public enum Smer {
    HORE("hore"),
    VPRAVO("vpravo"),
    DOLE("dole"),
    VLAVO("vlavo");
    
    private final String smer;
    
    /**
     * Konštruktor triedy Smer.
     */
    Smer(String smer) {
        this.smer = smer;
    }
}