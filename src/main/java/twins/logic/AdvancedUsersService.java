package twins.logic;

import java.util.List;

import twins.userAPI.UserBoundary;

public interface AdvancedUsersService extends UsersService {
	public List<UserBoundary> getAllUsers(String userSpace, String userEmail,int size, int page);

}
