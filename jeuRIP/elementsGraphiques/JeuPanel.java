package jeuRIP.elementsGraphiques;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import jeuRIP.Jeu;


public class JeuPanel extends JPanel{
	
	Jeu jeu ;
	
	PanelInventaire panelInventaire  ; 
	PanelCdes panelCdes ;
	PanelZone panelZone ; // panel affichage zone
	
	// Btn de deplacement entre zones 
	public JLabel NORD = new JLabel("NORD");
	public JLabel SUD = new JLabel("SUD");
	public JLabel EST = new JLabel("EST");
	public JLabel OUEST = new JLabel("OUEST");
	
	
	
	// Constructeur permet de creer un PANEL MASTER qui va contenir 
	//PANEL ZONE + Panel Commandes + Panel Inventaire
	public JeuPanel(Jeu jeu) { 
		super(null);
		
		this.jeu = jeu ;
		panelInventaire = new PanelInventaire();
		panelZone = new PanelZone(this);
		panelCdes = new PanelCdes();
		setBackground(Color.ORANGE);
		setBounds(0, 0, 800, 600);
		
		this.setPanelInventaire();
		this.setBtnSortie();  
	    this.setPanelCdes();
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
	
	// ramasser Item 
	public void ramasserItem(int indexItem) {
			jeu.ramasserItem(indexItem);

	}
	
	// ajouter item à l'inventaire 
	public  void ajouterItemInventaire(String nomImgItem) {
		this.panelInventaire.ajouterItem(nomImgItem);
	}
	
	/// initialiser (cacher) tous les objets dans la zone
	public void initAllItems() {
		this.panelZone.initAllItems();
	}
	
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
	
	public void setPanelCdes() {
	    this.add(panelCdes);
	}
	
	
	// creation et integration fleches sorties ( nord/ sud / est / ouest ) 
	public void setBtnSortie () {
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
