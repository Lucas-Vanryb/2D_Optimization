package Resolution;
/**
 * Classe implémentant IBloc
 * Modélisant les différentes caractéristiques par des booleans et les positions et le degré de contrainte par des entiers
 * 
 * @author Lucas VANRYB
 *
 */
public class Bloc implements IBloc {

    /*
     *  ATTRIBUTS
     */

    /**
     * carac est la caractéristique intrinsèque au Bloc, ici modélisée par un entier.
     */
    int carac;

    /**
     * X est une partie de la position du Bloc, c'est sa coordonnée correspondant à l'index de la colonne de l'aire de jeu qu'il occupe.
     */
    int X;

    /**
     * Y est une partie de la position du Bloc, c'est sa coordonnée correspondant à l'index de la ligne de l'aire de jeu qu'il occupe.
     */
    int Y;

    /**
     * posFinX est une partie de la position finale du Bloc, c'est sa coordonnée correspondant à l'index de la colonne qu'il occupe dans la Configuration finale.
     */
    int posFinX;

    /**
     * posFinY est une partie de la position finale du Bloc, c'est sa coordonnée correspondant à l'index de la ligne qu'il occupe dans la Configuration finale.
     */
    int posFinY;

    /**
     * degCont est un entier correspondant au degré de contrainte du Bloc dans une Configuration, c'est à dire au nombre de Blocs qu'il faut déplacer pour pouvoir libérer ce Bloc.
     */
    int degCont;

    /**
     * estLibre est un boolean valant true si le Bloc est libre dans la configuration, c'est à dire déplacable sans avoir à bouger d'autres Blocs de la Configuration, false sinon.
     */
    boolean estLibre;

    /**
     * estPlace est un boolean valant true si le Bloc est placé au même endroit dans sa Configuration et dans la Configuration finale, et si c'est également le cas de tous les Blocs situés en dessous de celui ci dans la Configuration, false sinon. 
     */
    boolean estPlace;

    /**
     * estPlacable est un boolean valant true si tous les Blocs de la Configuration situés en dessous de la position dans la Configuration finale de ce Bloc sont placés, false sinon.
     */
    boolean estPlacable;

    /*
     * CONSTRUCTEUR
     */

    /**
     * Constructeur construisant un Bloc à partir d'une caractéristique et d'une position dans une Configuration
     * @param c la caractéristique du Bloc à construire
     * @param y l'index de la ligne de la Configuration dans laquelle le Bloc se situe
     * @param x l'index de la colonne de la Configuration dans laquelle le Bloc se situe
     */
    public Bloc(int c,int y,int x) {
	this.carac=c;
	this.deplacer(x,y);

	this.deplacerPosFin(x,y);
	this.estPlace=false;
	degCont=0;
	estLibre=false;
	estPlacable=false;}

    /*
     * ACCESSEURS
     */

    /**
     * Retourne la caractéristique du Bloc, c'est à dire sa propriété intrinsèque, qui ne dépend pas de sa situation dans la Configuration
     * @return la caractéristique du Bloc, c'est à dire sa propriété intrinsèque, qui ne dépend pas de sa situation dans la Configuration
     */
    public int getCarac() {
	return carac;
    }

    /**
     * Set la variable d'instance correspondant à l'index de la colonne de la Configuration contenant le Bloc à x, le paramètre transmis
     * @param x l'index de la colonne de la Configuration qu'occupe maintenant le Bloc
     */
    private void setX(int x) {
	this.X=x;
    }

    /**
     * Set la variable d'instance correspondant à l'index de la ligne de la Configuration contenant le Bloc à y, le paramètre transmis
     * @param y l'index de la ligne de la Configuration qu'occupe maintenant le Bloc
     */
    private void setY(int y) {
	this.Y=y;
    }

    /**
     * Set la variable d'instance correspondant à l'index de la colonne de la Configuration finale contenant le Bloc à x, le paramètre transmis
     * @param x l'index de la colonne de la Configuration finale que contient le Bloc
     */
    private void setPosFinX(int x) {
	this.posFinX=x;
    }

    /**
     * Set la variable d'instance correspondant à l'index de la ligne de la Configuration finale contenant le Bloc à x, le paramètre transmis
     * @param y l'index de la ligne de la Configuration finale que contient le Bloc
     */
    private void setPosFinY(int y) {
	this.posFinY=y;
    }
    
    /**
     * Retourne true si le Bloc est libre, c'est à dire si aucun autre Bloc n'est posé dessus, false sinon
     * @return true si le Bloc est libre, c'est à dire si aucun autre Bloc n'est posé dessus, false sinon
     */
    public boolean estLibre() {
	return estLibre;
    }
    
