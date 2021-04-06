package twins.data;

import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import restapi.models.InvokedBy;
import restapi.models.Item;
import restapi.models.ItemID;
import restapi.models.OperationId;

@Entity
@Table(name="OPERATIONS")
public class OperationEntity {
	
	private OperationId operationId;
	private String type;
	private Item item;
	private Date createdTimestamp;
	private InvokedBy invokedBy;
	private Map<String, Object> operationAttributes;
	
	public OperationEntity() {
		
	}

	@Id
	public OperationId getOperationId() {
		return operationId;
	}

	public void setOperationId(OperationId operationId) {
		this.operationId = operationId;
	}

	@Transient
	public String getType() {
		return type;
	}

	@Transient
	public void setType(String type) {
		this.type = type;
	}

	@Transient
	public Item getItem() {
		return item;
	}
	
	@Transient
	public void setItem(Item item) {
		this.item = item;
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
	public InvokedBy getInvokedBy() {
		return invokedBy;
	}

	@Transient
	public void setInvokedBy(InvokedBy invokedBy) {
		this.invokedBy = invokedBy;
	}
	
	@Transient
	public Map<String, Object> getOperationAttributes() {
		return operationAttributes;
	}
	
	@Transient
	public void setOperationAttributes(Map<String, Object> operationAttributes) {
		this.operationAttributes = operationAttributes;
	}

	
}
