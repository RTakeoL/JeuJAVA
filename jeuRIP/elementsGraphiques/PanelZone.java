package jeuRIP.elementsGraphiques;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelZone extends JPanel {
	JeuPanel jeuPanel ;
	public JPanel panelZoneCourante= new JPanel (null);
	public JPanel panelZoneNouvelle= new JPanel (null);
	private JLabel lblImgZone= new JLabel() ;
	private JLabel lblImgZoneNvlle= new JLabel() ;
	
	private int zoneW = 800 ;
	private int zoneH = 500 ;
	
	JLabel imgItem1 = new JLabel("ITEM 1 ....");// pour affichage item1
	JLabel imgItem2 = new JLabel("ITEM 2 ....");
	JLabel imgItem3 = new JLabel("ITEM 3 ....");
	
	public PanelZone(JeuPanel jeuPanel) {
		super(null);
		this.jeuPanel = jeuPanel ;
		setBackground(Color.LIGHT_GRAY);
	    setBounds(0, 0, this.zoneW, this.zoneH);
	    
	    
	    
	    this.panelZoneCourante.setBounds(0, 0, this.zoneW, this.zoneH);
	    this.panelZoneNouvelle.setBounds(0, -600, this.zoneW, this.zoneH);
	    
	    this.lblImgZone.setBounds(0, 0, this.zoneW, this.zoneH);
	    this.lblImgZoneNvlle.setBounds(0, 0, this.zoneW, this.zoneH);
	    
	 // cadre affichage item1
	    imgItem1.setBounds(0, -100, 100, 100);
	    imgItem1.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    			System.out.println("item clicked  .....");
	    		//initImgItem(0);
	    		ramasserItem(0);

	    	}
	    });
	    
	    this.add(imgItem1);
	    // cadre affichage item2
	    imgItem2.setBounds(0, -100, 100, 100);
	    imgItem2.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    			System.out.println("item clicked  .....");
	    		//initImgItem(1);
	    		ramasserItem(1);

	    	}
	    });
	    
	    this.add(imgItem2);
	    
	 // cadre affichage item3
	    imgItem3.setBounds(0, -100, 100, 100);
	    imgItem3.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    			System.out.println("item clicked  .....");
	    		//initImgItem(2);
	    		ramasserItem(2);

	    	}
	    });
	    
	    this.add(imgItem3);
	    
	    
	    this.panelZoneCourante.add(imgItem1);
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
	
	
	
	public void setImgItem(int index ,String imgItem , int X , int Y , int W , int H)  {
		
		if(index ==0) {
			initImgItem(0);
			this.imgItem1.setBounds(X ,Y , W , H);
			this.setImageDeFondLbl(imgItem, imgItem1);
		}
		if(index ==1) {
			initImgItem(1);
			this.imgItem2.setBounds(X ,Y , W , H);
			this.setImageDeFondLbl(imgItem, imgItem2);
		}
		if(index ==2) {
			initImgItem(2);
			this.imgItem3.setBounds(X ,Y , W , H);
			this.setImageDeFondLbl(imgItem, imgItem3);
		}
		//this.revalidate();
		//this.repaint();
	}
	
	
	private void ramasserItem(int indexItem) {
		jeuPanel.ramasserItem(indexItem);
		initImgItem(indexItem);
		
		
	}
	
	public void initImgItem(int index) {
		if(index == 0) {
			imgItem1.setBounds(0, -100, 100, 100);
			this.imgItem1.setIcon(null);
		}
		if(index == 1) {
			imgItem2.setBounds(0, -100, 100, 100);
			this.imgItem2.setIcon(null);
		}
		if(index == 2) {
			imgItem3.setBounds(50, -100, 100, 100);
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
	
	
	
	public void ajouterImgZoneCourante(String nomImg) {
		//this.removeAll();
		this.setImgZone(nomImg, panelZoneCourante);
		panelZoneCourante.setLocation(0, 0);
		//this.add(panelZoneCourante );
		//this.add(panelZoneNouvelle );
		
		this.revalidate();
		this.repaint();
	}
	
	
		 private void updatePanelZone() {
			 
			 System.out.println("updatePanelZone");
			 this.repaint();
			 this.revalidate();

		};
		
		
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
