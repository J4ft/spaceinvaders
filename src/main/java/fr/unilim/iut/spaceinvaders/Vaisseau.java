package fr.unilim.iut.spaceinvaders;

public class Vaisseau extends Sprite {
	public Vaisseau(Dimension dimension, Position positionOrigine, int vitesse) {
	    super(dimension, positionOrigine, vitesse);
    }
	
	public Vaisseau(int longueur, int hauteur) {
		this(new Dimension(longueur, hauteur), new Position(0, 0), 1);
	}

	public Missile tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
		Position positionOrigineMissile = calculerLaPositionDeTirDuMissile(dimensionMissile);
		return new Missile(dimensionMissile, positionOrigineMissile, vitesseMissile);
	}

	private Position calculerLaPositionDeTirDuMissile(Dimension dimensionMissile) {
		int abscisseMilieuVaisseau = this.abscisseLaPlusAGauche() + (this.longueur() / 2);
		int abscisseOrigineMissile = abscisseMilieuVaisseau - (dimensionMissile.longueur() / 2);

		int ordonneeeOrigineMissile = this.ordonneeLaPlusBasse() - dimensionMissile.hauteur();
		Position positionOrigineMissile = new Position(abscisseOrigineMissile, ordonneeeOrigineMissile);
		return positionOrigineMissile;
	}

}
