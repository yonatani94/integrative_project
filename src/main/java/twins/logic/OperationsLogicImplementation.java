package twins.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import twins.data.ItemEntity;
import twins.data.OperationEntity;
import twins.data.UserEntity;
import twins.digitalItemAPI.Item;
import twins.digitalItemAPI.ItemBoundary;
import twins.digitalItemAPI.ItemID;
import twins.operationsAPI.InvokedBy;
import twins.operationsAPI.OperationBoundary;
import twins.operationsAPI.OperationId;
import twins.userAPI.UserBoundary;
import twins.userAPI.UserID;

@Service
public class OperationsLogicImplementation implements AdvancedOperationsService {

	private final String ADD_BUILDING = "ADD_BUILDING";
	private final String ADD_APARTMENT = "ADD_APARTMENT";
	private final String ADD_FACILITY = "ADD_FACILITY";
	private final String FETCH_APARTMENTS_BY_ID = "FETCH_APARTMENTS_BY_ID";

	private OperationDao operationDao;
	private ObjectMapper jackson;
	private AtomicLong atomicLong;
	private UserDao userDao;
	private ItemDao itemDao;
	private JmsTemplate jmsTemplate;

	@Autowired
	public OperationsLogicImplementation(OperationDao operationDao, ObjectMapper jackson, ItemDao itemDao) {
		super();
		this.operationDao = operationDao;
		this.itemDao = itemDao;
		this.jackson = new ObjectMapper();
		this.atomicLong = new AtomicLong(1L);
	}

	@Autowired
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@Override
	@Transactional // (readOnly = false)
	public Object invokeOperation(OperationBoundary operation) {
		System.out.println(operation.toString());

		switch (operation.getType()) {

		// Get all apartments of a specific building and return a list of apartments
		case FETCH_APARTMENTS_BY_ID:
			return (fetchApartmentsFromBuilding(operation));
		case ADD_BUILDING:
			addBuildingToProject(operation);
			break;
		case ADD_FACILITY:
			break;
		default:
			break;
		}

		OperationEntity entity = this.convertFromBoundary(operation);
		entity.setCreatedTimestamp(new Date());
		entity.setOperationID("" + this.atomicLong.getAndIncrement());

		// store entity to database using INSERT query
		entity = this.operationDao.save(entity);

		return this.convertToBoundary(entity); // convert entity to boundary
	}

	/** A method to add the building to a project */
	private void addBuildingToProject(OperationBoundary operation) {
		// Add building to the DB with parentID
		//

	}

	/** Fetch all apartments from a building by building ID */

	private Object fetchApartmentsFromBuilding(OperationBoundary operation) {
		System.out.println("fetchApartmentsFromBuilding");

		// I want to fetch all the apartments of building X
		// I will iterate through all items which are of type "Apartment"
		// I will add the apartments which their parent is building X to a list
		// I will return the list of the apartments
		// {
		// "operationId": {
		// "space": "2021b.twins",
		// "id": "451"
		// },
		// "type": "GET_APARTMENTS",
		// "item": {
		// "itemId": {
		// "space": "2021b.twins", ->>> It is possible to sent the building itemID here
		// "id": "99"
		// }
		// },
		// "createdTimestamp": "2021-03-07T09:57:13.109+0000",
		// "invokedBy": {
		// "userId": {
		// "space": "2021b.twins",
		// "email": "temp@mail.com"
		// }
		// },
		// "operationAttributes": {
		// "key1": "can be set to any value you wish", ->>> It is possible to send the
		// building itemID here
		// "key2": {
		// "key2Subkey1": "can be nested json"
		// }
		// }
		// }

		String desiredBuildingId = operation.getItem().getItemId().getId(); // the desired building id

		List<ItemEntity> apartmentList = this.itemDao.findAllByType("Apartment"); // list of all apartments
		List<ItemEntity> relevantApartments = new ArrayList<>();

		for (ItemEntity e : apartmentList) {
			String tempAttributes = e.getItemAttributes(); // Get the extra Attributes Json
			try {
				JSONObject obj = new JSONObject(tempAttributes); // Extract Json from attributes
				String tempParentId = obj.getString("parentID"); // Get the parent ID from the Json
				if (tempParentId.equals(desiredBuildingId)) { // If it is the correct Id
					relevantApartments.add(e); // Add proper apartment
				}
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		
		for(ItemEntity e:relevantApartments) {
			System.out.println(e.toString());
		}
		
		return relevantApartments;
	}

	@Override
	@Transactional // (readOnly = false)
	public OperationBoundary invokeAsynchronousOperation(OperationBoundary operation) {

//		try {
//			Thread.sleep(20000); // sleep test 30 seconds
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

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
				Page<OperationEntity> pageOfEntities = this.operationDao
						.findAll(PageRequest.of(page, size, Direction.ASC, "operationID"));

				List<OperationEntity> entities = pageOfEntities.getContent();
				List<OperationBoundary> rv = new ArrayList<>();
				for (OperationEntity entity : entities) {
					OperationBoundary boundary = convertToBoundary(entity);
					rv.add(boundary);
				}
				return rv;
			}

		} else {
			throw new UserNotFoundException(); // TODO: return status = 404 instead of status = 500

		}
		throw new RuntimeException("user is not admin");

	}

	@Override
	@Transactional
	public OperationBoundary sendAndForget(OperationBoundary operation) {
		try {
			// Generate a new random ID
			operation.setOperationId(
					new OperationId(operation.getOperationId().getSpace(), UUID.randomUUID().toString()));
			String json = this.jackson.writeValueAsString(operation);
			// Send a message to the destenation
			this.jmsTemplate.send("MyOperations", session -> session.createTextMessage(json));
			return operation; // meanwhile return something to the client

		} catch (JsonProcessingException e) {
			System.out.println("Exception: " + e.getMessage());
			throw new RuntimeException();
		}
	}

}
