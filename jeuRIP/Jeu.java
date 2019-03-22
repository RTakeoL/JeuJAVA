package jeuRIP;

import jeuRIP.elementsGraphiques.JeuPanel;



import java.util.*;

import jeuRIP.Entites.*;

public class Jeu {
	
	
	public Zone zoneCourante;
	Zone[] zones;
	Fenetre fenetre  ; 
	JeuPanel jeuPanel ;
	public HashMap<String, Item> tableItems ;
	public HashMap<String, PersoNonJoueur> tablePNJ;
	public HashMap<String, Item> inventaireItems; // par kh 15/03
	private MapZone mapJeu;

	// Propriété cheminFin qui permets de savoir quel chemin à été pris:
	// True => Marina
	// False => Aéroport
	private Boolean cheminFinMarina = false;
	
	
	public Jeu () {
		// Création des zones et sorties....
				 this.creerCarte();
				 
				 // Création des sorties
				 this.initSortieZones();
				 // Création de la Map une fois les zone créer
				 this.creerMap();
				// Création d'items pour le jeu.....
				 this.creerItem();	
				 // Association item <-> Zone
				 this.associerItemZone();
				 
				 // Création des PNJ
				 this.creerPNJ();
				 
				 // Association des PNJ <-> Zone
				 this.associerPNJZone();
				 
				 this.fenetre= null;
	}
	
	public void setFenetre( Fenetre fen) { 
		fenetre = fen ;
		setPanel(fenetre.getPanel());
	}
	
	public void setPanel(JeuPanel panel) {
		this.jeuPanel = panel;
		jeuPanel.afficherImgZone(zoneCourante.getNomImage());
		jeuPanel.checkSorties(this.zoneCourante);
		jeuPanel.setImageMap(this.mapJeu.getMap(this.zoneCourante.getDescription()));
		//afficherItemZC(zoneCourante); // affichage item 1
	}
	private void creerCarte() {
        this.zones = new Zone[18];
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
			 
		// Partie fin de jeu....
		this.zones[15] = new Zone("Fin", "");// Pas besoin d'image pour la zone fin car on affiche en image soit la bonne soit la mauvaise fin
		this.zones[16] = new Zone("Bonne fin", "ZONE16.png");
		this.zones[17] = new Zone("Mauvaise fin", "ZONE17.png");

		this.zoneCourante = zones[0]; 
	}
	private void initSortieZones() {
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
       //  this.zones[7].ajouteSortie(Sortie.EST, zones[8]);  	apres init fille
        this.zones[7].ajouteSortie(Sortie.SUD, zones[13]);		
        //this.zones[7].ajouteSortie(Sortie.NORD, zones[9]);	apres usage métro ou init fille
        
        // Zone Maison
        this.zones[8].ajouteSortie(Sortie.OUEST, zones[7]);
        
        // Zone Ruelle EST (Sud)
        this.zones[9].ajouteSortie(Sortie.SUD, zones[7]);
        this.zones[9].ajouteSortie(Sortie.NORD, zones[11]);
        //this.zones[9].ajouteSortie(Sortie.EST, zones[10]); 	apres utilisation hache
       
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
        	
	}
	

	 private void goTo(String direction) {
	    	Zone nouvelle = zoneCourante.obtientSortie( direction);
	    	if ( nouvelle == null ) {
	    		jeuPanel.afficher( "Pas de sortie " + direction);
	    		
	    	}
	        else {
	        	this.zoneCourante = nouvelle;
				this.etatJeu(zoneCourante);
			    jeuPanel.checkSorties(this.zoneCourante); // par kh 19/03 pour dactiver btn sortie si il exist pas
	        	jeuPanel.afficherImgZone(zoneCourante.getNomImage());
	        	jeuPanel.afficherItemZC(zoneCourante); // affichage items
	       		jeuPanel.afficherPNJ(this.zoneCourante.getPNJZone());
	        	jeuPanel.setImageMap(this.mapJeu.getMap(this.zoneCourante.getDescription()));
	        	
	        	System.out.println(" test .........: "+this.zoneCourante.listItemZone.size());
	        }
	}


