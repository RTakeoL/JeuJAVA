package jeuRIP.elementsGraphiques;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import jeuRIP.Jeu;
import jeuRIP.Entites.Item;
import jeuRIP.Entites.PersoNonJoueur;
import jeuRIP.Entites.Zone;


public class JeuPanel extends JPanel   {
	
	private Jeu jeu ;
	private final int nbItemxMax = 20 ;  //  nombre items maxi pour controler l'affichage des items dans la zone (utilisé dans JeuPanel)
	private PanelInventaire panelInventaire  ; 	// cadre affichage inventaire
	private PanelZone panelZone ; // cadre affichage zone	
	private PanelCdes  panelCdes;  // cadre affichage btns commandes
	private PanelMap panelMap; // cadre affichage MAP
	private  PanelMsgBox panelMsgBox ; // cadre affichage message

	// Constructeur permet de creer un PANEL MASTER qui va contenir 
	//PANEL ZONE +  Panel Inventaire
	public JeuPanel(Jeu jeu) { 
		super(null);
		this.setBounds(0, 0, 800, 600);
		
		this.jeu = jeu ;
		this.panelMsgBox = new PanelMsgBox(this);
		this.panelMap = new PanelMap(this);		 
		this.panelInventaire = new PanelInventaire(this);		
		this.add(panelInventaire);
		this.panelCdes = new PanelCdes(this);		
		this.panelZone = new PanelZone(this);
		this.panelZone.addMouseListener(new MouseAdapter() {			
			@Override
			public void mouseClicked(MouseEvent e) {
				panelInventaire.cacherInventaire();
				panelMap.cacherMap();
				//msgBox.fermerMsgBox();
			}
		});
	    this.add(panelZone);
	    	
	}
	
	private void updateJeu() {
		this.repaint();
		this.revalidate();

	}
	 
	 public void changerZone(String dir) {
		 if (this.jeu.getZoneCourante().obtientSortie( dir).getDescription() != "Fin") {
			 this.jeu.seDeplacer( dir);	
			 if(this.jeu.getZoneCourante().getDescription() == "Fin"){
				 this.panelZone.ajouterImgFinJeu(this.jeu.getZoneCourante().getNomImage());
				 this.panelCdes.cacherAllCdes();
				 this.panelMsgBox.fermerMsgBox();
				 this.updateJeu();
				 
				 
			 }else {
				 this.initAffichageZC(this.jeu.getZoneCourante());
			 }
			 
		 }else {
			 this.jeu.seDeplacer( dir);	
			 this.panelZone.ajouterImgFinJeu(this.jeu.getZoneCourante().getNomImage());
			 this.panelCdes.cacherAllCdes();
			 this.panelMsgBox.fermerMsgBox();
			 this.updateJeu();
		 }
		 
		 
		 this.panelInventaire.cacherInventaire();
	     this.panelMap.cacherMap();
	      
	 }
	 
	 
	 public  void initAffichageZC(Zone zc) {
		this.checkSorties(zc); // par kh 19/03 pour dactiver btn sortie si il exist pas
     	this.afficherImgZone(zc.getNomImage());
     	this.afficherItemZC(zc); // affichage items
    	this.afficherPNJ(zc.getPNJZone());
     	this.setImageMap(this.jeu.getMapJeu().getMap(zc.getDescription()));
     	this.afficherDescriptionZone(zc.getDescription());

	}
	 
	 public void afficherImgZone(String nomImg) {			
			this.panelZone.ajouterImgZoneCourante(nomImg);	
			this.updateJeu();	
	}
	 // pour activer ou desactiver btn SORTIES
	public void  checkSorties (Zone zc) {
		if (zc.obtientSortie("NORD") != null) { this.panelCdes.afficherBtnSortie("NORD");}
			else { this.panelCdes.cacherBtnSortie("NORD");}
		
		if (zc.obtientSortie("SUD") != null) { this.panelCdes.afficherBtnSortie("SUD");}
		else { this.panelCdes.cacherBtnSortie("SUD");}
		
		if (zc.obtientSortie("EST") != null) { this.panelCdes.afficherBtnSortie("EST");}
		else { this.panelCdes.cacherBtnSortie("EST");}
		
		if (zc.obtientSortie("OUEST") != null) { this.panelCdes.afficherBtnSortie("OUEST");}
		else { this.panelCdes.cacherBtnSortie("OUEST");}
	}
		
	
	
