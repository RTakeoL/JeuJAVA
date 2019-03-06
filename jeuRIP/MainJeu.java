package jeuRIP;


public class MainJeu {

	public static void main(String[] args)  {
		
		Jeu jeu = new Jeu();
		Fenetre fenetre = new Fenetre(jeu);
		jeu.setFenetre( fenetre);
		

	}

}
