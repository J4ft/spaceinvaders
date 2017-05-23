package fr.unilim.iut.spaceinvaders;

public class Vaisseau {
	Position origine;
	Dimension dimension;
	int vitesse;


	public Vaisseau(Dimension dimension, Position positionOrigine, int vitesse) {
	    this.dimension = dimension;
	    this.origine = positionOrigine;
	    this.vitesse = vitesse;
    }
	
	public Vaisseau(int longueur, int hauteur) {
		this(new Dimension(longueur, hauteur), new Position(0, 0), 1);
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

	public void seDeplacerVersLaDroite() {
		origine.changerAbscisse(this.abscisse() + this.vitesse);
	}

	public void seDeplacerVersLaGauche() {
		origine.changerAbscisse(this.abscisse() - this.vitesse);
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

}
