package LectureTexte;
/**
 * Classe permettant de lire un fichier texte dans un formattage donné et de remplir des tableaux d'entier modélisant les configurations finale et initiale à partir de ce fichier texte
 * Ce fichier texte est laissé au choix de l'utilisateur
 * 
 * @author Lucas VANRYB
 */
public interface ILecture {

    /*
     * ACCESSEURS
     */
    
    /**
     * Retourne le tableau d'entier de dimension deux modélisant la configuration initiale
     * @return le tableau d'entier de dimension deux modélisant la configuration initiale
     */
    public abstract int[][] getTab();

    /**
     * Retourne le tableau d'entier de dimension deux modélisant la configuration finale
     * @return le tableau d'entier de dimension deux modélisant la configuration finale
     */
    public abstract int[][] getTabFin();

}