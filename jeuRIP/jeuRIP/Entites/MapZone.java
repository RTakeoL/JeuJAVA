package jeuRIP.Entites;
import java.util.*;


public class MapZone {
	
	/**
	 * String : Nom de la zone..
	 * String : Image de la map à afficher...
	 */
	private HashMap<String, String> mapZone;
	
	public MapZone() {
		this.mapZone = new HashMap<String, String>();
	}
	
	/**
	 * 
	 * @param nomZone
	 * Passer par la classe Jeu, on donne la description de la zone à la méthode 
	 * Pour récupérer l'image de la map à afficher par la suite.
	 * @return
	 * Un string qui renvoit le path pour afficher l'image de la map.
	 */
	public String getMap(String nomZone) {
		String nomImage;
		nomImage = this.mapZone.get(nomZone);
		return nomImage;
	}
	
	public void setMap(String nomZone, String nomImage) {
		this.mapZone.put(nomZone, nomImage);
	}
}
