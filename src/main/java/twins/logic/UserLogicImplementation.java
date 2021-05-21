package twins.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import twins.data.UserEntity;
import twins.data.UserRole;
import twins.userAPI.NewUserDetails;
import twins.userAPI.UserBoundary;
import twins.userAPI.UserID;

@Service
public class UserLogicImplementation implements UsersService {
	private UserDao userDao;
	private String space;

	@Autowired
	public UserLogicImplementation(UserDao userDao) {
		super();
		this.userDao = userDao;

	}

	@Override
	@Transactional // (readOnly = false)
	public UserBoundary createUser(UserBoundary input) {
		// Tx - BEGIN
		UserEntity entity = this.convertFromBoundary(input);

		// store entity to database using INSERT query
		entity = this.userDao.save(entity);

		// on success: Tx COMMIT
		// on exception: Tx ROLLBACK

		return this.convertToBoundary(entity); // convert entity to boundary
	}

	@Override
	public UserBoundary login(String userSpace, String userEmail) {
		Optional<UserEntity> op = this.userDao.findById(userEmail +"$" + userSpace);
		// if user does not exist, throw exception
		if (op.isPresent()) {
			UserEntity entity = op.get();
			return this.convertToBoundary(entity);
		} else {
			throw new RuntimeException("user is not exist"); // TODO: return status = 404 instead of status = 500
		}
	}

	@Override
	@Transactional // (readOnly = false)
	public UserBoundary updateUser(String userSpace, String userEmail, UserBoundary update) {
		Optional<UserEntity> op = this.userDao.findById(userEmail +"$" + userSpace);

		if (op.isPresent()) {
			UserEntity existing = op.get();

			UserEntity updatedEntity = this.convertFromBoundary(update);

			// this can not change in user entity!
			updatedEntity.setSpace(existing.getSpace());
			updatedEntity.setEmail(existing.getEmail());

			this.userDao.save(updatedEntity);
			return this.convertToBoundary(updatedEntity);

		} else {
			throw new RuntimeException(); // TODO: return status = 404 instead of status = 500
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserBoundary> getAllUsers(String adminSpace, String adminEmail) {
		Iterable<UserEntity> allEntities = this.userDao.findAll();

		List<UserBoundary> rv = new ArrayList<>();
		for (UserEntity entity : allEntities) {
			UserBoundary boundary = convertToBoundary(entity);
			rv.add(boundary);
		}
		return rv;

	}

	@Override
	public void deleteAllUsers(String adminSpace, String adminEmail) {
		this.userDao.deleteAll();

	}

	private UserEntity convertFromBoundary(UserBoundary boundary) {
		UserEntity entity = new UserEntity();

		if (boundary.getUserId() != null) {
			entity.setEmail(boundary.getUserId().getEmail());
			entity.setSpace(boundary.getUserId().getSpace());
			entity.setEmail_space(boundary.getUserId().getEmail() + "$" + boundary.getUserId().getSpace());
		}
		else 
		{
			throw new RuntimeException("faild to get userID"); // TODO: return status = 404 instead of status = 500
		}

		if (boundary.getUsername() != null) {
			entity.setUsername(boundary.getUsername());

		}
		else 
		{
			throw new RuntimeException("faild to get user name"); // TODO: return status = 404 instead of status = 500
		}
		if (boundary.getRole().equals(UserRole.ADMIN.name()) || boundary.getRole().equals(UserRole.MANAGER.name())
				|| boundary.getRole().equals(UserRole.PLAYER.name())) {
			entity.setRole(boundary.getRole());
		}
		else 
		{
			throw new RuntimeException("faild to get role"); // TODO: return status = 404 instead of status = 500
		}
		if (boundary.getAvatar() != null || boundary.getAvatar().isEmpty() == true) {
			entity.setAvatar(boundary.getAvatar());
		}
		else 
		{
			throw new RuntimeException("faild to get avatar"); // TODO: return status = 404 instead of status = 500
		}
		//

		return entity;

	}
	@Value("${spring.application.name:dummy}")
	public void setSpace(String space) {
		this.space = space;
	}
	// TODO: 
	public UserBoundary converNewtUserDeatailsToBoundary(NewUserDetails userDeatalis)
	{
		UserBoundary boundary = new UserBoundary();
		if(userDeatalis.getEmail()== null || userDeatalis.getUsername()==null || userDeatalis.getAvatar()==null || userDeatalis.getRole()==null)
		{
			throw new RuntimeException("faild to convert in new user to boundry"); // TODO: return status = 404 instead of status = 500
		}
		else
		{
			boundary.setUsername(userDeatalis.getUsername());
			boundary.setUserId(new UserID(this.space, userDeatalis.getEmail()));
			boundary.setRole(userDeatalis.getRole());
			boundary.setAvatar(userDeatalis.getAvatar());
		}
		return boundary;
		
	}
	
	private UserBoundary convertToBoundary(UserEntity entity) {
		UserBoundary boundary = new UserBoundary();
		if (entity.getUsername() == null || entity.getRole() == null || entity.getAvatar() == null
				|| entity.getEmail() == null) {
			throw new RuntimeException("cant conert to bouddry from entity"); // TODO: return status = 404 instead of status = 500

		} else {

			boundary.setUsername(entity.getUsername());
			boundary.setUserId(new UserID(entity.getSpace(), entity.getEmail()));
			boundary.setRole(entity.getRole());
			boundary.setAvatar(entity.getAvatar());
		}

		return boundary;
	}



}
