package fr.unilim.iut.spaceinvaders;

public class Vaisseau {
	Position origine;
	Dimension dimension;

	public Vaisseau(int longueur, int hauteur) {
		this.origine = new Position(0, 0);
		this.dimension = new Dimension(longueur, hauteur);
	}

	public Vaisseau(Dimension dimension, Position positionOrigine) {
	    this.dimension = dimension;
	    this.origine = positionOrigine;
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

	private int ordonneeLaPlusBasse() {
		return this.ordonnee();
	}

	private int ordonneeLaPlusHaute() {
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
		origine.changerAbscisse(this.abscisse() + 1);
	}

	public void seDeplacerVersLaGauche() {
		origine.changerAbscisse(this.abscisse() - 1);
	}

	public int abscisse() {
		return this.origine.abscisse();
	}
	
	public int ordonnee() {
		return this.origine.ordonnee();
	}

}
