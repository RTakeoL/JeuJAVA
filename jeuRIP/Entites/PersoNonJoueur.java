package jeuRIP.Entites;


public class PersoNonJoueur {
	/**\
	 * @param
	 * @nomPNJ => Nom du personnage non joueur;
	 * @nomImage => Image associé à ce personnage.
	 * @dialogue => Dialogue lié à ce personnage.
	 * @etatPNJ => Boolean, soit le on as bien aidé le personnage, soit non.....
	 * @descriptionPNJ => Description un peu fun du personnage...
	 */
	private String nomPNJ;
	private String nomImage;
	private String dialogue;
	private String descriptionPNJ;
	private Boolean questDone; // Boolean to true if player achieve the quest triggered by NPC
	private Boolean questInit; // Boolean to true if player accept the quest triggered by NPC
	private Item itemPNJ;
	
	public PersoNonJoueur(String nomPNJ, String nomImage, String dialogue) { // Exemple : Capitaine / Fille capitaine....)
		this(nomPNJ,nomImage,dialogue,"");
		this.questDone = false;
		this.questInit = false;
	}
	
	public PersoNonJoueur(String nomPNJ, String nomImage, String dialogue, String descriptionPNJ) {
		this.nomPNJ = nomPNJ;
		this.nomImage = nomImage;
		this.dialogue = dialogue;
		this.descriptionPNJ = descriptionPNJ;
		this.questDone = false;
		this.questInit = false;
		
		
	}
	
	
	public void setDescriptionPNJ(String descriptionPNJ) {
		this.descriptionPNJ = descriptionPNJ;
	}
	
	public String getDescriptionPNJ() {
		return(this.descriptionPNJ);
	}
	
	public void setQuestDone(Boolean questResult) {
		this.questDone = questResult;
	}
	
	public void setQuestEtat() {
		
	}
	
	public String getNomPNJ() {
		return(this.nomPNJ);
	}
	
	public String getImage() {
		return(this.nomImage);
	}
	
	public void setImage(String newImage) {
		this.nomImage = newImage;
	}
	
	public String printDialogue() {
		return(this.dialogue);
	}
	
	public void setDialogue(String newDialogue) {
		this.dialogue = newDialogue;
	}	
	
	public void setItemPNJ(Item itemPNJ) {
		this.itemPNJ = itemPNJ;
	}
}
