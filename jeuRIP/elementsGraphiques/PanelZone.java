package jeuRIP.elementsGraphiques;

import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.LineBorder;

import jeuRIP.Utils.ImgFond;

public class PanelZone extends JPanel {
	private JeuPanel jeuPanel ;
	private JPanel panelZoneCourante; // cadre affichage zone
	private JLabel lblImgZone ; // image de fond de zone 
	private int zoneW = 800 ;
	private int zoneH = 600 ;
	
	private HashMap <Integer , JLabel> listeItemsZone ;	
	
	private JLabel fondZoneDescipt;
	private JTextArea zoneDescipt;
	private int descX = 600 ; 
	private int descY = 100 ;
	
	private JLabel imgPNJ ;
	private int imgPNJH =100 ;
	private int imgPNJW = 100 ;
	private String nomImgPNJ ;
	
	public PanelZone(JeuPanel jeuPanel) {
		super(null);
		this.jeuPanel = jeuPanel ;
		this.panelZoneCourante= new JPanel (null);
		this.lblImgZone= new JLabel() ;
		this.setBackground(Color.LIGHT_GRAY);
	    this.setBounds(0, 0, this.zoneW, this.zoneH);
	    this.setZoneDescript() ;
	    this.panelZoneCourante.setBounds(0, 0, this.zoneW, this.zoneH);
	    this.lblImgZone.setBounds(0, 0, this.zoneW, this.zoneH);	    
	  
	    this.listeItemsZone = new HashMap<Integer, JLabel>() ;	    
	    this.setImgPNJ();// creation de zone d'affichage PNJ 
	    this.panelZoneCourante.add(lblImgZone); 
		this.add(this.panelZoneCourante);
	
	}

	
	// pour changer image zc
	public void ajouterImgZoneCourante(String nomImg) {
			
		 	this.setImageDeFondLbl(nomImg, this.lblImgZone);
			this.panelZoneCourante.setLocation(0, 0);
			this.revalidate();
			this.repaint();
	}
	
	
	// creation de zone d'affichage PNJ 
	private void setImgPNJ() {
		this.imgPNJ = new JLabel("PNJ .....");   
		this.imgPNJ.setOpaque(false);
		this.imgPNJ.addMouseListener(new MouseAdapter() {			
			@Override
			public void mouseClicked(MouseEvent e) {
				//imgPNJ.setBorder(new LineBorder(Color.GREEN, 3, true));
				jeuPanel.afficherDialoguePNJWait ();	
			}
//			@Override
//	    	public void mouseEntered(MouseEvent e) {
//	    		imgPNJ.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
//	    	}
//	    	@Override
//	    	public void mouseExited(MouseEvent e) {
//	    		imgPNJ.setBorder(null);
//	    		
//	    	}	
		});		
		this.add(this.imgPNJ);
	}
	
	
	// pour initialiser img PNJ 
	public void initImgPNJ() {
		this.imgPNJ.setBounds(100 , -300 , 100 , 100 ); 
		this.imgPNJ.setIcon(null);	
	}
	
	// pour aficher image PNJ
	public void afficherPNJ( String nomImgPNJ , int X , int Y , int W , int H) {
		
		this.imgPNJH = H ;
		this.imgPNJW = W ;
		this.nomImgPNJ = nomImgPNJ ;
		this.imgPNJ.setBounds(X ,Y , W , H);
		this.setImageDeFondLbl(nomImgPNJ, imgPNJ);
		
	}
		
	// pour afficher image item
	 public void ajouterImgItem(int index ,String imgItem , int X , int Y , int W , int H) {
			
	   		JLabel imgItemlbl = new JLabel("ITEM ....") ;	  
			this.listeItemsZone.put(index, imgItemlbl);
			imgItemlbl.setBounds(X ,Y , W , H);
			this.setImageDeFondLbl(imgItem, imgItemlbl);			
			imgItemlbl.addMouseListener(new MouseAdapter() {
		    	@Override
		    	public void mouseClicked(MouseEvent arg0) {
		    			System.out.println("item clicked  .....");// pour debug
		    		imgItemlbl.setBorder(new LineBorder(Color.GREEN, 4, true));	
		    		jeuPanel.ramasserItem(index);		    		 
		    	}		    	
		    	@Override
		    	public void mouseEntered(MouseEvent e) {
		    		imgItemlbl.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		    	}
		    	@Override
		    	public void mouseExited(MouseEvent e) {
		    		imgItemlbl.setBorder(null);
		    	}
			});
		    
		    this.add(imgItemlbl , 1);
	 }
	 
	 public void supprimerImgItem (int index) {
		   if (this.listeItemsZone.containsKey(index)) {
			   this.listeItemsZone.get(index).setLocation(-300 , -300);
			   this.listeItemsZone.remove(index);
		   }
		 
	   }  
	   
	   
	   public void initAllImgsItems(int nbItem) {
		   if(! this.listeItemsZone.isEmpty()) {			   
			   for(int i=0 ; i <=nbItem ; i++) {
				   if (this.listeItemsZone.containsKey(i)) {
					   this.listeItemsZone.get(i).setLocation(-300 , -300);
					   this.listeItemsZone.remove(i);
				   }
			   }
		   }			
		}	 
	
	

	// pour affichage descritption zone
	 private void setZoneDescript() {
		   this.fondZoneDescipt = new JLabel();
		   this.fondZoneDescipt.setBounds(descX , descY , 220 , 70 ); 
		   this.fondZoneDescipt.setOpaque(true);
		   setImageDeFondLbl ("fondPensee.png" , this.fondZoneDescipt);
		   
		   
		   this.zoneDescipt = new JTextArea() ;
		   
		   this.zoneDescipt.setWrapStyleWord(true);
		   this.zoneDescipt.setLineWrap(true);
		   this.zoneDescipt.setEditable(false);
		   this.zoneDescipt.setOpaque(false);
		   this.zoneDescipt.setForeground(Color.BLACK);
		   this.zoneDescipt.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 20));
		   this.zoneDescipt.setBounds(descX+30 , descY+10, 150 , 60 );
		   this.zoneDescipt.setPreferredSize( new Dimension(150 , 60)) ;
		   this.add(this.zoneDescipt);
		   this.add(this.fondZoneDescipt);
		   
	   }
	   
	   public  void afficherDescript(String texte) {
		    this.zoneDescipt.setText(texte);
			Thread thread = new Thread (new Runnable (){
				public void run() {
					fondZoneDescipt.setLocation(descX, descY);	
					 zoneDescipt.setLocation(descX+30, descY+10);
					 	try {
							 
							Thread.currentThread().sleep(1400);
							
						} catch (InterruptedException e) {
							
							e.printStackTrace();
							Thread.currentThread().interrupt();
						} 
					 fondZoneDescipt.setLocation(descX, -110);	
					 zoneDescipt.setLocation(descX+30, -110);
				}
			});
			thread.start(); ;
	   }
	   

	// pour fixer l'image de fond d'un LABEL
	public void setImageDeFondLbl (String nomFichier, JLabel lbl) {
	    
	    ImgFond.setImageDeFondLbl(nomFichier, lbl, this.getClass());
	}  
	
	

}
