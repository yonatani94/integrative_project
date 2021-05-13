package twins.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import twins.data.UserEntity;
import twins.data.UserRole;
import twins.userAPI.UserBoundary;
import twins.userAPI.UserID;

@Service
public class UserLogicImplementation implements UsersService {
	private UserDao userDao;
	private ObjectMapper jackson;

	@Autowired
	public UserLogicImplementation(UserDao userDao, ObjectMapper jackso) {
		super();
		this.userDao = userDao;
		this.jackson = new ObjectMapper();

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
		Optional<UserEntity> op = this.userDao.findById(userSpace);
		// if user does not exist, throw exception
		if (op.isPresent()) {
			UserEntity entity = op.get();
			return this.convertToBoundary(entity);
		} else {
			throw new RuntimeException(); // TODO: return status = 404 instead of status = 500
		}
	}

	@Override
	@Transactional // (readOnly = false)
	public UserBoundary updateUser(String userSpace, String userEmail, UserBoundary update) {
		Optional<UserEntity> op = this.userDao.findById(userSpace);

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
		}

		if (boundary.getUsername() != null) {
			entity.setUsername(boundary.getUsername());

		}
		if (boundary.getRole().equals(UserRole.ADMIN.name()) || boundary.getRole().equals(UserRole.MANAGER.name())
				|| boundary.getRole().equals(UserRole.PLAYER.name())) {
			entity.setRole(boundary.getRole());
		}
		if (boundary.getAvatar() != null || boundary.getAvatar().isEmpty() == true) {
			entity.setAvatar(boundary.getAvatar());
		}

		return entity;

	}

	private UserBoundary convertToBoundary(UserEntity entity) {
		UserBoundary boundary = new UserBoundary();
		if (entity.getUsername() == null || entity.getRole() == null || entity.getAvatar() == null
				|| entity.getEmail() == null) {
			throw new RuntimeException(); // TODO: return status = 404 instead of status = 500

		} else {

			boundary.setUsername(entity.getUsername());
			boundary.setUserId(new UserID(entity.getSpace(), entity.getEmail()));
			boundary.setRole(entity.getRole());
			boundary.setAvatar(entity.getAvatar());
		}

		return boundary;
	}

	// use Jackson to convert JSON to Object
	private <T> T unmarshal(String json, Class<T> type) {
		try {
			return this.jackson.readValue(json, type);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String marshal(Object moreDetails) {
		try {
			return this.jackson.writeValueAsString(moreDetails);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
