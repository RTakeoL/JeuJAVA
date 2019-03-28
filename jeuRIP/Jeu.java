package jeuRIP;

import jeuRIP.elementsGraphiques.JeuPanel;



import java.util.*;

import jeuRIP.Entites.*;

public class Jeu {
	
	private Fenetre fenetre  ; 
	private JeuPanel jeuPanel ;
	
	private Zone[] zones;
	private HashMap<String, Item> tableItems ;
	private HashMap<String, PersoNonJoueur> tablePNJ;
	private HashMap<String, Item> inventaireItems; // par kh 15/03
	private Zone zoneCourante;
	private Zone zonePrec; // zonePrec, capture la zone qui precede celle qui va s'afficher. Elle permet notemment de savoir par quelle fin le joueur souhaite passer.
	private MapZone mapJeu;
	private Boolean tempsJeuDepasse = false;
	private static int nombreTour = 0;
	private static final int nombreTourMax = 30;
	
	
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
		this.fenetre = fen ;
		this.setPanel(fenetre.getPanel());
	}
	
	public void setPanel(JeuPanel panel) {
		this.jeuPanel = panel;
		
		this.jeuPanel.afficherImgStart("ZONE2.png"); // par khamis le 27/03 pour image demarrage jeu
	}
	private void creerCarte() {
        this.zones = new Zone[16];
        // partie de gauche (marina)
        this.zones[0] = new Zone("Ruelle de Départ", "ZONE0.png" );
        this.zones[1] = new Zone("Ruelle OUEST (Sud)", "ZONE1.png");
        this.zones[2] = new Zone("Bar", "ZONE2.png");
        this.zones[3] = new Zone("Hotel", "ZONE3.jpg");
        this.zones[4] = new Zone("Ruelle OUEST (Nord)", "ZONE4.png");
        this.zones[5] = new Zone("Marina", "ZONE5.png");
        this.zones[6] = new Zone("Supermarché", "ZONE6.jpg");
        
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
				this.zonePrec = this.zoneCourante;
				this.zoneCourante = nouvelle;
				Jeu.nombreTour +=1;
				if(Jeu.nombreTour >= Jeu.nombreTourMax) {
					this.tempsJeuDepasse = true;
					this.zoneCourante = this.zones[15];
				}			
				this.etatJeu(this.zoneCourante);
				 
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
					} 

					break;
					case "Métro" :
						if(this.zones[7].obtientSortie("SUD") != null) {
							jeuPanel.afficherPensee(
							"** ATTENTION À TOUS LES USAGERS.. CECI EST LE DERNIER TRAIN POUR L'AÉROPORT.. LE SERVICE SERA TERMINÉ APRÈS CELUI-CI **" + 
							"Le métro est le moyen le plus rapide pour rejoindre l'aéroport, et en plus c'est le dernier, je devrais le prendre au plus vite pour quitter l'île....");
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
						jeuPanel.afficherPensee(
						"** La porte vers la piste c'est fermée derrière vous.... ** " + 
						"ZUT ! Il faut que je trouve un objet pouvant m'aider à couper les chaînes ou faire un trou dans ces fichus portes..." );
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
					} 



					break;

					//-------------------------------------------------------------------
					case "Hotel" :
					if(capitaine.getDoneQuete() || fille.getDoneQuete()) {
				           // On ramasse la clé et cela signifie que la bonne fin est débloquée
						Item Cle = new Item("Cle","","Ceci est la cle du bateau",this.zones[5].getDescription());
						tableItems.put("Cle", Cle);
						jeuPanel.afficherPensee("** Vous rentrez dans le motel, chambre 14.... vous parvenez à trouver la clé du bateau ! ** ");
					}
					else {
							// On as accomplit aucune des deux quetes et donc on affiche
							// Le dialogue qui renseigne qu'aucune action ne peut être effectuées avec cette zone.
						jeuPanel.afficherPensee("Pas le temps de demander à la Police de m'aider... il faut que je trouve un moyen de sortir de cette île au plus vite...");
						}
					break;


					//-------------------------------------------------------------------
					case "Marina" :
					if((capitaine.getDoneQuete() || fille.getDoneQuete()) && (this.tableItems.get("Cle") != null)) {
						if(this.zones[5].obtientSortie("NORD") == null) {
							this.zones[5].ajouteSortie(Sortie.NORD, this.zones[15]);
						}
						if(capitaine.getDoneQuete() && fille.getDoneQuete()) {
							jeuPanel.afficherPensee("** En arrivant, au loin au NORD, le capitaine avec sa fille à bord du navire... le Titanic_v2..... ** ");
						}else {
							jeuPanel.afficherPensee("** Le bateau se met à rugir, la fuite vers le NORD est possible, mais le capitaine et sa fille ne se trouve nulle part sur la Marina.... Que faire ?! **");
						}
					}
					
					break;


					//-------------------------------------------------------------------
					// Le cas du supermarché doit être traiter pour le case des zombies...
					case "Supermarché" :
						int indexItemZone;
						if(this.zones[6].getPNJZone() == null && this.tableItems.get("Bouteille")!=null) { // Cas ou la bouteille est utilisé
							if(this.tableItems.get("Bouteille").getEtatItem() && !this.tableItems.get("Gun").getEtatItem()) {
								this.zones[6].ajoutePNJ(this.tablePNJ.get("Zombie"));
								jeuPanel.afficherPNJ(this.tablePNJ.get("Zombie"));
							}
						}
						
						
						
						indexItemZone=0;
						if(this.zones[6].getPNJZone() != null) { // Si des zombies sont présent dans la zone..
							
							// regarder si des items sont liés à la zone, dans le cas oui, il faut les cacher....
							if(!this.zones[6].listItemZone.isEmpty()) { 
								while(!this.zones[6].listItemZone.isEmpty()) {
									if(this.zones[6].listItemZone.get(indexItemZone) != null) {
										this.zones[6].listItemZone.remove(indexItemZone);
									}
									indexItemZone+=1;
								}
							}
							
							jeuPanel.afficherItemZC(this.zoneCourante);
							
							// Initialiser les dialogues...
							if(!zombie.getInitQuete()) {
								zombie.setInitQuete(true);
								jeuPanel.afficherPensee("** Un zombie qui semble agité vous barre la route... ** " + 
								" Chouette, un vrai de vrai, il faut que je trouve un objet pour le faire partir !");
							} else {
								if(!zombie.getDoneQuete()) {
									jeuPanel.afficherPensee("** Ce zombie semble apprécié les bonbons du supermarché, il ne partira pas de si tôt...."
												+ "Il faut trouver un objet qui pourrait le faire partir, ou bien le tuer.... **");
								}
							}
						
						}
					break;
					//-------------------------------------------------------------------
					// Le cas de la station essence où l'on n'as pas le bidon d'essence pour le remplir...
					case "Station Essence" :
					if(this.inventaireItems.get("Jerrican") == null && this.inventaireItems.get("Jerrican (Plein)")==null && pilote.getInitQuete()) {
						jeuPanel.afficherPensee("** LA STATION ESSENCE QUI A DU SENS ! **" + 
						" Cette station essence c'est toute mon enfance.... ");
					}
					break;

					// -----------------------------------------------------------
					case "Ruelle EST (Sud)" :
					if(this.zones[9].obtientSortie("EST") == null) {
						jeuPanel.afficherPensee("** La sortie vers l'EST est bloquée par des branches... **" + 
						" L'armurerie de la ville se trouve à droite de cette rue, une hache pourrait m'aider à libérer le chemin. " + 
						" L'armurerie doit regorger d'objets qui pourraient m'aider à me défendre...");
					}
					break;

					//------------------------------------------------------------------

					// Arrivée au Bar déclenche la quete du capitaine..
					// On réutilise le même principe que pour la fille du capitaine...

					case "Bar" :
					if(!(capitaine.getInitQuete())) {
						capitaine.setInitQuete(true);

			
						jeuPanel.afficherDialoguePNJ(capitaine.getInitDialogue(), capitaine.getImage());
					} 

					break; 
					// ----------------------------------------------
					case "Piste Aéroport" :
					if(!pilote.getInitQuete()) {
						pilote.setInitQuete(true);
						jeuPanel.afficherDialoguePNJ(pilote.getInitDialogue(),pilote.getImage());	
						this.zones[13].enleveSortie("NORD", zones[14]);
					}
					break;
					// ----------------------------------------------------
					case "Fin" :
					if(this.tempsJeuDepasse) {
						this.zoneCourante.setNomImage("badTime.gif");
					} else {
						if(this.zonePrec.getDescription() == "Marina") {
							if((capitaine.getDoneQuete() && fille.getDoneQuete()) && 
									(this.tableItems.get("Cle") != null)) {
								this.zoneCourante.setNomImage("good.gif"); // par khamis image pour la bonne fin 
								
							} else {
								this.zoneCourante.setNomImage("badMarina.gif"); // par khamis  image pour mauvaise fin
							}
						} else {
							if((this.inventaireItems.get("Parachute")!=null && pilote.getDoneQuete())) {
								this.zoneCourante.setNomImage("good.gif"); // par khamis pour bonne fin 
							} else {
								this.zoneCourante.setNomImage("badAirport.gif"); // par khamis pour mauvaise fin
							}
						}
					}
					break;

					
					//-------------------------------------------------------------------
					default:
					break;
				}
			}

			

	public boolean  utiliserItem (Item item) {
		boolean utilisé = true;
		switch (item.getNomItem()) {
		case "Hache":
			this.inventaireItems.get("Hache").setEtatItem(true);
			this.inventaireItems.remove("Hache");
			if(this.zones[9].obtientSortie("EST") == null) {
				this.zones[9].ajouteSortie(Sortie.EST, this.zones[10]);
				jeuPanel.afficherPensee("** Un arbre tombé à cause du chaos vous barre la route... **" + 
				"Aller hop ! Mes heures de gym ne vont pas servir à rien ! " + 
				"** Vous débloquez le chemin vers l'armurerie ! **");
				jeuPanel.checkSorties(this.zoneCourante);
			}
		break;
		case "Gun" :
			if(this.zones[6].getPNJZone() != null) {
				this.inventaireItems.get("Gun").setEtatItem(true);
				this.inventaireItems.remove("Gun");
				this.tablePNJ.get("Zombie").setDoneQuete(true);
				jeuPanel.afficherDialoguePNJ(this.tablePNJ.get("Zombie").getDoneDialogue(), 
						this.tablePNJ.get("Zombie").getImage());
				
				
						if(this.inventaireItems.get("Pills") == null && !this.tableItems.get("Pills").getEtatItem()) {
							this.zones[6].ajouteItems(1, tableItems.get("Pills"));
						}
						if(this.inventaireItems.get("Pince") == null && !this.tableItems.get("Pince").getEtatItem()) {
							this.zones[6].ajouteItems(0, tableItems.get("Pince"));
						}
						if(this.inventaireItems.get("Jerrican") == null && !this.tableItems.get("Jerrican").getEtatItem()) {
							this.zones[6].ajouteItems(2, tableItems.get("Jerrican"));
						}
						

				this.zones[6].setPNJZone(null);
				jeuPanel.afficherPNJ(null);
				jeuPanel.afficherItemZC(this.zoneCourante);
				} else {
					utilisé = false;
				}
		break;
		case "Pince" :
			this.inventaireItems.get("Pince").setEtatItem(true);
			this.inventaireItems.remove("Pince");

			if(this.zones[13].obtientSortie("NORD") == null) {
				this.zones[13].ajouteSortie(Sortie.NORD, this.zones[14]);
				jeuPanel.afficherPensee("La piste est de nouveau accessible...");
				jeuPanel.checkSorties(this.zoneCourante);
			}

		break;
		case  "Bouteille":
			this.inventaireItems.get("Bouteille").setEtatItem(true);
			this.inventaireItems.remove("Bouteille");

			if(this.zones[6].getPNJZone() != null) {
				this.zones[6].setPNJZone(null);
			jeuPanel.afficherPensee("Le zombie est distrait.. seulement pour un certains temps.....");
			if(this.inventaireItems.get("Pills") == null && !this.tableItems.get("Pills").getEtatItem()) {
				this.zones[6].ajouteItems(1, tableItems.get("Pills"));
			}
			if(this.inventaireItems.get("Pince") == null && !this.tableItems.get("Pince").getEtatItem()) {
				this.zones[6].ajouteItems(0, tableItems.get("Pince"));
			}
			if(this.inventaireItems.get("Jerrican") == null && !this.tableItems.get("Jerrican").getEtatItem()) {
				this.zones[6].ajouteItems(2, tableItems.get("Jerrican"));
			}

			jeuPanel.afficherPNJ(null);
			jeuPanel.initAffichageZC(this.zoneCourante);
			}
		break;
		case "Jerrican" :
			this.inventaireItems.get("Jerrican").setEtatItem(true);
			this.inventaireItems.remove("Jerrican");
			this.zones[12].ajouteItems(1, tableItems.get("Jerrican (Plein)"));
			jeuPanel.initAffichageZC(this.zoneCourante);
		break;
		case "Pills" :
			this.inventaireItems.get("Pills").setEtatItem(true);
			this.inventaireItems.remove("Pills");
			this.tablePNJ.get("Capitaine").setDoneQuete(true);
			jeuPanel.afficherDialoguePNJ(this.tablePNJ.get("Capitaine").getDoneDialogue(),
			this.tablePNJ.get("Capitaine").getImage());
		break;
		case "Portable" :
				this.inventaireItems.get("Portable").setEtatItem(true);
				this.inventaireItems.remove("Portable");
				this.tablePNJ.get("Fille").setDoneQuete(true);
				jeuPanel.afficherDialoguePNJ(this.tablePNJ.get("Fille").getDoneDialogue(),
				this.tablePNJ.get("Fille").getImage());
		break;
		case "CouteauDeGuerre" :
				this.inventaireItems.get("CouteauDeGuerre").setEtatItem(true);
				this.inventaireItems.remove("CouteauDeGuerre");
				this.tablePNJ.get("Veteran de guerre").setDoneQuete(true);
				jeuPanel.afficherDialoguePNJ(this.tablePNJ.get("Veteran de guerre").getDoneDialogue(),
				this.tablePNJ.get("Veteran de guerre").getImage());
				this.zones[13].ajouteItems(0, tableItems.get("Parachute"));
				jeuPanel.afficherItemZC(this.zoneCourante);
		break;
		case "Jerrican (Plein)":
			this.inventaireItems.get("Jerrican (Plein)").setEtatItem(true);
			this.inventaireItems.remove("Jerrican (Plein)");
			this.tablePNJ.get("Pilote").setDoneQuete(true);
			jeuPanel.afficherDialoguePNJ(this.tablePNJ.get("Pilote").getDoneDialogue(),
			this.tablePNJ.get("Pilote").getImage());
			this.zones[14].ajouteSortie(Sortie.NORD, this.zones[15]);
			this.jeuPanel.initAffichageZC(this.zoneCourante);
		break;
		default: 
		break;
		 }	
		return utilisé;
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
			Hache.setPosition(80, 450);
			Hache.setSize(100, 100);
			tableItems.put("Hache", Hache);
			
			Item Gun = new Item("Gun","gun.png","Ceci est un gun",this.zones[6].getDescription());
			Gun.setPosition(20, 450);
			Gun.setSize(100, 100);
			tableItems.put("Gun", Gun);
			
			Item Pince = new Item("Pince","pince.jpg","Ceci est une pince",this.zones[13].getDescription());
			Pince.setPosition(100, 100);
			Pince.setSize(100, 100);
			tableItems.put("Pince", Pince);
			
			Item Bouteille = new Item("Bouteille","bouteille.png","Ceci est une bouteille",this.zones[6].getDescription());
			Bouteille.setPosition(175, 296);
			Bouteille.setSize(65, 35);
			tableItems.put("Bouteille", Bouteille);
			
			Item Jerrican = new Item("Jerrican","jerrican.png","Ceci est un jerrican",this.zones[12].getDescription());
			Jerrican.setPosition(550, 475);
			Jerrican.setSize(100, 100);
			tableItems.put("Jerrican", Jerrican);
			
			Item Parachute = new Item("Parachute","parachute.png","Ceci est un parachute",this.zones[15].getDescription());
			Parachute.setPosition(100, 400);
			Parachute.setSize(100, 100);
			tableItems.put("Parachute",Parachute);
			
			Item Pills = new Item("Pills","pills.jpg","Ceci est un pills",this.zones[2].getDescription());
			Pills.setPosition(200, 100);
			Pills.setSize(100, 100);
			tableItems.put("Pills",Pills);
			
			Item Portable = new Item("Portable","portable.png","Ceci est un portable",this.zones[1].getDescription());
			Portable.setPosition(650, 350);
			Portable.setSize(20, 10);
			tableItems.put("Portable", Portable);

			Item CouteauDeGuerre = new Item("CouteauDeGuerre", "couteau.png","L'objet favoris du vétéran de guerre!",this.zones[13].getDescription());
			CouteauDeGuerre.setPosition(700, 450);
			CouteauDeGuerre.setSize(100, 100);
			tableItems.put("CouteauDeGuerre", CouteauDeGuerre);

			Item JerricanPlein = new Item("Jerrican (Plein)","jerrican.png","Le jerrican est remplit d'essence..",this.zones[14].getDescription());
			JerricanPlein.setPosition(400, 450);
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
					"Ma mère doit s'inquiéter pour moi... Retrouve vite mon téléphone s'il te plaît..",
					"Oh merci à toi! Si tu cherche à t'enfuir mon père possède un bateau. La clé du bateau se trouve dans sa chambre de Motel, c'est la chambre n°14." + 
					" Je te retrouve à la Marina avec mon Père dès que possible !",
					"fille en detresse qui se dit etre la fille du capitaine.");
			Fille.setPosition(250, 450);
			Fille.setSize(100, 100);
			tablePNJ.put("Fille",Fille);
			
			PersoNonJoueur Capitaine = new PersoNonJoueur("Capitaine", "capitaine.png", "Attention..*ich* ! Capitaine à..*ich* babord !",
					"Zzzzzzzzzz !", "Oh ma tête ! Que ce passe-t-il ici? Je dois retrouver ma fille. "
							+ "J'ai laisser mes clés de bateau dans ma chambre d'hotel, prends les et rejoins moi a la Marina, c'est la chambre n°14",
					"Capitaine d'un bateau qui semble avoir un penchant pour l'alcool.");
			Capitaine.setPosition(450, 275);
			Capitaine.setSize(300, 300);
			tablePNJ.put("Capitaine",Capitaine);
			
			PersoNonJoueur VeteranGuerre = new PersoNonJoueur("Veteran de guerre", "soldat.png", 
			"** Un étrange personnage s'approche en courant vers vous....**" + 
			" Pas le temps de réfléchir ! Retrouve mon couteau afin de m'aider à ouvrir ce fichu paquet tombé du ciel. Il renferme peut-être d'énorme trésors !" + 
			"** Vous décidez d'accepter, car comme dans vos rêves 'C'est pas parceque qu'il y'as zombie qui faut plus vivre hein.... **", 
					"Alors ?! Il est ou ce fichu couteau.....", 
					"Merci bien, regardons ce que ce paquet cache..... Ah ! Seulement des parachutes... à quoi ces parachutes peuvent-ils bien servir pfft...",
					"Un étrange personnage qui semble avoir des séquels de la guerre.");
			VeteranGuerre.setPosition(100, 300);
			VeteranGuerre.setSize(450, 450);
			tablePNJ.put("Veteran de guerre",VeteranGuerre);
			
			PersoNonJoueur Pilote = new PersoNonJoueur("Pilote", "pilote.png", "** Vous décidez de parler à un pilote dans l'espoir de monter dans un avion... **"
					+ " Je peux vous prendre avec moi pour un vol, mais mon avion n'as plus d'essence... ils nous faut de l'essence avant de décoller...", 
					"L'avion est prêt, il ne manque plus que de l'essence pour pouvoir partir..", 
					"Partons vite de cette île, avant qu'il ne soit trop tard !",
					"Pilote d'avion qui peut m'aider à quitter cette île.");
			Pilote.setPosition(575, 350);
			Pilote.setSize(250, 250);
			tablePNJ.put("Pilote",Pilote);
			
			PersoNonJoueur Zombie = new PersoNonJoueur("Zombie", "zombie.jpg", "",
					"** Un zombie traîne dans le supermarché, il faut trouver un moyen de le faire partir, ou pire... le tuer.... **", 
					"** Un tir, dans le ventre.... le zombie s'approche ! Second tir... pleine tête ! La fin du zombie.... **",
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
	 
	 public MapZone getMapJeu() {
		return (this.mapJeu);
	}
	 
	 public Zone getZoneCourante() {
		return zoneCourante;
	}
	 public HashMap<String, Item> getInventaireItems() {
		return inventaireItems;
	}
	 
	 public void startJeu() {
		 this.zoneCourante = this.zones[0] ; 
	 }
	 
}
