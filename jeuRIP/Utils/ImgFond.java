package jeuRIP.Utils;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImgFond {
	
	String nomImg ;

		
	 public static void setImageDeFondLbl (String nomFichier, JLabel lbl , Class classe ) {
			//System.out.println(this.getClass().getResource("/images/"+ nomFichier)); // debug
			lbl.setIcon(null);
			ImageIcon icon = new ImageIcon( classe.getResource("/images/"+ nomFichier));
		    Image img = icon.getImage();
		    Image newImg = img.getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
		    ImageIcon newIcon = new ImageIcon(newImg);
		    lbl.setIcon(newIcon);
		}

}
