package jeuRIP.Entites;

import java.util.ArrayList;
import java.util.HashMap;

public class Zone   {
	
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
		private String description;
		private String nomImage;
		private HashMap<String, Zone> sorties;
		public HashMap<Integer , Item> listItemZone ;
		private PersoNonJoueur PNJZone;
		
		/**
		 * 
		 * @param
		 * descripition, String qui décrit un peu la zone
		 * nomImage, chemin où est localisé l'image de la zone
		 * sorties, la direction de la sortie
		 * listItemZone, les items présente dans la zone
		 * PNJZone, le PNJ présent dans la zone
		 * 
		 * @method
		 * private Personnage persZone;
		 */

		
		public Zone(String description, String nomImage) {
			this.description = description;
			this.nomImage = nomImage;
			this.sorties = new HashMap<String, Zone>();
			this.listItemZone= new HashMap< Integer,Item>();
		}
		
		public void ajouteSortie(Sortie sortie, Zone zoneVoisine) {
			sorties.put(sortie.name(), zoneVoisine);
		}
		
		public void ajoutePNJ(PersoNonJoueur nomPNJ) {
			this.PNJZone = nomPNJ;
		}
		
		public String getNomImage() {
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
		
		public void ajouteItems(int index, Item itemZone) {
			this.listItemZone.put(index , itemZone);
		}
		
		public Item getItem(int indexItem) {
			return(this.listItemZone.get(indexItem));
		}
		
		
		
		public String getDescription() {
			return(this.description);
		}	
}
