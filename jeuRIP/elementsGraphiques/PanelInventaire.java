package jeuRIP.elementsGraphiques;



import javax.swing.*;
import java.awt.*;

import jeuRIP.Utils.ImageDeFond;
import jeuRIP.Utils.MouseEventListener ;


public class PanelInventaire extends JPanel {
	
		ImageDeFond imgLoader = new ImageDeFond();
		private int posInventX= -320;
		private int posInventY= 50;
		
		JLabel lblObj_1 ;
		JLabel lblObj_2 ;
		JLabel lblObj_3 ;
		JLabel lblObj_4 ;
		JLabel lblObj_5 ;
		JLabel lblObj_6 ;
		JLabel lblObj_7 ;
		boolean obj1Empty = true ;
		boolean obj2Empty = true; 
		boolean obj3Empty = true ;
		boolean obj4Empty = true; 		
		boolean obj5Empty = true ;
		boolean obj6Empty = true; 
		boolean obj7Empty = true ;
		
		
	    public PanelInventaire( ) {
	    	super(null);
	    	setSize(new Dimension(20, 20));
	    	
	    	
	    		        	  	
	    	//setOpaque(false);
	    	
	    	
	    	setBounds(-320, 50, 350, 300);
	    	
	    	setLayout(null);
	    	
	    	lblObj_1 = new JLabel("obj");
	    	lblObj_1.setLocation(22, 29);
	    	lblObj_1.setIcon(new ImageIcon(PanelInventaire.class.getResource("/images/caseVideInvent.png")));
	    	lblObj_1.setBackground(Color.BLACK);
	    	lblObj_1.setSize(new Dimension(50, 50));
	    	lblObj_1.setPreferredSize(new Dimension(50, 50));
	    	
	    	add(lblObj_1);
	    	
	    	lblObj_2 = new JLabel("obj");
	    	lblObj_2.setLocation(82, 29);
	    	lblObj_2.setIcon(new ImageIcon(PanelInventaire.class.getResource("/images/caseVideInvent.png")));
	    	lblObj_2.setSize(new Dimension(50, 50));
	    	lblObj_2.setPreferredSize(new Dimension(50, 50));
	    	lblObj_2.setMinimumSize(new Dimension(50, 50));
	    	lblObj_2.setMaximumSize(new Dimension(50, 50));
	    	lblObj_2.setBackground(Color.BLACK);
	    	add(lblObj_2);
	    	
	    	lblObj_3 = new JLabel("obj");
	    	lblObj_3.setLocation(142, 29);
	    	lblObj_3.setIcon(new ImageIcon(PanelInventaire.class.getResource("/images/caseVideInvent.png")));
	    	lblObj_3.setSize(new Dimension(50, 50));
	    	lblObj_3.setPreferredSize(new Dimension(50, 50));
	    	lblObj_3.setBackground(Color.BLACK);
	    	add(lblObj_3);
	    	
	    	lblObj_4 = new JLabel("obj");
	    	lblObj_4.setLocation(202, 29);
	    	lblObj_4.setIcon(new ImageIcon(PanelInventaire.class.getResource("/images/caseVideInvent.png")));
	    	lblObj_4.setSize(new Dimension(50, 50));
	    	lblObj_4.setPreferredSize(new Dimension(50, 50));
	    	lblObj_4.setMinimumSize(new Dimension(50, 50));
	    	lblObj_4.setMaximumSize(new Dimension(50, 50));
	    	lblObj_4.setBackground(Color.BLACK);
	    	add(lblObj_4);
	    	
	    	lblObj_5 = new JLabel("obj");
	    	lblObj_5.setLocation(262, 29);
	    	lblObj_5.setIcon(new ImageIcon(PanelInventaire.class.getResource("/images/caseVideInvent.png")));
	    	lblObj_5.setSize(new Dimension(50, 50));
	    	lblObj_5.setPreferredSize(new Dimension(50, 50));    	
	    	add(lblObj_5);
	    	
	    	lblObj_6 = new JLabel("obj");
	    	lblObj_6.setLocation(22, 90);
	    	lblObj_6.setIcon(new ImageIcon(PanelInventaire.class.getResource("/images/caseVideInvent.png")));
	    	lblObj_6.setSize(new Dimension(50, 50));
	    	lblObj_6.setPreferredSize(new Dimension(50, 50));
	    	
	    	lblObj_6.setBackground(Color.BLACK);
	    	add(lblObj_6);
	    	
	    	lblObj_7 = new JLabel("obj");
	    	lblObj_7.setLocation(82, 90);
	    	lblObj_7.setIcon(new ImageIcon(PanelInventaire.class.getResource("/images/caseVideInvent.png")));
	    	lblObj_7.setSize(new Dimension(50, 50));
	    	lblObj_7.setPreferredSize(new Dimension(50, 50));
	    	lblObj_7.setBackground(Color.BLACK);
	    	add(lblObj_7);
	    	
	    	
	    	
	    	
	    	
	    	
	    	JLabel objDiscription = new JLabel("dfdsùsdfùsdùlslkkkkkkjkjkjkj");
	    	objDiscription.setOpaque(true);
	    	objDiscription.setBackground(Color.LIGHT_GRAY);
	       	objDiscription.setBounds(59, 179, 230, 99);
	    	this.add(objDiscription);
	    	
	    	JLabel discription = new JLabel("Discription :");
	    	discription.setBounds(22, 151, 104, 19);
	       	this.add(discription);
	    	
	    	JLabel fond = new JLabel();
	    	//fond.setIcon(new ImageIcon(PanelInventaire.class.getResource("/images/fondInvent.png")));
	    	fond.setBounds(0, 0, 350, 300);
	    	add(fond);
	    	
	    	
	    }
	    
