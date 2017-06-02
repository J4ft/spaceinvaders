package fr.unilim.iut.spaceinvaders.model;

import java.util.ArrayList;
import java.util.List;

import fr.unilim.iut.moteurjeu.Commande;
import fr.unilim.iut.moteurjeu.Jeu;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class SpaceInvaders implements Jeu {

	int longueur;
	int hauteur;
	Vaisseau vaisseau;
	List<Missile> missiles;
	List<Envahisseur> ligneEnvahisseurs;
	Envahisseur envahisseurLePlusAGauche;
	Envahisseur envahisseurLePlusADroite;
	Direction directionEnvahisseur;
	int timerMissile;
	int vitesseRechargementMissile;
	int marge;
	boolean estFini;
	int score;
	int timerFinJeu;
	
	
	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
		
		this.missiles = new ArrayList<Missile>();
		this.timerMissile = 0;
		this.vitesseRechargementMissile = 0;
		
		this.ligneEnvahisseurs = new ArrayList<Envahisseur>();
		
		this.marge = 0;
		
		this.estFini = false;
		this.score = 0;
		this.timerFinJeu = 0;
	}

	public void initialiser() {
		positionnerUnNouveauVaisseau(Constante.VAISSEAU,
				new Position((Constante.ECRAN.longueur() / 2) - (Constante.VAISSEAU.longueur() / 2), hauteur - 1),
				Constante.VAISSEAU_VITESSE);

		this.marge = Constante.MARGE_BORD_ECRAN;

		positionnerUneNouvelleLigneEnvahisseur(Constante.ENVAHISSEUR, new Position(0, Constante.ENVAHISSEUR.hauteur() * 3 + 1), Constante.ENVAHISSEURS_NOMBRE, Constante.ENVAHISSEUR_VITESSE);
		
		setVitesseRechargementMissile(Constante.VITESSE_RECHARGEMENT_MISSILE);
		
		this.timerFinJeu = Constante.DUREE_APRES_FIN_JEU;

	}


	public boolean peutTirerMissile() {
		return this.timerMissile == 0;
	}
	
	public void setVitesseRechargementMissile(int vitesseRechargementMissile) {
		if(vitesseRechargementMissile < 0) {
			throw new IllegalArgumentException("La vitesse de rechargement des missiles doit être positive");
		}
		
		this.vitesseRechargementMissile = vitesseRechargementMissile;
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
		else if (aUnEnvahisseurQuiOccupeLaPosition(x,y))
			marque = Constante.MARQUE_ENVAHISSEUR;
		else if (aUnMissileQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_MISSILE;
		else
			marque = Constante.MARQUE_VIDE;
		return marque;
	}

	private boolean aUnEnvahisseurQuiOccupeLaPosition(int x, int y) {
		//return aUnEnvahisseur() && envahisseur.occupeLaPosition(x, y);
		if (aUnEnvahisseur()) {
			for(Envahisseur envahisseur : ligneEnvahisseurs) {
				if(envahisseur.occupeLaPosition(x, y))
					return true;
			}
		}
		return false;
	}

	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {		
		if (aUnMissile()) {
			for (Missile missile : this.missiles) {
				if (missile.occupeLaPosition(x, y))
					return true;
			}
		}
		return false;
	}

	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
	}
	
	public boolean aUnEnvahisseur() {
		return ligneEnvahisseurs.size() != 0;
	}

	private boolean aUnVaisseau() {
		return vaisseau != null;
	}
	
	public boolean aUnMissile() {
		return this.missiles.size() != 0;
	}

	private boolean estDansEspaceJeu(int x, int y) {
		return x < longueur && x >= 0 && y < hauteur && y >= 0;
	}

	public void deplacerVaisseauVersLaDroite() {
		if (vaisseau.abscisseLaPlusADroite() < (longueur - 1)) {
			vaisseau.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) {
				vaisseau.positionner(longueur - vaisseau.longueur(), vaisseau.ordonnee());
			}
		}
	}
	
	public void deplacerVaisseauVersLaGauche() {
		if (0 < vaisseau.abscisseLaPlusAGauche())
			vaisseau.deplacerHorizontalementVers(Direction.GAUCHE);
		if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusHaute())) {
			vaisseau.positionner(0, vaisseau.ordonnee());
		}
	}

	public Vaisseau getVaisseau() {
		return this.vaisseau;
	}

	public void evoluer(Commande commandeUser) {
		if (commandeUser.gauche)
			deplacerVaisseauVersLaGauche();

		if (commandeUser.droite)
			deplacerVaisseauVersLaDroite();

		if (commandeUser.espace && peutTirerMissile())
			tirerUnMissile(Constante.MISSILE, Constante.MISSILE_VITESSE);

		if (aUnMissile())
			deplacerMissile();

		if (aUnEnvahisseur())
			deplacerEnvahisseurs();

		for(Envahisseur envahisseur : collisionMissileEnvahisseurs()) {
			detruireEnvahisseur(envahisseur);
		}
		
		if(!aUnEnvahisseur())
			this.estFini = true;

	}

	private void detruireEnvahisseur(Envahisseur envahisseur) {
		this.ligneEnvahisseurs.remove(envahisseur);
		
		if(envahisseur == this.envahisseurLePlusAGauche)
			this.envahisseurLePlusAGauche = trouverEnvahisseurLePlusAGauche();
		
		if(envahisseur == this.envahisseurLePlusADroite)
			this.envahisseurLePlusADroite = trouverEnvahisseurLePlusADroite();
		
		envahisseur = null;
		
		this.score += Constante.SCORE_DETRUIRE_ENVAHISSEUR;
	}

	public List<Envahisseur> collisionMissileEnvahisseurs() {
		List<Envahisseur> envahisseursTouche = new ArrayList<Envahisseur>();
		
		if (aUnMissile() && aUnEnvahisseur()) {
			
			for (Missile missile : this.missiles) {
				for (Envahisseur envahisseur : this.ligneEnvahisseurs) {
					if (Collision.detecterCollision(envahisseur, missile))
						envahisseursTouche.add(envahisseur);
				}
			}
		}
		return envahisseursTouche;
	}
	
	public boolean etreFini() {
		
		if(this.estFini) {
			if(this.timerFinJeu == 0)
				return true;
			else {
				this.timerFinJeu--;
			}
		}
		return false;
	}

	public void tirerUnMissile(Dimension dimension, int vitesse) {
		if ((vaisseau.hauteur()+ dimension.hauteur()) > this.hauteur )
			throw new MissileException("Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");
		
		if (!peutTirerMissile())
			throw new MissileException("Le temps de rechargement du missile n'est pas encore écoulé.");
		
		this.missiles.add(this.vaisseau.tirerUnMissile(dimension, vitesse));
		this.timerMissile = this.vitesseRechargementMissile;
		
	}

	public List<Missile> getMissiles() {
		return this.missiles;
	}

	public void deplacerMissile() {
		if (aUnMissile()) {
			for (int i = 0; i < this.missiles.size(); i++) {
				this.missiles.get(i).deplacerVerticalementVers(Direction.HAUT_ECRAN);
				if (this.missiles.get(i).ordonneeLaPlusHaute() < 0)
					this.missiles.remove(i);
			}
		}
		
		if(this.timerMissile > 0)
			this.timerMissile--;
	}

	public void positionnerUnNouvelEnvahisseur(Dimension dimension, Position position, int vitesse) {
		Envahisseur envahisseur = new Envahisseur();
		this.ligneEnvahisseurs.add((Envahisseur) positionnerUnNouveauSprite(envahisseur, dimension, position, vitesse));
		
		this.directionEnvahisseur = Direction.DROITE;
		
		this.envahisseurLePlusAGauche = trouverEnvahisseurLePlusAGauche();
		this.envahisseurLePlusADroite = trouverEnvahisseurLePlusADroite();
	}

	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {
		Vaisseau vaisseau = new Vaisseau();
		this.vaisseau = (Vaisseau) positionnerUnNouveauSprite(vaisseau, dimension, position, vitesse);
	}

	public Sprite positionnerUnNouveauSprite(Sprite sprite, Dimension dimension, Position position, int vitesse) {
		int x = position.abscisse();
		int y = position.ordonnee();
		
		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du sprite est en dehors de l'espace jeu");

		if (!estDansEspaceJeu(x + dimension.longueur() - 1, y))
			throw new DebordementEspaceJeuException(
					"Le sprite déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - dimension.hauteur() + 1))
			throw new DebordementEspaceJeuException(
					"Le sprite déborde de l'espace jeu vers le bas à cause de sa hauteur");

		sprite.changerDimension(dimension);
		sprite.changerPosition(position);
		sprite.changerVitesse(vitesse);
		sprite.positionner(x, y);
		return sprite;
	}

	public List<Envahisseur> getEnvahisseurs() {
		return this.ligneEnvahisseurs;
	}

	public void deplacerEnvahisseurs() {
		if (aUnEnvahisseur()) {
			if (this.envahisseurLePlusAGauche.abscisseLaPlusAGauche() < 1
					|| this.envahisseurLePlusADroite.abscisseLaPlusADroite() > this.longueur - 2) {
				this.directionEnvahisseur = Direction.inverse(this.directionEnvahisseur);
			}
			
			
			for (Envahisseur envahisseur : ligneEnvahisseurs) {
				envahisseur.deplacerHorizontalementVers(this.directionEnvahisseur);
			}
		}
	}

	public void positionnerUneNouvelleLigneEnvahisseur(Dimension dimension, Position position, int nombreEnvahisseur, int vitesse) {
		int espacement = (this.longueur - dimension.longueur() * nombreEnvahisseur - 2 * this.marge) / (nombreEnvahisseur + 1);
		
		if (espacement < 0)
			throw new IllegalArgumentException();
		
		int abscisse = espacement;
		int ordonnee = position.ordonnee();
		
		for(int i = 0; i < nombreEnvahisseur; i++) {
			this.positionnerUnNouvelEnvahisseur(dimension, new Position(abscisse, ordonnee), vitesse);
			abscisse += espacement + dimension.longueur();
		}
	}
	
	public Envahisseur trouverEnvahisseurLePlusAGauche() {
		int abscisseLaPlusAGauche = this.longueur;
		Envahisseur envahisseurLePlusAGauche = null;
		
		for (Envahisseur envahisseur : ligneEnvahisseurs) {
			if (envahisseur.abscisseLaPlusAGauche() < abscisseLaPlusAGauche) {
				abscisseLaPlusAGauche = envahisseur.abscisseLaPlusAGauche();
				envahisseurLePlusAGauche = envahisseur;
			}
		}
		
		return envahisseurLePlusAGauche;
	}
	
	public Envahisseur trouverEnvahisseurLePlusADroite() {
		int abscisseLaPlusADroite = 0;
		Envahisseur envahisseurLePlusADroite = null;
		
		for (Envahisseur envahisseur : ligneEnvahisseurs) {
			if (envahisseur.abscisseLaPlusADroite() > abscisseLaPlusADroite) {
				abscisseLaPlusADroite = envahisseur.abscisseLaPlusADroite();
				envahisseurLePlusADroite = envahisseur;
			}
		}
		
		return envahisseurLePlusADroite;
	}

	public int score() {
		return this.score;
	}
}
