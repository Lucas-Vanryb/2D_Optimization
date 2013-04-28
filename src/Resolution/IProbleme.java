package Resolution;

/**
 * Interface modélisant un problème par une configuration et une IHM
 * 
 * @author Lucas VANRYB
 *
 */
public interface IProbleme {

    /*
     * ACCESSEUR
     */
    
    /**
     * Retourne la Configuration associée au Probleme
     * @return la Configuration associée au Probleme
     */
    public abstract IConfiguration getConfig();

    /*
     * SERVICE
     */
    
    /**
     * Méthode résolvant le problème
     */
    public abstract void resoudre();

}