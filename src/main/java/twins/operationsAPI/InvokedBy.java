package twins.operationsAPI;

import twins.userAPI.UserID;

public class InvokedBy {

	private UserID userID;

	public UserID getUserID() {
		return userID;
	}

	public InvokedBy() {
		super();
	}

	public void setUserID(UserID userID) {
		this.userID = userID;
	}

	public InvokedBy(UserID userID) {
		super();
		this.userID = userID;
	}

}
