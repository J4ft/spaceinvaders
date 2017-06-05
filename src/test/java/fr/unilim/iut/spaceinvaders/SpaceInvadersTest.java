package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.moteurjeu.Commande;
import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class SpaceInvadersTest {
	
	private SpaceInvaders spaceinvaders;
	
	@Before
	public void Initialisation() {
		spaceinvaders = new SpaceInvaders(15, 10);
	}
	
	@Test
	public void test_AuDebut_JeuSpaceInvaderEstVide() {
		assertEquals("" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n"	+ 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n"	+ 
		"...............\n" + 
		"...............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_unNouveauVaisseauEstCorrectementPositionneDansEspaceJeu() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1), new Position(7, 9), 1);
		assertEquals("" + 
		"...............\n" + 
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		".......V.......\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_unNouveauVaisseauAvecDimensionEstCorrectementPositionneDansEspaceJeu() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9), 1);
		assertEquals("" + 
		"...............\n" + 
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		".......VVV.....\n" + 
		".......VVV.....\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_UnNouveauVaisseauPositionneHorsEspaceJeu_DoitLeverUneException() {

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(15, 9), 1);
			fail("Position trop à droite : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1), new Position(-1, 9), 1);
			fail("Position trop à gauche : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1), new Position(4, 10), 1);
			fail("Position trop en bas : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1), new Position(14, -1), 1);
			fail("Position trop à haut : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

	}
	
	@Test
	public void test_UnNouveauVaisseauPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {
		
		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(9,2),new Position(7,9), 1);
			fail("Dépassement du vaisseau à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}
		
		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,4),new Position(7,1), 1);
			fail("Dépassement du vaisseau vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}		
	}

	@Test
	public void test_VaisseauAvecDimensionImmobile_DeplacerVaisseauVersLaDroite() {
		
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(12,9), 1);
		spaceinvaders.deplacerVaisseauVersLaDroite();
		assertEquals("" + 
		"...............\n" + 
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"............VVV\n" + 
		"............VVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
    
	@Test
	public void test_VaisseauAvecDimensionAvance_DeplacerVaisseauVersLaGauche() {
		
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9), 3);
	       spaceinvaders.deplacerVaisseauVersLaGauche();

	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "....VVV........\n" + 
	       "....VVV........\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }
	 
	@Test
	public void test_VaisseauAvecDimensionImmobile_DeplacerVaisseauVersLaGauche() {
		
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(0,9), 1);
		spaceinvaders.deplacerVaisseauVersLaGauche();
		
		assertEquals("" + 
		"...............\n" + 
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"VVV............\n" + 
		"VVV............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
    public void test_VaisseauAvance_DeplacerVaisseauVersLaDroite() {

        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9),3);
        spaceinvaders.deplacerVaisseauVersLaDroite();
        assertEquals("" + 
        "...............\n" + 
        "...............\n" +
        "...............\n" + 
        "...............\n" + 
        "...............\n" + 
        "...............\n" + 
        "...............\n" + 
        "...............\n" + 
        "..........VVV..\n" + 
        "..........VVV..\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_VaisseauImmobile_DeplacerVaisseauVersLaDroite() {

       spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(12,9), 3);
       spaceinvaders.deplacerVaisseauVersLaDroite();
       assertEquals("" + 
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "............VVV\n" + 
       "............VVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }
    
    @Test
    public void test_VaisseauImmobile_DeplacerVaisseauVersLaGauche() {

	   spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(0,9), 3);
       spaceinvaders.deplacerVaisseauVersLaGauche();

       assertEquals("" + 
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "VVV............\n" + 
       "VVV............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }
    
    @Test
    public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaGauche() {

       spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(1,9), 3);
       spaceinvaders.deplacerVaisseauVersLaGauche();

       assertEquals("" + 
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "VVV............\n" + 
       "VVV............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }
    
    @Test
    public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaDroite() {

       spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(10,9),3);
       spaceinvaders.deplacerVaisseauVersLaDroite();
       assertEquals("" + 
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "............VVV\n" + 
       "............VVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }
	
	@Test
    public void test_MissileVaisseauBienTireDepuisVaisseau_VaisseauLongueurImpaireMissileLongueurImpaire() {

	   spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 2);
	   spaceinvaders.vaisseauTireUnMissile(new Dimension(3,2),2);
	   assertEquals("" + 
	   "...............\n" + 
	   "...............\n" +
	   "...............\n" + 
	   "...............\n" + 
	   "...............\n" + 
	   "...............\n" + 
	   ".......MMM.....\n" + 
	   ".......MMM.....\n" + 
	   ".....VVVVVVV...\n" + 
	   ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
   }
	
	@Test
	public void test_MissileVaisseauAvanceAutomatiquement_ApresTirDepuisLeVaisseau() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7, 2), new Position(5, 9), 2);
		spaceinvaders.vaisseauTireUnMissile(new Dimension(3, 2), 2);
		
		spaceinvaders.deplacerMissile();
		
		assertEquals("" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n"	+ 
		".......MMM.....\n" + 
		".......MMM.....\n" + 
		"...............\n" + 
		"...............\n"	+ 
		".....VVVVVVV...\n" + 
		".....VVVVVVV...\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_MissileVaisseauDisparait_QuandIlCommenceASortirDeEspaceJeu() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7, 2), new Position(5, 9), 1);
		spaceinvaders.vaisseauTireUnMissile(new Dimension(3, 2), 1);
		
		for(int i = 0; i < 6; i++) spaceinvaders.deplacerMissile();

		spaceinvaders.deplacerMissile();
		
		assertEquals("" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n"	+ 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n"	+ 
		".....VVVVVVV...\n" + 
		".....VVVVVVV...\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test(expected = MissileException.class)
	public void test_PasAssezDePlacePourTirerUnMissileVaisseau_UneExceptionEstLevee() throws Exception { 
	   spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 1);
	   spaceinvaders.vaisseauTireUnMissile(new Dimension(7,9),1);
	}
	
	@Test
	public void test_tirerPlusieursMissilesVaisseau() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7, 2), new Position(5, 9), 2);
		spaceinvaders.vaisseauTireUnMissile(new Dimension(3, 2), 2);
		
		spaceinvaders.deplacerMissile();
		spaceinvaders.deplacerMissile();
		
		spaceinvaders.vaisseauTireUnMissile(new Dimension(3, 2), 2);
		
		assertEquals("" + 
		"...............\n" + 
		"...............\n" + 
		".......MMM.....\n" + 
		".......MMM.....\n"	+ 
		"...............\n" + 
		"...............\n" + 
		".......MMM.....\n" + 
		".......MMM.....\n"	+ 
		".....VVVVVVV...\n" + 
		".....VVVVVVV...\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_unNouveauEnvahisseurEstCorrectementPositionneDansEspaceJeu(){
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1,1), new Position(7, 0), 1);
		
		assertEquals("" + 
		".......E.......\n" + 
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_unNouveauEnvahisseurEstCorrectementPositionneDansEspaceJeu_AvecDimension(){
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(7, 1), 1);
		
		assertEquals("" + 
		".......EEE.....\n" + 
		".......EEE.....\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_UnNouvelEnvahisseurPositionneHorsEspaceJeu_DoitLeverUneException() {

		try {
			spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1,1),new Position(15, 9), 1);
			fail("Position trop à droite : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1,1), new Position(-1, 9), 1);
			fail("Position trop à gauche : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1,1), new Position(4, 10), 1);
			fail("Position trop en bas : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1,1), new Position(14, -1), 1);
			fail("Position trop à haut : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

	}
	
	@Test
	public void test_EnvahisseurAvanceAutomatiquement() {
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(7, 1), 1);
		
		spaceinvaders.deplacerEnvahisseurs();
		
		assertEquals("" + 
		"........EEE....\n" + 
		"........EEE....\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_EnvahisseurAvanceAutomatiquementEtChangeDeDirection() {
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(7, 1), 1);
		
		for(int i = 0; i < 6; i++) {
			spaceinvaders.deplacerEnvahisseurs();
		}
		
		assertEquals("" + 
		"...........EEE.\n" + 
		"...........EEE.\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_plusDEnvahisseur_finDePartie() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(7, 9), 1);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(2, 1), 1);
		spaceinvaders.vaisseauTireUnMissile(new Dimension(1, 1), 1);
		
		// simulation boucle de jeu
		for(int i = 0; i < 6; i++) {
			spaceinvaders.evoluer(new Commande());
		}
		
		assertEquals(true, spaceinvaders.etreFini());
		
	}
	
	@Test
	public void test_PasCollisionEntreMissileEtEnvahisseur_pasFinDePartie() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(7, 9), 1);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(7, 1), 1);
		spaceinvaders.vaisseauTireUnMissile(new Dimension(1, 1), 1);
		
		// simulation boucle de jeu
		for(int i = 0; i < 6; i++) {
			spaceinvaders.deplacerEnvahisseurs();
			spaceinvaders.deplacerMissile();
		}
		
		assertEquals(false, spaceinvaders.etreFini());
		
	}
	
	@Test
	public void test_unNouvelleLigneEnvahisseurEstCorrectementPositionneDansEspaceJeu(){
		spaceinvaders.positionnerUneNouvelleLigneEnvahisseur(new Dimension(1,1), new Position(7, 0), 3, 1);
		
		assertEquals("" + 
		"...E...E...E...\n" + 
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_deplacerLigneEnvahisseur(){
		spaceinvaders.positionnerUneNouvelleLigneEnvahisseur(new Dimension(1,1), new Position(7, 0), 3, 1);
		
		spaceinvaders.deplacerEnvahisseurs();
		
		assertEquals("" + 
		"....E...E...E..\n" + 
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_deplacerLigneEnvahisseur_ChangementDeSens(){
		spaceinvaders.positionnerUneNouvelleLigneEnvahisseur(new Dimension(1,1), new Position(7, 0), 3, 1);
		
		for(int i = 0; i < 4; i++) {
			spaceinvaders.deplacerEnvahisseurs();
		}
		
		
		assertEquals("" + 
		".....E...E...E.\n" + 
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_scoreVaut0AuDebutDeLaPartie() {
		assertEquals(0, spaceinvaders.score());
	}
	
	@Test
	public void test_scoreVaut100ApresDestructionEnvahisseur() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(7, 9), 1);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,2), new Position(2, 1), 1);
		spaceinvaders.vaisseauTireUnMissile(new Dimension(1, 1), 1);
		
		// simulation boucle de jeu
		for(int i = 0; i < 6; i++) {
			spaceinvaders.evoluer(new Commande());
		}
		
		assertEquals(100, spaceinvaders.score());
	}
	
	@Test
	public void test_envahisseurTireMissile() {
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1,1), new Position(7, 0), 1);
		spaceinvaders.envahisseurTireUnMissile(new Dimension(1, 1), 1);
		
		assertEquals("" + 
		".......E.......\n" + 
		".......M.......\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_MissileEnvahisseurAvanceAutomatiquement_ApresTirDepuisLeVaisseau() {
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1,1), new Position(7, 0), 1);
		spaceinvaders.envahisseurTireUnMissile(new Dimension(1, 1), 1);
		
		spaceinvaders.deplacerMissile();
		
		assertEquals("" + 
		".......E.......\n" + 
		"...............\n" + 
		".......M.......\n" + 
		"...............\n"	+ 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n"	+ 
		"...............\n" + 
		"...............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_MissileEnvahisseurDisparait_QuandIlCommenceASortirDeEspaceJeu() {
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(1,1), new Position(7, 0), 1);
		spaceinvaders.envahisseurTireUnMissile(new Dimension(1, 1), 1);
		
		for(int i = 0; i < 9; i++) spaceinvaders.deplacerMissile();

		spaceinvaders.deplacerMissile();
		
		assertEquals("" + 
		".......E.......\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n"	+ 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n"	+ 
		"...............\n" + 
		"...............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
}
