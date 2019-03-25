package jeuRIP.elementsGraphiques;

import javax.swing.*; 
import jeuRIP.Utils.ImgFond;

public class PanelMap {
	
	private JeuPanel jeuPanel ;
	private JPanel MapContainer ;
	private JLabel fond ;
	private JLabel imgMap ;
	private int mapW = 350 ;
	private int mapH = 300 ;
	private int mapX = 800 ; 
	private int mapY = 50 ;
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
	
	 private void setImageDeFondLbl (String nomFichier, JLabel lbl) {
		 ImgFond.setImageDeFondLbl(nomFichier, lbl, this.getClass());
	}
}
