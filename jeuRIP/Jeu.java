package jeuRIP;

import jeuRIP.elementsGraphiques.JeuPanel;


import java.util.HashMap;
import jeuRIP.Entites.*;

public class Jeu {
	
	
	public Zone zoneCourante;
	Zone[] zones;
	Fenetre fenetre  ; 
	JeuPanel jeuPanel ;
	public HashMap<String, Item> tableItems ;
	public HashMap<String, PersoNonJoueur> tablePNJ;
	public HashMap<String, Item> inventaireItems; // par kh 15/03
	
	
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
		//afficherItemZC(zoneCourante); // affichage item 1
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
        this.zones[6].ajouteItems(1, tableItems.get("Pills"));
        this.zones[6].ajouteItems(2, tableItems.get("Jerrican"));
        this.zones[12].ajouteItems(0, tableItems.get("Hache")); // attention changement de zone pour la hache qui est dans station essence
        this.zones[8].ajouteItems(0, tableItems.get("Portable"));
        this.zones[10].ajouteItems(0, tableItems.get("Gun"));
        this.zones[13].ajouteItems(0, tableItems.get("Parachute"));
			 
			 this.créerPNJ();
			 this.zones[1].ajoutePNJ(tablePNJ.get("Fille"));
			 this.zones[2].ajoutePNJ(tablePNJ.get("Capitaine"));
			 this.zones[13].ajoutePNJ(tablePNJ.get("VeterantGuerre"));
			 this.zones[14].ajoutePNJ(tablePNJ.get("Pilote"));
			 this.zones[6].ajoutePNJ(tablePNJ.get("Zombie"));
      
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
				//		this.etatJeu(zoneCourante);
	        	jeuPanel.afficherImgZone(zoneCourante.getNomImage());
	        	jeuPanel.afficherItemZC(zoneCourante); // affichage items
	       		jeuPanel.afficherPNJ(this.zoneCourante.getPNJ());
	        	
	        }
			}
			
