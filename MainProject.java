/**
 * 
 */
package ownProject;

/**
 * @author Rld-Comp
 *
 */
public class MainProject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jeu nouvJeu = new Jeu();
		nouvJeu.creerCarte();
		int i;
		i=0;
		while(i < nouvJeu.zones.size()) {
			System.out.println("Zone de jeu " + i + " : " + 
						nouvJeu.zones.get(i).toString() + "\n");
			System.out.println("Zone de jeu et ses sorties " + i + " : " + 
					nouvJeu.zones.get(i).getNomSorties() + "\n");	
			i=i+1;
		}
	}

}
