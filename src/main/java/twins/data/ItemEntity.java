package twins.data;

import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import twins.digitalItemAPI.CreatedBy;
import twins.digitalItemAPI.ItemID;
import twins.digitalItemAPI.Location;

@Entity
@Table(name="ITEMS")
public class ItemEntity {
	private String id;
	private String space;
	
	private String type;
	private String name;
	private boolean active;
	private Date createdTimestamp;
	private CreatedBy createdBy;
	private Location location;
	private Map<String, Object> itemAttributes;
	
	public ItemEntity() {
		
	}
	
	
	@Id
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	public String getSpace() {
		return space;
	}


	public void setSpace(String space) {
		this.space = space;
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
	
	@Transient
	public boolean isActive() {
		return active;
	}
	
	@Transient
	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Transient
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	
	@Transient
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	
	@Transient
	public CreatedBy getCreatedBy() {
		return createdBy;
	}
	
	@Transient
	public void setCreatedBy(CreatedBy createdBy) {
		this.createdBy = createdBy;
	}
	
	@Transient
	public Location getLocation() {
		return location;
	}
	
	@Transient
	public void setLocation(Location location) {
		this.location = location;
	}
	
	@Transient
	public Map<String, Object> getItemAttributes() {
		return itemAttributes;
	}
	
	@Transient
	public void setItemAttributes(Map<String, Object> itemAttributes) {
		this.itemAttributes = itemAttributes;
	}
	
	
	
	
	
	
}
