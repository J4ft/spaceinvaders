package fr.unilim.iut.spaceinvaders;

public class Position {

	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int abscisse() {
		return x;
	}

	public int ordonnee() {
		return y;
	}
	
	public void changerAbscisse(int abscisse) {
		this.x = abscisse;
	}
	
	public void changerOrdonnee(int ordonnee) {
		this.y = ordonnee;
	}

}
