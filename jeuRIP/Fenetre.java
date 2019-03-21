package jeuRIP;



import javax.swing.*;

import jeuRIP.elementsGraphiques.JeuPanel;





public class Fenetre {
	
	public static JFrame window = new JFrame ();
		
	public JeuPanel panel  ;
	private Jeu jeu;
	
	private void creerFenetre() {
		window.setTitle("Jeu RIP");
	    window.setSize(800, 600);
	    window.getContentPane().setLayout(null);
	    window.setResizable(false);
	    window.setLocationRelativeTo(null);
	       
	    window.getContentPane().add(this.panel); 
	    
	        
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setVisible(true);
	}
	
	public Fenetre(Jeu jeu){ 
		
		 this.jeu = jeu;
		 this.panel = new JeuPanel(jeu) ;
	     creerFenetre();
		
	}
	
	public JeuPanel getPanel() {
		return (this.panel );
	}
	
	
	

		
}
