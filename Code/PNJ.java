
public class PNJ {
	private String nomPNJ;
	private String nomImage;
	private String dialogue;
	
	public PNJ(String nomPNJ, String nomImage, String dialogue) {
		this.nomPNJ = nomPNJ;
		this.nomImage = nomImage;
		this.dialogue = dialogue;
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
	
}
