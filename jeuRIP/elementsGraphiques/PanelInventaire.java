package jeuRIP.elementsGraphiques;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import jeuRIP.Entites.Item;
import jeuRIP.Utils.ImgFond;



public class PanelInventaire extends JPanel {
		private JeuPanel jeuPanel ;
		private int posInventX= -400;
		private int posInventY= 50;
		private int invetW= 350;
		private int inventH= 300;
		private boolean inventAffiche = false ;
		private HashMap<Integer, JLabel> itemsIcons; // liste des cases affichage items inventaire
		private int iconX = 22 ; // position 1ere icon item dans panel Inventaire
		
		private JTextArea itemDescript ;
		private JLabel btnUtiliserItem ;
		private boolean btnUtiliserActif = false ;
		private HashMap<Integer, Item> itemsInventaire; 
		private Item itemSelected  = null ;
		
		private JLabel btnJeterItem ;
		private boolean btnJeterActif = false ;
		
		private Thread thread ;
		
	    public PanelInventaire( JeuPanel jeuPanel) {
	    	super(null);
	    	this.jeuPanel = jeuPanel ; 	  	
	    	
		    setBounds(posInventX, posInventY, invetW, inventH);
	    	setLayout(null);
	    	
	    	initItemIcons(); // creer les 5 cases vides pour les items
	    	this.btnUtiliserItem = new JLabel("    UTILISER ");
	    	
	        this.btnUtiliserItem.addMouseListener(new MouseAdapter() {
		    		@Override
			    	public void mouseClicked(MouseEvent arg0) {
			   			utiliserItem(itemSelected);
			   			activerBtnUtiliser(false);
			   			activerBtnJeter(false);
			   			indexSelectedIcon = 0 ;
			   			cacherInventaire();			   			
			    	}	
			    });	        
	    	this.btnUtiliserItem.setBackground(Color.DARK_GRAY);
	    	this.btnUtiliserItem.setOpaque(true);
	    	this.btnUtiliserItem.setBounds(210, 100, 80, 40);
	    	//btnUtiliserItem.setForeground(Color.LIGHT_GRAY);
	    	
	    	this.btnJeterItem = new JLabel("   JETER ");
	    	this.btnJeterItem.setBackground(Color.DARK_GRAY);
	    	this.btnJeterItem.setOpaque(true);
	    	this.btnJeterItem.setBounds(100, 100, 80, 40);
	    	this.btnJeterItem.addMouseListener(new MouseAdapter() {
	    		@Override
		    	public void mouseClicked(MouseEvent arg0) {
		   			jeterItem(itemSelected);
		   			activerBtnUtiliser(false);
		   			activerBtnJeter(false);
		   			indexSelectedIcon = 0 ;
		   			cacherInventaire();
		    	}	
		    });
	    	
	    	this.add(btnJeterItem);
	    	
	    	this.add(btnUtiliserItem);
	    	
	    	itemDescript = new JTextArea();
	    	itemDescript.setOpaque(false);
	    	//itemDescript.setBackground(Color.DARK_GRAY);
	       	itemDescript.setBounds(59, 179, 230, 99);
	       	itemDescript.setForeground(Color.LIGHT_GRAY);
	    	this.add(itemDescript);
	    	
	    	JLabel discription = new JLabel("Discription :");
	    	discription.setBounds(22, 151, 104, 19);
	    	discription.setForeground(Color.LIGHT_GRAY);
	       	this.add(discription);
	    	
	    	JLabel fond = new JLabel();
	    	fond.setBounds(0, 0, 350, 300);
	    	setImageDeFondLbl ("inventaireBG.png" , fond);
	    	fond.addMouseListener(new MouseAdapter() {
	    		@Override
	    		public void mouseEntered(MouseEvent e) {
	    			afficherInventaire();
	    		}
		    });
	    	add(fond);
	    }    
	    
