/**
 * 
 */
package ownProject;
import java.util.Stack;
/**
 * @author Rld-Comp
 *
 */
public class Jeu {

	/**
	 * @param args
	 */
	private Zone zoneCourante;
	public Stack<Zone> zones; // private parce que fenetre est instancie dans jeu
	
	
	public void creerCarte() {
        this.zones = new Stack<Zone>();
        
        this.zones.add(new Zone("Ruelle de Départ", "Couloir.jpg" ));
        this.zones.add(new Zone("Métro", "Escalier.jpg" ));
        this.zones.add(new Zone("Ruelle EST (Sud)", "GrandeSalle.jpg" ));
        this.zones.add(new Zone("Station Essence", "SalleAManger.jpg"));
        this.zones.add(new Zone("Armurerie", "SalleAManger.jpg"));
        this.zones.add(new Zone("Entrée Aéroport", "SalleAManger.jpg"));
        this.zones.add(new Zone("Piste Aéroport", "SalleAManger.jpg"));
        this.zones.add(new Zone("Supermarché", "SalleAManger.jpg"));
        this.zones.add(new Zone("Ruelle OUEST (Nord)", "SalleAManger.jpg"));
        this.zones.add(new Zone("Ruelle OUEST (Sud)", "SalleAManger.jpg"));
        this.zones.add(new Zone("Marina", "SalleAManger.jpg"));
        this.zones.add(new Zone("Hotel", "SalleAManger.jpg"));
        this.zones.add(new Zone("Bar", "SalleAManger.jpg"));
        this.zones.add(new Zone("Maison", "Maison.jpg"));
        this.zones.add(new Zone("Ruelle EST (Nord)", "GrandeSalle.jpg" ));
        
        
        // Zone Ruelle de départ
        this.zones.get(0).ajouteSortie(Sortie.EST, zones.get(1));
        this.zones.get(0).ajouteSortie(Sortie.OUEST, zones.get(9));
        
        // Zone Metro
        this.zones.get(1).ajouteSortie(Sortie.OUEST, zones.get(0));
        this.zones.get(1).ajouteSortie(Sortie.EST, zones.get(13));
        this.zones.get(1).ajouteSortie(Sortie.METRO, zones.get(5));
        this.zones.get(1).ajouteSortie(Sortie.NORD, zones.get(2));
        
        // Zone Maison
        this.zones.get(13).ajouteSortie(Sortie.OUEST, zones.get(1));
        
        // Zone Ruelle EST (Sud)
        this.zones.get(2).ajouteSortie(Sortie.SUD, zones.get(1));
        this.zones.get(2).ajouteSortie(Sortie.NORD, zones.get(14));
        this.zones.get(2).ajouteSortie(Sortie.EST, zones.get(4));
        
        // Zone Ruelle EST (Nord)
        this.zones.get(14).ajouteSortie(Sortie.SUD, zones.get(2));
        this.zones.get(14).ajouteSortie(Sortie.EST, zones.get(3));
        this.zones.get(14).ajouteSortie(Sortie.OUEST, zones.get(7));
        this.zones.get(14).ajouteSortie(Sortie.NORD, zones.get(5));
        
        // Zone Armurerie && Station Essence
        this.zones.get(4).ajouteSortie(Sortie.OUEST, zones.get(2));
        this.zones.get(3).ajouteSortie(Sortie.OUEST, zones.get(2));
        
        // Zone Entrée Aéroport
        this.zones.get(5).ajouteSortie(Sortie.SUD, zones.get(2));
        this.zones.get(5).ajouteSortie(Sortie.OUEST, zones.get(7));
        this.zones.get(5).ajouteSortie(Sortie.NORD, zones.get(6));
        
        // Zone supermarché
        this.zones.get(7).ajouteSortie(Sortie.EST, zones.get(2));
        this.zones.get(7).ajouteSortie(Sortie.OUEST, zones.get(8));
        
        // Zone Ruelle OUEST (Nord)
        this.zones.get(8).ajouteSortie(Sortie.EST, zones.get(7));
        this.zones.get(8).ajouteSortie(Sortie.OUEST, zones.get(10));
        this.zones.get(8).ajouteSortie(Sortie.SUD, zones.get(9));
        
        //Zone Ruelle OUEST (Sud)
        this.zones.get(9).ajouteSortie(Sortie.NORD, zones.get(8));
        this.zones.get(9).ajouteSortie(Sortie.OUEST, zones.get(11));
        this.zones.get(9).ajouteSortie(Sortie.SUD, zones.get(12));
        
        
        // Zone Bar
        this.zones.get(12).ajouteSortie(Sortie.EST, zones.get(9));
        
        // Zone Hotel
        this.zones.get(11).ajouteSortie(Sortie.EST, zones.get(9));
        
        // Marina
        this.zones.get(10).ajouteSortie(Sortie.EST, zones.get(8));
        
        // Piste Aéroport
        this.zones.get(6).ajouteSortie(Sortie.SUD, zones.get(5));
        
        this.zoneCourante = zones.get(0);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
