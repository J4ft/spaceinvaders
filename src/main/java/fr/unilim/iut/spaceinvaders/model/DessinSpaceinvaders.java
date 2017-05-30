package fr.unilim.iut.spaceinvaders.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import fr.unilim.iut.moteurjeu.DessinJeu;

public class DessinSpaceinvaders implements DessinJeu {
	
	SpaceInvaders spaceInvaders;

	public DessinSpaceinvaders(SpaceInvaders spaceInvaders) {
		this.spaceInvaders = spaceInvaders;
	}

	public void dessiner(BufferedImage image) {
		Graphics2D g = (Graphics2D) image.getGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Constante.ECRAN.longueur(), Constante.ECRAN.hauteur());
		
		int positionVaisseauX = spaceInvaders.getVaisseau().abscisse();
		int positionVaisseauY = spaceInvaders.getVaisseau().ordonnee() - Constante.VAISSEAU.hauteur();
		
		g.setColor(Color.MAGENTA);
		g.fillRect(positionVaisseauX, positionVaisseauY, Constante.VAISSEAU.longueur(), Constante.VAISSEAU.hauteur());
		
		int positionEnvahisseurX = spaceInvaders.getEnvahisseur().abscisse();
		int positionEnvahisseurY = spaceInvaders.getEnvahisseur().ordonnee() - Constante.ENVAHISSEUR.hauteur();
		
		g.setColor(Color.RED);
		g.fillRect(positionEnvahisseurX, positionEnvahisseurY, Constante.ENVAHISSEUR.longueur(), Constante.ENVAHISSEUR.hauteur());
		
		
		if(spaceInvaders.aUnMissile()) {
			List<Missile> missiles = spaceInvaders.getMissiles();
			g.setColor(Color.GREEN);
			
			for (Missile missile : missiles) {
				int positionMissileX = missile.abscisse();
				int positionMissileY = missile.ordonnee() - Constante.MISSILE.hauteur();

				g.fillRect(positionMissileX, positionMissileY, Constante.MISSILE.longueur(),
						Constante.MISSILE.hauteur());
			}
		}
	}
}