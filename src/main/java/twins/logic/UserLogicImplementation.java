package twins.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restapi.boundarys.UserBoundary;
import twins.data.UserEntity;
@Service
public class UserLogicImplementation implements UsersService {
	private UserDao userDao;
	
	@Autowired
	public UserLogicImplementation(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public UserBoundary createUser(UserBoundary user) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public UserBoundary login(String userSpace, String userEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserBoundary updateUser(String userSpace, String userEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserBoundary> getAllUsers(String adminSpace, String adminEmail) {
		Iterable<UserEntity> allEntities = this.userDao
				.findAll();
			
			List<UserBoundary> rv = new ArrayList<>();
			for (UserEntity entity : allEntities) {
				// TODO create a generic converter from entity to boundary
				UserBoundary boundary = new UserBoundary();
				
				
				 boundary.setUserId(entity.getUserId());
				 boundary.setUsername(entity.getUsername());
				 boundary.setRole(entity.getRole());
				 boundary.setAvatar(entity.getAvatar());
				 
				
				rv.add(boundary);
			}
			
			
			return rv;
	}

	@Override
	public void deleteAllUsers(String adminSpace, String adminEmail) {
		// TODO Auto-generated method stub
		
	}

}
