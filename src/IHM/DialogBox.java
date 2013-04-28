package IHM;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.*;


/**
 * Classe implémentant IDialogBox
 * 
 * @author Lucas VANRYB
 *
 */
public class DialogBox extends JFrame implements IDialogBox{

    private final static long serialVersionUID = 1L;
    
    /*
     * ATTRIBUTS
     */
    
    /**
     * booleen qui vaut true si l'utilisateurs n'a pas encore saisi les données nécessaire, false sinon
     */
    boolean enCours;
    /**
     * JPanel contenant la partie concernant la saisie de la largeur de la JFrame this
     */
    JPanel panLarg;
    /**
     * JPanel contenant la partie concernant la saisie de la hauteur de la JFrame this
     */
    JPanel panHaut;
    /**
     * JPanel contenant le bouton de la JFrame this
     */
    JPanel panBouton;
    /**
     * JPanel contenant l'ensemble de ce qui doit être dans la JFrame
     */
    JPanel pan;

    /**
     * Champ de texte dans lequel l'utilisateur saisira la hauteur
     */
    JFormattedTextField hauteur;
    /**
     * Champ de texte dans lequel l'utilisateur saisira la largeur
     */
    JFormattedTextField largeur;

    /**
     * Entier contenant la hauteur saisie par l'utilisateur
     */
    int hauteurFin;
    /**
     * Entier contenant la largeur saisie par l'utilisateur
     */
    int largeurFin;

    public DialogBox(){
	
	this.enCours=true;
	JPANVIDE.setBackground(Color.white);
	/*
	 * On règle les paramètres de la fenetre
	 */
	this.setTitle("Choix des dimensions :");
	this.setSize(320, 160);
	this.setLocationRelativeTo(null);
	this.setResizable(false);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	/*
	 * On crée le panel contenant tout et on appelle la méthode qui le remplira
	 */
	this.pan=new JPanel();
	this.pan.setPreferredSize(new Dimension (200,130));
	this.pan.setBackground(Color.white);
	this.pan.setLayout(new GridLayout(3,1));
	initPanel();
    }

    /*
     * ACCESSEURS
     */
    
    /**
     * Renvoie la hauteur de l'aire de jeu saisie par l'utilisateur
     * @return la hauteur de l'aire de jeu saisie par l'utilisateur
     */
    public int getHauteur() {
	try {
	    return Integer.parseInt(this.hauteur.getText());
	}
	catch(Exception e) {
	    return 0;
	}
    }

    /**
     * Renvoie la largeur de l'aire de jeu saisie par l'utilisateur
     * @return la largeur de l'aire de jeu saisie par l'utilisateur
     */
    public int getLargeur() {
	try {
	    return Integer.parseInt(this.largeur.getText());
	}
	catch (Exception e) {
	    return 0;
	}
    }

    /**
     * Méthode settant la hauteur de l'aire de jeu au paramètre transmis
     * @param i la hauteur de l'aire de jeu
     */
    private void setHauteur(int i) {
	this.hauteurFin=i;
    }

    /**
     * Méthode settant la largeur de l'aire de jeu au paramètre transmis
     * @param i la largeur de l'aire de jeu
     */
    private void setLargeur(int i) {
	this.largeurFin=i;
    }

    /**
     * Renvoie true si l'utilisateur n'a pas encore saisi les données, false sinon
     * @return true si l'utilisateur n'a pas encore saisi les données, false sinon
     */
    public boolean getEnCours() {
	return this.enCours;
    }
    
    /*
     * SERVICES
     */
    
