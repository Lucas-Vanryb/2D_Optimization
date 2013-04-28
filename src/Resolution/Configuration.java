package Resolution;
import java.util.*;

/*
 * On importe Enregistrer pour écrire la trace de chaque déplacement dans un txt
 */
import EnregistrerTexte.Enregistrer;
import EnregistrerTexte.IEnregistrer;
import IHM.IIHM;

/**
 * Configuration est une classe modélisant des Blocs dans un univers (une aire de jeu).
 * Elle est une implémentation de IConfiguration
 * Elle contient tous les Blocs de la Configuration sur laquelle on travaillera (on déplacera des Blocs) ainsi qu'une largeur et une hauteur
 * 
 * @author Lucas VANRYB
 * 
 */
public class Configuration implements IConfiguration {

    /*
     * ATTRIBUTS
     */

    /**
     * tab est l'ArrayList de Blocs de la configuration.
     * C'est ici que sont stockés les Blocs composant la configuration.
     */
    ArrayList<Bloc> tab;

    /**
     * largeur est l'entier correspondant à la largeur de l'aire de jeu.
     * C'est à dire le nombre de colonnes de l'aire de jeu.
     */
    int largeur;

    /**
     * hauteur est l'entier correspondant à la hauteur de l'aire de jeu.
     * C'est à dire le nombre de lignes de l'aire de jeu.
     */
    int hauteur;

    /**
     * rec est un Enreistrer permettant d'ecrire tous les deplacements sur un fichier txt plac� dans le repertoire du programme et
     * dont le nom est determiné dans IHM par la String constante NOMFICHIER
     */
    IEnregistrer rec;

    /*
     * CONSTRUCTEUR
     */

    /**
     * Constructeur de Configuration
     * @param tabInt un tableau d'entier modélisant la configuration initiale, un 0 modélisant une absence de Bloc,
     * et un entier positif modélisant un bloc ayant pour "Caractéristique", c'est à dire la seule proprieté unique
     * (deux Blocs de la même configuration ont nécessairement des caractéristiques différentes) et intrinsèque au Bloc .
     * Sachant que tabInt[0] est une ligne de l'aire de jeu, on a donc en tabInt[j][i] l'entier modélisant le Bloc présent en (i,j) dans l'aire de jeu.
     * @param tabIntFin un tableau à deux dimensions d'entier modélisant la configuration finale, de la même facon que tabInt modélise la configuration initiale.
     */
    public Configuration(int[][] tabInt, int[][] tabIntFin) {

	rec=new Enregistrer(IIHM.NOMFICHIER);
	tab=new ArrayList<Bloc>();
	this.largeur=tabInt[0].length;
	this.hauteur=tabInt.length;

	/*
	 * On ajoute les Blocs à la Configuration this par lecture du tableau d'entier passé en paramètre
	 */
	for(int i=0;i<tabInt.length;i++) {
	    for(int j=0;j<tabInt[0].length;j++) {
		if( tabInt[i][j]!=0) {
		    this.ajouterBloc(new Bloc(tabInt[i][j],i,j));
		}
	    }
	}
	/*
	 * On va chercher le placement final de chaque Bloc de la configuration
	 */
	this.setPosFin(this.contruireArrayListBlocFin(tabIntFin));

	/*
	 * On vérifie si des Blocs sont déjà placés
	 */
	this.checkPlaceAll();

	/*
	 * On actualise les propriétés estLibre, estPlacable et degCont chez caque Bloc de la Configuration
	 */
	for (int k=0;k<this.getNbBlocs();k++) {
	    this.actualiser(this.getBlocs().get(k));
	}
    }

    /*
     * ACCESSEURS
     */

    /**
     * Retourne la hauteur de l'aire de jeu
     * @return hauteur, c'est à dire le nombre de lignes de l'aire de jeu.
     */
    public int getHauteur() {
	return this.hauteur;
    }

    /**
     * Retourne la largeur de l'aire de jeu
     * @return largeur, c'est à dire le nombre de colonnes de l'aire de jeu.
     */
    public int getLargeur() {
	return this.largeur;
    }

    /**
     * Retourne la liste de Blocs de la Configuration
     * @return tab, c'est à dire l'ArrayList de Bloc propre à la configuration. C'est à dire toutes les informations sur l'etat des Blocs dans la Configuration
     */
    public ArrayList<Bloc> getBlocs() {
	return this.tab;
    }

    /**
     * Retourne le nombre de Blocs composant la Configuration
     * @return le nombre de Blocs présent dans la configuration.
     */
    public int getNbBlocs() {
	return this.getBlocs().size();
    }

