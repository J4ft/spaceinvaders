package fr.unilim.iut.spaceinvaders.model;

import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class Vaisseau extends Sprite implements TireMissile {
	public Vaisseau(Dimension dimension, Position positionOrigine, int vitesse) {
	    super(dimension, positionOrigine, vitesse);
    }
	
	public Vaisseau(int longueur, int hauteur) {
		this(new Dimension(longueur, hauteur), new Position(0, 0), 1);
	}

	public Vaisseau() {
		super();
	}

	public Missile tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
		// TODO vérifier lancement des exceptions
		if (dimensionMissile.hauteur() > this.dimension.hauteur() ||  dimensionMissile.longueur() > this.dimension.longueur()) {
			throw new MissileException("Le missile est plus grand que le vaisseau");
		}
		
		Position positionOrigineMissile = calculerLaPositionDeTirDuMissile(dimensionMissile);
		return new Missile(dimensionMissile, positionOrigineMissile, vitesseMissile);
	}

	private Position calculerLaPositionDeTirDuMissile(Dimension dimensionMissile) {
		int abscisseMilieuVaisseau = this.abscisseLaPlusAGauche() + (this.longueur() / 2);
		int abscisseOrigineMissile = abscisseMilieuVaisseau - (dimensionMissile.longueur() / 2);

		int ordonneeeOrigineMissile = this.ordonneeLaPlusHaute() - 1;
		Position positionOrigineMissile = new Position(abscisseOrigineMissile, ordonneeeOrigineMissile);
		return positionOrigineMissile;
	}

}
