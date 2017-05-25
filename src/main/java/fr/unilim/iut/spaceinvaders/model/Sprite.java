package fr.unilim.iut.spaceinvaders.model;

public abstract class Sprite {
	
	protected Position origine;
	protected Dimension dimension;
	protected int vitesse;

	public Sprite(Dimension dimension, Position origine, int vitesse) {
		super();
		this.dimension = dimension;
		this.origine = origine;
		this.vitesse = vitesse;
	}

	public Sprite() {
		super();
	}

	public void positionner(int x, int y) {
		origine.changerAbscisse(x);
		origine.changerOrdonnee(y);
	}

	public boolean occupeLaPosition(int x, int y) {
		return estAbscisseCouverte(x) && estOrdonneeCouverte(y);
	}

	private boolean estOrdonneeCouverte(int y) {
		return (ordonneeLaPlusHaute() <= y) && (y <= ordonneeLaPlusBasse());
	}

	public int ordonneeLaPlusBasse() {
		return this.ordonnee();
	}

	public int ordonneeLaPlusHaute() {
		return ordonneeLaPlusBasse() - this.dimension.hauteur() + 1;
	}

	private boolean estAbscisseCouverte(int x) {
		return (abscisseLaPlusAGauche() <= x) && (x <= abscisseLaPlusADroite());
	}

	public int abscisseLaPlusADroite() {
		return this.abscisse() + this.dimension.longueur() - 1;
	}

	public int abscisseLaPlusAGauche() {
		return this.abscisse();
	}
	
	public void deplacerVerticalementVers(Direction direction) {
		this.origine.changerOrdonnee(this.origine.ordonnee() + direction.valeur() * vitesse);
	}
	
	public void deplacerHorizontalementVers(Direction direction) {
		this.origine.changerAbscisse(this.origine.abscisse() + direction.valeur() * vitesse);
	}

	public int abscisse() {
		return this.origine.abscisse();
	}

	public int ordonnee() {
		return this.origine.ordonnee();
	}

	public int longueur() {
		return this.dimension.longueur();
	}

	public int hauteur() {
		return this.dimension.hauteur();
	}

	public void changerDimension(Dimension dimension) {
		this.dimension = dimension;
	}
	
	public void changerPosition(Position origine) {
		this.origine = origine;
	}
	
	public void changerVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

}