package twins.logic;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import twins.data.ItemEntity;
import twins.digitalItemAPI.ItemBoundary;
import twins.digitalItemAPI.ItemID;

@Service
public class ItemLogicImplementation implements ItemsService  {
	
	private ItemDao itemDao;
	private ObjectMapper jackson;
	
	
	@Autowired
	public ItemLogicImplementation(ItemDao itemDao,ObjectMapper jackson) {
		super();
		this.itemDao = itemDao;
		this.jackson =  new ObjectMapper();
	}

	@Override
	public ItemBoundary createItem(String userSpace, String userEmail, ItemBoundary item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemBoundary updateItem(String userSpace, String userEmail, String itemSpace, String itemId,
			ItemBoundary update) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItemBoundary> getAllItems(String userSpace, String userEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemBoundary getSpecificItem(String userSpace, String userEmail, String itemSpace, String itemId) {
		// TODO Auto-generated method stub
		
		Optional<ItemEntity> op = this.itemDao.findById(itemId);
			
			if (op.isPresent()) {
				ItemEntity entity = op.get();
				return this.convertToBoundary(entity);
			}else {
				throw new RuntimeException(); // TODO: return status = 404 instead of status = 500 
			}
			
		
	}

	@Override
	public void deleteAllItems(String adminSpace, String adminEmail) {
		// TODO Auto-generated method stub
		
	}
	
	private ItemBoundary convertToBoundary(ItemEntity entity) {
		ItemBoundary boundary = new ItemBoundary();
		
		boundary.setItemID(new ItemID(entity.getId() , entity.getSpace()));
		boundary.setType(entity.getType());
		boundary.setName(entity.getName());
		boundary.setActive(entity.isActive());
		boundary.setCreatedTimestamp(entity.getCreatedTimestamp());
		boundary.setCreatedBy(entity.getCreatedBy());
		boundary.setLocation(entity.getLocation());
		boundary.setItemAttributes((Map<String, Object>)this.unmarshal(entity.getItemAttributes().toString(), Map.class));
		
	
		return boundary;
	}
	
	
	// use Jackson to convert JSON to Object
	private <T> T unmarshal(String json, Class<T> type) {
		try {
			return this.jackson
				.readValue(json, type);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	


}
