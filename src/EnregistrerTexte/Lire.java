package EnregistrerTexte;

import java.io.BufferedReader;
import java.io.FileReader;

import IHM.IIHM;

/**
 * Classe possédant une méthode statique permettant de lire dans le fichier dans lequel Enregistrer à écrit
 * @author Lucas VANRYB
 */
public class Lire {

    /**
     * Retourne sous forme de String HTML le contenu du fichier texte dans lequel le programme a stocké les déplacements 
     * @return le contenu du fichier texte dans lequel le programme a stocké les déplacements sous forme de String HTML
     */
    public static String lireTexteEnreg() {
	String res=new String("<html>");
	String temp;
	try {
	    BufferedReader aLire= new BufferedReader(new FileReader(IIHM.NOMFICHIER)); 
	    do {
		temp= aLire.readLine();
		if(temp!=null) {
		    res+="<br>";
		    res+="  "+temp;
		}
	    }
	    while (temp!=null); 
	    res+="</html>";
	}
	catch(Exception e) {
	    System.exit(1);
	}
	return res;
    }
}
