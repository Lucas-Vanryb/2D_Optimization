package LectureTexte;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Classe implémentant iLecture
 * 
 * @author Lucas VANRYB
 */
public class Lecture implements ILecture {
    
    /*
     * ATTRIBUTS
     */
    
    /**
     * Tableau d'entier en deux dimensions servant de modèle de iConfiguration initiale
     */
    int[][] tab;
    /**
     * Tableau d'entier en deux dimensions servant de modèle de iConfiguration finale
     */
    int[][] tabFin;
    /**
     * Entier valant le nombre de lignes que contient l'aire de jeu
     */
    int hauteur;
    /**
     * Entier valant le nombre de colonnees que contient l'aire de jeu
     */
    int largeur;

    /*
     * CONSTRUCTEUR
     */

    /**
     * Construit une instance de Lecture à partir du nombre de lignes et du nombre de colonnes de l'aire de jeu
     * @param hauteur le nombre de lignes de l'aire de jeu
     * @param largeur le nombre de colonnes de l'aire de jeu
     */
    public Lecture(int hauteur, int largeur) {
	this.largeur=largeur;
	this.hauteur=hauteur;
	this.tab=new int[hauteur][largeur];
	this.tabFin=new int[hauteur][largeur];
	JOptionPane.showMessageDialog(null,"Le fichier texte doit être formatté comme suit, avec en haut la configuration initiale et en bas la configuration finale, séparées par une ligne vide : \n0,0,0\n3,4,0\n1,2,5 \n \n3,0,0 \n1,2,0 \n4,5,0","Formattage",JOptionPane.INFORMATION_MESSAGE);
	this.init();
    }

    /*
     * ACCESSEURS
     */

    /**
     * Retourne le tableau d'entier de dimension deux modélisant la configuration initiale
     * @return le tableau d'entier de dimension deux modélisant la configuration initiale
     */
    public int[][] getTab() {
	return this.tab;
    }

    /**
     * Retourne le tableau d'entier de dimension deux modélisant la configuration finale
     * @return le tableau d'entier de dimension deux modélisant la configuration finale
     */
    public int[][] getTabFin() {
	return this.tabFin;
    }

    /**
     * Méthode qui demande à l'utilisateur de stipuler un chemin vers le fichier texte à lire, puis appelle la méthode permettant de lire ce fichier
     */
    private void init() {
	String nom="";
	nom = JOptionPane.showInputDialog(null, "Merci de stipuler le chemin menant au fichier texte dans le champ ci-dessous", "Chemin", JOptionPane.QUESTION_MESSAGE);
	if(nom==null) {
	    System.out.println("Annulation, fermeture du programme");
	    System.exit(1);
	}
	this.lireFichierTexte(nom);
    }

