package Resolution;
import javax.swing.JOptionPane;
import IHM.*;
import LectureTexte.Lecture;
import LectureTexte.ILecture;

/**
 * Classe implémentant IProbleme
 * 
 * @author Lucas VANRYB
 *
 */
public class Probleme implements IProbleme {

    /*
     * ATTRIBUTS
     */
    /**
     * Configuration que l'on va modifier tout au long de l'execution jusqu'à obtenir la Configuration finale
     */
    IConfiguration c;
    /**
     * WatchDog permettant d'arrêter le programme si la résolution se montre impossible
     */
    int watchDog;
    /**
     * Coefficient jouant sur le nombre de tentatives de déplacement maximum autorisé avant l'arrêt du programme
     */
    final static int COEFF_WATCHDOG=20;
    /**
     * Booleen valant true si l'utilisateur choisit de saisir les configurations par l'IHM, false sinon
     */
    boolean iHMToggle;
    /**
     * L'IHM liée au problème, assurant la liaison avec l'utilisateur
     */
    IIHM iHM;

    /*
     * CONSTRUCTEUR 
     */

    /**
     * Construit un Probleme à partir d'une IIHM
     * @param iHM une Interface graphique
     */
    public Probleme (IIHM iHM) {
	/*
	 * On regarde si l'utilisateur veut saisir ses données via l'IHM
	 */
	this.iHMToggle=iHM.getToggle();
	/*
	 * Si oui, on lui fait saisir les configurations et on crée la Configuration à partir des tableaux d'entiers de l'IIHM
	 */
	if(this.iHMToggle) {
	    this.iHM=iHM;
	    this.getIHM().getSaisie();
	    this.c = new Configuration (this.getIHM().getTabInt(),this.getIHM().getTabIntFin());
	    this.watchDog=0;
	    this.resoudre();
	}
	/*
	 * Sinon, on appelle la méthode demandant à l'utilisateur de saisirun chemin vers un fichier, on lit ce fichier, et on remplit les tableaux à partir des attributs de l'instances de ILecture
	 */
	else{
	    ILecture l;
	    l=new Lecture(iHM.getHauteur(),iHM.getLargeur());
	    this.watchDog=0;
	    this.c= new Configuration (l.getTab(),l.getTabFin());
	    this.resoudre();

	}
    }

    /*
     * ACCESSEURS
     */

    /**
     * Retourne la Configuration associée au Probleme
     * @return la Configuration associée au Probleme
     */
    public IConfiguration getConfig() {
	return this.c;
    }

    /**
     * Retourne l'IHM liée au Problème
     * @return l'IHM liée au Problème
     */
    private IIHM getIHM() {
	return this.iHM;
    }

    /**
     * Retourne la valeur du WatchDog
     * @return la valeur du WatchDog
     */
    private int getWatchDog() {
	return this.watchDog;
    }

    /**
     * Incrémente de un le WatchDog
     */
    private void incrementeWatchDog() {
	this.watchDog++;
    }

    /*
     * SERVICES
     */

