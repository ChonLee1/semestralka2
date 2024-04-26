import javax.swing.JOptionPane;
/**
 * Táto trieda slúži na výber obtažností z enumu Obtiaznost.
 * 
 * @author Matej Ostrožovič
 * @version (a version number or a date)
 */
public class VyberObtiaznosti {
    private Obtiaznost obtiaznost;
    private String menoHraca;
    /**
     * Konštruktor triedy VyberObtiaznosti. 
     */
    public VyberObtiaznosti() {
        this.menoHraca = JOptionPane.showInputDialog(null, "Zadaj svoju prezivku: ");
        if (this.menoHraca.length() == 0) {
            this.menoHraca = JOptionPane.showInputDialog(null, "Zle zadane meno, zadaj znova: ");
        }
        
        String[] moznosti = {"lahka", "stredna", "tazka"};
        
        int moznost = JOptionPane.showOptionDialog(null, 
                "Vytaj, vyber si obtiaznost", "Hunter", JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE, null, moznosti, moznosti[0]);
        
        switch (moznost) {
            case 0:
                this.obtiaznost = Obtiaznost.LAHKA;
                break;
            case 1:
                this.obtiaznost = Obtiaznost.STREDNA;
                break;
            case 2:
                this.obtiaznost = Obtiaznost.TAZKA;
                break;
            default:
                System.exit(0);
                break;
        }
    }
    
    /**
     * Metoda, ktorá vracia atribút menoHraca.
     * 
     * @return String vracia prezývku používateľa.
     */
    public String getMenoHRaca() {
        return this.menoHraca;
    }
    
    /**
     * Metoda na vratenie atribútu obtiaznost.
     * 
     * @return int vracia čas vyjadrený obtažnostou používateľa.
     */
    public int getObtiaznost() {
        return this.obtiaznost.getCas();
    }
}