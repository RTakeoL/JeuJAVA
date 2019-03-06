package jeuRIP;


import jeuRIP.Entites.Zone;
import jeuRIP.elementsGraphiques.JeuPanel;
import jeuRIP.Entites.Sortie;
import java.util.HashMap;
import jeuRIP.Entites.*;
import jeuRIP.Item;

public class Jeu {
	
	
	private Zone zoneCourante;
	Zone[] zones;
	Fenetre fenetre  ; 
	JeuPanel jeuPanel ;
	public HashMap<String, Item> tableItems ;
	
	
	
	
	public Jeu () {
		
		 creerCarte();
		 this.fenetre= null  ;
		
	}
	
	public void setFenetre( Fenetre fen) { 
		fenetre = fen ;
		setPanel(fenetre.getPanel());
	}
	
	public void setPanel(JeuPanel panel) {
		this.jeuPanel = panel;
		jeuPanel.afficherImgZone(zoneCourante.getNomImage());
	}
	private void creerCarte() {
        this.zones = new Zone[15];
        
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

	
	
	
	
	
	
	 private void goTo(String direction) {
	    	Zone nouvelle = zoneCourante.obtientSortie( direction);
	    	if ( nouvelle == null ) {
	    		jeuPanel.afficher( "Pas de sortie " + direction);
	    		
	    	}
	        else {
	        	zoneCourante = nouvelle;
	        	jeuPanel.afficher(zoneCourante.getDescription());
	        	
	        	jeuPanel.afficherImgZone(zoneCourante.getNomImage());
	        }
	    }
	    
	
	 
	 public void seDeplacer(String direction) {
	    	// seDeplacer doit prendre en compte la sortie METRO
	        switch (direction) {
	        
	        case "NORD" :
	        	goTo( "NORD"); 
	        	break;
	       case "SUD" :
	        	goTo( "SUD"); 
	        	break;
	       case "EST" :
	        	goTo( "EST"); 
	        	break;
	       case "OUEST" :
	        	goTo( "OUEST"); 
	        	break;
	       
	       	default : 
	            
	            break;
	        }
	    }
	// remplir d'item par jb
		public void créerItem() {
			this.tableItems = new HashMap<String, Item>();
			Item Hache = new Item("Hache","/images/hache.png","Ceci est une hache");
			tableItems.put("Hache", Hache);
			Item Gun = new Item("Gun","/images/gun.png","Ceci est un gun");
			tableItems.put("Gun", Gun);
			Item Pince = new Item("Pince","/images/pince.png","Ceci est une pince");
			tableItems.put("Pince", Pince);
			Item Bouteille = new Item("Bouteille","/images/bouteille.png","Ceci est une bouteille");
			tableItems.put("Bouteille", Bouteille);
			Item Jerrican = new Item("Jerrican","/images/jerrican.png","Ceci est un jerrican");
			tableItems.put("Jerrican", Jerrican);
			Item Parachute = new Item("Parachute","/images/parachute.png","Ceci est un parachute");
			tableItems.put("Parachute",Parachute);
			Item Pills = new Item("Pills","/images/pills.png","Ceci est un pills");
			tableItems.put("Pills",Pills);
			Item Tel = new Item("Tel","/images/tel.png","Ceci est un tel");
			tableItems.put("Tel", Tel);
		}
}