    /**
     * Méthode initialisant tous les panels et ajoutant tous les autres panel dans le panel qui contiendra tout : pan
     */
    private void initPanel() {
	/*
	 * Init et saisie des preferences pour panLarg
	 */
	this.panLarg=new JPanel();
	this.panLarg.setBackground(Color.white);
	this.panLarg.setLayout(new GridLayout(2,1));
	this.panLarg.setPreferredSize(new Dimension(200,55));

	/*
	 * Init et saisie des preferences pour panHaut
	 */
	this.panHaut=new JPanel();
	this.panHaut.setBackground(Color.white);
	this.panHaut.setLayout(new GridLayout(2,1));
	this.panLarg.setPreferredSize(new Dimension(200,55));
	
	/*
	 * Ajout des masks restrictifs pour les zones de texte
	 */
	try {
	    Format mask= NumberFormat.getIntegerInstance(Locale.FRENCH);
	    this.hauteur=new JFormattedTextField(mask);
	    this.largeur=new JFormattedTextField(mask);
	}
	catch(Exception e) {
	    e.printStackTrace();
	}
	/*
	 * Reglage des tailles des zones de texte
	 */
	this.hauteur.setPreferredSize(new Dimension(160,40));
	this.largeur.setPreferredSize(new Dimension(160,40));
	
	/*
	 * Création des labels
	 */
	JLabel labHauteur=new JLabel ("Hauteur :");
	labHauteur.setPreferredSize(new Dimension(160,15));
	JLabel labLargeur=new JLabel("Largeur :");
	labLargeur.setPreferredSize(new Dimension(160,15));

	/*
	 * Ajout du label et de la zone de texte dans panLarg
	 */
	this.panLarg.add(labLargeur);
	this.panLarg.add(largeur);

	/*
	 * Ajout du label et de la zone de texte dans panHaut
	 */
	this.panHaut.add(labHauteur);
	this.panHaut.add(hauteur);

	/*
	 * Init et choix de préferences de panBouton
	 */
	this.panBouton=new JPanel();
	this.panBouton.setBackground(Color.white);
	this.panBouton.setLayout(new GridLayout(1,2));
	this.panBouton.setPreferredSize(new Dimension(200,20));

	/*
	 * Ajout du bouton ok
	 */
	JButton boutonOk = new JButton("OK");
	boutonOk.addActionListener(new myListener());

	/*
	 * Ajout du bouton dans panBouton
	 */
	this.panBouton.add(JPANVIDE);
	this.panBouton.add(boutonOk);

	/*
	 * Ajout des paneaux secondaires dans le panneau principal
	 */
	this.pan.add(panLarg);
	this.pan.add(panHaut);
	this.pan.add(panBouton);
	
	/*
	 * Ajout du paneau principal dans la fenetre et affichage
	 */
	this.setContentPane(pan);
	this.setVisible(true);
    }
    

    /**
     * Classe servant de listener pour le bouton OK
     * @author Lucas VANRYB
     *
     */
    class myListener implements ActionListener {
	/**
	 * Méthode appelée lorsque l'utilisateur clique sur le bouton OK. Règle la hauteur et la largeur de l'aire de jeu en fonction des données qu'a saisi l'utilisateur
	 */
	public void actionPerformed(ActionEvent event) {
	    if(getHauteur()!=0 && getLargeur()!=0) {
		if(getHauteur() < 100 && getLargeur()<100) {
		    setHauteur(getHauteur());
		    setLargeur(getLargeur());
		    setVisible(false);
		    enCours=false;
		}
		else {
		    JOptionPane.showMessageDialog(null,"Merci de saisir un entiers valides, c'est à dire compris entre 1 et 100","Erreur",JOptionPane.WARNING_MESSAGE);
		}
	    }
	    else {
		JOptionPane.showMessageDialog(null,"Merci de saisir des entiers valides, c'est à dire compris entre 1 et 100","Erreur",JOptionPane.WARNING_MESSAGE);
	    }
	}
    }

    /**
     * Méthode servant à tester la classe
     * @param args
     */
    public static void main(String[] args) {
	IDialogBox d=new DialogBox();
	while(d.getEnCours()) {
	}
	System.out.println(d.getHauteur());

    }

}


