package twins.digitalItemAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import twins.logic.ItemsService;

@RestController
public class DigitalItemRelatedController {
	private ItemsService itemsService;

	@Autowired
	public DigitalItemRelatedController(ItemsService itemsService) {
		this.itemsService = itemsService;
	}

	@RequestMapping(path = "/twins/items/{userSpace}/{userEmail}/{itemSpace}/{itemId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ItemBoundary getOperations(@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userEmail, @PathVariable("itemSpace") String itemSpace,
			@PathVariable("itemId") String itemId) {
		return this.itemsService.getSpecificItem(userSpace, userEmail, itemSpace, itemId);
	}

	@RequestMapping(path = "/twins/items/{userSpace}/{userEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ItemBoundary> getAllItems(@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userEmail) {
		return this.itemsService.getAllItems(userSpace, userEmail);
	}

	@RequestMapping(path = "/twins/items/{userSpace}/{userEmail}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ItemBoundary createItem(@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userMail, @RequestBody ItemBoundary itemBoundary) {

		return this.itemsService.createItem(userSpace, userMail, itemBoundary);
	}

	@RequestMapping(path = "/twins/items/{userSpace}/{userEmail}/{itemSpace}/{itemId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateItem(@PathVariable("userSpace") String userSpace, @PathVariable("userEmail") String userEmail,
			@PathVariable("itemSpace") String itemSpace, @PathVariable("itemId") String itemId,
			@RequestBody ItemBoundary itemBoundry) {
		this.itemsService.updateItem(userSpace, userEmail, itemSpace, itemId, itemBoundry);
	}

}
