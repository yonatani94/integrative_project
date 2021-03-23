package demo.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.boundarys.ItemBoundary;
import demo.boundarys.UserBoundary;
import demo.models.CreatedBy;
import demo.models.ItemID;
import demo.models.Location;
import demo.models.UserID;

@RestController
public class DigitalItemRelatedController {

	@RequestMapping(path = "/twins/items/{userSpace}/{userEmail}/{itemSpace}/{itemId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ItemBoundary getOperations(@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userEmail) {

		// Maps for testing
		Map<String, Object> itemAttributes = new HashMap<>();
		itemAttributes.put("key1", "can be set to any value");
		itemAttributes.put("key3", 58);
		itemAttributes.put("key4", false);

		ItemBoundary rv = new ItemBoundary(new ItemID(userSpace, "99"), "demoType", "demo Item", true, new Date(),
				new CreatedBy(new UserID(userSpace, userEmail)), new Location(32.115139, 34.817804), itemAttributes);
		System.out.println(rv);
		return rv;
	}

	@RequestMapping(path = "/twins/items/{userSpace}/{userEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ItemBoundary[] getUser(@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userEmail) {

		// Maps for testing
		Map<String, Object> itemAttributes = new HashMap<>();
		itemAttributes.put("key1", "can be set to any value");
		itemAttributes.put("key3", 58);
		itemAttributes.put("key4", false);

		ItemBoundary[] rv = new ItemBoundary[] { new ItemBoundary(new ItemID(userSpace, "99"), "demoType", "demo Item",
				true, new Date(), new CreatedBy(new UserID(userSpace, userEmail)), new Location(32.115139, 34.817804),
				itemAttributes) };

		System.out.println(rv);
		return rv;
	}

}
