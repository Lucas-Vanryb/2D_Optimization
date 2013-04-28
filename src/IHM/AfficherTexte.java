package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 * Classe permettant d'afficher le texte lu dans le fichier dans lequel les déplacements ont été enregistrés.
 * La String passée en paramètre du contructeur doit être au format HTML
 * Le constructeur affiche le contenu de la String passée en caractère dans une JFrame
 * 
 * @author Lucas VANRYB
 */
public class AfficherTexte extends JFrame {

    private final static long serialVersionUID = 1L;
    
    /**
     * Constructeur prenant en paramètre une String au format HTML, contenant l'historique des déplacements
     * @param s une String au format HTML, contenant l'historique des déplacements
     */
    public AfficherTexte(String s) {
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setTitle("Déplacements effectués :");
	this.setBackground(Color.white);
	this.setSize(new Dimension(300,600));
	this.setLocationRelativeTo(null);
	this.setResizable(true);
	this.setLayout(new BorderLayout());
	
	JLabel lab= new JLabel(s);
	JScrollPane pan=new JScrollPane(lab);
	
	this.add(pan,BorderLayout.CENTER);
	this.setVisible(true);
    }
    
}
