package jeuRIP.elementsGraphiques;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

import jeuRIP.Fenetre;
import jeuRIP.Jeu;
import jeuRIP.Entites.Item;
import jeuRIP.Entites.PersoNonJoueur;
import jeuRIP.Entites.Zone;


public class JeuPanel extends JPanel   {
	
	Jeu jeu ;

	private PanelInventaire panelInventaire  ; 
	
	private PanelZone panelZone ; // panel affichage zone
	
	private PanelCdes  panelCdes;
	
	private PanelMap panelMap;
	
	// cadre affichage message 
	private  PanelMsgBox panelMsgBox ;
	
	
	// Constructeur permet de creer un PANEL MASTER qui va contenir 
	//PANEL ZONE +  Panel Inventaire
	public JeuPanel(Jeu jeu) { 
		super(null);
		setBounds(0, 0, 800, 600);
		
		this.jeu = jeu ;
		
		//this.setBtnQuiter();
		
		this.panelMsgBox = new PanelMsgBox(this);
		
		//msgBox.afficherPensee("helooooooooooooooooooooooooooooooo");
//		panelMsgBox.afficherMsgPJN("heloooooooooooofldjjldflgdfljgldfljdlfgjldfjdflgdlfgjldkkkkkkkkkkkkkkk"
//				+ "khddddddddddddddddd"
//				+ "hkdlfssssssssssssssssssssssss"
//				+ "dfshkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"
//				+ "dssssssssssssssssssssssssssssssssssssssh"
//				+ "jdfslsdsdhsdhfsfhsdhfhsdhlsdhldhlhshdhlhlsdhlshldhlsdlhkflsooooooooooo", "fille.png");
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
	
	
	public void setImageMap(String nomImgMap) {
		this.panelMap.setImageMap(nomImgMap) ;
	}
	
	private void updateJeu() {
		//this.removeAll();
		
		this.repaint();
		this.revalidate();

	}
	
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
		
	public void afficherImgZone(String nomImg) {
			
			this.panelZone.ajouterImgZoneCourante(nomImg);	
			updateJeu();	
	}
	
	/// affichage item dans la zone
	public void afficherItem(int indexItem , String nomImgItem , int X , int Y , int W , int H) {
		this.panelZone.setImgItem(indexItem,nomImgItem, X, Y, W, H);
	}
	
	
	///// ramasser Item  de la zone  le 15/03
		public void ramasserItem(int indexItem) {	
			Item item = this.jeu.zoneCourante.getItem(indexItem); // recuperer item de la zone
			
			this.jeu.zoneCourante.listItemZone.remove(indexItem); // supprimer item de la zone 
			this.jeu.inventaireItems.put(item.getNomItem(), item) ; // ajouter item dans liste inventaire
			this.ajouterItemInventaire(item); // afficher item dans inventaire panel
			System.out.println(item.getNomItem()); // pr debug
			System.out.println("----nb items inventaire :"+this.jeu.inventaireItems.size()); // pr debug
		}
		
	
	// ajouter item à l'inventaire 
	public  void ajouterItemInventaire(Item item) {
		this.panelInventaire.ajouterItem(item);
	}
	
	/// initialiser (cacher) tous les objets dans la zone
	public void initAllItems() {
		this.panelZone.initAllItems();
	}
	
	// pour verifier si on peut utiliser item  dans la bonne zone
	public boolean checkItemWithZone(Item item) {
		//return (this.jeu.zoneCourante.getDescription() == "Ruelle de Départ" && item.getNomItem() == "Jerrican") ;
		 return ( this.jeu.zoneCourante.getDescription() == item.getZoneUtilise() ) ;

	}
	
	public void utiliserItem(Item item) {
		jeu.utiliserItem(item);

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
	
	// afficher msg 
	public void afficherDialoguePNJ(String msg , String nomImg) {
	 	
		this.panelMsgBox.afficherMsgPJN(msg, nomImg);
		



	}

	// afficher pensée joueur
	public void afficherPensee(String texte ) {
	 	
		this.panelMsgBox.afficherPensee(texte);
	}

	
	 public void afficher(String s) {
	        System.out.println("---- "+ s);
	 }
	
	 
	 public void seDeplacer(String dir) {
	        
	        jeu.seDeplacer( dir);
	 }
	 
	
	
	 
	 
	// pour afficher un item dans la zone 
	 public void afficherItemZC(Zone zc) {

			initAllItems(); // initialiser les cadres affichage pour les nvx items (sinon les items persistent)
			if(zc.listItemZone.size() > 0) {
				
				System.out.println(zc.listItemZone.size() +" 8888888888888");
				for(int i=0 ; i<3 ; i++){
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
						afficherItem(i, imgItem, X, Y, W, H);
						
					
					}
				}	
			}	
		}
	  
	 
	
	 
	 public void toggleIneventaire() {
		this.panelInventaire.togglePanelInventaire();

	}
	 
	 public void toggleMap() {
		 this.panelMap.toggleMap();
	 }
	 
	 public void setImageDeFondLbl (String nomFichier, JLabel lbl) {
			//System.out.println(this.getClass().getResource("/images/"+ nomFichier)); // debug
			lbl.setIcon(null);
			ImageIcon icon = new ImageIcon( this.getClass().getResource("/images/"+ nomFichier));
		    Image img = icon.getImage();
		    Image newImg = img.getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
		    ImageIcon newIcon = new ImageIcon(newImg);
		    lbl.setIcon(newIcon);
		}


	 
	
}
