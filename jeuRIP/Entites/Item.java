package jeuRIP.Entites;

public class Item {
	private String nomItem;
	private String nomImage;
	private String description;
	private int itemPxH;
	private int itemPxW;
	private int itemX;
	private int itemY;
	
	public Item(String nomItem, String nomImage, String description) {
		this.nomItem = nomItem;
		this.nomImage = nomImage;
		this.description = description;
		this.itemPxH=0;
		this.itemPxW=0;
		this.itemX=0;
		this.itemY=0;
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
	
	public void setPosition(int x, int y) {
		this.itemX=x;
		this.itemY=y;
	}
	
	public void setSize(int pxW, int pxH) {
		this.itemPxW=pxW;
		this.itemPxH=pxH;
	}

	public int getItemPxH() {
		return itemPxH;
	}

	public int getItemPxW() {
		return itemPxW;
	}

	public int getItemX() {
		return itemX;
	}

	public int getItemY() {
		return itemY;
	}
}
