package EnregistrerTexte;

import java.io.*;

/**
 * Classe implémentant l'interface iEnregistrer
 * @author Lucas VANRYB
 */
public class Enregistrer implements IEnregistrer {

    /*
     * ATTRIBUTS
     */
    
    /**
     * Attribut permettant de sauvegarder la méhode d'écriture de l'instance d'Enregistrer
     */
    PrintWriter ecrire;

    /*
     * CONSTRUCTEUR
     */
    
    /**
     * Construit un Enregistrer à partir d'un nom de fichier qui sera le nom du fichier texte dans le répertoire du programme
     * @param nomFile le nom souhaité pour le fichier texte dans le répertoire du programme
     */
    public Enregistrer (String nomFile) {

	try {
	    /*
	     * On supprime le fichier de nom identique dans le repertoire du programme si il existe, sinon on ne fait rien
	     */
	    File fichier = new File(nomFile);
	    fichier.delete();
	}
	catch(Exception e) {
	}
	try { 
	    /*
	     * On crée le fichier texte dans lequel on va écrire à partir du nom transmis en paramètre
	     */
	    File nouveauFichier = new File(nomFile);
	    nouveauFichier.createNewFile();
	    /*
	     * On initialise la méthode qui nous permettra d'écrire dans ce fichier
	     */
	    this.ecrire = new PrintWriter(new BufferedWriter(new FileWriter(nomFile)));
	}
	catch(IOException e) {
	}
    }

    /*
     * SERVICES
     */
    
    /**
     * Méthode permettant d'écrire la String passée en paramètre dans le fichier texte lié à l'instance d'objet
     * @param aEcrire la String à écrire dans le fichier texte
     */
    public void EcrireTexte (String aEcrire){
	this.ecrire.println(aEcrire);  
	this.ecrire.flush();
    }

    /**
     * Méthode qui ferme le lien entre le fichier texte et l'instance d'objet. Cette méthode est invoquée lorsque l'écriture est terminée.
     */
    public void finaliser() {
	ecrire.close();
    }
}
