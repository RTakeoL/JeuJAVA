package jeuRIP.elementsGraphiques;



import javax.swing.*;
import java.awt.*;
import jeuRIP.Utils.MouseEventListener ;


public class PanelInventaire extends JPanel {
	
		
		private int posInventX= -320;
		private int posInventY= 50;
	   
		
	    
	    public PanelInventaire( ) {
	    	super(null);
	    	setSize(new Dimension(20, 20));
	    	
	    	
	    		        	  	
	    	//setOpaque(false);
	    	
	    	
	    	setBounds(-320, 50, 350, 300);
	    	/*
	    	GridBagLayout gridBagLayout = new GridBagLayout();
	    	gridBagLayout.columnWidths = new int[]{36, 50, 50, 50, 50, 56, 37};
	    	gridBagLayout.rowHeights = new int[]{56, 50, 50, 43, 0, 0, 0, 0, 0};
	    	*/
	    	
	    	

	    	setLayout(null);
	    	
	    	JLabel lblObj = new JLabel("obj");
	    	lblObj.setLocation(22, 29);
	    	lblObj.setIcon(new ImageIcon(PanelInventaire.class.getResource("/images/caseVideInvent.png")));
	    	lblObj.setBackground(Color.BLACK);
	    	lblObj.setSize(new Dimension(50, 50));
	    	lblObj.setPreferredSize(new Dimension(50, 50));
	    	
	    	add(lblObj);
	    	
	    	JLabel lblObj_1 = new JLabel("obj");
	    	lblObj_1.setLocation(82, 29);
	    	lblObj_1.setIcon(new ImageIcon(PanelInventaire.class.getResource("/images/caseVideInvent.png")));
	    	lblObj_1.setSize(new Dimension(50, 50));
	    	lblObj_1.setPreferredSize(new Dimension(50, 50));
	    	lblObj_1.setMinimumSize(new Dimension(50, 50));
	    	lblObj_1.setMaximumSize(new Dimension(50, 50));
	    	lblObj_1.setBackground(Color.BLACK);
	    	
	    	add(lblObj_1);
	    	
	    	JLabel lblObj_2 = new JLabel("obj");
	    	lblObj_2.setLocation(142, 29);
	    	lblObj_2.setIcon(new ImageIcon(PanelInventaire.class.getResource("/images/caseVideInvent.png")));
	    	lblObj_2.setSize(new Dimension(50, 50));
	    	lblObj_2.setPreferredSize(new Dimension(50, 50));
	    	
	    	lblObj_2.setBackground(Color.BLACK);
	    	
	    	add(lblObj_2);
	    	JLabel lblObj_3 = new JLabel("obj");
	    	lblObj_3.setLocation(202, 29);
	    	lblObj_3.setIcon(new ImageIcon(PanelInventaire.class.getResource("/images/caseVideInvent.png")));
	    	lblObj_3.setSize(new Dimension(50, 50));
	    	lblObj_3.setPreferredSize(new Dimension(50, 50));
	    	lblObj_3.setMinimumSize(new Dimension(50, 50));
	    	lblObj_3.setMaximumSize(new Dimension(50, 50));
	    	lblObj_3.setBackground(Color.BLACK);
	    	
	    	add(lblObj_3);
	    	
	    	JLabel lblObj_4 = new JLabel("obj");
	    	lblObj_4.setLocation(262, 29);
	    	lblObj_4.setIcon(new ImageIcon(PanelInventaire.class.getResource("/images/caseVideInvent.png")));
	    	lblObj_4.setSize(new Dimension(50, 50));
	    	lblObj_4.setPreferredSize(new Dimension(50, 50));    	
	    	add(lblObj_4);
	    	
	    	JLabel lblObj_5 = new JLabel("obj");
	    	lblObj_5.setLocation(22, 90);
	    	lblObj_5.setIcon(new ImageIcon(PanelInventaire.class.getResource("/images/caseVideInvent.png")));
	    	lblObj_5.setSize(new Dimension(50, 50));
	    	lblObj_5.setPreferredSize(new Dimension(50, 50));
	    	
	    	lblObj_5.setBackground(Color.BLACK);
	    	add(lblObj_5);
	    	
	    	JLabel lblObj_6 = new JLabel("obj");
	    	lblObj_6.setLocation(82, 90);
	    	lblObj_6.setIcon(new ImageIcon(PanelInventaire.class.getResource("/images/caseVideInvent.png")));
	    	lblObj_6.setSize(new Dimension(50, 50));
	    	lblObj_6.setPreferredSize(new Dimension(50, 50));
	    	lblObj_6.setBackground(Color.BLACK);
	    	add(lblObj_6);
	    	
	    	JLabel lblObj_7 = new JLabel("obj");
	    	lblObj_7.setLocation(142, 90);
	    	lblObj_7.setIcon(new ImageIcon(PanelInventaire.class.getResource("/images/caseVideInvent.png")));
	    	lblObj_7.setSize(new Dimension(50, 50));
	    	lblObj_7.setPreferredSize(new Dimension(50, 50));
	    	lblObj_7.setBackground(Color.BLACK);
	    	add(lblObj_7);
	    	
	    	JLabel lblObj_8 = new JLabel("obj");
	    	lblObj_8.setLocation(202, 90);
	    	lblObj_8.setIcon(new ImageIcon(PanelInventaire.class.getResource("/images/caseVideInvent.png")));
	    	lblObj_8.setSize(new Dimension(50, 50));
	    	lblObj_8.setPreferredSize(new Dimension(50, 50));
	    	lblObj_8.setBackground(Color.BLACK);
	    	add(lblObj_8);
	    	
	    	JLabel lblObj_9 = new JLabel("obj");
	    	lblObj_9.setLocation(262, 90);
	    	lblObj_9.setIcon(new ImageIcon(PanelInventaire.class.getResource("/images/caseVideInvent.png")));
	    	lblObj_9.setSize(new Dimension(50, 50));
	    	lblObj_9.setPreferredSize(new Dimension(50, 50));
	    	lblObj_9.setBackground(Color.BLACK);
	    	add(lblObj_9);
	    	
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
	    	
	    public void ajoutInventaire () {
	    	
	    }
}