	// affiche discription zone 
		public void afficherDescriptionZone (String descript) {
			this.panelZone.afficherDescript( descript);
		}
		// pour initialiser image map 
		public void setImageMap(String nomImgMap) {
			this.panelMap.setImageMap(nomImgMap) ;
		}
			
		
		// pour afficher un item dans la zone 
		 public void afficherItemZC(Zone zc) {
			 this.panelZone.initAllImgsItems(this.nbItemxMax);
			 if(zc.listItemZone.size() > 0) {
				
				for(int i=0 ; i<this.nbItemxMax ; i++){
					// récuperer l'item avec l'index depuis la liste items zone courante
					Item item ;
					if (zc.listItemZone.containsKey(i)) {
						System.out.println("test khamis");
						item = zc.getItem(i);
						String imgItem = item.getImage();
						int X = item.getItemX();
						int Y = item.getItemY();
						int W = item.getItemPxW();
						int H = item.getItemPxH();
						// afficher item dans l'emplacement prévu au PanelZone
						this.panelZone.ajouterImgItem(i, imgItem, X, Y, W, H);					
					}
				}	
			}	
		}
	
		// pour verifier si on peut utiliser item  dans la bonne zone
		public boolean checkItemWithZone(Item item) {
			 return ( this.jeu.getZoneCourante().getDescription() == item.getZoneUtilise() ) ;
		}
		 
		 
	   // ramasser Item  de la zone  le 15/03
		public void ramasserItem(int indexItem) {			
			Item item = this.jeu.getZoneCourante().getItem(indexItem); // recuperer item de la zone
			if (this.jeu.getInventaireItems().size() < 5)	{				
				this.jeu.getZoneCourante().listItemZone.remove(indexItem); // supprimer item de la zone 
				this.jeu.getInventaireItems().put(item.getNomItem(), item) ; // ajouter item dans liste inventaire				
				this.panelZone.supprimerImgItem(indexItem); // supprimer img item de le panel zone
				this.panelInventaire.ajouterItem(item); // afficher item dans inventaire panel
				  
			}else {
				this.afficherPensee("   Vous ne pouvez pas ramasser plus de 5 objets");
			}
			
			System.out.println(item.getNomItem()); // pr debug
			System.out.println("----nb items inventaire :"+this.jeu.getInventaireItems().size()); // pr debug
		}		   
	
	// utiliser objet  ( applée au click sur btn utiliser dans panel INVENTAIRE ) 
	public boolean utiliserItem(Item item) {
		return ( jeu.utiliserItem(item) );

	}
	
	// jeter objet  ( applée au click sur btn JETER dans panel INVENTAIRE ) 
	public void jeterItem(Item item) {
		this.cacherIneventaire();
		this.jeu.getInventaireItems().remove(item.getNomItem()) ; // supprimer de l'inventaire item dans liste inventaire
		int index = 0 ;
		while (this.jeu.getZoneCourante().listItemZone.containsKey(index)) {
				 index ++ ;	
		}
		this.jeu.getZoneCourante().ajouteItems(index ,  item);
		
    	//this.afficherItemZC(jeu.zoneCourante); 
		this.initAffichageZC(this.jeu.getZoneCourante());

	}
	
	// afficher PNJ 
	public void afficherPNJ(PersoNonJoueur PNJ) {
		this.panelZone.initImgPNJ();
		if (PNJ != null) {
			String imgPNJ = PNJ.getImage();
			System.out.println(imgPNJ);
			int X = PNJ.getPNJX();
			int Y = PNJ.getPNJY();
			int W = PNJ.getPNJPxW();
			int H = PNJ.getPNJPxH();
			this.panelZone.afficherPNJ(imgPNJ, X, Y, W, H);			
		}	
	}
	
	// afficher msg PNJ 
	public void afficherDialoguePNJ(String msg , String nomImg) {
	 	
		this.panelMsgBox.afficherMsgPNJ(msg, nomImg);
	}
	
	// afficher dialogue PNJ WAIT  => pour le click dans PanelZone : 
	public void afficherDialoguePNJWait () {
		
		
		if(this.jeu.getZoneCourante().getPNJZone() != null  && ! this.jeu.getZoneCourante().getPNJZone().getDoneQuete()) {
			
			String msg =this.jeu.getZoneCourante().getPNJZone().getWaitDialogue();
			String nomImg = this.jeu.getZoneCourante().getPNJZone().getImage() ;
			
			 this.afficherDialoguePNJ(msg , nomImg);
		}
	}

	// afficher pensée joueur
	public void afficherPensee(String texte ) {
		this.panelMsgBox.afficherPensee(texte);
	}
	
	 public void afficher(String s) {
	        System.out.println("---- "+ s);
	 }
	
	 
	
	 //pour  controler affichage inventaire
	public void toggleIneventaire() {
			this.panelInventaire.togglePanelInventaire();

		}
	public void cacherIneventaire() {
			this.panelInventaire.cacherInventaire();

	}
	 
	public void toggleMap() {
		 this.panelMap.toggleMap();
	}

	
	
	
	
	
}
