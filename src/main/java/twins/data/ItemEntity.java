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
	private String email;
	
	private String type;
	private String name;
	private boolean active;
	private Date createdTimestamp;
	private Location location;
	private String itemAttributes;
	
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
	public String getEmail() {
		return email;
	}

	@Transient
	public void setEmail(String email) {
		this.email = email;
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
	public Location getLocation() {
		return location;
	}
	
	@Transient
	public void setLocation(Location location) {
		this.location = location;
	}
	
	@Transient
	public String getItemAttributes() {
		return itemAttributes;
	}
	
	@Transient
	public void setItemAttributes(String itemAttributes) {
		this.itemAttributes = itemAttributes;
	}
	
	
	
	
	
	
}
