/**
 * 
 */
package ownProject;
/**
 * @author Rld-Comp
 *
 */
public class Jeu {

	/**
	 * @param args
	 */
	private Zone zoneCourante;
	public Zone[] zones; // private parce que fenetre est instancie dans jeu
	
	
	public void creerCarte() {
        this.zones = new Zone[14];
        
        this.zones[0] = new Zone("Ruelle de Départ", "../img/Couloir.jpg" );
        this.zones[1] = new Zone("Métro", "Escalier.jpg" );
        this.zones[2] = new Zone("Ruelle EST (Sud)", "GrandeSalle.jpg" );
        this.zones[3] = new Zone("Station Essence", "SalleAManger.jpg");
        this.zones[4] = new Zone("Armurerie", "SalleAManger.jpg");
        this.zones[5] = new Zone("Entrée Aéroport", "SalleAManger.jpg");
        this.zones[6] = new Zone("Piste Aéroport", "SalleAManger.jpg");
        this.zones[7] = new Zone("Supermarché", "SalleAManger.jpg");
        this.zones[8] = new Zone("Ruelle OUEST (Nord)", "SalleAManger.jpg");
        this.zones[9] = new Zone("Ruelle OUEST (Sud)", "SalleAManger.jpg");
        this.zones[10] = new Zone("Marina", "SalleAManger.jpg");
        this.zones[11] = new Zone("Hotel", "SalleAManger.jpg");
        this.zones[12] = new Zone("Bar", "SalleAManger.jpg");
        this.zones[13] = new Zone("Maison", "Maison.jpg");
        this.zones[14] = new Zone("Ruelle EST (Nord)", "GrandeSalle.jpg" );
        
        
        // Zone Ruelle de départ
        this.zones[0].ajouteSortie(Sortie.EST, zones[1]);
        this.zones[0].ajouteSortie(Sortie.OUEST, zones[9]);
        
        // Zone Metro
        this.zones[1].ajouteSortie(Sortie.OUEST, zones[0]);
        this.zones[1].ajouteSortie(Sortie.EST, zones[13]);
        this.zones[1].ajouteSortie(Sortie.METRO, zones[5]);
        this.zones[1].ajouteSortie(Sortie.NORD, zones[2]);
        
        // Zone Maison
        this.zones[13].ajouteSortie(Sortie.OUEST, zones[1]);
        
        // Zone Ruelle EST (Sud)
        this.zones[2].ajouteSortie(Sortie.SUD, zones[1]);
        this.zones[2].ajouteSortie(Sortie.NORD, zones[14]);
        this.zones[2].ajouteSortie(Sortie.EST, zones[4]);
        
        // Zone Ruelle EST (Nord)
        this.zones[14].ajouteSortie(Sortie.SUD, zones[2]);
        this.zones[14].ajouteSortie(Sortie.EST, zones[3]);
        this.zones[14].ajouteSortie(Sortie.OUEST, zones[7]);
        this.zones[14].ajouteSortie(Sortie.NORD, zones[5]);
        
        // Zone Armurerie && Station Essence
        this.zones[4].ajouteSortie(Sortie.OUEST, zones[2]);
        this.zones[3].ajouteSortie(Sortie.OUEST, zones[2]);
        
        // Zone Entrée Aéroport
        this.zones[5].ajouteSortie(Sortie.SUD, zones[2]);
        this.zones[5].ajouteSortie(Sortie.OUEST, zones[7]);
        this.zones[5].ajouteSortie(Sortie.NORD, zones[6]);
        
        // Zone supermarché
        this.zones[7].ajouteSortie(Sortie.EST, zones[2]);
        this.zones[7].ajouteSortie(Sortie.OUEST, zones[8]);
        
        // Zone Ruelle OUEST (Nord)
        this.zones[8].ajouteSortie(Sortie.EST, zones[7]);
        this.zones[8].ajouteSortie(Sortie.OUEST, zones[10]);
        this.zones[8].ajouteSortie(Sortie.SUD, zones[9]);
        
        //Zone Ruelle OUEST (Sud)
        this.zones[9].ajouteSortie(Sortie.NORD, zones[8]);
        this.zones[9].ajouteSortie(Sortie.OUEST, zones[11]);
        this.zones[9].ajouteSortie(Sortie.SUD, zones[12]);
        
        
        // Zone Bar
        this.zones[12].ajouteSortie(Sortie.EST, zones[9]);
        
        // Zone Hotel
        this.zones[11].ajouteSortie(Sortie.EST, zones[9]);
        
        // Marina
        this.zones[10].ajouteSortie(Sortie.EST, zones[8]);
        
        // Piste Aéroport
        this.zones[6].ajouteSortie(Sortie.SUD, zones[5]);
        
        this.zoneCourante = zones[0];
    }
	
	
	
	public void remplirZone() {
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
