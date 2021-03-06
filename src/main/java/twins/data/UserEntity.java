package twins.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;


@Entity
@Table(name = "USERS")
public class UserEntity {
	private String email_space;
	private String space;
	private String email;
	private String role;
	private String username;
	private String avatar;

	public UserEntity() {

	}

	@Id
	public String getEmail_space() {
		return email_space;
	}
	public void setEmail_space(String emailAndSpace) {
		this.email_space = emailAndSpace;
	}
	
	public String getSpace() {
		return space;
	}

	@Value("${spring.application.name:dummy}")
	public void setSpace(String space) {
		this.space = space;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
