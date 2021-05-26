package twins.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Value;

import twins.digitalItemAPI.Location;

@Entity
@Table(name = "ITEMS")
public class ItemEntity {
	// concat id and space
	private String id;
	private String space;
	private String idSpace;
	private String email;
	private String type;
	private String name;
	private boolean active;
	private Date createdTimestamp;
	// Change location attribute to 2 doubles
	private double log;
	private double lat;
	
	private String itemAttributes;

	public ItemEntity() {

	}

	@Id
	public String getIdSpace() {
		return idSpace;
	}

	public void setIdSpace(String idSpace) {
		this.idSpace = idSpace;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSpace() {
		return space;
	}

	@Value("${spring.application.name:dummy}")
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLog() {
		return log;
	}

	public void setLog(double log) {
		this.log = log;
	}

	@Lob
	public String getItemAttributes() {
		return itemAttributes;
	}

	public void setItemAttributes(String itemAttributes) {
		this.itemAttributes = itemAttributes;
	}

}
