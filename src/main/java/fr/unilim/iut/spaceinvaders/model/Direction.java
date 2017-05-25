package fr.unilim.iut.spaceinvaders.model;

public enum Direction {

	HAUT(1),
	BAS(-1), 
	GAUCHE(-1), 
	DROITE(1),

	HAUT_ECRAN(-1), 
	BAS_ECRAN(1);

	private int valeur;

	private Direction(int valeur) {
		this.valeur = valeur;
	}

	public int valeur() {
		return this.valeur;
	}

	static public Direction inverse(Direction direction) {
		switch (direction) {
		case HAUT:
			return Direction.BAS;
		case BAS:
			return Direction.HAUT;
		case HAUT_ECRAN:
			return Direction.BAS_ECRAN;
		case BAS_ECRAN:
			return Direction.HAUT_ECRAN;
		case DROITE:
			return Direction.GAUCHE;
		case GAUCHE:
			return Direction.DROITE;
		default:
			return null;

		}
	}

}