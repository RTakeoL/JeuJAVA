package jeuRIP.elementsGraphiques;
import jeuRIP.Fenetre;

import java.awt.* ; 
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class PanelCdes extends JPanel {
	
	
	JeuPanel jeuPanel ;
	private JLabel btnInventaire ;
	private JLabel btnQuiter ;
	private JLabel btnMap ;
	
	// Btn de deplacement entre zones 
	private JLabel NORD ;
	private JLabel SUD ;
	private JLabel EST ;
	private JLabel OUEST;

	public PanelCdes(JeuPanel jeuPanel) {
		super(null);
		this.jeuPanel = jeuPanel ;
		
		setBackground(Color.BLACK);
	    setBounds(0, 520, 800, 100);
	    
	    this.setBtnInventaire();
	    //this.setBtnQuiter();
	    this.setBtnMap();
	    this.setBtnSortie (jeuPanel) ;
	   
	   
	}
	
	public void cacherBtnSortie( String sortie) {
		if(sortie == "NORD") {	this.NORD.setVisible(false);	} 
		if(sortie == "SUD") {	this.SUD.setVisible(false);	    } 
		if(sortie == "EST") {	this.EST.setVisible(false);	    } 
		if(sortie == "OUEST") {	this.OUEST.setVisible(false);	} 

	}
	
	public void afficherBtnSortie (String sortie) {
		if(sortie == "NORD") {	this.NORD.setVisible(true);	} 
		if(sortie == "SUD") {	this.SUD.setVisible(true);	    } 
		if(sortie == "EST") {	this.EST.setVisible(true);	    } 
		if(sortie == "OUEST") {	this.OUEST.setVisible(true);	} 
	}
	
	
	// creation et integration fleches sorties ( nord/ sud / est / ouest ) 
	public void setBtnSortie (JeuPanel jeuPanel) {
		
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
	    		System.out.println("NORD CLICKED...");// debug
	    		jeuPanel.seDeplacer("NORD"); 
	    		
	    	}	
	    });
	    jeuPanel.add(NORD );
	    
	    EST.setBackground(Color.WHITE);
	    EST.setBounds(760, 200, 40 ,70);
	    this.setImageDeFondLbl("flecheE.png", EST);
	    EST.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		System.out.println("EST CLICKED...");// debug
	    		 String dir = "EST" ;
	    		 jeuPanel.seDeplacer(dir); 
	    		
	    	}	
	    });
	    
	    jeuPanel.add(EST );
	    
	    
	    OUEST.setBackground(Color.WHITE);
	    OUEST.setBounds(58, 200, 40 ,70);
	    this.setImageDeFondLbl("flecheO.png", OUEST);
	    OUEST.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		
	    		System.out.println("OUEST CLICKED...");// debug
	    		jeuPanel.seDeplacer("OUEST"); 
	    		
	    	}
	    });
	    jeuPanel.add(OUEST);
	    	    
	    SUD.setBackground(Color.WHITE);
	    SUD.setBounds(390, 474, 70 ,40);
	    this.setImageDeFondLbl("flecheS.png", SUD);
	    SUD.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		
	    		System.out.println("sud CLICKED..."); // debug
	    		jeuPanel.seDeplacer("SUD");

	    	}
	    });
	   
	    jeuPanel.add(SUD);
	    	
	}
		
		
		
	public void setBtnInventaire () {
		
		 this.btnInventaire = new JLabel("INVENTAIRE  clickez ici");
		 this.btnInventaire.addMouseListener(new MouseAdapter() {
		    	@Override
		    	public void mouseClicked(MouseEvent arg0) {
		    		btnInventaire.setBorder(new LineBorder(Color.GREEN, 2, true));
		    		jeuPanel.toggleIneventaire();

		    	}
		    	@Override
		    	public void mouseEntered(MouseEvent e) {
		    		btnInventaire.setBorder(new LineBorder(Color.GREEN, 2, true));
		    	}
		    	@Override
		    	public void mouseExited(MouseEvent e) {
		    		btnInventaire.setBorder(null);
		    	}
		    });
		    
		    this.btnInventaire.setBackground(Color.WHITE);
		   
		    this.btnInventaire.setBounds(0, 0, 114, 45);
		    this.setImageDeFondLbl("btnInvent.png", this.btnInventaire);
		    this.jeuPanel.add(btnInventaire);
	}
	
	public void setBtnMap() {
		this.btnMap= new JLabel();
		this.btnMap.setLocation(680, 0);
		this.btnMap.setSize(120, 45);;
		this.btnMap.setOpaque(true);
		this.setImageDeFondLbl("btnMap.png", this.btnMap);
		
		this.btnMap.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		btnMap.setBorder(new LineBorder(Color.GREEN, 2, true));
	    		jeuPanel.toggleMap();

	    	}
	    	@Override
	    	public void mouseEntered(MouseEvent e) {
	    		btnMap.setBorder(new LineBorder(Color.GREEN, 2, true));
	    	}
	    	@Override
	    	public void mouseExited(MouseEvent e) {
	    		btnMap.setBorder(null);
	    	}
	    });
		
		
		
	    this.jeuPanel.add(btnMap);
	}
	
	 public void setBtnQuiter() {
		 	this.btnQuiter = new JLabel();
			this.btnQuiter.setLocation(680, 0);
			this.btnQuiter.setSize(120, 45);;
			this.btnQuiter.setOpaque(true);
			
			 this.btnQuiter.addMouseListener(new MouseAdapter() {
					
					
					
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						btnQuiter.setBorder(null);
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						
						btnQuiter.setBorder(new LineBorder(Color.GREEN, 2, true));
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
					    Fenetre.window.setVisible(false);
					    Fenetre.window.dispose();
						
					}
				});
			 setImageDeFondLbl("btnQuiter.png", this.btnQuiter);
			 this.jeuPanel.add(btnQuiter);
			
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
