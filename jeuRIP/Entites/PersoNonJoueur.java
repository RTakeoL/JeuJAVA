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
	private String initDialogue;
	private String waitDialogue;
	private String doneDialogue;
	private String descriptionPNJ;
	private Boolean initQuete;
	private Boolean doneQuete;
	//private Item itemPNJ;
	
	
	public PersoNonJoueur(String nomPNJ, String nomImage, String initDialogue, String waitDialogue, String doneDialogue,  String descriptionPNJ) {
		this.nomPNJ = nomPNJ;
		this.nomImage = nomImage;
		this.initDialogue = initDialogue;
		this.waitDialogue = waitDialogue;
		this.doneDialogue = doneDialogue;
		this.descriptionPNJ = descriptionPNJ;
		this.initQuete = false;
		this.doneQuete = false;	
	}
	
	
	public void setDescriptionPNJ(String descriptionPNJ) {
		this.descriptionPNJ = descriptionPNJ;
	}
	
	public String getDescriptionPNJ() {
		return(this.descriptionPNJ);
	}
	
	public void setInitQuete(Boolean etatPNJ) {
		this.initQuete = etatPNJ;
	}
	
	public Boolean getInitQuete() {
		return(this.initQuete);
	}
	public void setDoneQuete(Boolean etatPNJ) {
		this.doneQuete = etatPNJ;
	}
	
	public Boolean getDoneQuete() {
		return(this.doneQuete);
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
	
	public String getInitDialogue() {
		return(this.initDialogue);
	}
	public String getWaitDialogue() {
		return(this.waitDialogue);
	}
	public String getDoneDialogue() {
		return(this.waitDialogue);
	}
}