    /**
     * Setteur privé permettant d'ajouter le Bloc B à la configuration
     * @param B le bloc à ajouter
     */
    private void ajouterBloc(Bloc B) {
	this.tab.add(B);
    }

    /*
     * SERVICES
     */

    /**
     * Service qui recherche le Bloc de la configuration à un endroit donné de l'aire de jeu.
     * @param X la coordonnée X (ie le numéro de la colonne de l'aire de jeu) à laquelle on cherche un Bloc. 
     * @param Y la coordonnée Y (ie le numéro de la ligne de l'aire de jeu) à laquelle on cherche un Bloc.
     * @return le Bloc présent en (X,Y), null si il n'existe pas.
     */
    public IBloc getBloc(int X, int Y) {
	int i=0;
	IBloc res=null;
	boolean found=false;
	/*
	 * On cherche le Bloc situé en (X,Y) dans la liste des Blocs de la Configuration
	 */
	while(i<this.getNbBlocs() && !found) {
	    if(this.getBlocs().get(i).getPosX()==X && this.getBlocs().get(i).getPosY()==Y) {
		res=this.getBlocs().get(i);
		found=true;
	    }
	    i++;
	}
	/*
	 * Et on le retourne si on l'a trouvé, sinon on retourne null
	 */
	return res;
    }

    /**
     * Service qui recherche un Bloc par sa caractéristique.
     * @param c la caractéristique du Bloc à trouver
     * @return le Bloc de caractéristique c dans la Configuration, null si aucun Bloc de la Configuration n'a pour caracteristique c
     */
    public IBloc getBlocParNom(int c) {
	int i=0;
	IBloc res=null;
	boolean found=false;
	/*
	 * On cherche le Bloc de caractéristique c dans la liste des Blocs de la Configuration
	 */
	while(i<this.getNbBlocs() && !found) {
	    if(this.getBlocs().get(i).getCarac()==c) {
		res=this.getBlocs().get(i);
		found=true;
	    }
	    i++;
	}
	/*
	 * Et on le retourne si on l'a trouvé, sinon on retourne null
	 */
	return res;
    }

    /**
     * Service privé qui construit une ArrayList de Bloc à partir d'un tableau d'entier, en utilisant le même modèle que le constructeur
     * Il n'est utilisé qu'une fois à l'initialisation pour aider à transmettre aux différents Blocs de la Configuration leur position finale
     * @param tabIntFin un tableau d'entier à deux dimensions modélisant une Configuration de la même facon que les paramètres du constructeur
     * @return l'ArrayList de Bloc de la Configuration finale
     */
    private ArrayList<Bloc> contruireArrayListBlocFin(int[][] tabIntFin) {
	ArrayList<Bloc> l=new ArrayList<Bloc>();
	/*
	 * On parcourt le tableau passé en paramètre et lorqu'on tombe sur un Bloc on l'ajoute à l'ArrayList l,
	 * qu'on retournera.
	 */
	for(int i=0;i<tabIntFin.length;i++) {
	    for(int j=0;j<tabIntFin[0].length;j++) {
		if( tabIntFin[i][j]!=0) {
		    l.add(new Bloc(tabIntFin[i][j],i,j));
		}
	    }
	}
	return l;
    }

    /**
     * Service privé appelé une seule fois à l'initialisation par le contructeur.
     * Il permet de mettre à jour chez chaque Bloc de la Configuration sa position dans la Configuration finale
     * @param tabIntFin l'ArrayList de Bloc caractéristique de la Configuration finale
     */
    private void setPosFin(ArrayList<Bloc> tabIntFin) {
	/*
	 * On parcourt l'ArrayList de la Configuration et on va chercher le placement du Bloc correspondant dan la configuration finale.
	 */
	for(int i=0;i<this.getNbBlocs();i++) {
	    this.getBlocParNom(tabIntFin.get(i).getCarac()).deplacerPosFin(tabIntFin.get(i).getPosX(),tabIntFin.get(i).getPosY());
	}
    }

    /**
     * Service actualisant certaines des proprietés additionelles d'un Bloc à savoir estLibre,estPlacable,degCont
     * Ce service n'est invoqué qu'une seule fois, juste après que tous les Blocs de la configuration aient été actualisés
     * @param A un Bloc à actualiser
     */
    private void actualiser(IBloc A) {
	IBloc B=this.getBlocParNom(A.getCarac());
	this.checkLibre(B);
	this.checkPlacable(B);
	this.checkDegCont(B);
    }

