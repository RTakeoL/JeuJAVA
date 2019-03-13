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
	     this.creerFenetre();
		
	}
	
	public JeuPanel getPanel() {
		return (this.panel );
	}
	
	
	
	
	
	
	
	
	
	
		
//	JeuPanel jeuPanel = new JeuPanel(); 
//	CmdesPanel vueCmdes ;
//	InventairePanel vueInventaire  ;
//	
//	public Fenetre(){   
//		// parametres fenetre :
//	    this.setTitle("Jeu RIP");
//	    this.setSize(800, 600);
//	    this.setResizable(false);
//	    this.setLocationRelativeTo(null);
//	    getContentPane().setLayout(null);
//	     	    
//	    
//	    this.setContentPane(this.jeuPanel); 
//	    
//	    // coposant graphiques
//	    
//	    
//	    
//		vueInventaire = new InventairePanel() ;	  
//		vueCmdes = new CmdesPanel();
//	   
//		JLabel btnObjs = new JLabel("Inventaire");	
//		btnObjs.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//				
//				vueInventaire.toggleObjPanel();
//			}
//			
//			
//		});
//	    btnObjs.setFont(new Font("Lucida Handwriting", Font.BOLD | Font.ITALIC, 12));
//	    btnObjs.setForeground(new Color(165, 42, 42));
//    	btnObjs.setBounds(5, 2, 100, 30);
//    	    	 
//	    getContentPane().add(btnObjs);
//	        
//	        
//	    
//	    getContentPane().add(vueInventaire);
//	    getContentPane().add(vueCmdes);
//	     
//	    ////
//	   
//	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    this.setVisible(true);
//	     
//	  } 
//		
//		
//	
//	
//	public void setImageDeFondLbl (String nomFichier, JLabel lbl) {
//		
//		ImageIcon icon = new ImageIcon( getClass().getResource( "/images/"+ nomFichier));
//	    Image img = icon.getImage();
//	    Image newImg = img.getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
//	    ImageIcon newIcon = new ImageIcon(newImg);
//	    lbl.setIcon(newIcon);
//	    
//	}
//
//	
	
		
}
