import hra.Hra;
import hra.VyberObtiaznosti;

/**
 * Trieda, ktorá spája triedy hra.VyberObtiaznosti a hra.Hra. Obsahuje metódu main
 * 
 * 
 * @author Matej Ostrožovič
 * @version (a version number or a date)
 */
public class Main {
    public static void main (String[] args) {
        VyberObtiaznosti vyber = new VyberObtiaznosti();
        Hra hra = new Hra(vyber.getObtiaznost(), vyber.getMenoHRaca());
    }
}