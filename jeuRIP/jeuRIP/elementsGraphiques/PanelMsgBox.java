package jeuRIP.elementsGraphiques;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import jeuRIP.Utils.ImgFond;

import java.awt.Font;

public class PanelMsgBox {
	
	private JeuPanel jeuPanel ;
	private JPanel msgContainer ;
	private JTextArea msgText ;
	
	private JLabel imgPNJ ;
	private JLabel fondBox ;
	private JLabel btnFermer;
	private int boxX = 0 ;
	private int boxY = 600;
	private int boxW = 800;
	private int boxH = 200;
	
	
	public PanelMsgBox(JeuPanel jeuPanel) {
		this.jeuPanel = jeuPanel ;
		this.msgContainer = new JPanel();
		this.msgContainer.setLayout(null);
		this.msgContainer.setOpaque(true);
		this.msgContainer.setBounds(boxX  , boxY ,boxW , boxH); 
		
		this.fondBox = new JLabel();
		this.fondBox.setBackground(Color.BLACK);
		this.fondBox.setBounds(0  , 0,boxW , boxH);
    	setImageDeFondLbl ("fondDialogue.png" , this.fondBox);
    	
		this.imgPNJ =  new JLabel(" imgPNJ ");
		
		this.msgText = new JTextArea();
		this.msgText.setWrapStyleWord(true);
		this.msgText.setLineWrap(true);
		this.msgText.setEditable(false);
		this.msgText.setOpaque(false);
		this.msgText.setForeground(Color.BLACK);
		this.msgText.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 21));
		this.msgText.setBounds(170  , 13, 450 , 130 );
		this.msgText.setPreferredSize( new Dimension(400 , 130)) ;
		
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
		this.msgContainer.add(this.fondBox);
		this.jeuPanel.add(msgContainer); 
		
	}
	
	
	//
	public void afficherPensee(String texte) {
		//this.msgContainer.setLocation(800 , 150);
		//this.msgText.setLocation(170, 400);
		
		this.imgPNJ.setLocation(-200 , 0);
		this.msgText.setText(texte);
		this.msgText.setBounds(100, 15, 450 , 130 );
		this.msgText.setPreferredSize( new Dimension(450 , 130)) ;
		setImageDeFondLbl ("fondPensee.png" , this.fondBox);
		this.msgContainer.setLocation(0 , 400);
	}
	
	
	public void afficherMsgPNJ(String msgText , String nomImg){
		this.msgContainer.setLocation(800 , 150);
		//this.penseeText.setLocation(100, 400);
		
		setImageDeFondLbl ("fondDialogue.png" , this.fondBox);
		this.msgText.setText(msgText );
		this.msgText.setBounds(170  , 13, 450 , 130 );
		this.msgText.setPreferredSize( new Dimension(450 , 130)) ;
		
		this.imgPNJ.setBounds(33 , 23, 114 , 104 );
		this.imgPNJ.setPreferredSize( new Dimension(114, 104)) ;
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

		  ImgFond.setImageDeFondLbl(nomFichier, lbl, this.getClass());
	}
}
