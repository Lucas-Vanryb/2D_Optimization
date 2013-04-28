package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.*;


/**
 * Classe implémentant ISaisie
 * @author Lucas VANRYB
 *
 */
public class Saisie extends JFrame implements ISaisie{

    private final static long serialVersionUID = 1L;

    /**
     * le paneau contenant les cases dans lesquelles la configuration initiale sera saisie
     */
    JPanel panGauche;
    /**
     * le paneau contenant les cases dans lesquelles la configuration finale sera saisie
     */
    JPanel panDroite;
    /**
     * le paneau contenant le bouton ok
     */
    JPanel panBas;
    /**
     * Le panneau global contenant panGauche et panDroite
     */
    JPanel contains;
    /**
     * Le panneau contenant toute la fenêtre
     */
    JPanel total;
    /**
     * Le panneau contenant les labels
     */
    JPanel panHaut;
    /**
     * Panneau vide
     */
    final JPanel JPANVIDE;

    /**
     * Tableau de champs de textes qui contiendra la configuration initiale
     */
    JFormattedTextField[] casesGauches;
    /**
     * Tableau de champs de textes qui contiendra la configuration finale
     */
    JFormattedTextField[] casesDroites;
    /**
     * Le bouton OK
     */
    JButton boutonOk;

    /**
     * Le tableau qui contiendra la configuration initiale
     */
    int[][] tab;
    /**
     * Le tableau qui contiendra la configuration finale
     */
    int[][] tabFin;

    /**
     * La hauteur de l'aire de jeu
     */
    int hauteur;
    /**
     * La largeur de l'aire de jeu
     */
    int largeur;
    /**
     * Vaut true si l'utilisateur n'a pas encore saisi les données, false sinon
     */
    boolean enCours;

    /**
     * Constante qui vaut la hauteur de la fenetre
     */
    public final int HAUTEUR_FENETRE;
    /**
     * Constante qui vaut la largeur de la fenetre
     */
    public final int LARGEUR_FENETRE;
    /**
     * Construit une Saisie à partir du nombre de ligne h et du nombre de colonnes l
     * @param h le nombre de lignes
     * @param l le nombre de cases
     */
    public Saisie(int h, int l) {
	/*
	 * On init les differents attributs
	 */
	JPANVIDE=new JPanel();
	JPANVIDE.setBackground(Color.gray);
	this.enCours=true;
	this.hauteur=h;
	this.largeur=l;
	HAUTEUR_FENETRE=h*2*TAILLE_CASE+40;
	LARGEUR_FENETRE=l*4*TAILLE_CASE+10;
	this.casesGauches=new JFormattedTextField[this.getHauteur()*this.getLargeur()];
	this.casesDroites=new JFormattedTextField[this.getHauteur()*this.getLargeur()];
	this.tab=new int[h][l];
	this.tabFin=new int[h][l];

	/*
	 * On règle les paramètres de la JFrame this
	 */
	this.setTitle("Saisie des données :");
	this.setSize(LARGEUR_FENETRE, HAUTEUR_FENETRE);
	this.setLocationRelativeTo(null);
	this.setResizable(false);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	/*
	 * On initialise le panneau de gauche
	 */
	this.panGauche=new JPanel();
	initPanel(this.panGauche,HAUTEUR_FENETRE-40,LARGEUR_FENETRE/2,h,l);
	this.panGauche.setBorder(BorderFactory.createMatteBorder(0,0,0,3,Color.black));

	/*
	 * On initialise le panneau de droite
	 */
	this.panDroite=new JPanel();
	initPanel(this.panDroite,HAUTEUR_FENETRE-40,LARGEUR_FENETRE/2,h,l);

	/*
	 * On initialise le panneau du bas
	 */
	this.panBas=new JPanel();
	initPanel(this.panBas,20,LARGEUR_FENETRE,1,2);

	/*
	 * On initialise le panneau qui contiendra le panneau de gauche et le panneau de droite
	 */
	this.contains=new JPanel();
	initPanel(this.contains,HAUTEUR_FENETRE-40,LARGEUR_FENETRE,1,2);

	/*
	 * On initialise le panneau du haut
	 */
	this.panHaut=new JPanel();
	initPanel(this.panHaut,20,LARGEUR_FENETRE,1,2);

	/*
	 * On initialise le panneau qui contiendra tous les autres panneaux
	 */
	this.total=new JPanel();
	this.total.setPreferredSize(new Dimension (largeur,hauteur));
	this.total.setBackground(Color.gray);
	this.total.setLayout(new BoxLayout(total,BoxLayout.Y_AXIS));

	/*
	 * On initialise le bouton
	 */
	this.boutonOk=new JButton("OK");

	init();
    }

