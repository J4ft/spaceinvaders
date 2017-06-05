package fr.unilim.iut.spaceinvaders.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

import fr.unilim.iut.moteurjeu.DessinJeu;

public class DessinSpaceinvaders implements DessinJeu {

	public static final int NOMBRE_ETOILES_PREMIER_PLAN = 1000;
	public static final int VITESSE_DEPLACEMENT_ETOILES_PREMIER_PLAN = 3;
	
	public static final int NOMBRE_ETOILES_ARRIERE_PLAN = 500;
	public static final int VITESSE_DEPLACEMENT_ETOILES_ARRIERE_PLAN = 1;
	
	public static final int DUREE_AFFICHAGE_GAIN_SCORE = 30;
	
	SpaceInvaders spaceInvaders;
	int missileValeur;
	int etoileLuminosité;

	final Position[] etoilesPremierPlan;
	final Position[] etoilesArrierePlan;
	
	int score;
	int gainScore;
	int timerAffichageGainScore;

	public DessinSpaceinvaders(SpaceInvaders spaceInvaders) {
		this.spaceInvaders = spaceInvaders;

		this.missileValeur = 0;
		this.etoileLuminosité = 0;

		this.etoilesPremierPlan = new Position[NOMBRE_ETOILES_PREMIER_PLAN];
		this.etoilesArrierePlan = new Position[NOMBRE_ETOILES_ARRIERE_PLAN];

		Random rand = new Random();

		// Premier plan
		for (int i = 0; i < etoilesPremierPlan.length; i++) {
			etoilesPremierPlan[i] = new Position(rand.nextInt() % Constante.ECRAN.longueur(),
					rand.nextInt() % Constante.ECRAN.hauteur());
		}
		
		// Arrière plan
		for (int i = 0; i < etoilesArrierePlan.length; i++) {
			etoilesArrierePlan[i] = new Position(rand.nextInt() % Constante.ECRAN.longueur(),
					rand.nextInt() % Constante.ECRAN.hauteur());
		}
	}

	public void dessiner(BufferedImage image) {
		Graphics2D g = (Graphics2D) image.getGraphics();

		dessinerFond(g);

		dessinerEtoiles(g);

		dessinerVaisseau(g);

		dessinerEnvahisseurs(g);

		dessinerMissiles(g);
		
		if(this.score != spaceInvaders.score()) {
			this.gainScore = spaceInvaders.score() - this.score;
			this.score = spaceInvaders.score;
			this.timerAffichageGainScore = DUREE_AFFICHAGE_GAIN_SCORE;
		}
			
			
		g.setColor(Color.WHITE);
		g.drawString("Score : " + this.score, 5, 15);
		
		if(this.timerAffichageGainScore > 0) {
			if(this.timerAffichageGainScore % 8 <= 3) {
				g.drawString("+ " + this.gainScore, Constante.ECRAN.longueur() - 50, 15);
			}
			
			this.timerAffichageGainScore--;
		}
	}

	private void dessinerMissiles(Graphics2D g) {
		this.missileValeur = (this.missileValeur + 2) % 256;
		g.setColor(new Color(Color.HSBtoRGB((float) this.missileValeur / 256.0f, 0.5f, 1.0f)));

		if (spaceInvaders.aUnMissileVaisseau()) {
			List<Missile> missiles = spaceInvaders.getMissilesVaisseau();

			for (Missile missile : missiles) {
				dessinerMissile(g, missile);
			}
		}
		
		if (spaceInvaders.aUnMissileEnvahisseur()) {
			List<Missile> missiles = spaceInvaders.getMissilesEnvahisseur();

			for (Missile missile : missiles) {
				dessinerMissile(g, missile);
			}
		}
		
		
	}

	private void dessinerMissile(Graphics2D g, Missile missile) {
		int positionMissileX = missile.abscisse();
		int positionMissileY = missile.ordonnee() - Constante.MISSILE.hauteur();

		g.fillRect(positionMissileX, positionMissileY, Constante.MISSILE.longueur(),
				Constante.MISSILE.hauteur());
	}

	private void dessinerEnvahisseurs(Graphics2D g) {
		if (spaceInvaders.aUnEnvahisseur    ()) {
			List<Envahisseur> envahisseurs = spaceInvaders.getEnvahisseurs();
			
			g.setColor(Color.RED);
			
			for (Envahisseur envahisseur : envahisseurs) {
				int positionEnvahisseurX = envahisseur.abscisse();
				int positionEnvahisseurY = envahisseur.ordonnee() - Constante.ENVAHISSEUR.hauteur();

				g.fillRect(positionEnvahisseurX, positionEnvahisseurY, Constante.ENVAHISSEUR.longueur(),
						Constante.ENVAHISSEUR.hauteur());
			}
			
		}
	}

	private void dessinerVaisseau(Graphics2D g) {
		int positionVaisseauX = spaceInvaders.getVaisseau().abscisse();
		int positionVaisseauY = spaceInvaders.getVaisseau().ordonnee() - Constante.VAISSEAU.hauteur();

		g.setColor(Color.MAGENTA);
		g.fillRect(positionVaisseauX, positionVaisseauY, Constante.VAISSEAU.longueur(), Constante.VAISSEAU.hauteur());
	}

	private void dessinerEtoiles(Graphics2D g) {
		deplacerEtoiles();
		
		for (int i = 0; i < etoilesArrierePlan.length; i++) {
			this.etoileLuminosité = ((this.etoileLuminosité + 1) % 192) + 64;
			g.setColor(new Color(Color.HSBtoRGB(0.0f, 0.0f, (float) this.etoileLuminosité / 256.0f)));
			g.fillRect(etoilesArrierePlan[i].abscisse(), etoilesArrierePlan[i].ordonnee(), 1, 1);
		}
		
		for (int i = 0; i < etoilesPremierPlan.length; i++) {
			this.etoileLuminosité = ((this.etoileLuminosité + 1) % 192) + 64;
			g.setColor(new Color(Color.HSBtoRGB(0.0f, 0.0f, (float) this.etoileLuminosité / 256.0f)));
			g.fillRect(etoilesPremierPlan[i].abscisse(), etoilesPremierPlan[i].ordonnee(), 1, 1);
		}
	}

	private void dessinerFond(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Constante.ECRAN.longueur(), Constante.ECRAN.hauteur());
	}
	
	private void deplacerEtoiles() {
		for(Position etoile : etoilesPremierPlan) {
			etoile.changerOrdonnee((etoile.ordonnee() + VITESSE_DEPLACEMENT_ETOILES_PREMIER_PLAN) % Constante.ECRAN.hauteur());
		}
		
		for(Position etoile : etoilesArrierePlan) {
			etoile.changerOrdonnee((etoile.ordonnee() + VITESSE_DEPLACEMENT_ETOILES_ARRIERE_PLAN) % Constante.ECRAN.hauteur());
		}
	}
}