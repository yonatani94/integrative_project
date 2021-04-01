package twins.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import restapi.models.UserID;

@Entity
@Table(name="USERS")
public class UserEntity {
	private UserID userId;
	private String role;
	private String username;
	private String avatar;
	
	public UserEntity() {
		
	}

	@Id
	public UserID getUserId() {
		return userId;
	}

	public void setUserId(UserID userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	
	
}