/*
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
								//this.zones[7].enleveSortie("SUD", this.zones[13]);
							}
						}
						jeuPanel.afficherDialoguePNJ(fille.getInitDialogue());
					} else {
						// Dans ce IF Inti True && Done False
						if(!(fille.getDoneQuete())) {
							jeuPanel.afficherDialoguePNJ(fille.getWaitDialogue());
						} else {
							jeuPanel.afficherDialoguePNJ(fille.getDoneDialogue());	
						}
					}
					
					break;
					// ------------------------------------------------------------------
					case "Métro" :
					if(!fille.getInitQuete() && (this.zones[7].obtientSortie("SUD") != null)) {
						this.zones[7].enleveSortie("NORD", this.zones[9]);
					}
					break;


					//-------------------------------------------------------------------
					case "Entrée Aéroport" :
					if(this.zones[7].obtientSortie("SUD") != null) {
						this.zones[7].enleveSortie("SUD",this.zones[13]);
						if(this.zones[7].obtientSortie("NORD") == null) {
							this.zones[7].ajouteSortie(Sortie.NORD, this.zones[9]);
						} 
					}
					// Des que l'on atteint cette zone après qu'elle soit bloquée
					// Il faut avoir la pince pour débloquée la zone 
					if(this.zones[13].obtientSortie("NORD") == null && this.tableItems.get("Pince").getEtatItem()) {
						this.zones[13].ajouteSortie(Sortie.NORD, this.zones[14]);
						afficherDialoguePNJ("L'entrée vers l'aéroport est bloquée ! Il faut briser ces chaînes...");
					}

					if(this.zones[13].obtientSortie("SUD") == null && pilote.getInitQuete()) {
						this.zones[13].ajouteSortie(Sortie.SUD, this.zones[11]);
					}

					break;

					//-------------------------------------------------------------------
					case "Hotel" :
					if(!capitaine.getDoneQuete() && !fille.getDoneQuete()) {
						jeuPanel.afficherDialoguePNJ("Je n'ai rien à faire ici.....");
					} else {
						Item Cle = new Item("Cle","","",null);
						this.tableItems.put("Cle", Cle);
						jeuPanel.afficherDialoguePNJ("Voici la clé !");
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
					case "Marina" :
					if() {

					}
					break;
					//------------------------------------------------------------------
					// Arrivée au Bar déclenche la quete du capitaine..
					// On réutilise le même principe que pour la fille du capitaine...

					case "Bar" :
					if(!(capitaine.getInitQuete())) {
						capitaine.setInitQuete(true);
						afficherDialoguePNJ(capitaine.getInitDialogue());
					} else {
						if(!capitaine.getDoneQuete()) {
							afficherDialoguePNJ(capitaine.getWaitDialogue());	
						} else {
							afficherDialoguePNJ(capitaine.getDoneDialogue());
						}
					}
					break; 

					
					//-------------------------------------------------------------------
					default:
					break;
				}
			}
*/
	public void afficherDialoguePNJ(String dialoguePNJ) {
		// Méthode qui va invoquer une methode de jeuPanel pour afficher le bon dialogue...
		jeuPanel.afficherDialogue(dialoguePNJ);
	}
	
	// par kh :  utiliser item  à completer ......
	public void  utiliserItem (Item item) {
		 switch (item.getNomItem()) {
		 
		 case "Hache":
			
			 break;
		 case "Gun" :
			 break;
		 case "Pince" :
			 break;
		 case  "Bouteille":
			 break;
		 case "Jerrican" :
			 // test 
			 if(this.zoneCourante.getDescription()== "Ruelle de Départ") { 
				 System.out.println("avant supr : nb item = "+inventaireItems.size());
				 System.out.println("Jerrican utilisé dans zone Départ ...");
				 this.inventaireItems.remove("Jerrican");
				 System.out.println("apres supr : nb item = "+inventaireItems.size());
			 }
			 break;
		 case  "Parachute":
			 break;
		 case "Pills" :
			 break;
		 case "Portable" :
			 break;
		 default: 
	            break;
		 }	 
	}
	
	public void seDeplacer(String direction) {
	    	
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
			
			this.inventaireItems = new HashMap<String, Item>() ;
			this.tableItems = new HashMap<String, Item>();
			Item Hache = new Item("Hache","hache.png","Ceci est une hache",9);
			Hache.setPosition(100, 100);
			Hache.setSize(100, 100);
			tableItems.put("Hache", Hache);
			
			Item Gun = new Item("Gun","gun.png","Ceci est un gun",6);
			Gun.setPosition(100, 100);
			Gun.setSize(100, 100);
			tableItems.put("Gun", Gun);
			
			Item Pince = new Item("Pince","pince.jpg","Ceci est une pince",13);
			Pince.setPosition(100, 100);
			Pince.setSize(100, 100);
			tableItems.put("Pince", Pince);
			
			Item Bouteille = new Item("Bouteille","bouteille.jpg","Ceci est une bouteille",6);
			Bouteille.setPosition(100, 100);
			Bouteille.setSize(100, 100);
			tableItems.put("Bouteille", Bouteille);
			
			Item Jerrican = new Item("Jerrican","jerrican.png","Ceci est un jerrican",12);
			Jerrican.setPosition(300, 100);
			Jerrican.setSize(100, 100);
			tableItems.put("Jerrican", Jerrican);
			
			Item Parachute = new Item("Parachute","parachute.png","Ceci est un parachute",14);
			Parachute.setPosition(100, 100);
			Parachute.setSize(100, 100);
			tableItems.put("Parachute",Parachute);
			
			Item Pills = new Item("Pills","pills.jpg","Ceci est un pills",2);
			Pills.setPosition(200, 100);
			Pills.setSize(100, 100);
			tableItems.put("Pills",Pills);
			
			Item Portable = new Item("Portable","portable.jpg","Ceci est un portable",1);
			Portable.setPosition(100, 100);
			Portable.setSize(100, 100);
			tableItems.put("Portable", Portable);
		}


		public void créerPNJ() {
			this.tablePNJ = new HashMap<String, PersoNonJoueur>();
			PersoNonJoueur Fille = new PersoNonJoueur("Fille", "fille.png", "Hey toi! Oui toi! J'ai besoin de ton aide. Mon père possède un bateau pour s'enfuire mais il doit être encore bourré au bar."
					+ " J'ai oublier mon téléphone chez moi, je dois absolument appeler ma mère. Tu peux aller me le chercher? Ma maison se trouve près du métro en allant vers l'EST.",
					"Alors tu as trouvé mon téléphone?",
					"Oh merci à toi! Les clé du bateau de mon père sont dans sa chambre d'hotel, c'est la chambre n°14. Je te retrouve à la Marina",
					"fille en detresse qui se dit etre la fille du capitaine.");
			Fille.setPosition(100, 700);
			Fille.setSize(100, 100);
			tablePNJ.put("Fille",Fille);
			
			PersoNonJoueur Capitaine = new PersoNonJoueur("Capitaine", "capitaine.png", "Attention..*ich* ! Capitaine à..*ich* babord !",
					"Zzzzzzzzzz !", "Oh ma tête ! Que ce passe-t-il ici? Je dois retrouver ma fille. "
							+ "J'ai laisser mes clés de bateau dans ma chambre d'hotel, prends les et rejoins moi a la Marina, c'est la chambre n°14",
					"Capitaine d'un bateau qui semble avoir un penchant pour l'alcool.");
			Capitaine.setPosition(100, 700);
			Capitaine.setSize(100, 100);
			tablePNJ.put("Capiataine",Capitaine);
			
			PersoNonJoueur VeteranGuerre = new PersoNonJoueur("Veteran de guerre", "veteran.png", "Hey vous! Par ici!", 
					"", 
					"Soldat l'ennemie a franchie nos frontière! Nous allons devoir frapper par les airs. Prenez ce parachute et embarquez dans le cargo C201-13 avec votre escoude. Exécution soldat!",
					"Un étrange personnage qui semble avoir des séquels de la guerre.");
			VeteranGuerre.setPosition(100, 700);
			VeteranGuerre.setSize(100, 100);
			tablePNJ.put("Veteran de guerre",VeteranGuerre);
			
			PersoNonJoueur Pilote = new PersoNonJoueur("Pilote", "pilote.png", "Tous les avions sont partis, il ne reste que ce tas de féraille. Il est a court d'essence."
					+ " Ramenez nous de quoi faire le plein et on décolera d'ici avant qe les zombies nous attrapent", "On est a court d'essence", 
					"",
					"Pilote d'avion qui peut m'aider à quitter cette île.");
			Pilote.setPosition(100, 700);
			Pilote.setSize(100, 100);
			tablePNJ.put("Pilote",Pilote);
			
			PersoNonJoueur Zombie = new PersoNonJoueur("Zombie", "zombie.png", "",
					"", 
					"",
					"");
			Zombie.setPosition(100, 700);
			Zombie.setSize(100, 100);
			tablePNJ.put("Zombie",Zombie);
		}

}
