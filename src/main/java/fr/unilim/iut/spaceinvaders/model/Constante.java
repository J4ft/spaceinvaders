package fr.unilim.iut.spaceinvaders.model;

public class Constante {
	public static final Dimension ECRAN = new Dimension(250, 400);
	
	public static final Dimension VAISSEAU = new Dimension(33, 20);
	public static final int VAISSEAU_VITESSE = 14;
	
	public static final Dimension MISSILE = new Dimension(3, 6);
	public static final int MISSILE_VITESSE = 14;
	
	public static final Dimension ENVAHISSEUR = new Dimension(27, 15);
	public static final int ENVAHISSEUR_VITESSE = 5;

	public static final char MARQUE_VIDE = '.';
	public static final char MARQUE_VAISSEAU = 'V';
	public static final char MARQUE_MISSILE = 'M';
	public static final char MARQUE_ENVAHISSEUR = 'E';
}
