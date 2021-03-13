package demo.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.boundarys.OperationBoundary;
import demo.models.ItemID;
import demo.models.OperationId;
import demo.models.UserID;

@RestController
public class OperationController {

	@RequestMapping(path = "/twins/admin/operations/{userSpace}/{userEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public OperationBoundary[] getOperations(@PathVariable("userSpace") String userSpace,
			@PathVariable("userEmail") String userEmail) {

		// Maps for testing
		Map<String, Object> nestedOpAttributes = new HashMap<>();
		nestedOpAttributes.put("Key2Subkey", "can be nested json");
		nestedOpAttributes.put("Subkey2", "SubValue2");
		Map<String, Object> opAttributes = new HashMap<>();
		opAttributes.put("key1", "can be set to any value you wish");
		opAttributes.put("Key2", nestedOpAttributes);
		Map<String, Object> invokedByMap = new HashMap<>();
		invokedByMap.put("userId", new UserID(userSpace, userEmail));
		Map<String, Object> itemIdArray = new HashMap<>();
		itemIdArray.put("itemId", new ItemID(userSpace, "testId1"));

		OperationBoundary[] rv = new OperationBoundary[] { new OperationBoundary(new OperationId(userSpace, userEmail),
				"operationType", itemIdArray, new Date(), invokedByMap, opAttributes) };
		System.out.println(rv);
		return rv;
	}
}
