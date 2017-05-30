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
	Envahisseur envahisseur;
	Direction directionEnvahisseur;
	int timerMissile;
	int vitesseRechargementMissile;
	

	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
		
		this.missiles = new ArrayList<Missile>();
		this.timerMissile = 0;
		this.vitesseRechargementMissile = 0;
		
	}

	public void initialiser() {
		positionnerUnNouveauVaisseau(Constante.VAISSEAU,
				new Position((Constante.ECRAN.longueur() / 2) - (Constante.VAISSEAU.longueur() / 2), hauteur - 1),
				Constante.VAISSEAU_VITESSE);

		positionnerUnNouvelEnvahisseur(Constante.ENVAHISSEUR,
				new Position((Constante.ECRAN.longueur() / 2) - (Constante.ENVAHISSEUR.longueur() / 2),
						Constante.ENVAHISSEUR.hauteur() + 1),
				Constante.ENVAHISSEUR_VITESSE);
		
		setVitesseRechargementMissile(Constante.VITESSE_RECHARGEMENT_MISSILE);
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
		return aUnEnvahisseur() && envahisseur.occupeLaPosition(x, y);
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
	
	private boolean aUnEnvahisseur() {
		return envahisseur != null;
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
			deplacerEnvahisseur();

		if (collisionMissileEnvahisseur())
			System.out.println("touché");

	}

	public boolean collisionMissileEnvahisseur() {
		if (aUnMissile() && aUnEnvahisseur()) {
			for (Missile missile : this.missiles) {
				if (Collision.detecterCollision(envahisseur, missile))
					return true;
			}
		}
		return false;
	}
	
	public boolean etreFini() {
		return collisionMissileEnvahisseur();
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
		this.envahisseur = (Envahisseur) positionnerUnNouveauSprite(envahisseur, dimension, position, vitesse);
		
		this.directionEnvahisseur = Direction.DROITE;
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

	public Envahisseur getEnvahisseur() {
		return this.envahisseur;
	}

	public void deplacerEnvahisseur() {
		if(this.envahisseur.abscisseLaPlusAGauche() < 1 || this.envahisseur.abscisseLaPlusADroite() > this.longueur - 2)
			this.directionEnvahisseur = Direction.inverse(this.directionEnvahisseur);
			
		this.envahisseur.deplacerHorizontalementVers(this.directionEnvahisseur);

	}
}
