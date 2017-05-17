package fr.unilim.iut.spaceinvaders;

import fr.unilim.iut.moteurjeu.Commande;
import fr.unilim.iut.moteurjeu.Jeu;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;

public class SpaceInvaders implements Jeu {

	int longueur;
	int hauteur;
	Vaisseau vaisseau;

	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
	}

	public void initialiser() {
		positionnerUnNouveauVaisseau(Constante.VAISSEAU, new Position(Constante.VAISSEAU.longueur() / 2, hauteur -1));
	}

	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append("\n");
		}
		return espaceDeJeu.toString();
	}

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (aUnVaisseauQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_VAISSEAU;
		else
			marque = Constante.MARQUE_VIDE;
		return marque;
	}

	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
	}

	private boolean aUnVaisseau() {
		return vaisseau != null;
	}

	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position) {
		int x = position.abscisse();
		int y = position.ordonnee();
		
		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

		int longueurVaisseau = dimension.longueur();
		int hauteurVaisseau = dimension.hauteur();
		
		if (!estDansEspaceJeu(x + longueurVaisseau - 1, y))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurVaisseau + 1))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

		vaisseau = new Vaisseau(longueurVaisseau, hauteurVaisseau);
		vaisseau.positionner(x, y);
	}

	private boolean estDansEspaceJeu(int x, int y) {
		return x < longueur && x >= 0 && y < hauteur && y >= 0;
	}

	public void deplacerVaisseauVersLaDroite() {
		if (vaisseau.abscisseLaPlusADroite() < (longueur - 1))
			vaisseau.seDeplacerVersLaDroite();
	}

	public void deplacerVaisseauVersLaGauche() {
		if (vaisseau.abscisseLaPlusAGauche() > 0)
			vaisseau.seDeplacerVersLaGauche();
	}
	
	public Vaisseau getVaisseau() {
		return this.vaisseau;
	}
	
	public void evoluer(Commande commandeUser) {
		if(commandeUser.gauche) {
			deplacerVaisseauVersLaGauche();
		}
		
		if(commandeUser.droite) {
			deplacerVaisseauVersLaDroite();
		}
	}

	public boolean etreFini() {
		// TODO Auto-generated method stub
		return false;
	}


}
