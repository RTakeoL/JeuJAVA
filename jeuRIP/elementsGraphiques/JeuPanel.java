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
	
	
	private JLabel btnQuiter ;
	
	private PanelInventaire panelInventaire  ; 
	
	private PanelZone panelZone ; // panel affichage zone
	
	// Btn de deplacement entre zones 
	public JLabel NORD = new JLabel("NORD");
	public JLabel SUD = new JLabel("SUD");
	public JLabel EST = new JLabel("EST");
	public JLabel OUEST = new JLabel("OUEST");
	
	// cadre affichage message 
	 MsgBox msgBox ;
	
	
	// Constructeur permet de creer un PANEL MASTER qui va contenir 
	//PANEL ZONE +  Panel Inventaire
	public JeuPanel(Jeu jeu) { 
		super(null);
		setBounds(0, 0, 800, 600);
		
		this.jeu = jeu ;
		this.btnQuiter = new JLabel("Quiter");
		this.setBtnQuiter();
		
		this.msgBox = new MsgBox(this);
		
		//msgBox.afficherPensee("helooooooooooooooooooooooooooooooo");
		// msgBox.afficherMsgPJN("helooooooooooooooooooooooo", "fille.png");
		
		this.panelInventaire = new PanelInventaire(this);
		this.panelZone = new PanelZone(this);
		this.panelZone.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				panelInventaire.cacherInventaire();
				msgBox.fermerMsgBox();
			}
		});
		
		//panelCdes = new PanelCdes();
		
		this.setPanelInventaire();
		this.setBtnSortie();  
	    //this.setPanelCdes();
	    this.add(panelZone);
	    
	    
	    
	    	
	}
	
	
	private void updateJeu() {
		//this.removeAll();
		
		this.repaint();
		this.revalidate();

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
	
	// afficher msg PNJ
	public void afficherMsgPNJ(String msg , String nomImg) {
	 	
		this.msgBox.afficherMsgPJN(msg, nomImg);
		

	}

	// afficher pensée joueur
		public void afficherPensee(String texte ) {
		 	
			this.msgBox.afficherPensee(texte);
			

		}
	
	
	/*
	 * 
	 */
	public void setPanelInventaire () {
		
		this.add(panelInventaire);
		    
		 JLabel lblInventaire = new JLabel("INVENTAIRE  clickez ici");
		 lblInventaire.addMouseListener(new MouseAdapter() {
		    	@Override
		    	public void mouseClicked(MouseEvent arg0) {
		    			
		    		panelInventaire.togglePanelInventaire();

		    	}
		    });
		    
		    lblInventaire.setBackground(Color.WHITE);
		    lblInventaire.setBounds(10, 0, 114, 34);
		    this.add(lblInventaire);
		    
		
	}
	
	
//	
//	public void setPanelCdes() {
//	    this.add(panelCdes);
//	}
	
	
	// creation et integration fleches sorties ( nord/ sud / est / ouest ) 
	public void setBtnSortie () {
		
		this.NORD = new JLabel("NORD");
		this.SUD = new JLabel("SUD");
		this.EST = new JLabel("EST");
		this.OUEST = new JLabel("OUEST");
		
		NORD.setBackground(Color.WHITE);
	    NORD.setBounds(390, 0, 70 ,40);
	    this.setImageDeFondLbl("flecheN.png", NORD);
	    NORD.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		System.out.println("NORD CLICKED...");
	    		 seDeplacer("NORD"); 
	    		
	    	}	
	    });
	    this.add(NORD );
	    
	    EST.setBackground(Color.WHITE);
	    EST.setBounds(760, 200, 40 ,70);
	    this.setImageDeFondLbl("flecheE.png", EST);
	    EST.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		System.out.println("EST CLICKED...");
	    		 String dir = "EST" ;
	    		 seDeplacer(dir); 
	    		
	    	}	
	    });
	    
	    this.add(EST );
	    
	    
	    OUEST.setBackground(Color.WHITE);
	    OUEST.setBounds(58, 200, 40 ,70);
	    this.setImageDeFondLbl("flecheO.png", OUEST);
	    OUEST.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		
	    		System.out.println("OUEST CLICKED...");
	    		seDeplacer("OUEST"); 
	    		
	    	}
	    });
	    this.add(OUEST);
	    	    
	    SUD.setBackground(Color.WHITE);
	    SUD.setBounds(390, 474, 70 ,40);
	    this.setImageDeFondLbl("flecheS.png", SUD);
	    SUD.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		
	    		System.out.println("sud CLICKED...");
	    		seDeplacer("SUD");
	    		
	    		
	    	}
	    });
	    this.add(SUD);
	    	
	}
	
	
	 public void afficher(String s) {
	        System.out.println("---- "+ s);
	 }
	
	 private void seDeplacer(String dir) {
	        
	        jeu.seDeplacer( dir);
	 }
	 
	
	
	 
	 
	// pour afficher un item dans la zone 
	 public void afficherItemZC(Zone zc) {

			initAllItems(); // initialiser les cadres affichage pour les nvx items (sinon les items persistent)
			if(zc.listItemZone.size() > 0) {
				for(int i=0 ; i<zc.listItemZone.size() ; i++){
					// récuperer l'item avec l'index depuis la liste items zone courante
					Item item = zc.getItem(i);
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
	 
	 
	 public void setBtnQuiter() {
			btnQuiter.setLocation(650, 11);
			this.btnQuiter.setSize(120, 60);;
			
			 btnQuiter.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						btnQuiter.setBorder(null);
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						
						btnQuiter.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
					    Fenetre.window.setVisible(false);
					    Fenetre.window.dispose();
						
					}
				});
			 
			 this.add(btnQuiter);
			
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
