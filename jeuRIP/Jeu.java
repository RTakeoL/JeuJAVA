package jeuRIP;

import jeuRIP.Entites.Zone;
import jeuRIP.elementsGraphiques.JeuPanel;
import jeuRIP.Entites.Sortie;
import java.util.HashMap;
import jeuRIP.Entites.*;

public class Jeu {
	
	
	private Zone zoneCourante;
	Zone[] zones;
	Fenetre fenetre  ; 
	JeuPanel jeuPanel ;
	public HashMap<String, Item> tableItems ;
	public HashMap<String, PersoNonJoueur> tablePNJ;
	
	
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
		//afficherItemZC(zoneCourante, 0); // affichage item 1
	}
	private void creerCarte() {
        this.zones = new Zone[15];
        // partie de gauche (marina)
        this.zones[0] = new Zone("Ruelle de Départ", "ZONE0.png" );
        this.zones[1] = new Zone("Ruelle OUEST (Sud)", "ZONE1.png");
        this.zones[2] = new Zone("Bar", "ZONE2.png");
        this.zones[3] = new Zone("Hotel", "ZONE3.png");
        this.zones[4] = new Zone("Ruelle OUEST (Nord)", "ZONE4.png");
        this.zones[5] = new Zone("Marina", "ZONE5.png");
        this.zones[6] = new Zone("Supermarché", "ZONE6.png");
        
        //partie de droite (aéroport)
        this.zones[7] = new Zone("Métro", "ZONE7.png" );
        this.zones[8] = new Zone("Maison", "ZONE8.png");
        this.zones[9] = new Zone("Ruelle EST (Sud)", "ZONE9.png" ); 
        this.zones[10] = new Zone("Armurerie", "ZONE10.png");
         this.zones[11] = new Zone("Ruelle EST (Nord)", "ZONE11.png" );
        this.zones[12] = new Zone("Station Essence", "ZONE12.png");
        this.zones[13] = new Zone("Entrée Aéroport", "ZONE13.png");
        this.zones[14] = new Zone("Piste Aéroport", "ZONE14.png");
       
        this.creerItem();
        this.zones[2].ajouteItems(0, tableItems.get("Bouteille"));
        this.zones[6].ajouteItems(0, tableItems.get("Pince"));
        //this.zones[6].ajouteItems(2, tableItems.get("Pills"));
        //this.zones[6].ajouteItems(1, tableItems.get("Jerrican"));
        this.zones[8].ajouteItems(0, tableItems.get("Hache"));
        // this.zones[8].ajouteItems(0, tableItems.get("Portable"));
        this.zones[10].ajouteItems(0, tableItems.get("Gun"));
       //this.zones[13].ajouteItems(1, tableItems.get("Parachute"));
			 
			 this.créerPNJ();
			 this.zones[1].ajoutePNJ(tablePNJ.get("Fille"));
      
        // Zone Ruelle de départ
        this.zones[0].ajouteSortie(Sortie.EST, zones[7]);
        this.zones[0].ajouteSortie(Sortie.OUEST, zones[1]); 
        
        //Zone Ruelle OUEST (Sud)
        this.zones[1].ajouteSortie(Sortie.NORD, zones[4]);
        this.zones[1].ajouteSortie(Sortie.OUEST, zones[3]);
        this.zones[1].ajouteSortie(Sortie.SUD, zones[2]);
        this.zones[1].ajouteSortie(Sortie.EST, zones[0]);
        
          // Zone Bar
        this.zones[2].ajouteSortie(Sortie.NORD, zones[1]);
        
        // Zone Hotel
        this.zones[3].ajouteSortie(Sortie.EST, zones[1]);
        
        // Zone Ruelle OUEST (Nord)
        this.zones[4].ajouteSortie(Sortie.EST, zones[6]);
        this.zones[4].ajouteSortie(Sortie.NORD, zones[5]);
        this.zones[4].ajouteSortie(Sortie.SUD, zones[1]);
       
        // Marina
        this.zones[5].ajouteSortie(Sortie.SUD, zones[4]);
         
        // Zone supermarché
        this.zones[6].ajouteSortie(Sortie.EST, zones[11]);
        this.zones[6].ajouteSortie(Sortie.OUEST, zones[4]);
        
          // Zone Metro
        this.zones[7].ajouteSortie(Sortie.OUEST, zones[0]);
       //  this.zones[7].ajouteSortie(Sortie.EST, zones[8]);
        this.zones[7].ajouteSortie(Sortie.SUD, zones[13]);
        this.zones[7].ajouteSortie(Sortie.NORD, zones[9]);
        
        // Zone Maison
        this.zones[8].ajouteSortie(Sortie.OUEST, zones[7]);
        
        // Zone Ruelle EST (Sud)
        this.zones[9].ajouteSortie(Sortie.SUD, zones[7]);
        this.zones[9].ajouteSortie(Sortie.NORD, zones[11]);
        this.zones[9].ajouteSortie(Sortie.EST, zones[10]);
       
        // Zone Ruelle EST (Nord)
        this.zones[11].ajouteSortie(Sortie.SUD, zones[9]);
        this.zones[11].ajouteSortie(Sortie.EST, zones[12]);
        this.zones[11].ajouteSortie(Sortie.OUEST, zones[6]);
        this.zones[11].ajouteSortie(Sortie.NORD, zones[13]);
   
        
        // Zone Armurerie && Station Essence
        this.zones[10].ajouteSortie(Sortie.OUEST, zones[9]);
        this.zones[12].ajouteSortie(Sortie.OUEST, zones[11]);
        
        // Zone Entrée Aéroport
        this.zones[13].ajouteSortie(Sortie.SUD, zones[11]);
        this.zones[13].ajouteSortie(Sortie.NORD, zones[14]);
       
        // Piste Aéroport
        this.zones[14].ajouteSortie(Sortie.SUD, zones[13]);
        
        this.zoneCourante = zones[0]; 
	}

	 private void goTo(String direction) {
	    	Zone nouvelle = zoneCourante.obtientSortie( direction);
	    	if ( nouvelle == null ) {
	    		jeuPanel.afficher( "Pas de sortie " + direction);
	    		
	    	}
	        else {
	        	this.zoneCourante = nouvelle;
						this.etatJeu(zoneCourante);
	        	jeuPanel.afficherImgZone(zoneCourante.getNomImage());
	        	afficherItemZC(zoneCourante, 0); // affichage item 1
	        }
			}
			

			private void etatJeu(Zone zoneCourante) {
				PersoNonJoueur fille = this.tablePNJ.get("Fille");
				PersoNonJoueur capitaine = this.tablePNJ.get("Capitaine");
				PersoNonJoueur veteranGuerre = this.tablePNJ.get("VeteranGuerre");
				PersoNonJoueur pilote = this.tablePNJ.get("PiloteAvion");
				PersoNonJoueur zombie = this.tablePNJ.get("Zombie");
				switch(zoneCourante.getDescription()) {
					//-------------------------------------------------------------------
					case "Ruelle OUEST (Sud)" :
					// Premier cas lorsque le Init est false
					if(!(fille.getInitQuete())) {
						fille.setInitQuete(true);
						if(this.zones[7].obtientSortie("EST") == null) {
							if(fille.getInitQuete()) {
								this.zones[7].ajouteSortie(Sortie.EST, this.zones[8]);
								this.zones[7].enleveSortie("SUD", this.zones[13]);
							}
						}
	        	jeuPanel.afficherImgZone(zoneCourante.getNomImage());
						afficherItemZC(zoneCourante, 0); // affichage item 1
						afficherDialoguePNJ(fille.getInitDialogue());
					} else {
						// Dans ce IF Inti True && Done False
						if(!(fille.getDoneQuete())) {
							jeuPanel.afficherImgZone(zoneCourante.getNomImage());
							afficherItemZC(zoneCourante, 0); // affichage item 1
							afficherDialoguePNJ(fille.getWaitDialogue());
						} else {
							jeuPanel.afficherImgZone(zoneCourante.getNomImage());
							afficherItemZC(zoneCourante, 0); // affichage item 1
							afficherDialoguePNJ(fille.getDoneDialogue());	
						}
					}
					
					break;

					//-------------------------------------------------------------------
					case "Entrée Aéroport" :
					if(this.zones[7].obtientSortie("SUD") != null) {
						this.zones[7].enleveSortie("SUD",this.zones[13]);
						jeuPanel.afficherImgZone(zoneCourante.getNomImage());
						afficherItemZC(zoneCourante, 0); // affichage item 1
					}
					// La première fois que l'on atteint cette zone, la sortie vers
					// L'aéroport est bloquée. Il faut une pince pour débloquer la zone.
					if(this.zones[13].obtientSortie("NORD") == null) {
						jeuPanel.afficherImgZone(zoneCourante.getNomImage());
						afficherItemZC(zoneCourante, 0); // affichage item 1
						afficherDialoguePNJ("L'entrée vers l'aéroport est bloquée ! Il faut briser ces chaînes...");
					}
					break;

					//-------------------------------------------------------------------
					case "Hotel" :
					if(capitaine.getDoneQuete()) {
						if(fille.getDoneQuete()) {
							// On ramasse la clé et cela signifie que la bonne fin est débloquée
						} else {
							// On as pas accomplit la quete de fille....
						}
					} else {
						if(fille.getDoneQuete()) {
							// On as pas encore accomplit la quete du capitaine...
						} else {
							// On as accomplit aucune des deux quetes et donc on affiche
							// Le dialogue qui renseigne qu'aucune action ne peut être effectuées avec cette zone.
						}
					}
					break;

					//-------------------------------------------------------------------
					// Le cas du supermarché doit être traiter pour le case des zombies...
					case "Supermarché" :
					break;
					//-------------------------------------------------------------------
					// Le cas de la station essence où l'on n'as pas le bidon d'essence pour le remplir...
					case "Station Essence" :
					break;
					
					//-------------------------------------------------------------------
					
					// Arrivée au Bar déclenche la quete du capitaine..
					// On réutilise le même principe que pour la fille du capitaine...

					case "Bar" :
					if(!(capitaine.getInitQuete())) {
						capitaine.setInitQuete(true);
						jeuPanel.afficherImgZone(zoneCourante.getNomImage());
						afficherItemZC(zoneCourante, 0); // affichage item 1
						afficherDialoguePNJ(capitaine.getInitDialogue());
					} else {
						if(!capitaine.getDoneQuete()) {
							jeuPanel.afficherImgZone(zoneCourante.getNomImage());
							afficherItemZC(zoneCourante, 0); // affichage item 1
							afficherDialoguePNJ(capitaine.getWaitDialogue());	
						}
					}
					break; 


					
					//-------------------------------------------------------------------
					default:
					break;
				}
			}

			public void afficherDialoguePNJ(String dialoguePNJ) {

			}

