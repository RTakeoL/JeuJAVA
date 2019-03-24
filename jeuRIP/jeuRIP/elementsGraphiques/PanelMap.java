package jeuRIP.elementsGraphiques;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelMap {
	
	JeuPanel jeuPanel ;
	private JPanel MapContainer ;
	private JLabel fond ;
	private JLabel imgMap ;
	int mapW = 350 ;
	int mapH = 300 ;
	int mapX = 800 ; 
	int mapY = 50 ;
	private boolean mapAffiche = false ;
	public PanelMap(JeuPanel jeuPanel) {
		
		this.jeuPanel = jeuPanel ;
		this.MapContainer= new JPanel() ;
		this.MapContainer.setBounds(mapX, mapY, mapW, mapH);
		this.MapContainer.setLayout(null);
		fond = new JLabel();
		fond.setBounds(0, 0, mapW, mapH);
    	setImageDeFondLbl ("mapBG.png" , fond);
		this.MapContainer.add(fond);
		this.jeuPanel.add(MapContainer);
	}
	
	
	public void afficherMap() {
		//this.MapContainer.setLocation(400 , mapY);
		
		
		new Thread (new Runnable (){
			public void run() {
				 for (int i=mapX ; i>=450 ; i--) {
					 MapContainer.setLocation(i, mapY);				    					 
					 try {
						Thread.currentThread().sleep(3);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
						System.out.println("marche pas");
					} 
				 }	
				MapContainer.repaint();
		    	MapContainer.revalidate();
			}
		}).start(); ;
		
		this.mapAffiche = true ;
		
		
	}
	
	public void cacherMap() {
		this.MapContainer.setLocation(mapX , mapY);
		this.mapAffiche = false ;
	}
	
	
	public void toggleMap() {
		if (this.mapAffiche) {
			this.cacherMap();
		}else {
			this.afficherMap() ;
		}
	}
	
	
	public void setImageMap(String imgMap) {
		
    	setImageDeFondLbl (imgMap , fond);
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
