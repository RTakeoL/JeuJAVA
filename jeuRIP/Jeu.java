package jeuRIP;

import jeuRIP.Entites.Zone;
import jeuRIP.elementsGraphiques.JeuPanel;
import jeuRIP.Entites.Sortie;
import java.util.HashMap;
import jeuRIP.Entites.*;

public class Jeu {
	
	
	private Zone zoneCourante;
	protected Zone[] zones;
	protected Fenetre fenetre  ; 
	protected JeuPanel jeuPanel ;
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
        
        /**
         * Certaines zones n'ont pas toutes les sorties de disponibles au début du jeu. 
         * Ces sorties doivent être débloquer par le joueur au fil du jeu.
         */
        
        this.zones[0] = new Zone("Ruelle de Départ", "z1.png" );
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
//        this.zones[1].ajouteSortie(Sortie.EST, zones[13]);
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
        this.zones[7].ajouteSortie(Sortie.EST, zones[14]);
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
        
        // Ajout items par zone
        this.créerItem();
        this.zones[5].ajouteItems("Hache", tableItems.get("Hache"));
        this.zones[4].ajouteItems("Gun", tableItems.get("Gun"));
        this.zones[7].ajouteItems("Pills", tableItems.get("Pills"));
        this.zones[12].ajouteItems("Bouteille", tableItems.get("Bouteille"));
        this.zones[5].ajouteItems("Parachute", tableItems.get("Parachute"));
        this.zones[13].ajouteItems("Portable", tableItems.get("Portable"));
        this.zones[7].ajouteItems("Pince", tableItems.get("Pince"));
        this.zones[7].ajouteItems("Jerrican", tableItems.get("Jerrican"));
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
			Item Portable = new Item("Portable","/images/portable.jpg","Ceci est un portable");
			tableItems.put("Portable", Portable);
		}
		
		/**
		 * 
		 * @param indexZone, index de la zone qui donne sur la zone à débloquer
		 * @param directionSortie, la direction de la sortie à ajouter à la zone identifier avec indexZone
		 * @param indexZoneSortie, quelle zone on ajoute avec la directionSortie
		 */
	public void debloqueSortie(Integer indexZone, Sortie directionSortie, Integer indexZoneSortie) {
		String nomSortie = directionSortie.toString();
		if(this.zones[indexZone].obtientSortie(nomSortie) == null) {
			this.zones[indexZone].ajouteSortie(directionSortie, this.zones[indexZoneSortie]);	
		}
	}
	
	public void bloqueSortie(Integer indexZone, Sortie directionSortie,Integer indexZoneSortie) {
		String nomSortie = directionSortie.toString();
		if(this.zones[indexZone].obtientSortie(nomSortie) != null) {
			this.zones[indexZone].enleveSortie(directionSortie, this.zones[indexZoneSortie]);	
		}
	}
	
}
