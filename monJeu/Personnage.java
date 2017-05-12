package monJeu;

import moteurJeu.Commande;

/**
 * la classe correspondant au Personnage
 */
public class Personnage {

	/**
	 * position en X et y
	 */
	public int x;
	public int y;

	/**
	 * taille de l'environnement
	 */
	final static int LIMIT_X = 15;
	final static int LIMIT_Y = 15;

	/**
	 * constructeur vide
	 */
	public Personnage() {
		this.x = LIMIT_X / 2;
		this.y = LIMIT_Y / 2;
	}

	/**
	 * deplacer le personnage dans une direction
	 * 
	 * @param m
	 *            la chaine permettant de deplacer le personnage (N,S, E ou O)
	 */
	public void deplacer(Commande c) {
		
		if (c.gauche)
		{
			this.x--;
			if (this.x < 0)
				this.x = 0;
		}

		if (c.droite)
		{
			this.x++;
			if (this.x >LIMIT_X)
				this.x = LIMIT_X;
		}
	}

	public String toString() {
		return ("(" + this.x+","+this.y+")");
	}

}