			private void etatJeu(Zone zoneCourante) {
				PersoNonJoueur fille = this.tablePNJ.get("Fille");
				PersoNonJoueur capitaine = this.tablePNJ.get("Capitaine");
				PersoNonJoueur veteranGuerre = this.tablePNJ.get("Veteran de guerre");
				PersoNonJoueur pilote = this.tablePNJ.get("Pilote");
				PersoNonJoueur zombie = this.tablePNJ.get("Zombie");
				
				
				switch(this.zoneCourante.getDescription()) {
					//-------------------------------------------------------------------
					case "Ruelle OUEST (Sud)" :
					// Premier cas lorsque le Init est false
					if(!fille.getInitQuete()) {
						fille.setInitQuete(true);
						if(this.zones[7].obtientSortie("EST") == null) {
							this.zones[7].ajouteSortie(Sortie.EST, this.zones[8]);	
						}
						if(this.zones[7].obtientSortie("SUD") != null) {
							this.zones[7].enleveSortie("SUD", this.zones[13]);
						}
						if(this.zones[7].obtientSortie("NORD") == null) {
							this.zones[7].ajouteSortie(Sortie.NORD, this.zones[9]);
						}
						jeuPanel.afficherDialoguePNJ(fille.getInitDialogue(),fille.getImage());
					} else {
						// Dans ce IF Inti True && Done False
						if(!fille.getDoneQuete()) {
							jeuPanel.afficherDialoguePNJ(fille.getWaitDialogue(),fille.getImage());
						} else {
							//jeuPanel.afficherDialoguePNJ(fille.getDoneDialogue(),fille.getImage());	que quand on donne le portable
						}
					}
					break;
					case "Métro" :
						if(this.zones[7].obtientSortie("SUD") != null) {
							jeuPanel.afficherPensee("**Dernier train vers l'aéroport ! Départ iminant !**");
						}
						break;

					//-------------------------------------------------------------------
					case "Entrée Aéroport" :
					if(this.zones[7].obtientSortie("NORD") == null) {
							this.zones[7].ajouteSortie(Sortie.NORD, this.zones[9]);
						}
					if(this.zones[7].obtientSortie("SUD") != null) {
					this.zones[7].enleveSortie("SUD",this.zones[13]);
						
					}
					// La première fois que l'on atteint cette zone, la sortie vers
					// L'aéroport est bloquée. Il faut une pince pour débloquer la zone.
					if(this.zones[13].obtientSortie("NORD") == null) {
						
						jeuPanel.afficherPensee("L'entrée vers l'aéroport est bloquée ! Il faut briser ces chaînes...");
					}

					if(this.zones[13].obtientSortie("SUD") == null && pilote.getInitQuete()) {
						this.zones[13].ajouteSortie(Sortie.SUD, this.zones[11]);
					}

					// si quete pilote non accepté impossible d'aller au sud
					if ( ! pilote.getInitQuete() && this.zones[13].obtientSortie("SUD") != null) {
						this.zones[13].enleveSortie("SUD", this.zones[11]);
					}
					// C'est dans cette zone que le vétéran de guerre se trouve et donne sa quete..

					if(!veteranGuerre.getInitQuete()){
						veteranGuerre.setInitQuete(true);
						jeuPanel.afficherDialoguePNJ(veteranGuerre.getInitDialogue(),veteranGuerre.getImage());
					} else {
						if(!veteranGuerre.getDoneQuete()) {
							jeuPanel.afficherDialoguePNJ(veteranGuerre.getWaitDialogue(), veteranGuerre.getImage());
						}
					}


					break;

					//-------------------------------------------------------------------
					case "Hotel" :
					if(capitaine.getDoneQuete() || fille.getDoneQuete()) {
				           // On ramasse la clé et cela signifie que la bonne fin est débloquée
						Item Cle = new Item("Cle","","Ceci est la cle du bateau",this.zones[5].getDescription());
						tableItems.put("Cle", Cle);
						jeuPanel.afficherPensee("La cle est dans la chambre n°14 ... *Vous avez obtenu la cle du bateau* ");
					}
					else {
							// On as accomplit aucune des deux quetes et donc on affiche
							// Le dialogue qui renseigne qu'aucune action ne peut être effectuées avec cette zone.
						jeuPanel.afficherPensee("C'est un bel hotel mais je n'ai pas le temps pour ça.");
						}
					break;


					//-------------------------------------------------------------------
					case "Marina" :
					if(this.zones[5].obtientSortie("NORD") == null) {
						if((capitaine.getDoneQuete() || fille.getDoneQuete()) && (this.tableItems.get("Cle") != null)) {
							this.zones[5].ajouteSortie(Sortie.NORD, this.zones[15]);
							this.cheminFinMarina = true;
							jeuPanel.afficherPensee("Je peux enfin m'enfuir de l'île..");
						}
					}
					break;


					//-------------------------------------------------------------------
					// Le cas du supermarché doit être traiter pour le case des zombies...
					case "Supermarché" :
					if(!zombie.getInitQuete()) {
						zombie.setInitQuete(true);
						jeuPanel.afficherPensee("Je ne peux passer dans le supermarché des zombies bloque l'entrée.");
					} else {
						if(!zombie.getDoneQuete()) {
							jeuPanel.afficherPensee("Les zombies ne semble pas vouloir partir... "
										+ "Il faut trouver un objet qui pourrait les faire partir.");
						} 
					}
					break;
					//-------------------------------------------------------------------
					// Le cas de la station essence où l'on n'as pas le bidon d'essence pour le remplir...
					case "Station Essence" :
					if(this.inventaireItems.get("Jerrican") == null && this.inventaireItems.get("Jerrican (Plein)")==null && pilote.getInitQuete()) {
						jeuPanel.afficherPensee("Je dois trouver un moyen de récupérer l'essence...");
					}
					break;

					// -----------------------------------------------------------
					case "Ruelle EST (Sud)" :
					if(this.zones[9].obtientSortie("EST") == null) {
						jeuPanel.afficherPensee("Le chemin vers l'armurerie est bloqué par des branche..." + 
						" Une hache pourrait m'aider à libérer le chemin.");
					}
					break;

					//------------------------------------------------------------------

					// Arrivée au Bar déclenche la quete du capitaine..
					// On réutilise le même principe que pour la fille du capitaine...

					case "Bar" :
					if(!(capitaine.getInitQuete())) {
						capitaine.setInitQuete(true);

			
						jeuPanel.afficherDialoguePNJ(capitaine.getInitDialogue(), capitaine.getImage());
					} else {
						if(!capitaine.getDoneQuete()) {
							jeuPanel.afficherDialoguePNJ(capitaine.getWaitDialogue(), capitaine.getImage());	
						} else {
							//jeuPanel.afficherDialoguePNJ(capitaine.getDoneDialogue(), capitaine.getImage());
						}
					}
					break; 
					// ----------------------------------------------
					case "Piste Aéroport" :
					if(!pilote.getInitQuete()) {
						pilote.setInitQuete(true);
						jeuPanel.afficherDialoguePNJ(pilote.getInitDialogue(),pilote.getImage());	
						this.zones[13].enleveSortie("NORD", zones[14]);
					}
					else {
						if(!pilote.getDoneQuete()) {
							jeuPanel.afficherDialoguePNJ(pilote.getWaitDialogue(),pilote.getImage());	
						}
					}
					break;
					// ----------------------------------------------------
					case "Fin" :
					if(this.cheminFinMarina) {
						if((capitaine.getDoneQuete() && fille.getDoneQuete()) && 
								(this.tableItems.get("Cle") != null)) {
							this.zoneCourante = this.zones[16]; // Débloque la bonne fin..
						} else {
							this.zoneCourante = this.zones[17]; // Débloque mauvaise fin....
						}
					} else {
						if((veteranGuerre.getDoneQuete() && pilote.getDoneQuete())) {
							this.zoneCourante = this.zones[16];
						} else {
							this.zoneCourante = this.zones[17];
						}
					}
					break;

					
					//-------------------------------------------------------------------
					default:
					break;
				}
			}

			
			
			




