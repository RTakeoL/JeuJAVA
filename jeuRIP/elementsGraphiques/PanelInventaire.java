package jeuRIP.elementsGraphiques;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import jeuRIP.Entites.Item;
import jeuRIP.Utils.ImageDeFond;



public class PanelInventaire extends JPanel {
		JeuPanel jeuPanel ;
		private int posInventX= -400;
		private int posInventY= 50;
		private int invetW= 350;
		private int inventH= 300;
		private boolean inventAffiche = false ;
		private HashMap<Integer, JLabel> itemsIcons; // liste des cases affichage items inventaire
		private int iconX = 22 ; // position 1ere icon item dans panel Inventaire
		
		private JLabel itemDescript ;
		private JLabel btnUtiliserItem ;
		private boolean btnUtiliserActif = false ;
		private HashMap<Integer, Item> itemsInventaire; 
		private Item itemSelected  = null ;
		
		private Thread thread ;
		
	    public PanelInventaire( JeuPanel jeuPanel) {
	    	super(null);
	    	this.jeuPanel = jeuPanel ; 	  	
	    	
		    setBounds(posInventX, posInventY, invetW, inventH);
	    	setLayout(null);
	    	
	    	initItemIcons(); // creer les 5 cases vides pour les items
	    	
	    	btnUtiliserItem = new JLabel("    UTILISER ");
	    	
	        btnUtiliserItem.addMouseListener(new MouseAdapter() {
		    		@Override
			    	public void mouseClicked(MouseEvent arg0) {
			   			utiliserItem(itemSelected);
			   			activerBtnUtiliser(false);
			   			indexSelectedIcon = 0 ;
			   			
			    	}	
			    });
	        
	    	btnUtiliserItem.setBackground(Color.DARK_GRAY);
	    	btnUtiliserItem.setOpaque(true);
	    	btnUtiliserItem.setBounds(130, 100, 80, 40);
	    	//btnUtiliserItem.setForeground(Color.LIGHT_GRAY);
	    	
	    	this.add(btnUtiliserItem);
	    	
	    	itemDescript = new JLabel();
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
	    	
	    	//fond.setIcon(new ImageIcon(PanelInventaire.class.getResource("/images/inventaireBG.png")));
	    	fond.setBounds(0, 0, 350, 300);
	    	setImageDeFondLbl ("inventaireBG.png" , fond);
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
	    public void utiliserItem(Item item) {
			if (item != null && this.btnUtiliserActif ) {
			 supprimerItem(item) ;
			 this.jeuPanel.utiliserItem(item);
			
			 
	    	}
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
		
		private boolean estCaseItemVide(int index) {
			return(itemsInventaire.get(index)== null);

		}
		
		// ajout event click sur item 
		
		private int indexSelectedIcon = 0 ;
		public void addEvent(int index) {
			
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
			    		if (  itemSelected != null && jeuPanel.checkItemWithZone(itemSelected)) {
				    			activerBtnUtiliser(true);
			    		}else {
				    			activerBtnUtiliser(false);
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
		
		
		
		
		public void activerBtnUtiliser(boolean etatBtn) {
			
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
}
