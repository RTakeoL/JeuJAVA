package jeuRIP.elementsGraphiques;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jeuRIP.Entites.PersoNonJoueur;
import jeuRIP.Utils.ImgFond;

public class PanelZone extends JPanel {
	JeuPanel jeuPanel ;
	public JPanel panelZoneCourante= new JPanel (null);
	public JPanel panelZoneNouvelle= new JPanel (null);
	private JLabel lblImgZone= new JLabel() ;
	private JLabel lblImgZoneNvlle= new JLabel() ;
	
	private int zoneW = 800 ;
	private int zoneH = 600 ;
	
	private JLabel imgItem1 ;// pour affichage item1
	private JLabel imgItem2;
	private JLabel imgItem3;
	private JLabel imgItem4;
	
	private JLabel imgPNJ = new JLabel("PNJ .....");
	private int imgPNJH =100 ;
	private int imgPNJW = 100 ;
	private String nomImgPNJ ;
	
	public PanelZone(JeuPanel jeuPanel) {
		super(null);
		this.jeuPanel = jeuPanel ;
		setBackground(Color.LIGHT_GRAY);
	    setBounds(0, 0, this.zoneW, this.zoneH);
	    
	    this.panelZoneCourante.setBounds(0, 0, this.zoneW, this.zoneH);
	    this.panelZoneNouvelle.setBounds(0, -600, this.zoneW, this.zoneH);
	    
	    this.lblImgZone.setBounds(0, 0, this.zoneW, this.zoneH);
	    this.lblImgZoneNvlle.setBounds(0, 0, this.zoneW, this.zoneH);
	    
	 // cadre affichage items
	    this.setItems();
	    
	    this.imgPNJ.setBounds(100 , -300 , 100 , 100 ); 
	    this.imgPNJ.setOpaque(true);
	    this.add(this.imgPNJ);
	    
	    // 
	    this.panelZoneCourante.add(lblImgZone);
	    this.panelZoneNouvelle.add(lblImgZoneNvlle);
	    
		this.add(this.panelZoneCourante);
		this.add(this.panelZoneNouvelle);
		
		
	}
	
	private void setImgZone(String nomImgFond , JPanel zone ) {
	    
		 setImageDeFondLbl(nomImgFond, lblImgZone);
		 lblImgZone.setLocation(0, 0);
		 //zone.add(lblImgZone);
	
	}
	
	private void setItems() {
		// 3 items par zone maxi 
		this.imgItem1 = new JLabel("ITEM 1 ....");// pour affichage item
		this.imgItem2 = new JLabel("ITEM 2 ....");
		this.imgItem3 = new JLabel("ITEM 3 ....");
		this.imgItem4 = new JLabel("ITEM 4 ....");
		
		this.imgItem1.setBounds(0, -100, 100, 100);
		this.imgItem1.addMouseListener(new MouseAdapter() {
		    	@Override
		    	public void mouseClicked(MouseEvent arg0) {
		    			System.out.println("item clicked  .....");// pour debug
		    		ramasserItem(0);

		    	}
		    });
		    
		    this.add(this.imgItem1);
		    // cadre affichage item2
		    this.imgItem2.setBounds(0, -100, 100, 100);
		    this.imgItem2.addMouseListener(new MouseAdapter() {
		    	@Override
		    	public void mouseClicked(MouseEvent arg0) {
		    			System.out.println("item clicked  ....."); // pour debug
		    		//initImgItem(1);
		    		ramasserItem(1);

		    	}
		    });
		    
		    this.add(this.imgItem2);
		    
		 // cadre affichage item3
		    this.imgItem3.setBounds(0, -100, 100, 100);
		    this.imgItem3.addMouseListener(new MouseAdapter() {
		    	@Override
		    	public void mouseClicked(MouseEvent arg0) {
		    		System.out.println("item clicked  ....."); // pour debug
		    		ramasserItem(2);
		    	}
		    });
		    
		    this.add(this.imgItem3);
		    
		   

	}
	
