package fr.unilim.iut.spaceinvaders.model;

import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class Envahisseur extends Sprite implements TireMissile{
	public Envahisseur(Dimension dimension, Position positionOrigine, int vitesse) {
	    super(dimension, positionOrigine, vitesse);
    }

	public Envahisseur() {
		super();
	}

	public Missile tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
		// TODO vérifier lancement des exceptions
		if (dimensionMissile.hauteur() > this.dimension.hauteur() ||  dimensionMissile.longueur() > this.dimension.longueur()) {
			throw new MissileException("Le missile est plus grand que l'envahisseur");
		}
		
		Position positionOrigineMissile = calculerLaPositionDeTirDuMissile(dimensionMissile);
		return new Missile(dimensionMissile, positionOrigineMissile, vitesseMissile);
	}

	private Position calculerLaPositionDeTirDuMissile(Dimension dimensionMissile) {
		int abscisseMilieuEnvahisseur = this.abscisseLaPlusAGauche() + (this.longueur() / 2);
		int abscisseOrigineMissile = abscisseMilieuEnvahisseur - (dimensionMissile.longueur() / 2);

		int ordonneeeOrigineMissile = this.ordonneeLaPlusBasse() + 1;
		Position positionOrigineMissile = new Position(abscisseOrigineMissile, ordonneeeOrigineMissile);
		return positionOrigineMissile;
	}
}
