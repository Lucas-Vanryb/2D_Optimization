package Resolution;
import IHM.*;
/**
 * Classe servant de lanceur pour l'IHM basée sur la programation séquentielle
 * @author Lucas VANRYB
 *
 */
public class Main {

    /**
     * Méthode permettant de lancer l'IHM basée sur une programmation séquentielle
     */
    public static void main(String [] args) {
	IIHM iHM=new IHM();
	new Probleme(iHM);	   	
    }
}