	public void setImgItem(int index ,String imgItem , int X , int Y , int W , int H)  {
		
		if(index ==0) {
			initImgItem(0);
			this.imgItem1.setBounds(X ,Y , W , H);
			this.setImageDeFondLbl(imgItem, this.imgItem1);
		}
		if(index ==1) {
			initImgItem(1);
			this.imgItem2.setBounds(X ,Y , W , H);
			this.setImageDeFondLbl(imgItem, this.imgItem2);
		}
		if(index ==2) {
			initImgItem(2);
			this.imgItem3.setBounds(X ,Y , W , H);
			this.setImageDeFondLbl(imgItem, this.imgItem3);
		}
		//this.revalidate();
		//this.repaint();
	}
	
	
	private void ramasserItem(int indexItem) {
		this.jeuPanel.ramasserItem(indexItem);
		initImgItem(indexItem);		
	}
	
	public void initImgItem(int index) {
		if(index == 0) {
			this.imgItem1.setBounds(0, -100, 100, 100);
			this.imgItem1.setIcon(null);
		}
		if(index == 1) {
			imgItem2.setBounds(0, -100, 100, 100);
			this.imgItem2.setIcon(null);
		}
		if(index == 2) {
			this.imgItem3.setBounds(50, -100, 100, 100);
			this.imgItem3.setIcon(null);
		}
		
		//this.revalidate();
		//this.repaint();
	}
	
	
	public void initAllItems() {
		initImgItem(0);
		initImgItem(1);
		initImgItem(2);
	}
	
	public void initImgPNJ() {
		this.imgPNJ.setBounds(100 , -300 , 100 , 100 ); 
		this.imgPNJ.setIcon(null);
		this.imgPNJ.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//animationPNJUp();
				jeuPanel.afficherDialoguePNJWait ();
			}
		});
	}
	
	
	
	public void afficherPNJ( String nomImgPNJ , int X , int Y , int W , int H) {
		this.imgPNJH = H ;
		this.imgPNJW = W ;
		this.nomImgPNJ = nomImgPNJ ;
		this.imgPNJ.setBounds(X ,Y , W , H);
		this.setImageDeFondLbl(nomImgPNJ, imgPNJ);
	}
	
	public void animationPNJUp() {
		int H =  imgPNJH ; 
		int W =  imgPNJW ; 
		
		new Thread (new Runnable (){
			
			public void run() {
				 for (int i= H ; i<=H+60; i+=20) {
					 for (int j= W; j<=W+60; j+=20) {
						 imgPNJ.setSize(i, j);
						 setImageDeFondLbl(nomImgPNJ, imgPNJ);
						    
							 				    					 
						 try {
							Thread.currentThread().sleep(0);
							
						} catch (InterruptedException e) {
							e.printStackTrace();
							System.out.println("anim PNJ marche pas");
						}
					 } 
				 }	
				//repaint();
			    //revalidate();
			}
		}).start(); ;
	}
	
	
	public void ajouterImgZoneCourante(String nomImg) {
		//this.removeAll();
		this.setImgZone(nomImg, panelZoneCourante);
		this.panelZoneCourante.setLocation(0, 0);
		//this.add(panelZoneCourante );
		//this.add(panelZoneNouvelle );
		
		this.revalidate();
		this.repaint();
	}
	
		
		
	public void setImageDeFondLbl (String nomFichier, JLabel lbl) {
		//System.out.println(this.getClass().getResource("/images/"+ nomFichier)); // debug
//		lbl.setIcon(null);
//		ImageIcon icon = new ImageIcon( this.getClass().getResource("/images/"+ nomFichier));
//	    Image img = icon.getImage();
//	    Image newImg = img.getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
//	    ImageIcon newIcon = new ImageIcon(newImg);
//	    lbl.setIcon(newIcon);
	    
	    ImgFond.setImageDeFondLbl(nomFichier, lbl, this.getClass());
	}

	

	
	

}
