package fr.unilim.iut.spaceinvaders;

public class Vaisseau {
	int x;
	int y;
	int longueur;
	int hauteur;

	public Vaisseau(int longueur, int hauteur) {
		this.x = 0;
		this.y = 0;
		this.longueur = longueur;
		this.hauteur = hauteur;
	}

	public boolean occupeLaPosition(int x, int y) {
		return estAbscisseCouverte(x) && estOrdonneeCouverte(y);
	}

	private boolean estOrdonneeCouverte(int y) {
		return (ordonneeLaPlusHaute() <= y) && (y <= ordonneeLaPlusBasse());
	}

	private int ordonneeLaPlusBasse() {
		return this.y;
	}

	private int ordonneeLaPlusHaute() {
		return ordonneeLaPlusBasse() - this.hauteur + 1;
	}

	private boolean estAbscisseCouverte(int x) {
		return (abscisseLaPlusAGauche() <= x) && (x <= abscisseLaPlusADroite());
	}

	public int abscisseLaPlusADroite() {
		return this.x + this.longueur - 1;
	}

	public int abscisseLaPlusAGauche() {
		return this.x;
	}

	public void seDeplacerVersLaDroite() {
		this.x = this.x + 1;
	}

	public void seDeplacerVersLaGauche() {
		this.x = this.x - 1;
	}

	public int abscisse() {
		return this.x;
	}

	public void positionner(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
