/**
 * 
 */
package ownProject;
import java.util.*;


/**
 * @author Rld-Comp
 *
 */
public class Zone {

/**
 * @param args
 */
	private String description;
	private String nomImage;
	private HashMap<String, Zone> sorties;
	/**
	 * @param 
	 * String => Nom de l'objet comme clé
	 * ObjetJeu => Le type objet (la classe qui définit ce qu'est un objet
	 * @method
	 * private HashMap<String, ObjetJeu> objets;
	 * 
	 * @param
	 * Personnage, classe qui décrit les propriété des personnages
	 * 
	 * @method
	 * private Personnage persZone;
	 */

	
	public Zone(String description, String nomImage) {
		this.description = description;
		this.nomImage = nomImage;
		this.sorties = new HashMap<String, Zone>();
	}
	
	public void ajouteSortie(Sortie sortie, Zone zoneVoisine) {
		sorties.put(sortie.name(), zoneVoisine);
	}
	
	public String nomImage() {
		return(this.nomImage);
	}
	
	public String toString() {
		return(this.description);
	}
	
	public String getNomMap() {
		return(this.description);
	}
	
	public String getNomSorties() {
		return(this.getSorties());
	}
	
	private String getSorties() {
		return(this.sorties.keySet().toString());
	}
	
	public Zone obtientSortie(String direction) {
		return(this.sorties.get(direction));
	}
	
}