    /*
     * ACCESSEURS
     */
    
    /**
     * Méthode qui initialise un panneau en gridLayout avec les paramètres voulus
     * @param pan le panneau à initialiser
     * @param hauteur la hauteur qu'on veut donner à ce panneau
     * @param largeur la largeur qu'on veut donner à ce panneau
     * @param gridNbLig le nombre de lignes que doit contenir le GridLayout du panneau pan
     * @param gridNbCol le nombre de colonnes que doit contenir le GridLayout du panneau pan
     */
    private void initPanel(JPanel pan, int hauteur, int largeur, int gridNbLig, int gridNbCol) {
	pan.setPreferredSize(new Dimension (largeur,hauteur));
	pan.setBackground(Color.gray);
	pan.setLayout(new GridLayout(gridNbLig,gridNbCol));
    }

    /**
     * Renvoie le nombre de lignes de l'aire de jeu
     * @return le nombre de lignes de l'aire de jeu
     */
    private int getHauteur() {
	return this.hauteur;
    }

    /**
     * Renvoie le nombre de colonnes de l'aire de jeu
     * @return le nombre de colonnes de l'aire de jeu
     */
    private int getLargeur() {
	return this.largeur;
    }

    /**
     * Renvoie le nommbre de champs de texte présents 
     * @return le nommbre de champs de texte présents 
     */
    private int getNbCases() {
	return this.casesGauches.length;
    }

    /**
     * Renvoie le panneau de droite
     * @return le panneau de droite
     */
    private JPanel getPanDroite() {
	return this.panDroite;
    }

    /**
     * Renvoie le panneau de gauche
     * @return le panneau de gauche
     */
    private JPanel getPanGauche() {
	return this.panGauche;
    }

    /**
     * Renvoie le panneau du bas
     * @return le panneau du bas
     */
    private JPanel getPanBas() {
	return this.panBas;
    }

    /**
     * Renvoie le panneau contenant le panneau de gauche et le panneau de droite
     * @return le panneau contenant le panneau de gauche et le panneau de droite
     */
    private JPanel getPan(){
	return this.contains;
    }

    /**
     * Renvoie le panneau contenant tout
     * @return le panneau contenant tout
     */
    private JPanel getTotal() {
	return this.total;
    }

    /**
     * Renvoie le panneau du haut
     * @return le panneau du haut
     */
    private JPanel getPanHaut() {
	return this.panHaut;
    }

    /**
     * Renvoie le bouton ok
     * @return le bouton ok
     */
    private JButton getBouton() {
	return this.boutonOk;
    }

    /**
     * Renvoie le tableau des champs de textes situés à gauche
     * @return le tableau des champs de textes situés à gauche
     */
    private JFormattedTextField[] getCasesGauches() {
	return this.casesGauches;
    }

    /**
     * Renvoie le tableau des champs de textes situés à droite
     * @return le tableau des champs de textes situés à droite
     */
    private JFormattedTextField[] getCasesDroites() {
	return this.casesDroites;
    }

    /**
     * Renvoie le tableau modélisant la configuration initiale
     * @return le tableau modélisant la configuration initiale
     */
    public int[][] getTab() {
	return this.tab;
    }

