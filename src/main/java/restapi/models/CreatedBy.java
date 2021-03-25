package restapi.models;

public class CreatedBy {
	private UserID userId;

	public CreatedBy() {
		super();
	}

	public CreatedBy(UserID userId) {
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
