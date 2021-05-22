package twins.logic;

import java.util.List;

import twins.digitalItemAPI.ItemBoundary;

public interface AdvancedItemsService extends ItemsService {
	
	
	public List<ItemBoundary> getAllItems(String userSpace, String userEmail,int size, int page);
}
