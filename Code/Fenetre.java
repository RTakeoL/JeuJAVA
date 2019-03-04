package Jeu;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Image;
import javax.swing.JLabel;

public class Fenetre extends JFrame{
		
	JPanel vueUser = new JPanel(); // interface principale
	JPanel vueZone = new JPanel() ; // section zone => pour y ajouter la ZONE (partie Roland )
	JPanel vueObjJoueur = new JPanel() ; // section inventaire objs ramassés
	JPanel vueCmdes = new JPanel() ; // section commandes 
	
	
	public Fenetre(){   
		// parametres fenetre :
	    this.setTitle("Jeu RIP");
	    this.setSize(800, 600);
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);
	    
	    
	    // interface principale de l'itilisatuer
	    this.vueUser.setBackground(Color.DARK_GRAY);
	    this.vueUser.setLayout(null);
	    this.setContentPane(this.vueUser);
	    
	    // creation bouton sud : 
	    JButton btnSud = new JButton();
	    btnSud.setBackground(Color.DARK_GRAY);
	    btnSud.setBorderPainted(false);
	    btnSud.setBounds(390, 474, 70 ,40);
	    
	    // ajout image btn sud :
	    ImageIcon icon = new ImageIcon( Fenetre.class.getResource( "/images/flecheS.png"));
	    Image myImg = icon.getImage();
	    Image btnImg = myImg.getScaledInstance(btnSud.getWidth(), btnSud.getHeight(), Image.SCALE_SMOOTH);
	    ImageIcon btnImgIcon = new ImageIcon(btnImg);
	    btnSud.setIcon(btnImgIcon);
	    
	    
	    JButton btnNord = new JButton();
	    btnNord.setBackground(Color.DARK_GRAY);
	    btnNord.setBorderPainted(false);
	    btnNord.setBounds(390, 0, 70 ,40);
	    ImageIcon iconN = new ImageIcon( Fenetre.class.getResource( "/images/flecheN.png"));
	    Image myImgN = iconN.getImage();
	    Image btnImgN = myImgN.getScaledInstance(btnNord.getWidth(), btnNord.getHeight(), Image.SCALE_SMOOTH);
	    ImageIcon btnImgIconN = new ImageIcon(btnImgN);
	    btnNord.setIcon(btnImgIconN);
	   
	    
	    JButton btnOuest = new JButton();
	    btnOuest.setBackground(Color.DARK_GRAY);
	    btnOuest.setBorderPainted(false);
	    btnOuest.setBounds(58, 200, 40 ,70);
	    ImageIcon iconO = new ImageIcon( Fenetre.class.getResource( "/images/flecheO.png"));
	    Image myImgO = iconO.getImage();
	    Image btnImgO = myImgO.getScaledInstance(btnOuest.getWidth(), btnOuest.getHeight(), Image.SCALE_SMOOTH);
	    ImageIcon btnImgIconO = new ImageIcon(btnImgO);
	    btnOuest.setIcon(btnImgIconO);
	    
	    JButton btnEst = new JButton();
	    btnEst.setBackground(Color.DARK_GRAY);
	    btnEst.setBorderPainted(false);
	    btnEst.setBounds(760, 200, 40 ,70);
	    ImageIcon iconE = new ImageIcon( Fenetre.class.getResource( "/images/flecheE.png"));
	    Image myImgE = iconE.getImage();
	    Image btnImgE = myImgE.getScaledInstance(btnEst.getWidth(), btnEst.getHeight(), Image.SCALE_SMOOTH);
	    ImageIcon btnImgIconE = new ImageIcon(btnImgE);
	    btnEst.setIcon(btnImgIconE);
	    
	    
	    // section commandes 
	    this.vueCmdes.setBackground(Color.BLACK);
	    this.vueCmdes.setBounds(0, 516, 800, 64);
	    	 
	    JButton btnQuiter = new JButton("Quiter");
	    JButton btnParler = new JButton("Parler");
	    JButton btnUtiliser = new JButton("Utiliser");
	    JButton btnRamasser = new JButton("Ramasser");
	    
	    this.vueCmdes.add(btnQuiter);
	    this.vueCmdes.add(btnParler);
	    this.vueCmdes.add(btnUtiliser);
	    this.vueCmdes.add(btnRamasser);
	        
	    
	    // section Objets ramassés 
	    this.vueObjJoueur.setBackground(Color.GRAY);
	    this.vueObjJoueur.setBounds(0, 0, 48, 500);
	    
	    JLabel lblObjs = new JLabel("Objs");
	    vueObjJoueur.add(lblObjs);
	    
	    JLabel lblObj1 = new JLabel("obj1");
	    vueObjJoueur.add(lblObj1);
	    
	    JLabel lblObj2 = new JLabel("obj2");
	    vueObjJoueur.add(lblObj2);
	    
	    JLabel lblObj3 = new JLabel("obj3");
	    vueObjJoueur.add(lblObj3);
	    
	    
	    // image RIP
	    JLabel lblTitle = new JLabel();
	    lblTitle.setBounds(350, 150, 180, 120);
	    ImageIcon iconlbl = new ImageIcon( Fenetre.class.getResource( "/images/RIP.png"));
	    Image myImglbl = iconlbl.getImage();
	    Image imgT = myImglbl.getScaledInstance(lblTitle.getWidth(), lblTitle.getHeight(), Image.SCALE_SMOOTH);
	    ImageIcon imgTitle = new ImageIcon(imgT);
	    lblTitle.setIcon(imgTitle);
	    
	        
	    // ajout composants  à l'interface utilisateur
	    this.vueUser.add(btnNord);
	    this.vueUser.add(btnSud);
	    this.vueUser.add(btnOuest);
	    this.vueUser.add(btnEst);
	    this.vueUser.add(this.vueObjJoueur);
	    this.vueUser.add(this.vueCmdes);
	    
	   
	    this.vueUser.add(lblTitle);
	    
	    	     
	    // suite parametres fenetre  
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
	     
	  } 
}