    /**
     * Renvoie le tableau modélisant la configuration finale
     * @return le tableau modélisant la configuration finale
     */
    public int[][] getTabFin() {
	return this.tabFin;
    }

    /**
     * Set la case [i][j] du tableau modélisant la configuration initiale à c
     * @param i l'index de la ligne à modifier
     * @param j l'index de la colonne à modifier
     * @param c la valeur à inserer
     */
    private void setCaseTab(int i,int j, int c) {
	this.tab[i][j]=c;
    }

    /**
     * Set la case [i][j] du tableau modélisant la configuration finale à c
     * @param i l'index de la ligne à modifier
     * @param j l'index de la colonne à modifier
     * @param c la valeur à inserer
     */
    private void setCaseTabFin(int i, int j, int c) {
	this.tabFin[i][j]=c;
    }

    /*
     * SERVICES
     */
    
    /**
     * Méthode finalisant la saisie, cache la fenetre et met enCours à false
     */
    private void finaliser() {
	this.setVisible(false);
	this.enCours=false;
    }

    /**
     * Methode permettant de verifier qu'il n'y a pas de bloc "volant" dans les configuration, affiche une erreur et quitte le programme si il en trouve une, ne fait rien sinon
     * @return true si les configurations sont correctes et que tous les blocs ont des positions valides, false sinon
     */
    private boolean verifierTableau() {
	boolean erreur=false;
	erreur=(this.verifierTab() || this.verifierTabFin());
	return erreur;
    }

    /**
     * Methode permettant de verifier qu'il n'y a pas de bloc "volant" dans la configuration initiale , affiche une erreur et quitte le programme si il en trouve une, ne fait rien sinon
     * @return true si les configurations sont correctes et que tous les blocs ont des positions valides, false sinon
     */
    private boolean verifierTab() {
	boolean erreur=false;
	int i=0;
	int j=0;
	while(i<this.getTab().length && !erreur) {
	    j=0; 
	    while(j<this.getTab()[0].length && !erreur) {
		if(this.getTab()[i][j]!=0) {
		    if(i!=0 && this.getTab()[i-1][j]==0) {
			erreur=true;
			JOptionPane.showMessageDialog(null,"Les blocs ne peuvent pas rester en suspension, ils doivent impérativement être posés sur d'autres blocs ou sur le sol","Erreur",JOptionPane.WARNING_MESSAGE);
		    }
		}
		j++;
	    }
	    i++;
	}
	return erreur;
    }

    /**
     * Methode permettant de verifier qu'il n'y a pas de bloc "volant" dans la configuration finale, affiche une erreur et quitte le programme si il en trouve une, ne fait rien sinon
     * @return true si les configurations sont correctes et que tous les blocs ont des positions valides, false sinon
     */
    private boolean verifierTabFin() {
	boolean erreur=false;
	int i=0;
	int j=0;
	while(i<this.getTabFin().length && !erreur) {
	    j=0;  
	    while(j<this.getTabFin()[0].length && !erreur) {
		if(this.getTabFin()[i][j]!=0) {
		    if(!(i==0 || this.getTabFin()[i-1][j]!=0)) {
			erreur=true;
			JOptionPane.showMessageDialog(null,"Les blocs ne peuvent pas rester en suspension, ils doivent impérativement être posés sur d'autres blocs ou sur le sol","Erreur",JOptionPane.WARNING_MESSAGE);
		    }
		}
		j++;
	    }
	    i++;
	}
	return erreur;
    }

    /**
     * Listener permettant, couplé au mask, d'interdire les lettres et les symboles dans les cases du tableau
     * @param t le champ à surveiller
     */
    private void ajouterKeyListener (JFormattedTextField t) {
	t.addKeyListener(new KeyListener(){
	    public void keyTyped(KeyEvent e){
		JFormattedTextField j=(JFormattedTextField)(e.getSource());
		if(j.getText()==null) {
		    j.setValue(null);
		}
		if(j.getText().length()<3) {
		    try {
			Integer.parseInt(j.getText());
		    }
		    catch(Exception ex) {
			j.setValue(null);
		    }
		}
	    }
	    public void keyReleased(KeyEvent e){
	    }
	    public void keyPressed(KeyEvent e){
	    }
	}

	);
    }