    /**
     * Renvoie true si le Bloc est positionné dans la Configuration comme il l'est dans la Configuration finale, et que c'est également le cas des Blocs situés en dessous de lui dans la Configuration 
     * @return true si le Bloc est positionné dans la Configuration comme il l'est dans la Configuration finale, et que c'est également le cas des Blocs situés en dessous de lui dans la Configuration
     */
    public boolean estPlace() {
	return estPlace;
    }
    
    /**
     * Renvoie le nombre de Blocs situés au dessus du Bloc dans la Configuration
     * @return le nombre de Blocs situés au dessus du Bloc dans la Configuration
     */
    public int getDegCont() {
	return degCont;
    }
    
    /**
     * Renvoie l'index de la colonne qu'occupe le Bloc dans la Configuration finale
     * @return l'index de la colonne qu'occupe le Bloc dans la Configuration finale
     */
    public int getPosFinX() {
	return posFinX;
    }

    /**
     * Renvoie l'index de la ligne qu'occupe le Bloc dans la Configuration finale
     * @return l'index de la ligne qu'occupe le Bloc dans la Configuration finale
     */
    public int getPosFinY() {
	return posFinY;
    }

    /**
     * Renvoie l'index de la colonne qu'occupe le Bloc dans la Configuration
     * @return l'index de la colonne qu'occupe le Bloc dans la Configuration
     */
    public int getPosX() {
	return this.X;
    }

    /**
     * Renvoie l'index de la ligne qu'occupe le Bloc dans la Configuration
     * @return l'index de la ligne qu'occupe le Bloc dans la Configuration
     */
    public int getPosY() {
	return this.Y;
    }

    /**
     * Renvoie true si il n'y a pas de Bloc dans la Configuration situés sous la position de ce Bloc dans la Configuration finale qui ne soient pas placés, false sinon
     * @return true si il n'y a pas de Bloc dans la Configuration situés sous la position de ce Bloc dans la Configuration finale qui ne soient pas placés, false sinon
     */
    public boolean placable() {
	return estPlacable;
    }

    /**
     * Met en mémoire true si il n'y a pas de Bloc dans la Configuration situés sous la position de ce Bloc dans la Configuration finale qui ne soient pas placés, false sinon
     * @param p true si il n'y a pas de Bloc dans la Configuration situés sous la position de ce Bloc dans la Configuration finale qui ne soient pas placés, false sinon
     */
    public void setPlacable(boolean p) {
	this.estPlacable=p;
    }

    /**
     * Met en mémoire true si le Bloc peut être déplacé dans la Configuration sans qu'aucun autre Bloc ne soit déplacé, false sinon
     * @param l true si le Bloc peut être déplacé dans la Configuration sans qu'aucun autre Bloc ne soit déplacé, false sinon
     */
    public void setLibre(boolean l) {
	this.estLibre=l;
    }

    /**
     * Met en mémoire true si le Bloc est positionné dans la Configuration comme il l'est dans la Configuration finale, et que c'est également le cas des Blocs situés en dessous de lui dans la Configuration
     * @param p true si le Bloc est positionné dans la Confuration comme il l'est dans la Configuration finale, et que c'est également le cas des Blocs situés en dessous de lui dans la Configuration 
     */
    public void setPlace(boolean p) {
	this.estPlace=p;
    }

    /**
     * Met en mémoire le nombre de Blocs situés au dessus du Bloc dans la Configuration
     * @param deg le nombre de Blocs situés au dessus du Bloc dans la Configuration
     */
    public void setDegCont(int deg) {
	this.degCont=deg;
    }

    /*
     * SERVICES
     */

    /**
     * Service déplacant le Bloc dans la Configuration à la position (X,Y)
     * @param X l'index de la colonne dans laquelle on veut placer le Bloc
     * @param Y l'index de la ligne dans laquelle on veut placer le Bloc
     */
    public void deplacer(int X, int Y) {
	this.setX(X);
	this.setY(Y);
    }

    /**
     * Service modifiant la position du Bloc dans la Configuration finale à la position (X,Y)
     * @param x l'index de la colonne que doit occuper le Bloc dans la Configuration finale
     * @param y l'index de la colonne que doit occuper le Bloc dans la Configuration finale
     */
    public void deplacerPosFin(int x, int y) {
	this.setPosFinX(x);
	this.setPosFinY(y);
    }

    /**
     * Service mettant en mémoire true si le Bloc est positionné dans la Configuration comme il l'est dans la Configuration finale
     */
    public void checkPlace() {
	if(this.getPosX()==this.getPosFinX() && this.getPosY()==this.getPosFinY()) {
	    this.setPlace(true);
	}
	else {
	    this.setPlace(false);
	}
    }
}
