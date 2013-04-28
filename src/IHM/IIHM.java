package IHM;

/**
 * Cette interface est le modèle abstrait d'une interface graphique, assurant le lien entre l'utilisateur et le programme
 * 
 * @author Lucas VANRYB
 *
 */
public interface IIHM {

    /**
     * NOMFICHIER est une constante détérminant le nom du fichier qui sera crée dans le répertoire et qui stockera les déplacements
     */
    public static final String NOMFICHIER = "results.txt";

    /* 
     * ACCESSEURS
     */
    
    /**
     * Retourne la hauteur de l'aire de jeu, définie par l'utilisateur
     * @return la hauteur de l'aire de jeu, définie par l'utilisateur
     */
    public abstract int getHauteur();

    /**
     * Retourne la largeur de l'aire de jeu, définie par l'utilisateur
     * @return la largeur de l'aire de jeu, définie par l'utilisateur
     */
    public abstract int getLargeur();

    /**
     * Retourne le tableau d'entier modélisant la configuration initiale
     * @return le tableau d'entier modélisant la configuration initiale
     */
    public abstract int[][] getTabInt();

    /**
     * Retourne le tableau d'entier modélisant la configuration finale
     * @return le tableau d'entier modélisant la configuration finale
     */
    public abstract int[][] getTabIntFin();

    /**
     * Renvoie true si l'utilisateur choisit la saisie via IHM, false sinon
     * @return le tableau d'entier modélisant la configuration initiale
     */
    public abstract boolean getToggle();

    /*
     * SERVICES
     */
    
    /**
     * Demande à l'utilisateur de saisir les dimensions de l'aire de jeu et met à jour ces dernières dans this
     */
    public abstract void getInfos();

    /**
     * Demande à l'utilisateur de saisir les configurations via l'interface crée par la JFrame Saisie
     */
    public abstract void getSaisie();

    /**
     * Méthode demandant à l'utilisateur si il veut afficher le fichier texte contenant les déplacements éffectués ou si il veut quitter le programme
     */
    public abstract void demandeAfficherTexte();

}