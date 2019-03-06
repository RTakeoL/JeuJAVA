package jeuRIP.Entites;


public class Item {
	private String nomItem;
	private String nomImage;
	private String description;
	
	public Item(String nomItem, String nomImage, String description) {
		this.nomItem = nomItem;
		this.nomImage = nomImage;
		this.description = description;
	}
	
	public String getNomItem() {
		return(this.nomItem);
	}
	
	public String getImage() {
		return(this.nomImage);
	}
	
	public void setImage(String newImage) {
		this.nomImage = newImage;
	}
	
	public String getDescription() {
		return(this.description);
	}
	
	public void setDescription(String newDescription) {
		this.description = newDescription;
	}	
}