	    private boolean estVisible () {
	    	return (posInventX >= 0);
	    }
	    
	    
	    // Afficher OU Cacher l'INVENTAIRE
	    public void togglePanelInventaire () {
	    	
	    	if ( !this.estVisible() ) {
    			posInventX = 0 ;
    			System.out.println("inventaire visible");
    			new Thread (new Runnable (){

					public void run() {
						 for (int i=-300 ; i<=0 ; i++) {
	    					 
	    					 setLocation(i, 50);
	    						    					 
	    					 try {
								Thread.currentThread().sleep(3);
							
								
							} catch (InterruptedException e) {
								e.printStackTrace();
								System.out.println("marche pas");
							} 
	    				 }	
						 repaint();
				    	revalidate();
					}
					
    			}).start(); ;  					    		
    		}else {
    			posInventX = -320 ;
    			setLocation(posInventX, 50);
	    		System.out.println("inventaire invisible");
	    		this.repaint();
	    		this.revalidate();
    				    		
    		}	
	    }
	    	
	    public void ajouterItem (String nomImgItem) {
	    		JLabel lblobj = new JLabel();
	    		if(obj1Empty) { lblobj = lblObj_1 ;  obj1Empty = false ; }
	    		else if(obj2Empty) { lblobj = lblObj_2 ;  obj2Empty = false ; }
	    		else if(obj3Empty) { lblobj = lblObj_3 ;  obj3Empty = false ; }
	    		else if(obj4Empty) { lblobj = lblObj_4 ;  obj4Empty = false ; }
	    		else if(obj5Empty) { lblobj = lblObj_5 ;  obj5Empty = false ; }
	    		
	    		setImageDeFondLbl(nomImgItem, lblobj);
	    		
	    }
	    
	    
	    
	    private void supprimerItem() {
			
		}
	    
	    /*
	    public void setImageDeFondLbl (String nomFichier, JLabel lbl) {
			imgLoader.setImageDeFondLbl(nomFichier, lbl);
		}
*/
		
	    
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