	    public void cacherInventaire() {
			this.setLocation(-400, posInventY);		
			this.inventAffiche = false ;
    		
	    }
	  	    
	    public void afficherInventaire() {
	    	int x = this.getX();
			System.out.println("inventaire visible");
			thread = new Thread (new Runnable (){
				public void run() {
					
					 for (int i=x ; i<=0 ; i++) {
    					 setLocation(i, posInventY);				    					 
    					 try {
    						 
							Thread.currentThread().sleep(3);
							
						} catch (InterruptedException e) {
							
							e.printStackTrace();
							Thread.currentThread().interrupt();
							System.out.println("marche pas");
							break ;
						} 
    				 }						 
					 repaint();
			    	revalidate();
				}
			});
			thread.start(); ;
			
			this.inventAffiche =true ;
	    }
	    // Afficher OU Cacher l'INVENTAIRE
	    public void togglePanelInventaire () {
    		
    		if ( this.inventAffiche ) {
	    		
	    		this.cacherInventaire();			
    		}else {
    			this.afficherInventaire();	    				    		
    		}	
	    }
	   
	    public void ajouterItem (Item item ){
    		
	    	String nomImgItem = item.getImage();  
    		JLabel lblobj = new JLabel();
    		if(itemsInventaire.get(1)== null ) { lblobj = itemsIcons.get(1)  ;	this.itemsInventaire.put(1, item) ; }
    		else if(itemsInventaire.get(2)== null ) { lblobj = itemsIcons.get(2) ;	this.itemsInventaire.put(2, item) ; }
    		else if(itemsInventaire.get(3)== null ) { lblobj = itemsIcons.get(3) ;	this.itemsInventaire.put(3, item) ; }
    		else if(itemsInventaire.get(4)== null ) { lblobj = itemsIcons.get(4) ;  this.itemsInventaire.put(4, item) ; }
    		else if(itemsInventaire.get(5)== null ) { lblobj = itemsIcons.get(5) ;  this.itemsInventaire.put(5, item) ; }
    		
    		setImageDeFondLbl(nomImgItem, lblobj);	
    }
	    
	    // vider la case item 
	    private void supprimerItem(Item itemUsed) {
	    	for (int i = 1 ; i <6 ; i++) {
	    		if(itemUsed.getNomItem()== this.itemsInventaire.get(i).getNomItem()) {
		    		itemsIcons.get(i).setIcon(new ImageIcon(PanelInventaire.class.getResource("/images/caseVideInvent.png")));
		    		this.itemsInventaire.replace(i, null);
		    		break ;
		    	}
	    	}
		
		}
	  
	    // méthode invoquée au click sur btn UTILISER 
	    private void utiliserItem(Item item) {
			if (item != null && this.btnUtiliserActif ) {
				 if( this.jeuPanel.utiliserItem(item)) {
					 supprimerItem(item) ;
				 } 
	    	}
		}
		
	 // méthode invoquée au click sur btn JETER
	    private void jeterItem(Item item) {
			if (item != null && this.btnJeterActif ) {
			  
			   this.jeuPanel.jeterItem(item);
			   supprimerItem(item) ;
			 
	    	}
		}
			    
	    private void setImageDeFondLbl (String nomFichier, JLabel lbl) {

	    	ImgFond.setImageDeFondLbl(nomFichier, lbl, this.getClass());
	    	
		}
	    