	public void  utiliserItem (Item item) {
		switch (item.getNomItem()) {
		case "Hache":
			this.inventaireItems.get("Hache").setEtatItem(true);
			this.inventaireItems.remove("Hache");
			if(this.zones[9].obtientSortie("EST") == null) {
				this.zones[9].ajouteSortie(Sortie.EST, this.zones[10]);
				jeuPanel.afficherPensee("L'accés a l'armurie semble dégagé...");
				jeuPanel.checkSorties(this.zoneCourante);
			}
		break;
		case "Gun" :
			this.inventaireItems.get("Gun").setEtatItem(true);
			this.inventaireItems.remove("Gun");

			this.tablePNJ.get("Zombie").setDoneQuete(true);

			jeuPanel.afficherDialoguePNJ(this.tablePNJ.get("Zombie").getDoneDialogue(), 
					this.tablePNJ.get("Zombie").getImage());
			
			this.zones[6].ajouteItems(0, tableItems.get("Pince"));
			this.zones[6].ajouteItems(2, tableItems.get("Jerrican"));
			this.zones[6].ajouteItems(1, tableItems.get("Pills"));

			jeuPanel.afficherItemZC(this.zoneCourante);
		break;
		case "Pince" :
			this.inventaireItems.get("Pince").setEtatItem(true);
			this.inventaireItems.remove("Pince");

			if(this.zones[13].obtientSortie("NORD") == null) {
				this.zones[13].ajouteSortie(Sortie.NORD, this.zones[14]);
				jeuPanel.afficherPensee("La piste est de nouveau accessible...");
			}

		break;
		case  "Bouteille":
			this.inventaireItems.get("Bouteille").setEtatItem(true);
			this.inventaireItems.remove("Bouteille");
			
			this.tablePNJ.get("Zombie").setDoneQuete(true);

			jeuPanel.afficherDialoguePNJ(this.tablePNJ.get("Zombie").getDoneDialogue(), 
					this.tablePNJ.get("Zombie").getImage());

			this.zones[6].ajouteItems(0, tableItems.get("Pince"));
			this.zones[6].ajouteItems(2, tableItems.get("Jerrican"));
			this.zones[6].ajouteItems(1, tableItems.get("Pills"));
			
			jeuPanel.afficherItemZC(this.zoneCourante);
		break;
		case "Jerrican" :
			this.inventaireItems.get("Jerrican").setEtatItem(true);
			this.inventaireItems.remove("Jerrican");

			this.zones[12].ajouteItems(0, tableItems.get("Jerrican (Plein)"));
			jeuPanel.afficherItemZC(this.zoneCourante);
		break;
		case "Pills" :
			this.inventaireItems.get("Pills").setEtatItem(true);
			this.inventaireItems.remove("Pills");

			this.tablePNJ.get("Capitaine").setDoneQuete(true);
			jeuPanel.afficherDialoguePNJ(this.tablePNJ.get("Capitaine").getDoneDialogue(),
			this.tablePNJ.get("Capitaine").getImage());
		break;
		case "Portable" :
			//if(!this.inventaireItems.get("Portable").getEtatItem()) {
				this.inventaireItems.get("Portable").setEtatItem(true);
				this.inventaireItems.remove("Portable");

				this.tablePNJ.get("Fille").setDoneQuete(true);
				jeuPanel.afficherDialoguePNJ(this.tablePNJ.get("Fille").getDoneDialogue(),
				this.tablePNJ.get("Fille").getImage());
			//}
		break;
		case "CouteauDeGuerre" :
			//if(!this.inventaireItems.get("CouteauDeGuerre").getEtatItem()) {
				this.inventaireItems.get("CouteauDeGuerre").setEtatItem(true);
				this.inventaireItems.remove("CouteauDeGuerre");

				this.tablePNJ.get("Veteran de guerre").setDoneQuete(true);
				jeuPanel.afficherDialoguePNJ(this.tablePNJ.get("Veteran de guerre").getDoneDialogue(),
				this.tablePNJ.get("Veteran de guerre").getImage());
				
				this.zones[13].ajouteItems(0, tableItems.get("Parachute"));

				jeuPanel.afficherItemZC(this.zoneCourante);
			//}
		break;
		case "Jerrican (Plein)":
			this.inventaireItems.get("Jerrican (Plein)").setEtatItem(true);
			this.inventaireItems.remove("Jerrican (Plein)");
			
			this.tablePNJ.get("Pilote").setDoneQuete(true);
			jeuPanel.afficherDialoguePNJ(this.tablePNJ.get("Pilote").getDoneDialogue(),
			this.tablePNJ.get("Pilote").getImage());
			
			this.zones[14].ajouteSortie(Sortie.NORD, this.zones[15]);
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
	 
	 
	 private void creerItem() {
			
			this.inventaireItems = new HashMap<String, Item>() ;
			this.tableItems = new HashMap<String, Item>();

			Item Hache = new Item("Hache","hache.png","Ceci est une hache",this.zones[9].getDescription());
			Hache.setPosition(100, 100);
			Hache.setSize(100, 100);
			tableItems.put("Hache", Hache);
			
			Item Gun = new Item("Gun","gun.png","Ceci est un gun",this.zones[6].getDescription());
			Gun.setPosition(100, 100);
			Gun.setSize(100, 100);
			tableItems.put("Gun", Gun);
			
			Item Pince = new Item("Pince","pince.jpg","Ceci est une pince",this.zones[13].getDescription());
			Pince.setPosition(100, 100);
			Pince.setSize(100, 100);
			tableItems.put("Pince", Pince);
			
			Item Bouteille = new Item("Bouteille","bouteille.jpg","Ceci est une bouteille",this.zones[6].getDescription());
			Bouteille.setPosition(100, 100);
			Bouteille.setSize(100, 100);
			tableItems.put("Bouteille", Bouteille);
			
			Item Jerrican = new Item("Jerrican","jerrican.png","Ceci est un jerrican",this.zones[12].getDescription());
			Jerrican.setPosition(300, 100);
			Jerrican.setSize(100, 100);
			tableItems.put("Jerrican", Jerrican);
			
			Item Parachute = new Item("Parachute","parachute.png","Ceci est un parachute",this.zones[15].getDescription());
			Parachute.setPosition(100, 100);
			Parachute.setSize(100, 100);
			tableItems.put("Parachute",Parachute);
			
			Item Pills = new Item("Pills","pills.jpg","Ceci est un pills",this.zones[2].getDescription());
			Pills.setPosition(200, 100);
			Pills.setSize(100, 100);
			tableItems.put("Pills",Pills);
			
			Item Portable = new Item("Portable","portable.jpg","Ceci est un portable",this.zones[1].getDescription());
			Portable.setPosition(100, 100);
			Portable.setSize(100, 100);
			tableItems.put("Portable", Portable);

			Item CouteauDeGuerre = new Item("CouteauDeGuerre", "couteau.jpg","L'objet favoris du vétéran de guerre!",this.zones[13].getDescription());
			CouteauDeGuerre.setPosition(200, 100);
			CouteauDeGuerre.setSize(100, 100);
			tableItems.put("CouteauDeGuerre", CouteauDeGuerre);

			Item JerricanPlein = new Item("Jerrican (Plein)","jerrican.png","Le jerrican est remplit d'essence..",this.zones[14].getDescription());
			JerricanPlein.setPosition(200, 100);
			JerricanPlein.setSize(100, 100);
			tableItems.put("Jerrican (Plein)",JerricanPlein);
		}

	 private void associerItemZone() {
     	this.zones[2].ajouteItems(0, tableItems.get("Bouteille"));
     	this.zones[8].ajouteItems(0, tableItems.get("Portable"));
     	this.zones[10].ajouteItems(0, tableItems.get("Gun"));
		this.zones[10].ajouteItems(1, tableItems.get("CouteauDeGuerre")); // Le vétéran de guerre donne une quete...
		this.zones[12].ajouteItems(0, tableItems.get("Hache"));	 
	 }		

	 private void creerPNJ() {
			this.tablePNJ = new HashMap<String, PersoNonJoueur>();

			PersoNonJoueur Fille = new PersoNonJoueur("Fille", "fille.png", "Hey toi! Oui toi! J'ai besoin de ton aide. Mon père possède un bateau pour s'enfuire mais il doit être encore bourré au bar."
					+ " J'ai oublier mon téléphone chez moi, je dois absolument appeler ma mère. Tu peux aller me le chercher? Ma maison se trouve près du métro en allant vers l'EST.",
					"Alors tu as trouvé mon téléphone?",
					"Oh merci à toi! Les clé du bateau de mon père sont dans sa chambre d'hotel, c'est la chambre n°14. Je te retrouve à la Marina",
					"fille en detresse qui se dit etre la fille du capitaine.");
			Fille.setPosition(100, 300);
			Fille.setSize(100, 100);
			tablePNJ.put("Fille",Fille);
			
			PersoNonJoueur Capitaine = new PersoNonJoueur("Capitaine", "capitaine.jpg", "Attention..*ich* ! Capitaine à..*ich* babord !",
					"Zzzzzzzzzz !", "Oh ma tête ! Que ce passe-t-il ici? Je dois retrouver ma fille. "
							+ "J'ai laisser mes clés de bateau dans ma chambre d'hotel, prends les et rejoins moi a la Marina, c'est la chambre n°14",
					"Capitaine d'un bateau qui semble avoir un penchant pour l'alcool.");
			Capitaine.setPosition(100, 300);
			Capitaine.setSize(100, 100);
			tablePNJ.put("Capitaine",Capitaine);
			
			PersoNonJoueur VeteranGuerre = new PersoNonJoueur("Veteran de guerre", "veterant.jpg", "Hey vous! Par ici!", 
					"Mon couteau", 
					"Merci",
					"Un étrange personnage qui semble avoir des séquels de la guerre.");
			VeteranGuerre.setPosition(100, 300);
			VeteranGuerre.setSize(100, 100);
			tablePNJ.put("Veteran de guerre",VeteranGuerre);
			
			PersoNonJoueur Pilote = new PersoNonJoueur("Pilote", "pilote.jpg", "Tous les avions sont partis, il ne reste que ce tas de féraille. Il est a court d'essence."
					+ " Ramenez nous de quoi faire le plein et on décolera d'ici avant qe les zombies nous attrapent", 
					"On est a court d'essence", 
					"On peut décoller de cette île",
					"Pilote d'avion qui peut m'aider à quitter cette île.");
			Pilote.setPosition(100, 300);
			Pilote.setSize(100, 100);
			tablePNJ.put("Pilote",Pilote);
			
			PersoNonJoueur Zombie = new PersoNonJoueur("Zombie", "zombie.jpg", "",
					"", 
					"",
					"");
			Zombie.setPosition(100, 300);
			Zombie.setSize(100, 100);
			tablePNJ.put("Zombie",Zombie);
		}

	 private void creerMap() {
		 int i;
		 this.mapJeu = new MapZone();
		 i=0;
		 while(i<15) {
			 this.mapJeu.setMap(this.zones[i].getDescription(), "MapZone"+i+".png");
			 
			 i+=1; 
		 }
	 }

	 private void associerPNJZone() {
		 this.zones[1].ajoutePNJ(tablePNJ.get("Fille"));
			this.zones[2].ajoutePNJ(tablePNJ.get("Capitaine"));
			this.zones[13].ajoutePNJ(tablePNJ.get("Veteran de guerre"));
			this.zones[14].ajoutePNJ(tablePNJ.get("Pilote"));
			this.zones[6].ajoutePNJ(tablePNJ.get("Zombie"));
	 }
}
