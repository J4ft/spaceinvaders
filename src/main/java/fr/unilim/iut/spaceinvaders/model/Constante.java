package fr.unilim.iut.spaceinvaders.model;

public class Constante {
	public static final Dimension ECRAN = new Dimension(500, 400);
	
	public static final Dimension VAISSEAU = new Dimension(33, 20);
	public static final int VAISSEAU_VITESSE = 5;
	
	public static final Dimension MISSILE = new Dimension(3, 12);
	public static final int VITESSE_RECHARGEMENT_MISSILE = 30;
	public static final int MISSILE_VITESSE_VAISSEAU = 7;
	public static final int MISSILE_VITESSE_ENVAHISSEUR = 7;	
	
	public static final Dimension ENVAHISSEUR = new Dimension(27, 15);
	public static final int ENVAHISSEUR_VITESSE = 1;
	public static final int ENVAHISSEURS_NOMBRE = 8;
	
	public static final int MARGE_BORD_ECRAN = 20;
	
	public static final int SCORE_DETRUIRE_ENVAHISSEUR = 100;
	
	public static final int DUREE_APRES_FIN_JEU = 30;

	public static final char MARQUE_VIDE = '.';
	public static final char MARQUE_VAISSEAU = 'V';
	public static final char MARQUE_MISSILE = 'M';
	public static final char MARQUE_ENVAHISSEUR = 'E';
}
