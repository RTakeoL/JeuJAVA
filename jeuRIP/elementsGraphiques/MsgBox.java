package jeuRIP.elementsGraphiques;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class MsgBox {
	
	JeuPanel jeuPanel ;
	private JPanel msgContainer ;
	private JLabel msgBox ;
	private JLabel btnFermer;
	
	public MsgBox(JeuPanel jeuPanel) {
		this.jeuPanel = jeuPanel ;
		this.msgContainer = new JPanel();
		this.msgContainer.setLayout(null);
		this.msgContainer.setOpaque(true);
		this.msgContainer.setBounds(0  , -600 , 800 , 600 ); 
		
		this.msgBox = new JLabel();
		this.msgBox.setBounds(100  , 100, 600 , 300 );
		this.msgBox.setPreferredSize( new Dimension(400 , 300)) ;
		this.msgBox.setBackground(Color.GRAY);
		this.msgBox.setOpaque(true);
		
		this.btnFermer = new JLabel(" FERMER ");
		this.btnFermer.setBounds(380 , 410, 100 , 40 );
		this.btnFermer.setPreferredSize( new Dimension(100 , 40)) ;
		this.btnFermer.setBackground(Color.GREEN);
		this.btnFermer.setOpaque(true);
		this.btnFermer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fermerMsgBox();
			}
		});
		
		this.msgContainer.add(msgBox);
		this.msgContainer.add(btnFermer);
		this.jeuPanel.add(msgContainer); 
	}
	
	
	public void setMsgText(String text) {
		this.msgBox.setText(text);

	}
	
	public void afficherMsg() {
		this.msgContainer.setLocation(0 , 0);

	}
	
	private void fermerMsgBox() {
		
		this.msgContainer.setLocation(0 , -600);
	}
	
	public void affichageMsgPJN(){
		
		
	}
	

}
