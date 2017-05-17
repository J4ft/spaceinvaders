package fr.unilim.iut.spaceinvaders;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import fr.unilim.iut.moteurjeu.DessinJeu;

public class DessinSpaceinvaders implements DessinJeu {
	
	SpaceInvaders spaceInvaders;

	public DessinSpaceinvaders(SpaceInvaders spaceInvaders) {
		this.spaceInvaders = spaceInvaders;
	}

	public void dessiner(BufferedImage image) {
		Graphics2D g = (Graphics2D) image.getGraphics();
		
		int positionVaisseauX = spaceInvaders.getVaisseau().abscisse();
		int positionVaisseauY = spaceInvaders.getVaisseau().ordonnee() - Constante.VAISSEAU.hauteur();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Constante.ECRAN.longueur(), Constante.ECRAN.hauteur());
		
		g.setColor(Color.BLUE);
		g.fillRect(positionVaisseauX, positionVaisseauY, Constante.VAISSEAU.longueur(), Constante.VAISSEAU.hauteur());
		
	}

}