    /**
     * Service actualisant la proprieté estPlace chez tous les Blocs de la configuration
     */
    private void checkPlaceAll() {
	/*
	 * On appelle la méthode checkPlace() pour tous les Blocs de la Configuration situé au sommet d'une colonne,
	 * la méthode checkPlacé vérifiant si tous les Blocs de la colonne sont placés et mettant leur attribut estPlace à jour.
	 */
	for (IBloc B : this.getBlocs()) {
	    if(getBlocAuSommet(B.getPosX())-1==B.getPosY()) {
		this.checkPlace(B);
	    }
	}
    }

    /**
     * Service verifiant si les Blocs de la Configuration sont placables.
     * Il est appelé à chaque fois qu'un nouveau Bloc est placé.
     */
    public void verifierBlocsPlacables() {
	for (int i=0;i<this.getNbBlocs();i++) {
	    this.checkPlacable(this.getBlocs().get(i));
	}
    }

    /**
     * Service visant à actualiser le degré de contrainte d'un Bloc dans la Configuration,
     * c'est à dire le nombre de Bloc qui sont posés sur lui.
     * @param A le Bloc dont on veut vérifier le degCont
     */
    private void checkDegCont(IBloc A) {
	int i=1;
	int res=0;
	boolean stop=false;
	IBloc B=this.getBlocParNom(A.getCarac());
	/*
	 * On parcourt la colonne de bas en haut à partir du Bloc et on incrémente degCont jusqu'à tomber sur une "case" vide
	 */
	do {
	    if (this.getBloc(B.getPosX(),B.getPosY()+i)!=null) {
		res++;
	    }
	    else{
		stop=true;
	    }
	    i++;
	}
	while (B.getPosY()+i<this.getHauteur() && !stop);
	B.setDegCont(res);
    }

    /**
     * Service visant à actualiser le booleen estLibre pour un Bloc de la configuration,
     * qui vaut true ssi le Bloc n'a pas de Blocs posés sur lui
     * @param A le bloc dont on veut verifier l'attribut
     */
    private void checkLibre (IBloc A) {
	IBloc B=this.getBlocParNom(A.getCarac());
	/*
	 * On vérifie que le Bloc n'est pas au sommet d'une colonne pleine, sinon il est nécessairement libre, et on set le booleen estLibre
	 */
	if (B.getPosY()+1<this.getHauteur()) {
	    /*
	     * Si un Bloc est posé au dessus de lui, il n'est pas libre et on set son booleen estLibre à false
	     */
	    if (this.getBloc(B.getPosX(),B.getPosY()+1)!=null) {
		B.setLibre(false);
	    }
	    /*
	     * Sinon il l'est, et on set sa variable d'instance estLibre à true
	     */
	    else{
		B.setLibre(true);
	    }
	}
	else {
	    B.setLibre(true);
	}

    }


    /**
     * Service actualisant le booleen placable chez un Bloc,
     * ce booleen vaut true si et seulement si sa position finale est sur le sol,
     * ou si les Blocs positionnés sous sa position finale sont placés
     * @param A le Bloc dont on vérifie la placabilité
     */
    private void checkPlacable(IBloc A) {
	IBloc B=this.getBlocParNom(A.getCarac());
	/*
	 * Si le Bloc est posé sur le "sol" dans la configuration finale, il est placable
	 */
	if(B.getPosFinY()==0) {
	    B.setPlacable(true);
	}
	else {
	    int i=1;
	    boolean stop=false;
	    /*
	     * On parcourt les "cases" de la colonne dans laquelle doit être placé le Bloc et situés sous sa position finale
	     */
	    while(B.getPosFinY()-i>=0 && !stop) {
		/*
		 * Si on tombe sur une "case" vide, alors le Bloc n'est pas placable
		 * (il faut d'abord placer le Bloc correspondant à cette case dans la Configuration finale)
		 */
		if(this.getBloc(B.getPosFinX(), B.getPosFinY()-i)==null) {
		    stop=true;
		}
		else {    
		    /*
		     * De même, si on tombe sur un Bloc qui n'est pas à sa place, alors le Bloc n'est pas placable
		     * (il faut d'abord placer le Bloc correspondant à cette case dans la Configuration finale)
		     */
		    if (!this.getBloc(B.getPosFinX(), B.getPosFinY()-i).estPlace()) {
			stop=true;
		    }
		}
		i++;
	    }
	    /*
	     * Si tous les Blocs situes sous la position finale du Bloc sont placés, alors il est placable
	     */
	    if(!stop) {
		B.setPlacable(true);	
	    }
	    /*
	     * Si on est tombé sur une case vide et/ou un Bloc mal placé, le Bloc n'est pas placable
	     */
	    else {
		B.setPlacable(false);
	    }
	}
    }

