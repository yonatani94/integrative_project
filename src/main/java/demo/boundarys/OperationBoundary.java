package demo.boundarys;

import java.util.Date;
import java.util.Map;

import demo.models.InvokedBy;
import demo.models.Item;
import demo.models.ItemID;
import demo.models.OperationId;
import demo.models.UserID;

public class OperationBoundary {

	private OperationId operationId;
	private String type;
	private Item item;
	private Date createdTimestamp;
	private InvokedBy invokedBy;
	private Map<String, Object> operationAttributes;

	public OperationBoundary() {
		super();
	}

	@Override
	public String toString() {
		return "OperationBoundary [operationId=" + operationId + ", type=" + type + ", item=" + item
				+ ", createdTimestamp=" + createdTimestamp + ", invokedBy=" + invokedBy + ", operationAttributes="
				+ operationAttributes + "]";
	}

	public OperationBoundary(OperationId operationId, String type, Item item, Date createdTimestamp,
			InvokedBy invokedBy, Map<String, Object> operationAttributes) {
		super();
		this.operationId = operationId;
		this.type = type;
		this.item = item;
		this.createdTimestamp = createdTimestamp;
		this.invokedBy = invokedBy;
		this.operationAttributes = operationAttributes;
	}

	public OperationId getOperationId() {
		return operationId;
	}

	public void setOperationId(OperationId operationId) {
		this.operationId = operationId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public Map<String, Object> getOperationAttributes() {
		return operationAttributes;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public InvokedBy getInvokedBy() {
		return invokedBy;
	}

	public void setInvokedBy(InvokedBy invokedBy) {
		this.invokedBy = invokedBy;
	}

	public void setOperationAttributes(Map<String, Object> operationAttributes) {
		this.operationAttributes = operationAttributes;
	}

}
