package jeuRIP;


import jeuRIP.Entites.Zone;
import jeuRIP.elementsGraphiques.JeuPanel;
import jeuRIP.Entites.*;

public class Jeu {
	
	
	private Zone zoneCourante;
	Zone [] zones = new Zone [4];
	Fenetre fenetre  ; 
	JeuPanel jeuPanel ;
	
	
	
	
	
	public Jeu () {
		
		 creerCarte();
		 this.fenetre= null  ;
		
	}
	
	public void setFenetre( Fenetre fen) { 
		fenetre = fen ;
		setPanel(fenetre.getPanel());
	}
	
	public void setPanel(JeuPanel panel) {
		this.jeuPanel = panel;
		jeuPanel.afficherImgZone(zoneCourante.getNomImage());
	}
	private void creerCarte() {
		
		zones[0] = new Zone("z1", "z1.png" );
        zones[1] = new Zone("z2", "z2.png" );
        zones[2] = new Zone("z3", "z3.png" );
        zones[0].ajouteSortie(Sortie.EST, zones[1]);
        zones[1].ajouteSortie(Sortie.OUEST, zones[0]);
        zones[1].ajouteSortie(Sortie.SUD, zones[2]);
        zones[2].ajouteSortie(Sortie.NORD, zones[1]);
        zoneCourante = zones[1]; 
        
	}

	
	
	
	
	
	
	 private void goTo(String direction) {
	    	Zone nouvelle = zoneCourante.obtientSortie( direction);
	    	if ( nouvelle == null ) {
	    		jeuPanel.afficher( "Pas de sortie " + direction);
	    		
	    	}
	        else {
	        	zoneCourante = nouvelle;
	        	jeuPanel.afficher(zoneCourante.getDescription());
	        	
	        	jeuPanel.afficherImgZone(zoneCourante.getNomImage());
	        }
	    }
	    
	
	 
	 public void seDeplacer(String direction) {
	    	
	        switch (direction) {
	        
	        case "NORD" :
	        	goTo( "NORD"); 
	        	break;
	       case "SUD" :
	        	goTo( "SUD"); 
	        	break;
	       case "EST" :
	        	goTo( "EST"); 
	        	break;
	       case "OUEST" :
	        	goTo( "OUEST"); 
	        	break;
	       
	       	default : 
	            
	            break;
	        }
	    }
	
}