//public void initZC() {
//	afficherItemZC(zoneCourante, 0); // affichage item 1
//	afficherZC();
//}
//
//private void afficherZC() {
//	jeuPanel.afficherImgZone(zoneCourante.getNomImage());
//
//}
	 // pour afficher un item dans la zone 
public void afficherItemZC(Zone zc , int indexItem) {
	// check si liste items non vide
	if(zc.listItemZone.size() > 0) {	
		if(zc.getItem(indexItem) != null) {
				
				// récuperer l'item avec l'index depuis la liste items zone courante
				Item item = zc.getItem(indexItem);
				String imgItem = item.getImage();
				int X = item.getItemX();
				int Y = item.getItemY();
				int W = item.getItemPxW();
				int H = item.getItemPxH();
				// afficher item dans l'emplacement prévu
				jeuPanel.afficherItem(indexItem, imgItem, X, Y, W, H);
		}	
	}	else {
		// si vide initialiser tous les items
		jeuPanel.initAllItems();
	}
} 
	public void ramasserItem(String nomItem) {
//		jeuPanel.suprimmerItem1(nomItem);
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
		public void creerItem() {
			this.tableItems = new HashMap<String, Item>();
			Item Hache = new Item("Hache","hache.png","Ceci est une hache");
			Hache.setPosition(100, 100);
			Hache.setSize(100, 100);
			tableItems.put("Hache", Hache);
			
			Item Gun = new Item("Gun","gun.png","Ceci est un gun");
			Gun.setPosition(100, 100);
			Gun.setSize(100, 100);
			tableItems.put("Gun", Gun);
			
			Item Pince = new Item("Pince","pince.jpg","Ceci est une pince");
			Pince.setPosition(100, 100);
			Pince.setSize(100, 100);
			tableItems.put("Pince", Pince);
			
			Item Bouteille = new Item("Bouteille","bouteille.jpg","Ceci est une bouteille");
			Bouteille.setPosition(100, 100);
			Bouteille.setSize(100, 100);
			tableItems.put("Bouteille", Bouteille);
			
			Item Jerrican = new Item("Jerrican","jerrican.png","Ceci est un jerrican");
			Jerrican.setPosition(100, 100);
			Jerrican.setSize(100, 100);
			tableItems.put("Jerrican", Jerrican);
			
			Item Parachute = new Item("Parachute","parachute.png","Ceci est un parachute");
			Parachute.setPosition(100, 100);
			Parachute.setSize(100, 100);
			tableItems.put("Parachute",Parachute);
			
			Item Pills = new Item("Pills","pills.png","Ceci est un pills");
			Pills.setPosition(100, 100);
			Pills.setSize(100, 100);
			tableItems.put("Pills",Pills);
			
			Item Portable = new Item("Portable","portable.jpg","Ceci est un portable");
			Portable.setPosition(100, 100);
			Portable.setSize(100, 100);
			tableItems.put("Portable", Portable);
		}


		public void créerPNJ() {
			this.tablePNJ = new HashMap<String, PersoNonJoueur>();
			PersoNonJoueur Fille = new PersoNonJoueur("Fille", "fille.png", "Salut tu peux me chercher mon portable", "alors tu y vas", "Merci bien", "fille en detresse qui se dit etre la fille du capitaine");
			Fille.setPosition(100, 700);
			Fille.setSize(100, 100);
			tablePNJ.put("Fille",Fille);
		}

}