    /**
     * Méthode qui finit les initialisations/réglages et affiche l'interface
     */
    private void init() {
	try {
	    Format mask= NumberFormat.getIntegerInstance(Locale.FRENCH);
	    /*
	     * On parcourt le tableau des champs de texte
	     */
	    for (int i=0;i<this.getNbCases();i++) {
		/*
		 * On initialise les champs avec un mask
		 */
		this.getCasesGauches()[i]=new JFormattedTextField(mask);
		this.getCasesDroites()[i]=new JFormattedTextField(mask);

		/*
		 * On lie le KeyListener à chacun des champs
		 */
		this.ajouterKeyListener(this.getCasesGauches()[i]);
		this.ajouterKeyListener(this.getCasesDroites()[i]);

		/*
		 * On ajoute les champs au panneau correspondant
		 */
		this.getPanGauche().add(this.getCasesGauches()[i]);
		this.getPanDroite().add(this.getCasesDroites()[i]);
	    }
	}
	catch(Exception e) {
	}

	/*
	 * On crée et on initialise le label de gauche
	 */
	JLabel labGauche=new JLabel ("    Configuration initiale :");
	labGauche.setPreferredSize(new Dimension(LARGEUR_FENETRE,20));

	/*
	 * On crée et on initialise le label de droite
	 */
	JLabel labDroite=new JLabel ("    Configuration finale :");
	labDroite.setPreferredSize(new Dimension(LARGEUR_FENETRE,20));

	/*
	 * On effectue des réglages sur le couton et on le lie à son listener
	 */
	this.getBouton().setPreferredSize(new Dimension(80,20));
	this.getBouton().addActionListener(new ActionListener(){
	    public void actionPerformed(ActionEvent event) {
		listener();
	    }
	});

	/*
	 * On crée le pannel contenant le bouton
	 */
	JPanel panBouton=new JPanel();
	panBouton.setPreferredSize(new Dimension(80,20));
	panBouton.add(this.getBouton());
	panBouton.setBackground(Color.gray);

	/*
	 * On ajoute les différents composants aux différents pannels
	 */
	this.getPanHaut().add(labGauche);
	this.getPanHaut().add(labDroite);

	this.getPanBas().add(JPANVIDE);
	this.getPanBas().add(panBouton);

	this.getPan().add(this.getPanGauche());
	this.getPan().add(this.getPanDroite());

	this.getTotal().add(this.getPanHaut());
	this.getTotal().add(this.getPan());
	this.getTotal().add(this.getPanBas());

	/*
	 * On ajoute le pannel contenant tout à la fenetre Saisie, et on l'affiche
	 */
	this.setContentPane(this.getTotal());
	this.setVisible(true);

	JOptionPane.showMessageDialog(null,"Veuillir saisir les informations : à gauche la configuration initiale, à droite la configuration finale","Saisie",JOptionPane.INFORMATION_MESSAGE);

    }
    /**
     * Listener du bouton Ok, il lit tous les champs, vérifie que les informations forment une configuration correcte et remplit les tableaux attributs
     */
    private void listener() {
	ArrayList<Integer> list=new ArrayList<Integer>();
	ArrayList<Integer> listFin=new ArrayList<Integer>();
	boolean erreur = false;
	int i=0;
	int k=getHauteur()-1;
	int j=0;
	/*
	 * On parcourt le tableau de champ de texte et on remplit les tableaux
	 */
	while(k>=0 && !erreur) {
	    j=getHauteur()-1-k;
	    i=0;
	    while(i<getLargeur() && !erreur) {
		/*
		 * On regarde si le champ est vide, dans ce cas, on ajoute 0 au tableau, sinon on ajoute la valeur du champ au tableau et on vérifie qu'elle est correcte
		 */
		try {
		    /*
		     * On vérifie que tous les entiers sont supérieurs à 0
		     */
		    if(Integer.parseInt(getCasesGauches()[i+k*getLargeur()].getText())>0) {
			/*
			 * On ajoute dans le tableau la donnée du champ de texte correspondant
			 */
			setCaseTab(j,i,Integer.parseInt(getCasesGauches()[i+k*getLargeur()].getText()));
			/*
			 * On verifie que le meme entier n'est pas deux fois dans la meme configuration
			 */
			if(!list.contains(Integer.parseInt(getCasesGauches()[i+k*getLargeur()].getText()))) {
			    list.add(Integer.parseInt(getCasesGauches()[i+k*getLargeur()].getText()));
			}
			else {
			    erreur=true;
			    JOptionPane.showMessageDialog(null,"Merci de ne pas faire apparaitre deux fois le meme bloc dans la configuration initiale","Erreur",JOptionPane.WARNING_MESSAGE);
			}
		    }
		    else {
			erreur=true;
			JOptionPane.showMessageDialog(null,"Merci de ne pas mettre de 0, ni d'entiers négatifs, laissez en blanc pour une case inoccupée","Erreur",JOptionPane.WARNING_MESSAGE);
		    }

		}
		catch(Exception e) {
		    /*
		     * Le champ est vide, on ajoute 0 au tableau
		     */
		    setCaseTab(j,i,0);
		}
		/*
		 * On regarde si le champ est vide, dans ce cas, on ajoute 0 au tableau, sinon on ajoute la valeur du champ au tableau et on vérifie qu'elle est correcte
		 */
		try {
		    /*
		     * On vérifie que tous les entiers sont supérieurs à 0
		     */
		    if(Integer.parseInt(getCasesDroites()[i+k*getLargeur()].getText())>0) {
			/*
			 * On ajoute dans le tableau la donnée du champ de texte correspondant
			 */
			setCaseTabFin(j,i,Integer.parseInt(getCasesDroites()[i+k*getLargeur()].getText()));
			/*
			 * On verifie que le meme entier n'est pas deux fois dans la meme configuration
			 */
			if(!listFin.contains(Integer.parseInt(getCasesDroites()[i+k*getLargeur()].getText()))) {
			    listFin.add(Integer.parseInt(getCasesDroites()[i+k*getLargeur()].getText()));
			}
			else {
			    erreur=true;
			    JOptionPane.showMessageDialog(null,"Merci de ne pas faire apparaitre deux fois le meme bloc dans la configuration finale","Erreur",JOptionPane.WARNING_MESSAGE);
			}
		    }
		    else {
			erreur=true;
			JOptionPane.showMessageDialog(null,"Merci de ne pas mettre de 0, ni d'entiers négatifs, laissez en blanc pour une case inoccupée","Erreur",JOptionPane.WARNING_MESSAGE);
		    }
		}
		catch(Exception e) {
		    /*
		     * La case est vide, on ajoute 0 au tableau
		     */
		    setCaseTabFin(j,i,0);
		}
		i++;
	    }
	    k--;
	}
	erreur= erreur || verifierTableau();
	/*
	 * On verifie que tous les blocs de la configuration finale sont présents dans la configuration initiale et vice versa
	 */
	if(!listFin.containsAll(list) && !erreur) {
	    erreur=true;
	    JOptionPane.showMessageDialog(null,"Veuillez s'il vous plait vous assurer que tous les blocs de la configuration initiale sont bien présents dans la configuration finale","Erreur",JOptionPane.WARNING_MESSAGE);
	}
	if(!list.containsAll(listFin) && !erreur) {
	    erreur=true;
	    JOptionPane.showMessageDialog(null,"Veuillez s'il vous plait vous assurer que tous les blocs de la configuration finale sont bien présents dans la configuration initiale","Erreur",JOptionPane.WARNING_MESSAGE);
	}
	if(!erreur) {
	    finaliser();
	}
    }

    /**
     * Main de test
     * @param args
     */
    public static void main(String[] args) {
	new Saisie(17,17);
    }
}