	    // creer 5 cases vides pour items + et les ajouter au panel inventaire
		private void initItemIcons() {
			// creation de 5 cases vides
			this.itemsIcons = new HashMap<Integer, JLabel>();
			
			this.itemsInventaire = new HashMap<Integer, Item>();
			
			for (int i = 1 ; i<6 ; i++) {
				
				JLabel itemIcon = new JLabel("obj");
		    	itemIcon.setLocation(iconX, 35);
		    	itemIcon.setIcon(new ImageIcon(PanelInventaire.class.getResource("/images/caseVideInvent.png")));
		    	itemIcon.setSize(new Dimension(50, 50));
		    	itemIcon.setPreferredSize(new Dimension(50, 50));
		    	
		    	itemsIcons.put(i, itemIcon);
		    	
		    	itemsInventaire.put(i, null);
		    	iconX +=60 ; // next item position 
			}
			 // ajouter les cases vides au invenataire panel
			for(int ii = 1 ; ii<6 ; ii++) {
				this.addEvent(ii);
				this.add(itemsIcons.get(ii)) ;
			}			
		}
		
		
		// ajout event click sur item 
		private int indexSelectedIcon = 0 ;
		private void addEvent(int index) {
			
			 itemsIcons.get(index).addMouseListener(new MouseAdapter() {
		    		
				 	@Override
				 	public void mouseEntered(MouseEvent e) {
				 		if(index != indexSelectedIcon){
				 			itemsIcons.get(index).setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true)) ;
			 			}
				 	}
				 	@Override
				 	public void mouseExited(MouseEvent e) {
				 		
				 		if(index != indexSelectedIcon){
				 			itemsIcons.get(index).setBorder(null) ;
			 			}
				 		
				 	}
				 	@Override
			    	public void mouseClicked(MouseEvent arg0) {
				 		indexSelectedIcon = index ;
			    		setItemDescript(index);
			    		itemSelected = itemsInventaire.get(index);
			    		selectedCase(index);
			    		if (  itemSelected != null ) {			    				
			    				if(jeuPanel.checkItemWithZone(itemSelected)) {  
			    					activerBtnUtiliser(true);	
			    				}else {  activerBtnUtiliser(false) ; }		
			    				activerBtnJeter(true);				    			
			    		}else {
				    			activerBtnUtiliser(false);
				    			activerBtnJeter(false);		
				    	}
		    		}
			    });
		 
		}
		
		private void selectedCase(int index ) {
			for(int i=1 ; i<=5 ; i++) {
				itemsIcons.get(i).setBorder(null);
			}
			if(index !=0) {
				itemsIcons.get(index).setBorder(new LineBorder(Color.GREEN, 2, true)) ;
				
			}
		}
		
		private void setItemDescript(int indexItem) {
			
			String descript = "vide ..." ; 
			if(itemsInventaire.get(indexItem) != null) {
				descript = itemsInventaire.get(indexItem).getDescription();
			}			
			this.itemDescript.setText(descript);

		}
		
		
		
		
		private void activerBtnUtiliser(boolean etatBtn) {
			
			this.btnUtiliserActif = etatBtn ;
			if(etatBtn) {
				this.btnUtiliserItem.setBackground(Color.DARK_GRAY);  // btn UTILISER activé
				this.btnUtiliserItem.setForeground(Color.LIGHT_GRAY);
				this.btnUtiliserItem.setBorder(new LineBorder(Color.GREEN, 2, true)) ;
			}else {
				this.btnUtiliserItem.setBackground(Color.DARK_GRAY); // btn UTILISER désactivé
				this.btnUtiliserItem.setForeground(Color.GRAY);
				this.btnUtiliserItem.setBorder(null) ;
				
				
			}
		}
		
		private void activerBtnJeter(boolean etatBtn) {
			
			this.btnJeterActif = etatBtn ;
			if(etatBtn) {
				this.btnJeterItem.setBackground(Color.DARK_GRAY);  // btn UTILISER activé
				this.btnJeterItem.setForeground(Color.LIGHT_GRAY);
				this.btnJeterItem.setBorder(new LineBorder(Color.RED, 2, true)) ;
			}else {
				this.btnJeterItem.setBackground(Color.DARK_GRAY); // btn UTILISER désactivé
				this.btnJeterItem.setForeground(Color.GRAY);
				this.btnJeterItem.setBorder(null) ;
				
				
			}
		}
}
