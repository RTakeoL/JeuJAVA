package jeuRIP.elementsGraphiques;
import jeuRIP.Fenetre;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import com.sun.javafx.embed.swing.Disposer;
import javax.swing.border.LineBorder;

public class PanelCdes extends JPanel {
	
	private JLabel btnQuiter = new JLabel("Quiter");

	public PanelCdes() {
		super(null);
		
		setBackground(Color.BLACK);
	    setBounds(0, 520, 800, 100);
	    
	    setBtnQuiter();
	    this.add(btnQuiter);
	   
	}
	
	public void setBtnQuiter() {
		btnQuiter.setLocation(586, 11);
		this.btnQuiter.setSize(120, 60);;
		
		 btnQuiter.addMouseListener(new MouseListener() {
				
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
					// TODO Auto-generated method stub
					btnQuiter.setBorder(null);
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					
					btnQuiter.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
				    Fenetre.window.setVisible(false);
				    Fenetre.window.dispose();
					
				}
			});
		 
		 this.add(btnQuiter);
		
	}

}
