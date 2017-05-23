package fr.unilim.iut.spaceinvaders;

public class Vaisseau extends Sprite {
	public Vaisseau(Dimension dimension, Position positionOrigine, int vitesse) {
	    this.dimension = dimension;
	    this.origine = positionOrigine;
	    this.vitesse = vitesse;
    }
	
	public Vaisseau(int longueur, int hauteur) {
		this(new Dimension(longueur, hauteur), new Position(0, 0), 1);
	}

}
