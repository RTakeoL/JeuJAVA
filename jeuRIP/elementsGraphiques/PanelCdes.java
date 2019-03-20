package jeuRIP.elementsGraphiques;
import jeuRIP.Fenetre;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

//import com.sun.javafx.embed.swing.Disposer;
import javax.swing.border.LineBorder;

public class PanelCdes extends JPanel {
	
	private JLabel btnQuiter = new JLabel("Quiter");

	public PanelCdes() {
		super(null);
		
		setBackground(Color.BLACK);
	    setBounds(0, 520, 800, 100);
	   
	   
	}
	
	

}
