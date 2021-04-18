package twins.data;

import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import twins.digitalItemAPI.Item;
import twins.operationsAPI.InvokedBy;
import twins.operationsAPI.OperationId;

@Entity
@Table(name = "OPERATIONS")
public class OperationEntity {

	private String operationSpace;
	private String operationID;
	private String type;
	private String itemID;
	private String itemSpace;
	private Date createdTimestamp;
	private String invokedByUserEmail;
	private String invokedByUserSpace;
	private String operationAttributes;

	public OperationEntity() {

	}

	public String getOperationSpace() {
		return operationSpace;
	}

	public void setOperationSpace(String operationSpace) {
		this.operationSpace = operationSpace;
	}

	@Id
	public String getOperationID() {
		return operationID;
	}

	public void setOperationID(String operationID) {
		this.operationID = operationID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	@Lob
	public String getOperationAttributes() {
		return operationAttributes;
	}

	public void setOperationAttributes(String operationAttributes) {
		this.operationAttributes = operationAttributes;
	}

	public String getItemSpace() {
		return itemSpace;
	}

	public void setItemSpace(String itemSpace) {
		this.itemSpace = itemSpace;
	}

	public String getInvokedByUserSpace() {
		return invokedByUserSpace;
	}

	public void setInvokedByUserSpace(String invokedByUserSpace) {
		this.invokedByUserSpace = invokedByUserSpace;
	}

	public String getInvokedByUserEmail() {
		return invokedByUserEmail;
	}

	public void setInvokedByUserEmail(String invokedByUserEmail) {
		this.invokedByUserEmail = invokedByUserEmail;
	}

	@Override
	public String toString() {
		return "OperationEntity [operationSpace=" + operationSpace + ", operationID=" + operationID + ", type=" + type
				+ ", itemID=" + itemID + ", itemSpace=" + itemSpace + ", createdTimestamp=" + createdTimestamp
				+ ", invokedByUserEmail=" + invokedByUserEmail + ", invokedByUserSpace=" + invokedByUserSpace
				+ ", operationAttributes=" + operationAttributes + "]";
	}

}
