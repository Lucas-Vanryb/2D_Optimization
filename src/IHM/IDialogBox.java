package IHM;

import javax.swing.JPanel;

/**
 * Interface modélisant une IHM demandant à l'utilisateur de saisir la largeur et la hauteur de l'aire de jeu qu'il souhaite
 * 
 * @author Lucas VANRYB
 *
 */
public interface IDialogBox {

    /**
     * JPanel vide utile pour la mise en page
     */
    public static final JPanel JPANVIDE = new JPanel();

    /*
     * ACCESSEURS
     */
    
    /**
     * Renvoie la hauteur de l'aire de jeu saisie par l'utilisateur
     * @return la hauteur de l'aire de jeu saisie par l'utilisateur
     */
    public abstract int getHauteur();

    /**
     * Renvoie la largeur de l'aire de jeu saisie par l'utilisateur
     * @return la largeur de l'aire de jeu saisie par l'utilisateur
     */
    public abstract int getLargeur();

    /**
     * Renvoie true si l'utilisateur n'a pas encore saisi les données, false sinon
     * @return true si l'utilisateur n'a pas encore saisi les données, false sinon
     */
    public abstract boolean getEnCours();

}