    /**
     * Méthode permettant de remplir les tableaux d'entiers modélisant les configurations à partir de la lecture du fichier texte si celui ci est valide, sinon signale l'erreur et quitte le programme
     * @param path le chemin menant au fichier texte
     */
    private void lireFichierTexte(String path) {
	try {
	    /*
	     * On crée les listes permettant de vérifier la validité du fichier texte
	     */
	    ArrayList<Integer> list=new ArrayList<Integer>();
	    ArrayList<Integer> listFin=new ArrayList<Integer>();
	    String aDiviser;
	    BufferedReader aLire=new BufferedReader(new FileReader(path));
	    int j=0;
	    /*
	     * On parcourt le fichier texte
	     */
	    do {
		/*
		 * On lit une ligne dans le fichier texte
		 */
		aDiviser=aLire.readLine();
		/*
		 * On vérifie que cette ligne contient quelquechose (c'est à dire qu'elle n'est pas la ligne intermédiaire)
		 */
		if (aDiviser!=null) {
		    /*
		     * On découpe la String grâce au séparateur ","
		     */
		    String[] tokens = aDiviser.split("\\,");
		    /*
		     * Si le résultat ne correspond pas à la largeur transmise au constructeur, alors on imprime une érreur et on arrête la lecture
		     */
		    if( j!= this.hauteur && tokens.length!=this.largeur) {
			JOptionPane.showMessageDialog(null,"Votre fichier texte ne correspond pas au données de largeur/hauteur que vous nous avez fourni et/ou contient des erreurs: fermeture du programme","Erreur Fatale",JOptionPane.ERROR_MESSAGE);
			System.out.println("Votre fichier texte ne correspond pas au données de largeur/hauteur que vous nous avez fourni et/ou contient des erreurs: fermeture du programme");
			System.exit(1);
		    }
		    else {
			for(int i=0; i<this.largeur;i++) {
			    /*
			     * On regarde si le texte ne contient que des entiers séparés par les bons séparateurs, sinon on signale l'erreur et on ferme le programme
			     */
			    try {
				/*
				 * Si on est dans la première partie du texte, on remplit le tableau modélisant la configuration initiale
				 */
				if(j<this.hauteur) {
				    /*
				     * On sauvegarde la valeur dans le tableau
				     */
				    tab[this.hauteur-1-j][i]=Integer.parseInt(tokens[i]);
				    /*
				     * Si un nombre négatif est présent dans le fichier texte, on signale l'erreur et on quitte le programme
				     */
				    if(Integer.parseInt(tokens[i])<0) {
					JOptionPane.showMessageDialog(null,"Merci de ne pas faire apparaitre de nombres négatifs, veuillez modifier le fichier texte et réessayer","Erreur",JOptionPane.ERROR_MESSAGE);
					System.out.println("Merci de ne pas faire apparaitre de nombres négatifs, veuillez modifier le fichier texte et réessayer");
					System.exit(1);
				    }
				    /*
				     * Si la liste des nombres déja ajoutés au tableau contient déjà le nombre qu'on veut ajouter, on signale l'erreur et on quitte le programme
				     */
				    if(!list.contains(Integer.parseInt(tokens[i])) || Integer.parseInt(tokens[i])==0) {
					/*
					 * On ajoute le nombre à la liste
					 */
					list.add(Integer.parseInt(tokens[i]));
				    }
				    else {
					JOptionPane.showMessageDialog(null,"Merci de ne pas faire apparaitre deux fois le meme bloc dans la configuration initiale, veuillez modifier le fichier texte et réessayer","Erreur",JOptionPane.ERROR_MESSAGE);
					System.out.println("Merci de ne pas faire apparaitre deux fois le meme bloc dans la configuration initiale, veuillez modifier le fichier texte et réessayer");
					System.exit(1);
				    }
				}
				/*
				 * Si on est dans la deuxième partie du texte, on remplit le tableau modélisant la configuration finale
				 */
				if(j>this.hauteur) {
				    /*
				     * On sauvegarde la valeur dans le tableau
				     */
				    tabFin[2*this.hauteur-j][i]=Integer.parseInt(tokens[i]);
				    /*
				     * Si un nombre négatif est présent dans le fichier texte, on signale l'erreur et on quitte le programme
				     */
				    if(Integer.parseInt(tokens[i])<0) {
					JOptionPane.showMessageDialog(null,"Merci de ne pas faire apparaitre de nombres négatifs, veuillez modifier le fichier texte et réessayer","Erreur",JOptionPane.ERROR_MESSAGE);
					System.out.println("Merci de ne pas faire apparaitre de nombres négatifs, veuillez modifier le fichier texte et réessayer");
					System.exit(1);
				    }
				    /*
				     * Si la liste des nombres déja ajoutés au tableau contient déjà le nombre qu'on veut ajouter, on signale l'erreur et on quitte le programme
				     */
				    if(!listFin.contains(Integer.parseInt(tokens[i])) || Integer.parseInt(tokens[i])==0) {
					/*
					 * On ajoute le nombre à la liste
					 */
					listFin.add(Integer.parseInt(tokens[i]));
				    }
				    else {
					JOptionPane.showMessageDialog(null,"Merci de ne pas faire apparaitre deux fois le meme bloc dans la configuration finale, veuillez modifier le fichier texte et réessayer","Erreur",JOptionPane.ERROR_MESSAGE);
					System.out.println("Merci de ne pas faire apparaitre deux fois le meme bloc dans la configuration finale, veuillez modifier le fichier texte et réessayer");
					System.exit(1);
				    }
				}
			    }
			    catch(Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null,"Votre fichier texte ne contient pas que des entiers, ou n'utilise pas les bons séparateurs : fermeture du programme","Erreur Fatale",JOptionPane.ERROR_MESSAGE);
				System.out.println("Votre fichier texte ne contient pas que des entiers, ou n'utilise pas les bons séparateurs : fermeture du programme");
				System.exit(1);
			    }
			}
		    }
		}
		j++;
	    }
	    while (j<2*this.hauteur+1);
	    /*
	     * On ferme le Buffer
	     */
	    aLire.close();
	    /*
	     * On verifie que tous les entiers de la configuration initiale sont présents dans la configuration finale, si ce n'est pas le cas, on affiche une erreur et on ferme le programme
	     */
	    if(!listFin.containsAll(list)) {
		JOptionPane.showMessageDialog(null,"Veuillez s'il vous plait vous assurer que tous les blocs de la configuration initiale sont bien présents dans la configuration finale","Erreur",JOptionPane.WARNING_MESSAGE);
		System.exit(1);
	    }
	    /*
	     * On verifie que tous les entiers de la configuration initiale sont présents dans la configuration finale, si ce n'est pas le cas, on affiche une erreur et on ferme le programme
	     */
	    if(!list.containsAll(listFin)) {
		JOptionPane.showMessageDialog(null,"Veuillez s'il vous plait vous assurer que tous les blocs de la configuration finale sont bien présents dans la configuration initiale","Erreur",JOptionPane.WARNING_MESSAGE);
		System.exit(1);
	    }
	    /*
	     * On vérifie qu'aucun Bloc des deux configurations ne repose sur rien
	     */
	    this.verifierTableau();
	}
	catch(IOException e) {
	    JOptionPane.showMessageDialog(null,"Fichier Texte introuvable","Erreur",JOptionPane.ERROR_MESSAGE);
	    System.out.println("Erreur de lecture du fichier texte");
	    this.init();
	}
    }

    /**
     * Méthode vérifiant qu'aucun Bloc ne repose sur rien dans les deux configurations modélisés par des tableaux d'entier, si ce n'est pas le cas, affiche une erreur et ferme le programme
     */
    private void verifierTableau() {
	this.verifierTab();
	this.verifierTabFin();
    }

    /**
     * Méthode vérifiant qu'aucun Bloc ne repose sur rien dans la configuration initiale modélisée par le tableaux d'entier tab, si ce n'est pas le cas, affiche une erreur et ferme le programme
     */
    private void verifierTab() {
	boolean erreur=false;
	int i=0;
	int j=0;
	/*
	 * On parcourt le tableau
	 */
	while(i<this.getTab().length && !erreur) {
	    j=0; 
	    while(j<this.getTab()[0].length && !erreur) {
		/*
		 * Si il y a un Bloc en [i][j]
		 */
		if(this.getTab()[i][j]!=0) {
		    /*
		     * On verifie qu'il repose sur un autre Bloc ou qu'il est sur le sol, sinon on renvoie une erreur et on ferme le programme
		     */
		    if(i!=0 && this.getTab()[i-1][j]==0) {
			JOptionPane.showMessageDialog(null,"Les blocs ne peuvent pas rester en suspension, ils doivent impérativement être posés sur d'autres blocs ou sur le sol","Erreur",JOptionPane.WARNING_MESSAGE);
			System.exit(1);
		    }
		}
		j++;
	    }
	    i++;
	}
    }

    /**
     * Méthode vérifiant qu'aucun Bloc ne repose sur rien dans la configuration finale modélisée par le tableaux d'entier tabFin, si ce n'est pas le cas, affiche une erreur et ferme le programme
     */
    private void verifierTabFin() {
	boolean erreur=false;
	int i=0;
	int j=0;
	/*
	 * On parcourt le tableau
	 */
	while(i<this.getTabFin().length && !erreur) {
	    j=0;  
	    while(j<this.getTabFin()[0].length && !erreur) {
		/*
		 * Si il y a un Bloc en [i][j]
		 */
		if(this.getTabFin()[i][j]!=0) {
		    /*
		     * On verifie qu'il repose sur un autre Bloc ou qu'il est sur le sol, sinon on renvoie une erreur et on ferme le programme
		     */
		    if(!(i==0 || this.getTabFin()[i-1][j]!=0)) {
			JOptionPane.showMessageDialog(null,"Les blocs ne peuvent pas rester en suspension, ils doivent impérativement être posés sur d'autres blocs ou sur le sol","Erreur",JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		    }
		}
		j++;
	    }
	    i++;
	}
    }
}
