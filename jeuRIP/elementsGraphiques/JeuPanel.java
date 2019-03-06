package jeuRIP.elementsGraphiques;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import jeuRIP.Jeu;


public class JeuPanel extends JPanel{
	
	Jeu jeu ;
	PanelInventaire panelInventaire = new PanelInventaire() ; 
	
	// 
	PanelCdes panelCdes = new PanelCdes();
	 
	PanelZone panelZone = new PanelZone(); // panel affichage zone
	
	public JLabel NORD = new JLabel("NORD");
	public JLabel SUD = new JLabel("SUD");
	public JLabel EST = new JLabel("EST");
	public JLabel OUEST = new JLabel("OUEST");
	
	
	
	public JeuPanel(Jeu jeu) { // Constructeur permet de creer un PANEL MASTER qui va contenir 
						//PANEL ZONE qui elle meme doit gerer le changement zoneCourante => zoneNouvelle
		super(null);
		
		this.jeu = jeu ;
		
		setBackground(Color.ORANGE);
		setBounds(0, 0, 800, 600);
		
		this.setPanelInventaire();
		this.setBtnSortie();  
	    this.setPanelCdes();
	    this.add(panelZone);
	    	
	}
	
	private void updateJeu() {
		this.removeAll();
		this.setPanelInventaire();
		this.setBtnSortie();
		this.add(panelInventaire, 1);
		this.add(panelCdes , 7);
		this.add(panelZone, 8);
		
		this.repaint();
		this.revalidate();

	}
	
	
	
	public void afficherImgZone(String nomImg) {
			
			this.panelZone.ajouterImgZoneCourante(nomImg);
			
			System.out.println("init..............");
			this.add(panelInventaire );
			this.add(panelCdes );
			this.add(panelZone);
			
			this.revalidate();
			this.repaint();
	}
	
	
	public void changerImgZone( String nomImg) {
		
		
		this.panelZone.ajouterImgNvlleZone(nomImg);
		
		//this.panelZone.ajouterImgZoneCourante(nomImg);
				
		System.out.println("init..............");
		this.setPanelInventaire();
		this.setBtnSortie();
		this.add(panelInventaire);
		this.add(panelCdes );
		this.add(panelZone );
		
		this.revalidate();
		this.repaint();
		//updateJeu();
	 	
		
		
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
	    NORD.setBounds(425, 118, 46, 14);
	    NORD.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		System.out.println("NORD CLICKED...");
	    		 seDeplacer("NORD"); 
	          
	    	}	
	    });
	    this.add(NORD );
	    
	    EST.setBackground(Color.WHITE);
	    EST.setBounds(543, 203, 46, 14);
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
	    OUEST.setBounds(267, 203, 46, 14);
	    OUEST.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		
	    		System.out.println("OUEST CLICKED...");
	    		seDeplacer("OUEST"); 
	    	}
	    });
	    this.add(OUEST);
	    	    
	    SUD.setBackground(Color.WHITE);
	    SUD.setBounds(425, 296, 46, 14);
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
	
	

}
