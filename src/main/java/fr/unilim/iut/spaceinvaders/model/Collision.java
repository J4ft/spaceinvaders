package fr.unilim.iut.spaceinvaders.model;

public class Collision {
	static public boolean detecterCollision(Sprite sprite1, Sprite sprite2) {
		boolean collision = false;
		
		if(detecterCollision(sprite1, new Position(sprite2.abscisseLaPlusAGauche(), sprite2.ordonneeLaPlusHaute())))
			collision = true;
		
		else if(detecterCollision(sprite1, new Position(sprite2.abscisseLaPlusADroite(), sprite2.ordonneeLaPlusHaute())))
			collision = true;
		
		else if(detecterCollision(sprite1, new Position(sprite2.abscisseLaPlusAGauche(), sprite2.ordonneeLaPlusBasse())))
			collision = true;
		
		else if(detecterCollision(sprite1, new Position(sprite2.abscisseLaPlusADroite(), sprite2.ordonneeLaPlusBasse())))
			collision = true;
		
		else if(detecterCollision(sprite2, new Position(sprite1.abscisseLaPlusAGauche(), sprite1.ordonneeLaPlusHaute())))
			collision = true;
		
		else if(detecterCollision(sprite2, new Position(sprite1.abscisseLaPlusADroite(), sprite1.ordonneeLaPlusHaute())))
			collision = true;
		
		else if(detecterCollision(sprite2, new Position(sprite1.abscisseLaPlusAGauche(), sprite1.ordonneeLaPlusBasse())))
			collision = true;
		
		else if(detecterCollision(sprite2, new Position(sprite1.abscisseLaPlusADroite(), sprite1.ordonneeLaPlusBasse())))
			collision = true;
		
		return collision;
	}
	
	static public boolean detecterCollision(Sprite sprite, Position position) {
		int x = position.abscisse();
		int y = position.ordonnee();

		return (x >= sprite.abscisseLaPlusAGauche() && x <= sprite.abscisseLaPlusADroite())
				&& (y >= sprite.ordonneeLaPlusHaute() && y <= sprite.ordonneeLaPlusBasse());
	}
}
