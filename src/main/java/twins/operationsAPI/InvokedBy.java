package twins.operationsAPI;

import twins.userAPI.UserID;

public class InvokedBy {
	private UserID userId;

	public InvokedBy() {
		super();
	}

	public InvokedBy(UserID userId) {
		super();
		this.userId = userId;
	}

	public UserID getUserId() {
		return userId;
	}

	public void setUserId(UserID userId) {
		this.userId = userId;
	}

}
