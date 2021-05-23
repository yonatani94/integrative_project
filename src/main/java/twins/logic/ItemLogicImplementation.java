package twins.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import twins.data.ItemEntity;
import twins.data.UserEntity;
import twins.data.UserRole;
import twins.digitalItemAPI.CreatedBy;
import twins.digitalItemAPI.ItemBoundary;
import twins.digitalItemAPI.ItemID;
import twins.digitalItemAPI.Location;
import twins.userAPI.UserBoundary;
import twins.userAPI.UserID;

@Service
public class ItemLogicImplementation implements AdvancedItemsService {

	private ItemDao itemDao;
	private ObjectMapper jackson;
	private AtomicLong atomicLong;
	private UserDao userDao;

	@Autowired
	public ItemLogicImplementation(ItemDao itemDao, ObjectMapper jackson,UserDao userDao) {
		super();
		this.itemDao = itemDao;
		this.jackson = new ObjectMapper();
		this.atomicLong = new AtomicLong();
		this.userDao = userDao;

	}

	@Override
	@Transactional // (readOnly = false)
	public ItemBoundary createItem(String userSpace, String userEmail, ItemBoundary item) {

		Optional<UserEntity> op = this.userDao.findById(userEmail + "$" + userSpace);

		if (op.isPresent()) {
			if (isUserManger(op)) {
				ItemEntity i = this.convertFromBoundary(item);
				i.setCreatedTimestamp(new Date());
				i.setId("" + this.atomicLong.getAndIncrement());
				i.setEmail(userEmail);
				i.setSpace(userSpace);
				i.setIdSpace(userEmail + "$" + userSpace);

				// store entity to database using INSERT query
				i = this.itemDao.save(i);

				return this.convertToBoundary(i);
			}

		}

		else {
			throw new UserNotFoundException(); // TODO: return status = 404 instead of status = 500

		}
		throw new RuntimeException("item cant create without permssion for MANGER"); // TODO: return status = 404
																						// instead of status = 500
	}

	@Override
	@Transactional // (readOnly = false)
	public ItemBoundary updateItem(String userSpace, String userEmail, String itemSpace, String itemId,
			ItemBoundary update) {
		Optional<UserEntity> op_user = this.userDao.findById(userEmail + "$" + userSpace);
		Optional<ItemEntity> op_item = this.itemDao.findById(userEmail + "$" + userSpace);

		ItemEntity updatedEntity;
		if (op_user.isPresent() && op_item.isPresent()) {
			if (isUserManger(op_user)) {
				ItemEntity existing = op_item.get();

				updatedEntity = this.convertFromBoundary(update);

				updatedEntity.setId(existing.getId());
				updatedEntity.setSpace(existing.getEmail());
				updatedEntity.setEmail(existing.getEmail());
				updatedEntity.setItemAttributes(this.marshal(update.getItemAttributes()));
				updatedEntity.setActive(update.getActive());
				updatedEntity.setCreatedTimestamp(existing.getCreatedTimestamp());
				updatedEntity.setLog(update.getLocation().getLog());
				updatedEntity.setLat(update.getLocation().getLat());
				updatedEntity.setType(update.getType());
				updatedEntity.setName(existing.getName());
				updatedEntity.setIdSpace(existing.getEmail() + "$" + existing.getSpace());

				this.itemDao.save(updatedEntity);
				return this.convertToBoundary(updatedEntity);

			}
		} else {
			throw new RuntimeException(); // TODO: return status = 404 instead of status = 500
		}
		throw new RuntimeException("item cant update without permssion for MANGER"); // TODO: return status = 404
																						// instead of status = 500
	}

	@Override
	@Transactional(readOnly = true)
	public List<ItemBoundary> getAllItems(String userSpace, String userEmail) {
		List<ItemBoundary> rv = new ArrayList<>();
		Optional<UserEntity> op = this.userDao.findById(userEmail + "$" + userSpace);

		if (op.isPresent()) {
			if (isUserManger(op)) {
				Iterable<ItemEntity> allEntities = this.itemDao.findAll();

				for (ItemEntity entity : allEntities) {
					ItemBoundary boundary = convertToBoundary(entity);

					rv.add(boundary);
				}

			}
			return rv;

		} else
			throw new UserNotFoundException();
	}