    /**
     * Service permettant de déplacer le Bloc de la configuration situé en (X1,Y1) en (X2,Y2), et de mettre à jour les degCont et les estLibre des Blocs affectés
     * Si ce déplacement est impossible parce qu'il y a un bloc en (X2,Y2) ou pas de Bloc en (X2,Y2-1), print une erreur et quitte l'application
     * De même si le Bloc en (X1,Y1) n'est pas libre, print une erreur et quitte l'application
     * @param X1 la coordonnée X dans la Configuration du Bloc à déplacer
     * @param Y1 la coordonnée Y dans la Configuration du Bloc à déplacer
     * @param X2 la coordonnée X dans la Configuration qu'occupera le Bloc après le déplacement
     * @param Y2 la coordonnée Y dans la Configuration qu'occupera le Bloc après le déplacement
     */
    public void deplacerBloc(int X1, int Y1, int X2, int Y2) {

	IBloc B=this.getBloc(X1, Y1);
	/*
	 * On vérifie que le Bloc en (X1,Y1) est bien libre
	 * Sinon on print une erreur et on quitte l'application (cas normalement impossible)
	 */
	if(B.estLibre()) {

	    /*
	     * On vérifie qu'il n'y a pas de Bloc en (X2,Y2) et qu'on peut y poser un Bloc
	     * Sinon on print une erreur et on quitte l'application (cas normalement impossible)
	     */
	    if (this.getBloc(X2,Y2)==null && (this.getBloc(X2, Y2-1)!=null || Y2==0) && Y2<this.getHauteur()) {

		/*
		 * On déplace le Bloc à proprement parlé
		 */
		B.deplacer(X2, Y2);

		/*
		 * On met à jour le booleen estLibre chez le Bloc qui était
		 * sous le Bloc déplacé, et le Bloc qui le soutient maintenant, si ils existent.
		 */
		if(this.getBloc(X1, Y1-1)!=null) {
		    this.getBloc(X1, Y1-1).setLibre(true);
		}
		if(this.getBloc(X2, Y2-1)!=null) {
		    this.getBloc(X2, Y2-1).setLibre(false);
		}
		/*
		 * On regarde si le Bloc qui vient d'être déplacé est maintenant placé
		 */
		this.checkPlace(B);

		/*
		 * On met à jour le degCont chez les Bocs concernés :
		 * Le Bloc qui vient d'être déplacé a nécessairement pour degCont 0
		 */
		B.setDegCont(0);

		/*
		 * Tous les Blocs sous la nouvelle position du Bloc gagne 1 en degCont
		 * puisqu'un Bloc supplémentaire est dans la colonne dans laquelle ils sont.
		 */
		for(int i=1;Y2-i>=0;i++){
		    this.getBloc(X2, Y2-i).setDegCont(this.getBloc(X2, Y2-i).getDegCont()+1);
		}

		/*
		 * Tous les Blocs sous l'ancienne position du Bloc déplacé perde 1 degCont
		 * puisqu'un Bloc a quitté la colonne dans laquelle ils sont.
		 */
		for(int i=1;Y1-i>=0;i++){
		    this.getBloc(X1, Y1-i).setDegCont(this.getBloc(X1, Y1-i).getDegCont()-1);
		}

		/*
		 * On affiche le déplacement dans la console
		 */
		System.out.println("Deplacement du Bloc"+B.getCarac()+" en ("+B.getPosX()+","+B.getPosY()+")");

		/*
		 * On écrit dans un fichier texte le déplacement qui vient d'être réalisé
		 */
		rec.EcrireTexte("  Deplacement du Bloc"+B.getCarac()+" en ("+B.getPosX()+","+B.getPosY()+")");
	    }
	    else {
		System.out.println("Erreur interne, fermeture du programme!");
		System.exit(1);
	    }
	}
	else{
	    System.out.println("Erreur interne, fermeture du programme");
	    System.exit(1);
	}
    }

    /**
     * Service vérifiant si un Bloc de la Configuration est placé, c'est à dire si il est au même emplacement que dans la Configuration finale, et si c'est également le cas de tous les Blocs en dessous de lui et qui ajuste son attribut estPlace en conséquence.
     * @param bloc le bloc dont on veut vérifier le placement
     */
    public void checkPlace(IBloc bloc) {
	/*
	 * Si le Bloc est pose sur le sol, on vérifie juste qu'il est dans la même position que dans la Configuration finale
	 */
	if(bloc.getPosY()==0) {
	    bloc.checkPlace();
	}
	else {
	    /*
	     * Sinon, on vérifie que les Bloc d'en dessous sont placés
	     */
	    checkPlace(this.getBloc(bloc.getPosX(),bloc.getPosY()-1));
	    if(this.getBloc(bloc.getPosX(),bloc.getPosY()-1).estPlace()) {
		/*
		 * Si ils le sont, on vérifie l'emplacement du Bloc et on change son attribut estPlace en conséquence
		 */
		bloc.checkPlace();
	    }
	    else {
		/*
		 * Sinon, il ne peut pas être considéré comme placé, et on set son booleen estPlace à false
		 */
		bloc.setPlace(false);
	    }

	}
    }

