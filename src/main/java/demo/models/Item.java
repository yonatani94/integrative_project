package demo.models;

public class Item {
	private ItemID itemId;

	public Item() {
		super();
	}

	public Item(ItemID itemId) {
		super();
		this.itemId = itemId;
	}

	public ItemID getItemId() {
		return itemId;
	}

	public void setItemId(ItemID itemId) {
		this.itemId = itemId;
	}

}
