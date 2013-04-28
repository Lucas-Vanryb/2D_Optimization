package IHM;

import javax.swing.JOptionPane;

import EnregistrerTexte.Lire;
/**
 * Classe implémentant IIHM
 * 
 * @author Lucas VANRYB
 */
public class IHM implements IIHM {

    /*
     * ATTRIBUTS
     */
    
    /**
     * hauteur est un int qui vaut la hauteur de l'aire de jeu
     */
    int hauteur;
    /**
     * largeur est un int qui vaut la largeur de l'aire de jeu
     */
    int largeur;
    /**
     * tab est un tableau d'entier de dimension deux modélisant la configuration initiale
     */
    int[][] tab;
    /**
     * tabFin est un tableau d'entier de dimension deux modélisant la configuration finale
     */
    int [][] tabFin;
    /**
     * toggle est un boolean qui vaut true si l'IHM est activée et false sinon
     */
    boolean toggle;
    /**
     * Construit une IHM c'est à dire lance la séquence programmée
     * Demande à l'utilisateur de saisir la hauteur et la largeur qu'il souhaite et lui demande si il souhaite poursuivre avec la saisie IHM ou si il veut lire un fichier texte
     */
    public IHM() {
	this.getInfos();
	this.tab=new int[this.hauteur][this.largeur];
	this.tab=new int[this.hauteur][this.largeur];
	this.toggleIHM();
    }

    /*
     * ACCESSEURS
     */
    
    /**
     * Retourne la hauteur de l'aire de jeu, définie par l'utilisateur
     * @return la hauteur de l'aire de jeu, définie par l'utilisateur
     */
    public int getHauteur() {
	return this.hauteur;
    }

    /**
     * Retourne la largeur de l'aire de jeu, définie par l'utilisateur
     * @return la largeur de l'aire de jeu, définie par l'utilisateur
     */
    public int getLargeur() {
	return this.largeur;
    }

    /**
     * Set la hauteur de l'aire de jeu
     * @param h la hauteur de l'aire de jeu
     */
    private void setHauteur(int h) {
	this.hauteur=h;
    }

    /**
     * Set la largeur de l'aire de jeu
     * @param l la largeur de l'aire de jeu
     */
    private void setLargeur(int l) {
	this.largeur=l;
    }

    /**
     * Retourne le tableau d'entier modélisant la configuration initiale
     * @return le tableau d'entier modélisant la configuration initiale
     */
    public int[][] getTabInt() {
	return this.tab;
    }

    /**
     * Retourne le tableau d'entier modélisant la configuration finale
     * @return le tableau d'entier modélisant la configuration finale
     */
    public int[][] getTabIntFin() {
	return this.tabFin;
    }

    /**
     * Renvoie true si l'utilisateur choisit la saisie via IHM, false sinon
     * @return le tableau d'entier modélisant la configuration initiale
     */
    public boolean getToggle() {
	return this.toggle;
    }

    /*
     * SERVICES
     */
    
    /**
     * Demande à l'utilisateur de saisir les dimensions de l'aire de jeu et met à jour ces dernières dans this
     */
    public void getInfos() {
	/*
	 * On appelle l'interface permettant à l'utilisateur de saisir la hauteur et la largeur de l'aire de jeu
	 */
	IDialogBox d=new DialogBox();
	/*
	 * On attend qu'il ait saisi les données
	 */
	while(d.getEnCours()) {
	}
	/*
	 * On met à jour les données de this
	 */
	this.setHauteur(d.getHauteur());
	this.setLargeur(d.getLargeur());
    }

    /**
     * Demande à l'utilisateur de saisir les configurations via l'interface crée par la JFrame Saisie
     */
    public void getSaisie(){
	/*
	 * On appelle l'interface permettant à l'utilisateur de saisir les données des configurations finale et initiale
	 */
	Saisie s=new Saisie(this.hauteur,this.largeur);
	/*
	 * On attends qu'il ait saisit les données
	 */
	while(s.enCours) {
	}
	/*
	 * On met à jour les données de this
	 */
	this.tab=s.getTab();
	this.tabFin=s.getTabFin();
    }

    /**
     * Méthode demandant à l'utilisateur si il veut afficher le fichier texte contenant les déplacements éffectués ou si il veut quitter le programme
     */
    public void demandeAfficherTexte() {
	String[] options={"Ne pas afficher le fichier texte","Afficher le fichier texte"};
	int reponse=JOptionPane.showOptionDialog(null, "Résolution terminée. Deplacements enregistrés dans le fichier"+NOMFICHIER, "Résolution terminée", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
	if (!(reponse==JOptionPane.OK_OPTION)) {
	    this.afficherTexte();
	}
	else {
	    System.exit(0);
	}
    }

    /**
     * Méthode affichant le texte enregistré contenant tous les déplacements éffectués
     */
    private void afficherTexte() {
	new AfficherTexte(Lire.lireTexteEnreg());
    }

    /**
     * Méthode demandant à l'utilisateur si il veut utiliser l'interface graphique pour saisir les configurations ou non
     */
    private void toggleIHM() {
	if(this.getHauteur()<=15 && this.getLargeur()<=15) {
	    String[] options={"Par lecture d'un fichier texte","Par l'interface graphique"};
	    int reponse=JOptionPane.showOptionDialog(null, "Comment voulez vous saisir vos données  ?", "Choix de la méthode", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
	    if (!(reponse==JOptionPane.OK_OPTION)) {
		this.toggle=true;
	    }
	    else {
		this.toggle=false;
	    }
	}
	else {
	    this.toggle=false;
	}
    }
}
