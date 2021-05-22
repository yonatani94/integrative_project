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

import twins.data.OperationEntity;
import twins.data.UserEntity;
import twins.digitalItemAPI.Item;
import twins.digitalItemAPI.ItemID;
import twins.operationsAPI.InvokedBy;
import twins.operationsAPI.OperationBoundary;
import twins.operationsAPI.OperationId;
import twins.userAPI.UserBoundary;
import twins.userAPI.UserID;

@Service
public class OperationsLogicImplementation implements AdvancedOpretionsService {
	private OperationDao operationDao;
	private ObjectMapper jackson;
	private AtomicLong atomicLong;
	private UserDao userDao;


	@Autowired
	public OperationsLogicImplementation(OperationDao operationDao, ObjectMapper jackson) {
		super();
		this.operationDao = operationDao;
		this.jackson = new ObjectMapper();
		this.atomicLong = new AtomicLong(1L);
	}

	@Override
	@Transactional // (readOnly = false)
	public Object invokeOperation(OperationBoundary operation) {
		System.out.println(operation.toString());

		// Tx - BEGIN

		OperationEntity entity = this.convertFromBoundary(operation);
		entity.setCreatedTimestamp(new Date());
		entity.setOperationID("" + this.atomicLong.getAndIncrement());

		// store entity to database using INSERT query
		entity = this.operationDao.save(entity);

		// on success: Tx COMMIT
		// on exception: Tx ROLLBACK

		return this.convertToBoundary(entity); // convert entity to boundary
	}

	@Override
	public OperationBoundary invokeAsynchronousOperation(OperationBoundary operation) {

		System.out.println(operation.toString());

		// Tx - BEGIN

		OperationEntity entity = this.convertFromBoundary(operation);
		entity.setCreatedTimestamp(new Date());
		entity.setOperationID("" + this.atomicLong.getAndIncrement());

		// store entity to database using INSERT query
		entity = this.operationDao.save(entity);

		// on success: Tx COMMIT
		// on exception: Tx ROLLBACK

		return this.convertToBoundary(entity); // convert entity to boundary
	}

	@Override
	@Transactional(readOnly = true)
	public List<OperationBoundary> getAllOperations(String adminSpace, String adminEmail) {
		// Transaction - BEGIN

		Iterable<OperationEntity> allEntities = this.operationDao.findAll();

		List<OperationBoundary> rv = new ArrayList<>();
		for (OperationEntity entity : allEntities) {
			OperationBoundary boundary = convertToBoundary(entity);

			rv.add(boundary);
		}

		// Transaction: on success invocation - COMMIT
		// Transaction: on error - ROLLBACK
		return rv;
	}

	@Override
	@Transactional // (readOnly = false)
	public void deleteAllOperations(String adminSpace, String adminEmail) {
		
		Optional<UserEntity> op = this.userDao.findById(adminEmail + "$" + adminSpace);
		if (op.isPresent()) {
			if (UserLogicImplementation.isUserAdmin(op))
				this.operationDao.deleteAll();
		}

		else {
			throw new UserNotFoundException(); // TODO: return status = 404 instead of status = 500

		}
	}

	private OperationEntity convertFromBoundary(OperationBoundary boundary) {
		OperationEntity entity = new OperationEntity();

		if (boundary.getType() != null) { // Check for valid operation type
			entity.setType(boundary.getType());
		}

		if (boundary.getItem().getItemId() != null) { // Check for valid item ID
			ItemID temp = boundary.getItem().getItemId();
			entity.setItemID(temp.getId());
			entity.setItemSpace(temp.getSpace());
		}

		if (boundary.getInvokedBy().getUserId() != null) { // Check for valid user ID
			UserID temp = boundary.getInvokedBy().getUserId();
			entity.setInvokedByUserEmail(temp.getEmail());
			entity.setInvokedByUserSpace(temp.getSpace());
		} else {
			System.out.println("User id is null! invoked by: " + boundary.getInvokedBy().toString());
		}

		entity.setOperationSpace(boundary.getOperationId().getSpace());
		entity.setOperationID(boundary.getOperationId().getId());
		entity.setCreatedTimestamp(boundary.getCreatedTimestamp());
		entity.setOperationAttributes(this.marshal(boundary.getOperationAttributes()));

		return entity;

	}

	private OperationBoundary convertToBoundary(OperationEntity entity) {
		
		OperationBoundary boundary = new OperationBoundary();

		boundary.setOperationId(new OperationId(entity.getOperationSpace(), entity.getOperationID()));
		boundary.setType(entity.getType());
		boundary.setItem(new Item(new ItemID(entity.getItemSpace(), entity.getItemID())));
		boundary.setCreatedTimestamp(entity.getCreatedTimestamp());
		boundary.setInvokedBy(
				new InvokedBy(new UserID(entity.getInvokedByUserSpace(), entity.getInvokedByUserEmail())));
		boundary.setOperationAttributes(
				(Map<String, Object>) this.unmarshal(entity.getOperationAttributes(), Map.class));

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

	@Override
	public List<OperationBoundary> getAllOperations(String userSpace, String userEmail, int size, int page) {
		
		Optional<UserEntity> op = this.userDao.findById(userEmail + "$" + userSpace);
		if (op.isPresent()) {
			if (UserLogicImplementation.isUserAdmin(op)) {
				Page<OperationEntity> pageOfEntities = this.operationDao.findAll(PageRequest.of(page, size,Direction.ASC,"operationID"));
				
				List<OperationEntity> entities = pageOfEntities.getContent();
				List<OperationBoundary> rv = new ArrayList<>();
				for (OperationEntity entity : entities) {
					OperationBoundary boundary = convertToBoundary(entity);
					rv.add(boundary);
				}
				return rv;
			}
			
		}
		else {
			throw new UserNotFoundException(); // TODO: return status = 404 instead of status = 500

		}
		throw new RuntimeException("user is not admin");

	}
	

}
