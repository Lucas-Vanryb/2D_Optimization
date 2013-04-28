package Resolution;
/**
 * Interface établissant le modèle abstrait d'un Bloc dans une IConfiguration
 * Il est constitué de diverses caractéristiques (peut être libre ou pas, a un degré de contrainte, peut être placable ou pas, placé ou pas) ainsi que d'une position et d'une caractéristique modélisée par un entier
 *
 * @author Lucas VANRYB
 *
 */
public interface IBloc {

    /*
     * ACCESSEURS
     */

    /**
     * Retourne la caractéristique du Bloc, c'est à dire sa propriété intrinsèque, qui ne dépend pas de sa situation dans la Configuration
     * @return la caractéristique du Bloc, c'est à dire sa propriété intrinsèque, qui ne dépend pas de sa situation dans la Configuration
     */
    public abstract int getCarac();

    /**
     * Retourne true si le Bloc est libre, c'est à dire si aucun autre Bloc n'est posé dessus, false sinon
     * @return true si le Bloc est libre, c'est à dire si aucun autre Bloc n'est posé dessus, false sinon
     */
    public abstract boolean estLibre();

    /**
     * Renvoie true si le Bloc est positionné dans la Configuration comme il l'est dans la Configuration finale, et que c'est également le cas des Blocs situés en dessous de lui dans la Configuration 
     * @return true si le Bloc est positionné dans la Configuration comme il l'est dans la Configuration finale, et que c'est également le cas des Blocs situés en dessous de lui dans la Configuration
     */
    public abstract boolean estPlace();

    /**
     * Renvoie le nombre de Blocs situés au dessus du Bloc dans la Configuration
     * @return le nombre de Blocs situés au dessus du Bloc dans la Configuration
     */
    public abstract int getDegCont();

    /**
     * Renvoie l'index de la colonne qu'occupe le Bloc dans la Configuration finale
     * @return l'index de la colonne qu'occupe le Bloc dans la Configuration finale
     */
    public abstract int getPosFinX();

    /**
     * Renvoie l'index de la ligne qu'occupe le Bloc dans la Configuration finale
     * @return l'index de la ligne qu'occupe le Bloc dans la Configuration finale
     */
    public abstract int getPosFinY();

    /**
     * Renvoie l'index de la colonne qu'occupe le Bloc dans la Configuration
     * @return l'index de la colonne qu'occupe le Bloc dans la Configuration
     */
    public abstract int getPosX();

    /**
     * Renvoie l'index de la ligne qu'occupe le Bloc dans la Configuration
     * @return l'index de la ligne qu'occupe le Bloc dans la Configuration
     */
    public abstract int getPosY();

    /**
     * Renvoie true si il n'y a pas de Bloc dans la Configuration situés sous la position de ce Bloc dans la Configuration finale qui ne soient pas placés, false sinon
     * @return true si il n'y a pas de Bloc dans la Configuration situés sous la position de ce Bloc dans la Configuration finale qui ne soient pas placés, false sinon
     */
    public abstract boolean placable();

    /**
     * Met en mémoire true si il n'y a pas de Bloc dans la Configuration situés sous la position de ce Bloc dans la Configuration finale qui ne soient pas placés, false sinon
     * @param p true si il n'y a pas de Bloc dans la Configuration situés sous la position de ce Bloc dans la Configuration finale qui ne soient pas placés, false sinon
     */
    public abstract void setPlacable(boolean p);

    /**
     * Met en mémoire true si le Bloc peut être déplacé dans la Configuration sans qu'aucun autre Bloc ne soit déplacé, false sinon
     * @param l true si le Bloc peut être déplacé dans la Configuration sans qu'aucun autre Bloc ne soit déplacé, false sinon
     */
    public abstract void setLibre(boolean l);

    /**
     * Met en mémoire true si le Bloc est positionné dans la Configuration comme il l'est dans la Configuration finale, et que c'est également le cas des Blocs situés en dessous de lui dans la Configuration
     * @param p true si le Bloc est positionné dans la Confuration comme il l'est dans la Configuration finale, et que c'est également le cas des Blocs situés en dessous de lui dans la Configuration 
     */
    public abstract void setPlace(boolean p);

    /**
     * Met en mémoire le nombre de Blocs situés au dessus du Bloc dans la Configuration
     * @param deg le nombre de Blocs situés au dessus du Bloc dans la Configuration
     */
    public abstract void setDegCont(int deg);

    /*
     * SERVICES
     */

    /**
     * Service déplacant le Bloc dans la Configuration à la position (X,Y)
     * @param X l'index de la colonne dans laquelle on veut placer le Bloc
     * @param Y l'index de la ligne dans laquelle on veut placer le Bloc
     */
    public abstract void deplacer(int X, int Y);

    /**
     * Service modifiant la position du Bloc dans la Configuration finale à la position (X,Y)
     * @param x l'index de la colonne que doit occuper le Bloc dans la Configuration finale
     * @param y l'index de la colonne que doit occuper le Bloc dans la Configuration finale
     */
    public abstract void deplacerPosFin(int x, int y);

    /**
     * Service mettant en mémoire true si le Bloc est positionné dans la Configuration comme il l'est dans la Configuration finale
     */
    public abstract void checkPlace();

}