package Resolution;
import java.util.ArrayList;

/**
 * Interface du modèle de Configuration, des services et des accesseurs qu'elle doit contenir
 * Une IConfiguration représente un univers en deux dimensions contenant des Blocs situés à divers points de cet univers, déplacables et soumis à certaines règles.
 * 
 * @author Lucas VANRYB
 * 
 */
public interface IConfiguration {

    /*
     * ACCESSEURS
     */
    
    /**
     * Retourne la hauteur de l'aire de jeu
     * @return hauteur, c'est à dire le nombre de lignes de l'aire de jeu.
     */
    public abstract int getHauteur();

    /**
     * Retourne la largeur de l'aire de jeu
     * @return largeur, c'est à dire le nombre de colonnes de l'aire de jeu.
     */
    public abstract int getLargeur();

    /**
     * Retourne la liste de Blocs de la Configuration
     * @return tab, c'est à dire l'ArrayList de Bloc propre à la configuration. C'est à dire toutes les informations sur l'etat des Blocs dans la Configuration
     */
    public abstract ArrayList<Bloc> getBlocs();

    /**
     * Retourne le nombre de Blocs composant la Configuration
     * @return le nombre de Blocs présent dans la configuration.
     */
    public abstract int getNbBlocs();

    /*
     * SERVICES
     */
    
    /**
     * Service qui recherche le Bloc de la configuration à un endroit donné de l'aire de jeu.
     * @param X la coordonnée X (ie le numéro de la colonne de l'aire de jeu) à laquelle on cherche un Bloc. 
     * @param Y la coordonnée Y (ie le numéro de la ligne de l'aire de jeu) à laquelle on cherche un Bloc.
     * @return le Bloc présent en (X,Y), null si il n'existe pas.
     */
     public abstract IBloc getBloc(int X, int Y);

    /**
     * Service qui recherche un Bloc par sa caractéristique.
     * @param c la caractéristique du Bloc à trouver
     * @return le Bloc de caractéristique c dans la Configuration, null si aucun Bloc de la Configuration n'a pour caracteristique c
     */
    public abstract IBloc getBlocParNom(int c);

    /**
     * Service verifiant si les Blocs de la Configuration sont placables.
     * Il est appelé à chaque fois qu'un nouveau Bloc est placé.
     */
    public abstract void verifierBlocsPlacables();

    /**
     * Service permettant de déplacer le Bloc de la configuration situé en (X1,Y1) en (X2,Y2), et de mettre à jour les degCont et les estLibre des Blocs affectés
     * Si ce déplacement est impossible parce qu'il y a un bloc en (X2,Y2) ou pas de Bloc en (X2,Y2-1), print une erreur et quitte l'application
     * De même si le Bloc en (X1,Y1) n'est pas libre, print une erreur et quitte l'application
     * @param X1 la coordonnée X dans la Configuration du Bloc à déplacer
     * @param Y1 la coordonnée Y dans la Configuration du Bloc à déplacer
     * @param X2 la coordonnée X dans la Configuration qu'occupera le Bloc après le déplacement
     * @param Y2 la coordonnée Y dans la Configuration qu'occupera le Bloc après le déplacement
     */
    public abstract void deplacerBloc(int X1, int Y1, int X2, int Y2);

    /**
     * Service vérifiant si un Bloc de la Configuration est placé, c'est à dire si il est au même emplacement que dans la Configuration finale, et si c'est également le cas de tous les Blocs en dessous de lui et qui ajuste son attribut estPlace en conséquence.
     * @param bloc le bloc dont on veut vérifier le placement
     */
    public abstract void checkPlace(IBloc bloc);

    /**
     * Service qui retourne le plus petit index Y de la colonne passée en paramètre tel que la "case" (X,Y) soit vide
     * @param X la colonne dont on veut la première case libre
     * @return un entier tel Y tel que le déplacement d'un Bloc libre vers (X,Y) soit possible si il existe, -1 sinon 
     */
    public abstract int getBlocAuSommet(int X);

    /**
     * Service permettant de trouver une colonne de l'aire de jeu qui n'est pas pleine, et qui n'est pas une des deux colonnes dont l'index est passé en paramètre, et renvoie son index, ou -1 si il n'en n'existe pas
     * @param X1 la première colonne à exclure des recherches 
     * @param X2 la deuxième colonne à exclure des recherches
     * @return l'index d'une colonne non vide différent de X1 et de X2 ou -1 si il n'en n'existe pas
     */
    public abstract int trouverUneColonneNonPleine(int X1, int X2);

    /**
     * Service renvoyant l'index d'une colonne pleine de la Configuration d'index différent de X2 si il en existe une, -1 sinon
     * @param X2 l'index de la colonne à exclure des recherches
     * @return l'index de la colonne pleine différente de X2 si elle existe, -1 sinon
     */
    public abstract int trouverUneColonnePleine(int X2);

    /**
     * Service renvoyant true si tous les Blocs de la Configuration sont placés, et false sinon
     * @return true si tous les Blocs de la Configuration sont placés, false sinon
     */
    public abstract boolean win();

}