	@Override
	@Transactional(readOnly = true)
	public ItemBoundary getSpecificItem(String userSpace, String userEmail, String itemSpace, String itemId) {
		// TODO Auto-generated method stub

		Optional<ItemEntity> op_item = this.itemDao.findById(userEmail + "$" + userSpace);
		Optional<UserEntity> op_user = this.userDao.findById(userEmail + "$" + userSpace);

		if (op_user.isPresent() && op_item.isPresent()) {
			if (isUserManger(op_user)) {
				ItemEntity entity = op_item.get();
				return this.convertToBoundary(entity);
			}
			
		} else {
			throw new UserNotFoundException(); // TODO: return status = 404 instead of status = 500
		}
		throw new RuntimeException();

	}

	@Override
	@Transactional // (readOnly = false)
	public void deleteAllItems(String adminSpace, String adminEmail) {
		Optional<UserEntity> op = this.userDao.findById(adminEmail + "$" + adminSpace);
		if (op.isPresent()) {
			if (UserLogicImplementation.isUserAdmin(op))
				this.itemDao.deleteAll();
		}

		else {
			throw new UserNotFoundException(); // TODO: return status = 404 instead of status = 500

		}

	}

	private ItemBoundary convertToBoundary(ItemEntity entity) {
		ItemBoundary boundary = new ItemBoundary();

		boundary.setItemId(new ItemID(entity.getSpace(), entity.getId()));
		if (entity.getType() == null || entity.getName() == null) {
			throw new RuntimeException(); // TODO: return status = 404 instead of status = 500
		} else {
			boundary.setType(entity.getType());
			boundary.setName(entity.getName());
		}
		boundary.setActive(entity.isActive());
		boundary.setCreatedTimestamp(entity.getCreatedTimestamp());
		boundary.setCreatedBy(new CreatedBy(new UserID(entity.getSpace(), entity.getEmail())));
		boundary.setLocation(new Location(entity.getLat(), entity.getLog()));
		boundary.setItemAttributes(
				((Map<String, Object>) this.unmarshal(entity.getItemAttributes().toString(), Map.class)));

		return boundary;
	}

	private ItemEntity convertFromBoundary(ItemBoundary boundary) {
		ItemEntity entity = new ItemEntity();
		if (boundary.getItemId() != null) {
			// TODO: Split here with '$' to get id and space
			entity.setId(boundary.getItemId().getId());
			entity.setSpace(boundary.getItemId().getSpace());
			entity.setIdSpace(entity.getEmail() + "$" + entity.getSpace());
		} else {
			System.out.println("Item id is null!");

		}

		if (boundary.getCreatedBy() != null) {
			entity.setEmail(boundary.getCreatedBy().getUserId().getEmail());
		} else {
			System.out.println("Created By is null!");
		}

		if (boundary.getName() != null) {
			entity.setName(boundary.getName());
		} else {
			System.out.println("Name is null!");
		}

		entity.setType(boundary.getType());
		entity.setActive(boundary.getActive());
		entity.setCreatedTimestamp(boundary.getCreatedTimestamp());
		entity.setLog(boundary.getLocation().getLog());
		entity.setLat(boundary.getLocation().getLat());

		String json = this.marshal(boundary.getItemAttributes());
		entity.setItemAttributes(json);

		return entity;

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

	@Override
	public List<ItemBoundary> getAllItems(String userSpace, String userEmail, int size, int page) {

		Page<ItemEntity> pageOfEntities = this.itemDao
				.findAll(PageRequest.of(page, size, Direction.ASC, "id", "createdTimestamp"));

		List<ItemEntity> entities = pageOfEntities.getContent();
		List<ItemBoundary> rv = new ArrayList<>();
		for (ItemEntity entity : entities) {
			ItemBoundary boundary = convertToBoundary(entity);
			rv.add(boundary);
		}
		return rv;

	}

	@Override
	@Transactional(readOnly = true)
	public List<ItemBoundary> getActiveItemsOnly(int size, int page) {
		List<ItemEntity> allImportantEntities = this.itemDao.findAllByActive(true,
				PageRequest.of(page, size, Direction.DESC, "messageTimestamp", "id"));

		List<ItemBoundary> rv = new ArrayList<>();
		for (ItemEntity entity : allImportantEntities) {
			ItemBoundary boundary = this.convertToBoundary(entity);
			rv.add(boundary);
		}
		return rv;
	}

	public static boolean isUserManger(Optional<UserEntity> op) {
		UserEntity existing = op.get();

		if (existing.getRole().toString().equals(UserRole.MANAGER.name())) {

			return true;
		} else {
			throw new RuntimeException("user is not Manager");
		}
	}

}
