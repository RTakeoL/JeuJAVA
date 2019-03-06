package jeuRIP.elementsGraphiques;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelZone extends JPanel {
	
	public JPanel panelZoneCourante= new JPanel (null);
	public JPanel panelZoneNouvelle= new JPanel (null);
	private JLabel lblImgZone= new JLabel() ;
	private JLabel lblImgZoneNvlle= new JLabel() ;
	private int zoneW = 800 ;
	private int zoneH = 500 ;
	
	public PanelZone() {
		super(null);
		
		setBackground(Color.LIGHT_GRAY);
	    setBounds(0, 0, this.zoneW, this.zoneH);
	    
	    this.panelZoneCourante.setBounds(0, 0, this.zoneW, this.zoneH);
	    this.panelZoneNouvelle.setBounds(0, -600, this.zoneW, this.zoneH);
	    
	    this.lblImgZone.setBounds(0, 0, this.zoneW, this.zoneH);
	    this.lblImgZoneNvlle.setBounds(0, 0, this.zoneW, this.zoneH);
	    
	    this.panelZoneCourante.add(lblImgZone);
	    this.panelZoneNouvelle.add(lblImgZoneNvlle);
	    
		this.add(this.panelZoneCourante);
		this.add(this.panelZoneNouvelle);
	}
	
	
	private void setImgZoneCourante(String nomImgFond , JPanel zone ) {
	    
		 setImageDeFondLbl(nomImgFond, lblImgZone);
		 lblImgZone.setLocation(0, 0);
		 zone.add(lblImgZone);
	
	}
	
	private void setImgZoneNvlle(String nomImgFond , JPanel zone ) {
	    
		 setImageDeFondLbl(nomImgFond, lblImgZoneNvlle);
		 lblImgZoneNvlle.setLocation(0, 0);
		 zone.add(lblImgZoneNvlle);
	
	}
	
	public void ajouterImgZoneCourante(String nomImg) {
		this.removeAll();
		this.setImgZoneCourante(nomImg, panelZoneCourante);
		panelZoneCourante.setLocation(0 , 0);
		this.add(panelZoneCourante , 0);
		this.add(panelZoneNouvelle , 1);
		
		this.revalidate();
		this.repaint();
	}

	
	public void ajouterImgNvlleZone( String nomImg) {
				
		this.setImgZoneNvlle(nomImg, panelZoneNouvelle);
		//panelZoneNouvelle.setLocation(0, -500);
		//panelZoneCourante.setLocation(0, 0);
		
		/*
		
		new Thread (new Runnable (){

			public void run() {
				 for (int i=-500 ; i<=0 ; i++) {
					 
					 	panelZoneNouvelle.setLocation(0, i );
					 	if(i==0) {
					 		
					 	}
					 try {
						Thread.currentThread().sleep(3);
						System.out.println("marche ZONE");
					} catch (InterruptedException e) {
						e.printStackTrace();
						System.out.println("marche pas ZONE");
					} 
				 }
				 
				 
				 //updatePanelZone();
				 
				
			}
			
		}).start();     
		*/
		
		
		ajouterImgZoneCourante(nomImg);
		
	}
	
	
	///  Animation slide Zone à terminer  ----------------------------
	
		public void slideZone(JPanel zoneNew) {
		
			
			new Thread (new Runnable (){

				public void run() {
					 for (int i=-500 ; i<=0 ; i++) {
						 
						 	zoneNew.setLocation(0, i );
						 	
						 try {
							Thread.currentThread().sleep(3);
							System.out.println("marche ZONE");
						} catch (InterruptedException e) {
							e.printStackTrace();
							System.out.println("marche pas ZONE");
						} 
					 }
					 
					 updatePanelZone();
				}
				
			}).start(); 
		
		}
		
		 private void updatePanelZone() {
			 
			 System.out.println("updatePanelZone");
			 this.removeAll();
			 panelZoneCourante.setLocation(0, 0);
			 panelZoneNouvelle.setLocation(0, -500);
			 this.add(this.panelZoneCourante);
			
			 this.add(this.panelZoneNouvelle);
			 this.repaint();
			 this.revalidate();

		};
		
		
	public void setImageDeFondLbl (String nomFichier, JLabel lbl) {
		System.out.println(this.getClass().getResource("/images/"+ nomFichier));
		ImageIcon icon = new ImageIcon( this.getClass().getResource("/images/"+ nomFichier));
	    Image img = icon.getImage();
	    Image newImg = img.getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
	    ImageIcon newIcon = new ImageIcon(newImg);
	    lbl.setIcon(newIcon);
	}

	
	
	

}
