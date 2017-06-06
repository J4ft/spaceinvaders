package fr.unilim.iut.moteurjeu;

import javax.swing.JFrame;


/**
 * cree une interface graphique avec son controleur et son afficheur
 * @author Graou
 *
 */
public class InterfaceGraphique  {

	
	private JFrame f;
	/**
	 * le Panel lie a la JFrame
	 */
	private PanelDessin panel;
	
	/**
	 * le controleur lie a la JFrame
	 */
	private Controleur controleur;
	
	/**
	 * la construction de l'interface grpahique
	 * - construit la JFrame
	 * - construit les Attributs
	 * 
	 * @param afficheurUtil l'afficheur a utiliser dans le moteur
	 * 
	 */
	public InterfaceGraphique(DessinJeu afficheurUtil,int x,int y)
	{
		//creation JFrame
		this.f =new JFrame();
		this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// creation panel
		this.panel=new PanelDessin(x, y,afficheurUtil);
		this.f.setContentPane(this.panel);
		
		//ajout du controleur
		Controleur controlleurGraph=new Controleur();
		this.controleur=controlleurGraph;
		this.panel.addKeyListener(controlleurGraph);	
		
		//recuperation du focus
		this.f.pack();
		this.f.setVisible(true);
		this.f.getContentPane().setFocusable(true);
		this.f.getContentPane().requestFocus();
	}
	
	
	/**
	 * retourne le controleur de l'affichage construit
	 * @return
	 */
	public Controleur getControleur() {
		return controleur;
	}

	/**
	 * demande la mise a jour du dessin
	 */
	public void dessiner() {
		this.panel.dessinerJeu();	
	}
	
	public void fermerFenetre() {
		this.f.setVisible(false);
		this.f.dispose();
	}
	
}