    /**
     * Service déplacant tous les Blocs qu'il faut pour déplacer un Bloc B en (X2,Y2)
     * @param B le Bloc à deplacer
     * @param X2 l'index de la colonne de la configuration dans laquelle on veut déplacer B
     * @param Y2 l'index de la ligne de la configuration dans laquelle on veut déplacer B
     */
    private void deplacerBloc(IBloc B,int X2,int Y2) {
	/*
	 * On verifie qu'on est dans un nombre d'essais autorisé, sinon on affiche une erreur et on quitte le programme
	 */
	if(this.getWatchDog()<COEFF_WATCHDOG*this.getConfig().getNbBlocs()) {
	    
	    /*
	     * On incremente le WatchDog
	     */
	    this.incrementeWatchDog();
	    
	    int ligArrivee;
	    int colArrivee;
	    /*
	     * On imprime l'objectif des déplacements qui vont suivre dans la console
	     */
	    System.out.println("Nouvel objectif : Deplacer" + B.getCarac()+ " en ("+X2+","+Y2+")");

	    /*
	     * Si B est sur sa colonne d'arrivée et qu'il existe une colonne pleine (différente de X2) alors on met B au sommet de cette colonne à la place de l'ancien Bloc
	     */
	    if(B.getPosX()==X2 && this.getConfig().trouverUneColonnePleine(X2)!=-1) {
		this.deplacerBloc(B, this.getConfig().trouverUneColonnePleine(X2), this.getConfig().getHauteur()-1);
	    }
	    int stop2=B.getDegCont();
	    
	    /*
	     * On déplace tous les Blocs situés au dessus de B afin de povoir le déplacer
	     */
	    for(int i=0;i<stop2;) {
		colArrivee=this.getConfig().trouverUneColonneNonPleine(B.getPosX(), X2);
		if(colArrivee==-1) {
		    /*
		     * Si aucune colonne ne peut acceuillir les Blocs au dessus de B, on place B sur une colonne pleine, ce qui laisse plus de case disponnible pour placer les cases au dessus de lui
		     */
		    if (this.getConfig().trouverUneColonnePleine(B.getPosX())!=-1) {
			System.out.println("Pour accomplir l'objectif, il faut d'abord mettre "+B.getCarac()+" en ("+this.getConfig().trouverUneColonnePleine(B.getPosX())+","+(this.getConfig().getHauteur()-1)+")");
			this.deplacerBloc(B, this.getConfig().trouverUneColonnePleine(B.getPosX()), this.getConfig().getHauteur()-1);
			i=0;
			stop2=B.getDegCont();
		    }
		}
		else {
		    ligArrivee=this.getConfig().getBlocAuSommet(colArrivee);
		    this.getConfig().deplacerBloc(B.getPosX(), B.getPosY()+stop2-i, colArrivee, ligArrivee);
		    i++;
		}
	    }
	   
	    IBloc C=this.getConfig().getBloc(X2, Y2);
	    /*
	     * Si la position d'arrivee de (X2,Y2) est plus haute que la position actuelle de B, on déplace B sur une autre colonne
	     */
	    if(C!=null) {
		if(this.getConfig().getBlocAuSommet(C.getPosX())>B.getPosY()) {
		    colArrivee=this.getConfig().trouverUneColonneNonPleine(B.getPosX(), X2);
		    if(colArrivee==-1) {
			/*
			 * Si aucune colonne non pleine n'est disponnible, alors on déplace B au sommet d'une colonne pleine à la place d'un autre Bloc
			 */
			if (this.getConfig().trouverUneColonnePleine(B.getPosX())!=-1) {
			    System.out.println("Pour accomplir l'objectif, il faut d'abord mettre "+B.getCarac()+" en ("+this.getConfig().trouverUneColonnePleine(B.getPosX())+","+(this.getConfig().getHauteur()-1)+")");
			    this.deplacerBloc(B, this.getConfig().trouverUneColonnePleine(B.getPosX()), this.getConfig().getHauteur()-1);
			}
			else {
			    JOptionPane.showMessageDialog(null,"Cas non résolvable, désolé","Erreur",JOptionPane.ERROR_MESSAGE);
			    System.out.println("Cas non resolvable, sorry kid");
			    System.exit(1);
			}
		    }
		    else {
			ligArrivee=this.getConfig().getBlocAuSommet(colArrivee);
			this.getConfig().deplacerBloc(B.getPosX(), B.getPosY(), colArrivee, ligArrivee);
		    }
		}
	    }

	    /*
	     * On libère tous les Blocs situé à la position d'arrivée et ceux d'au dessus
	     */
	    if (C!=null) {
		System.out.println("On degage le bloc "+ C.getCarac());
		int stop=C.getDegCont();
		System.out.println("Il y a "+stop+" blocs dessus");
		for(int i=0;i<=stop;) {
		    colArrivee=this.getConfig().trouverUneColonneNonPleine(B.getPosX(), X2);
		    if(colArrivee==-1) {
			/*
			 * Si il n'y a plus de place pour vider ces blocs, on déplce B au sommet d'une colonne pleine
			 */
			if (this.getConfig().trouverUneColonnePleine(B.getPosX())!=-1) {
			    this.deplacerBloc(B, this.getConfig().trouverUneColonnePleine(B.getPosX()), this.getConfig().getHauteur()-1);
			    stop=C.getDegCont();
			    i=0;
			}
			else {
			    JOptionPane.showMessageDialog(null,"Cas non résolvable, désolé","Erreur",JOptionPane.ERROR_MESSAGE);
			    System.out.println("Cas non resolvable, sorry kid");
			    System.exit(1);
			}
		    }
		    else {
			ligArrivee=this.getConfig().getBlocAuSommet(colArrivee);
			this.getConfig().deplacerBloc(C.getPosX(), C.getPosY()+stop-i, colArrivee, ligArrivee);
			i++;
		    }

		}
	    }
	    /*
	     * On déplace le Bloc B à sa position d'arrivée
	     */
	    this.getConfig().deplacerBloc(B.getPosX(), B.getPosY(), X2, Y2);
	    /*
	     * On regarde si des Blocs de la Configuration deviennent placable
	     */
	    this.getConfig().verifierBlocsPlacables();
	}
	else {
	    JOptionPane.showMessageDialog(null,"Cas non résolvable, désolé","Erreur",JOptionPane.ERROR_MESSAGE);
	    System.out.println("Cas non resolvable, sorry kid");
	    System.exit(1);
	}

    }