    /**
     * Service qui retourne le plus petit index Y de la colonne passée en paramètre tel que la "case" (X,Y) soit vide
     * @param X la colonne dont on veut la première case libre
     * @return un entier tel Y tel que le déplacement d'un Bloc libre vers (X,Y) soit possible si il existe, -1 sinon 
     */
    public int getBlocAuSommet(int X) {
	int i=0;
	int res=-1;
	boolean found=false;
	/*
	 * On parcourt les cases de la colonne X en partant du sol jusqu'à trouver une "case" libre
	 */
	while(i<this.getHauteur() && !found) {
	    if(this.getBloc(X, i)==null) {
		found=true;
		res=i;
	    }
	    i++;
	}
	return res;
    }

    /**
     * Service permettant de trouver une colonne de l'aire de jeu qui n'est pas pleine, et qui n'est pas une des deux colonnes dont l'index est passé en paramètre, et renvoie son index, ou -1 si il n'en n'existe pas
     * @param X1 la première colonne à exclure des recherches 
     * @param X2 la deuxième colonne à exclure des recherches
     * @return l'index d'une colonne non vide différent de X1 et de X2 ou -1 si il n'en n'existe pas
     */
    public int trouverUneColonneNonPleine(int X1, int X2) {
	int i=0;
	int res=-1;
	boolean found=false;

	/*
	 * On parcourt les colonnes de l'aire de jau
	 */
	while(i<this.getLargeur() && !found) {
	    /*
	     * On ignore la colonne d'index X1 et celle d'index X2 des recherches
	     */
	    if (i!=X1 && i!=X2) {
		if(this.getBlocAuSommet(i)!=-1) {
		    found=true;
		    res=i;
		}
	    }
	    i++;
	}
	/*
	 * On renvoie l'index de la colonne trouvée si elle existe, -1 sinon
	 */
	return res;
    }

    /**
     * Service renvoyant l'index d'une colonne pleine de la Configuration d'index différent de X2 si il en existe une, -1 sinon
     * @param X2 l'index de la colonne à exclure des recherches
     * @return l'index de la colonne pleine différente de X2 si elle existe, -1 sinon
     */
    public int trouverUneColonnePleine(int X2) {
	int i=0;
	int res=-1;
	boolean found=false;
	/*
	 * On parcourt les colonnes de l'aire de jeu jusqu'à en tomber sur une pleine différente de X2
	 */
	while(i<this.getLargeur() && !found) {
	    /*
	     * On exclue X2 des recherches
	     */
	    if(i!=X2) {
		if(this.getBlocAuSommet(i)==-1) {    
		    found=true;
		    res=i;
		}
	    }
	    i++;
	}
	return res;
    }

    /**
     * Service renvoyant true si tous les Blocs de la Configuration sont placés, et false sinon
     * @return true si tous les Blocs de la Configuration sont placés, false sinon
     */
    public boolean win() {
	int i=0;
	while(i<this.getNbBlocs()) {
	    if(!this.getBlocs().get(i).estPlace()) {
		return false;
	    }
	    i++;
	}
	/*
	 * On ferme le Buffer d'écriture, la résolution étant terminée
	 */
	rec.finaliser();
	return true;

    }

    /*
     * Main permettant de tester la Classe
     */
    public static void main(String[] args) {
	int[][] tab1 = new int[][]{{1,2,3},{4,5,9},{6,7,8}};
	int[][] tab2 = new int[][]{{1,2,3},{4,5,6},{8,7,9}};
	IConfiguration c=new Configuration(tab1,tab2);
	System.out.println(c.getBlocParNom(1).getPosX());
	System.out.println(c.getBlocParNom(1).estPlace());
	System.out.println(c.getBlocParNom(1).estLibre());
	System.out.println(c.getBlocParNom(1).placable());
	System.out.println(c.getBlocParNom(6).getPosY());
	System.out.println(c.getBlocParNom(6).getPosFinY());
	System.out.println(c.getBlocParNom(8).getPosY());
	System.out.println(c.getBlocParNom(3).estPlace());
	System.out.println(c.getBlocParNom(9).placable());
    }



}
