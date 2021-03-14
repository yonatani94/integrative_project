package demo.boundarys;

import demo.models.CreatedBy;
import demo.models.ItemAttributes;
import demo.models.ItemID;
import demo.models.Location;
import demo.models.OperationId;

public class ItemBoundary {

	private ItemID itemID;
	private String type;
	private String name;
	private boolean active;
	private String createdTimestamp;
	private CreatedBy createdBy;
	private Location location;
	private ItemAttributes itemAttributes;
	
	public ItemBoundary(ItemID itemID, String type, String name, boolean active, String createdTimestamp,
			CreatedBy createdBy , Location location, ItemAttributes itemAttributes) {
		super();
		this.itemID = itemID;
		this.type = type;
		this.name = name;
		this.active = active;
		this.createdTimestamp = createdTimestamp;
		this.createdBy = createdBy;
		this.location = location;
		this.itemAttributes = itemAttributes;
	}

	public ItemID getItemID() {
		return itemID;
	}

	public void setItemID(ItemID itemID) {
		this.itemID = itemID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(String createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public CreatedBy getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(CreatedBy createdBy) {
		this.createdBy = createdBy;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public ItemAttributes getItemAttributes() {
		return itemAttributes;
	}

	public void setItemAttributes(ItemAttributes itemAttributes) {
		this.itemAttributes = itemAttributes;
	}

	@Override
	public String toString() {
		return "ItemBoundary [itemID=" + itemID + ", type=" + type + ", name=" + name + ", active=" + active
				+ ", createdTimestamp=" + createdTimestamp + ", createdBy=" + createdBy + ", location=" + location
				+ ", itemAttributes=" + itemAttributes + "]";
	}
	
	
	
	
}