    /**
     * Retourne le Bloc le plus propice à être placé au prochain mouvement
     * @return le Bloc le plus propice à être placé au prochain mouvement
     */
    private IBloc getBlocADeplacer() {
	IBloc B=this.getConfig().getBlocs().get(0);
	int j=0;
	/*
	 * On cherche le Bloc au degCont le plus grand pour avoir un point de comparaison dans la boucle d'après
	 */
	do {
	    if(this.getConfig().getBlocs().get(j).getDegCont()>=B.getDegCont()) {
		B=this.getConfig().getBlocs().get(j);
	    }
	    j++;
	}
	while(j<this.getConfig().getNbBlocs());

	/*
	 * On choisit le meilleur Bloc possible
	 * Il doit être placable
	 * Il ne doit pas être placé
	 * Il doit avoir le plus petit degCont possible
	 */
	for(int i=0;i<this.getConfig().getNbBlocs();i++) {
	    if(!this.getConfig().getBlocs().get(i).estPlace() && this.getConfig().getBlocs().get(i).placable() && this.getConfig().getBlocs().get(i).getDegCont()<=B.getDegCont()) {
		this.getConfig().checkPlace(this.getConfig().getBlocs().get(i)); 
		B=this.getConfig().getBlocs().get(i);
	    }
	}
	System.out.println("On choisit de placer le bloc "+ B.getCarac());
	/*
	 * On retourne le Bloc choisi
	 */
	return B;
    }

    /**
     * Méthode résolvant le problème
     */
    public void resoudre() {
	IBloc B;
	/*
	 * Tant que l'objectif n'est pas atteint
	 */
	while(!this.getConfig().win()) {
	    /*
	     * On cherche un Bloc à placer
	     */
	    B=this.getBlocADeplacer();
	    /*
	     * On éffectue les déplacements nécessaires pour placer ce Bloc
	     */
	    this.deplacerBloc(B, B.getPosFinX(), B.getPosFinY());
	}
	/*
	 * Quand le Problème est résolu, on demande à l'utilisateur si il veut voir le fichier texte contenant les déplacemets éffectués
	 */
	this.getIHM().demandeAfficherTexte();
    }
}
