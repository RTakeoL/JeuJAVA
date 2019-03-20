package jeuRIP.elementsGraphiques;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class MsgBox {
	
	JeuPanel jeuPanel ;
	private JPanel msgContainer ;
	private JLabel msgText ;
	private JLabel imgPNJ ;
	private JLabel btnFermer;
	private int boxX = 0 ;
	private int boxY = 600;
	
	
	public MsgBox(JeuPanel jeuPanel) {
		this.jeuPanel = jeuPanel ;
		this.msgContainer = new JPanel();
		this.msgContainer.setLayout(null);
		this.msgContainer.setOpaque(true);
		this.msgContainer.setBounds(boxX  , boxY , 800 , 200); 
		
		
		this.imgPNJ =  new JLabel(" imgPNJ ");
		
		
		
		this.msgText = new JLabel();
		this.msgText.setBackground(Color.GRAY);
		this.msgText.setOpaque(true);
		
		this.btnFermer = new JLabel(" FERMER ");
		this.btnFermer.setBounds(650 , 20, 80 , 40 );
		this.btnFermer.setPreferredSize( new Dimension(80 , 40)) ;
		this.btnFermer.setBackground(Color.GREEN);
		this.btnFermer.setOpaque(true);
		this.btnFermer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fermerMsgBox();
			}
		});
		
		this.msgContainer.add(imgPNJ);
		this.msgContainer.add(msgText);
		this.msgContainer.add(btnFermer);
		this.jeuPanel.add(msgContainer); 
	}
	
	
	//
	public void afficherPensee(String texte) {
		this.msgContainer.setLocation(800 , 150);
		this.imgPNJ.setLocation(-200 , 0);
		this.msgText.setText(texte);
		this.msgText.setBounds(100, 10, 400 , 130 );
		this.msgText.setPreferredSize( new Dimension(400 , 130)) ;
		
		this.msgContainer.setLocation(0 , 400);
	}
	
	
	public void afficherMsgPJN(String msgText , String nomImg){
		this.msgContainer.setLocation(800 , 150);
		this.msgText.setText(msgText );
		this.msgText.setBounds(160  , 10, 450 , 130 );
		
		this.msgText.setPreferredSize( new Dimension(400 , 130)) ;
		
		this.imgPNJ.setBounds(20 , 10, 130 , 130 );
		this.imgPNJ.setPreferredSize( new Dimension(130, 130)) ;
		this.imgPNJ.setBackground(Color.BLUE);
		this.imgPNJ.setOpaque(true);
		setImageDeFondLbl(nomImg , imgPNJ);
		
		this.msgContainer.setLocation(0 , 400);
		
		

	}
	
	
	
	
	private void mouseLeft() {

		this.msgContainer.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				new Thread (new Runnable (){
					public void run() {
						 for (int i=400 ; i<=800 ; i++) {
							 msgContainer.setLocation(i, 150);				    					 
							 try {
								Thread.currentThread().sleep(1);
								
							} catch (InterruptedException e) {
								e.printStackTrace();
								System.out.println("marche pas");
							} 
						 }	
						 
					}
				}).start(); ; 
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}
		});

	}
	
	public void fermerMsgBox() {
		
		this.msgContainer.setLocation(this.boxX, this.boxY);
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
