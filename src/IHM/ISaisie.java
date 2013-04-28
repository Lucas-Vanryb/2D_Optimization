package IHM;
/**
 * Interface modélisant une interface entre l'utilisateur et le programme permettant à l'utilisateur de saisir les configurations via des champs de texte.
 * 
 * @author Lucas VANRYB
 *
 */
public interface ISaisie {

    /**
     * Constante définissant la taille qu'auront les champs de texte (carrés)
     */
    public static final int TAILLE_CASE = 20;

    /**
     * Renvoie le tableau modélisant la configuration initiale
     * @return le tableau modélisant la configuration initiale
     */
    public abstract int[][] getTab();

    /**
     * Renvoie le tableau modélisant la configuration finale
     * @return le tableau modélisant la configuration finale
     */
    public abstract int[][] getTabFin();

}