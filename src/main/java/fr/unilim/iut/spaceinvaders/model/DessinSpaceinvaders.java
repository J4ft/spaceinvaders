package fr.unilim.iut.spaceinvaders.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

import fr.unilim.iut.moteurjeu.DessinJeu;

public class DessinSpaceinvaders implements DessinJeu {
	
	SpaceInvaders spaceInvaders;
	int missileValeur;
	int etoileLuminosité;
	
	
	
	Position[] etoiles;

	public DessinSpaceinvaders(SpaceInvaders spaceInvaders) {
		this.spaceInvaders = spaceInvaders;
		
		this.missileValeur = 0;
		this.etoileLuminosité = 0;
		
		this.etoiles = new Position[Constante.NOMBRE_ETOILES];
		
		
		Random rand = new Random();
		
		for (int i = 0; i < etoiles.length; i++) {
			etoiles[i] = new Position(rand.nextInt() % Constante.ECRAN.longueur(), rand.nextInt() % Constante.ECRAN.hauteur());
		}
		
	}

	public void dessiner(BufferedImage image) {
		Graphics2D g = (Graphics2D) image.getGraphics();
		
		// fond
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Constante.ECRAN.longueur(), Constante.ECRAN.hauteur());
		
		// etoiles
		
		for (int i = 0; i < etoiles.length; i++) {			
			this.etoileLuminosité = ((this.etoileLuminosité + 1) % 192) + 64;
			g.setColor(new Color(Color.HSBtoRGB(0.0f, 0.0f, (float) this.etoileLuminosité / 256.0f)));
			g.fillRect(etoiles[i].abscisse(), etoiles[i].ordonnee(), 1, 1);
		}
		
		// vaisseau
		int positionVaisseauX = spaceInvaders.getVaisseau().abscisse();
		int positionVaisseauY = spaceInvaders.getVaisseau().ordonnee() - Constante.VAISSEAU.hauteur();
		
		g.setColor(Color.MAGENTA);
		g.fillRect(positionVaisseauX, positionVaisseauY, Constante.VAISSEAU.longueur(), Constante.VAISSEAU.hauteur());
		
		// envahisseur
		int positionEnvahisseurX = spaceInvaders.getEnvahisseur().abscisse();
		int positionEnvahisseurY = spaceInvaders.getEnvahisseur().ordonnee() - Constante.ENVAHISSEUR.hauteur();
		
		g.setColor(Color.RED);
		g.fillRect(positionEnvahisseurX, positionEnvahisseurY, Constante.ENVAHISSEUR.longueur(), Constante.ENVAHISSEUR.hauteur());
		
		// missiles
		if(spaceInvaders.aUnMissile()) {
			List<Missile> missiles = spaceInvaders.getMissiles();
			
			this.missileValeur = (this.missileValeur + 2) % 256;
			g.setColor(new Color(Color.HSBtoRGB((float) this.missileValeur / 256.0f, 0.5f, 1.0f)));
			
			for (Missile missile : missiles) {
				int positionMissileX = missile.abscisse();
				int positionMissileY = missile.ordonnee() - Constante.MISSILE.hauteur();

				g.fillRect(positionMissileX, positionMissileY, Constante.MISSILE.longueur(),
						Constante.MISSILE.hauteur());
			}
		}
	}
}