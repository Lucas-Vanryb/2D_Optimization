package EnregistrerTexte;
/**
 * Interface modélisant un lien entre un fichier texte et le code Java
 * @author Lucas VANRYB
 *
 */
public interface IEnregistrer {

    /*
     * SERVICES
     */
    
    /**
     * Méthode permettant d'écrire la String passée en paramètre dans le fichier texte lié à l'instance d'objet
     * @param aEcrire la String à écrire dans le fichier texte
     */
    public abstract void EcrireTexte(String aEcrire);

    /**
     * Méthode qui ferme le lien entre le fichier texte et l'instance d'objet. Cette méthode est invoquée lorsque l'écriture est terminée.
     */
    public abstract void finaliser();

}